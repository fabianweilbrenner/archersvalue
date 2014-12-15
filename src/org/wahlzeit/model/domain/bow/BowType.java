package org.wahlzeit.model.domain.bow;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.wahlzeit.services.DataObject;

public class BowType extends DataObject {

	///////////////////////////////////
	/// Fields
	///////////////////////////////////
	private final String MANUFACTURER = "manufacturer";
	private final String RISER = "riser";
	private final String LIMBS = "limbs";
	
	private int id;
	private String manufacturer;
	private String riser;
	private String limbs;


	///////////////////////////////////
	/// Constructor
	///////////////////////////////////
	/**
	 * @methodtype constructor
	 */
	public BowType(String manufacturer, String riser, String limbs) {
		this.id = BowTypeManager.getInstance().getNextId();
		
		this.manufacturer = manufacturer;
		this.riser = riser;
		this.limbs = limbs;
		
		incWriteCount();
	}
	
	public BowType(ResultSet rset) throws SQLException {
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
	public String getManufacturer() {
		return manufacturer;
	}
	
	/**
	 * 
	 * @methodtype set
	 */
	public void setManufacturer(String manufacturer) {
		// precondition
		assert manufacturer != null;
		
		this.manufacturer = manufacturer;
	}
	
	/**
	 * 
	 * @return
	 * @methodtype get
	 */
	public String getRiser() {
		return riser;
	}
	
	/**
	 * 
	 * @methodtype set
	 */
	public void setRiser(String riser) {
		// precondition
		assert riser != null;
		
		this.riser = riser;
	}
	
	/**
	 * 
	 * @return
	 * @methodtype get
	 */
	public String getLimbs() {
		return limbs;
	}
	
	/**
	 * 
	 * @methodtype set
	 */
	public void setLimbs(String limbs) {
		// precondition
		assert limbs != null;
		
		this.limbs = limbs;
	}
	
	public String asString() {
		return "Hersteller: "+ manufacturer + "; Mittelteil: " + riser + "; Wurfarme: " + limbs;
	}
	
	public int getId() {
		return id;
	}
	
	/**
	 * 
	 */
	@Override
	public String getIdAsString() {
		return String.valueOf(id);
	}

	/**
	 * 
	 */
	@Override
	public void readFrom(ResultSet rset) throws SQLException {
		manufacturer = rset.getString(MANUFACTURER);
		riser = rset.getString(RISER);
		limbs = rset.getString(LIMBS);
	}

	/**
	 * 
	 */
	@Override
	public void writeOn(ResultSet rset) throws SQLException {
		rset.updateString(MANUFACTURER, manufacturer);
		rset.updateString(RISER, riser);
		rset.updateString(LIMBS, limbs);
	}

	/**
	 * 
	 */
	@Override
	public void writeId(PreparedStatement stmt, int pos) throws SQLException {
		stmt.setInt(pos, id);
	}
	
}
