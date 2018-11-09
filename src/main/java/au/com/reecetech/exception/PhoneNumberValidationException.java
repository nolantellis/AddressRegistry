package au.com.reecetech.exception;

/**
 * 
 * Suppressing warning as serial number not required.
 * 
 * @author nolan
 */
@SuppressWarnings("serial")
public class PhoneNumberValidationException extends Exception {

	String message;

	public PhoneNumberValidationException(String message) {
		super(message);
		this.message = message;
	}

}
