package usingComparators;

import java.util.ArrayList;
import java.util.Comparator;

public class Sorter<T extends Comparable<T>> {

	public ArrayList<T> sort(ArrayList<T> collection) {
		// insertion sort
		// at every round you insert the next value into the correct position,
		// you may have to bump the remaining values down to make room
		T value = null;
		int position = 0;
		for (int i = 1; i < collection.size(); i++) {
			position = i;
			value = collection.get(i);
			while (position > 0 && value.compareTo(collection.get(position - 1)) < 0) {
				collection.set(position, collection.get(position - 1));
				position--;
			}
			collection.set(position, value);
		}
		return collection;
	}

	public ArrayList<T> sortByComparator(ArrayList<T> collection, Comparator<T> comparator) {
		T value = null;
		int position = 0;
		for (int i = 1; i < collection.size(); i++) {
			position = i;
			value = collection.get(i);
		while (position > 0 && comparator.compare(value, collection.get(position - 1)) < 0) {
				collection.set(position, collection.get(position - 1));
				position--;
			}
			collection.set(position, value);
		}
		return collection;
	}
}