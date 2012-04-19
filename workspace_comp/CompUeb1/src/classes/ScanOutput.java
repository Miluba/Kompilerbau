package classes;

public class ScanOutput {
	
	private int token, error;
	private String lexem;
	
	
	public ScanOutput(int token, String lexem, int error){
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
