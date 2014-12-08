package org.wahlzeit.model.domain;

import junit.framework.TestCase;

public class CompetitionCategoryTest extends TestCase {

	public static void main(final String[] args) {
		junit.textui.TestRunner.run(DrawWeightTest.class);
	}
	
	public void testGetFromInt() {
		assertEquals(CompetitionCategory.WorldChampionship, CompetitionCategory.getFromInt(0));
		assertEquals(CompetitionCategory.WorldCup, CompetitionCategory.getFromInt(1));
		assertEquals(CompetitionCategory.IndoorWorldCup, CompetitionCategory.getFromInt(2));
		assertEquals(CompetitionCategory.LocalTournament, CompetitionCategory.getFromInt(3));
		assertEquals(CompetitionCategory.Other, CompetitionCategory.getFromInt(4));
	}
	
	public void testGetFromString() {
		assertEquals(CompetitionCategory.WorldChampionship, CompetitionCategory.getFromString("World Championship"));
		assertEquals(CompetitionCategory.WorldCup, CompetitionCategory.getFromString("World Cup"));
		assertEquals(CompetitionCategory.IndoorWorldCup, CompetitionCategory.getFromString("Indoor World Cup"));
		assertEquals(CompetitionCategory.LocalTournament, CompetitionCategory.getFromString("Local Tournament"));
		assertEquals(CompetitionCategory.Other, CompetitionCategory.getFromString("Other"));
	}
}
