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
	
	protected BowCategory bowCategory;
	protected CompetitionCategory competitionCategory;


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
	 * @throws SQLException 
	 * 
	 */
	public void readFrom(ResultSet rset) throws SQLException {
		super.readFrom(rset);
		
		bowCategory = new BowCategory(BowCategories.valueOf(rset.getString(BOW_CATEGORY)));
		competitionCategory = new CompetitionCategory(CompetitionCategories.valueOf(rset.getString(COMPETITION_CATEGORY)));
	}
	
	/**
	 * @throws SQLException
	 */
	public void writeOn(ResultSet rset) throws SQLException {
		super.writeOn(rset);
		
		rset.updateString(BOW_CATEGORY, bowCategory.getBowCategory().toString());
		rset.updateString(COMPETITION_CATEGORY, competitionCategory.getCompetitionCategory().toString());
	}
	
	private void assertInvariants() {
		assert bowCategory != null;
		assert competitionCategory != null;
	}
	
	/**
	 * @methodtype initialization
	 */
	private void initialize() {
		bowCategory = new BowCategory();
		competitionCategory = new CompetitionCategory();
		
		assertInvariants();
		
		incWriteCount();
	}

}
