package org.wahlzeit.model.domain;


/**
 * 
 * @author Fabian Weilbrenner
 *
 */
public enum BowCategory implements Category {
	
	Recurve(0, "Recurve"),
	Compound(1, "Compound"),
	Longbow(2, "Longbow"),
	Other(3, "Other");
	
	private final String name;
	private final int intValue;
	
	BowCategory(int intValue, String name) {
		this.intValue = intValue;
		this.name = name;
	}
	
	public static BowCategory getFromInt(int intValue) {
		BowCategory[] categories = values();
		for(int i = 0; i < categories.length; ++i) {
			if(categories[i].asInt() == intValue) {
				return categories[i];
			}
		}
		
		return Other;
	}
	
	public static BowCategory getFromString(String name) {
		BowCategory[] categories = values();
		for(int i = 0; i < categories.length; ++i) {
			if(categories[i].getTypeName().equals(name)) {
				return categories[i];
			}
		}
		
		return Other;
	}

	@Override
	public String asString() {
		return "BowCategory: " + name;
	}

	@Override
	public int asInt() {
		return intValue;
	}

	@Override
	public BowCategory[] getAllValues() {
		return values();
	}

	@Override
	public String getTypeName() {
		return name;
	}
	
}