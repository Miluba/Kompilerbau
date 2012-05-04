package classes;

import interfaces.ScanOutput;

public class ScanOutputImpl implements ScanOutput {
	
	private int token, error;
	private String lexem;
	
	
	public ScanOutputImpl(int token, String lexem, int error){
		this.token = token;
		this.lexem = lexem;
		this.error = error;
		
	}


	public int getToken() {
		return token;
	}


	public int getErrorCode() {
		return error;
	}


	public String getLexem() {
		return lexem;
	}

}
