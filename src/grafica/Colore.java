package grafica;

import grafica.formattatori.Formattabile;

public enum Colore implements Formattabile{
	//i colori sono codici ANSI
	DEFAULT("\u001B[0m"), // per settare il colore default di stampa
	BIANCO("\u001B[38;5;15m"),
	GIALLO("\u001B[38;5;226m"),
	VIOLA("\u001B[38;5;129m"),
	VIOLA_LILLA("\u001B[38;5;183m"),
	MARRONE("\u001B[38;5;137m"),
	MARRONE_BEIGE("\u001B[38;5;180m"),
	VERDE("\u001B[38;5;34m"),
	VERDE_LIME("\u001B[38;5;120m"), 
	AZZURRO("\u001B[38;5;51m"),
	BLU("\u001B[38;5;32m"),
	ROSSO("\u001B[38;5;203m"),
	GRIGIO("\u001B[37m"),
	ARGENTO("\u001B[38;2;192;192;192m"),
	ORO("\u001B[38;2;255;215;0m");


	private final String codice;

	private Colore(String cod) {
		codice = cod;
	}

	public String getCodice() {
		return codice;
	}
	
	public Colore getColore() {
		return this;
	}	
}