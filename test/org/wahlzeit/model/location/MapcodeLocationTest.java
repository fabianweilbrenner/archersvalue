package org.wahlzeit.model.location;

import junit.framework.TestCase;

/**
 * 
 * @author Fabian Weilbrenner
 *
 */
public class MapcodeLocationTest extends TestCase {

	public static void main(final String[] args) {
		junit.textui.TestRunner.run(MapcodeLocationTest.class);
	}
	
	public void testGetComponents() {
		MapcodeLocation location1 = new MapcodeLocation("DEU QQH.MB");
		MapcodeLocation location2 = new MapcodeLocation("DEU","QQH", "MB");
		
		assertEquals("QQH", location1.getComponents()[0]);
		assertEquals("MB", location1.getComponents()[1]);
		
		assertEquals("QQH", location2.getComponents()[0]);
		assertEquals("MB", location2.getComponents()[1]);
	}
	
	public void testHasContext() {
		MapcodeLocation location1 = new MapcodeLocation("DEU QQH.MB");
		MapcodeLocation location2 = new MapcodeLocation("VJGK7.02NK");
		
		assertTrue(location1.hasContext());
		assertFalse(location2.hasContext());
	}
	
	public void testGetContext() {
		MapcodeLocation location1 = new MapcodeLocation("DEU QQH.MB");
		MapcodeLocation location2 = new MapcodeLocation("VJGK7.02NK");
		
		assertEquals("DEU", location1.getContext());
		assertEquals("", location2.getContext());
	}
	
	public void testSetContext() {
		MapcodeLocation location1 = new MapcodeLocation("");
		
		location1.setContext("DEU");
		
		assertEquals("DEU", location1.getContext());
	}
	
	public void testGetDelimiter() {
		MapcodeLocation location1 = new MapcodeLocation("");
		
		assertEquals(".", location1.getDelimiter());
	}
	
	public void testConvertTo() {
		MapcodeLocation location1 = new MapcodeLocation("DEU QQH.MB");
		
		Location convertedLocation = location1.convertTo(GPSLocation.class);
		
		assertEquals(49.45052, Double.parseDouble(convertedLocation.getFirstComponent()), 0.0001);
		assertEquals(11.08048, Double.parseDouble(convertedLocation.getSecondComponent()), 0.0001);
	}
	
	public void testAsString() {
		MapcodeLocation location1 = new MapcodeLocation("DEU QQH.MB");
		MapcodeLocation location2 = new MapcodeLocation("DEU","QQH", "MB");
		
		assertEquals("DEU QQH.MB", location1.asString());
		assertEquals("DEU QQH.MB", location2.asString());
	}
}
