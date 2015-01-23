package de.weilbrenner.archersvalue;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;


import de.weilbrenner.archersvalue.DrawWeight.Units;
import de.weilbrenner.archersvalue.bow.Bow;
import de.weilbrenner.archersvalue.bow.BowType;

/**
 * The ArcheryFactory class is responsible for creating instances of the archery domain classes.
 * 
 * @collaboration Factory
 * @author Fabian Weilbrenner
 *
 */
public class ArcheryFactory {

	///////////////////////////////////
	/// Fields
	///////////////////////////////////
	private static ArcheryFactory instance;
	private static HashMap<String, DrawWeight> drawWeightInstances = new HashMap<String, DrawWeight>();


	///////////////////////////////////
	/// Constructor
	///////////////////////////////////
	private ArcheryFactory() {

	}


	///////////////////////////////////
	/// Methods
	///////////////////////////////////
	/**
	 * Getter method of the singleton
	 * 
	 * @return the instance of the class
	 * @methodtype get
	 */
	public static ArcheryFactory getInstance() {
		if(instance == null) {
			instance = new ArcheryFactory();
		}
		return instance;
	}

	/**
	 * Returns a default archery object
	 * 
	 * @return default archery object
	 * @methodtype factory
	 */
	public Archery createArcheryObject() {
		return new Archery();
	}

	/**
	 * Creates a new Archery object
	 * 
	 * @param bowCategory bow category to be set
	 * @param competitionCategory competition category to be set
	 * @param drawWeight draw weight to be set
	 * @return Returns the new archery object
	 * @methodtype factory
	 */
	public Archery createArcheryObject(BowCategory bowCategory, CompetitionCategory competitionCategory, DrawWeight drawWeight, Bow bow) {
		//precondition
		assert bowCategory != null;
		assert competitionCategory != null;
		assert drawWeight != null;
		assert bow != null;

		return new Archery(bowCategory, competitionCategory, drawWeight, bow);
	}

	/**
	 * Returns a new BowCategory object from a given id
	 * 
	 * @param categoryId Id of the requested object
	 * @return the respective category object
	 * @methodtype factory
	 */
	public BowCategory createBowCategory(int categoryId) {
		//precondition
		assert categoryId >= 0;

		return BowCategory.getFromInt(categoryId);
	}

	/**
	 * Returns a new BowCategory object from a given name
	 * 
	 * @param categoryName Name of the requested object
	 * @return the respective category object
	 * @methodtype factory
	 */
	public BowCategory createBowCategory(String categoryName) {
		//precondition
		assert categoryName != null && categoryName.length() >= 0;

		return BowCategory.getFromString(categoryName);
	}

	/**
	 * Returns a new CompetitionCategory object from a given id
	 * 
	 * @param categoryId Id of the requested object
	 * @return the respective category object
	 * @methodtype factory
	 */
	public CompetitionCategory createCompetitionCategory(int categoryId) {
		//precondition
		assert categoryId >= 0;

		return CompetitionCategory.getFromInt(categoryId);
	}

	/**
	 * Returns a new CompetitionCategory object from a given name
	 * 
	 * @param categoryName Name of the requested object
	 * @return the respective category object
	 * @methodtype factory
	 */
	public CompetitionCategory createCompetitionCategory(String categoryName) {
		//precondition
		assert categoryName != null && categoryName.length() >= 0;

		return CompetitionCategory.getFromString(categoryName);
	}

	/**
	 * Creates a new DrawWeight object
	 * 
	 * @param value The value of the new object 
	 * @param unit The Unit of the new object
	 * @return the respective DrawWeight object
	 * @methodtype factory
	 */
	public DrawWeight createDrawWeight(int value, Units unit) {
		//precondition
		assert value >= 0;
		assert unit != null;

		return getDrawWeightInstance(value, unit);
	}
	
	public BowType createBowType(String manufacturer, String riser, String limbs) {
		return new BowType(manufacturer, riser, limbs);
	}
	
	public BowType createBowType(ResultSet rset) throws SQLException {
		return new BowType(rset);
	}
	
	public Bow createBow(BowType bowType, int constructionYear) {
		return new Bow(bowType, constructionYear);
	}
	
	public Bow createBow(ResultSet rset) throws SQLException {
		return new Bow(rset);
	}

	/**
	 * Returns an existings instance of the DrawWeight object. If there isn't an existing one, it creates a new one.
	 * 
	 * @param value represents the integer value
	 * @param unit represents the unit
	 * @return return an instance of the DrawWeight class
	 */
	private static DrawWeight getDrawWeightInstance(int value, Units unit) {
		//postcondition
		assert unit != null;
		assert value >= 0;

		String key = DrawWeight.asString(value, unit);

		DrawWeight instance = null;
		if(drawWeightInstances.containsKey(key)) {
			instance = drawWeightInstances.get(key);
		} else {
			instance = new DrawWeight(value, unit);
			drawWeightInstances.put(key, instance);
		}

		return instance;
	}
}
