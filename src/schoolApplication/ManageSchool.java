package schoolApplication;

import java.io.FileNotFoundException;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class ManageSchool {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		System.out
				.println("Congradulations on opeing your school!\n What would you like the name of your school to be?");
		String schoolName = input.nextLine();
		System.out
				.println("What is the address of your school(street and number");
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String phoneNumber;
		do {
			System.out
					.println("What is your school's phone number? Please enter their 10 digit number with no spaces");
			phoneNumber = input.next();
		} while (phoneNumber.length() != 10);
		School aSchool = null;
		try {
			aSchool = new School(schoolName, address, phoneNumber);
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		Semester semester= null;
		boolean found= false;
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
				aSchool.setPhoneNumber(phoneNumber);
				break;
			case 5:
				System.out.println("What is the teacher's ID?");
				teacherID = input.nextInt();
				System.out.println("What is the teacher's first name?");
				firstName = input.next();
				System.out.println("What is the teacher's last name?");
				lastName = input.next();
				System.out.println("Please enter the street and number");
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
				System.out
						.println("Enter the hire date in the following format(mm/dd/yyyy)");
				String hireDateFull = input.next();
				String[] hireDateSplit = hireDateFull.split("/");
				GregorianCalendar hireDate = new GregorianCalendar(new Integer(
						hireDateSplit[0]), new Integer(hireDateSplit[1]),
						new Integer(hireDateSplit[2]));
				System.out
						.println("Enter the birth date in following format(mm/dd/yyyy)");
				dateOfBirthFull = input.next();
				dateOfBirthSplit = dateOfBirthFull.split("/");
				dateOfBirth = new GregorianCalendar(new Integer(
						dateOfBirthSplit[0]), new Integer(dateOfBirthSplit[1]),
						new Integer(dateOfBirthSplit[2]));
				String employeeTypeS;
				do {
					System.out
							.println("Enter the teacher's title(professor/instructor)");
					employeeTypeS = input.next();
				} while (!(employeeTypeS.equalsIgnoreCase("professor") || (employeeTypeS
						.equalsIgnoreCase("instructor"))));
				EmployeeType employeeType;
				if (employeeTypeS.equalsIgnoreCase("professor")) {
					employeeType = EmployeeType.PROFESSOR;
				} else {
					employeeType = EmployeeType.INSTRUCTOR;
				}
				System.out.println("Please enter the social security number.");
				socialSecurityNum = input.next();

				do {
					System.out
							.println("Please enter the teacher's degree. Enter the Acronym. For example, a Bachelor degree would be 'BA'.");
					degreeS = input.nextLine();
					found = false;
					for (Degree d : Degree.values()) {
						if (degreeS.equalsIgnoreCase(d.toString())) {

							degree = Degree.valueOf(degreeS.toUpperCase());
							found = true;
							break;
						}
					}
				} while (found = false);
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
				System.out.println("Please enter the teacher's salary.");
				Double salary = input.nextDouble();
				try {
					aSchool.addTeacher(teacherID, firstName, lastName, address,
							gender, hireDate, dateOfBirth, employeeType,
							socialSecurityNum, degree, major, salary);
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
				System.out.println("Enter street name and number.");
				addressS = input.nextLine();
				System.out.println("Enter city");
				city = input.nextLine();
				System.out.println("Enter state");
				state = input.nextLine();
				System.out.println("Enter zip code");
				zipCode = input.next();
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
				System.out.println("Please enter the social security number.");
				socialSecurityNum = input.next();
				System.out
						.println("Enter the hire date in the following format(mm/dd/yyyy)");
				String enrolledDateFull = input.next();
				String[] enrolledDateSplit = enrolledDateFull.split("/");
				GregorianCalendar enrolledDate = new GregorianCalendar(
						new Integer(enrolledDateSplit[0]), new Integer(
								enrolledDateSplit[1]), new Integer(
								enrolledDateSplit[2]));
				System.out
						.println("Enter the birth date in following format(mm/dd/yyyy)");
				dateOfBirthFull = input.next();
				dateOfBirthSplit = dateOfBirthFull.split("/");
				dateOfBirth = new GregorianCalendar(new Integer(
						dateOfBirthSplit[0]), new Integer(dateOfBirthSplit[1]),
						new Integer(dateOfBirthSplit[2]));
				try {
					aSchool.addStudent(studentID, firstName, lastName,
							midInitial, address, phoneNumber, gender, major,
							socialSecurityNum, enrolledDate, dateOfBirth);
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
				aSchool.removeTeacher(teacherID);
				break;
			case 10:
				System.out
						.println("Which student would you like to remove? Please enter the student ID.");
				studentID = input.nextInt();
				aSchool.removeStudent(studentID);
				break;
			case 11:
				System.out
						.println("Which course would you like to remove? Please enter the course ID.");
				courseID = input.next();
				aSchool.removecourse(courseID);
				break;
			case 12:
				System.out.println("What is the teacher's ID?");
				teacherID = input.nextInt();
				System.out.println("What is the teacher's new last name?");
				lastName = input.next();
				aSchool.modifyTeacherLastName(teacherID, lastName);
				break;
			case 13:
				System.out.println("What is the teacher's ID?");
				teacherID = input.nextInt();
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
				do {
					System.out
							.println("Please enter the teacher's new degree. Enter the Acronym. For example, a Bachelor degree would be 'BA'.");
					degreeS = input.nextLine();
					found = false;
					for (Degree d : Degree.values()) {
						if (degreeS.equalsIgnoreCase(d.toString())) {

							degree = Degree.valueOf(degreeS.toUpperCase());
							found = true;
							break;
						}
					}
				} while (found = false);
				do {
					System.out
							.println("Please enter the teacher's new major. Enter the full name");
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
				aSchool.modifyTeacherDegree(teacherID, degree, major);
				break;
			case 15:
				System.out.println("What is the teacher's ID?");
				teacherID = input.nextInt();
				System.out
						.println("What percent raise would you like to give the teacher?");
				Double percent = input.nextDouble();
				try {
					aSchool.giveTeacherPercentRaise(teacherID, percent);
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
				aSchool.modifyStudentLastName(studentID, lastName);
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
				System.out
						.println("enter the grade the student earned on the course. please enter the full word, for example, (AMINUS)");
				gradeS = input.next();
				grade = null;
				found = false;
				for (Grade g : Grade.values()) {
					if (gradeS.equalsIgnoreCase(g.toString())) {
						grade = Grade.valueOf(gradeS.toUpperCase());
						found = true;
						break;
					}
				}
				try {
					aSchool.addCompletedCourse(studentID, courseID, grade);
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
					aSchool.getStudentGPA(studentID);
				} catch (InvalidIDException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 21:
				System.out.println("enter student ID");
				studentID = input.nextInt();
				System.out.println("Enter courseID");
				courseID = input.next();
				try {
					aSchool.getGradeofCourse(studentID, courseID);
				} catch (InvalidIDException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
					aSchool.getCoursesbyDepartment(studentID, departmentID);
				} catch (InvalidIDException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 23:
				System.out.println("enter student ID");
				studentID = input.nextInt();
				System.out
						.println("For what grade would you like to get the courses?");
				gradeS = input.next();
				grade = null;
				found = false;
				for (Grade g : Grade.values()) {
					if (gradeS.equalsIgnoreCase(g.toString())) {
						grade = Grade.valueOf(gradeS.toUpperCase());
						found = true;
						break;
					}
				}
				try {
					aSchool.getCoursesbyGrade(studentID, grade);
				} catch (InvalidIDException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 24:
				System.out.println(aSchool.getTeachersSortedByName());
				
				break;
			case 25:
				System.out.println(aSchool.getTeachers());
				break;
			case 26:
				System.out.println(aSchool.getStudents());
				break;
			case 27:
				System.out.println(aSchool.getStudentsByName());
				break;
			case 28:
				System.out.println("What is the teacher's ID?");
				teacherID = input.nextInt();
				System.out.println("enter the course's ID?");
				courseID = input.next();
				System.out.println("What year was the course taught?");
				year = input.nextInt();
				

				do {
					System.out.println("What semester was the course taught?");
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
				} while (found = false);

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
				} while (found = false);
				try {
					aSchool.addTaughtCourse(teacherID, courseID, year,
							semester, section);
				} catch (NullPointerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidEntryException e) {
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
				System.out.println("What year was the course taught?");
				year = input.nextInt();
				do {
					System.out.println("What semester was the course taught?");
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
				} while (found = false);
				try {
					aSchool.howManyCoursesPerSemester(teacherID, year, semester);
				} catch (InvalidIDException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			}
			choice = menu();
		} while (choice != 0);
		input.close();
	}

	public static int menu() {
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
							+ "\n22. Get the course of a specific department"
							+ "\n23. Get the courses that a student earned a specific grade"
							+ "\n24. Get a list of teachers, sorted by name"
							+ "\n25. Get a list of teachers, sorted by their ID"
							+ "\n26. Get a list of students, sorted by their ID"
							+ "\n27. Get a list of students, sorted by thier name"
							+ "\n28. Add a taught course"
							+ "\n29. Find how many courses a teacher taught in a specific semester"
							+ "\n0. Exit");
			choice = menu.nextInt();
		} while (choice < 0 || choice > 28);
		return choice;
	}

}
