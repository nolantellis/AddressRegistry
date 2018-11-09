package au.com.reecetech.service;

public class PhoneNumberValidationService {
	/***
	 * Generic Regex to validate international phone numbers format
	 * +CCC.NNNNNNNNNNxEEEE CC-Country Code NN-Numbers xEEE-Extension
	 */
	private static final String phoneNumberRegex = "^\\+[0-9]{1,3}\\.[0-9]{4,14}(?:x.+)?$";

	public static boolean isValidNumber(String number) {
		return number.matches(phoneNumberRegex);
	}
}
