package queueArray;

public class LinkedListQueue<T> {
	private Node<?> head;
	private Node<T> tail;

	public LinkedListQueue() {
		head = tail = null;
	}

	public boolean isEmpty() {
		return (head == null);
	}

	public void enqueue(T data) {
		if (head == null && tail == null) {
			Node<T> newNode = new Node<T>(data);
			head = tail = newNode;
		} else {
			Node<T> newNode = new Node<T>(data);
			tail.setNextNode(newNode);
			tail = newNode;
		}
	}

	public T dequeue() {
		if (isEmpty()) {
			throw new QueueEmptyException();
		}
		@SuppressWarnings("unchecked")
		T queueFront = (T) head.getData();
		head = head.getNextNode();
		if (head == null) {
			tail = null;
		}
		return queueFront;
	}

	public void removeAll() {
		head = tail = null;
	}

	public static void main(String args[]) {
		LinkedListQueue<String> names = new LinkedListQueue<String>();
		names.enqueue("Etti");
		names.enqueue("Shifra");
		names.enqueue("Chayala");
		System.out.println(names.dequeue());
		System.out.println(names.dequeue());
		System.out.println(names.dequeue());
	}
}
