package classes;

import exceptions.CloseBracketException;
import exceptions.FactorException;
import exceptions.TerminationException;
import interfaces.ScanOutput;
import interfaces.Scanner;

public class Parser {

	private static Scanner scanner = null;
	private static ScanOutput out = null;
	


	public static void main(String[] args) {
		Parser parser = new Parser();
		parser.erweiterter_ausdruck();
	}
	
	public void erweiterter_ausdruck(){
		double result;
		scanner = new ScannerImpl();
		out = scanner.scan();
		
		try {
				result = ausdruck();

			if (out.getToken() == Scanner.ENDE) {
				System.out.println("Eingabe syntaktisch richtig ::: Ergebnis: "+result);
			} else {

				throw new TerminationException("An Stelle " + scanner.getIndex()
						+ " steht " + out.getLexem()
						+ " es wurde aber das Endezeichen [;] erwartet");
			}
		} catch (CloseBracketException e) {
			e.printStackTrace();
		} catch (FactorException e) {
			e.printStackTrace();
		} catch (TerminationException e) {
			e.printStackTrace();
		}
		
	}

	private double ausdruck() throws FactorException, CloseBracketException {
		double sa2, sa3,sa4, ha4, ha6,ha7, ha8;
		String ka1;
		if (out.getToken() == Scanner.MINUS) {
			
			out = scanner.scan();
			sa2 = mal_durch_Term();
			ha4 = -sa2;
			ha6=ha4;

		} else{
			sa3 = mal_durch_Term();
			ha6 = sa3;
		}

		while (out.getToken() == Scanner.PLUS
				|| out.getToken() == Scanner.MINUS) {
			
			
			ka1 = (out.getToken() == Scanner.PLUS) ? "+" : "-";
			out = scanner.scan();
			sa4 =mal_durch_Term();
			ha7=sa4;
			ha8 = (ka1.equals("+")) ? ha6+ha7 : ha6-ha7;
			ha6=ha8;
		}
			return ha6;
	}

	private double mal_durch_Term() throws FactorException, CloseBracketException {
		double sa3,sa4, ha6,ha7, ha8;
		String ka1;
		
		sa3 = pot_Term();
		ha6 = sa3;
		while (out.getToken() == Scanner.MAL
				|| out.getToken() == Scanner.GETEILT) {
			

			ka1 = (out.getToken() == Scanner.GETEILT) ? "/" : "*";
			out = scanner.scan();
			sa4=pot_Term();
			ha7 = sa4;
			ha8 = (ka1.equals("/")) ? ha6/ha7 : ha6*ha7;
			ha6=ha8;
		}
		return ha6;
	}

	private double pot_Term() throws FactorException, CloseBracketException {
		double sa3, sa4, ha6, ha8;
		sa3=faktor();
		ha6=sa3;

		
		if (out.getToken() == Scanner.HOCH) {
			out = scanner.scan();
			sa4=pot_Term();
			
			ha8=Math.pow(sa4, sa3);
			ha6=ha8; 
		}
		return ha6;
	}

	private double faktor() throws FactorException, CloseBracketException {
		double sa3, ha6;
		
		if (out.getToken() == Scanner.KLAMMER_AUF) {
			out = scanner.scan();
			sa3=ausdruck();
			ha6=sa3;
			if (out.getToken() == Scanner.KLAMMER_ZU) {
				out = scanner.scan();
			} else {

				throw new CloseBracketException("An Stelle "
						+ scanner.getIndex() + " steht " + out.getLexem()
						+ " es wurde aber eine schlie§ende Klammer erwartet");
			}

		} else if (out.getToken() == Scanner.ZAHL) {
			ha6=Double.parseDouble(out.getLexem());
			out = scanner.scan();
		} else {

			throw new FactorException("An Stelle " + scanner.getIndex()
					+ " steht " + out.getLexem()
					+ " es wurde aber eine Zahl oder Klammer erwartet");

		}
		return ha6;
	}

}
