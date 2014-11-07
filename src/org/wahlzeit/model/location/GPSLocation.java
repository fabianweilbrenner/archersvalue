package org.wahlzeit.model.location;

import java.util.StringTokenizer;

import com.mapcode.MapcodeCodec;
import com.mapcode.Point;
import com.mapcode.UnknownMapcodeException;

/**
 * 
 * @author Fabian Weilbrenner
 *
 */
public class GPSLocation extends AbstractLocation {

	///////////////////////////////////
	/// Fields
	///////////////////////////////////
	private final String GPS_DELIMITER = " ";
	
	
	///////////////////////////////////
	/// Constructor
	///////////////////////////////////
	public GPSLocation(GPSLocation gpsLocation) {
		this(new String(gpsLocation.getFirstComponent()),
			 new String(gpsLocation.getSecondComponent()));
	}
	
	public GPSLocation(String gpsString) {
		this();
		parseLocationString(gpsString);
	}
	
	public GPSLocation(double latitude, double longitude) {
		this(""+latitude, ""+longitude);
	}
	
	public GPSLocation(String latitude, String longitude) {
		this();
		setComponents(latitude, longitude);
	}
	
	private GPSLocation() {
		super();
		initialize();
	}
	
	
	///////////////////////////////////
	/// Methods
	///////////////////////////////////
	@Override
	protected void assertConvertTo(Location location) throws Exception {
		if(!(location instanceof GPSLocation) && !(location instanceof MapcodeLocation)) {
			throw new Exception("Convertion Error");
		}
	}

	@Override
	protected Location doConvertTo(Location location) {
		Location convertedLocation = null;
		
		if(location instanceof GPSLocation) {
			convertedLocation = new GPSLocation((GPSLocation)location);
		} else if(location instanceof MapcodeLocation) {
			try {
				Point p = MapcodeCodec.decode(location.asString());
				convertedLocation = new GPSLocation(p.getLatDeg(), p.getLonDeg());
			} catch (IllegalArgumentException | UnknownMapcodeException e) {
				//e.printStackTrace();
			}
		}
		
		return convertedLocation;
	}
	
	@Override
	protected void parseLocationString(String locationString) {
		StringTokenizer strTokenizer = new StringTokenizer(locationString, GPS_DELIMITER);
		
		if(strTokenizer.countTokens() == 2) {
			String latitude = strTokenizer.nextToken();
			String longitude = strTokenizer.nextToken();
			setComponents(latitude, longitude);
		}
	}
	
	private void initialize() {
		setDelimiter(GPS_DELIMITER);
	}

}
