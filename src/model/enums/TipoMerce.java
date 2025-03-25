package model.enums;

public enum TipoMerce {
	BLU(1), VERDE(2), GIALLO(3), ROSSO(4);
		
	private final int valore;
	
	private TipoMerce(int valore) {
		this.valore = valore;
	}
	
	public int getValore() {
		return valore;
	}
}
