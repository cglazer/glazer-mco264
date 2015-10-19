package medicines;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class PharmacyList implements Serializable {

	// this class hold arrayLists of indexes of the company codes and their
	// location in the RAM file and the company names and their location in the
	// file

	private static final long serialVersionUID = 1L;
	private ArrayList<CompanyCodeIndex> companyCodes;
	private ArrayList<CompanyNameIndex> companyNames;
	transient private RandomAccessFile companyData;

	// constructor
	public PharmacyList() {
		companyCodes = new ArrayList<CompanyCodeIndex>();
		companyNames = new ArrayList<CompanyNameIndex>();
	}

	public void connectToData(String fileName) throws FileNotFoundException {
		companyData = new RandomAccessFile(fileName, "rw");
	}

	public void closeFile() throws IOException {
		this.companyData.close();
	}

	/**
	 * This method adds a company's Data to RAM file to the next available
	 * location in memory and adds its indexes to the arrayLists
	 * 
	 * @throws DuplicateDataException
	 *             if the company was already written
	 */
	public void addCompany(PharmaceuticalCo p) throws IOException,
			DuplicateDataException {
		Long location = findCompanyCode1(p.getCompanyCode());
		// make sure this is a unique company code
		if (location == null) {
			location = this.companyData.length();
			// write it to the RAM file
			location = p.writeToFile(this.companyData, location);
			// add the indexes
			this.companyCodes.add(new CompanyCodeIndex(p.getCompanyCode(),
					location));
			// the arrayLists must always be kept in sorted order
			Collections.sort(this.companyCodes);
			this.companyNames.add(new CompanyNameIndex(p.getCompanyName(),
					location));
			Collections.sort(this.companyNames);
		} else {
			throw new DuplicateDataException();
		}
		// TODO Auto-generated method stub
	}

	/**
	 * this method creates a PharmaceuticalCo object and adds it to the RAM file
	 * at the next memory location and stores it's in index in the arrayLists.
	 * 
	 * @throws DuplicateDataException
	 *             - if the company was already written to the file
	 */
	public void addCompany(String companyCode, String phoneNumber,
			String companyName) throws DuplicateDataException,
			NullPointerException, InvalidDataException, IOException {
		Long location = findCompanyCode1(companyCode);
		if (location != null) {
			throw new DuplicateDataException();
		}
		// create a PharmaceuticalCo object
		PharmaceuticalCo aPharmacy = new PharmaceuticalCo(companyCode,
				phoneNumber, companyName);
		location = this.companyData.length();
		// write to the RAM file
		location = aPharmacy.writeToFile(this.companyData, location);
		// add the indexes
		this.companyCodes.add(new CompanyCodeIndex(companyCode, location));
		// keep the arrayLists in sorted order
		Collections.sort(this.companyCodes);
		location = findCompanyName(companyName);
		// make sure it is a unique company Name
		if (location != null) {
			throw new DuplicateDataException();
		}
		location = this.companyData.length();
		location = aPharmacy.writeToFile(this.companyData, location);
		this.companyNames.add(new CompanyNameIndex(companyName, location));
		Collections.sort(this.companyNames);
	}

	/**
	 * This method returns information about one company given the company code
	 */
	public String getPharmacyInfo(String companyCode)
			throws NullPointerException, IOException, InvalidDataException {
		Long location = findCompanyCode1(companyCode);
		if (location == null) {
			return null;
		} else {
			PharmaceuticalCo pharmacy = new PharmaceuticalCo(companyData,
					location);
			return pharmacy.toString();
		}
	}

	/**
	 * This method returns information about one company give the company name
	 */
	public String getPharmacyCodeInfo(String companyName)
			throws NullPointerException, IOException, InvalidDataException {
		Long location = findCompanyName(companyName);
		if (location == null) {
			return null;
		} else {
			PharmaceuticalCo pharmacy = new PharmaceuticalCo(this.companyData,
					location);
			return pharmacy.toString();
		}
	}

	public void modifyCompanyPhone(String companyCode, String newNumber)
			throws NotFoundException, NullPointerException, IOException,
			InvalidDataException {
		Long location = findCompanyCode1(companyCode);
		if (location == null) {
			throw new NotFoundException();
		}
		PharmaceuticalCo aPharmacy = new PharmaceuticalCo(companyData, location);
		aPharmacy.setPhoneNumber(newNumber);
		aPharmacy.writeToFile(companyData, location);
	}

	/**
	 * this method uses a binary search to check if this company code is found
	 * in the RAM file by looking through the list of indexes and returns the
	 * location it is found. if is not found, it returns null
	 */
	private Long findCompanyCode1(String companyCode) {
		int start = 0;
		int last = this.companyCodes.size() - 1;
		int mid;
		while (start <= last) {
			mid = (start + last) / 2;
			CompanyCodeIndex indexR = this.companyCodes.get(mid);
			String c = indexR.getCompanyCode();

			if (companyCode.equalsIgnoreCase(c)) {
				if (indexR.isActive()) {
					return this.companyCodes.get(mid).getLocation();
				} else {
					return null;
				}
			} else {
				if (companyCode.compareTo(c) < 0) {
					last = mid - 1;
				} else {
					if (companyCode.compareTo(c) > 0) {
						start = mid + 1;
					}
				}
			}
		}

		return null;
	}

	/**
	 * this method uses a binary search to check if this company name is found
	 * in the RAM file by looking through the list of indexes and returns the
	 * location it is found. if is not found, it returns null
	 */
	private Long findCompanyName(String companyName)
			throws InvalidDataException {
		int start = 0;
		int last = this.companyNames.size() - 1;
		int mid;
		while (start <= last) {
			mid = (start + last) / 2;
			CompanyNameIndex indexR = this.companyNames.get(mid);
			// the name in memory is trimmed so as not compare the company names
			// with extra spaces stored in memory because of the fixed-length
			// fields
			String c = indexR.getCompanyName().trim();

			if (companyName.equalsIgnoreCase(c)) {
				if (indexR.isActive()) {
					return this.companyNames.get(mid).getLocation();
				} else {
					return null;
				}
			} else {
				if (companyName.compareTo(c) < 0) {
					last = mid - 1;
				} else {
					if (companyName.compareTo(c) > 0) {
						start = mid + 1;
					}
				}
			}
		}
		return null;
	}

	/**
	 * This method removes a company's index records
	 */
	public void removeCompany(String companyCode) throws NullPointerException,
			IOException, InvalidDataException {
		Long location = findCompanyCode1(companyCode);
		PharmaceuticalCo pharmacy = new PharmaceuticalCo(this.companyData,
				location);
		for (int i = 0; i < companyCodes.size(); i++) {
			if (companyCode.equalsIgnoreCase(companyCodes.get(i)
					.getCompanyCode())) {
				companyCodes.remove(i);
			}
		}
		for (int i = 0; i < companyNames.size(); i++) {
			if (pharmacy.getCompanyName().equalsIgnoreCase(
					companyNames.get(i).getCompanyName())) {
				companyNames.remove(i);
			}
		}
	}

	/**
	 * This method creates and returns an ArrayList of all the companies by
	 * looping through the list of indexes
	 */
	public String getCompanies() throws NullPointerException, IOException,
			InvalidDataException {
		ArrayList<PharmaceuticalCo> allCompanies = new ArrayList<PharmaceuticalCo>();
		Long location;
		for (CompanyCodeIndex index : this.companyCodes) {
			location = findCompanyCode1(index.getCompanyCode());
			PharmaceuticalCo aCompany = new PharmaceuticalCo(this.companyData,
					location);
			allCompanies.add(aCompany);
		}
		return allCompanies.toString();
	}

}
