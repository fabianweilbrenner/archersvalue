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
		try {
			MapcodeLocation location1 = new MapcodeLocation("DEU QQH.MB");
			MapcodeLocation location2 = new MapcodeLocation("DEU","QQH", "MB");
			
			assertEquals("QQH", location1.getComponents()[0]);
			assertEquals("MB", location1.getComponents()[1]);
			
			assertEquals("QQH", location2.getComponents()[0]);
			assertEquals("MB", location2.getComponents()[1]);
		} catch(Exception e) {
			fail("Exception occured");
		}
	}
	
	public void testHasContext() {
		try {
			MapcodeLocation location1 = new MapcodeLocation("DEU QQH.MB");
			MapcodeLocation location2 = new MapcodeLocation("VJGK7.02NK");
			
			assertTrue(location1.hasContext());
			assertFalse(location2.hasContext());
		} catch(Exception e) {
			fail("Exception occured");
		}	
	}
	
	public void testGetContext() {
		try {
			MapcodeLocation location1 = new MapcodeLocation("DEU QQH.MB");
			MapcodeLocation location2 = new MapcodeLocation("VJGK7.02NK");
			
			assertEquals("DEU", location1.getContext());
			assertEquals("", location2.getContext());
		} catch(Exception e) {
			fail("Exception occured");
		}		
	}
	
	public void testSetContext() {
		try {
			MapcodeLocation location1 = new MapcodeLocation("");
			
			location1.setContext("DEU");
			
			assertEquals("DEU", location1.getContext());
		} catch(Exception e) {
			fail("Exception occured");
		}
	}
	
	public void testGetDelimiter() {
		try {
			MapcodeLocation location1 = new MapcodeLocation("");
			
			assertEquals(".", location1.getDelimiter());
		} catch(Exception e) {
			fail("Exception occured");
		}	
	}
	
	public void testConvertTo() {
		try {
			MapcodeLocation location1 = new MapcodeLocation("DEU QQH.MB");
			
			Location convertedLocation = location1.convertTo(GPSLocation.class);
			
			assertEquals(49.45052, Double.parseDouble(convertedLocation.getFirstComponent()), 0.0001);
			assertEquals(11.08048, Double.parseDouble(convertedLocation.getSecondComponent()), 0.0001);
		} catch(Exception e) {
			fail("Exception occured");
		}		
	}
	
	public void testAsString() {
		try {
			MapcodeLocation location1 = new MapcodeLocation("DEU QQH.MB");
			MapcodeLocation location2 = new MapcodeLocation("DEU","QQH", "MB");
			
			assertEquals("DEU QQH.MB", location1.asString());
			assertEquals("DEU QQH.MB", location2.asString());
		} catch(Exception e) {
			fail("Exception occured");
		}			
	}
	
}
