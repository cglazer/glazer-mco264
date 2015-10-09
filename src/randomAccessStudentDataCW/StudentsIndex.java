package randomAccessStudentDataCW;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class StudentsIndex implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<StudentIndexRec> index;

	/**
	 * set up an index for the first time
	 * 
	 */
	public StudentsIndex() {

		index = new ArrayList<StudentIndexRec>();
	}

	/**
	 * 
	 * @param studentID
	 * @param fileLocation
	 * @throws Exception
	 *             if duplicate
	 */
	public void addStudentToIndex(Integer studentID, Long fileLocation) throws DuplicateDataException {
		StudentIndexRec indexRec = new StudentIndexRec(studentID, fileLocation);
		if (this.index.contains(indexRec)) {
			throw new DuplicateDataException();
		}
		this.index.add(indexRec);
		sortIndex();
	}

	/**
	 * 
	 * @param studentID
	 * @return Long - location of record in the data file
	 * @throws NotFoundException
	 */

	public Long findStudentLocation(Integer studentID) throws NotFoundException {
		int elementNum = findStudent(studentID);
		if (elementNum >= 0) {
			return index.get(elementNum).getFileLocation();
		} else {
			throw new NotFoundException();
		}
	}

	/**
	 * 
	 * @param studentID
	 * @return element number of the studentindexrec in the array
	 * @throws NotFoundException
	 */
	private int findStudent(Integer studentID) throws NotFoundException {
		// dummy record
		StudentIndexRec dummyRec = new StudentIndexRec(studentID, 0L);
		int elementNum = Collections.binarySearch(index, dummyRec);
		if (elementNum >= 0) {
			return elementNum;
		} else {
			throw new NotFoundException();
		}
	}

	/**
	 * 
	 * @param studentID
	 * @return true if studentid appears in the index array
	 * @throws NotFoundException
	 */

	public boolean hasStudent(Integer studentID) throws NotFoundException {
		int elemNum = findStudent(studentID);
		if (elemNum >= 0) {
			return true;
		}
		return false;
	}

	public void removeStudent(Integer studentID) throws NotFoundException {
		Integer elemNum = findStudent(studentID);
		// or you can set isActive to false
		if (elemNum >= 0) {
			index.remove(elemNum);
		}
	}

	private void sortIndex() {
		Collections.sort(this.index);
	}
	/**
	 * private int findStudentBinSearch(Integer studentID) {
	 * 
	 * }
	 */

}
