package org.wahlzeit.model.domain;

import java.util.HashMap;

/**
 * 
 * @author Fabian Weilbrenner
 *
 */
public class DrawWeight {

	public enum Units {
		LBS(0, "lbs"),
		KG(1, "kg");

		private String shortTerm;
		private int unitAsInt;

		Units(int unitAsInt, String shortTerm) {
			this.shortTerm = shortTerm;
			this.unitAsInt = unitAsInt;
		}
		
		public static Units getFromInt(int intValue) {
			Units[] units = values();
			for(int i = 0; i < units.length; ++i) {
				if(units[i].asInt() == intValue) {
					return units[i];
				}
			}
			
			return LBS;
		}
		
		public static Units getFromString(String shortTerm) {
			Units[] units = values();
			for(int i = 0; i < units.length; ++i) {
				if(units[i].getShortTerm().equals(shortTerm)) {
					return units[i];
				}
			}
			
			return LBS;
		}

		public String getShortTerm() {
			return shortTerm;
		}	
		
		public int asInt() {
			return unitAsInt;
		}
	}

	///////////////////////////////////
	/// Fields
	///////////////////////////////////
	private static HashMap<String, DrawWeight> instances = new HashMap<String, DrawWeight>();

	private final Units unit;
	private final int value;


	///////////////////////////////////
	/// Constructor
	///////////////////////////////////
	/**
	 * @methodtype constructor
	 */
	private DrawWeight(int value, Units unit) {
		this.value = value;
		this.unit  = unit;
	}
	

	///////////////////////////////////
	/// Methods
	///////////////////////////////////
	/**
	 * Returns an existings instance of the DrawWeight object. If there isn't an existing one, it creates a new one.
	 * 
	 * @param value represents the integer value
	 * @param unit represents the unit
	 * @return return an instance of the DrawWeight class
	 */
	public static DrawWeight getInstance(int value, Units unit) {
		//postcondition
		assert unit != null;
		assert value >= 0;
		
		String key = asString(value, unit);

		DrawWeight instance = null;
		if(instances.containsKey(key)) {
			instance = instances.get(key);
		} else {
			instance = new DrawWeight(value, unit);
			instances.put(key, instance);
		}

		return instance;
	}

	/**
	 * Returns the unit of the value object
	 * 
	 * @return returns the unit of the value object
	 * @methodtype get
	 */
	public Units getUnit() {
		return unit;
	}

	/**
	 * Returns the value of the value object
	 * 
	 * @return returns the value of the value object
	 * @methodtype get
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Returns a string representation of the value object
	 * 
	 * @return returns a string representation of the value object
	 * @methodtype conversion
	 */
	public String asString() {
		return asString(value, unit);
	}

	private static String asString(int value, Units unit) {
		return value + " " + unit.getShortTerm();
	}


}

