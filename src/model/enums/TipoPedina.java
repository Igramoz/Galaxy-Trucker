package model.enums;

import view.Colore;
import view.formattatori.Formattabile;

public enum TipoPedina implements Formattabile {
	ASTRONAUTA(Colore.BIANCO), ALIENO_VIOLA(Colore.VIOLA_LILLA), ALIENO_MARRONE(Colore.MARRONE);
	
	Colore colore;
	
	TipoPedina(Colore colore) {
		this.colore = colore;
	}
	
	public Colore getColore() {
		return colore;
	}	
}