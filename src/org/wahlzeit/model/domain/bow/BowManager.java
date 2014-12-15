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

public class BowManager extends ObjectManager {

	///////////////////////////////////
	/// Fields
	///////////////////////////////////
	private final String SELECT_STATEMENT_BOW_ID = "SELECT * FROM bows WHERE id = ?";
	private final String INSERT_STATEMENT_BOW = "INSERT INTO bows(id) VALUES(?)";

	private Map<Integer, Bow> bowCache = new HashMap<Integer, Bow>();

	private static BowManager instance;

	///////////////////////////////////
	/// Constructor
	///////////////////////////////////
	private BowManager() {}

	///////////////////////////////////
	/// Methods
	///////////////////////////////////

	public static BowManager getInstance() {
		if(instance == null) {
			instance = new BowManager();
		}
		return instance;
	}

	public Bow getBowFromId(int id) {
		Bow result = bowCache.get(new Integer(id));

		if(result == null) {
			try {
				PreparedStatement stmt = getReadingStatement(SELECT_STATEMENT_BOW_ID);
				result = (Bow) readObject(stmt, id);
			} catch (SQLException sex) {
				SysLog.logThrowable(sex);
			}
			if (result != null) {
				doAddBow(id, result);
			}
		}

		return result;
	}
	
	public int getNextId() {
		int id = 0;
		
		try {
			PreparedStatement stmt = getReadingStatement("SELECT * FROM bows");
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

	public void addBow(Bow bow) {
		doAddBow(bow.getId(), bow);

		try {
			PreparedStatement stmt = getReadingStatement(INSERT_STATEMENT_BOW);
			createObject(bow, stmt, bow.getId());
			ServiceMain.getInstance().saveGlobals();
		} catch (SQLException sex) {
			SysLog.logThrowable(sex);
		}
	}

	/**
	 * 
	 */
	public void saveBow(Bow bow) {
		try {
			PreparedStatement stmt = getUpdatingStatement(SELECT_STATEMENT_BOW_ID);
			updateObject(bow, stmt);
		} catch (SQLException sex) {
			SysLog.logThrowable(sex);
		}
	}

	/**
	 * 
	 */
	public void saveBows() {
		try {
			PreparedStatement stmt = getUpdatingStatement(SELECT_STATEMENT_BOW_ID);
			updateObjects(bowCache.values(), stmt);
		} catch (SQLException sex) {
			SysLog.logThrowable(sex);
		}
	}

	@Override
	protected Persistent createObject(ResultSet rset) throws SQLException {
		return ArcheryFactory.getInstance().createBow(rset);
	}

	private void doAddBow(int id, Bow bow) {
		bowCache.put(new Integer(id), bow);
	}

}
