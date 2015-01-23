package de.weilbrenner.archersvalue;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoFactory;
import org.wahlzeit.model.PhotoId;
import org.wahlzeit.services.SysLog;

/**
 * ArcheryPhotoFactory creates new instances of {@link ArcheryPhoto}.
 * 
 * @collaboration Archery, Factory
 * @author Fabian Weilbrenner
 *
 */
public class ArcheryPhotoFactory extends PhotoFactory {
	
	private static ArcheryPhotoFactory instance;
	
	/**
	 * Public singleton access method.
	 */
	public static synchronized PhotoFactory getInstance() {
		if (instance == null) {
			SysLog.logSysInfo("setting ArcheryPhotoFactory");
			instance = new ArcheryPhotoFactory();
		}
		
		return instance;
	}
	
	/**
	 * @methodtype factory
	 */
	@Override
	public Photo createPhoto() {
		return new ArcheryPhoto();
	}
	
	/**
	 * 
	 * @methodtype factory
	 */
	@Override
	public Photo createPhoto(PhotoId id) {
		return new ArcheryPhoto(id);
	}
	
	/**
	 * 
	 * @methodtype factory
	 */
	@Override
	public Photo createPhoto(ResultSet rs) throws SQLException {
		return new ArcheryPhoto(rs);
	}

}
