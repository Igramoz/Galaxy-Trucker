package model.enums;

import grafica.Colore;

public enum TipoPedina {
	ASTRONAUTA(Colore.BIANCO), ALIENO_VIOLA(Colore.VIOLA_LILLA), ALIENO_MARRONE(Colore.MARRONE);
	
	Colore colore;
	
	TipoPedina(Colore colore) {
		this.colore = colore;
	}
	
	public Colore getColore() {
		return colore;
	}	
}