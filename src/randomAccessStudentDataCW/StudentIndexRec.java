package randomAccessStudentDataCW;

import java.io.Serializable;

public class StudentIndexRec implements Serializable, Comparable<StudentIndexRec> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer studentID;
	private Long fileLocation;

	public StudentIndexRec(Integer studentID, Long fileLocation) {
		this.studentID = studentID;
		this.fileLocation = fileLocation;
	}

	public Integer getStudentID() {
		return this.studentID;
	}

	public Long getFileLocation() {
		return this.fileLocation;
	}

	// base comparison on the value of the student ID
	public int compareTo(StudentIndexRec indexRec) {
		return this.getStudentID().compareTo(indexRec.getStudentID());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentIndexRec other = (StudentIndexRec) obj;
		if (studentID == null) {
			if (other.studentID != null)
				return false;
		} else if (!studentID.equals(other.studentID))
			return false;
		return true;
	}
	
}
