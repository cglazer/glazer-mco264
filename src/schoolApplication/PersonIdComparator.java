package schoolApplication;

import java.util.Comparator;

public class PersonIdComparator implements Comparator<Person> {

	@Override
	public int compare(Person t1, Person t2) {
		// TODO Auto-generated method stub
		return t1.getID().compareTo(t2.getID());
	}

}
