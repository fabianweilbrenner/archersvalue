package org.wahlzeit.model.domain.bow;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.wahlzeit.main.ServiceMain;
import org.wahlzeit.model.domain.ArcheryFactory;
import org.wahlzeit.services.ObjectManager;
import org.wahlzeit.services.Persistent;
import org.wahlzeit.services.SysLog;

public class BowTypeManager extends ObjectManager {

	///////////////////////////////////
	/// Fields
	///////////////////////////////////
	private final String SELECT_STATEMENT_BOW_TYPE_ID = "SELECT * FROM bowtypes WHERE id = ?";
	private final String INSERT_STATEMENT_BOW_TYPE = "INSERT INTO bowtypes(id) VALUES(?)";

	private Map<Integer, BowType> bowTypeCache = new HashMap<Integer, BowType>();

	private static BowTypeManager instance;


	///////////////////////////////////
	/// Constructor
	///////////////////////////////////
	private BowTypeManager() {}


	///////////////////////////////////
	/// Methods
	///////////////////////////////////
	public static BowTypeManager getInstance() {
		if(instance == null) {
			instance = new BowTypeManager();
		}
		return instance;
	}

	public BowType getBowTypeFromId(int id) {
		BowType result = bowTypeCache.get(new Integer(id));

		if(result == null) {
			try {
				PreparedStatement stmt = getReadingStatement(SELECT_STATEMENT_BOW_TYPE_ID);
				result = (BowType) readObject(stmt, id);
			} catch (SQLException sex) {
				SysLog.logThrowable(sex);
			}
			if (result != null) {
				doAddBowType(id, result);
			}
		}

		return result;
	}
	
	public int getNextId() {
		int id = 0;
		
		try {
			PreparedStatement stmt = getReadingStatement("SELECT * FROM bowtypes");
			ResultSet rset = stmt.executeQuery();
			//TODO Use max()
			while(rset.next()) {
				int currId = rset.getInt("id");
				if(currId > id) {
					id = currId;
				}
			}
		} catch (SQLException sex) {
			SysLog.logThrowable(sex);
		}
		
		return id+1;
	}
	
	public void addBowType(BowType bowType) {
		doAddBowType(bowType.getId(), bowType);

		try {
			PreparedStatement stmt = getReadingStatement(INSERT_STATEMENT_BOW_TYPE);
			createObject(bowType, stmt, bowType.getId());
			ServiceMain.getInstance().saveGlobals();
		} catch (SQLException sex) {
			SysLog.logThrowable(sex);
		}
	}

	/**
	 * 
	 */
	public void saveBowType(BowType bowType) {
		try {
			PreparedStatement stmt = getUpdatingStatement(SELECT_STATEMENT_BOW_TYPE_ID);
			updateObject(bowType, stmt);
		} catch (SQLException sex) {
			SysLog.logThrowable(sex);
		}
	}

	/**
	 * 
	 */
	public void saveBowTypes() {
		try {
			PreparedStatement stmt = getUpdatingStatement(SELECT_STATEMENT_BOW_TYPE_ID);
			updateObjects(bowTypeCache.values(), stmt);
		} catch (SQLException sex) {
			SysLog.logThrowable(sex);
		}
	}

	@Override
	protected Persistent createObject(ResultSet rset) throws SQLException {
		return ArcheryFactory.getInstance().createBowType(rset);
	}

	private void doAddBowType(int id , BowType bowType) {
		bowTypeCache.put(new Integer(id), bowType);
	}

}
