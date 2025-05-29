package model.carte.pianeti;

import java.util.List;

import model.enums.TipoMerce;
import view.Colore;
import model.Giocatore;

public class Pianeta {

	private final List<TipoMerce> merciDisponibili;
	private Giocatore player;
	private final Colore colore;

	public Pianeta(List<TipoMerce> merci, Colore col) {
		merciDisponibili = merci;
		colore = col;
		player = null;
	}

	/**
	 * Ciascun pianeta ospita solo un giocatore alla volta, quindi devo controllare
	 * che sia libero.
	 * 
	 * @param g: il giocatore che vuole atterrare sul pianeta
	 * @return true se è atterrato
	 */
	public boolean atterra(Giocatore g) {
		if (!isOccupato()) {
			player = g;
			return true;
		}
		return false;
	}

	public List<TipoMerce> getMerciDisponibili() {
		return merciDisponibili;
	}

	public boolean isOccupato() {
		return player != null;
	}

	public Giocatore getOccupante() {
		return player;
	}

	public Colore getColore() {
		return colore;
	}

	public int getNumeroMerci() {
		return merciDisponibili.size();
	}
}