package org.wahlzeit.model.location;

import junit.framework.*;

/**
 * 
 * @author Fabian Weilbrenner
 *
 */
public class AllTests extends TestSuite {

	/**
	 * 
	 */
	public static Test suite() {
		TestSuite suite = new TestSuite();
		
		suite.addTestSuite(GPSLocationTest.class);
		suite.addTestSuite(MapcodeLocationTest.class);
		
		return suite;
	}
}
