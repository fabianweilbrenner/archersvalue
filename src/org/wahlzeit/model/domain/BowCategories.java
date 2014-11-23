package org.wahlzeit.model.domain;

public enum BowCategories {

	Recurve("Recurve"),
	Compound("Compound"),
	Longbow("Longbow"),
	Other("Other");
	
	private String name;
	
	BowCategories(String name) {
		this.name = name;
		assertInvariants();
	}
	
	String getName() {
		return name;
	}
	
	private void assertInvariants() {
		assert name != null;
	}
	
}
