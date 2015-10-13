package schoolApplication;

import java.io.FileNotFoundException;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManageSchool {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		System.out
				.println("Congradulations on opeing your school!\n What would you like the name of your school to be?");
		String schoolName = input.nextLine();
		System.out
				.println("What is the address of your school(street and number)?");
		String addressS = input.nextLine();
		System.out.println("What city is your school located in?");
		String city = input.nextLine();
		System.out.println("What state is your school located in?");
		String state = input.nextLine();
		System.out.println("What is the zip code of your school?");
		String zipCode = input.next();
		while (!(zipCode.length() == 5 || zipCode.length() == 9)) {
			System.out
					.println("Invalid zip code. Please reenter your zip code.");
			zipCode = input.next();
		}
		Address address = null;
		try {
			address = new Address(addressS, city, state, zipCode);
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidDataException e) {
			System.out.println("You have invalid address information.");
			System.exit(1);
		}
		String phoneNumber;
		do {
			System.out
					.println("What is your school's phone number? Please enter their 10 digit number with no spaces.");
			phoneNumber = input.next();
		} while (phoneNumber.length() != 10);
		School aSchool = null;
		try {

			aSchool = new School(schoolName, address, phoneNumber,
					"teachers.txt", "students.txt", "departments.txt",
					"courses.txt");
		} catch (InvalidEmployeeException e) {
			// TODO Auto-generated catch block
			System.out.println("An employee must be eighteen years or older.");
			System.exit(1);
		} catch (DuplicateDataException e) {
			// TODO Auto-generated catch block
			System.out.println("Invalid. Duplicate data.");

		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File not found.");
			System.exit(1);
		} catch (InvalidDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String firstName;
		String lastName;
		Major major = null;
		String degreeS;
		String genderS;
		Gender gender;
		String dateOfBirthFull;
		String[] dateOfBirthSplit;
		GregorianCalendar dateOfBirth;
		String socialSecurityNum;

		Degree degree = null;
		String majorS;
		String departmentID;
		Integer teacherID;
		String courseID;
		Integer studentID;
		String gradeS;
		Grade grade = null;
		String semesterS;
		Integer year;
		Semester semester = null;
		boolean found = false;
		int choice = menu();
		do {
			switch (choice) {
			case 1:
				System.out.println(aSchool.getSchoolName());
				break;
			case 2:
				System.out.println(aSchool.getAddress());
				break;
			case 3:
				System.out.println(aSchool.getPhoneNumber());
				break;
			case 4:
				do {
					System.out
							.println("What is the school's new phone number? Please enter the 10 digit number with no spaces");
					phoneNumber = input.next();
				} while (phoneNumber.length() != 10);
				try {
					aSchool.setPhoneNumber(phoneNumber);
				} catch (NullPointerException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (InvalidDataException e2) {
					System.out.println("Invalid phone number.");
				}
				break;
			case 5:
				System.out.println("What is the teacher's ID?");
				teacherID = input.nextInt();
				System.out.println("What is the teacher's first name?");
				firstName = input.next();
				System.out.println("What is the teacher's last name?");
				lastName = input.next();
				input.nextLine();
				System.out.println("Please enter the street and number");
				addressS = input.nextLine();
				System.out.println("Please enter the city.");
				city = input.nextLine();
				System.out.println("Please enter the state.");
				state = input.nextLine();
				System.out.println("Please ender the zip code.");
				zipCode = input.next();
				while (!(zipCode.length() == 5 || zipCode.length() == 9)) {
					System.out
							.println("Invalid zip code. Please reenter your zip code.");
					zipCode = input.next();
				}
				try {
					address = new Address(addressS, city, state, zipCode);
				} catch (NullPointerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidDataException e) {
					System.out.println("Invalid address information");
				}
				String tPhoneNumber;
				do {
					System.out
							.println("Please enter the teacher's 10 digit phone number.");
					tPhoneNumber = input.next();
				} while (tPhoneNumber.length() != 10);
				do {
					System.out.println("Please enter the gender(Female/Male).");
					genderS = input.next();
				} while (!(genderS.equalsIgnoreCase("female") || (genderS
						.equalsIgnoreCase("male"))));

				if (genderS.equalsIgnoreCase("female")) {
					gender = Gender.FEMALE;
				} else {
					gender = Gender.MALE;
				}
				Pattern validate = Pattern.compile("\\d{2}/\\d{2}/\\d{4}");
				Matcher matcher;
				String hireDateFull;
				do {
					System.out
							.println("Enter the date hired in the following format(mm/dd/yyyy)");
					hireDateFull = input.next();
					matcher = validate.matcher(hireDateFull);
				} while (!(matcher.matches()));
				String[] hireDateSplit = hireDateFull.split("/");
				GregorianCalendar hireDate = new GregorianCalendar(new Integer(
						hireDateSplit[2]), new Integer(hireDateSplit[0]),
						new Integer(hireDateSplit[1]));
				do {
					System.out
							.println("Enter the birth date in following format(mm/dd/yyyy)");
					dateOfBirthFull = input.next();
					matcher = validate.matcher(dateOfBirthFull);
				} while (!(matcher.matches()));
				dateOfBirthSplit = dateOfBirthFull.split("/");
				dateOfBirth = new GregorianCalendar(new Integer(
						dateOfBirthSplit[2]), new Integer(dateOfBirthSplit[0]),
						new Integer(dateOfBirthSplit[1]));
				String employeeTypeS;
				do {
					System.out
							.println("Enter the teacher's title(professor/instructor).");
					employeeTypeS = input.next();
				} while (!(employeeTypeS.equalsIgnoreCase("professor") || (employeeTypeS
						.equalsIgnoreCase("instructor"))));
				EmployeeType employeeType;
				if (employeeTypeS.equalsIgnoreCase("professor")) {
					employeeType = EmployeeType.PROFESSOR;
				} else {
					employeeType = EmployeeType.INSTRUCTOR;
				}
				System.out.println("Please enter the teacher's departmentId.");
				departmentID = input.next();
				System.out.println("Please enter the social security number.");
				socialSecurityNum = input.next();
				// every country has different amount of digits in the ssn
				do {
					System.out
							.println("Please enter the teacher's degree. Enter the Acronym. For example, a Bachelor degree would be 'BA'.");
					degreeS = input.next();
					found = false;
					for (Degree d : Degree.values()) {
						if (degreeS.equalsIgnoreCase(d.toString())) {
							degree = Degree.valueOf(d.toString());
							found = true;
							break;
						}
					}
				} while (!found);
				input.nextLine();
				do {
					System.out
							.println("Please enter the teacher's major. Enter the full name");
					majorS = input.nextLine();
					found = false;
					for (Major m : Major.values()) {
						if (majorS.equalsIgnoreCase(m.toString())) {
							major = Major.valueOf(majorS.toUpperCase());
							found = true;
							break;
						}
					}
				} while (found = false);
				double salary;
				System.out.println("Please enter the teacher's salary.");
				salary = input.nextDouble();
				while (salary < 20000 || salary > 125000) {
					System.out
							.println("Invalid. Please enter the teacher's salary.");
					salary = input.nextDouble();
				}
				try {
					aSchool.addTeacher(teacherID, firstName, lastName, address,
							tPhoneNumber, gender, hireDate, dateOfBirth,
							employeeType, departmentID, socialSecurityNum,
							degree, major, salary);
				} catch (InvalidEmployeeException e) {
					System.out
							.println("Error. Cannot process the requested data. An employee must be 18 years.");

				} catch (DuplicateDataException e) {
					System.out
							.println("Error. An instance of this already exists");

				} catch (NullPointerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidDataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 6:
				System.out.println("enter ID");
				studentID = input.nextInt();
				System.out.println("enter first name");
				firstName = input.next();
				System.out.println("Enter last name");
				lastName = input.next();
				System.out.println("Enter midInitial");
				char midInitial = input.next().charAt(0);
				input.nextLine();
				System.out.println("Enter street name and number.");
				addressS = input.nextLine();
				System.out.println("Enter city");
				city = input.nextLine();
				System.out.println("Enter state");
				state = input.nextLine();
				System.out.println("Enter zip code");
				zipCode = input.next();
				while (!(zipCode.length() == 5 || zipCode.length() == 9)) {
					System.out
							.println("Invalid zip code. Please reenter your zip code.");
					zipCode = input.next();
				}
				try {
					address = new Address(addressS, city, state, zipCode);
				} catch (NullPointerException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InvalidDataException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				do {
					System.out
							.println("Enter the 10 digit phone number with no spaces.");
					phoneNumber = input.next();
				} while (phoneNumber.length() != 10);
				do {
					System.out.println("Please enter the gender(Female/Male)");
					genderS = input.next();
				} while (!(genderS.equalsIgnoreCase("female") || (genderS
						.equalsIgnoreCase("male"))));
				if (genderS.equalsIgnoreCase("female")) {
					gender = Gender.FEMALE;
				} else {
					gender = Gender.MALE;
				}
				input.nextLine();
				do {
					System.out
							.println("Please enter the student's major. Enter the full name.");
					majorS = input.nextLine();
					found = false;
					for (Major m : Major.values()) {
						if (majorS.equalsIgnoreCase(m.toString())) {
							major = Major.valueOf(majorS.toUpperCase());
							found = true;
							break;
						}
					}
				} while (!found);
				System.out.println("Please enter the social security number.");
				socialSecurityNum = input.next();
				System.out
						.println("Enter the hire date in the following format(mm/dd/yyyy)");
				String enrolledDateFull = input.next();
				String[] enrolledDateSplit = enrolledDateFull.split("/");
				GregorianCalendar enrolledDate = new GregorianCalendar(
						new Integer(enrolledDateSplit[2]), new Integer(
								enrolledDateSplit[0]), new Integer(
								enrolledDateSplit[1]));
				System.out
						.println("Enter the birth date in following format(mm/dd/yyyy)");
				dateOfBirthFull = input.next();
				dateOfBirthSplit = dateOfBirthFull.split("/");
				dateOfBirth = new GregorianCalendar(new Integer(
						dateOfBirthSplit[2]), new Integer(dateOfBirthSplit[0]),
						new Integer(dateOfBirthSplit[1]));
				try {

					aSchool.addStudent(studentID, firstName, lastName,
							midInitial, address, phoneNumber, gender, major,
							socialSecurityNum, enrolledDate, dateOfBirth);
				} catch (DuplicateDataException e) {
					System.out.println("An instance of this already exists");
				} catch (NullPointerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidDataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 7:
				System.out.println("Enter courseID");
				courseID = input.next();
				input.nextLine();
				System.out.println("Enter course description");
				String description = input.nextLine();
				System.out
						.println("Enter the number of credits the class is worth");
				Integer numCredits = input.nextInt();
				System.out.println("Enter department ID");
				departmentID = input.next();
				try {

					aSchool.addCourse(courseID, description, numCredits,
							departmentID);
				} catch (DuplicateDataException e) {
					System.out.println("An instance of this already exists");
				} catch (NullPointerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidDataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 8:
				System.out.println("Enter department ID");
				departmentID = input.next();
				input.nextLine();
				System.out.println("Enter department name");
				String departmentName = input.nextLine();
				System.out.println("Enter department location");
				String location = input.nextLine();
				do {
					System.out
							.println("Enter the 10 digit phone number with no spaces.");
					phoneNumber = input.next();
				} while (phoneNumber.length() != 10);
				String faxNumber;
				do {
					System.out
							.println("Enter the 10 digit fax number with no spaces.");
					faxNumber = input.next();
				} while (faxNumber.length() != 10);
				System.out.println("Enter chairperson ID");
				Integer departmentChairperson = input.nextInt();
				try {

					aSchool.addDepartment(departmentID, departmentName,
							location, phoneNumber, faxNumber,
							departmentChairperson);
				} catch (DuplicateDataException e) {
					System.out.println("An instance of this already exists");

				} catch (NullPointerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidDataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 9:
				System.out
						.println("Which teacher would you like to remove? Please enter the teacher's ID");
				teacherID = input.nextInt();
				try {
					aSchool.removeTeacher(teacherID);
				} catch (NotFoundException e3) {
					System.out.println("Error. Teacher not found.");
				}
				break;
			case 10:
				System.out
						.println("Which student would you like to remove? Please enter the student ID.");
				studentID = input.nextInt();
				try {
					aSchool.removeStudent(studentID);
				} catch (NotFoundException e2) {
					System.out.println("Error. Student not found.");
				}
				break;
			case 11:
				System.out
						.println("Which course would you like to remove? Please enter the course ID.");
				courseID = input.next();
				try {
					aSchool.removeCourse(courseID);
				} catch (NotFoundException e2) {
					System.out.println("Error. Course not found.");
				}
				break;
			case 12:
				System.out.println("What is the teacher's ID?");
				teacherID = input.nextInt();
				System.out.println("What is the teacher's new last name?");
				lastName = input.next();
				try {
					aSchool.modifyTeacherLastName(teacherID, lastName);
				} catch (NotFoundException e4) {
					System.out.println("Error. Teacher not found.");
				}
				break;
			case 13:
				System.out.println("What is the teacher's ID?");
				teacherID = input.nextInt();
				input.nextLine();
				System.out
						.println("Please enter the street and number of the new address?");
				addressS = input.nextLine();
				System.out.println("Please enter the city.");
				city = input.nextLine();
				System.out.println("Please enter the state.");
				state = input.nextLine();
				System.out.println("Please ender the zip code.");
				zipCode = input.next();
				try {
					address = new Address(addressS, city, state, zipCode);
				} catch (NullPointerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidDataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {

					aSchool.modifyTeacherAddress(teacherID, address);
				} catch (NotFoundException e) {
					System.out.println("Teacher not found");
				} catch (NullPointerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidDataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 14:
				System.out.println("What is the teacher's ID?");
				teacherID = input.nextInt();
				input.nextLine();
				found = false;
				do {
					System.out
							.println("Please enter the teacher's degree. Enter the Acronym. For example, a Bachelor degree would be 'BA'.");
					degreeS = input.next();
					found = false;
					for (Degree d : Degree.values()) {
						if (degreeS.equalsIgnoreCase(d.toString())) {
							degree = Degree.valueOf(d.toString());
							found = true;
							break;
						}
					}
				} while (!found);
				found = false;
				input.nextLine();
				do {
					System.out
							.println("Please enter the teacher's new major. Enter the full name.");
					majorS = input.nextLine();
					for (Major m : Major.values()) {
						if (majorS.equalsIgnoreCase(m.toString())) {
							major = Major.valueOf(majorS.toUpperCase());
							found = true;
							break;
						}
					}
				} while (!found);
				try {
					aSchool.modifyTeacherDegree(teacherID, degree, major);
				} catch (NotFoundException e3) {
					System.out.println("Error. Teacher not found.");
				}
				break;
			case 15:
				System.out.println("What is the teacher's ID?");
				teacherID = input.nextInt();
				System.out
						.println("What percent raise would you like to give the teacher?");
				Double percent = input.nextDouble();
				try {

					aSchool.giveTeacherPercentRaise(teacherID, percent);
				} catch (NotFoundException e) {
					System.out.println("Teacher not found");
				} catch (NullPointerException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InvalidDataException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case 16:
				System.out.println("What is the teacher's ID?");
				teacherID = input.nextInt();
				System.out
						.println("What raise would you like give to the teacher's salary?");
				Double amount = input.nextDouble();
				try {

					aSchool.giveTeacherRaise(teacherID, amount);
				} catch (NotFoundException e) {
					System.out.println("Error. Teacher not found.");
				} catch (NullPointerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidDataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 17:
				System.out.println("enter ID");
				studentID = input.nextInt();
				System.out.println("Enter the new last name");
				lastName = input.next();
				try {
					aSchool.modifyStudentLastName(studentID, lastName);
				} catch (NotFoundException e2) {
					System.out.println("Error. Student not found.");
				}
				break;
			case 18:
				System.out.println("enter ID");
				studentID = input.nextInt();
				do {
					System.out
							.println("Enter the new 10 digit phone number with no spaces.");
					phoneNumber = input.next();
				} while (phoneNumber.length() != 10);
				try {

					aSchool.modifyStudentPhoneNumber(studentID, phoneNumber);
				} catch (NotFoundException e) {
					System.out.println("Error. Student not found.");
				} catch (NullPointerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidDataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 19:
				System.out.println("enter student ID");
				studentID = input.nextInt();
				System.out.println("enter the ID of the completed course");
				courseID = input.next();
				found = false;
				do {
					System.out
							.println("enter the grade the student earned on the course. please enter the full word, for example, (AMINUS)");
					gradeS = input.next();
					grade = null;
					for (Grade g : Grade.values()) {
						if (gradeS.equalsIgnoreCase(g.toString())) {
							grade = Grade.valueOf(gradeS.toUpperCase());
							found = true;
							break;
						}
					}
				} while (!found);
				try {

					aSchool.addCompletedCourse(studentID, courseID, grade);
				} catch (NotFoundException e) {
					System.out
							.println("Error. Cannot process the requested data.");
				} catch (DuplicateDataException e) {
					System.out.println("An instance of this already exists");
				} catch (NullPointerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidDataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 20:
				System.out.println("enter student ID");
				studentID = input.nextInt();
				try {

					System.out.println(aSchool.getStudentGPA(studentID));
				} catch (NotFoundException e) {
					System.out.println("Error. Student not found");

				}
				break;
			case 21:
				System.out.println("enter student ID");
				studentID = input.nextInt();
				System.out.println("Enter courseID");
				courseID = input.next();
				try {
					System.out.println(aSchool.getGradeofCourse(studentID,
							courseID));
				} catch (NullPointerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NotFoundException e) {
					System.out
							.println("Error. Cannot find the requested data.");
				} catch (InvalidDataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 22:
				System.out.println("enter student ID");
				studentID = input.nextInt();
				System.out.println("Enter departmentID");
				departmentID = input.next();
				try {
					System.out.println(aSchool.getCoursesbyDepartment(
							studentID, departmentID));
				} catch (NotFoundException e1) {
					System.out
							.println("Error. Cannot find the requested data.");
				}

				break;
			case 23:
				System.out.println("enter student ID");
				studentID = input.nextInt();
				found = false;
				do {
					System.out
							.println("For what grade would you like to get the courses?");
					gradeS = input.next();
					grade = null;
					for (Grade g : Grade.values()) {
						if (gradeS.equalsIgnoreCase(g.toString())) {
							grade = Grade.valueOf(gradeS.toUpperCase());
							found = true;
							break;
						}
					}
				} while (!found);

				try {
					System.out.println(aSchool.getCoursesbyGrade(studentID,
							grade));
				} catch (NotFoundException e1) {
					System.out
							.println("Error. Cannot find the requested data.");
				}

				break;
			case 24:
				System.out.println(aSchool.getTeachersSortedByName());
				break;
			case 25:
				System.out.println(aSchool.getTeachersSortedById());
				break;
			case 26:
				System.out.println(aSchool.getStudentsSortedById());
				break;
			case 27:
				System.out.println(aSchool.getStudentsSortedByName());
				break;
			case 28:
				System.out.println("What is the teacher's ID?");
				teacherID = input.nextInt();
				System.out.println("enter the course's ID?");
				courseID = input.next();
				System.out.println("What year was the course taught?");
				year = input.nextInt();
				do {
					System.out
							.println("What semester was the course taught?(Fall, Spring, Summer1, or Summer2");
					semesterS = input.next();
					found = false;
					for (Semester s : Semester.values()) {
						if (semesterS.equalsIgnoreCase(s.toString())) {

							semester = Semester
									.valueOf(semesterS.toUpperCase());
							found = true;
							break;
						}
					}
				} while (!found);

				Section section = null;
				do {
					System.out.println("What section was the course taught?");
					String sectionS = input.next();
					found = false;
					for (Section s : Section.values()) {
						if (sectionS.equalsIgnoreCase(s.toString())) {

							section = Section.valueOf(sectionS.toUpperCase());
							found = true;
							break;
						}
					}
				} while (!found);
				try {

					aSchool.addTaughtCourse(teacherID, courseID, year,
							semester, section);
				} catch (NotFoundException e) {
					System.out
							.println("Error. Cannot process the requested data.");
				} catch (DuplicateDataException e) {
					System.out.println("An instance of this already exists.");
				} catch (NullPointerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidDataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 29:
				System.out.println("What is the teacher's ID?");
				teacherID = input.nextInt();
				System.out.println("What year?");
				year = input.nextInt();
				do {
					System.out.println("What semester?");
					semesterS = input.next();
					found = false;
					for (Semester s : Semester.values()) {
						if (semesterS.equalsIgnoreCase(s.toString())) {

							semester = Semester
									.valueOf(semesterS.toUpperCase());
							found = true;
							break;
						}
					}
				} while (!found);
				try {
					System.out.println(aSchool.howManyCoursesPerSemester(
							teacherID, year, semester));
				} catch (NotFoundException e) {
					System.out
							.println("Error. Cannot find the requested data.");
				}
				break;
			case 30:
				System.out.println(aSchool.getPeople());
				break;
			case 31:
				System.out.println(aSchool.getCourses());
				break;
			case 32:
				System.out.println(aSchool.getDepartments());
				break;

			}
			choice = menu();
		} while (choice != 0);
		input.close();
	}

	public static int menu() {
		@SuppressWarnings("resource")
		Scanner menu = new Scanner(System.in);
		int choice;
		do {
			System.out
					.println("1. Get school name"
							+ "\n2. Get school address"
							+ "\n3. Get school phone number"
							+ "\n4. Modify school phone number"
							+ "\n5. Add a teacher"
							+ "\n6. Add a student"
							+ "\n7. Add a course"
							+ "\n8. Add a department"
							+ "\n9. Remove a Teacher"
							+ "\n10. Remove a student"
							+ "\n11. Remove a course"
							+ "\n12. Modify a teacher's last name"
							+ "\n13. Modify a teacher's address"
							+ "\n14. Modify a teeacher's degree"
							+ "\n15. Give a teacher a percent raise"
							+ "\n16. Give a teacher a bonus raise"
							+ "\n17. Modify a student's last name"
							+ "\n18. Modify a student's phone number"
							+ "\n19. Add a completed course"
							+ "\n20. Get a student's GPA"
							+ "\n21. Get the grade a student earned in a course"
							+ "\n22. Get the course a student completed in a specific department"
							+ "\n23. Get the courses that a student earned a specific grade"
							+ "\n24. Get a list of teachers, sorted by name"
							+ "\n25. Get a list of teachers, sorted by their ID"
							+ "\n26. Get a list of students, sorted by their ID"
							+ "\n27. Get a list of students, sorted by thier name"
							+ "\n28. Add a taught course"
							+ "\n29. Find how many courses a teacher taught in a specific semester"
							+ "\n30. Get all People" + "\n31. Get all courses"
							+ "\n32. Get all Departments" + "\n0. Exit");
			choice = menu.nextInt();
		} while (choice < 0 || choice > 32);
		return choice;
	}

}
