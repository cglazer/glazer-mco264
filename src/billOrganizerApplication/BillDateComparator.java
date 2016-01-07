package billOrganizerApplication;

import java.util.Comparator;

public class BillDateComparator implements Comparator<Bill> {

	@Override
	public int compare(Bill o1, Bill o2) {
		// TODO Auto-generated method stub
		return o1.getDateDue().compareTo(o2.getDateDue());
	}

}
