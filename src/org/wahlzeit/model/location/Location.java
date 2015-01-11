package org.wahlzeit.model.location;

/**
 * 
 * @collaboration Location
 * @author Fabian Weilbrenner
 *
 */
public interface Location {
	
	/**
	 * 
	 * @methodtype boolean query
	 */
	public boolean hasComponents();
	
	/**
	 * 
	 * @methodtype get
	 */
	public String[] getComponents();
	
	/**
	 * 
	 * @methodtype get
	 */
	public String getFirstComponent();
	
	/**
	 * 
	 * @methodtype get
	 */
	public String getSecondComponent();
	
	/**
	 * 
	 * @methodtype set
	 */
	public void setComponents(String[] components);
	
	/**
	 * 
	 * @methodtype set
	 */
	public void setComponents(String leftComponent, String rightComponent);
	
	/**
	 * 
	 * @methodtype boolean query
	 */
	public boolean hasContext();
	
	/**
	 * 
	 * @methodtype get
	 */
	public String getContext();
	
	/**
	 * 
	 * @methodtype set
	 */
	public void setContext(String context);
	
	/**
	 * 
	 * @methodtype get
	 */
	public String getDelimiter();
	
	/**
	 * 
	 * @methodtype convertion
	 */
	public Location convertTo(Class<? extends Location> classToConvert);
	
	/**
	 * 
	 * @methodtype convertion
	 */
	public String asString();
	
	/**
	 * 
	 * @methodtype comparison
	 */
	public boolean isEqual(Location location);

}
