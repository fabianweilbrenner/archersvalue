package org.wahlzeit.model.domain;

public interface Category {
	
	public String asString();
	public int asInt();
	public Category[] getAllValues();
	public String getTypeName();

}
