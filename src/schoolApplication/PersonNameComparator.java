package schoolApplication;

import java.util.Comparator;

public class PersonNameComparator implements Comparator<Person> {

	@Override
	public int compare(Person t1, Person t2) {
		if (t1.getLastName().equalsIgnoreCase(t2.getLastName())) {
			return t1.getFirstName().compareTo(t2.getFirstName());
		}
		return t1.getLastName().compareTo(t2.getLastName());
	}
}
