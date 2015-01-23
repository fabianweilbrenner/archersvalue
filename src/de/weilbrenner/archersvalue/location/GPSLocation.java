package de.weilbrenner.archersvalue.location;

import java.util.List;
import java.util.StringTokenizer;

import com.mapcode.Mapcode;
import com.mapcode.MapcodeCodec;

/**
 * 
 * @collaboration Location
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
		doSetComponents(gpsLocation.getComponents());
	}
	
	/**
	 * 
	 * @throws LocationException 
	 * @methodtype constructor
	 */
	public GPSLocation(String gpsString) throws LocationException {
		this();
		parseLocationString(gpsString);
	}
	
	/**
	 * 
	 * @methodtype constructor
	 */
	public GPSLocation(double latitude, double longitude) {
		this();
		doSetComponents(new String[] { ""+latitude, ""+longitude });
	}
	
	/**
	 * 
	 * @throws LocationException 
	 * @methodtype constructor
	 */
	public GPSLocation(String latitude, String longitude) throws LocationException {
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
			throw new LocationException("Convertion Error");
		}
	}

	/**
	 * 
	 * @throws LocationException 
	 * @methodtype convertion
	 */
	@Override
	protected Location doConvertTo(Class<? extends Location> classToConvert) throws LocationException {
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
	 * @throws LocationException 
	 * @methodtype command
	 */
	@Override
	protected void parseLocationString(String locationString) throws LocationException {
		StringTokenizer strTokenizer = new StringTokenizer(locationString, GPS_DELIMITER);
		
		if(strTokenizer.countTokens() == 2) {
			String latitude = strTokenizer.nextToken().trim();
			String longitude = strTokenizer.nextToken().trim();
			setComponents(latitude, longitude);
		} else {
			throw new LocationException("Could not parse the string into the GPS components.");
		}
	}
	
	/**
	 * 
	 * @throws LocationException 
	 * @methodtype boolean query
	 */
	@Override
	protected boolean assertSetComponents(String[] components) throws LocationException {
		try {
			Double.parseDouble(components[0]);
			Double.parseDouble(components[1]);
		} catch(Exception e) {
			throw new LocationException("Invalid GPS components");
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
