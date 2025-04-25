package model.carte.pianeti;

import java.util.List;

import model.Giocatore;
import model.carte.Carta;
import model.carte.TipoCarta;

public class CartaPianeti extends Carta {

	private final List<Pianeta> pianeti; // lista dei pianeti di questa carta
	private final int giorniVoloPersi;
	private final int numeroMerci;

	public CartaPianeti(List<Pianeta> listaPianeti, int giorni, int numMerci) {
		super(TipoCarta.PIANETI);
		pianeti = listaPianeti;
		giorniVoloPersi = giorni;
		numeroMerci = numMerci;
	}

	@Override
	public void eseguiEvento(Giocatore[] giocatori) {
		carteRenderer.stampaPianeti(this);
		// TODO: menu
	}

	public List<Pianeta> getPianeti() {
		return pianeti;
	}

	public int getGiorniVoloPersi() {
		return giorniVoloPersi;
	}

	public int getNumeroMerci() {
		return numeroMerci;
	}
}