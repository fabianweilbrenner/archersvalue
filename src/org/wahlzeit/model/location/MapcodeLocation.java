package org.wahlzeit.model.location;

import java.util.StringTokenizer;

import com.mapcode.MapcodeCodec;
import com.mapcode.Point;
import com.mapcode.UnknownMapcodeException;

/**
 * 
 * @collaboration Location
 * @author Fabian Weilbrenner
 *
 */
public class MapcodeLocation extends AbstractLocation {

	///////////////////////////////////
	/// Fields
	///////////////////////////////////
	private final String MAPCODE_DELIMITER = ".";
	
	
	///////////////////////////////////
	/// Constructor
	///////////////////////////////////
	/**
	 * 
	 * @methodtype constructor
	 */
	public MapcodeLocation(MapcodeLocation mapcodeLocation) {
		setContext(mapcodeLocation.getContext());
		doSetComponents(mapcodeLocation.getComponents());
	}
	
	/**
	 * 
	 * @throws LocationException 
	 * @methodtype constructor
	 */
	public MapcodeLocation(String mapcodeString) throws LocationException {
		this();
		parseLocationString(mapcodeString);
	}
	
	/**
	 * 
	 * @throws LocationException 
	 * @methodtype constructor
	 */
	public MapcodeLocation(String leftComponent, String rightComponent) throws LocationException {
		this("", leftComponent, rightComponent);
	}
	
	/**
	 * 
	 * @throws LocationException 
	 * @methodtype constructor
	 */
	public MapcodeLocation(String context, String leftComponent, String rightComponent) throws LocationException {
		this();
		setContext(context);
		setComponents(leftComponent, rightComponent);
	}
	
	/**
	 * 
	 * @methodtype constructor
	 */
	private MapcodeLocation() {
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
	 * @methodtype conversion
	 */
	@Override
	protected Location doConvertTo(Class<? extends Location> classToConvert) throws LocationException {
		Location convertedLocation = null;

		if (classToConvert.equals(GPSLocation.class)) {
			try {
				Point p = MapcodeCodec.decode(asString());
				convertedLocation = new GPSLocation(p.getLatDeg(), p.getLonDeg());
			} catch (IllegalArgumentException | UnknownMapcodeException e) {
				throw new LocationException("Convertion Error", e);
			}
		} else if (classToConvert.equals(MapcodeLocation.class)) {
			convertedLocation = new MapcodeLocation(this);
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
		StringTokenizer strTokenizer = new StringTokenizer(locationString, " ");		
		String components = "";
		
		if(strTokenizer.countTokens() == 1) {
			components = strTokenizer.nextToken();
		} else if(strTokenizer.countTokens() == 2) {
			setContext(strTokenizer.nextToken());
			components = strTokenizer.nextToken();
		}
		
		strTokenizer = new StringTokenizer(components, MAPCODE_DELIMITER);
		
		if(strTokenizer.countTokens() == 2) {
			String leftComponent = strTokenizer.nextToken();
			String rightComponent = strTokenizer.nextToken();
			setComponents(leftComponent, rightComponent);
		}
	}
	
	/**
	 * 
	 * @methodtype boolean query
	 */
	@Override
	protected boolean assertSetComponents(String[] components) {
		return true;
	}

	/**
	 * 
	 * @methodtype initialization
	 */
	private void initialize() {
		setDelimiter(MAPCODE_DELIMITER);
	}
	

}
