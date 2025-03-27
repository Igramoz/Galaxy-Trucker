package componenti;

public enum TipoComponente {
	CABINA_EQUIPAGGIO("CE"), CABINA_PARTENZA("CP"), CANNONE_SINGOLO("CS"), CANNONE_DOPPIO("CD"), MOTORE_SINGOLO("MS"),
	MOTORE_DOPPIO("MD"), SCUDO("S"), STIVA("M"), VANO_BATTERIA("B"), MODULO_SUPPORTO_ALIENI("MSA"), TUBO("T");
	// La M di stiva significa Magazzino

	private final String sigla;
	private final int maxIstanze;
	
	private TipoComponente(String s, int maxIstanze) {
		sigla = s;
		this.maxIstanze = maxIstanze;
	}
	
	
	@Override
	public String toString() {
		return sigla;
	}
}