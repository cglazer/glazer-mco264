package billOrganizerApplication;

import java.io.Serializable;
import java.util.Comparator;

public class PriorityQueue<T extends Comparable<T>> implements Serializable {

	/**
	 * 
	 */
	private SortedLinkedList<T> list;
	private static final long serialVersionUID = 1L;
	private Comparator<T> comparator;

	public PriorityQueue(Comparator<T> comparator) {
		this.comparator = comparator;
		this.list = new SortedLinkedList<T>();
	}

	public void enqueue(T data) throws DuplicateDataException {
		list.insert(data, this.comparator);
	}

	public T dequeue() throws NotFoundException {
		T data = list.getHead();
		list.remove(data);
		return data;
	}

	public T peek() {
		return list.getHead();
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void remove(T data) throws NotFoundException {
		list.remove(data);
	}

	public LLIterator<T> EIterator() {
		return list.externalIterator();
	}

	public boolean contains(T data) {
		return this.list.contains(data);
	}

}
