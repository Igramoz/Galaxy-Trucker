package componenti;

public enum TipoComponente {
	CABINA_EQUIPAGGIO("CE", 17, "\u001B[38;5;15m"), // Bianco
	CABINA_PARTENZA("CP", 4, "\u001B[38;5;226m"), // Giallo
	CANNONE_SINGOLO("CS", 25, "\u001B[38;5;183m"), // Viola
	CANNONE_DOPPIO("CD", 11, "\u001B[38;5;183m"),
	MOTORE_SINGOLO("MS", 21, "\u001B[38;5;180m"), // Marrone
	MOTORE_DOPPIO("MD", 9, "\u001B[38;5;180m"),
	SCUDO("SC", 8, "\u001B[38;5;34m"), // Verde
	STIVA("S", 25, "\u001B[38;5;51m"), // Azzurro
	STIVA_SPECIALE("SS", 9, "\u001B[38;5;203m"), // Rosso
	VANO_BATTERIA("B", 17, "\u001B[38;5;120m"), // Verde
	SOVRASTRUTTURA_ALIENA_VIOLA("SAV", 6, "\u001B[38;5;129m"), // Viola scuro
	SOVRASTRUTTURA_ALIENA_MARRONE("SAM", 6, "\u001B[38;5;137m"), // Marrone scuro
	TUBO("T", 8, "\u001B[37m"); // Grigio

	private static final String RESET = "\u001B[0m"; // reimposta il colore default di stampa

	private final String sigla;
	private final int maxIstanze;
	private final String colore; // è un codice ANSI

	private TipoComponente(String s, int maxIstanze, String colore) {
		sigla = s;
		this.maxIstanze = maxIstanze;
		this.colore = colore;
	}

	@Override
	public String toString() {
		return colore + sigla + RESET;
	}

	public int getMaxIstanze() {
		return maxIstanze;
	}

	public String getColore() {
		return colore;
	}

	public String getSigla() {
		return sigla;
	}
}