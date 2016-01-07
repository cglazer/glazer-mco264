package billOrganizerApplication;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Scanner;
import javax.swing.JLabel;

public class Bill implements Comparable<Bill>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String vendorName;
	private Double amountDue;
	private GregorianCalendar dateDue;
	private BillTypeEnum billType;
	private Integer billID;
	private static int lastID = 0;

	public Bill(String vendorName, double amountDue, GregorianCalendar dateDue,
			BillTypeEnum billType) {
		this.vendorName = vendorName;
		this.amountDue = amountDue;
		this.dateDue = dateDue;
		this.billType = billType;
		this.billID = lastID++;
	}

	public Bill(Scanner fileName) throws InvalidBillTypeException {
		// this.billID = fileName.nextInt();
		// Bill.lastID = billID + 1;
		this.billID = lastID++;
		this.vendorName = fileName.next();
		this.amountDue = fileName.nextDouble();
		String dueDate = fileName.next();
		String[] date = dueDate.split("/");
		int month = Integer.parseInt(date[0]) - 1;
		int day = Integer.parseInt(date[1]);
		int year = Integer.parseInt(date[2]);
		this.dateDue = new GregorianCalendar(year, month, day);
		String inputType = fileName.next();
		boolean found = false;
		for (BillTypeEnum type : BillTypeEnum.values()) {
			if (type.toString().equalsIgnoreCase(inputType)) {
				this.billType = type;
				found = true;
			}
		}
		if (!found) {
			throw new InvalidBillTypeException();
		}

	}

	/**
	 * This method is for the serialized files. This method will only work for
	 * serialized files in order of id. so all files saved in this program are
	 * good
	 */
	public Bill(Integer billID, String vendorName, Double amountDue,
			BillTypeEnum billType, GregorianCalendar dateDue) {
		this.billID = billID;
		Bill.lastID = billID + 1;
		this.vendorName = vendorName;
		this.amountDue = amountDue;
		this.dateDue = dateDue;
		this.billType = billType;
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compareTo(Bill other) {
		return this.billID.compareTo(other.billID);
	}

	public String getVendorName() {
		return vendorName;
	}

	public Double getAmountDue() {
		return amountDue;
	}

	public Integer getBillID() {
		return billID;
	}

	public GregorianCalendar getDateDue() {
		return dateDue;
	}

	public BillTypeEnum getBillType() {
		return billType;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Bill ID: ");
		buffer.append(this.billID);
		buffer.append("\nVendor Name: ");
		buffer.append(this.vendorName);
		buffer.append("\nAmount Due: ");
		buffer.append(this.amountDue);
		buffer.append("\nDate Due: ");
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		buffer.append(formatter.format(this.dateDue.getTime()));
		GregorianCalendar now = new GregorianCalendar();
		if (this.dateDue.compareTo(now) < 0) {
			buffer.append("\nStatus: OVERDUE");
		}
		buffer.append("\nBill Type: ");
		buffer.append(this.billType.toString());
		return buffer.toString();
	}

	public JLabel[] toBillLabel() {
		JLabel[] label = new JLabel[5];
		label[0] = new JLabel("Bill ID: " + this.billID);
		label[1] = new JLabel("Vendor Name: " + this.vendorName);
		DecimalFormat formatter = new DecimalFormat("###,###,###.00");
		label[2] = new JLabel("Amount Due: $"
				+ formatter.format(this.amountDue));
		SimpleDateFormat formatter2 = new SimpleDateFormat("MM/dd/yyyy");
		label[3] = new JLabel("Date Due: "
				+ formatter2.format(this.dateDue.getTime()));
		label[4] = new JLabel("Bill Type: " + this.billType);

		return label;

	}

}
