package au.com.reecetech.model.phone;

import au.com.reecetech.constants.ErrorMsg;
import au.com.reecetech.exception.PhoneNumberValidationException;
import au.com.reecetech.service.PhoneNumberValidationService;

public class PhoneNumber {

	String phoneNumber;

	public PhoneNumber(String phoneNumber) throws PhoneNumberValidationException {
		if (!PhoneNumberValidationService.isValidNumber(phoneNumber)) {
			throw new PhoneNumberValidationException(ErrorMsg.INVALID_PHONE_NUMBER);
		}
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	@Override
	public int hashCode() {
		return phoneNumber == null ? 0 : phoneNumber.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhoneNumber other = (PhoneNumber) obj;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PhoneNumber [phoneNumber=" + phoneNumber + "]";
	}

}
