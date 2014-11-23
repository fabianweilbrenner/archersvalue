package org.wahlzeit.model.domain;

/**
 * 
 * @author Fabian Weilbrenner
 *
 */
public abstract class AbstractCategory {

	///////////////////////////////////
	/// Fields
	///////////////////////////////////
	protected String name;


	///////////////////////////////////
	/// Constructor
	///////////////////////////////////
	/**
	 * @methodtype constructor
	 */
	public AbstractCategory() {
		this("");
	}

	/**
	 * @methodtype constructor
	 */
	public AbstractCategory(String name) {
		initialize();
		setName(name);
	}

	///////////////////////////////////
	/// Methods
	///////////////////////////////////
	/**
	 * This methods returns a human readable expression of the category
	 * 
	 * @return human readable expression of the category
	 * @methodtype convertion
	 */
	public abstract String asString();
	public abstract String categoryNameAsString();

	/**
	 * Getter method of the name of the category
	 * 
	 * @return the name of the category
	 * @methodtype get
	 */
	public String getName() {
		//precondition
		assert name != null;
		
		return name;
	}

	/**
	 * Setter method of the name of the category
	 * 
	 * @param name this string will be set as the new name of the category
	 * @methodtype set
	 */
	public void setName(String name) {
		//precondition
		assert name != null;
		
		this.name = name;
		
		//postcondition
		assert this.name != null;
		assert this.name == name;
	}
	
	/**
	 * This method handles the class invariants of the category classes
	 */
	protected void assertInvariants() {
		assert name != null;
	}

	/**
	 * Initializes the fields with default values
	 * 
	 * @methodtype initialization
	 */
	private void initialize() {
		name = "";
		
		//postcondition
		assert name != null;
	}

}
