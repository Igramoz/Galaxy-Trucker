package model.titoli;

import grafica.formattatori.Formattabile;

public enum TipoTitolo implements Formattabile {

	TRASPORTATORE_SUPREMO(new TrasportatoreSupremo()),
	BATTERISTA(new Batterista()),
	XENOQUARTIERMASTRO,
	CAPITANO_DA_CROCIERA(new CapitanoDaCrociera()),
	MASTRO_INGEGNERE(new MastroIngegnere()),
	MASTRO_CORRIDOISTA;

	private final Titolo titolo;

	public TipoTitolo(Titolo titolo) {
		this.titolo = titolo;
	}

	public Titolo getTitolo() {
		return titolo;
	}

}
