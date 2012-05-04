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
		
		scanner = new ScannerImpl();
		out = scanner.scan();
		
		try {
			this.ausdruck();

			if (out.getToken() == Scanner.ENDE) {
				System.out.println("Eingabe syntaktisch richtig");
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

	private void ausdruck() throws FactorException, CloseBracketException {

		if (out.getToken() == Scanner.MINUS) {
			out = scanner.scan();
			mal_durch_Term();

		} else
			mal_durch_Term();

		while (out.getToken() == Scanner.PLUS
				|| out.getToken() == Scanner.MINUS) {
			out = scanner.scan();
			mal_durch_Term();
		}

	}

	private void mal_durch_Term() throws FactorException, CloseBracketException {
		pot_Term();
		while (out.getToken() == Scanner.MAL
				|| out.getToken() == Scanner.GETEILT) {
			out = scanner.scan();
			pot_Term();
		}

	}

	private void pot_Term() throws FactorException, CloseBracketException {
		faktor();

		if (out.getToken() == Scanner.HOCH) {
			out = scanner.scan();
			pot_Term();
		}

	}

	private void faktor() throws FactorException, CloseBracketException {

		if (out.getToken() == Scanner.KLAMMER_AUF) {
			out = scanner.scan();
			ausdruck();
			if (out.getToken() == Scanner.KLAMMER_ZU) {
				out = scanner.scan();
			} else {

				throw new CloseBracketException("An Stelle "
						+ scanner.getIndex() + " steht " + out.getLexem()
						+ " es wurde aber eine Klammer erwartet");

			}

		} else if (out.getToken() == Scanner.ZAHL) {
			out = scanner.scan();
		} else {

			throw new FactorException("An Stelle " + scanner.getIndex()
					+ " steht " + out.getLexem()
					+ " es wurde aber eine Zahl erwartet");

		}
	}

}
