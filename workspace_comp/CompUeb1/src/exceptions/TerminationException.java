package exceptions;

public class TerminationException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TerminationException(String e){
		super(e);
	}

	public TerminationException(String e, Throwable t){
		super(e,t);
	}
}
