package org.wahlzeit.model.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoId;

/**
 * 
 * @author Fabian Weilbrenner
 *
 */
public class ArcheryPhoto extends Photo {

	///////////////////////////////////
	/// Fields
	///////////////////////////////////
	public static final String BOW_CATEGORY = "bowCategory";
	public static final String COMPETITION_CATEGORY = "competitionCategory";
	public static final String DRAW_WEIGHT_VALUE = "drawWeightValue";
	public static final String DRAW_WEIGHT_UNIT = "drawWeightUnit";
	public static final String DRAW_WEIGHT = "drawWeight";
	
	protected BowCategory bowCategory;
	protected CompetitionCategory competitionCategory;
	protected DrawWeight drawWeight;


	///////////////////////////////////
	/// Constructor
	///////////////////////////////////
	/**
	 * @methodtype constructor
	 */
	public ArcheryPhoto() {
		super();

		initialize();
	}
	
	/**
	 * @methodtype constructor
	 */
	public ArcheryPhoto(PhotoId myId) {
		super(myId);
		
		initialize();
	}
	
	/**
	 * @throws SQLException 
	 * @methodtype constructor
	 */
	public ArcheryPhoto(ResultSet resultSet) throws SQLException {
		super(resultSet);
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
		//precondition
		assert bowCategory != null;
		
		return bowCategory;
	}
	
	/**
	 * Setter method of the bow category
	 * 
	 * @methodtype set
	 */
	public void setBowCategory(BowCategory bowCategory) {
		//precondition
		assert bowCategory != null;
		
		this.bowCategory = bowCategory;
		incWriteCount();
		
		//postcondition
		assert this.bowCategory != null;
		assert this.bowCategory == bowCategory;
	}
	
	/**
	 * Getter method of the competition category
	 * 
	 * @return the competition category
	 * @methodtype get
	 */
	public CompetitionCategory getCompetitionCategory() {
		//precondition
		assert competitionCategory != null;
		
		return competitionCategory;
	}
	
	/**
	 * Setter method of the competition category
	 * 
	 * @methodtype set
	 */
	public void setCompetitionCategory(CompetitionCategory competitionCategory) {
		//precondition
		assert competitionCategory != null;
		
		this.competitionCategory = competitionCategory;
		incWriteCount();
		
		//postcondition
		assert this.competitionCategory != null;
		assert this.competitionCategory == competitionCategory;
	}
	
	/**
	 * Getter method of the draw weight
	 * 
	 * @return the draw weight
	 * @methodtype get
	 */
	public DrawWeight getDrawWeight() {
		//precondition
		assert drawWeight != null;
		
		return drawWeight;
	}
	
	/**
	 * Setter method of the draw weight
	 * 
	 * @methodtype set
	 */
	public void setDrawWeight(DrawWeight drawWeight) {
		//precondition
		assert drawWeight != null;
		
		this.drawWeight = drawWeight;
		incWriteCount();
		
		//postcondition
		assert this.drawWeight != null;
	}
	
	/**
	 * @throws SQLException 
	 * 
	 */
	public void readFrom(ResultSet rset) throws SQLException {
		super.readFrom(rset);
		
		bowCategory = BowCategory.getFromInt(rset.getInt(BOW_CATEGORY));
		competitionCategory = CompetitionCategory.getFromInt(rset.getInt(COMPETITION_CATEGORY));
		
		int drawWeightValue = rset.getInt(DRAW_WEIGHT_VALUE);
		int drawWeightUnitAsInt = rset.getInt(DRAW_WEIGHT_UNIT);
		
		drawWeight = DrawWeight.getInstance(drawWeightValue, DrawWeight.Units.getFromInt(drawWeightUnitAsInt));
	}
	
	/**
	 * @throws SQLException
	 */
	public void writeOn(ResultSet rset) throws SQLException {
		super.writeOn(rset);
		
		rset.updateInt(BOW_CATEGORY, bowCategory.asInt());
		rset.updateInt(COMPETITION_CATEGORY, competitionCategory.asInt());
		rset.updateInt(DRAW_WEIGHT_VALUE, drawWeight.getValue());
		rset.updateInt(DRAW_WEIGHT_UNIT, drawWeight.getUnit().asInt());
	}
	
	private void assertInvariants() {
		assert bowCategory != null;
		assert competitionCategory != null;
		assert drawWeight != null;
	}
	
	/**
	 * @methodtype initialization
	 */
	private void initialize() {
		bowCategory = BowCategory.Other;
		competitionCategory = CompetitionCategory.Other;
		
		assertInvariants();
		
		incWriteCount();
	}

}
