package de.weilbrenner.archersvalue.bow;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.wahlzeit.services.DataObject;

/**
 * Bow contains all information of a bow.
 * 
 * @collaboration Archery, Manager, Factory, Type Object
 * @author Fabian Weilbrenner
 *
 */
public class Bow extends DataObject {

	///////////////////////////////////
	/// Fields
	///////////////////////////////////
	private final String BOW_CONSTRUCTION_YEAR = "constructionYear";
	private final String BOW_BOWTYPE_ID = "bowType";
	
	private int id;
	private BowType bowType;
	private int constructionYear;


	///////////////////////////////////
	/// Constructor
	///////////////////////////////////
	/**
	 * @methodtype constructor
	 */
	public Bow(BowType bowType, int constructionYear) {
		this.id = BowManager.getInstance().getNextId();
		
		this.constructionYear = constructionYear;
		this.bowType = bowType;
		
		incWriteCount();
	}
	
	/**
	 * 
	 * @methodtype constructor
	 */
	public Bow(ResultSet rset) throws SQLException {
		readFrom(rset);
	}

	
	///////////////////////////////////
	/// Methods
	///////////////////////////////////
	/**
	 * 
	 * @return
	 * @methodtype get
	 */
	public int getConstructionYear() {
		return constructionYear;
	}
	
	/**
	 * 
	 * @return
	 * @methodtype get
	 */
	public BowType getBowType() {
		return bowType;
	}
	
	public int getId() {
		return id;
	}
	
	public String asString() {
		return bowType.asString();
	}
	
	@Override
	public String getIdAsString() {
		return String.valueOf(id);
	}

	@Override
	public void readFrom(ResultSet rset) throws SQLException {
		constructionYear = rset.getInt(BOW_CONSTRUCTION_YEAR);	
		int bowTypeId = rset.getInt(BOW_BOWTYPE_ID);
		bowType = BowTypeManager.getInstance().getBowTypeFromId(bowTypeId);		
	}

	@Override
	public void writeOn(ResultSet rset) throws SQLException {
		rset.updateInt(BOW_CONSTRUCTION_YEAR, constructionYear);
		rset.updateInt(BOW_BOWTYPE_ID, bowType.getId());
	}

	@Override
	public void writeId(PreparedStatement stmt, int pos) throws SQLException {
		stmt.setInt(pos, id);
	}
}
