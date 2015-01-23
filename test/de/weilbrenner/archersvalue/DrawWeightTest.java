package de.weilbrenner.archersvalue;

import de.weilbrenner.archersvalue.DrawWeight;
import junit.framework.TestCase;

public class DrawWeightTest extends TestCase {

	public static void main(final String[] args) {
		junit.textui.TestRunner.run(DrawWeightTest.class);
	}
	
	public void testGetValue() {
		DrawWeight drawWeight1 = new DrawWeight(31, DrawWeight.Units.LBS);
		DrawWeight drawWeight2 = new DrawWeight(28, DrawWeight.Units.KG);
		
		assertEquals(drawWeight1.getValue(), 31);
		assertEquals(drawWeight2.getValue(), 28);
	}
	
	public void testGetUnit() {
		DrawWeight drawWeight1 = new DrawWeight(31, DrawWeight.Units.LBS);
		DrawWeight drawWeight2 = new DrawWeight(28, DrawWeight.Units.KG);
		
		assertEquals(drawWeight1.getUnit(), DrawWeight.Units.LBS);
		assertEquals(drawWeight2.getUnit(), DrawWeight.Units.KG);
	}
	
	public void testAsString() {
		DrawWeight drawWeight1 = new DrawWeight(31, DrawWeight.Units.LBS);
		DrawWeight drawWeight2 = new DrawWeight(28, DrawWeight.Units.KG);
		
		assertTrue(drawWeight1.asString().equals("31 lbs"));
		assertTrue(drawWeight2.asString().equals("28 kg"));
	}
	
}
