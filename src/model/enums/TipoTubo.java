package model.enums;

public enum TipoTubo {
	NESSUNO ("", ""),
	SINGOLO ("|", "-"),
	DOPPIO ("||","="),
	UNIVERSALE ("#", "#");
	
	private final String siglaOrizzontale; // Sigla da usare quando il tubo è orizzontale
	private final String siglaVerticale; // Sigla da usare quando il tubo è verticale
	
	private TipoTubo() {
		siglaOrizzontale = "";
		siglaVerticale = "";
	}
	
	private TipoTubo(String siglaOrizzontale, String siglaVerticale) {
		this.siglaOrizzontale = siglaOrizzontale;
		this.siglaVerticale = siglaVerticale;
	}
	
	// TODO: Va bene chiamarlo toString così? Anche se non è override?
	public String toString(Direzione direzione) {
		if(direzione == Direzione.SINISTRA || direzione == Direzione.DESTRA) {
			return siglaOrizzontale;
		} else {
			return siglaVerticale;
		}
	}
	
}
