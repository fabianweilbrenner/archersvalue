package de.weilbrenner.archersvalue.location;

/**
 * The location class represents the location of a particular photo
 * 
 * @collaboration Location
 * @author Fabian Weilbrenner
 *
 */
public interface Location {
	
	/**
	 * Indicates if there are location components available
	 * 
	 * The method is deprecated because there shouldn't be any longer instances without components
	 * 
	 * @deprecated
	 * @methodtype boolean query
	 */
	public boolean hasComponents();
	
	/**
	 * Gets the location components as an array
	 * 
	 * @methodtype get
	 */
	public String[] getComponents();
	
	/**
	 * Gets the first location component
	 * 
	 * @methodtype get
	 */
	public String getFirstComponent();
	
	/**
	 * Gets the second location component
	 * 
	 * @methodtype get
	 */
	public String getSecondComponent();
	
	/**
	 * Sets the location components
	 * 
	 * @throws LocationException 
	 * @methodtype set
	 */
	public void setComponents(String[] components) throws LocationException;
	
	/**
	 * Sets the location components
	 * 
	 * @throws LocationException 
	 * @methodtype set
	 */
	public void setComponents(String leftComponent, String rightComponent) throws LocationException;
	
	/**
	 * Indicates if the location have any context
	 * 
	 * @methodtype boolean query
	 */
	public boolean hasContext();
	
	/**
	 * Gets the location context
	 * 
	 * @methodtype get
	 */
	public String getContext();
	
	/**
	 * Sets the location context
	 * 
	 * @methodtype set
	 */
	public void setContext(String context);
	
	/**
	 * Gets the delimiter of the location
	 * 
	 * @methodtype get
	 */
	public String getDelimiter();
	
	/**
	 * Converts a location instance from one type to another
	 * 
	 * @throws LocationException 
	 * @methodtype convertion
	 */
	public Location convertTo(Class<? extends Location> classToConvert) throws LocationException;
	
	/**
	 * Returns the location in a human-readable representation
	 * 
	 * @methodtype convertion
	 */
	public String asString();
	
	/**
	 * Indicates if two location are equal
	 * 
	 * @methodtype comparison
	 */
	public boolean isEqual(Location location);

}
