package au.com.reecetech.app;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import au.com.reecetech.service.AddressBookRegistryService;

public class AddressRegistryApp {

	public static void main(String[] args) {

		int choice = -1;
		AddressBookRegistryService service=new AddressBookRegistryService();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		do {
			try {
				System.out.println("Choose the below options");

				System.out.println("1. Create address book");
				System.out.println("2. Add contact to address book");
				System.out.println("3. Display specific contacts from address book");
				System.out.println("4. Display all contacts from address book");
				System.out.println("5. Display all contacts from address registry");
				System.out.println("6. Remove contact from addressbook");
				System.out.println("7. Exit");

				choice = Integer.parseInt(reader.readLine());

				switch (choice) {
				case 1:
					service.createAddressBook(reader);
					break;
				case 2:
					service.addContactToAddressBook(reader);
					break;
				case 3:
					service.displaySpecificContact(reader);
					break;
				case 4:
					service.displayAllContactsFromAddressBook(reader);
					break;
				case 5:
					service.displayAllContactsFromRegistry();
					break;
				case 6:
					service.removeContactFromAddressBook(reader);
					break;
				}

			} catch (Exception e) {
				System.out.println("OOPS!! Something went wrong");
			}
		} while (choice != 7);
	}

}
