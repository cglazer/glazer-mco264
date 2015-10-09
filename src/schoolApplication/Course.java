package schoolApplication;

public class Course {

	private String courseID;
	private String description;
	private Integer numCredits;
	private String departmentID;

	public Course(String courseID, String description, Integer numCredits,
			String departmentID) throws NullPointerException,
			InvalidDataException {
		if (courseID == null || description == null || numCredits == null
				|| departmentID == null) {
			throw new NullPointerException();
		}
		if (numCredits < 0 || numCredits > 4) {
			throw new InvalidDataException();
		}

	}

	public String getCourseID() {
		return courseID;
	}

	public String getDescription() {
		return description;
	}

	public Integer getNumCredits() {
		return numCredits;
	}

	public String getDepartmentID() {
		return departmentID;
	}

	public int CompareTo(Course other) {
		return this.courseID.compareTo(other.courseID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (courseID == null) {
			if (other.courseID != null)
				return false;
		} else if (!courseID.equals(other.courseID))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Course: \n");
		buffer.append("\nCourse ID: ");
		buffer.append(this.courseID);
		buffer.append("\nDescription: ");
		buffer.append(this.description);
		buffer.append("\nNum Credits: ");
		buffer.append(this.numCredits);
		buffer.append("\nDepartment ID: ");
		buffer.append(this.departmentID);
		return buffer.toString();
	}

}
