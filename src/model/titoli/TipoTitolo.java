package model.titoli;

import grafica.Colore;
import grafica.formattatori.Formattabile;

public enum TipoTitolo implements Formattabile {

	TRASPORTATORE_SUPREMO(new TrasportatoreSupremo()), 
	BATTERISTA(new Batterista()),
	XENOQUARTIERMASTRO(new Xenoquartiermastro()), 
	CAPITANO_DA_CROCIERA(new CapitanoDaCrociera()),
	MASTRO_INGEGNERE(new MastroIngegnere()),
	MASTRO_CORRIDOISTA(new MastroCorridoista());

	private final Titolo titolo;

	private TipoTitolo(Titolo titolo) {
		this.titolo = titolo;
	}

	public Titolo getTitolo() {
		return titolo;
	}

	@Override
	public Colore getColore() {
		return titolo.getColoreTitolo().getColore();
	}
}
