package randomAccessExceptions;

public class InvalidPermissionException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidPermissionException (){
		super("invalid supervisor override");
	}

}
