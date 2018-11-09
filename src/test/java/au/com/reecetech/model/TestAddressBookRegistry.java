package au.com.reecetech.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import au.com.reecetech.model.phone.AddressBookRegistry;

public class TestAddressBookRegistry {

	AddressBookRegistry bookRegistry;

	@Before
	public void setUp() {
		bookRegistry = new AddressBookRegistry();
	}

	@Test
	public void testCreateAddressBook() {
		boolean status = bookRegistry.createAddressBook("Personal");
		assertTrue(status);
		status = bookRegistry.createAddressBook("Office");
		assertTrue(status);

		assertTrue(bookRegistry.getAddressBookNames()
				.contains("Personal"));
		assertTrue(bookRegistry.getAddressBookNames()
				.contains("Office"));
	}

	@Test
	public void testCreateDuplicateAddressBook() {
		boolean status = bookRegistry.createAddressBook("Personal");
		assertTrue(status);
		status = bookRegistry.createAddressBook("Personal");
		assertFalse(status);
	}

	@Test
	public void testRemoveAddressBook() {
		boolean status = bookRegistry.createAddressBook("Personal");
		assertTrue(status);
		status = bookRegistry.removeAddressBook("Personal");
		assertTrue(status);
	}

	@Test
	public void testGetAddressBookWhichNotExist() {
		assertFalse(bookRegistry.getAddressBook("Personal")
				.isPresent());
	}

}
