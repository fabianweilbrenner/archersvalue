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
	private String[] components;
	private String context;
	private String delimiter;
	
	
	///////////////////////////////////
	/// Constructor
	///////////////////////////////////
	/**
	 * 
	 * @methodtype constructor
	 */
	protected AbstractLocation() {
		initialize();
	}
	
	
	///////////////////////////////////
	/// Methods
	///////////////////////////////////
	/**
	 * 
	 * @methodtype assertion
	 */
	protected abstract void assertConvertTo(Class<? extends Location> classToConvert) throws Exception;
	
	/**
	 * 
	 * @methodtype conversion
	 */
	protected abstract Location doConvertTo(Class<? extends Location> classToConvert);
	
	/**
	 * 
	 * @methodtype command
	 */
	protected abstract void parseLocationString(String locationString);
	
	/**
	 * 
	 * @methodtype boolean query
	 */
	protected abstract boolean areValidComponents(String[] components);
	
	/**
	 * 
	 * @methodtype boolean query
	 */
	public boolean hasComponents() {
		return (!components[0].equals("") && !components[1].equals(""));
	}
	
	/**
	 * 
	 * @methodtype get
	 */
	public String[] getComponents() {
		return components;
	}
	
	/**
	 * 
	 * @methodtype get
	 */
	public String getFirstComponent() {
		return components[0];
	}
	
	/**
	 * 
	 * @methodtype get
	 */
	public String getSecondComponent() {
		return components[1];
	}
	
	/**
	 * 
	 * @methodtype set
	 */
	public void setComponents(String[] components) {
		if(components.length == 2 && areValidComponents(components)) {
			this.components = components;
		}		
	}
	
	/**
	 * 
	 * @methodtype set
	 */
	public void setComponents(String leftComponent, String rightComponent) {
		String[] components = new String[] { leftComponent, rightComponent };
		if(areValidComponents(components)) {
			this.components = components;
		}
	}
	
	/**
	 * 
	 * @methodtype boolean query
	 */
	public boolean hasContext() {
		return !context.equals("");
	}
	
	/**
	 * 
	 * @methodtype get
	 */
	public String getContext() {
		return context;
	}
	
	/**
	 * 
	 * @methodtype set
	 */
	public void setContext(String context) {
		this.context = context;
	}
	
	/**
	 * 
	 * @methodtype get
	 */
	public String getDelimiter() {
		return delimiter;
	}
	
	/**
	 * 
	 * @methodtype set
	 */
	protected void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}
	
	/**
	 * 
	 * @methodtype get
	 */
	public Location convertTo(Class<? extends Location> classToConvert) {
		try {
			assertConvertTo(classToConvert);
			return doConvertTo(classToConvert);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @methodtype conversion
	 */
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
	
	/**
	 * 
	 * @methodtype comparison
	 */
	public boolean isEqual(Location location) {
		if (location.getFirstComponent().equals(getFirstComponent())
				&& location.getSecondComponent().equals(getSecondComponent())
				&& location.getContext().equals(getContext())) {
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @methodtype initialization
	 */
	private void initialize() {
		components = new String[2];
		
		for(int i = 0; i < components.length; ++i) {
			components[i] = "";
		}
		
		context    = "";
		delimiter  = "";
	}

}
