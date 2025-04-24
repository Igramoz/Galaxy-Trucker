package model.carte.pianeti;

import java.util.List;

import model.Giocatore;
import model.carte.Carta;
import model.enums.TipoCarta;

public class CartaPianeti extends Carta {

	private final List<Pianeta> pianeti; // lista dei pianeti di questa carta
	private final int giorniVoloPersi;

	public CartaPianeti(List<Pianeta> listaPianeti, int giorni) {
		super(TipoCarta.PIANETI);
		pianeti = listaPianeti;
		giorniVoloPersi = giorni;
	}

	@Override
	public void eseguiEvento(Giocatore[] giocatori) {
		// TODO: gestisci la scelta dei pianeti da parte dei giocatori
		// (in ordine di rotta, una sola nave per pianeta)

		carteRenderer.stampaPianeti(this);
	}

	public List<Pianeta> getPianeti() {
		return pianeti;
	}

	public int getGiorniVoloPersi() {
		return giorniVoloPersi;
	}
}