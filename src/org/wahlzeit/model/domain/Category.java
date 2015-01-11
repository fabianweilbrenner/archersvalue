package org.wahlzeit.model.domain;

/**
 * The {@link Category} interface contains common methods for categories.
 * 
 * @collaboration Archery, Category
 * @author Fabian Weilbrenner
 *
 */
public interface Category {
	
	public String asString();
	public int asInt();
	public Category[] getAllValues();
	public String getTypeName();

}
