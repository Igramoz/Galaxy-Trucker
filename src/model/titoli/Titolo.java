package model.titoli;

import java.util.List;

import grafica.Colore;
import grafica.formattatori.Formattabile;
import model.Giocatore;



public abstract class Titolo implements Formattabile{
	
	private ColoreTitolo colore;
	private TipoTitolo titolo;
	
	public String getNome() {
		return titolo.name().toLowerCase();
	}
	
	public Titolo(TipoTitolo titolo) {
		this.titolo = titolo;
		colore = ColoreTitolo.Argento;
	}
	
	
	public ColoreTitolo getColoreTitolo() {
		return colore;
	}
	
	public void rendiOro() {
		this.colore = ColoreTitolo.Oro;
	}
	
	public Colore getColore() {
		return colore.getColore();
	}
	
	public abstract Giocatore valutaTitolo (List<Giocatore> giocatori);
		
	

}
