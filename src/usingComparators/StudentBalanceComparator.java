package usingComparators;

import java.util.Comparator;

public class StudentBalanceComparator implements Comparator<Student>{

	public int compare(Student one, Student two){
		return one.getBalance().compareTo(two.getBalance());
	}
}
