package medicines;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class ManagePharmacyList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String companyCode;
		String phoneNumber;
		String companyName;
		Scanner inputText = new Scanner(System.in);
		PharmacyList aPharmacy = new PharmacyList();
		char response;
		do {
			System.out
					.println("Was the Pharmacy Application set up yet? Enter Y or N.");
			response = inputText.next().toUpperCase().charAt(0);
		} while (!(response == 'Y' || response == 'N'));
		if (response == 'N') {
			// connect to the file, read it and store the information in RAM
			try {
				aPharmacy.connectToData("pharmacyCompanies.dat");
				Scanner inputFile = new Scanner(new File(
						"pharmacyCompanies.txt"));
				while (inputFile.hasNext()) {
					try {
						PharmaceuticalCo p = new PharmaceuticalCo(inputFile);
						try {
							aPharmacy.addCompany(p);
						} catch (DuplicateDataException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();

					} catch (NullPointerException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InvalidDataException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				inputFile.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			// read in the previous serialized file as an object
			ObjectInputStream input;
			try {
				input = new ObjectInputStream(new FileInputStream(
						"pharmacyList.ser"));
				aPharmacy = (PharmacyList) input.readObject();
				aPharmacy.connectToData("pharmacyCompanies.dat");
				input.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.exit(1);
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				System.exit(1);
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		int choice = menu();
		while (choice > 0 && choice < 6) {
			switch (choice) {
			case 1:
				System.out.println("Enter the company code:");
				companyCode = inputText.next().toUpperCase();
				inputText.nextLine();
				System.out.println("Enter the company name:");
				companyName = inputText.nextLine();
				do {
					System.out
							.println("Enter the 10-digit company phone number");
					phoneNumber = inputText.next();
				} while (phoneNumber.length() != 10);
				try {
					aPharmacy.addCompany(companyCode, phoneNumber, companyName);
				} catch (NullPointerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DuplicateDataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidDataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 2:
				System.out.println("Enter the company code:");
				companyCode = inputText.next().toUpperCase();
				try {

					aPharmacy.removeCompany(companyCode);
				} catch (InvalidDataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				} catch (NullPointerException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case 3:
				System.out.println("Enter the company code:");
				companyCode = inputText.next().toUpperCase();
				do {
					System.out.println("Enter the new 10-digit phone number:");
					phoneNumber = inputText.next();
				} while (phoneNumber.length() != 10);
				try {
					aPharmacy.modifyCompanyPhone(companyCode, phoneNumber);
				} catch (NullPointerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidDataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 4:
				int enter;
				do {
					System.out
							.println("Enter 1 if you know the company code and enter 2 if you know the company name");
					enter = inputText.nextInt();
				} while (enter < 1 || enter > 2);
				inputText.nextLine();
				if (enter == 1) {
					System.out.println("Enter the company code:");
					companyCode = inputText.next().toUpperCase();
					try {

						System.out.println(aPharmacy
								.getPharmacyInfo(companyCode));
					} catch (InvalidDataException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();

					} catch (NullPointerException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					inputText.nextLine();
					System.out.println("Enter the company name: ");
					companyName = inputText.nextLine();
					try {

						System.out.println(aPharmacy
								.getPharmacyCodeInfo(companyName));
					} catch (InvalidDataException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();

					} catch (NullPointerException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			case 5:

				try {
					System.out.println(aPharmacy.getCompanies());
				} catch (NullPointerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidDataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;
			}
			choice = menu();
		}
		inputText.close();

		try {
			aPharmacy.closeFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// store the PharmacyList class which holds the arrayLists using object
		// serialization
		ObjectOutputStream outStream;
		try {
			outStream = new ObjectOutputStream(new FileOutputStream(
					"pharmacyList.ser"));
			outStream.writeObject(aPharmacy);
			outStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static int menu() {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		int choice;
		do {
			System.out.println("\n1. Add a pharmaceutical company"
					+ "\n2. Remove a company"
					+ "\n3. Modify company phone number"
					+ "\n4. Display company information"
					+ "\n5. List information about each company on file"
					+ "\n6. End the application");
			choice = input.nextInt();
		} while (choice < 1 || choice > 6);
		return choice;
	}

}

/**
 * The company name was put last because it was able to be more than one word so
 * the read in would have to use a nextLine instead of a next, because there
 * were no other present deliminators
 * 
 * providing all companies with the information about them in inefficient
 * because an array list must be created just for this purpose and before
 * that-the objects must all be created to store in the list all just to print
 * out the information
 */
