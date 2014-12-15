package org.wahlzeit.model.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoId;
import org.wahlzeit.model.domain.bow.Bow;
import org.wahlzeit.model.domain.bow.BowManager;

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
	public static final String BOW = "bow";
	
	private Archery archery;


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
	 * Getter method of the domain class
	 * 
	 * @return the domain class
	 * @methodtype get
	 */
	public Archery getArchery() {
		return archery;
	}
	
	/**
	 * Setter method of the domain class
	 * 
	 * @param archery domain class to be set
	 * @methodtype set
	 */
	public void setArchery(Archery archery) {
		this.archery = archery;
		incWriteCount();
	}
	
	/**
	 * @throws SQLException 
	 * 
	 */
	public void readFrom(ResultSet rset) throws SQLException {
		super.readFrom(rset);
		
		BowCategory bowCategory = ArcheryFactory.getInstance().createBowCategory(rset.getInt(BOW_CATEGORY));
		CompetitionCategory competitionCategory = ArcheryFactory.getInstance().createCompetitionCategory(rset.getInt(COMPETITION_CATEGORY));
		
		int drawWeightValue = rset.getInt(DRAW_WEIGHT_VALUE);
		int drawWeightUnitAsInt = rset.getInt(DRAW_WEIGHT_UNIT);
		
		int bowId = rset.getInt(BOW);
		Bow bow = BowManager.getInstance().getBowFromId(bowId);
		
		DrawWeight drawWeight = ArcheryFactory.getInstance().createDrawWeight(drawWeightValue, DrawWeight.Units.getFromInt(drawWeightUnitAsInt));
		
		archery = ArcheryFactory.getInstance().createArcheryObject(bowCategory, competitionCategory, drawWeight, bow);
	}
	
	/**
	 * @throws SQLException
	 */
	public void writeOn(ResultSet rset) throws SQLException {
		super.writeOn(rset);
		
		rset.updateInt(BOW_CATEGORY, archery.bowCategory.asInt());
		rset.updateInt(COMPETITION_CATEGORY, archery.competitionCategory.asInt());
		rset.updateInt(DRAW_WEIGHT_VALUE, archery.drawWeight.getValue());
		rset.updateInt(DRAW_WEIGHT_UNIT, archery.drawWeight.getUnit().asInt());
		rset.updateInt(BOW, archery.getBow().getId());
	}
	
	private void assertInvariants() {
		assert archery != null;
	}
	
	/**
	 * @methodtype initialization
	 */
	private void initialize() {
		archery = new Archery();
		
		assertInvariants();
		
		incWriteCount();
	}

}
