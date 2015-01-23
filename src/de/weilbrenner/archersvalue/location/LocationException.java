package de.weilbrenner.archersvalue.location;

/**
 * 
 * @author Fabian Weilbrenner
 * @collaboration location
 * 
 */
public class LocationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3659802571031367129L;


	///////////////////////////////////
	/// Constructors
	///////////////////////////////////

	public LocationException() {
		super();
	}

	public LocationException(String message) {
		super(message);
	}

	public LocationException(Throwable cause) {
		super(cause);
	}

	public LocationException(String message, Throwable cause) {
		super(message, cause);
	}
}
