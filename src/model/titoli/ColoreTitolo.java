package model.titoli;

import view.Colore;

public enum ColoreTitolo {

	Argento(Colore.ARGENTO),
	Oro(Colore.ORO);
	
	private final Colore colore;
	
	private ColoreTitolo(Colore colore) {
		this.colore = colore;
	}
	
	public Colore getColore() {
		return colore;
	}	
}
