package medicines;

import java.io.Serializable;

public class CompanyNameIndex implements Comparable<CompanyNameIndex>,
		Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String companyName;
	private Long location;
	private boolean isActive;

	// constructor
	public CompanyNameIndex(String companyName, Long location) {
		this.companyName = companyName;
		this.location = location;
		this.isActive = true;
	}

	// getters
	public String getCompanyName() {
		return this.companyName;
	}

	public Long getLocation() {
		return this.location;
	}

	public boolean isActive() {
		return this.isActive;
	}

	@Override
	public int compareTo(CompanyNameIndex other) {
		return this.companyName.compareTo(other.companyName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompanyNameIndex other = (CompanyNameIndex) obj;
		if (companyName == null) {
			if (other.companyName != null) {
				return false;
			}
		} else if (!companyName.equals(other.companyName)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Company Name Index: ");
		buffer.append("Company Name: ");
		buffer.append(this.companyName);
		buffer.append("Location: ");
		buffer.append(this.location);
		buffer.append("Is Active: ");
		buffer.append(this.isActive);
		return buffer.toString();
	}

}
