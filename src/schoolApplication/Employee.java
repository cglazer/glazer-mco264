package schoolApplication;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Employee extends Person {

	private GregorianCalendar hireDate;
	private GregorianCalendar dateOfBirth;
	private EmployeeType employeeType;
	private Major employeeMajor;

	public Employee(Integer ID, String firstName, String lastName,
			Address address, Gender gender, GregorianCalendar hireDate,
			GregorianCalendar dateOfBirth, EmployeeType employeeType)
			throws NullPointerException, InvalidDataException,
			InvalidEmployeeException {
		this(ID, firstName, lastName, null, address, null, gender, hireDate,
				dateOfBirth, employeeType, null);
	}

	public Employee(Integer ID, String firstName, String lastName,
			Character midInitial, Address address, String phoneNumber,
			Gender gender, GregorianCalendar hireDate,
			GregorianCalendar dateOfBirth, EmployeeType employeeType,
			Major major) throws NullPointerException, InvalidDataException,
			InvalidEmployeeException {
		super(ID, firstName, lastName, midInitial, address, phoneNumber, gender);
		// TODO Auto-generated constructor stub
		if (hireDate == null || dateOfBirth == null || employeeType == null) {
			throw new NullPointerException();
		}
		GregorianCalendar eighteen = new GregorianCalendar(
				dateOfBirth.get(Calendar.YEAR),
				dateOfBirth.get(Calendar.MONTH),
				dateOfBirth.get(Calendar.DAY_OF_MONTH));
		eighteen.add(Calendar.YEAR, 18);
		if (eighteen.getTime().compareTo(hireDate.getTime()) > 0) {
			throw new InvalidEmployeeException();
		}
		if (major != null) {
			this.employeeMajor = major;
		} else {
			this.employeeMajor = Major.UDCD;
		}
		this.hireDate = hireDate;
		this.dateOfBirth = dateOfBirth;
		this.employeeType = employeeType;
	}

	public EmployeeType getEmployeeType() {
		return this.employeeType;
	}

	public void setEmployeeTypeID(EmployeeType employeeTypeID)
			throws NullPointerException, InvalidDataException {
		if (employeeTypeID == null) {
			throw new NullPointerException();
		}
		this.employeeType = employeeTypeID;
	}

	public String getHireDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		return formatter.format(this.hireDate.getTime());
	}

	public String getDateOfBirth() {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		return formatter.format(this.dateOfBirth.getTime());
	}

	public Major getEmployeeMajor() {
		return this.employeeMajor;
	}

	public void setEmployeeMajor(Major employeeMajor) {
		if (employeeMajor == null) {
			throw new NullPointerException();
		}
		this.employeeMajor = employeeMajor;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		buffer.append(super.toString());
		buffer.append("\nHire Date: ");
		buffer.append(formatter.format(this.hireDate.getTime()));
		buffer.append("\nDate of Birth: ");
		buffer.append(formatter.format(this.dateOfBirth.getTime()));
		buffer.append("\nEmployee Type: ");
		buffer.append(this.employeeType);
		buffer.append("\nEmployee Major: ");
		buffer.append(this.employeeMajor);
		return buffer.toString();
	}

}
