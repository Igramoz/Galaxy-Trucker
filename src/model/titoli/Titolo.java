package model.titoli;

import grafica.Colore;
import grafica.formattatori.Formattabile;

public enum Titolo implements Formattabile {

	TRASPORTATORE_SUPREMO,
	BATTERISTA,
	XENOQUARTIERMASTRO,
	CAPITANO_DA_CROCIERA,
	MASTRO_INGEGNERE,
	MASTRO_CORRIDOISTA;
	
	
	private ColoreTitolo coloreTitolo;
	
	public ColoreTitolo getColoreTitolo() {
		return coloreTitolo;
	}
	
	public void rendiOro() {
		this.coloreTitolo = ColoreTitolo.Oro;
	}
	
	public Colore getColore() {
		return coloreTitolo.getColore();
	}
	
}
