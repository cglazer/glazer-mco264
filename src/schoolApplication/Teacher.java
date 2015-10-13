package schoolApplication;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Teacher extends Employee {
	private String departmentID;
	private String socialSecurityNum;
	private Degree degree;
	private Double salary;
	private ArrayList<TaughtCourse> taughtCourses;

	public Teacher(Integer teacherID, String firstName, String lastName,
			Address address, Gender gender, GregorianCalendar hireDate,
			GregorianCalendar dateOfBirth, EmployeeType employeeType,
			String departmentID, String socialSecurityNum, Degree degree,
			Major majorID, Double salary) throws NullPointerException,
			InvalidDataException, InvalidEmployeeException {
		this(teacherID, firstName, lastName, null, address, null, gender,
				hireDate, dateOfBirth, employeeType, departmentID,
				socialSecurityNum, degree, majorID, salary);
	}

	public Teacher(Integer teacherID, String firstName, String lastName,
			Character midInitial, Address address, String phoneNumber,
			Gender gender, GregorianCalendar hireDate,
			GregorianCalendar dateOfBirth, EmployeeType employeeType,
			String departmentID, String socialSecurityNum, Degree degree,
			Major majorID, Double salary) throws NullPointerException,
			InvalidDataException, InvalidEmployeeException {
		super(teacherID, firstName, lastName, midInitial, address, phoneNumber,
				gender, hireDate, dateOfBirth, employeeType, majorID);
		// TODO Auto-generated constructor stub\\
		if (socialSecurityNum == null || degree == null || majorID == null
				|| salary == null) {
			throw new NullPointerException();
		}
		if (salary < 20000 || salary > 125000) {
			throw new InvalidDataException();
		}
		this.socialSecurityNum = socialSecurityNum;
		this.degree = degree;
		this.salary = salary;
		this.taughtCourses = new ArrayList<TaughtCourse>();
		this.departmentID = departmentID;
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

	public void setMajorID(Major majorID) throws NullPointerException {
		if (majorID == null) {
			throw new NullPointerException();
		}
		super.setEmployeeMajor(majorID);
	}

	public String getDepartmentID() {
		return this.departmentID;
	}

	public String getSocialSecurityNum() {
		return this.socialSecurityNum;
	}

	public Double getSalary() {
		return this.salary;
	}

	/**
	 * This method returns an arrayList of courses taught by the teacher
	 */
	public String getTaughtCourses() {
		return this.taughtCourses.toString();
	}

	/**
	 * This method sets a teacher's salary
	 */
	public void setSalary(Double amount) throws NullPointerException,
			InvalidDataException {
		if (amount == null) {
			throw new NullPointerException();
		} else if (amount < 15000 || amount > 150000) {
			throw new InvalidDataException();
		}
		this.salary = amount;
	}

	/**
	 * this method gives a teacher a fixed amount raise.
	 */
	public void applyBonusRaise(Double bonus) throws InvalidDataException {
		if (bonus == null) {
			throw new NullPointerException();
		}
		// a bonus cannot be more than a person's salary
		if (bonus > this.salary) {
			throw new InvalidDataException();
		}
		this.salary += bonus;
	}

	/**
	 * This method gives a teacher a percent raise in his salary
	 */
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

	/**
	 * This method creates an instance of a taught course and if it wasn't added
	 * to the list of taught courses, it is added. it also validates the the
	 * teacher is available at that time slot.
	 */
	public void taughtCourse(Course c, Integer year, Semester semester,
			Section sectionID) throws NullPointerException,
			InvalidDataException, DuplicateDataException {
		TaughtCourse taught = new TaughtCourse(c.getCourseID(),
				c.getDescription(), c.getNumCredits(), c.getDepartmentID(),
				year, semester, sectionID, super.getID());
		if (this.taughtCourses.contains(taught)) {
			throw new DuplicateDataException();
		}
		for (TaughtCourse t : taughtCourses) {
			if (t.getYear().equals(year)) {
				if (t.getSemesterID().equals(semester)) {
					if (t.getSectionID().equals(sectionID)) {
						throw new InvalidDataException();
					}
				}
			}
		}

		this.taughtCourses.add(taught);

	}

	/**
	 * This method returns the number of courses the teacher taught in a
	 * specific semester
	 */
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

	// return how many courses this Teacher taught in total
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
		buffer.append("\n\nTeacher: ");
		buffer.append(super.toString());
		buffer.append("\nDepartment ID: ");
		buffer.append(this.departmentID);
		buffer.append("\nSocial Security Number: ");
		buffer.append(this.socialSecurityNum);
		buffer.append("\nDegree: ");
		buffer.append(this.degree);
		buffer.append("\nSalary: $");
		DecimalFormat formatter = new DecimalFormat("###,###,###.00");
		buffer.append(formatter.format(this.salary));
		buffer.append("\nTaught Courses: ");
		buffer.append(this.taughtCourses);
		return buffer.toString();
	}

}
