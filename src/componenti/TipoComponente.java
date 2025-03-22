package componenti;

public enum TipoComponente {
	CABINA_EQUIPAGGIO("CE"), CABINA_PARTENZA("CP"), CANNONE_SINGOLO("CS"), CANNONE_DOPPIO("CD"), MOTORE_SINGOLO("MS"),
	MOTORE_DOPPIO("MD"), SCUDO("S"), STIVA("M"), VANO_BATTERIA("B"), MODULO_SUPPORTO_ALIENI("MSA"), TUBO("T");
	// La M di stiva significa Magazzino

	private final String sigla;

	private TipoComponente(String s) {
		sigla = s;
	}

	public String getSigla() {
		return sigla;
	}
}