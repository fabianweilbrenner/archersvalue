package org.wahlzeit.model.location;

/**
 * 
 * @author Fabian Weilbrenner
 *
 */
public interface Location {
	
	public boolean hasComponents();
	public String[] getComponents();
	public String getFirstComponent();
	public String getSecondComponent();
	public void setComponents(String[] components);
	public void setComponents(String leftComponent, String rightComponent);
	
	public boolean hasContext();
	public String getContext();
	public void setContext(String context);
	
	public String getDelimiter();
	
	public Location convertTo(Class<? extends Location> classToConvert);
	
	public String asString();

}
