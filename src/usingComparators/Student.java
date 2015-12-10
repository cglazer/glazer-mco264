package usingComparators;


public class Student implements Comparable<Student>{
String studentID;
String firstName;
Double balance;
public Student(String studentID, String firstName, Double balance) {
	this.studentID = studentID;
	this.firstName = firstName;
	this.balance = balance;
}

public int compareTo(Student other){
	return this.studentID.compareTo(other.studentID);
}

public String getStudentID() {
	return studentID;
}

public String getFirstName() {
	return firstName;
}

public Double getBalance() {
	return balance;
}

@Override
public String toString() {
	return "Student [studentID=" + studentID + ", firstName=" + firstName + ", balance=" + balance + "]";
}

}
