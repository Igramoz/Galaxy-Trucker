package model.titoli;

import java.util.List;

import grafica.Colore;
import grafica.formattatori.Formattabile;
import model.Giocatore;
import partita.LivelliPartita;

public abstract class Titolo implements Formattabile {

	private ColoreTitolo colore;
	private TipoTitolo titolo;

	private static final int CREDITI_TITOLO_LIVELLO_1 = 2;
	private static final int CREDITI_TITOLO_LIVELLO_2 = 4;
	private static final int CREDITI_TITOLO_LIVELLO_3_ARGENTO = 6;
	private static final int CREDITI_TITOLO_LIVELLO_3_ORO = 12;

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

	/**
	 * @return numero di crediti che si guadagna se si ha difeso il titolo o quando
	 *         lo si ottiene
	 */
	public int getCrediti(LivelliPartita livello) {

		switch (livello) {
		case LIVELLO_1:
			return CREDITI_TITOLO_LIVELLO_1;
		case LIVELLO_2:
			return CREDITI_TITOLO_LIVELLO_2;
		case LIVELLO_3:
			if (colore == ColoreTitolo.Argento)
				return CREDITI_TITOLO_LIVELLO_3_ARGENTO;
			else
				return CREDITI_TITOLO_LIVELLO_3_ORO;
		default:
			throw new IllegalArgumentException("Livello partita non valido: " + livello);
		}
	}

	/**
	 * Data una lista di giocatori valuta quale rispetta le condizioni del titolo
	 * 
	 * @param giocatori, i giocatori dei quali bisogna valutare il titolo
	 * @return giocatore che rispetta le condizioni per guadangare o mantenere il
	 *         titolo
	 */
	public abstract Giocatore valutaTitolo(List<Giocatore> giocatori);

}
