package classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

import interfaces.Scanner;

public class ScannerImpl implements Scanner {

	private char[] eingabe = null;
	private String lexem = "";
	private int arrayPosition = 0;

	public ScannerImpl() {

		BufferedReader in = null;
		String input = null;

		try {
			in = new BufferedReader(new InputStreamReader(System.in));
			input = in.readLine();
			eingabe = input.toCharArray();
		} catch (IOException e) {
			System.out.println("InputStreamReader" + e.getMessage());
			e.printStackTrace();
		}
	}

	private void init() {
		lexem = "";

	}

	private String nextCharachter() {
		if (eingabe.length > arrayPosition)
			lexem = lexem + eingabe[arrayPosition++];
		else
			return null;

		return lexem;
	}

	@Override
	public ScanOutput scan() {
		this.init();

		nextCharachter();

		// Leerzeichen
		while (lexem.equals(" ")) {
			this.init();

			if (nextCharachter() == null)
				break;
		}

		if (lexem.equals("+")) {
			return new ScanOutput(PLUS, lexem, 0);
		} else if (lexem.equals("-"))
			return new ScanOutput(MINUS, lexem, 0);
		else if (lexem.equals("/"))
			return new ScanOutput(GETEILT, lexem, 0);
		else if (lexem.equals("*"))
			return new ScanOutput(MAL, lexem, 0);
		else if (lexem.equals("^"))
			return new ScanOutput(HOCH, lexem, 0);
		else if (lexem.equals("("))
			return new ScanOutput(KLAMMER_AUF, lexem, 0);
		else if (lexem.equals(")"))
			return new ScanOutput(KLAMMER_ZU, lexem, 0);
		else if (lexem.equals("."))
			return new ScanOutput(FALSCHE_KONST, lexem, FEHLER);
		else if (lexem.equals(";")) {
			return new ScanOutput(ENDE, lexem, 0);
		} else if (!isNumber(lexem))
			return null;// new ScanOutput(FALSCHES_ZEICHEN, lexem, FEHLER);
		else if (isNumber(lexem)) {

			while (isNumber(lexem)) {

				String next = nextCharachter();

				if (lexem.endsWith("."))
					nextCharachter();
				else if (!isNumber(lexem)) {

					arrayPosition--;
					return new ScanOutput(ZAHL, lexem.substring(0,
							lexem.length() - 1), 0);
				} else if (next == null)
					return new ScanOutput(ZAHL, lexem, 0);

			}
		}

		return new ScanOutput(FALSCHES_ZEICHEN, lexem, FEHLER);
	}

	public boolean isNumber(String regex) {
		return Pattern.matches("([0-9]*\\.)?[0-9]+", regex);
	}

	public static void main(String[] args) {
		ScannerImpl sc = new ScannerImpl();
		ScanOutput out = null;
		while ((out = sc.scan()) != null) {

			System.out.println(out.getToken() + " | " + out.getLexem() + " | "
					+ out.getErrorCode());

			if (out.getToken() == ENDE)
				break;

		}
	}

}
