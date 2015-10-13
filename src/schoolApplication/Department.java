package schoolApplication;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Department {
	private String departmentID;
	private String departmentName;
	private String location;
	private String phoneNumber;
	private String faxNumber;
	private Integer departmentChairpersonID;

	public Department(String departmentID, String departmentName)
			throws NullPointerException, InvalidDataException {
		this(departmentID, departmentName, null, null, null, null);
	}

	public Department(String departmentID, String departmentName,
			String location, String phoneNumber, String faxNumber,
			Integer departmentChairperson) throws NullPointerException,
			InvalidDataException {
		if (departmentName == null || departmentID == null) {
			throw new NullPointerException();
		}
		this.departmentID = departmentID;
		this.departmentName = departmentName;
		this.location = location;
		// validate phone number and fax number before storing their values
		if (isNumberValid(phoneNumber)) {
			this.phoneNumber = phoneNumber;
		} else {
			throw new InvalidDataException();
		}
		if (isNumberValid(faxNumber)) {
			this.faxNumber = faxNumber;
		} else {
			throw new InvalidDataException();
		}
		this.departmentChairpersonID = departmentChairperson;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) throws NullPointerException {
		if (location == null) {
			throw new NullPointerException();
		}
		this.location = location;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) throws NullPointerException,
			InvalidDataException {
		if (phoneNumber == null) {
			throw new NullPointerException();
		}
		if (!isNumberValid(phoneNumber)) {
			throw new InvalidDataException();
		}
		this.phoneNumber = phoneNumber;
	}

	public String getFaxNumber() {
		return this.faxNumber;
	}

	public void setFaxNumber(String faxNumber) throws NullPointerException,
			InvalidDataException {
		if (faxNumber == null) {
			throw new NullPointerException();
		}
		if (!(isNumberValid(faxNumber))) {
			throw new NullPointerException();
		}
		this.faxNumber = faxNumber;
	}

	public Integer getDepartmentChairpersonID() {
		return this.departmentChairpersonID;
	}

	public void setDepartmentChairpersonID(Integer departmentChairpersonID)
			throws NullPointerException {
		if (departmentChairpersonID == null) {
			throw new NullPointerException();
		}
		this.departmentChairpersonID = departmentChairpersonID;
	}

	public String getDepartmentID() {
		return this.departmentID;
	}

	public String getDepartmentName() {
		return this.departmentName;
	}

	/**
	 * this method validates phone numbers and fax numbers and returns true if
	 * they're valid
	 */
	private boolean isNumberValid(String number) throws NullPointerException {
		if (number == null) {
			throw new NullPointerException();
		}
		Pattern validateNumber = Pattern.compile("\\d{10}");
		Matcher matcher = validateNumber.matcher(number);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Department other = (Department) obj;
		if (departmentID != other.departmentID)
			return false;
		return true;
	}

	public int compareTo(Department other) {
		return this.departmentID.compareTo(other.departmentID);
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("\n\nDepartment: ");
		buffer.append("\nDepartment ID: ");
		buffer.append(this.departmentID);
		buffer.append("\nDepartment Name: ");
		buffer.append(this.departmentName);
		if (this.location != null) {
			buffer.append("\nLocation: ");
			buffer.append(this.location);
		}
		if (this.phoneNumber != null) {
			buffer.append("\nPhone Number: ");
			buffer.append(this.phoneNumber);
		}
		if (this.faxNumber != null) {
			buffer.append("\nFax Number: ");
			buffer.append(this.faxNumber);
		}
		if (this.departmentChairpersonID != null) {
			buffer.append("\nDepartment Chairperson ID: ");
			buffer.append(this.departmentChairpersonID);
		}
		return buffer.toString();
	}

}
