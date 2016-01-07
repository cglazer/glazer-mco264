package billOrganizerApplication;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JLabel;

public class BillOrganizer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PriorityQueue<Bill> dateQueue;
	private PriorityQueue<Bill> amountDueQueue;
	private PriorityQueue<Bill> billTypeQueue;
	private SortedLinkedList<Bill> llist;

	public BillOrganizer() {
		this.dateQueue = new PriorityQueue<Bill>(new BillDateComparator());
		this.amountDueQueue = new PriorityQueue<Bill>(
				new BillAmountComparator());
		this.billTypeQueue = new PriorityQueue<Bill>(new BillTypeComparator());
		this.llist = new SortedLinkedList<Bill>();
	}

	public BillOrganizer(Scanner file) throws DuplicateDataException {
		this();
		insertStatements(file);
	}

	public BillOrganizer(String filename) throws FileNotFoundException,
			IOException, ClassNotFoundException, DuplicateDataException {
		this();
		ObjectInputStream input = new ObjectInputStream(new FileInputStream(
				filename));
		@SuppressWarnings("unchecked")
		SortedLinkedList<Bill> newList = (SortedLinkedList<Bill>) input
				.readObject();
		input.close();
		LLIterator<Bill> iter = newList.externalIterator();
		Bill bill;
		while (iter.hasNext()) {
			bill = iter.next();
			// I had to create a new bill so that the static id in the bill
			// class can change
			Bill newBill = new Bill(bill.getBillID(), bill.getVendorName(),
					bill.getAmountDue(), bill.getBillType(), bill.getDateDue());
			insert(newBill);
		}
	}

	public void insertStatements(Scanner file) throws DuplicateDataException {
		Bill bill;
		while (file.hasNext()) {
			bill = new Bill(file);
			insert(bill);
		}
	}

	public void insert(Bill bill) throws DuplicateDataException {
		if (!llist.contains(bill)) {
			this.dateQueue.enqueue(bill);
			this.amountDueQueue.enqueue(bill);
			this.billTypeQueue.enqueue(bill);
			this.llist.insert(bill);
		} else {
			throw new DuplicateDataException();
		}
	}

	public Bill payNextBill(BillCriteriaEnum criteria) throws NotFoundException {
		Bill removed = null;
		if (BillCriteriaEnum.BILLDUEDATE.equals(criteria)) {
			removed = this.dateQueue.peek();
			// I would really call the removeBill(removed) method now but I'm
			// leaving the code here to show using dequeue
			dateQueue.dequeue();
			this.amountDueQueue.remove(removed);
			this.billTypeQueue.remove(removed);
		} else if (BillCriteriaEnum.BILLAMOUNT.equals(criteria)) {
			removed = this.amountDueQueue.peek();
			this.amountDueQueue.dequeue();
			this.dateQueue.remove(removed);
			this.billTypeQueue.remove(removed);
		} else {
			removed = this.billTypeQueue.peek();
			this.billTypeQueue.dequeue();
			this.amountDueQueue.remove(removed);
			this.dateQueue.remove(removed);
		}
		this.llist.remove(removed);
		return removed;

	}

	public Bill payBillID(int BillID) throws NotFoundException {
		Bill remove = find(BillID);
		removeBill(remove);
		return remove;
	}

	private void removeBill(Bill bill) throws NotFoundException {
		this.billTypeQueue.remove(bill);
		this.dateQueue.remove(bill);
		this.amountDueQueue.remove(bill);
		this.llist.remove(bill);
	}

	public double totalBills() {
		LLIterator<Bill> iter = this.llist.externalIterator();
		double totalDue = 0;
		while (iter.hasNext()) {
			Bill bill = iter.next();
			totalDue += bill.getAmountDue();
		}
		return totalDue;
	}

	public String toStrings() {
		StringBuffer buffer = new StringBuffer();
		int numBill = 0;
		LLIterator<Bill> iter = this.llist.externalIterator();
		while (iter.hasNext()) {
			Bill bill = iter.next();
			numBill++;
			buffer.append("\n\nBill " + numBill + "\n");
			buffer.append(bill.toString());
		}
		return buffer.toString();
	}

	public ArrayList<JLabel> toLabelList() {
		LLIterator<Bill> iter = this.llist.externalIterator();
		ArrayList<JLabel> allLabels = new ArrayList<JLabel>();
		while (iter.hasNext()) {
			Bill bill = iter.next();
			JLabel[] thisLabel = bill.toBillLabel();
			for (int i = 0; i < thisLabel.length; i++) {
				allLabels.add(thisLabel[i]);
			}
			allLabels.add(new JLabel(" "));
		}
		return allLabels;
	}

	public void closeOrganizer(String filename) throws IOException {
		ObjectOutputStream output = new ObjectOutputStream(
				new FileOutputStream(filename));
		output.writeObject(this.llist);
		output.close();
	}

	public LLIterator<Bill> iteratorByDate() {
		// should return LinkedListIterator initialized with the head of the
		// list
		LLIterator<Bill> iter = this.dateQueue.EIterator();
		return iter;
	}

	public LLIterator<Bill> iteratorByAmount() {
		LLIterator<Bill> iter = this.amountDueQueue.EIterator();
		return iter;
	}

	public LLIterator<Bill> iteratorByType() {
		LLIterator<Bill> iter = this.billTypeQueue.EIterator();
		return iter;
	}

	public Bill find(int ID) throws NotFoundException {
		LLIterator<Bill> iter = this.llist.externalIterator();
		Bill current;
		while (iter.hasNext()) {
			current = iter.next();
			if (current.getBillID() == ID) {
				return current;
			}
		}
		throw new NotFoundException();
	}
}
