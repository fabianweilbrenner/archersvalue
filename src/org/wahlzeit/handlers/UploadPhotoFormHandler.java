/*
 * Copyright (c) 2006-2009 by Dirk Riehle, http://dirkriehle.com
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */

package org.wahlzeit.handlers;

import java.util.*;
import java.io.*;

import org.wahlzeit.model.*;
import org.wahlzeit.services.*;
import org.wahlzeit.utils.*;
import org.wahlzeit.webparts.*;

import de.weilbrenner.archersvalue.Archery;
import de.weilbrenner.archersvalue.ArcheryFactory;
import de.weilbrenner.archersvalue.ArcheryPhoto;
import de.weilbrenner.archersvalue.BowCategory;
import de.weilbrenner.archersvalue.CompetitionCategory;
import de.weilbrenner.archersvalue.DrawWeight;
import de.weilbrenner.archersvalue.bow.Bow;
import de.weilbrenner.archersvalue.bow.BowManager;
import de.weilbrenner.archersvalue.bow.BowType;
import de.weilbrenner.archersvalue.bow.BowTypeManager;
import de.weilbrenner.archersvalue.location.GPSLocation;
import de.weilbrenner.archersvalue.location.LocationException;
import de.weilbrenner.archersvalue.location.MapcodeLocation;

/**
 * 
 * @author dirkriehle
 *
 */
public class UploadPhotoFormHandler extends AbstractWebFormHandler {
	
	/**
	 *
	 */
	public UploadPhotoFormHandler() {
		initialize(PartUtil.UPLOAD_PHOTO_FORM_FILE, AccessRights.USER);
	}
	
	/**
	 * 
	 */
	protected void doMakeWebPart(UserSession us, WebPart part) {
		Map<String, Object> args = us.getSavedArgs();
		part.addStringFromArgs(args, UserSession.MESSAGE);

		part.maskAndAddStringFromArgs(args, Photo.TAGS);
	}
	
	/**
	 * 
	 */
	protected String doHandlePost(UserSession us, Map args) {
		String tags = us.getAndSaveAsString(args, Photo.TAGS);

		if (!StringUtil.isLegalTagsString(tags)) {
			us.setMessage(us.cfg().getInputIsInvalid());
			return PartUtil.UPLOAD_PHOTO_PAGE_NAME;
		}

		try {
			PhotoManager pm = PhotoManager.getInstance();
			String sourceFileName = us.getAsString(args, "fileName");
			File file = new File(sourceFileName);
			Photo photo = pm.createPhoto(file);
			
			
			if(photo instanceof ArcheryPhoto) {
				ArcheryPhoto archPhoto = (ArcheryPhoto)photo;
				
				String drawWeightUnit = us.getAsString(args, "drawWeightUnit");
				int drawWeightValue;
				try {
					drawWeightValue = Integer.parseInt(us.getAsString(args, "drawWeightValue"));
				} catch(Exception e) {
					drawWeightValue = 0;
				}
				
				ArcheryFactory factory = ArcheryFactory.getInstance();
				
				BowCategory bowCat = factory.createBowCategory(us.getAsString(args, "bowCategory"));
				CompetitionCategory compCat = factory.createCompetitionCategory(us.getAsString(args, "competitionCategory"));
				DrawWeight drawWeight = factory.createDrawWeight(drawWeightValue, DrawWeight.Units.getFromString(drawWeightUnit));
		
				BowType bowType = factory.createBowType(us.getAsString(args, "manufacturer"), 
														us.getAsString(args, "riser"), 
														us.getAsString(args, "limbs"));
				BowTypeManager.getInstance().addBowType(bowType);
				BowTypeManager.getInstance().saveBowType(bowType);
				
				Bow bow = factory.createBow(bowType, Integer.parseInt(us.getAsString(args, "constructionYear")));
				BowManager.getInstance().addBow(bow);
				BowManager.getInstance().saveBow(bow);
				
				Archery archery = factory.createArcheryObject(bowCat, compCat, drawWeight, bow);
				
				archPhoto.setArchery(archery);				
			}
			

			String targetFileName = SysConfig.getBackupDir().asString() + photo.getId().asString();
			createBackup(sourceFileName, targetFileName);
		
			User user = (User) us.getClient();
			user.addPhoto(photo); 
			
			photo.setTags(new Tags(tags));
			
			String locationType = us.getAsString(args, "locationType");
			String locationString = us.getAsString(args, "location");
			
			if(!locationString.equals("")) {
				if(locationType.equals("GPS")) {
					photo.setLocation(new GPSLocation(locationString));
				} else if(locationType.equals("MAPCODE")) {
					photo.setLocation(new MapcodeLocation(locationString));
				}
			}	

			pm.savePhoto(photo);

			StringBuffer sb = UserLog.createActionEntry("UploadPhoto");
			UserLog.addCreatedObject(sb, "Photo", photo.getId().asString());
			UserLog.log(sb);
			
			us.setTwoLineMessage(us.cfg().getPhotoUploadSucceeded(), us.cfg().getKeepGoing());
		} catch(LocationException locEx) {
			SysLog.logThrowable(locEx);
			us.setMessage(us.cfg().getPhotoUploadLocationFailed());
		} catch (Exception ex) {
			SysLog.logThrowable(ex);
			us.setMessage(us.cfg().getPhotoUploadFailed());
		}
		
		return PartUtil.UPLOAD_PHOTO_PAGE_NAME;
	}
	
	/**
	 * 
	 */
	protected void createBackup(String sourceName, String targetName) {
		try {
			File sourceFile = new File(sourceName);
			InputStream inputStream = new FileInputStream(sourceFile);
			File targetFile = new File(targetName);
			OutputStream outputStream = new FileOutputStream(targetFile);
			// @FIXME IO.copy(inputStream, outputStream);
		} catch (Exception ex) {
			SysLog.logSysInfo("could not create backup file of photo");
			SysLog.logThrowable(ex);			
		}
	}
}
