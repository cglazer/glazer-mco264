package queueArray;

public class QueueEmptyException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public QueueEmptyException(){
		super("queue empty");
	}

}
