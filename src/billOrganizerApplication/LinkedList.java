package billOrganizerApplication;

import java.io.Serializable;

public class LinkedList<T extends Comparable<T>> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Node<T> head;

	public LinkedList() {
		this.head = null;
	}

	public void add(T data) {
		Node<T> currentNode;
		Node<T> prevNode;
		Node<T> newNode = new Node<T>(data);
		if (this.head == null) {
			this.head = newNode;
			return;
		}
		// allocate a new Node
		// find the right spot for the Node inside the list
		currentNode = prevNode = this.head;

		while (currentNode != null && data.compareTo(currentNode.getData()) > 0) {
			prevNode = currentNode;
			currentNode = currentNode.getNext(); // move along to next Node
		}
		// found the right place , attach the links
		if (currentNode == this.head) {
			// the new node will become the new head and point to the
			// current head
			newNode.setNext(this.head);
			this.head = newNode;
		} else { // goes in between other node or at the end of the list
			prevNode.setNext(newNode);
			newNode.setNext(currentNode);
		}
	}

	public void remove(T data) throws NotFoundException {
		Node<T> currentNode = this.head;
		Node<T> prevNode = this.head;

		while (currentNode != null) {
			if (currentNode.getData().equals(data)) {
				// this is the Node that must be removed
				if (currentNode == this.head) {
					this.head = this.head.getNext(); // must reset the head
					return;
				} else {
					prevNode.setNext(currentNode.getNext());
					return;
				}
			} else {
				// move further along in the list
				prevNode = currentNode;
				currentNode = currentNode.getNext();
			}
		}
		throw new NotFoundException();
	}

	public T getHead() {
		return head.getData();
	}

	public Node<T> find(T data) throws NotFoundException {
		Node<T> currentNode = head;
		while (currentNode != null) {
			if (currentNode.getData().equals(data)) {
				return currentNode;
			}
			currentNode = currentNode.getNext();
		}
		throw new NotFoundException();
	}

	public LLIterator<T> externalIterator() {
		return new LLIterator<T>(head);
	}

}