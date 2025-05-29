package model.componenti;

import view.Colore;
import view.formattatori.Formattabile;

public enum TipoComponente implements Formattabile{
	CABINA_EQUIPAGGIO("CE", 17, Colore.BIANCO), 
	CABINA_PARTENZA("CP", 4, null),
	CANNONE_SINGOLO("CS", 25, Colore.VIOLA_LILLA), 
	CANNONE_DOPPIO("CD", 11, Colore.VIOLA_LILLA),
	MOTORE_SINGOLO("MS", 21, Colore.MARRONE_BEIGE), 
	MOTORE_DOPPIO("MD", 9, Colore.MARRONE_BEIGE),
	SCUDO("S", 8, Colore.VERDE), 
	STIVA("ST", 25, Colore.AZZURRO), 
	STIVA_SPECIALE("SS", 9, Colore.ROSSO),
	VANO_BATTERIA("B", 17, Colore.VERDE_LIME), 
	SOVRASTRUTTURA_ALIENA_VIOLA("SAV", 6, Colore.VIOLA),
	SOVRASTRUTTURA_ALIENA_MARRONE("SAM", 6, Colore.MARRONE), 
	TUBO("T", 8, Colore.GRIGIO);

	private final String sigla;
	private final int maxIstanze;
	private final Colore colore; // è un codice ANSI

	private TipoComponente(String s, int maxIstanze, Colore colore) {
		sigla = s;
		this.maxIstanze = maxIstanze;
		this.colore = colore;
	}

	@Override
	public String toString() {
		if(colore == null)
			return sigla + Colore.DEFAULT.getCodice();
		else
			return colore.getCodice() + sigla + Colore.DEFAULT.getCodice();

	}

	public int getMaxIstanze() {
		return maxIstanze;
	}

	public Colore getColore() {
		return colore;
	}

	public String getSigla() {
		return sigla;
	}
}