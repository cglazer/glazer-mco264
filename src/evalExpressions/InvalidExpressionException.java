package evalExpressions;

public class InvalidExpressionException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidExpressionException () {
		super("invalid expression");
	}

}
