package org.wahlzeit.model.domain;

import junit.framework.TestCase;

public class BowCategoryTest extends TestCase {

	public static void main(final String[] args) {
		junit.textui.TestRunner.run(DrawWeightTest.class);
	}
	
	public void testGetFromInt() {
		assertEquals(BowCategory.Recurve, BowCategory.getFromInt(0));
		assertEquals(BowCategory.Compound, BowCategory.getFromInt(1));
		assertEquals(BowCategory.Longbow, BowCategory.getFromInt(2));
		assertEquals(BowCategory.Other, BowCategory.getFromInt(3));
	}
	
	public void testGetFromString() {
		assertEquals(BowCategory.Recurve, BowCategory.getFromString("Recurve"));
		assertEquals(BowCategory.Compound, BowCategory.getFromString("Compound"));
		assertEquals(BowCategory.Longbow, BowCategory.getFromString("Longbow"));
		assertEquals(BowCategory.Other, BowCategory.getFromString("Other"));
	}
	
}
