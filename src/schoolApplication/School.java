package schoolApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class School {

	private String schoolName;
	private Address address;
	private String phoneNumber;
	private ArrayList<Person> people;
	private ArrayList<Course> courses;
	private ArrayList<Department> departments;
	private ArrayList<Person> teachers;
	private ArrayList<Person> students;

	public School(String schoolname, Address address, String phoneNumber)
			throws NullPointerException, FileNotFoundException,
			InvalidDataException {
		this(schoolname, address, phoneNumber, null, null, null, null);
	}

	public School(String schoolname, Address address, String phoneNumber,
			String teachFileName, String studentFileName,
			String departmentFileName, String courseFileName)
			throws NullPointerException, FileNotFoundException,
			InvalidDataException {
		if (schoolname == null || address == null || phoneNumber == null) {
			throw new NullPointerException();
		}
		this.schoolName = schoolname;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.people = new ArrayList<Person>();
		this.courses = new ArrayList<Course>();
		this.departments = new ArrayList<Department>();
		readFiles();
	}
	public void readFiles() throws FileNotFoundException, NullPointerException, InvalidDataException{
		String data;
		String[] tokens = null;
		String[] tokens2;
		String[] tokens3;
		String departmentID;
		Integer teacherID;
		String firstName;
		String lastName;
		String cityState;
		String[] tokens4;
		String city;
		String state;
		String pPhoneNumber;
		char gender;
		Gender genderR = null;
		String major;
		Major majorR = null;
		String socialSecurityNum;
		GregorianCalendar dateOfBirth;
		GregorianCalendar hireDate;
		boolean found = false;
		Scanner input = new Scanner(new File("./courses.txt"));
		while (input.hasNext()) {
			data = input.next();
			tokens = data.split(";");
			String courseID = tokens[0];
			String courseName = tokens[1];
			departmentID = tokens[2];
			Integer numberOfCredits = new Integer(tokens[3]);
			courses.add(new Course(courseID, courseName, numberOfCredits,
					departmentID));
		}
		Scanner input2 = new Scanner(new File("./departments.txt"));
		while (input2.hasNext()) {
			data = input2.next();
			tokens2 = data.split(";");
			departmentID = tokens[0];
			String departmentName = tokens[1];
			String location = tokens[2];
			String DPhoneNumber = tokens[3];
			String faxNumber = tokens[4];
			teacherID = new Integer(tokens[5]);
			departments.add(new Department(departmentID, departmentName,
					location, DPhoneNumber, faxNumber, teacherID));
		}

		Scanner input3 = new Scanner(new File("./teachers.txt"));
		while (input3.hasNext()) {
			teacherID = input3.nextInt();
			firstName = input3.next();
			lastName = input3.next();
			String street = input3.nextLine();
			cityState = input3.next();
			tokens3 = cityState.split(",");
			city = tokens[0];
			state = tokens[1];
			String zipCode = input3.next();
			Address pAddress = new Address(street, city, state, zipCode);
			pPhoneNumber = input3.next();
			gender = input3.next().charAt(0);
			if (gender == 'f' || gender == 'F') {
				genderR = Gender.FEMALE;
			} else {
				genderR = Gender.MALE;
			}
			String hireDateFull = input3.next();
			String[] hireDateSplit = hireDateFull.split("/");
			hireDate = new GregorianCalendar(new Integer(hireDateSplit[0]),
					new Integer(hireDateSplit[1]),
					new Integer(hireDateSplit[2]));
			String birthDate = input3.next();
			String[] birthDateSplit = birthDate.split("/");
			dateOfBirth = new GregorianCalendar(new Integer(birthDateSplit[0]),
					new Integer(birthDateSplit[1]), new Integer(
							birthDateSplit[2]));
			String employeeTypeID = input3.next();
			EmployeeType employeeType = null;
			found = false;
			for (EmployeeType e : EmployeeType.values()) {
				if (employeeTypeID.equalsIgnoreCase(e.toString())) {

					employeeType = EmployeeType.valueOf(employeeTypeID
							.toUpperCase());
					found = true;
					break;
				}
			}
			if (found = false) {
				throw new InvalidDataException();
			}
			String departmentCode = input3.next();
			socialSecurityNum = input3.next();
			String degree = input3.next();
			Degree degreeR = null;
			for (Degree d : Degree.values()) {
				if (degree.equalsIgnoreCase(d.toString())) {

					degreeR = Degree.valueOf(degree.toUpperCase());
					found = true;
					break;
				}
			}
			if (found = false) {
				throw new InvalidDataException();
			}
			major = input3.next();
			found = false;
			for (Major m : Major.values()) {
				if (major.equalsIgnoreCase(m.toString())) {

					majorR = Major.valueOf(major.toUpperCase());
					found = true;
					break;
				}
			}
			Double salary = input3.nextDouble();
			people.add((new Teacher(teacherID, firstName, lastName, pAddress,
					genderR, hireDate, dateOfBirth, employeeType,
					socialSecurityNum, degreeR, majorR, salary)));
		}

		Scanner input4 = new Scanner(new File("./students.txt"));
		while (input4.hasNext()) {
			Integer studentID = input4.nextInt();
			lastName = input4.next();
			firstName = input4.next();
			char midInitial = input4.next().charAt(0);
			cityState = input4.next();
			tokens4 = cityState.split(",");
			city = tokens4[0];
			state = tokens4[1];
			Address sAddress = new Address(city, state);
			pPhoneNumber = input4.next();
			gender = input4.next().charAt(0);
			if (gender == 'f' || gender == 'F') {
				genderR = Gender.FEMALE;
			} else {
				genderR = Gender.MALE;
			}
			major = input4.next();
			found = false;
			for (Major m : Major.values()) {
				if (major.equalsIgnoreCase(m.toString())) {

					majorR = Major.valueOf(major.toUpperCase());
					found = true;
					break;
				}
			}
			people.add(new Student(studentID, firstName, lastName, midInitial,
					sAddress, phoneNumber, genderR, majorR));
		}
		teachers = new ArrayList<Person>();
		for (Person p : people) {
			if (p instanceof Teacher) {
				teachers.add(p);
			}
		}
		students = new ArrayList<Person>();
		for (Person p : people) {
			if (p instanceof Student) {
				students.add(p);
			}
		}
		input.close();
		input2.close();
		input3.close();
		input4.close();
		// Read in the data from each file, instantiate an instance of the
		// appropriate class (Teacher, Student, Department, or Course) and add
		// the
		// instance to the appropriate ArrayList
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) throws NullPointerException {
		if (phoneNumber == null) {
			throw new NullPointerException();
		}
		this.phoneNumber = phoneNumber;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public Address getAddress() {
		return address;
	}

	public ArrayList<Person> getPeople() {
		return people;
	}

	public ArrayList<Course> getCourses() {
		return courses;
	}

	public ArrayList<Department> getDepartments() {
		return departments;
	}

	@Override
	public String toString() {
		return "School [schoolName=" + schoolName + ", address=" + address
				+ ", phoneNumber=" + phoneNumber + ", people=" + people
				+ ", courses=" + courses + ", departments=" + departments + "]";
	}

	public void addTeacher(Integer teacherID, String firstName,
			String lastName, Address pAddress, Gender gender,
			GregorianCalendar hireDate, GregorianCalendar dateOfBirth,
			EmployeeType employeeType, String socialSecurityNum, Degree degree,
			Major major, Double salary) throws NullPointerException,
			InvalidDataException {
		people.add(new Teacher(teacherID, firstName, lastName, pAddress,
				gender, hireDate, dateOfBirth, employeeType, socialSecurityNum,
				degree, major, salary));
	}

	public void addStudent(Integer studentID, String firstName,
			String lastName, Character midInitial, Address address,
			String phoneNumber, Gender gender, Major major,
			String socialSecurityNumber, GregorianCalendar enrolledDate,
			GregorianCalendar dateOfBirth) throws NullPointerException,
			InvalidDataException {
		people.add(new Student(studentID, firstName, lastName, midInitial,
				address, phoneNumber, gender, major, socialSecurityNumber,
				enrolledDate, dateOfBirth));

	}

	public void addCourse(String courseID, String description,
			Integer numCredits, String departmentID)
			throws NullPointerException, InvalidDataException {
		courses.add(new Course(courseID, description, numCredits, departmentID));
	}

	public void addDepartment(String departmentID, String departmentName,
			String location, String phoneNumber, String faxNumber,
			Integer departmentChairperson) throws NullPointerException,
			InvalidDataException {
		departments.add(new Department(departmentID, departmentName, location,
				phoneNumber, faxNumber, departmentChairperson));
	}

	public void removeTeacher(Integer teacherID) {
		for (Person p : people) {
			if (p instanceof Teacher) {
				if (p.getID() == teacherID) {
					people.remove(p);
				}
			}
		}
	}

	public void removeStudent(Integer studentID) {
		for (Person p : people) {
			if (p instanceof Student) {
				if (p.getID() == studentID) {
					people.remove(p);
				}
			}
		}
	}

	public void removecourse(String courseID) {
		for (Course c : courses) {
			if (c.getCourseID().equalsIgnoreCase(courseID)) {
				courses.remove(c);
			}
		}
	}

	public void modifyTeacherLastName(Integer teacherID, String newLastName) {
		for (Person p : people) {
			if (p instanceof Teacher) {
				if (teacherID.equals(p.getID())) {
					p.setLastName(newLastName);
					break;
				}
			}
		}
	}

	public void modifyTeacherAddress(Integer teacherID, Address address)
			throws NullPointerException, InvalidDataException {
		for (Person p : people) {
			if (p instanceof Teacher) {
				if (teacherID.equals(p.getID())) {
					p.setAddress(address);
					break;
				}
			}
		}
	}

	public void modifyTeacherDegree(Integer teacherID, Degree degree,
			Major major) {
		for (Person p : people) {
			if (p instanceof Teacher) {
				if (teacherID.equals(p.getID())) {
					((Teacher) p).setDegree(degree);
					((Teacher) p).setMajorID(major);
					break;
				}
			}
		}
	}

	public void giveTeacherPercentRaise(Integer teacherID, Double percent)
			throws NullPointerException, InvalidDataException {
		for (Person p : people) {
			if (p instanceof Teacher) {
				if (teacherID.equals(p.getID())) {
					((Teacher) p).applyRaise(percent);
					break;
				}
			}
		}
	}

	public void giveTeacherRaise(Integer teacherID, Double amount)
			throws NullPointerException, InvalidDataException {
		for (Person p : people) {
			if (p instanceof Teacher) {
				if (teacherID.equals(p.getID())) {
					((Teacher) p).setSalary(amount);
					break;
				}
			}
		}
	}

	public void modifyStudentLastName(Integer studentID, String newLastName) {
		for (Person p : people) {
			if (p instanceof Student) {
				if (studentID.equals(p.getID())) {
					p.setLastName(newLastName);
					break;
				}
			}
		}
	}

	public void modifyStudentPhoneNumber(Integer studentID,
			String newPhoneNumber) throws NullPointerException,
			InvalidDataException {
		for (Person p : people) {
			if (p instanceof Student) {
				if (studentID.equals(p.getID())) {
					p.setPhoneNumber(newPhoneNumber);
					break;
				}
			}
		}
	}

	public void addCompletedCourse(Integer studentID, String courseID,
			Grade grade) throws NullPointerException, InvalidDataException {
		for (Course c : courses) {
			if (c.getCourseID().equals(courseID)) {
				courses.add(new CompletedCourse(c.getCourseID(), c
						.getDescription(), c.getNumCredits(), c
						.getDepartmentID(), studentID, grade));
				for (Person p : people) {
					if (p instanceof Student) {
						if (studentID == p.getID()) {
							((Student) p).addCompletedCourse(c, grade);
							break;
						}
					}
				}
			}
		}

		// search through ArrayList of Courses and find the courseID , then
		// based on the data you have, instantiate a CompletedCourse and add
		// that
		// course to record of the Student with id, studentID.
	}

	public Double getStudentGPA(Integer studentID) throws InvalidIDException {
		for (Person p : people) {
			if (p instanceof Student) {
				if (studentID == p.getID()) {
					return ((Student) p).getGPA();

				}
			}
		}
		throw new InvalidIDException();
	}

	public Grade getGradeofCourse(Integer studentID, String courseID)
			throws InvalidIDException, InvalidDataException {
		for (Person p : people) {
			if (p instanceof Student) {
				if (p.getID() == studentID) {
					return ((Student) p).getGradeofCourse(courseID);

				}
			}
		}
		throw new InvalidDataException();
	}

	public ArrayList<String> getCoursesbyDepartment(Integer studentID,
			String departmentID) throws InvalidIDException {
		for (Person p : people) {
			if (p instanceof Student) {
				if (p.getID() == studentID) {
					return ((Student) p).getCoursesbyDepartment(departmentID);
				}
			}
		}
		throw new InvalidIDException();
	}

	public ArrayList<String> getCoursesbyGrade(Integer studentID, Grade g)
			throws InvalidIDException {
		for (Person p : people) {
			if (p instanceof Student) {
				if (p.getID().equals(studentID)) {
					return ((Student) p).getCoursesbyGrade(g);
				}
			}
		}
		throw new InvalidIDException();

	}

	public String getTeachersSortedByName() {
		// return a list of Teachers sorted by lastname, firstname);

		return Teacher.nameComparator.toString();

	}

	public String getTeachers() {
		// return list of Teachers sorted by teacherid
		return Teacher.IDComparator.toString();

	}

	public String getStudents() {
		// return list of Students sorted by studentid {
		return Student.IDComparator.toString();
	}

	public String getStudentsByName() {
		// return list of Students sorted by lastname, firstname {
		return Student.nameComparator.toString();
	}

	public void addTaughtCourse(Integer teacherID, String courseID,
			Integer year, Semester semester, Section section)
			throws NullPointerException, InvalidEntryException,
			InvalidDataException {
		for (Person p : people) {
			if (p instanceof Teacher) {
				if (p.getID() == teacherID) {
					for (Course c : courses) {
						if (c.getCourseID().equals(courseID)) {
							((Teacher) p).taughtCourse(c, year, semester,
									section);
							break;
						}
					}
				}
			}
		}
	}

	public Integer howManyCoursesPerSemester(Integer teacherID, Integer year,
			Semester semester) throws InvalidIDException {
		for (Person p : people) {
			if (p instanceof Teacher) {
				if (p.getID() == teacherID) {
					return ((Teacher) p).howManyCoursesPerSemester(year,
							semester);
				}
			}
		}
		throw new InvalidIDException();
		// search for the Teacher with teacherID and then invoke the
		// Teacher.howManyCoursesPerSemester to find out how many courses that
		// particular Teacher taught that semester.
	}

}

// @Override
// public int compareTo(Teacher o) {
// int compareID= ((Teacher)o).getID();
// return this. -o;
// }

