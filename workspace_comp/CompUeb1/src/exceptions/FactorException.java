package exceptions;

public class FactorException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FactorException(String e){
		super(e);
	}
	
	public FactorException(String e, Throwable t){
		super(e,t);
	}

}
