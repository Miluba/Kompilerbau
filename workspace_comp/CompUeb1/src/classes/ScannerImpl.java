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
	public ScanOutputImpl scan() {
		this.init();

		nextCharachter();

		// Leerzeichen
		while (lexem.equals(" ")) {
			this.init();

			if (nextCharachter() == null)
				break;
		}

		if (lexem.equals("+")) {
			return new ScanOutputImpl(PLUS, lexem, 0);
		} else if (lexem.equals("-"))
			return new ScanOutputImpl(MINUS, lexem, 0);
		else if (lexem.equals("/"))
			return new ScanOutputImpl(GETEILT, lexem, 0);
		else if (lexem.equals("*"))
			return new ScanOutputImpl(MAL, lexem, 0);
		else if (lexem.equals("^"))
			return new ScanOutputImpl(HOCH, lexem, 0);
		else if (lexem.equals("("))
			return new ScanOutputImpl(KLAMMER_AUF, lexem, 0);
		else if (lexem.equals(")"))
			return new ScanOutputImpl(KLAMMER_ZU, lexem, 0);
		else if (lexem.equals("."))
			return new ScanOutputImpl(FALSCHE_KONST, lexem, FEHLER);
		else if (lexem.equals(";")) {
			return new ScanOutputImpl(ENDE, lexem, 0);
		} else if (!isNumber(lexem))
			return new ScanOutputImpl(FALSCHES_ZEICHEN, lexem, FEHLER);
		else if (isNumber(lexem)) {

			while (isNumber(lexem)) {

				String next = nextCharachter();

				if (lexem.endsWith("."))
					nextCharachter();
				else if (!isNumber(lexem)) {

					arrayPosition--;
					return new ScanOutputImpl(ZAHL, lexem.substring(0,
							lexem.length() - 1), 0);
				} else if (next == null)
					return new ScanOutputImpl(ZAHL, lexem, 0);

			}
		}

		return new ScanOutputImpl(FALSCHES_ZEICHEN, lexem, FEHLER);
	}

	public boolean isNumber(String regex) {
		return Pattern.matches("([0-9]*\\.)?[0-9]+", regex);
	}
	
	public int getIndex(){
		return arrayPosition;
	}

}
