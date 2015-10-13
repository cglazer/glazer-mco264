package schoolApplication;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person {
	private Integer ID;
	private String firstName;
	private String lastName;
	private Character midInitial;
	private Address address;
	private String phoneNumber;
	private Gender gender;

	public Person(Integer ID, String firstName, String lastName,
			Address address, Gender gender) throws NullPointerException,
			InvalidDataException {
		this(ID, firstName, lastName, null, address, null, gender);
	}

	public Person(Integer ID, String firstName, String lastName,
			Character midInitial, Address address, String phoneNumber,
			Gender gender) throws NullPointerException, InvalidDataException {
		if (ID == null || firstName == null || lastName == null
				|| address == null || gender == null) {
			throw new NullPointerException();
		}
		this.ID = ID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.midInitial = midInitial;
		this.address = address;
		if (phoneNumber != null) {
			if (isNumberValid(phoneNumber)) {
				this.phoneNumber = phoneNumber;
			} else {
				throw new InvalidDataException();
			}
		}
		this.gender = gender;
	}

	/**
	 * This method validates phone numbers are valid 10 digit numbers and
	 * returns true if they are valid
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

	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) throws NullPointerException {
		if (lastName == null) {
			throw new NullPointerException();
		}
		this.lastName = lastName;
	}

	public Character getMidInitial() {
		return this.midInitial;
	}

	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) throws NullPointerException,
			InvalidDataException {
		if (address == null) {
			throw new NullPointerException();

		}
		this.address = address;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) throws NullPointerException,
			InvalidDataException {
		if (phoneNumber == null) {
			throw new NullPointerException();
		}
		if (isNumberValid(phoneNumber)) {
			this.phoneNumber = phoneNumber;
		} else {
			throw new InvalidDataException();
		}
	}

	public Gender getGender() {
		return this.gender;
	}

	public Integer getID() {
		return this.ID;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.ID))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("\nID: ");
		buffer.append(this.ID);
		buffer.append("\nLast Name: ");
		buffer.append(this.lastName);
		buffer.append("\nFirst Name: ");
		buffer.append(this.firstName);
		if (this.midInitial != null) {
			buffer.append("\nMiddle Initial: ");
			buffer.append(this.midInitial);
		}
		buffer.append("\nAddress: ");
		buffer.append(this.address);
		if (this.phoneNumber != null) {
			buffer.append("\nPhone Number: ");
			buffer.append(this.phoneNumber);
		}
		buffer.append("\nGender: ");
		buffer.append(this.gender);
		return buffer.toString();
	}

}
