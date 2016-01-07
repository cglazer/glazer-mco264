package quickSort;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class QuickSort<T extends Comparable<T>> {

	public void sort(ArrayList<T> items, int first, int last) {
		int pos;
		if (first < last) {
			// list still has elements in it
			pos = split(items, first, last);
			// sort the left sublist
			sort(items, first, pos - 1);
			// sort the right sublist
			sort(items, pos + 1, last);

		}
	}

	public int split(ArrayList<T> items, int first, int last) {
		int pos;
		int left = first;
		int right = last;
		// choose an arbitrary pivot value
		T pivot = items.get(left);
		
		T temp;

		while (left < right) {
			// items remain in the list
			// search from right to find elements that are <= pivot and should
			// therefore be swapped over to the set on the left
			while (items.get(right).compareTo(pivot) > 0) {
				right--; // it is in correct place, continue search
			}
			// search from left for element that > pivot
			while (left < right && items.get(left).compareTo(pivot) <= 0)
				left++; // nothing to do, bec. values is in correct position so
						// far
			if (left < right) {
				// found two values that should be swapped, swap the references
				temp = items.get(left);
				
				items.set(left, items.get(right));
			items.set(right, temp);
			

			}
		}
		// now place the pivot in between the two lists
		pos = right;
		items.set(first, items.get(pos));
		items.set(first, items.get(pos)); 
		items.set(pos, pivot);
		return pos;

	}

	public static void main(String[] args) {
	//	Integer[] num = new Integer[4];
		ArrayList<Integer> array= new ArrayList<Integer>();
	//	num[0] = 3;
	//	num[1] = 2;
	//	num[2] = 6;
		//num[3]= 0;
		array.add(3);
		array.add(2);
		array.add(8);
		array.add(0);
		QuickSort sort = new QuickSort();
	//	sort.sort(num, 0, 3);
		sort.sort(array, 0, array.size()-1);
		for (int i = 0; i < array.size(); i++) {
			System.out.println(array.get(i));
		}
	}

}
