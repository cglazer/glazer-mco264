package schoolApplication;

public class TaughtCourse extends Course {
	private Integer teacherID;
	private Integer year;
	private Semester semesterID;
	private Section sectionID;

	public TaughtCourse(Course c, Integer year, Semester semesterID,
			Section sectionID, Integer teacherID) throws NullPointerException,
			InvalidDataException {
		this(c.getCourseID(), c.getDescription(), c.getNumCredits(), c
				.getDepartmentID(), year, semesterID, sectionID, teacherID);
	}

	public TaughtCourse(String courseID, String description,
			Integer numCredits, String departmentID, Integer year,
			Semester semesterID, Section sectionID, Integer teacherID)
			throws NullPointerException, InvalidDataException {
		super(courseID, description, numCredits, departmentID);
		// TODO Auto-generated constructor stub
		if (teacherID == null || year == null || semesterID == null
				|| sectionID == null) {
			throw new NullPointerException();
		}
		this.teacherID = teacherID;
		this.year = year;
		this.semesterID = semesterID;
		this.sectionID = sectionID;
	}

	public Integer getTeacherID() {
		return this.teacherID;
	}

	public Integer getYear() {
		return this.year;
	}

	public Semester getSemesterID() {
		return this.semesterID;
	}

	public Section getSectionID() {
		return this.sectionID;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("TaughtCourse: \n");
		buffer.append("Teacher ID: ");
		buffer.append(this.teacherID);
		buffer.append("Year: ");
		buffer.append(this.year);
		buffer.append("Semester ID: ");
		buffer.append(this.semesterID);
		buffer.append("Section ID: ");
		buffer.append(this.sectionID);
		return buffer.toString();
	}

}
