package medicines;

import java.io.Serializable;

public class CompanyCodeIndex implements Comparable<CompanyCodeIndex>,
		Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String CompanyCode;
	private Long location;
	private boolean isActive;

	public CompanyCodeIndex(String companyCode, Long location)
			throws NullPointerException {
		if (companyCode == null) {
			throw new NullPointerException();
		}
		this.CompanyCode = companyCode;
		this.location = location;
		this.isActive = true;
	}

	public String getCompanyCode() {
		return this.CompanyCode;
	}

	public Long getLocation() {
		return this.location;
	}

	public boolean isActive() {
		return this.isActive;
	}

	@Override
	public int compareTo(CompanyCodeIndex other) {
		return this.CompanyCode.compareTo(other.CompanyCode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompanyCodeIndex other = (CompanyCodeIndex) obj;
		if (CompanyCode == null) {
			if (other.CompanyCode != null)
				return false;
		} else if (!CompanyCode.equals(other.CompanyCode)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Company Code Index: ");
		buffer.append("Company Code: ");
		buffer.append(this.CompanyCode);
		buffer.append("Location: ");
		buffer.append(this.location);
		buffer.append("Is Active: ");
		buffer.append(this.isActive);
		return buffer.toString();
	}

}
