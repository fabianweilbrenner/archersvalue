package org.wahlzeit.model.location;

import java.util.List;
import java.util.StringTokenizer;

import com.mapcode.Mapcode;
import com.mapcode.MapcodeCodec;

/**
 * 
 * @author Fabian Weilbrenner
 *
 */
public class GPSLocation extends AbstractLocation {

	///////////////////////////////////
	/// Fields
	///////////////////////////////////
	private final String GPS_DELIMITER = ",";
	
	
	///////////////////////////////////
	/// Constructor
	///////////////////////////////////
	/**
	 * 
	 * @methodtype constructor
	 */
	public GPSLocation(GPSLocation gpsLocation) {
		this(new String(gpsLocation.getFirstComponent()),
			 new String(gpsLocation.getSecondComponent()));
	}
	
	/**
	 * 
	 * @methodtype constructor
	 */
	public GPSLocation(String gpsString) {
		this();
		parseLocationString(gpsString);
	}
	
	/**
	 * 
	 * @methodtype constructor
	 */
	public GPSLocation(double latitude, double longitude) {
		this(""+latitude, ""+longitude);
	}
	
	/**
	 * 
	 * @methodtype constructor
	 */
	public GPSLocation(String latitude, String longitude) {
		this();
		setComponents(latitude, longitude);
	}
	
	/**
	 * 
	 * @methodtype constructor
	 */
	private GPSLocation() {
		super();
		initialize();
	}
	
	
	///////////////////////////////////
	/// Methods
	///////////////////////////////////
	/**
	 * 
	 * @methodtype assertion
	 */
	@Override
	protected void assertConvertTo(Class<? extends Location> classToConvert) throws Exception {
		if (!Location.class.isAssignableFrom(classToConvert)) {
			throw new Exception("Convertion Error");
		}
	}

	/**
	 * 
	 * @methodtype convertion
	 */
	@Override
	protected Location doConvertTo(Class<? extends Location> classToConvert) {
		Location convertedLocation = null;
		
		if(classToConvert.equals(GPSLocation.class)) {
			convertedLocation = new GPSLocation(this);
		} else if(classToConvert.equals(MapcodeLocation.class)) {
			double fComp = Double.parseDouble(getFirstComponent());
			double sComp = Double.parseDouble(getSecondComponent());
			List<Mapcode> mcList = MapcodeCodec.encode(fComp, sComp);
			if(mcList.size() > 0) {
				convertedLocation = new MapcodeLocation(mcList.get(0).asInternationalISO());
			}
		}	
		
		return convertedLocation;
	}
	
	/**
	 * 
	 * @methodtype command
	 */
	@Override
	protected void parseLocationString(String locationString) {
		StringTokenizer strTokenizer = new StringTokenizer(locationString, GPS_DELIMITER);
		
		if(strTokenizer.countTokens() == 2) {
			String latitude = strTokenizer.nextToken().trim();
			String longitude = strTokenizer.nextToken().trim();
			setComponents(latitude, longitude);
		}
	}
	
	/**
	 * 
	 * @methodtype boolean query
	 */
	@Override
	protected boolean areValidComponents(String[] components) {
		try {
			Double.parseDouble(components[0]);
			Double.parseDouble(components[1]);
		} catch(Exception e) {
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * @methodtype initialization
	 */
	private void initialize() {
		setDelimiter(GPS_DELIMITER);
	}

}
