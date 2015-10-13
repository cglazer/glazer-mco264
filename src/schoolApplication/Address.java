package schoolApplication;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Address {

	private String street;
	private String city;
	private USState state;
	private String zipCode;

	public Address(String city, String state) throws NullPointerException,
			InvalidDataException {
		this(null, city, state, null);
	}

	public Address(String street, String city, String state, String zipCode)
			throws NullPointerException, InvalidDataException {
		if (city == null || state == null) {
			throw new NullPointerException();
		}
		if (zipCode != null) {
			if (isZipValid(zipCode)) {
				this.zipCode = zipCode;
			} else {
				throw new InvalidDataException();
			}
		}
		USState stateCode = getStateCode(state);
		if (stateCode == null) {
			throw new InvalidDataException();
		}
		this.state = stateCode;
		this.street = street;
		this.city = city;

	}

	/**
	 * This method validates zipcodes and returns true when it is valid
	 */
	private boolean isZipValid(String zipCode) {
		Pattern validateZip = Pattern.compile("\\d{5}");
		Matcher matcher = validateZip.matcher(zipCode);
		if (matcher.matches()) {
			return true;
		} else {
			validateZip = Pattern.compile("\\d{9}");
			matcher = validateZip.matcher(zipCode);
			if (matcher.matches()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * this method finds the enumerator value of a state
	 */
	private static USState getStateCode(String state) {
		// when given the full state name
		if (state.length() > 2) {
			for (USState theState : USState.values()) {
				if (theState.getName().equalsIgnoreCase(state.trim())) {
					return theState;
				}
			}
			// when given the abbreviation..it gets validated
		} else {
			for (USState theState : USState.values()) {
				if (theState.name().equalsIgnoreCase(state.toUpperCase())) {
					return theState;
				}
			}
		}
		return null; // couldn't find the state
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) throws NullPointerException {
		if (street == null) {
			throw new NullPointerException();
		}
		this.street = street;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) throws NullPointerException {
		if (city == null) {
			throw new NullPointerException();
		}
		this.city = city;
	}

	public USState getState() {
		return this.state;
	}

	public void setState(String state) throws NullPointerException,
			InvalidDataException {
		if (state == null) {
			throw new NullPointerException();
		}
		USState stateCode = getStateCode(state);
		if (stateCode == null) {
			throw new InvalidDataException();
		}
		this.state = stateCode;
	}

	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zipCode) throws NullPointerException,
			InvalidDataException {
		if (zipCode == null) {
			throw new NullPointerException();
		}
		if (isZipValid(zipCode)) {
			this.zipCode = zipCode;
		} else {
			throw new InvalidDataException();
		}
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("\nStreet: ");
		buffer.append(this.street);
		buffer.append("\nCity: ");
		buffer.append(this.city);
		buffer.append("\nState: ");
		buffer.append(this.state);
		buffer.append("\nZip Code: ");
		buffer.append(this.zipCode);
		return buffer.toString();
	}

}
