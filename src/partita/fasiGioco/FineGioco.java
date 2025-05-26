package partita.fasiGioco;

import java.util.ArrayList;
import java.util.List;

import grafica.formattatori.FormattatoreGrafico;
import io.GestoreIO;
import model.Giocatore;
import partita.Partita;

public class FineGioco {

	private Giocatore[] giocatori;
	private GestoreIO gestoreIO;
	private FormattatoreGrafico formattatore;

	public FineGioco(Giocatore[] giocatori) {
		this.giocatori = giocatori;
		gestoreIO = new GestoreIO();
		formattatore = new FormattatoreGrafico();
	}

	/**
	 * Stabilisce quali siano i giocatori vincenti (ed il più vincente) in base al
	 * numero di punti finali. Infine, richiede agli stessi giocatori di giocare
	 * ancora.
	 */
	public void start() {
		List<Giocatore> vincitori = new ArrayList<>();
		Giocatore maggiorVincitore = giocatori[0];
		for (int i = 0; i < giocatori.length; i++) {
			Giocatore g = giocatori[i];
			if (g.getCrediti() >= 1) {
				vincitori.add(g);
				if (g.getCrediti() > maggiorVincitore.getCrediti()) {
					maggiorVincitore = g;
				}
			}
		}

		int lunghezza = vincitori.size();
		if (lunghezza == 0) {
			gestoreIO.stampa("Nessun giocatore ha vinto");
		} else if (lunghezza == 1) {
			gestoreIO.stampa("L'unico vincitore è stato " + formattatore.formatta(maggiorVincitore) + " con "
					+ maggiorVincitore.getCrediti() + " punti.");
		} else {
			gestoreIO.stampa("I vincitori sono stati:");
			for (int i = 0; i < lunghezza; i++) {
				gestoreIO.stampa(
						formattatore.formatta(vincitori.get(i)) + " con " + vincitori.get(i).getCrediti() + " punti");
			}
			gestoreIO.stampa("Ma " + formattatore.formatta(maggiorVincitore) + " ha vinto più degli altri!");
		}

		String[] menu = { "Rigioca", "Chiudi gioco" };
		int scelta = gestoreIO.stampaMenu(menu);
		switch (scelta) {
		case 0:
			String temp[] = new String[0];
			Partita p = new Partita(temp);
			p.gioca();
			break;
		case 1:
			gestoreIO.stampa("Grazie per aver giocato.");
			break;
		}
	}
}