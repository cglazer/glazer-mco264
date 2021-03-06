package schoolApplication;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class CompletedCourse extends Course {

	private Integer studentID;
	private Grade grade;
	private GregorianCalendar completedDate;

	public CompletedCourse(String courseID, String description,
			Integer numCredits, String departmentID, Integer studentID,
			Grade grade) throws NullPointerException, InvalidDataException {
		super(courseID, description, numCredits, departmentID);
		// TODO Auto-generated constructor stub
		this.studentID = studentID;
		this.grade = grade;
		this.completedDate = new GregorianCalendar();

	}

	public Grade getGrade() {
		return this.grade;
	}

	public Integer getStudentID() {
		return this.studentID;
	}

	public String getCompletedDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		return formatter.format(this.completedDate);
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(super.toString());
		buffer.append("\nStudent ID: ");
		buffer.append(this.studentID);
		buffer.append("\nGrade: ");
		buffer.append(this.grade);
		buffer.append("\nCompleted Date: ");
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		buffer.append(formatter.format(this.completedDate.getTime()));
		return buffer.toString();
	}

}
