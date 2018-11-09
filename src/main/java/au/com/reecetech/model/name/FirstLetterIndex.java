package au.com.reecetech.model.name;

/***
 * This is a concrete implementation of a index. This is a sample index which
 * returns first letter. Just like an address book.
 * 
 * @author nolan
 */
public class FirstLetterIndex implements Index {

	String firstChar;

	public FirstLetterIndex(Name name) {
		super();
		this.firstChar = name.getName()
				.substring(0, 1)
				.toLowerCase();
	}

	@Override
	public String getIndex() {
		return firstChar;
	}

	@Override
	public int hashCode() {
		int result = (firstChar == null) ? 0 : firstChar.hashCode();
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

		FirstLetterIndex other = (FirstLetterIndex) obj;
		if (firstChar == null) {
			if (other.firstChar != null)
				return false;
		} else if (!firstChar.equals(other.firstChar))
			return false;
		return true;
	}

}
