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
	protected void assertConvertTo(Class<?> classToConvert) throws Exception {
		if (!Location.class.isAssignableFrom(classToConvert)) {
			throw new Exception("Convertion Error");
		}
	}

	@Override
	protected Location doConvertTo(Class<?> classToConvert) {
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
	
	@Override
	protected void parseLocationString(String locationString) {
		StringTokenizer strTokenizer = new StringTokenizer(locationString, GPS_DELIMITER);
		
		if(strTokenizer.countTokens() == 2) {
			String latitude = strTokenizer.nextToken().trim();
			String longitude = strTokenizer.nextToken().trim();
			setComponents(latitude, longitude);
		}
	}
	
	private void initialize() {
		setDelimiter(GPS_DELIMITER);
	}

}
