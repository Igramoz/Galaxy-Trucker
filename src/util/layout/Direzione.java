package util.layout;

public enum Direzione {
    SOPRA,
    SINISTRA,
    SOTTO,
    DESTRA;
	

    public Direzione opposta() {
        return switch (this) {
            case SOPRA -> SOTTO;
            case SOTTO -> SOPRA;
            case DESTRA -> SINISTRA;
            case SINISTRA -> DESTRA;
        };
    }
	
    // Ruota in senso antiorario
    public Direzione ruota() {
    	Direzione[] valori = Direzione.values();
    	int nuovaDirezione = (this.ordinal() +1 ) % valori.length;
    	return valori[nuovaDirezione];
    }
    
}