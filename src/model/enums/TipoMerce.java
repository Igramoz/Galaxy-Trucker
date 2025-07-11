package model.enums;

import view.Colore;
import view.formattatori.Formattabile;

public enum TipoMerce implements Formattabile {
    ROSSO(4, Colore.ROSSO),
    GIALLO(3, Colore.GIALLO),
    VERDE(2, Colore.VERDE),
    BLU(1, Colore.BLU);
	
	private final int valore;
	private final Colore colore;
	
	private TipoMerce(int valore, Colore colore) {
		this.valore = valore;
		this.colore = colore;
	}
	
	public int getValore() {
		return valore;
	}
	
	public Colore getColore() {
		return colore;
	}
}
