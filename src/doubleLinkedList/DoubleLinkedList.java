package doubleLinkedList;

import java.io.Serializable;

import randomAccessExceptions.NotFoundException;

public class DoubleLinkedList<T extends Comparable<T>> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DoubleLinkNode<?> head;
	private DoubleLinkNode<?> tail;

	public DoubleLinkedList() {
		this.head = null;
		this.tail = null;
	}

	public ReverseIterator<?> externalIterator() {
		ReverseIterator<T> iterator = new ReverseIterator<T>(tail);
		return iterator;
	}

	public void add(T data) {
		DoubleLinkNode<?> currentNode = null;
		DoubleLinkNode<?> prevNode = null;
		if (this.head == null) { // the first Node in the list
			this.head = new DoubleLinkNode((Serializable) data);
			currentNode = this.head;
			this.tail = this.head;
		} else {
			DoubleLinkNode newNode = new DoubleLinkNode((Serializable) data);
			currentNode = this.head;
			while (currentNode != null
					&& data.compareTo((T) currentNode.getData()) > 0) {
				prevNode = currentNode;
				currentNode = currentNode.getNext();
			}
			if (currentNode == head) {
				newNode.setNext(head);
				head.setPrev(newNode);
				head = newNode;
			} else { // goes in between other node or at the end of the list
				prevNode.setNext(newNode);
				newNode.setNext(currentNode);
				newNode.setPrev(prevNode);
			}
			if (currentNode != null) {
				tail = currentNode;
				while (currentNode.getNext() != null) {
					currentNode = currentNode.getNext();
					this.tail = currentNode;
				}
			} else {
				tail = newNode;
			}
		}
	}

	public void remove(T data) throws NotFoundException {
		DoubleLinkNode<?> currentNode = head;
		DoubleLinkNode prevNode = head;

		while (currentNode != null) {
			if (currentNode.getData().equals(data)) {
				// this is the Node that must be removed
				if (currentNode == head) {
					head = head.getNext(); // must reset the head
					return;
				} else {
					this.tail = prevNode;
					prevNode.setNext(currentNode.getNext());
					currentNode = currentNode.getNext();
					if (currentNode != null) {
						currentNode.setPrev(prevNode);
						this.tail = currentNode;

						while (currentNode.getNext() != null) {
							currentNode = currentNode.getNext();
							this.tail = currentNode;
						}
					}
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

	public T find(T data) throws NotFoundException {
		DoubleLinkNode<?> currentNode = head; // start to iterate through the
												// list
		while (currentNode != null) {
			if (currentNode.getData().equals(data)) {
				return (T) currentNode.getData();
			}
			currentNode = currentNode.getNext(); // move further down the list
		}
		throw new NotFoundException();
	}
}
