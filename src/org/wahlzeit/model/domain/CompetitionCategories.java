package org.wahlzeit.model.domain;

public enum CompetitionCategories {

	WorldChampionship("World Championship"),
	WorldCup("World Cup"),
	IndoorWorldCup("Indoor World Cup"),
	LocalTournament("Local Tournament"),
	Other("Other");
	
	private String name;
	
	CompetitionCategories(String name) {	
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
