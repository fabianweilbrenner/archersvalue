package org.wahlzeit.model.domain;

import junit.framework.Test;
import junit.framework.TestSuite;

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
		
		suite.addTestSuite(DrawWeightTest.class);
		suite.addTestSuite(BowCategoryTest.class);
		suite.addTestSuite(CompetitionCategoryTest.class);
		
		return suite;
	}
}
