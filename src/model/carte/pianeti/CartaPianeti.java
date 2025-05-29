package model.carte.pianeti;

import java.util.ArrayList;
import java.util.List;

import controller.partita.fasiGioco.volo.ManagerDiVolo;
import model.Giocatore;
import model.carte.Carta;
import model.carte.TipoCarta;
import model.carte.criteriEffetti.Effetto;

public class CartaPianeti extends Carta {

	private final List<Pianeta> pianeti; // lista dei pianeti di questa carta
	private final int giorniVoloPersi;
	private final Effetto effetto = Effetto.GUADAGNA_MERCE;

	public CartaPianeti(List<Pianeta> listaPianeti, int giorni) {
		super(TipoCarta.PIANETI);
		pianeti = listaPianeti;
		giorniVoloPersi = giorni;
	}

	/**
	 * La carta pianeti gestisce la possibilità dei giocatori presenti di poter
	 * atterrare su determinati pianeti o meno.
	 * 
	 * @param listaManager: array dei giocatori presenti nel volo
	 */
	@Override
	public void eseguiEvento(ManagerDiVolo[] listaManager) {
		carteRenderer.rappresentaCarta(this);
		for (ManagerDiVolo m : listaManager) {
			Giocatore g = m.getGiocatore();
			List<Pianeta> pianetiDisponibili = new ArrayList<>();
			for (Pianeta p : pianeti) {
				if (!p.isOccupato()) {
					pianetiDisponibili.add(p);
				}
			}
			int numPianetiDisponibili = pianetiDisponibili.size();
			if (pianetiDisponibili.isEmpty()) {
				io.stampa("Non ci sono più pianeti disponibili su cui poter atterrare.");
				break;
			}

			// preparazione del menu
			String[] menu = new String[numPianetiDisponibili + 1];
			for (int i = 0; i < numPianetiDisponibili; i++) {
				menu[i] = "Atterra sul Pianeta " + formattatoreGrafico.formatta(pianetiDisponibili.get(i).getColore());
			}
			menu[numPianetiDisponibili] = "Non voglio atterare";

			// stampa del menu
			io.stampa(formattatoreGrafico.formatta(g) + ", su quale pianeta vuoi atterrare?");
			int scelta = io.stampaMenu(menu);

			if (scelta < numPianetiDisponibili) {
				Pianeta scelto = pianetiDisponibili.get(scelta);
				if (scelto.atterra(g)) {
					io.stampa(formattatoreGrafico.formatta(g) + " è atterrato sul Pianeta "
							+ formattatoreGrafico.formatta(scelto.getColore()));
					effetto.applica(m, scelto.getMerciDisponibili());
					Effetto.GIORNI_VOLO.applica(m, giorniVoloPersi);
				}
			} else {
				io.stampa(formattatoreGrafico.formatta(g) + " ha scelto di non atterrare.");
			}
		}
		io.aCapo();
	}

	public List<Pianeta> getPianeti() {
		return pianeti;
	}

	public int getGiorniVoloPersi() {
		return giorniVoloPersi;
	}

	public Effetto getEffetto() {
		return effetto;
	}
}