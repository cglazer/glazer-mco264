package schoolApplication;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Employee extends Person {

	
	private GregorianCalendar hireDate;
	private GregorianCalendar dateOfBirth;
	private EmployeeType employeeType;

	public Employee(Integer ID, String firstName, String lastName, Address address,
			 Gender gender,
			GregorianCalendar hireDate, GregorianCalendar dateOfBirth,
			EmployeeType employeeType) throws NullPointerException,
			InvalidDataException {
		this(ID, firstName, lastName, null, address, null,
				gender, hireDate, dateOfBirth, employeeType);
	}

	public Employee(Integer ID, String firstName, String lastName, Character midInitial,
			Address address, 
			String phoneNumber, Gender gender, GregorianCalendar hireDate,
			GregorianCalendar dateOfBirth, EmployeeType employeeType)
			throws NullPointerException, InvalidDataException {
		super(ID, firstName, lastName, midInitial, address,
				phoneNumber, gender);
		// TODO Auto-generated constructor stub
		GregorianCalendar eighteen= new GregorianCalendar(dateOfBirth.get(Calendar.YEAR),dateOfBirth.get(Calendar.MONTH),dateOfBirth.get(Calendar.DAY_OF_MONTH));
		eighteen.add(Calendar.YEAR, 18);
		if (eighteen.getTime().compareTo(hireDate.getTime()) < 0) {
			throw new InvalidDataException();
		}
		this.hireDate = hireDate;
		this.dateOfBirth = dateOfBirth;
		boolean found = false;
		for (EmployeeType types : EmployeeType.values()) {
			if (types.toString().equals(employeeType)) {
				found = true;
				this.employeeType = types;
				break;
			}
		}
		if (!(found)) {
			throw new InvalidDataException();
		}
	}

	public EmployeeType getEmployeeType() {
		return this.employeeType;
	}

	public void setEmployeeTypeID(EmployeeType employeeTypeID)
			throws NullPointerException, InvalidDataException {
		if (employeeTypeID == null) {
			throw new NullPointerException();
		}
		boolean found = false;
		for (EmployeeType types : EmployeeType.values()) {
			if (types.toString().equals(employeeTypeID)) {
				found = true;
				this.employeeType = types;
				break;
			}
		}
		if (!(found)) {
			throw new InvalidDataException();
		}
	}

	public GregorianCalendar getHireDate() {
		return this.hireDate;
	}

	public GregorianCalendar getDateOfBirth() {
		return this.dateOfBirth;
	}

}
