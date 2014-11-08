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
		GPSLocation location1 = new GPSLocation("49.45052, 11.08048");
		GPSLocation location2 = new GPSLocation("");
		
		assertTrue(location1.hasComponents());
		assertFalse(location2.hasComponents());
	}
	
	public void testGetComponents() {
		GPSLocation location1 = new GPSLocation("49.45052, 11.08048");
		GPSLocation location2 = new GPSLocation("49.45052", "11.08048");
		GPSLocation location3 = new GPSLocation(49.45052, 11.08048);
		
		assertEquals("49.45052", location1.getComponents()[0]);
		assertEquals("11.08048", location1.getComponents()[1]);
		
		assertEquals("49.45052", location2.getComponents()[0]);
		assertEquals("11.08048", location2.getComponents()[1]);
		
		assertEquals("49.45052", location3.getComponents()[0]);
		assertEquals("11.08048", location3.getComponents()[1]);
	}
	
	public void testGetFirstComponent() {
		GPSLocation location1 = new GPSLocation("49.45052, 11.08048");
		GPSLocation location2 = new GPSLocation("49.45052", "11.08048");
		GPSLocation location3 = new GPSLocation(49.45052, 11.08048);
		
		assertEquals("49.45052", location1.getFirstComponent());
		assertEquals("49.45052", location2.getFirstComponent());
		assertEquals("49.45052", location3.getFirstComponent());
	}
	
	public void testGetSecondComponent() {
		GPSLocation location1 = new GPSLocation("49.45052, 11.08048");
		GPSLocation location2 = new GPSLocation("49.45052", "11.08048");
		GPSLocation location3 = new GPSLocation(49.45052, 11.08048);
		
		assertEquals("11.08048", location1.getSecondComponent());
		assertEquals("11.08048", location2.getSecondComponent());
		assertEquals("11.08048", location3.getSecondComponent());
	}
	
	public void testSetComponents() {
		String firstComponent = "49.45052";
		String secondComponent = "11.08048";
		
		GPSLocation location1 = new GPSLocation("");
		GPSLocation location2 = new GPSLocation("");
		
		location1.setComponents(firstComponent, secondComponent);
		location2.setComponents(new String[] {firstComponent, secondComponent});
		
		assertEquals("49.45052", location1.getComponents()[0]);
		assertEquals("11.08048", location1.getComponents()[1]);
		
		assertEquals("49.45052", location2.getComponents()[0]);
		assertEquals("11.08048", location2.getComponents()[1]);
	}
	
	public void testHasContext() {
		GPSLocation location1 = new GPSLocation("49.45052, 11.08048");
		
		assertFalse(location1.hasContext());
	}
	
	public void testGetDelimiter() {
		GPSLocation location1 = new GPSLocation("49.45052, 11.08048");
		
		assertEquals(",", location1.getDelimiter());
	}
	
	public void testConvertTo() {
		GPSLocation location1 = new GPSLocation("49.45052, 11.08048");	
		
		Location convertedLocation = location1.convertTo(MapcodeLocation.class);
		
		assertEquals("DEU QQH.MB", convertedLocation.asString());
	}
	
	public void testAsString() {
		GPSLocation location1 = new GPSLocation("49.45052, 11.08048");
		GPSLocation location2 = new GPSLocation("49.45052", "11.08048");
		GPSLocation location3 = new GPSLocation(49.45052, 11.08048);
		
		assertEquals("49.45052,11.08048", location1.asString());
		assertEquals("49.45052,11.08048", location2.asString());
		assertEquals("49.45052,11.08048", location3.asString());
	}
	
}
