package org.wahlzeit.model.domain;

/**
 * 
 * @author Fabian Weilbrenner
 *
 */
public final class CompetitionCategory extends AbstractCategory {

	///////////////////////////////////
	/// Fields
	///////////////////////////////////
	private CompetitionCategories competitionCategory;

	///////////////////////////////////
	/// Constructor
	///////////////////////////////////
	/**
	 * @methodtype constructor
	 */
	public CompetitionCategory() {
		this(CompetitionCategories.Other);
	}

	/**
	 * @methodtype constructor
	 */
	public CompetitionCategory(CompetitionCategories competitionCategory) {
		super("Competition Category");
		initialize();

		this.competitionCategory = competitionCategory;
		
		assertInvariants();
	}

	///////////////////////////////////
	/// Methods
	///////////////////////////////////

	/**
	 * Getter method of the competition category
	 * 
	 * @return the competition category
	 * @methodtype get
	 */
	public CompetitionCategories getCompetitionCategory() {
		//precondition
		assert competitionCategory != null;
		
		return competitionCategory;
	}
	
	/**
	 * Setter method of the competition category
	 * 
	 * @param competitionCategory competition category to be set
	 * @methodtype set
	 */
	public void setCompetitionCategory(CompetitionCategories competitionCategory) {
		//precondition
		assert competitionCategory != null;
		
		this.competitionCategory = competitionCategory;
		
		//postcondition
		assert this.competitionCategory != null;
		assert this.competitionCategory == competitionCategory;
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
		assert competitionCategory != null;
		
		return name + ": " + competitionCategory.getName();
	}
	
	public String categoryNameAsString() {
		return competitionCategory.getName();
	}
	
	protected void assertInvariants() {
		super.assertInvariants();
		assert competitionCategory != null;
	}
	
	/**
	 * Initializes the fields with default values
	 * 
	 * @methodtype initialization
	 */
	private void initialize() {
		
	}


}
