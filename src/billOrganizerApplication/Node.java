package billOrganizerApplication;

import java.io.Serializable;

public class Node<T extends Comparable<T>> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private T data;
	private Node<T> nextNode;

	public Node(T data) {
		this.data = data;
		this.nextNode = null;
	}

	public Node(T data, Node<T> nextNode) {
		this.data = data;
		this.nextNode = nextNode;
	}

	public void setNext(Node<T> nextNode) {
		this.nextNode = nextNode;
	}

	public Node<T> getNext() {
		return this.nextNode;
	}

	public void setData(T data) {
		this.data = data;
	}

	public T getData() {
		return this.data;
	}

	public int compareTo(Node<T> otherNode) {
		return this.getData().compareTo(otherNode.getData());
	}
}
