package model.carte.pianeti;

import java.util.List;

import grafica.Colore;
import model.enums.TipoMerce;
import model.Giocatore;

public class Pianeta {

	private final List<TipoMerce> merciDisponibili;
	private Giocatore player; // solo uno può atterrare
	private final Colore colore;

	public Pianeta(List<TipoMerce> merci, Colore col) {
		merciDisponibili = merci;
		colore = col;
		player = null;
	}

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