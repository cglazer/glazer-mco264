package doubleLinkedList;

import java.util.Iterator;

public class ReverseIterator<T extends Comparable<T>> implements Iterator<T> {
	private DoubleLinkNode<?> currentNode;
	private DoubleLinkNode<?> tail;//to reset itself

	public ReverseIterator(DoubleLinkNode<?> tail) {
		this.tail=tail;
		this.currentNode = tail;
		
	}

	@Override
	public boolean hasNext() {
		if(this.currentNode==null){
			return false;
		}
		return true;
	}

	@Override
	public T next() {
		// TODO Auto-generated method stub
		T data = (T) this.currentNode.getData();
		this.currentNode = this.currentNode.getPrev();
		return data;
	}

	public void reset() {
		this.currentNode = this.tail;
	}

}
