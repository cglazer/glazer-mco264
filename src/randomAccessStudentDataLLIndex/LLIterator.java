package randomAccessStudentDataLLIndex;

import java.util.Iterator;

//we set it to T to represent data types, but we must extend the comparable 
public class LLIterator<T extends Comparable<T>> implements Iterator<T> {
	private Node<T> currentNode;
	private Node<T> head;// so that it can reset itself

	public LLIterator(Node<T> head) {
		this.head = head;
		this.currentNode = head;
	}

	@Override
	public boolean hasNext() {
		if (this.currentNode == null) {
			return false;
		}
		return true;
	}

	@Override
	public T next() {
		// TODO Auto-generated method stub
		T data = this.currentNode.getData();
		this.currentNode = this.currentNode.getNext();
		return data;
	}

	public void reset() {
		this.currentNode = this.head;
	}

}
