package org.wahlzeit.model.domain;

/**
 * 
 * @author Fabian Weilbrenner
 *
 */
public final class BowCategory extends AbstractCategory {
	
	///////////////////////////////////
	/// Fields
	///////////////////////////////////
	private BowCategories bowCategory;

	///////////////////////////////////
	/// Constructor
	///////////////////////////////////
	/**
	 * @methodtype constructor
	 */
	public BowCategory() {
		this(BowCategories.Other);
	}

	/**
	 * @methodtype constructor
	 */
	public BowCategory(BowCategories bowCategory) {
		super("Bow Category");
		initialize();
		
		this.bowCategory = bowCategory;
		
		assertInvariants();
	}

	///////////////////////////////////
	/// Methods
	///////////////////////////////////
	/**
	 * Getter method of the bow category
	 * 
	 * @return the bow category
	 * @methodtype get
	 */
	public BowCategories getBowCategory() {
		//precondition
		assert bowCategory != null;
		
		return bowCategory;
	}
	
	/**
	 * Setter method of the bow category
	 * 
	 * @param bowCategory bow category to be set
	 * @methodtype set
	 */
	public void setBowCategory(BowCategories bowCategory) {
		//precondition
		assert bowCategory != null;
		
		this.bowCategory = bowCategory;
		
		//postcondition
		assert this.bowCategory != null;
		assert this.bowCategory == bowCategory;
	}
	
	/**
	 * This methods returns a human readable expression of the category
	 * 
	 * @return human readable expression of the category
	 * @methodtype convertion
	 */
	public String asString() {
		//precondition
		assert name != null;
		assert bowCategory != null;
		
		return name + ": " + bowCategory.getName();
	}
	
	protected void assertInvariants() {
		super.assertInvariants();
		assert bowCategory != null;
	}
	
	/**
	 * Initializes the fields with default values
	 * 
	 * @methodtype initialization
	 */
	private void initialize() {
		bowCategory = BowCategories.Other;
		
		//postcondition
		assert bowCategory != null;
	}

}
