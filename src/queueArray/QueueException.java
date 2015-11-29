package queueArray;

public class QueueException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public QueueException(String message) {
		super(message);
	}

	public QueueException() {
		super("Queue problem");
	}
}
