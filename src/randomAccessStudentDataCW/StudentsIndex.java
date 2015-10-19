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
	public void addStudentToIndex(Integer studentID, Long fileLocation)
			throws DuplicateDataException {
		StudentIndexRec indexRec = new StudentIndexRec(studentID, fileLocation);
		if (index.contains(indexRec)) {
			throw new DuplicateDataException();
		}
		index.add(indexRec);
		sortIndex();
	}

	/**
	 * 
	 * @param studentID
	 * @return Long - location of record in the data file
	 * @throws NotFoundException
	 */

	public Long findStudentLocation(Integer studentID) throws NotFoundException {
		Long location = findStudentBinSearch(studentID);
		if (location == null) {
			throw new NotFoundException();
		}
		return location;
	}

	/**
	 * 
	 * @param studentID
	 * @return element number of the studentindexrec in the array
	 * @throws NotFoundException
	 */
	private int findStudent(Integer studentID) throws NotFoundException {
		Long location = findStudentBinSearch(studentID);
		if (location == null) {
			throw new NotFoundException();
		}
		StudentIndexRec indexR = new StudentIndexRec(studentID, location);
		for (int i = 0; i < index.size(); i++) {
			if (indexR.equals(index.get(i))) {
				return i;
			}
		}
		throw new NotFoundException();
	}

	/**
	 * 
	 * @param studentID
	 * @return true if studentid appears in the index array
	 */

	public boolean hasStudent(Integer studentID) {
		Long location = findStudentBinSearch(studentID);
		if (location == null) {
			return false;
		}
		return true;
	}

	public void removeStudent(Integer studentID) throws NotFoundException {
		if (hasStudent(studentID)) {
			index.remove(findStudent(studentID));
		}
		throw new NotFoundException();
	}

	private void sortIndex() {
		Collections.sort(this.index);
	}

	private Long findStudentBinSearch(Integer studentID) {
		int start = 0;
		int last = this.index.size() - 1;
		int mid;
		while (start <= last) {
			mid = (start + last) / 2;
			StudentIndexRec indexR = this.index.get(mid);
			// the name in memory is trimmed so as not compare the company names
			// with extra spaces stored in memory because of the fixed-length
			// fields
			Integer c = indexR.getStudentID();

			if (studentID.equals(c)) {
				return this.index.get(mid).getFileLocation();
			} else {
				if (studentID.compareTo(c) < 0) {
					last = mid - 1;
				} else {
					if (studentID.compareTo(c) > 0) {
						start = mid + 1;
					}
				}
			}
		}
		return null;

	}
}
