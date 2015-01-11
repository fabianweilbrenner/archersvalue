package org.wahlzeit.model.domain;

/**
 * DrawWeight contains all information of the draw weight of a bow.
 * 
 * @collaboration Archery, Factory
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
	private final Units unit;
	private final int value;


	///////////////////////////////////
	/// Constructor
	///////////////////////////////////
	/**
	 * @methodtype constructor
	 */
	protected DrawWeight(int value, Units unit) {
		this.value = value;
		this.unit  = unit;
	}
	

	///////////////////////////////////
	/// Methods
	///////////////////////////////////

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

	public static String asString(int value, Units unit) {
		return value + " " + unit.getShortTerm();
	}


}

