package model.carte.pianeti;

import java.util.ArrayList;
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

		for (Giocatore g : giocatori) {
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
				menu[i] = "Atterra sul Pianeta "
						+ formattatoreGrafico.formattaColore(pianetiDisponibili.get(i).getColore());
			}
			menu[numPianetiDisponibili] = "Non voglio atterare";

			// stampa del menu
			io.stampa(formattatoreGrafico.formattaGiocatore(g) + ", su quale pianeta vuoi atterrare?");
			int scelta = io.stampaMenu(menu);

			if (scelta < numPianetiDisponibili) {
				Pianeta scelto = pianetiDisponibili.get(scelta);
				if (scelto.atterra(g)) {
					io.stampa(formattatoreGrafico.formattaGiocatore(g) + " è atterrato sul Pianeta "
							+ formattatoreGrafico.formattaColore(scelto.getColore()));
					g.getNave().setMerci(scelto.getMerciDisponibili());
				}
			} else {
				io.stampa(formattatoreGrafico.formattaGiocatore(g) + " ha scelto di non atterrare.");
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

	public int getNumeroMerci() {
		return numeroMerci;
	}
}