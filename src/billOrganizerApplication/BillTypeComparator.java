package billOrganizerApplication;

import java.util.Comparator;

public class BillTypeComparator implements Comparator<Bill> {

	@Override
	public int compare(Bill o1, Bill o2) {
		// TODO Auto-generated method stub
		return o1.getBillType().compareTo(o2.getBillType());
	}

}
