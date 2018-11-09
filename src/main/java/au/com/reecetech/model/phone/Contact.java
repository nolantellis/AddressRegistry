package au.com.reecetech.model.phone;

import au.com.reecetech.model.name.Name;

public class Contact {

	Name name;
	PhoneNumber phoneNumber;

	public Contact(Name name, PhoneNumber phoneNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;
	}

	public Name getName() {
		return name;
	}

	public PhoneNumber getPhoneNumber() {
		return phoneNumber;
	}

	/***
	 * Using default generated Method
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Contact [name=" + name.getName() + ", phoneNumber=" + phoneNumber.getPhoneNumber() + "]";
	}

}
