package org.wahlzeit.model.domain;

public class Archery {


	///////////////////////////////////
	/// Fields
	///////////////////////////////////
	protected final BowCategory bowCategory;
	protected final CompetitionCategory competitionCategory;
	protected final DrawWeight drawWeight;


	///////////////////////////////////
	/// Constructor
	///////////////////////////////////
	/**
	 * @methodtype constructor
	 */
	protected Archery() {
		this(BowCategory.Other, CompetitionCategory.Other, ArcheryFactory.getInstance().createDrawWeight(0, DrawWeight.Units.LBS));
	}
	
	/**
	 * @methodtype constructor
	 */
	protected Archery(BowCategory bowCategory, CompetitionCategory competitionCategory, DrawWeight drawWeight) {
		this.bowCategory 		 = bowCategory;
		this.competitionCategory = competitionCategory;
		this.drawWeight			 = drawWeight;
		
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
	public BowCategory getBowCategory() {
		return bowCategory;
	}


	/**
	 * Getter method of the competition category
	 * 
	 * @return the competition category
	 * @methodtype get
	 */
	public CompetitionCategory getCompetitionCategory() {
		return competitionCategory;
	}

	/**
	 * Getter method of the draw weight
	 * 
	 * @return the draw weight
	 * @methodtype get
	 */
	public DrawWeight getDrawWeight() {
		return drawWeight;
	}
	
	/**
	 * Method that checks the class invariants
	 */
	private void assertInvariants() {
		if(bowCategory == null || competitionCategory == null || drawWeight == null) {
			throw new IllegalStateException("Illegal state of the class 'Archery'");
		}
	}

}
