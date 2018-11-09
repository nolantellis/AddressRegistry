package au.com.reecetech.model.phone;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/***
 * 
 * @author nolan
 *
 *         This Class holds a collection of AddressBooks
 */
public class AddressBookRegistry {

	Map<String, AddressBook> addressBookCollection = new HashMap<>();

	/***
	 * 
	 * @param name
	 * @return Optional Address Book. Address Book mat not exist
	 */
	public Optional<AddressBook> getAddressBook(String name) {
		return Optional.ofNullable(addressBookCollection.get(name));
	}

	public boolean createAddressBook(String name) {
		if (getAddressBook(name).isPresent()) {
			return false;
		} else {
			addressBookCollection.put(name, new AddressBook());
			return true;
		}
	}

	public boolean removeAddressBook(String name) {

		if (!getAddressBook(name).isPresent()) {
			return false;
		} else {
			addressBookCollection.remove(name);
			return true;
		}
	}

	public Set<String> getAddressBookNames() {
		return Collections.unmodifiableSet(addressBookCollection.keySet());
	}

}
