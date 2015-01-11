package org.wahlzeit.model.domain;

import org.wahlzeit.model.domain.bow.Bow;

/**
 * This class wraps all specific domain classes for the {@link ArcheryPhoto}.
 * 
 * @collaboration Archery, ArcheryPhoto/Archery, Value Object, Factory 
 * @author Fabian Weilbrenner
 *
 */
public class Archery {


	///////////////////////////////////
	/// Fields
	///////////////////////////////////
	protected final BowCategory bowCategory;
	protected final CompetitionCategory competitionCategory;
	protected final DrawWeight drawWeight;
	protected final Bow bow;


	///////////////////////////////////
	/// Constructor
	///////////////////////////////////
	/**
	 * @methodtype constructor
	 */
	protected Archery() {
		this(BowCategory.Other, 
		     CompetitionCategory.Other, 
		     ArcheryFactory.getInstance().createDrawWeight(0, DrawWeight.Units.LBS),
		     null);
	}
	
	/**
	 * @methodtype constructor
	 */
	protected Archery(BowCategory bowCategory, CompetitionCategory competitionCategory, DrawWeight drawWeight, Bow bow) {
		this.bowCategory 		 = bowCategory;
		this.competitionCategory = competitionCategory;
		this.drawWeight			 = drawWeight;
		this.bow 				 = bow;
		
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
	 * Getter method of the bow
	 * 
	 * @return the bow
	 * @methodtype get
	 */
	public Bow getBow() {
		return bow;
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
