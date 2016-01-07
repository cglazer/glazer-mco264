package billOrganizerApplication;

import java.util.Comparator;

public class BillAmountComparator implements Comparator<Bill> {

	@Override
	public int compare(Bill bill1, Bill bill2) {
		return bill1.getAmountDue().compareTo(bill2.getAmountDue());
	}

}
