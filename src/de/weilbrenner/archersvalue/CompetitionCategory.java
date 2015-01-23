package de.weilbrenner.archersvalue;


/**
 * CompetitionCategory contains all information of the archery competitions.
 * 
 * @collaboration Archery, Factory, Value Object, Category
 * @author Fabian Weilbrenner
 *
 */
public enum CompetitionCategory implements Category {
	WorldChampionship(0, "World Championship"),
	WorldCup(1, "World Cup"),
	IndoorWorldCup(2, "Indoor World Cup"),
	LocalTournament(3, "Local Tournament"),
	Other(4, "Other");
	
	private final String name;
	private final int intValue;
	
	private CompetitionCategory(int intValue, String name) {
		this.intValue = intValue;
		this.name = name;
	}
	
	public static CompetitionCategory getFromInt(int intValue) {
		CompetitionCategory[] categories = values();
		for(int i = 0; i < categories.length; ++i) {
			if(categories[i].asInt() == intValue) {
				return categories[i];
			}
		}
		
		return Other;
	}
	
	public static CompetitionCategory getFromString(String name) {
		CompetitionCategory[] categories = values();
		for(int i = 0; i < categories.length; ++i) {
			if(categories[i].getTypeName().equals(name)) {
				return categories[i];
			}
		}
		
		return Other;
	}

	@Override
	public String asString() {
		return "CompetitionCategory: " + name;
	}

	@Override
	public int asInt() {
		return intValue;
	}

	@Override
	public Category[] getAllValues() {
		return values();
	}

	@Override
	public String getTypeName() {
		return name;
	}
	
}
