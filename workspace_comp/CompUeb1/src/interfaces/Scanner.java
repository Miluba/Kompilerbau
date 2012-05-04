package interfaces;

import classes.ScanOutputImpl;

public interface Scanner {
	
	public static int PLUS =1, MINUS=2, MAL=3, GETEILT=4, HOCH=5, KLAMMER_AUF=6, KLAMMER_ZU=7, ZAHL=8, FEHLER=9, ENDE=10, FALSCHES_ZEICHEN=11, FALSCHE_KONST=12 ;
	
	public ScanOutputImpl scan();
	
	public int getIndex();
	
}
