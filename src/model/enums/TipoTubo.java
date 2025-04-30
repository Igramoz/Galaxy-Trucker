package model.enums;

import util.layout.Direzione;

public enum TipoTubo {
	NESSUNO (" ", " "),
	SINGOLO ("|", "-"),
	DOPPIO ("||","="),
	UNIVERSALE ("#", "#");
	
	private final String siglaVerticale; // Sigla da usare quando il tubo è verticale
	private final String siglaOrizzontale; // Sigla da usare quando il tubo è orizzontale
	
	private TipoTubo() {
		siglaVerticale = "";
		siglaOrizzontale= "";
	}
	
	private TipoTubo(String siglaVerticale, String siglaOrizzontale) {
		this.siglaVerticale = siglaVerticale;
		this.siglaOrizzontale = siglaOrizzontale;
	}
	
	public String getSimbolo(Direzione direzione) {
		if(direzione == Direzione.SINISTRA || direzione == Direzione.DESTRA) {
			return siglaOrizzontale;
		} else {
			return siglaVerticale;
		}
	}
	
	public boolean isCompatibileCon(TipoTubo altro) {
		if ((this == NESSUNO && altro != NESSUNO) || (altro == NESSUNO && this != NESSUNO)) {
			return false;
		}

		return this == UNIVERSALE || altro == UNIVERSALE || this == altro;
	}
}
