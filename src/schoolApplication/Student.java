package schoolApplication;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Student extends Person {

	private Major major;
	private GregorianCalendar enrolledDate;
	private GregorianCalendar dateOfBirth;
	private Double GPA;
	private Integer creditsEarned;
	private String socialSecurityNumber;
	private ArrayList<CompletedCourse> completedCourses;

	public Student(Integer studentID, String firstName, String lastName,
			char midInitial, Address address, String phoneNumber,
			Gender gender, Major major) throws NullPointerException,
			InvalidDataException {
		this(studentID, firstName, lastName, midInitial, address, phoneNumber,
				gender, major, null, null, null);
		// TODO Auto-generated constructor stub
	}

	public Student(Integer studentID, String firstName, String lastName,
			Address address, Gender gender, String socialSecurityNumber,
			GregorianCalendar enrolledDate, GregorianCalendar dateOfBirth)
			throws NullPointerException, InvalidDataException {
		this(studentID, firstName, lastName, null, address, null, gender, null,
				socialSecurityNumber, enrolledDate, dateOfBirth);
		// TODO Auto-generated constructor stub
	}

	public Student(Integer studentID, String firstName, String lastName,
			Character midInitial, Address address, String phoneNumber,
			Gender gender, Major major, String socialSecurityNumber,
			GregorianCalendar enrolledDate, GregorianCalendar dateOfBirth)
			throws NullPointerException, InvalidDataException {
		super(studentID, firstName, lastName, midInitial, address, phoneNumber,
				gender);
		if (major == null) {
			this.major = Major.UDCD;
		} else {
			this.major = major;
		}
		this.GPA = null;
		this.creditsEarned = 0;
		this.socialSecurityNumber = socialSecurityNumber;
		this.enrolledDate = enrolledDate;
		this.dateOfBirth = dateOfBirth;
		this.completedCourses = new ArrayList<CompletedCourse>();
	}

	public Major getMajor() {
		return this.major;
	}

	public void setMajor(Major major) {
		if (major == null) {
			this.major = Major.UDCD;
		}
		this.major = major;
	}

	public String getEnrolledDate() {
		SimpleDateFormat formatter = new SimpleDateFormat();
		return formatter.format(this.enrolledDate.getTime());
	}

	public Double getGPA() {
		return this.GPA;
	}

	private void setGPA(Double gPA) throws NullPointerException,
			InvalidDataException {
		if (gPA == null) {
			throw new NullPointerException();
		}
		if (gPA < 0 || gPA > 4) {
			throw new InvalidDataException();
		}
		if (this.GPA == null) {
			this.GPA = gPA;
		} else {
			this.GPA = ((gPA + this.GPA) / 2);
		}
	}

	public Integer getCreditsEarned() {
		return this.creditsEarned;
	}

	public void setCreditsEarned(Integer creditsEarned)
			throws NullPointerException, InvalidDataException {
		if (creditsEarned == null) {
			throw new NullPointerException();
		}
		if (creditsEarned < 0 || creditsEarned > 4) {
			throw new InvalidDataException();
		}
		this.creditsEarned = creditsEarned;
	}

	public String getDateOfBirth() {
		SimpleDateFormat formatter = new SimpleDateFormat();
		return formatter.format(this.dateOfBirth.getTime());
	}

	public String getSocialSecurityNumber() {
		return this.socialSecurityNumber;
	}

	/**
	 * This method returns an arrayList of completed courses
	 * 
	 * @return
	 */
	public String getCompletedCourses() {
		return this.completedCourses.toString();
	}

	/**
	 * this method verifies that a completed course wasn't yet added and then
	 * adds it to the arrayList of completed courses
	 */
	public void addCompletedCourse(Course c, Grade g)
			throws NullPointerException, InvalidDataException,
			DuplicateDataException {
		CompletedCourse completed = new CompletedCourse(c.getCourseID(),
				c.getDescription(), c.getNumCredits(), c.getDepartmentID(),
				super.getID(), g);
		if (this.completedCourses.contains(completed)) {
			throw new DuplicateDataException();
		}
		this.completedCourses.add(completed);
		// add the credits earned
		this.creditsEarned += completed.getNumCredits();
		// adjusts the gpa
		setGPA(g.getGrade());
	}

	/**
	 * this method finds a course completed by a student or throws an exception
	 * if the course wasn't found
	 * */
	public String findCompletedCourse(String courseID) throws NotFoundException {
		for (CompletedCourse c : completedCourses) {
			if (c.getCourseID().equals(courseID)) {
				return c.toString() + "\nCourseID: " + c.getCourseID();
			}
		}
		throw new NotFoundException();
	}

	/**
	 * This method returns the grade a student received on a course
	 */
	public Grade getGradeofCourse(String courseID) throws NullPointerException,
			NotFoundException {
		if (courseID == null) {
			throw new NullPointerException();
		}
		for (CompletedCourse c : completedCourses) {
			if (c.getCourseID().equalsIgnoreCase(courseID)) {
				return c.getGrade();
			}
		}
		throw new NotFoundException();
	}

	/**
	 * This method returns an array of courses taken by a student in a specific
	 * department
	 */
	public ArrayList<String> getCoursesbyDepartment(String departmentID)
			throws NullPointerException {
		if (departmentID == null) {
			throw new NullPointerException();
		}
		ArrayList<String> completedCoursesByDepartment = new ArrayList<String>();
		for (CompletedCourse c : completedCourses) {
			if (c.getDepartmentID().equalsIgnoreCase(departmentID)) {
				completedCoursesByDepartment.add(c.getDescription());
			}
		}
		return completedCoursesByDepartment;
	}


	/**
	 * this method returns an arrayList of courses where the student received a
	 * specific grade
	 * 
	 * @throws NotFoundException
	 */
	public ArrayList<String> getCoursesbyGrade(Grade g) throws NullPointerException,
			NotFoundException {
		if (g == null) {
			throw new NullPointerException();
		}
		ArrayList<String> getCoursesByGrade = new ArrayList<String>();
		for (CompletedCourse c : completedCourses) {
			if (c.getGrade().equals(g)) {
				getCoursesByGrade.add(c.getDescription());
			}
		}
		if (getCoursesByGrade.size() > 1) {
			throw new NotFoundException();
		}
		return getCoursesByGrade;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		buffer.append("\n\nStudent: ");
		buffer.append(super.toString());
		buffer.append("\nMajor: ");
		buffer.append(this.major);
		if (this.dateOfBirth != null) {
			buffer.append("\nDate of Birth: ");
			buffer.append(formatter.format(this.dateOfBirth.getTime()));
		}
		if (this.enrolledDate != null) {
			buffer.append("\nEnrolled Date: ");
			buffer.append(formatter.format(this.enrolledDate.getTime()));
		}
		buffer.append("\nGPA: ");
		buffer.append(this.GPA);
		buffer.append("\nCredits Earned: ");
		buffer.append(this.creditsEarned);
		if (this.socialSecurityNumber != null) {
			buffer.append("\nSocial Security Number: ");
			buffer.append(this.socialSecurityNumber);
		}
		buffer.append("\nCompleted Courses: ");
		buffer.append(this.completedCourses);
		return buffer.toString();
	}

}