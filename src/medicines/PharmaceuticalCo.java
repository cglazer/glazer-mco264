package medicines;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PharmaceuticalCo {
	
	private String companyCode;
	private String companyName;
	private String phoneNumber;

	/**
	 * Constructors- each for different type of input
	 */
	public PharmaceuticalCo(String companyCode, String phoneNumber, String name)
			throws InvalidDataException, NullPointerException {
		if (companyCode == null || name == null || phoneNumber == null) {
			throw new NullPointerException();
		}
		this.companyCode = companyCode;
		this.companyName = name;
		// phone number must always be validated
		if (isPhoneNumberValid(phoneNumber)) {
			this.phoneNumber = phoneNumber;
		} else {
			throw new InvalidDataException();
		}
	}

	public PharmaceuticalCo(Scanner filename) throws InvalidDataException,
			NullPointerException {
		if (filename == null) {
			throw new NullPointerException();
		}
		if (filename.hasNext()) {
			this.companyCode = filename.next();
		}
		if (filename.hasNext()) {
			String check = filename.next();
			// validate phone number
			if (isPhoneNumberValid(check)) {
				this.phoneNumber = check;
			} else {
				throw new InvalidDataException();
			}
		}
		if (filename.hasNext()) {
			this.companyName = filename.nextLine();
		}
	}

	public PharmaceuticalCo(RandomAccessFile raFile, Long location)
			throws IOException, NullPointerException, InvalidDataException {
		raFile.seek(location);
		// read in the data from the RAM file and store it in variables
		this.companyCode = raFile.readUTF();
		String check = raFile.readUTF();
		// validate phone number
		if (isPhoneNumberValid(check)) {
			this.phoneNumber = check;
		} else {
			throw new InvalidDataException();
		}
		this.companyName = raFile.readUTF();
	}

	/**
	 * this method validates that its input is a valid 10-digit number
	 * 
	 * @returns false if the input is an invalid number
	 */
	private boolean isPhoneNumberValid(String phoneNumber)
			throws NullPointerException {
		if (phoneNumber == null) {
			throw new NullPointerException();
		}
		Pattern validatePhone = Pattern.compile("\\d{10}");
		Matcher matcher = validatePhone.matcher(phoneNumber);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}

	// Getters and setters
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) throws InvalidDataException,
			NullPointerException {
		if (phoneNumber == null) {
			throw new NullPointerException();
		}
		if (isPhoneNumberValid(phoneNumber)) {
			this.phoneNumber = phoneNumber;
		} else {
			throw new InvalidDataException();
		}
	}

	public String getCompanyCode() {
		return this.companyCode;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	/**
	 * This method writes added companies to a RAM file in fixed length fields
	 * at the next available location
	 * 
	 * @return- the location it was written
	 */
	public Long writeToFile(RandomAccessFile raFile, Long location)
			throws IOException {
		raFile.seek(location);
		raFile.writeUTF(String.format("%-4s", this.companyCode));
		raFile.writeUTF(String.format("%-10s", this.phoneNumber));
		raFile.writeUTF(String.format("%-25s", this.companyName));
		return location;
	}

	public int compareTo(PharmaceuticalCo other) {
		return this.companyCode.compareTo(other.companyCode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PharmaceuticalCo other = (PharmaceuticalCo) obj;
		if (companyCode == null) {
			if (other.companyCode != null)
				return false;
		} else if (!companyCode.equals(other.companyCode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("\n\nPharmaceuticalCo: ");
		buffer.append("\nComapny Code: ");
		buffer.append(this.companyCode);
		buffer.append("\nCompany Name: ");
		buffer.append(this.companyName.trim());
		buffer.append("\nPhone Number: ");
		buffer.append(this.phoneNumber);
		return buffer.toString();
	}
}
