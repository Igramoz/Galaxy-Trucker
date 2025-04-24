package model.carte.pianeti;

import java.util.List;

import model.enums.TipoMerce;
import model.Giocatore;

public class Pianeta {

	private final int giorniDiVolo; // giorni di volo persi
	private final List<TipoMerce> merciDisponibili;
	private Giocatore player; // solo uno può atterrare

	public Pianeta(int giorni, List<TipoMerce> merci) {
		giorniDiVolo = giorni;
		merciDisponibili = merci;
		player = null;
	}

	public boolean puoAtterrare(Giocatore g) {
		return player == null;
	}

	public boolean atterra(Giocatore g) {
		if (puoAtterrare(g)) {
			player = g;
			// perdo giorni di volo, e devo caricare le merci
			return true;
		}
		return false;
	}

	public int getGiorniDiVolo() {
		return giorniDiVolo;
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
}
