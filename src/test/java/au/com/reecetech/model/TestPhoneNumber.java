package au.com.reecetech.model;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

import au.com.reecetech.exception.PhoneNumberValidationException;
import au.com.reecetech.model.phone.PhoneNumber;

public class TestPhoneNumber {

	@Test
	public void testValidPhoneNumber() throws PhoneNumberValidationException {
		String phoneNumberString = "+41.414926579";
		PhoneNumber number = new PhoneNumber(phoneNumberString);
		Assert.assertEquals("Phone Numbers not matching", phoneNumberString, number.getPhoneNumber());
	}

	@Test(expected = PhoneNumberValidationException.class)
	public void testInValidPhoneNumber() throws PhoneNumberValidationException {
		String phoneNumberString = "+41414926579";
		PhoneNumber number = new PhoneNumber(phoneNumberString);
	}

	@Test
	public void testEqualPhoneNumber() throws PhoneNumberValidationException {
		PhoneNumber number1 = new PhoneNumber("+41.414926579");
		PhoneNumber number2 = new PhoneNumber("+41.414926579");
		assertEquals(number1, number2);
	}

}
