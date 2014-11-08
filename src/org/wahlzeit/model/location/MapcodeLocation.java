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
public class MapcodeLocation extends AbstractLocation {

	///////////////////////////////////
	/// Fields
	///////////////////////////////////
	private final String MAPCODE_DELIMITER = ".";
	
	
	///////////////////////////////////
	/// Constructor
	///////////////////////////////////
	public MapcodeLocation(MapcodeLocation mapcodeLocation) {
		this(new String(mapcodeLocation.getContext()), 
			 new String(mapcodeLocation.getFirstComponent()), 
			 new String(mapcodeLocation.getSecondComponent()));
	}
	
	public MapcodeLocation(String mapcodeString) {
		this();
		parseLocationString(mapcodeString);
	}
	
	public MapcodeLocation(String leftComponent, String rightComponent) {
		this("", leftComponent, rightComponent);
	}
	
	public MapcodeLocation(String context, String leftComponent, String rightComponent) {
		this();
		setContext(context);
		setComponents(leftComponent, rightComponent);
	}
	
	private MapcodeLocation() {
		super();
		initialize();
	}
	
	
	///////////////////////////////////
	/// Methods
	///////////////////////////////////
	
	@Override
	protected void assertConvertTo(Location location) throws Exception {
		if (!(location instanceof GPSLocation) && !(location instanceof MapcodeLocation)) {
			throw new Exception("Convertion Error");
		}
	}

	@Override
	protected Location doConvertTo(Location location) {
		Location convertedLocation = null;

		if (location instanceof GPSLocation) {
			double fComp = Double.parseDouble(location.getFirstComponent());
			double sComp = Double.parseDouble(location.getSecondComponent());
			List<Mapcode> mcList = MapcodeCodec.encode(fComp, sComp);
			if(mcList.size() > 0) {
				convertedLocation = new MapcodeLocation(mcList.get(0).asInternationalISO());
			}
		} else if (location instanceof MapcodeLocation) {
			convertedLocation = new MapcodeLocation((MapcodeLocation) location);
		}

		return convertedLocation;
	}
	
	@Override
	protected void parseLocationString(String locationString) {
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

	private void initialize() {
		setDelimiter(MAPCODE_DELIMITER);
	}
	

}
