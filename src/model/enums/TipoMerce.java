package model.enums;

import grafica.Colore;

public enum TipoMerce {
	BLU(1, Colore.BLU), VERDE(2, Colore.VERDE), GIALLO(3, Colore.GIALLO), ROSSO(4, Colore.ROSSO);
		
	private final int valore;
	private final Colore colore;
	
	private TipoMerce(int valore, Colore colore) {
		this.valore = valore;
		this.colore = colore;
	}
	
	public int getValore() {
		return valore;
	}
}
