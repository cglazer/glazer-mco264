package quickSort;

public class QuickSort<T extends Comparable<T>> {
	public void sort(T items[], int first, int last) {
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

	public int split(T items[], int first, int last) {
		int pos;
		int left = first;
		int right = last;
		// choose an arbitrary pivot value
		T pivot = items[left];
		T temp;

		while (left < right) {
			// items remain in the list
			// search from right to find elements that are <= pivot and should
			// therefore be swapped over to the set on the left
			while (items[right].compareTo(pivot) > 0) {
				right--; // it is in correct place, continue search
			}
			// search from left for element that > pivot
			while (left < right && items[left].compareTo(pivot) <= 0)
				left++; // nothing to do, bec. values is in correct position so
						// far
			if (left < right) {
				// found two values that should be swapped, swap the references
				temp = items[left];
				items[left] = items[right];
				items[right] = temp;

			}
		}
		// now place the pivot in between the two lists
		pos = right;
		items[first] = items[pos];
		items[pos] = pivot;
		return pos;

	}

}
