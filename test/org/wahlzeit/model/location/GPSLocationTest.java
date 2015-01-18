package org.wahlzeit.model.location;

import junit.framework.TestCase;

/**
 * 
 * @author Fabian Weilbrenner
 *
 */
public class GPSLocationTest extends TestCase {

	public static void main(final String[] args) {
		junit.textui.TestRunner.run(GPSLocationTest.class);
	}
	
	public void testHasComponents() {
		try {
			GPSLocation location1 = new GPSLocation("49.45052, 11.08048");
			//GPSLocation location2 = new GPSLocation("");
			
			assertTrue(location1.hasComponents());
			//assertFalse(location2.hasComponents());
		} catch(Exception e) {
			fail("Exception occured");
		}
	}
	
	public void testGetComponents() {	
		try {
			GPSLocation location1 = new GPSLocation("49.45052, 11.08048");
			GPSLocation location2 = new GPSLocation("49.45052", "11.08048");
			GPSLocation location3 = new GPSLocation(49.45052, 11.08048);
			
			assertEquals("49.45052", location1.getComponents()[0]);
			assertEquals("11.08048", location1.getComponents()[1]);
			
			assertEquals("49.45052", location2.getComponents()[0]);
			assertEquals("11.08048", location2.getComponents()[1]);
			
			assertEquals("49.45052", location3.getComponents()[0]);
			assertEquals("11.08048", location3.getComponents()[1]);
		} catch(Exception e) {
			fail("Exception occured");
		}	
	}
	
	public void testGetFirstComponent() {
		try {
			GPSLocation location1 = new GPSLocation("49.45052, 11.08048");
			GPSLocation location2 = new GPSLocation("49.45052", "11.08048");
			GPSLocation location3 = new GPSLocation(49.45052, 11.08048);
			
			assertEquals("49.45052", location1.getFirstComponent());
			assertEquals("49.45052", location2.getFirstComponent());
			assertEquals("49.45052", location3.getFirstComponent());
		} catch(Exception e) {
			fail("Exception occured");
		}			
	}
	
	public void testGetSecondComponent() {
		try {
			GPSLocation location1 = new GPSLocation("49.45052, 11.08048");
			GPSLocation location2 = new GPSLocation("49.45052", "11.08048");
			GPSLocation location3 = new GPSLocation(49.45052, 11.08048);
			
			assertEquals("11.08048", location1.getSecondComponent());
			assertEquals("11.08048", location2.getSecondComponent());
			assertEquals("11.08048", location3.getSecondComponent());
		} catch(Exception e) {
			fail("Exception occured");
		}	
	}
	
	public void testSetComponents() {
		try {
			String firstComponent = "49.45052";
			String secondComponent = "11.08048";
			
			GPSLocation location1 = new GPSLocation("20.1344, 34.28239");
			GPSLocation location2 = new GPSLocation("20.1344, 34.28239");
			
			location1.setComponents(firstComponent, secondComponent);
			location2.setComponents(new String[] {firstComponent, secondComponent});
			
			assertEquals("49.45052", location1.getComponents()[0]);
			assertEquals("11.08048", location1.getComponents()[1]);
			
			assertEquals("49.45052", location2.getComponents()[0]);
			assertEquals("11.08048", location2.getComponents()[1]);
			
		} catch(Exception e) {
			fail("Exception occured");
		}	
		
	}
	
	public void testSetComponentsWithInvalidValues() {
		try {
			String firstComponent = "49.45052";
			String wrongComponent = "24.948c";
			
			GPSLocation location = new GPSLocation("20.1344, 34.28239");
			
			location.setComponents(firstComponent, wrongComponent);
			
			fail();
		} catch(LocationException e) {
			// expected
		}
	}
	
	public void testHasContext() {
		try {
			GPSLocation location1 = new GPSLocation("49.45052, 11.08048");
			
			assertFalse(location1.hasContext());
		} catch(Exception e) {
			fail("Exception occured");
		}
		
	}
	
	public void testGetDelimiter() {
		try {
			GPSLocation location1 = new GPSLocation("49.45052, 11.08048");
			
			assertEquals(",", location1.getDelimiter());
		} catch(Exception e) {
			fail("Exception occured");
		}
	}
	
	public void testConvertTo() {
		try {
			GPSLocation location1 = new GPSLocation("49.45052, 11.08048");	
			
			Location convertedLocation = location1.convertTo(MapcodeLocation.class);
			
			assertEquals("DEU QQH.MB", convertedLocation.asString());
		} catch(Exception e) {
			fail("Exception occured");
		}
	}
	
	public void testAsString() {
		try {
			GPSLocation location1 = new GPSLocation("49.45052, 11.08048");
			GPSLocation location2 = new GPSLocation("49.45052", "11.08048");
			GPSLocation location3 = new GPSLocation(49.45052, 11.08048);
			
			assertEquals("49.45052,11.08048", location1.asString());
			assertEquals("49.45052,11.08048", location2.asString());
			assertEquals("49.45052,11.08048", location3.asString());
		} catch(Exception e) {
			fail("Exception occured");
		}		
	}
	
	public void testIsEqual() {
		try {
			GPSLocation location1 = new GPSLocation("49.45052, 11.08048");
			GPSLocation location2 = new GPSLocation("49.45052", "11.08048");
			GPSLocation location3 = new GPSLocation("39.45052, 21.08048");
			
			assertTrue(location1.isEqual(location2));
			assertFalse(location1.isEqual(location3));
		} catch(Exception e) {
			fail("Exception occured");
		}
		
		
	}
	
}
