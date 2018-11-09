package au.com.reecetech.service;

import static au.com.reecetech.service.PrintService.printMessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import au.com.reecetech.exception.PhoneNumberValidationException;
import au.com.reecetech.model.name.Person;
import au.com.reecetech.model.phone.AddressBook;
import au.com.reecetech.model.phone.AddressBookRegistry;
import au.com.reecetech.model.phone.Contact;
import au.com.reecetech.model.phone.PhoneNumber;

/***
 * This is a helper class which will help in taking various inputs from UI. NOTE
 * : This is not included in testing as I am considering it as part of User
 * interface component.
 * 
 * @author nolan
 */
public class AddressBookRegistryService {

	AddressBookRegistry registry = new AddressBookRegistry();

	public void createAddressBook(BufferedReader reader) throws IOException {
		printMessage("Enter name of AddressBook");
		String name = reader.readLine();
		if (registry.createAddressBook(name)) {
			printMessage("Address Book Created");
		} else {
			printMessage("Address Book already exists");
		}
	}

	public void addContactToAddressBook(BufferedReader reader) throws IOException {
		Optional<AddressBook> optionalAddressBook = getAddressBookFromUser(reader);
		if (!optionalAddressBook.isPresent()) {
			printMessage("Address Book not exist. Please choose option 1 to add address book.");
			return;
		}
		Person person = getNameFromUser(reader);
		printMessage("Enter phone Number for Contact (International Format : Example +41.123456 or +41.12344x123)");
		PhoneNumber number = null;
		try {
			number = new PhoneNumber(reader.readLine());
		} catch (PhoneNumberValidationException e) {
			printMessage(
					"Invalid Phone Number Format (Example +CCC.NNNNNNNNNNxEEEE CC-Country Code NN-Numbers xEEE-Extension)");
			return;
		}

		boolean isContactAdded = optionalAddressBook.get()
				.addContact(new Contact(person, number));
		if (isContactAdded) {
			printMessage("Contact Added");
		} else {
			printMessage("Duplicate Contact");
		}
	}

	public void displaySpecificContact(BufferedReader reader) throws IOException {

		Optional<AddressBook> optionalAddressBook = getAddressBookFromUser(reader);
		if (!optionalAddressBook.isPresent()) {
			printMessage("Address Book not exist. Please choose option 1 to add address book.");
			return;
		}
		Person person = getNameFromUser(reader);

		printMessage(optionalAddressBook.get()
				.getContact(person)
				.toString());
	}

	public void displayAllContactsFromAddressBook(BufferedReader reader) throws IOException {
		Optional<AddressBook> optionalAddressBook = getAddressBookFromUser(reader);
		if (!optionalAddressBook.isPresent()) {
			printMessage("Address Book not exist. Please choose option 1 to add address book.");
			return;
		}
		printMessage(optionalAddressBook.get()
				.getAllContacts()
				.toString());

	}

	private Person getNameFromUser(BufferedReader reader) throws IOException {
		printMessage("Enter name for Contact");
		String name = reader.readLine();
		Person person = new Person(name);
		return person;
	}

	private Optional<AddressBook> getAddressBookFromUser(BufferedReader reader) throws IOException {
		printMessage("Enter name of AddressBook");
		String addressBookName = reader.readLine();
		Optional<AddressBook> optionalAddressBook = registry.getAddressBook(addressBookName);
		return optionalAddressBook;
	}

	public void displayAllContactsFromRegistry() {

		Set<String> bookNames = registry.getAddressBookNames();

		Set<Contact> contacts = new HashSet();
		for (String name : bookNames) {
			contacts.addAll(registry.getAddressBook(name)
					.get()
					.getAllContacts());
		}
		printMessage(contacts.toString());

	}

	public void removeContactFromAddressBook(BufferedReader reader) throws IOException {
		Optional<AddressBook> optionalAddressBook = getAddressBookFromUser(reader);
		if (!optionalAddressBook.isPresent()) {
			printMessage("Address Book not exist. Please choose option 1 to add address book.");
			return;
		}
		Person person = getNameFromUser(reader);

		boolean isContactRemoved = optionalAddressBook.get()
				.removeContact(person);
		if (isContactRemoved) {
			printMessage("Contact Removed");
		} else {
			printMessage("Contact not present");
		}

	}

}
