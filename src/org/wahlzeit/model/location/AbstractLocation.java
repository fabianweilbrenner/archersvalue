package org.wahlzeit.model.location;

/**
 * 
 * @collaboration Location
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
	 * @throws LocationException 
	 * @methodtype conversion
	 */
	protected abstract Location doConvertTo(Class<? extends Location> classToConvert) throws LocationException;
	
	/**
	 * 
	 * @throws LocationException 
	 * @methodtype command
	 */
	protected abstract void parseLocationString(String locationString) throws LocationException;
	
	/**
	 * 
	 * @throws LocationException 
	 * @methodtype boolean query
	 */
	protected abstract boolean assertSetComponents(String[] components) throws LocationException;
	
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
	 * @throws LocationException 
	 * @methodtype set
	 */
	public void setComponents(String[] components) throws LocationException {
		if(components.length == 2 && assertSetComponents(components)) {
			doSetComponents(components);
		}		
	}
	
	protected void doSetComponents(String[] components) {
		this.components = components;
	}
	
	/**
	 * 
	 * @throws LocationException 
	 * @methodtype set
	 */
	public void setComponents(String leftComponent, String rightComponent) throws LocationException {
		String[] components = new String[] { leftComponent, rightComponent };
		setComponents(components);
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
	 * @throws LocationException 
	 * @methodtype get
	 */
	public Location convertTo(Class<? extends Location> classToConvert) throws LocationException {
		try {
			assertConvertTo(classToConvert);
			return doConvertTo(classToConvert);
		} catch (Exception e) {
			throw new LocationException(e);
		}
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
