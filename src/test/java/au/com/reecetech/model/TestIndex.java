package au.com.reecetech.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import au.com.reecetech.model.name.FirstLetterIndex;
import au.com.reecetech.model.name.Index;
import au.com.reecetech.model.name.Name;

public class TestIndex {

	Set<Index> indexSet;
	Name name;

	/***
	 * Used to test if hashing works for hashMap functionality of index.
	 */
	@Before
	public void setUp() {
		indexSet = new HashSet<>();
		name = Mockito.mock(Name.class);
	}

	@Test
	public void testValidFirstLetterIndex() {
		String expectedString = "n";
		Mockito.when(name.getName())
				.thenReturn("Nolan");
		Index index = new FirstLetterIndex(name);
		assertEquals(expectedString, index.getIndex());
		Mockito.verify(name)
				.getName();
	}

	@Test
	public void testInValidFirstLetterIndex() {
		String expectedString = "n";
		Mockito.when(name.getName())
				.thenReturn("Viola-Wife");
		Index index = new FirstLetterIndex(name);
		assertNotEquals(expectedString, index.getIndex());
		Mockito.verify(name)
				.getName();
	}

	@Test
	public void testHashCodeAndEqualsForIndex() {
		Name name1 = Mockito.mock(Name.class);
		Name name2 = Mockito.mock(Name.class);

		Mockito.when(name1.getName())
				.thenReturn("Viola-Wife");
		Mockito.when(name2.getName())
				.thenReturn("Viola-Wife");

		indexSet.add(new FirstLetterIndex(name1));
		indexSet.add(new FirstLetterIndex(name2));

		assertEquals(1, indexSet.size());
		Mockito.verify(name1)
				.getName();
		Mockito.verify(name2)
				.getName();
	}

	@Test
	public void testHashCodeAndEqualsForIndex1() {
		Name name1 = Mockito.mock(Name.class);
		Name name2 = Mockito.mock(Name.class);
		Mockito.when(name1.getName())
				.thenReturn("Viola-Wife");
		Mockito.when(name2.getName())
				.thenReturn("Nolan-Husband");
		indexSet.add(new FirstLetterIndex(name1));
		indexSet.add(new FirstLetterIndex(name2));
		assertEquals(2, indexSet.size());
		Mockito.verify(name1)
				.getName();
		Mockito.verify(name2)
				.getName();
	}

}
