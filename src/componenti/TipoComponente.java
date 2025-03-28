package componenti;

public enum TipoComponente {
	CABINA_EQUIPAGGIO("CE", 17), CABINA_PARTENZA("CP", 4), CANNONE_SINGOLO("CS", 25), CANNONE_DOPPIO("CD", 11),
	MOTORE_SINGOLO("MS", 21), MOTORE_DOPPIO("MD", 9), SCUDO("S", 8), STIVA("M", 25), VANO_BATTERIA("B", 17),
	SOVRASTRUTTURA_ALIENA_VIOLA("SAV", 6), SOVRASTRUTTURA_ALIENA_MARRONE("SAM", 6), TUBO("T", 8);
	// La M di stiva significa Magazzino

	private final String sigla;
	private final int limiteMax;
	//TODO: attributo colore

	private TipoComponente(String s, int max) {
		sigla = s;
		limiteMax = max;
	}

	public String getSigla() {
		return sigla;
	}

	public int getLimiteMax() {
		return limiteMax;
	}
}