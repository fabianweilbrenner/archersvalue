package org.wahlzeit.model.location;

/**
 * 
 * @author Fabian Weilbrenner
 *
 */
public abstract class AbstractLocation implements Location {
	
	
	///////////////////////////////////
	/// Fields
	///////////////////////////////////
	public String[] components;
	public String context;
	public String delimiter;
	
	
	///////////////////////////////////
	/// Constructor
	///////////////////////////////////
	protected AbstractLocation() {
		initialize();
	}
	
	
	///////////////////////////////////
	/// Methods
	///////////////////////////////////
	protected abstract void assertConvertTo(Location location) throws Exception;
	protected abstract Location doConvertTo(Location location);
	
	protected abstract void parseLocationString(String locationString);
	
	public boolean hasComponents() {
		return (!components[0].equals("") && !components[1].equals(""));
	}
	
	public String[] getComponents() {
		return components;
	}
	
	public String getFirstComponent() {
		return components[0];
	}
	
	public String getSecondComponent() {
		return components[1];
	}
	
	public void setComponents(String[] components) {
		if(components.length == 2) {
			this.components = components;
		}		
	}
	
	public void setComponents(String leftComponent, String rightComponent) {
		this.components[0] = leftComponent;
		this.components[1] = rightComponent;
	}
	
	public boolean hasContext() {
		return !context.equals("");
	}
	
	public String getContext() {
		return context;
	}
	
	public void setContext(String context) {
		this.context = context;
	}
	
	public String getDelimiter() {
		return delimiter;
	}
	
	protected void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}
	
	public Location convertTo(Location location) {
		try {
			assertConvertTo(location);
			return doConvertTo(location);
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return null;
	}
	
	public String asString() {
		StringBuilder strBuilder = new StringBuilder();
		if(hasContext()) {
			strBuilder.append(context);
			strBuilder.append(" ");
		}
		
		strBuilder.append(components[0]);
		strBuilder.append(delimiter);
		strBuilder.append(components[1]);
		
		return strBuilder.toString();
	}
	
	private void initialize() {
		components = new String[2];
		context    = "";
		delimiter  = "";
	}

}
