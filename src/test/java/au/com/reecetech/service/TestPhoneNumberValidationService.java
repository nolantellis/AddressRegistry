package au.com.reecetech.service;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class TestPhoneNumberValidationService {

	String phoneNumber;
	boolean expectedValue;

	public TestPhoneNumberValidationService(String phoneNumber, boolean expectedValue) {
		super();
		this.phoneNumber = phoneNumber;
		this.expectedValue = expectedValue;
	}

	@Parameterized.Parameters
	public static Collection getValues() {
		return Arrays.asList(new Object[][] { { "+41.12121212", true }, { "1221", false }, { "+3", false },
				{ "+21.333333333x23", true }, { "aaa", false } });
	}

	@Test
	public void testPhoneService() {

		assertEquals(expectedValue, PhoneNumberValidationService.isValidNumber(phoneNumber));
	}
}
