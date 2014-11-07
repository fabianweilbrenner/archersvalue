package org.wahlzeit.model.location;

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
	
	public Location convertTo(Location location);
	
	public String asString();

}
