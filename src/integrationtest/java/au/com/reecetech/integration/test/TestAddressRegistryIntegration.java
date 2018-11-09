package au.com.reecetech.integration.test;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import au.com.reecetech.constants.Constants;
import au.com.reecetech.exception.PhoneNumberValidationException;
import au.com.reecetech.model.name.Person;
import au.com.reecetech.model.phone.AddressBook;
import au.com.reecetech.model.phone.AddressBookRegistry;
import au.com.reecetech.model.phone.Contact;
import au.com.reecetech.model.phone.PhoneNumber;

public class TestAddressRegistryIntegration {

	AddressBookRegistry addressBookRegistry;

	@Before
	public void setUp() {
		addressBookRegistry = new AddressBookRegistry();
	}

	@Test
	public void testAdd3ContactWith1Duplicate() throws PhoneNumberValidationException {
		boolean status = addressBookRegistry.createAddressBook(Constants.PERSONAL);
		assertTrue(status);

		Optional<AddressBook> optionalAddressBook = addressBookRegistry.getAddressBook(Constants.PERSONAL);

		assertTrue(optionalAddressBook.isPresent());

		AddressBook addressBook = optionalAddressBook.get();

		Contact contact1 = new Contact(new Person("Nolan"), new PhoneNumber("+41.1234556"));
		Contact contact2 = new Contact(new Person("Nolan"), new PhoneNumber("+41.1234556"));
		Contact contact3 = new Contact(new Person("Viola"), new PhoneNumber("+41.1234556x123"));
		// Add 3 contacts
		addressBook.addContact(contact1);
		addressBook.addContact(contact2);
		addressBook.addContact(contact3);

		Set<Contact> contactSet = addressBook.getAllContacts();

		assertTrue(contactSet.size() == 2);

		assertTrue(contactSet.contains(contact1));
		assertTrue(contactSet.contains(contact2));
		// Duplicate but is comparable with the existing contact in list
		assertTrue(contactSet.contains(contact3));

		status = addressBook.removeContact(new Person("Nolan"));

		assertTrue(status);
		// Check only 1 contact
		assertTrue(addressBook.getAllContacts()
				.size() == 1);

	}

	@Test
	public void testAdd3DistinctContactRemove2Contact() throws PhoneNumberValidationException {
		boolean status = addressBookRegistry.createAddressBook(Constants.PERSONAL);
		assertTrue(status);

		Optional<AddressBook> optionalAddressBook = addressBookRegistry.getAddressBook(Constants.PERSONAL);

		assertTrue(optionalAddressBook.isPresent());

		AddressBook addressBook = optionalAddressBook.get();

		Contact contact1 = new Contact(new Person("Nolan"), new PhoneNumber("+41.1234556"));
		Contact contact2 = new Contact(new Person("Nolan"), new PhoneNumber("+41.12345567"));
		Contact contact3 = new Contact(new Person("Viola"), new PhoneNumber("+41.1234556x123"));
		// Add 3 contacts
		addressBook.addContact(contact1);
		addressBook.addContact(contact2);
		addressBook.addContact(contact3);

		Set<Contact> contactSet = addressBook.getAllContacts();

		assertTrue(contactSet.size() == 3);

		assertTrue(contactSet.contains(contact1));
		assertTrue(contactSet.contains(contact2));
		assertTrue(contactSet.contains(contact3));

		status = addressBook.removeContact(new Person("Nolan"));

		assertTrue(status);

		assertTrue(addressBook.getAllContacts()
				.size() == 1);
	}

	@Test
	public void testAdd4Contacts2AddressBook1DuplicateInBothBook() throws PhoneNumberValidationException {

		boolean status = addressBookRegistry.createAddressBook(Constants.PERSONAL);
		assertTrue(status);

		Optional<AddressBook> optionalPersonalAddressBook = addressBookRegistry.getAddressBook(Constants.PERSONAL);

		assertTrue(optionalPersonalAddressBook.isPresent());

		status = addressBookRegistry.createAddressBook(Constants.OFFICIAL);
		assertTrue(status);

		Optional<AddressBook> optionalOfficialAddressBook = addressBookRegistry.getAddressBook(Constants.OFFICIAL);

		assertTrue(optionalOfficialAddressBook.isPresent());

		Contact contact1 = new Contact(new Person("Nolan"), new PhoneNumber("+41.1234556"));
		Contact contact2 = new Contact(new Person("Nolan1"), new PhoneNumber("+41.12345567"));
		Contact contact3 = new Contact(new Person("Viola"), new PhoneNumber("+41.1234556x123"));

		status = addressBookRegistry.getAddressBook(Constants.PERSONAL)
				.get()
				.addContact(contact1);
		assertTrue(status);
		status = addressBookRegistry.getAddressBook(Constants.PERSONAL)
				.get()
				.addContact(contact2);
		assertTrue(status);

		status = addressBookRegistry.getAddressBook(Constants.OFFICIAL)
				.get()
				.addContact(contact2);
		assertTrue(status);
		status = addressBookRegistry.getAddressBook(Constants.OFFICIAL)
				.get()
				.addContact(contact3);
		assertTrue(status);

		assertTrue(addressBookRegistry.getAddressBook(Constants.PERSONAL)
				.get()
				.getAllContacts()
				.containsAll(Arrays.asList(contact1, contact2)));

		assertTrue(addressBookRegistry.getAddressBook(Constants.OFFICIAL)
				.get()
				.getAllContacts()
				.containsAll(Arrays.asList(contact2, contact3)));

		Set<Contact> contactSetForBothBooks = getAddContactsFromAddressRegistry();

		assertTrue(contactSetForBothBooks.size() == 3);

		addressBookRegistry.getAddressBook(Constants.OFFICIAL)
				.get()
				.removeContact(new Person("Nolan1"));

		contactSetForBothBooks = getAddContactsFromAddressRegistry();
		assertTrue(contactSetForBothBooks.size() == 3);

		addressBookRegistry.getAddressBook(Constants.OFFICIAL)
				.get()
				.removeContact(new Person("Viola"));

		contactSetForBothBooks = getAddContactsFromAddressRegistry();
		assertTrue(contactSetForBothBooks.size() == 2);
	}

	private Set<Contact> getAddContactsFromAddressRegistry() {
		Set<String> addressBookName = addressBookRegistry.getAddressBookNames();
		Set<Contact> contactSetForBothBooks = new HashSet<>();
		for (String name : addressBookName) {
			contactSetForBothBooks.addAll(addressBookRegistry.getAddressBook(name)
					.get()
					.getAllContacts());
		}
		return contactSetForBothBooks;
	}

}
