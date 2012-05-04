package exceptions;

public class CloseBracketException  extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CloseBracketException(String e){
		super(e);
	}
	
	public CloseBracketException(String e, Throwable t){
		super(e,t);
	}

}
