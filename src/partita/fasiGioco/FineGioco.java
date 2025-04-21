package partita.fasiGioco;

import java.util.ArrayList;
import java.util.List;

import grafica.FormattatoreGrafico;
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

	public void start() {
		List<Giocatore> vincitori = new ArrayList<>();
		Giocatore maggiorVincitore = giocatori[0];
		int contatore = 0;
		for (int i = 0; i < giocatori.length; i++) {
			if (giocatori[i].getCrediti() >= 1) {
				vincitori.add(giocatori[i]);
				contatore++;
				if (giocatori[i].getCrediti() > maggiorVincitore.getCrediti()) {
					maggiorVincitore = giocatori[i];
				}
			}
		}

		int lunghezza = vincitori.size();
		if (lunghezza == 0) {
			gestoreIO.stampa("Nessun giocatore ha vinto");
		} else if (lunghezza == 1) {
			gestoreIO.stampa("L'unico vincitore è stato " + formattatore.formattaGiocatore(maggiorVincitore) + " con "
					+ maggiorVincitore.getCrediti() + " punti.");
		} else {
			gestoreIO.stampa("I vincitori sono stati:");
			for (int i = 0; i < lunghezza; i++) {
				gestoreIO.stampa(formattatore.formattaGiocatore(vincitori.get(i)) + " con "
						+ vincitori.get(i).getCrediti() + " punti");
			}
			gestoreIO.stampa("Ma " + formattatore.formattaGiocatore(maggiorVincitore) + " ha vinto più degli altri!");
		}

		String[] menu = { "Rigioca", "Chiudi gioco" };
		int scelta = gestoreIO.stampaMenu(menu);
		switch (scelta) {
		case 0:
			Partita p = new Partita();
			p.gioca();
			break;
		case 1:
			gestoreIO.stampa("Grazie per aver giocato.");
			break;
		}
	}
}