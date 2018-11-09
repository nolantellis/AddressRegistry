package au.com.reecetech.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import au.com.reecetech.exception.PhoneNumberValidationException;
import au.com.reecetech.model.name.Person;
import au.com.reecetech.model.phone.AddressBook;
import au.com.reecetech.model.phone.Contact;
import au.com.reecetech.model.phone.PhoneNumber;

public class TestAddressBook {

	AddressBook book;

	@Before
	public void setUp() {
		book = new AddressBook();
	}

	@Test
	public void testAddContactToAddressBook() throws PhoneNumberValidationException {
		Contact actualContact = new Contact(new Person("Nolan"), new PhoneNumber("+41.1234556"));
		boolean status = book.addContact(actualContact);
		Set<Contact> expectedContactList = book.getContact(new Person("Nolan"));
		assertTrue(expectedContactList.contains(actualContact));
		assertTrue(status);
	}

	@Test
	public void testAddDuplicateContactToAddressBook() throws PhoneNumberValidationException {
		Contact actualContact = new Contact(new Person("Nolan"), new PhoneNumber("+41.1234556"));
		Contact duplicateContact = new Contact(new Person("Nolan"), new PhoneNumber("+41.1234556"));
		boolean status = book.addContact(actualContact);
		assertTrue(status);
		status = book.addContact(duplicateContact);
		assertFalse(status);
		Set<Contact> expectedContactList = book.getContact(new Person("Nolan"));
		assertTrue(expectedContactList.contains(actualContact));
	}

	@Test
	public void testGetEmptyContact() throws PhoneNumberValidationException {
		Contact actualContact = new Contact(new Person("Nolan"), new PhoneNumber("+41.1234556"));
		boolean status = book.addContact(actualContact);
		assertTrue(status);
		Set<Contact> expectedContactList = book.getContact(new Person("NolanNotExist"));
		assertTrue(expectedContactList.isEmpty());
	}

	@Test
	public void testAddDifferentContactWithSameName() throws PhoneNumberValidationException {
		Contact contact1 = new Contact(new Person("Nolan"), new PhoneNumber("+41.1234556"));
		Contact contact2 = new Contact(new Person("Nolan"), new PhoneNumber("+41.1234556x123"));
		boolean status = book.addContact(contact1);
		assertTrue(status);
		status = book.addContact(contact2);
		assertTrue(status);
		Set<Contact> expectedContactList = book.getContact(new Person("Nolan"));
		assertTrue(expectedContactList.contains(contact1));
		assertTrue(expectedContactList.contains(contact2));
	}

	@Test
	public void testRemoveContactByName() throws PhoneNumberValidationException {
		Contact contact1 = new Contact(new Person("Nolan"), new PhoneNumber("+41.1234556"));
		Contact contact2 = new Contact(new Person("Nolan"), new PhoneNumber("+41.1234556x123"));
		boolean status = book.addContact(contact1);
		assertTrue(status);
		status = book.addContact(contact2);
		assertTrue(status);
		Person p = new Person("Nolan");
		status = book.removeContact(p);
		assertTrue(status);
		assertTrue(book.getContact(p)
				.isEmpty());
		// check removing contact that does not exist

		status = book.removeContact(p);
		assertFalse(status);
	}

	@Test
	public void testGetAllContact() throws PhoneNumberValidationException {
		Contact contact1 = new Contact(new Person("Nolan"), new PhoneNumber("+41.1234556"));
		Contact contact2 = new Contact(new Person("Nolan"), new PhoneNumber("+41.1234556x123"));
		Contact contact3 = new Contact(new Person("Viola"), new PhoneNumber("+41.1234556x123"));
		book.addContact(contact1);
		book.addContact(contact2);
		book.addContact(contact3);

		Set<Contact> allContacts = book.getAllContacts();
		assertTrue(allContacts.size() == 3);

		assertTrue(allContacts.contains(contact1));
		assertTrue(allContacts.contains(contact2));
		assertTrue(allContacts.contains(contact3));

	}

}
