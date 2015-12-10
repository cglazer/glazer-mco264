package usingComparators;

import java.util.ArrayList;
import java.util.Collections;

public class StudentCollections {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Student> students = new ArrayList<Student>();
		students.add(new Student("3", "Libby", 4.5));
		students.add(new Student("1", "Chaya", 3.0));
		students.add(new Student("2", "Atara", 4.2));
		System.out.println(students.toString());
		Collections.sort(students);
		System.out.println(students.toString());
		Collections.sort(students, new StudentBalanceComparator());
		System.out.println(students.toString());
		Sorter<Student> insertionSort = new Sorter<Student>();
		System.out.println(students.toString());
		students = insertionSort.sort(students);
		System.out.println(students.toString()); 
	}

}
