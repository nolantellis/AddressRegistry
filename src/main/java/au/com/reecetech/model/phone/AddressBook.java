package au.com.reecetech.model.phone;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import au.com.reecetech.model.name.FirstLetterIndex;
import au.com.reecetech.model.name.Index;
import au.com.reecetech.model.name.Name;

public class AddressBook {

	private Map<Index, Set<Contact>> addressMap = new HashMap<>();

	public boolean addContact(Contact contact) {
		Index index = getIndex(contact.getName());

		Set<Contact> contacts = addressMap.get(index);

		if (Objects.isNull(contacts)) {
			contacts = new HashSet<>();
			addressMap.put(index, contacts);
		}

		return contacts.add(contact);
	}

	public Set<Contact> getContact(Name name) {
		Index index = getIndex(name);

		Predicate<Contact> filter = (Contact c) -> {
			return c.getName()
					.equals(name);
		};
		Set<Contact> contactSet = addressMap.get(index);
		if (Objects.isNull(contactSet)) {
			return new HashSet<>();
		}
		return Collections.unmodifiableSet(contactSet.stream()
				.filter(filter)
				.collect(Collectors.toSet()));

	}

	public boolean removeContact(Name name) {
		Set<Contact> contacts = getContact(name);

		if (contacts.isEmpty()) {
			return false;
		}
		Index index = getIndex(name);

		return addressMap.get(index)
				.removeAll(contacts);
	}

	public Set<Contact> getAllContacts() {
		Set<Contact> contacts = new HashSet<>();

		addressMap.values()
				.stream()
				.forEach(contacts::addAll);

		return Collections.unmodifiableSet(contacts);
	}

	private Index getIndex(Name name) {
		return new FirstLetterIndex(name);
	}

}
