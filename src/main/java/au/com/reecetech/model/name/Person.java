package au.com.reecetech.model.name;

/***
 * This is a concrete class person. Which can contain any details related to a
 * person. But if its required to be part of a addressbook then it should
 * implemment {@link Name}
 * 
 * @author nolan
 * @see Name
 */
public class Person implements Name {

	String name;

	public Person(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		int result = (name == null) ? 0 : name.hashCode();
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
		
		Person other = (Person) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + "]";
	}

}
