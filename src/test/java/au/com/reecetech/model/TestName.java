package au.com.reecetech.model;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import au.com.reecetech.model.name.Name;
import au.com.reecetech.model.name.Person;

public class TestName {

	@Test
	public void testName() {
		Name name = new Person("Nolan");
		assertEquals("Nolan", name.getName());
	}

	@Test
	public void testSameNameDifferentObject() {
		Name name1 = new Person("Nolan");
		Name name2 = new Person("Nolan");
		assertEquals(name2, name1);
	}

	@Test
	public void testHashEqual() {
		Name name1 = new Person("Nolan");
		Name name2 = new Person("Nolan");
		Set<Name> dataSet = new HashSet<>();
		dataSet.add(name1);
		dataSet.add(name2);
		assertEquals(1, dataSet.size());
	}

	@Test
	public void testHashNotEqual() {
		Name name1 = new Person("Nolan");
		Name name2 = new Person("Nolan1");
		Set<Name> dataSet = new HashSet<>();
		dataSet.add(name1);
		dataSet.add(name2);
		assertEquals(2, dataSet.size());
	}
}
