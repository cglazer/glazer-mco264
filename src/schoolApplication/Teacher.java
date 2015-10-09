package schoolApplication;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.GregorianCalendar;

public class Teacher extends Employee {
	private Integer departmentID;
	private String socialSecurityNum;
	private Degree degree;
	private Major majorID;
	private Double salary;
	private ArrayList<TaughtCourse> taughtCourses;

	public Teacher(Integer teacherID, String firstName, String lastName,
			Address address, Gender gender, GregorianCalendar hireDate,
			GregorianCalendar dateOfBirth, EmployeeType employeeType,
			String socialSecurityNum, Degree degree, Major majorID,
			Double salary) throws NullPointerException, InvalidDataException {
		this(teacherID, firstName, lastName, null, address, null, gender,
				hireDate, dateOfBirth, employeeType, socialSecurityNum, degree,
				majorID, salary);
	}

	public Teacher(Integer teacherID, String firstName, String lastName,
			Character midInitial, Address address, String phoneNumber,
			Gender gender, GregorianCalendar hireDate,
			GregorianCalendar dateOfBirth, EmployeeType employeeType,
			String socialSecurityNum, Degree degree, Major majorID,
			Double salary) throws NullPointerException, InvalidDataException {
		super(teacherID, firstName, lastName, midInitial, address, phoneNumber,
				gender, hireDate, dateOfBirth, employeeType);
		// TODO Auto-generated constructor stub\\
		if (socialSecurityNum == null || degree == null || majorID == null
				|| salary == null) {
			throw new NullPointerException();
		}
		if (salary < 15000 || salary > 150000) {
			throw new InvalidDataException();
		}
		this.socialSecurityNum = socialSecurityNum;
		this.degree = degree;
		this.majorID = majorID;
		this.salary = salary;
		this.taughtCourses = new ArrayList<TaughtCourse>();
	}

	public Degree getDegree() {
		return this.degree;
	}

	public void setDegree(Degree degree) throws NullPointerException {
		if (degree == null) {
			throw new NullPointerException();
		}
		this.degree = degree;
	}

	public Major getMajorID() {
		return this.majorID;
	}

	public void setMajorID(Major majorID) throws NullPointerException {
		if (majorID == null) {
			throw new NullPointerException();
		}
		this.majorID = majorID;
	}

	public Integer getDepartmentID() {
		return this.departmentID;
	}

	public String getSocialSecurityNum() {
		return this.socialSecurityNum;
	}

	public Double getSalary() {
		return this.salary;
	}

	public ArrayList<TaughtCourse> getTaughtCourses() {
		return this.taughtCourses;
	}

	public void setSalary(Double amount) throws NullPointerException,
			InvalidDataException {
		if (amount == null) {
			throw new NullPointerException();
		} else if (amount < 15000 || amount > 150000) {
			throw new InvalidDataException();
		}
		this.salary += amount;
	}

	public void applyRaise(Double percent) throws NullPointerException,
			InvalidDataException {
		if (percent == null) {
			throw new NullPointerException();
		}
		if (percent < 0) {
			throw new InvalidDataException();
		}
		this.salary += this.salary * percent;
	}

	public void taughtCourse(Course c, Integer year, Semester semester,
			Section sectionID) throws InvalidEntryException,
			NullPointerException, InvalidDataException {
		TaughtCourse taught = new TaughtCourse(c.getCourseID(),
				c.getDescription(), c.getNumCredits(), c.getDepartmentID(),
				year, semester, sectionID, super.getID());
		if (this.taughtCourses.contains(taught)) {
			throw new InvalidEntryException();
		} else {
			this.taughtCourses.add(taught);
		}
	}

	public int howManyCoursesPerSemester(Integer year, Semester semesterID) {
		int numCoursesPerSemester = 0;
		for (TaughtCourse taught : this.taughtCourses) {
			if (taught.getSemesterID().equals(semesterID)
					&& taught.getYear().equals(year)) {
				numCoursesPerSemester++;
			}
		}
		return numCoursesPerSemester;
	}

	// return how many courses this Teacher taught during a given semester

	public int howManyDifferentCourses() {
		return this.taughtCourses.size();
	}

	public int CompareTo(Teacher other) {
		if (this.getLastName().equalsIgnoreCase(other.getLastName())) {
			return this.getFirstName().compareTo(other.getFirstName());
		} else {
			return this.getLastName().compareTo(other.getLastName());
		}
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Teacher: ");
		buffer.append("Department ID: ");
		buffer.append(this.departmentID);
		buffer.append("Social Security Number: ");
		buffer.append(this.socialSecurityNum);
		buffer.append("Degree: ");
		buffer.append(this.degree);
		buffer.append("Major ID: ");
		buffer.append(this.majorID);
		buffer.append("Salary: ");
		buffer.append(this.salary);
		buffer.append("Taught Courses: ");
		buffer.append(this.taughtCourses);
		return buffer.toString();
	}

	public static Comparator<Teacher> IDComparator = new Comparator<Teacher>() {

		@Override
		public int compare(Teacher e1, Teacher e2) {
			return (int) (e1.getID() - e2.getID());
		}
	};

	/**
	 * Comparator to sort employees list or array in order of Age
	 */
	public static Comparator<Teacher> nameComparator = new Comparator<Teacher>() {

		@Override
		public int compare(Teacher e1, Teacher e2) {
			if (e1.getLastName().equals(e2.getLastName())) {
				return e1.getFirstName().compareTo(e2.getFirstName());
			}
			return e1.getLastName().compareTo(e2.getLastName());
		}
	};
}
