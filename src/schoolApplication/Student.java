package schoolApplication;

import java.util.ArrayList;
import java.util.Comparator;
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

	public GregorianCalendar getEnrolledDate() {
		return this.enrolledDate;
	}

	public Double getGPA() {
		return this.GPA;
	}

	public void setGPA(Double gPA) throws NullPointerException,
			InvalidDataException {
		if (gPA == null) {
			throw new NullPointerException();
		}
		if (gPA < 0 || gPA > 4) {
			throw new InvalidDataException();
		}
		this.GPA = gPA;
	}

	public Integer getCreditsEarned() {
		return this.creditsEarned;
	}

	public void setCreditsEarned(Integer creditsEarned)
			throws NullPointerException, InvalidDataException {
		if (creditsEarned == null) {
			throw new NullPointerException();
		}
		if (creditsEarned < 1 || creditsEarned > 6) {
			throw new InvalidDataException();
		}
		this.creditsEarned = creditsEarned;
	}

	public GregorianCalendar getDateOfBirth() {
		return this.dateOfBirth;
	}

	public String getSocialSecurityNumber() {
		return this.socialSecurityNumber;
	}

	public ArrayList<CompletedCourse> getCompletedCourses() {
		return this.completedCourses;
	}

	public void addCompletedCourse(Course c, Grade g)
			throws NullPointerException, InvalidDataException {
		CompletedCourse completed = new CompletedCourse(c.getCourseID(),
				c.getDescription(), c.getNumCredits(), c.getDepartmentID(),
				super.getID(), g);
		this.completedCourses.add(completed);
		this.creditsEarned += completed.getNumCredits();
	}

	public String findCompletedCourse(String courseID) throws NotFoundException {
		for (CompletedCourse c : completedCourses) {
			if (c.getCourseID().equals(courseID)) {
				return c.toString() + "\nCourseID: " + c.getCourseID();
			}
		}

		throw new NotFoundException();

	}

	public Grade getGradeofCourse(String courseID) throws NullPointerException,
			InvalidIDException {
		if (courseID == null) {
			throw new NullPointerException();
		}
		for (CompletedCourse c : completedCourses) {
			if (c.getCourseID().equals(courseID)) {
				return c.getGrade();

			}
		}
		throw new InvalidIDException();
	}

	public ArrayList<String> getCoursesbyDepartment(String departmentID)
			throws NullPointerException {
		if (departmentID == null) {
			throw new NullPointerException();
		}
		ArrayList<String> completedCoursesByDepartment = new ArrayList<String>();
		for (CompletedCourse c : completedCourses) {
			if (c.getDepartmentID().equals(departmentID)) {
				completedCoursesByDepartment.add(c.getDescription());
			}
		}
		return completedCoursesByDepartment;
	}

	public ArrayList<String> getCoursesbyGrade(Grade g)
			throws NullPointerException {
		if (g == null) {
			throw new NullPointerException();
		}
		ArrayList<String> getCoursesByGrade = new ArrayList<String>();
		for (CompletedCourse c : completedCourses) {
			if (c.getGrade().equals(g)) {
				getCoursesByGrade.add(c.getDescription());
			}
		}
		return getCoursesByGrade;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Student: \n");
		buffer.append("Major: ");
		buffer.append(this.major);
		buffer.append("Date of Birth: ");
		buffer.append(this.dateOfBirth);
		buffer.append("GPA: ");
		buffer.append(this.GPA);
		buffer.append("Credits Earned: ");
		buffer.append(this.creditsEarned);
		buffer.append("Social Security Number: ");
		buffer.append(this.socialSecurityNumber);
		buffer.append("Completed Courses: ");
		buffer.append(this.completedCourses);
		return buffer.toString();
	}

	/**
	 * Comparator to sort by ID
	 */
	public static Comparator<Student> IDComparator = new Comparator<Student>() {

		@Override
		public int compare(Student e1, Student e2) {
			return (int) (e1.getID() - e2.getID());
		}
	};

	/**
	 * Comparator to sort by name
	 */
	public static Comparator<Student> nameComparator = new Comparator<Student>() {

		@Override
		public int compare(Student e1, Student e2) {
			if (e1.getLastName().equals(e2.getLastName())) {
				return e1.getFirstName().compareTo(e2.getFirstName());
			}
			return e1.getLastName().compareTo(e2.getLastName());
		}
	};

}
