package schoolApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class School {

	private String schoolName;
	private Address address;
	private String phoneNumber;
	private ArrayList<Person> people;
	private ArrayList<Course> courses;
	private ArrayList<Department> departments;

	public School(String schoolname, Address address, String phoneNumber)
			throws NullPointerException, FileNotFoundException,
			InvalidDataException, DuplicateDataException,
			InvalidEmployeeException {
		this(schoolname, address, phoneNumber, null, null, null, null);
	}

	public School(String schoolname, Address address, String phoneNumber,
			String teacherFileName, String studentFileName,
			String departmentFileName, String courseFileName)
			throws NullPointerException, FileNotFoundException,
			InvalidDataException, DuplicateDataException,
			InvalidEmployeeException {
		if (schoolname == null || address == null || phoneNumber == null) {
			throw new NullPointerException();
		}
		this.schoolName = schoolname;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.people = new ArrayList<Person>();
		this.courses = new ArrayList<Course>();
		this.departments = new ArrayList<Department>();
		readFiles(teacherFileName, studentFileName, courseFileName,
				departmentFileName);
	}

	/**
	 * this method reads in data from files and adds instances of people,
	 * courses and departments to their respective lists
	 * 
	 * @throws InvalidEmployeeException
	 */
	@SuppressWarnings("resource")
	public void readFiles(String teacherFileName, String studentFileName,
			String courseFileName, String departmentFileName)
			throws FileNotFoundException, NullPointerException,
			InvalidDataException, DuplicateDataException,
			InvalidEmployeeException {
		String[] tokens = null;
		Integer teacherID;
		String data, firstName, lastName, cityState, departmentID, major, socialSecurityNum;
		String city, state;
		char gender;
		Gender genderR = null;
		Major majorR = null;
		GregorianCalendar dateOfBirth, hireDate;
		Scanner input = new Scanner(new File(courseFileName));
		while (input.hasNext()) {
			data = input.nextLine();
			tokens = data.split(";");
			String courseID = tokens[0];
			String courseName = tokens[1];
			departmentID = tokens[2];
			Integer numberOfCredits = new Integer(tokens[3]);
			addCourse(courseID, courseName, numberOfCredits, departmentID);
		}
		input.close();
		Scanner input2 = new Scanner(new File(departmentFileName));
		while (input2.hasNext()) {
			data = input2.nextLine();
			String[] tokens2 = data.split(";");
			departmentID = tokens2[0];
			String departmentName = tokens2[1];
			String location = tokens2[2];
			String DPhoneNumber = tokens2[3];
			String faxNumber = tokens2[4];
			teacherID = new Integer(tokens2[5]);
			addDepartment(departmentID, departmentName, location, DPhoneNumber,
					faxNumber, teacherID);
		}
		input2.close();
		Scanner input3 = new Scanner(new File(teacherFileName));
		while (input3.hasNext()) {
			teacherID = input3.nextInt();
			firstName = input3.next();
			lastName = input3.next();
			input3.nextLine();
			String street = input3.nextLine();
			cityState = input3.nextLine();
			String[] tokens3 = cityState.split(",");
			city = tokens3[0];
			state = tokens3[1];
			String zipCode = input3.next();
			Address pAddress = new Address(street, city, state, zipCode);
			String pPhoneNumber = input3.next();
			gender = input3.next().charAt(0);
			if (gender == 'f' || gender == 'F') {
				genderR = Gender.FEMALE;
			} else if (gender == 'm' || gender == 'M') {
				genderR = Gender.MALE;
			} else {
				throw new InvalidDataException();
			}
			String hireDateFull = input3.next();
			String[] hireDateSplit = hireDateFull.split("/");
			hireDate = new GregorianCalendar(new Integer(hireDateSplit[2]),
					new Integer(hireDateSplit[0]),
					new Integer(hireDateSplit[1]));
			String birthDate = input3.next();
			String[] birthDateSplit = birthDate.split("/");
			dateOfBirth = new GregorianCalendar(new Integer(birthDateSplit[2]),
					new Integer(birthDateSplit[0]), new Integer(
							birthDateSplit[1]));
			String employeeTypeID = input3.next();
			EmployeeType employeeType = null;
			boolean found = false;
			for (EmployeeType e : EmployeeType.values()) {
				if (employeeTypeID.equalsIgnoreCase(e.toString())) {

					employeeType = EmployeeType.valueOf(employeeTypeID
							.toUpperCase());
					found = true;
					break;
				}
			}
			if (!found) {
				throw new InvalidDataException();
			}
			String departmentCode = input3.next();
			socialSecurityNum = input3.next();
			String degree = input3.next();
			Degree degreeR = null;
			for (Degree d : Degree.values()) {
				if (degree.equalsIgnoreCase(d.toString())) {
					degreeR = Degree.valueOf(d.toString());
					found = true;
					break;
				}
			}
			if (!found) {
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
			if (!found) {
				majorR = Major.UDCD;
			}
			Double salary = input3.nextDouble();
			addTeacher(teacherID, firstName, lastName, pAddress, pPhoneNumber,
					genderR, hireDate, dateOfBirth, employeeType,
					departmentCode, socialSecurityNum, degreeR, majorR, salary);
		}
		input3.close();
		Scanner input4 = new Scanner(new File(studentFileName));
		while (input4.hasNext()) {
			Integer studentID = input4.nextInt();
			lastName = input4.next();
			firstName = input4.next();
			char midInitial = input4.next().charAt(0);
			input4.nextLine();
			cityState = input4.nextLine();
			String[] tokens4 = cityState.split(",");
			city = tokens4[0];
			state = tokens4[1];
			Address sAddress = new Address(city, state);
			String pPhoneNumber = input4.next();
			gender = input4.next().charAt(0);
			if (gender == 'f' || gender == 'F') {
				genderR = Gender.FEMALE;
			} else if (gender == 'm' || gender == 'M') {
				genderR = Gender.MALE;
			} else {
				throw new InvalidDataException();
			}
			major = input4.next();
			boolean found = false;
			for (Major m : Major.values()) {
				if (major.equalsIgnoreCase(m.toString())) {

					majorR = Major.valueOf(major.toUpperCase());
					found = true;
					break;
				}
			}
			if (!found) {
				throw new InvalidDataException();
			}
			addStudent(studentID, firstName, lastName, midInitial, sAddress,
					pPhoneNumber, genderR, majorR);
		}
		input4.close();
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	/**
	 * This method validates phone numbers and fax numbers and returns true if
	 * they are valid
	 */
	private boolean isValidPhoneNumber(String phoneNubmer) {
		if (phoneNumber == null) {
			throw new NullPointerException();
		}
		Pattern validatePhone = Pattern.compile("\\d{10}");
		Matcher matcher = validatePhone.matcher(phoneNumber);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}

	public void setPhoneNumber(String phoneNumber) throws NullPointerException,
			InvalidDataException {
		if (phoneNumber == null) {
			throw new NullPointerException();
		}
		if (isValidPhoneNumber(phoneNumber)) {
			this.phoneNumber = phoneNumber;
		} else {
			throw new InvalidDataException();
		}
	}

	public String getSchoolName() {
		return this.schoolName;
	}

	public Address getAddress() {
		return this.address;
	}

	/**
	 * This method returns a list of all people involved in the school
	 */
	public String getPeople() {
		return this.people.toString();
	}

	/**
	 * This method returns a list of all courses offered in the school
	 */
	public String getCourses() {
		return this.courses.toString();
	}

	/**
	 * This method returns a list of all departments offered in the school
	 */
	public ArrayList<Department> getDepartments() {
		return this.departments;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("School: ");
		buffer.append("School Name: ");
		buffer.append(this.schoolName);
		buffer.append("Address: ");
		buffer.append(this.address);
		buffer.append("Phone Number: ");
		buffer.append(this.phoneNumber);
		buffer.append("People: ");
		buffer.append(this.people);
		buffer.append("Courses: ");
		buffer.append(this.courses);
		buffer.append("Departments: ");
		buffer.append(this.departments);
		return buffer.toString();
	}

	/**
	 * This method checks to see if a teacher already exists and if not, it is
	 * added to the arrayList of people
	 */
	public void addTeacher(Integer teacherID, String firstName,
			String lastName, Address pAddress, String phoneNumber,
			Gender gender, GregorianCalendar hireDate,
			GregorianCalendar dateOfBirth, EmployeeType employeeType,
			String departmentID, String socialSecurityNum, Degree degree,
			Major major, Double salary) throws NullPointerException,
			InvalidDataException, DuplicateDataException,
			InvalidEmployeeException {
		for (Person p : people) {
			if (p.getID().equals(teacherID)) {
				throw new DuplicateDataException();
			}
		}
		people.add(new Teacher(teacherID, firstName, lastName, pAddress,
				gender, hireDate, dateOfBirth, employeeType, departmentID,
				socialSecurityNum, degree, major, salary));
	}

	/**
	 * this method checks to see if an instance of student exists and if not it
	 * adds the instance to the arrayList of people
	 */
	private void addStudent(Integer studentID, String firstName,
			String lastName, char midInitial, Address sAddress,
			String pPhoneNumber, Gender genderR, Major majorR)
			throws NullPointerException, InvalidDataException,
			DuplicateDataException {
		for (Person p : people) {
			if (p.getID().equals(studentID)) {
				throw new DuplicateDataException();
			}
		}
		people.add(new Student(studentID, firstName, lastName, midInitial,
				sAddress, pPhoneNumber, genderR, majorR));
		// TODO Auto-generated method stub
	}

	/**
	 * this method checks to see if an instance of student exists and if not it
	 * adds the instance to the arrayList of people
	 */
	public void addStudent(Integer studentID, String firstName,
			String lastName, Character midInitial, Address address,
			String phoneNumber, Gender gender, Major major,
			String socialSecurityNumber, GregorianCalendar enrolledDate,
			GregorianCalendar dateOfBirth) throws NullPointerException,
			InvalidDataException, DuplicateDataException {
		for (Person p : people) {
			if (p.getID().equals(studentID)) {
				throw new DuplicateDataException();
			}
		}
		people.add(new Student(studentID, firstName, lastName, midInitial,
				address, phoneNumber, gender, major, socialSecurityNumber,
				enrolledDate, dateOfBirth));

	}

	/**
	 * This method creates an instance of Course and checks if it already
	 * exists. If not, it adds the instance to the list of courses
	 */
	public void addCourse(String courseID, String description,
			Integer numCredits, String departmentID)
			throws NullPointerException, InvalidDataException,
			DuplicateDataException {
		Course c = new Course(courseID, description, numCredits, departmentID);
		if (courses.contains(c)) {
			throw new DuplicateDataException();
		}
		courses.add(c);
	}

	/**
	 * This method creates an instance of Department and checks if it already
	 * exists. If not, it adds the instance to the list of departments
	 */
	public void addDepartment(String departmentID, String departmentName,
			String location, String phoneNumber, String faxNumber,
			Integer departmentChairperson) throws NullPointerException,
			InvalidDataException, DuplicateDataException {
		Department d = new Department(departmentID, departmentName, location,
				phoneNumber, faxNumber, departmentChairperson);
		if (departments.contains(d)) {
			throw new DuplicateDataException();
		}
		departments.add(d);
	}

	/**
	 * This method finds an instance of a teacher and removes it. If it's not
	 * found, an exception is thrown
	 */
	public void removeTeacher(Integer teacherID) throws NotFoundException {
		boolean found = false;
		for (Person p : people) {
			if (p instanceof Teacher) {
				if (p.getID().equals(teacherID)) {
					people.remove(p);
					found = true;
					break;
				}
			}
		}
		if (!found) {
			throw new NotFoundException();
		}
	}

	/**
	 * This method finds an instance of a student and removes it. If it's not
	 * found, an exception is thrown
	 */
	public void removeStudent(Integer studentID) throws NotFoundException {
		boolean found = false;
		for (Person p : this.people) {
			if (p instanceof Student) {
				if (p.getID().equals(studentID)) {
					this.people.remove(p);
					found = true;
					break;
				}
			}

		}
		if (!found) {
			throw new NotFoundException();
		}
	}

	/**
	 * This method finds an instance of a Course and removes it. If it's not
	 * found, an exception is thrown
	 */
	public void removeCourse(String courseID) throws NotFoundException {
		boolean found = false;
		for (Course c : courses) {
			if (c.getCourseID().equalsIgnoreCase(courseID)) {
				courses.remove(c);
				found = true;
				break;
			}
		}
		if (!found) {
			throw new NotFoundException();
		}
	}

	/**
	 * This method modifies a teacher's last name an exception is thrown if the
	 * ID is not found
	 */
	public void modifyTeacherLastName(Integer teacherID, String newLastName)
			throws NotFoundException {
		boolean found = false;
		for (Person p : people) {
			if (p instanceof Teacher) {
				if (teacherID.equals(p.getID())) {
					p.setLastName(newLastName);
					found = true;
					break;
				}
			}
		}
		if (!found) {
			throw new NotFoundException();
		}
	}

	/**
	 * This method modifies a teacher's address an exception is thrown if the ID
	 * is not found
	 */
	public void modifyTeacherAddress(Integer teacherID, Address address)
			throws NullPointerException, InvalidDataException,
			NotFoundException {
		boolean found = false;
		for (Person p : people) {
			if (p instanceof Teacher) {
				if (teacherID.equals(p.getID())) {
					p.setAddress(address);
					found = true;
					break;
				}
			}
		}
		if (!found) {
			throw new NotFoundException();
		}
	}

	/**
	 * This method modifies a teacher's degree. an exception is thrown if the ID
	 * is not found
	 */
	public void modifyTeacherDegree(Integer teacherID, Degree degree,
			Major major) throws NotFoundException {
		boolean found = false;
		for (Person p : people) {
			if (p instanceof Teacher) {
				if (teacherID.equals(p.getID())) {
					((Teacher) p).setDegree(degree);
					((Teacher) p).setMajorID(major);
					found = true;
					break;
				}
			}
		}
		if (!found) {
			throw new NotFoundException();
		}
	}

	/**
	 * This method gives a teacher a percent raise in his salary
	 * 
	 * @throws NotFoundException
	 *             -an exception is thrown if the ID is not found
	 */
	public void giveTeacherPercentRaise(Integer teacherID, Double percent)
			throws NullPointerException, InvalidDataException,
			NotFoundException {
		boolean found = false;
		for (Person p : people) {
			if (p instanceof Teacher) {
				if (teacherID.equals(p.getID())) {
					((Teacher) p).applyRaise(percent);
					found = true;
					break;
				}
			}
		}
		if (!found) {
			throw new NotFoundException();
		}
	}

	/**
	 * This method gives a teacher a bonus raise
	 * 
	 * @throws NotFoundException
	 *             - an exception is thrown if the ID is not found
	 */
	public void giveTeacherRaise(Integer teacherID, Double amount)
			throws NullPointerException, InvalidDataException,
			NotFoundException {
		boolean found = false;
		for (Person p : people) {
			if (p instanceof Teacher) {
				if (teacherID.equals(p.getID())) {
					((Teacher) p).applyBonusRaise(amount);
					found = true;
					break;
				}
			}
		}
		if (!found) {
			throw new NotFoundException();
		}
	}

	/**
	 * This method modifies a student's last name
	 * 
	 * @throws NotFoundException
	 *             - an exception is thrown if the ID is not found
	 */
	public void modifyStudentLastName(Integer studentID, String newLastName)
			throws NotFoundException {
		boolean found = false;
		for (Person p : people) {
			if (p instanceof Student) {
				if (studentID.equals(p.getID())) {
					p.setLastName(newLastName);
					found = true;
					break;
				}
			}
		}
		if (!found) {
			throw new NotFoundException();
		}
	}

	/**
	 * This method modifies a student's phone number
	 * 
	 * @throws NotFoundException
	 *             - an exception is thrown if the ID is not found
	 */
	public void modifyStudentPhoneNumber(Integer studentID,
			String newPhoneNumber) throws NullPointerException,
			InvalidDataException, NotFoundException {
		boolean found = false;
		for (Person p : people) {
			if (p instanceof Student) {
				if (studentID.equals(p.getID())) {
					p.setPhoneNumber(newPhoneNumber);
					found = true;
					break;
				}
			}
		}
		if (!found) {
			throw new NotFoundException();
		}
	}

	/**
	 * This method adds a completed course to a student's list of completed
	 * courses
	 * 
	 * @throws NotFoundException
	 *             - an exception is thrown if the ID is not found
	 */
	public void addCompletedCourse(Integer studentID, String courseID,
			Grade grade) throws NullPointerException, InvalidDataException,
			DuplicateDataException, NotFoundException {
		boolean found = false;
		boolean cfound = false;
		for (Course c : courses) {
			if (c.getCourseID().equalsIgnoreCase(courseID)) {
				CompletedCourse cc = new CompletedCourse(c.getCourseID(),
						c.getDescription(), c.getNumCredits(),
						c.getDepartmentID(), studentID, grade);
				cfound = true;
				for (Person p : people) {
					if (p instanceof Student) {
						if (studentID.equals(p.getID())) {
							((Student) p).addCompletedCourse(cc, grade);
							found = true;
							break;
						}
					}
				}
				if (!found) {
					throw new NotFoundException();
				}
			}
		}
		if (!cfound) {
			throw new NotFoundException();
		}
	}

	/**
	 * This method returns a students gpa
	 * 
	 * @throws NotFoundException
	 *             - an exception is thrown if the ID is not found
	 */
	public Double getStudentGPA(Integer studentID) throws NotFoundException {
		for (Person p : people) {
			if (p instanceof Student) {
				if (studentID.equals(p.getID())) {
					return ((Student) p).getGPA();
				}
			}
		}
		throw new NotFoundException();
	}

	/**
	 * This method gets the grade a student was earned from a specific course
	 * 
	 * @throws NotFoundException
	 *             - an exception is thrown if the ID is not found
	 */
	public Grade getGradeofCourse(Integer studentID, String courseID)
			throws InvalidDataException, NullPointerException,
			NotFoundException {
		for (Person p : people) {
			if (p instanceof Student) {
				if (p.getID().equals(studentID)) {
					return ((Student) p).getGradeofCourse(courseID);
				}
			}
		}
		throw new InvalidDataException();
	}

	/**
	 * This method returns a list of courses a student took in a specific
	 * department
	 * 
	 * @throws NotFoundException
	 *             - an exception is thrown if the ID is not found
	 */
	public ArrayList<String> getCoursesbyDepartment(Integer studentID,
			String departmentID) throws NotFoundException {
		for (Person p : people) {
			if (p instanceof Student) {
				if (p.getID().equals(studentID)) {
					return ((Student) p).getCoursesbyDepartment(departmentID);
				}
			}
		}
		throw new NotFoundException();
	}

	/**
	 * This method returns a list of courses that a student earned a specific
	 * grade
	 * 
	 * @throws NotFoundException
	 *             - an exception is thrown if the ID is not found
	 */
	public ArrayList<String> getCoursesbyGrade(Integer studentID, Grade g)
			throws NotFoundException {
		for (Person p : people) {
			if (p instanceof Student) {
				if (p.getID().equals(studentID)) {
					return ((Student) p).getCoursesbyGrade(g);
				}
			}
		}
		throw new NotFoundException();
	}

	/**
	 * This method returns a list of teachers, sorted by name
	 */
	public String getTeachersSortedByName() {
		List<Person> teachers = new ArrayList<Person>();
		for (Person p : people) {
			if (p instanceof Teacher) {
				teachers.add(p);
			}
		}
		Collections.sort(teachers, new PersonNameComparator());
		// return a list of Teachers sorted by lastname, firstname);
		return teachers.toString();
	}

	/**
	 * This method returns a list of teachers, sorted by ID
	 */
	public String getTeachersSortedById() {
		List<Person> teachers = new ArrayList<Person>();
		for (Person p : people) {
			if (p instanceof Teacher) {
				teachers.add(p);
			}
		}
		Collections.sort(teachers, new PersonIdComparator());
		return teachers.toString();
	}

	/**
	 * This method returns a list of students, sorted by ID
	 */
	public String getStudentsSortedById() {
		List<Person> students = new ArrayList<Person>();
		for (Person p : people) {
			if (p instanceof Student) {
				students.add(p);
			}
		}
		Collections.sort(students, new PersonIdComparator());
		// return list of Students sorted by studentid {
		return students.toString();
	}

	/**
	 * This method returns a list of teachers, sorted by name
	 */
	public String getStudentsSortedByName() {
		List<Person> students = new ArrayList<Person>();
		for (Person p : people) {
			if (p instanceof Student) {
				students.add(p);
			}
		}
		Collections.sort(students, new PersonNameComparator());
		// return list of Students sorted by lastname, firstname {
		return students.toString();
	}

	/**
	 * This method adds a taught course to a teacher's list of taught courses
	 * 
	 * @throws NotFoundException
	 *             - if the teacher or course is not found
	 */
	public void addTaughtCourse(Integer teacherID, String courseID,
			Integer year, Semester semester, Section section)
			throws NullPointerException, InvalidDataException,
			DuplicateDataException, NotFoundException {
		boolean found = false;
		boolean cfound = false;
		for (Person p : people) {
			if (p instanceof Teacher) {
				if (p.getID().equals(teacherID)) {
					found = true;
					for (Course c : courses) {
						if (c.getCourseID().equalsIgnoreCase(courseID)) {
							((Teacher) p).taughtCourse(c, year, semester,
									section);
							cfound = true;
							break;
						}
					}
					if (!cfound) {
						throw new NotFoundException();
					}
				}
			}
		}
		if (!found) {
			throw new NotFoundException();
		}
	}

	/**
	 * This method returns the number of courses a teacher taught in a specific
	 * semester
	 * 
	 * @throws NotFoundException
	 *             - if the teacher is not found
	 */
	public Integer howManyCoursesPerSemester(Integer teacherID, Integer year,
			Semester semester) throws NotFoundException {
		for (Person p : people) {
			if (p instanceof Teacher) {
				if (p.getID().equals(teacherID)) {
					return ((Teacher) p).howManyCoursesPerSemester(year,
							semester);
				}
			}
		}
		throw new NotFoundException();
	}
}