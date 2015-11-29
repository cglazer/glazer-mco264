package stacks;

import java.util.LinkedList;

public class StackLinkedList<T> {
	private LinkedList<T> values;

	public StackLinkedList() {
		values = new LinkedList<T>();
	}

	public void push(T data) {
		values.addFirst(data);
	}

	public void pop() {
		if (!isEmpty()) {
			values.removeFirst();
		}
	}

	public T peek() {
		return values.getFirst();
	}

	public boolean isEmpty() {
		return values.isEmpty();
	}
}
