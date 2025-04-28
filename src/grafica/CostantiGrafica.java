package grafica;


public class CostantiGrafica {
	public final static int LARGHEZZA_COMPONENTE = 5; // Numero spazi per rappresentare un componente
	public final static int ALTEZZA_COMPONENTE = 3; // Num righe per rappresentare ogni componente
	public final static String COMPONENTE_NULL  = "O";
	
	public static final String FRECCIA_SOPRA = "^";
	public static final String FRECCIA_SOTTO = "v";
	public static final String FRECCIA_SINISTRA = "<";
	public static final String FRECCIA_DESTRA = ">";
	
    public static final String COLPO_PICCOLO = Colore.GIALLO.getCodice() + "o" + Colore.DEFAULT.getCodice();
    public static final String COLPO_GROSSO = Colore.ROSSO.getCodice() + "O" + Colore.DEFAULT.getCodice();   
    
}
