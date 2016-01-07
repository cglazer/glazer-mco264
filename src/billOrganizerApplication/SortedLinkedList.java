package billOrganizerApplication;

import java.io.Serializable;
import java.util.Comparator;

public class SortedLinkedList<T extends Comparable<T>> extends LinkedList<T>
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SortedLinkedList() {
		super();
	}

	public void remove(T data) throws NotFoundException {
		super.remove(data);
	}

	public void insert(T data) throws DuplicateDataException {
		if (contains(data)) {
			throw new DuplicateDataException();
		}
		super.add(data);
	}

	public void insert(T data, Comparator<T> comparator)
			throws DuplicateDataException {
		if (contains(data)) {
			throw new DuplicateDataException();
		}
		Node<T> newNode = new Node<T>(data);
		// Node<T> currentNode = super.head;
		if (head == null) {
			head = newNode;
			return;
		}
		Node<T> currentNode = head;
		Node<T> prevNode = head;
		while (currentNode != null
				&& comparator.compare(data, currentNode.getData()) > 0) {
			prevNode = currentNode;
			currentNode = currentNode.getNext();
		}
		if (currentNode == super.head) {
			newNode.setNext(super.head);
			this.head = newNode;
		} else {
			prevNode.setNext(newNode);
			newNode.setNext(currentNode);
		}
	}

	public T getHead() {
		return super.head.getData();
	}

	public boolean contains(T data) {
		Node<T> iter = head;
		while (iter != null) {
			if (data.equals(iter.getData())) {
				return true;
			}
			iter = iter.getNext();
		}
		return false;
	}

	public Node<T> find(T data) throws NotFoundException {
		return super.find(data);
	}
}
