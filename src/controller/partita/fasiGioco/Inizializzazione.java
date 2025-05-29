package controller.partita.fasiGioco;

import java.util.ArrayList;
import java.util.List;

import controller.partita.LivelliPartita;
import controller.partita.ModalitaGioco;
import model.Giocatore;
import view.Colore;
import view.formattatori.FormattatoreGrafico;
import view.io.GestoreIO;

public class Inizializzazione {

	private Giocatore[] giocatori;
	private int numGiocatori;
	private GestoreIO gestoreIO;
	private FormattatoreGrafico formattatore;

	private static List<Colore> COLORI_RIMASTI;

	public Inizializzazione() {
		gestoreIO = new GestoreIO();
		COLORI_RIMASTI = new ArrayList<>(Giocatore.coloriDisponibiliGiocatori);
		formattatore = new FormattatoreGrafico();
	}

	/**
	 * Funzione iniziale del gioco che richiede la scelta e l'inserimento del numero
	 * dei giocatori, e dei loro rispettivi nomi e colori
	 * 
	 * @return restituisce l'array dei giocatori partecipanti
	 */
	public Giocatore[] getGiocatori() {
		Giocatore.resetNumeroGiocatori();
		// gestione numero dei giocatori
		do {
			gestoreIO.stampa("Inserisci il numero dei giocatori (minimo due, massimo quattro)");
			numGiocatori = gestoreIO.leggiIntero();
			if (numGiocatori < 2 || numGiocatori > 4)
				continue;
		} while (numGiocatori < 2 || numGiocatori > 4);
		giocatori = new Giocatore[numGiocatori];

		// gestione nome e colore dei giocatori
		for (int i = 0; i < numGiocatori; i++) {
			gestoreIO.stampa("Scrivi il nome del giocatore " + (i + 1));
			String nome = gestoreIO.leggiTesto();

			int lunghezza = COLORI_RIMASTI.size();
			String[] menuColori = new String[lunghezza];
			for (int j = 0; j < lunghezza; j++) {
				menuColori[j] = formattatore.formatta(COLORI_RIMASTI.get(j));
			}

			// ottengo la scelta del colore
			gestoreIO.stampa(nome + ", scegli il tuo colore");
			int sceltaColore = gestoreIO.stampaMenu(menuColori);

			// rimuovo il colore dalla lista
			Colore colore = COLORI_RIMASTI.get(sceltaColore);
			COLORI_RIMASTI.remove(sceltaColore);
			giocatori[i] = new Giocatore(nome, colore);
		}
		return giocatori;
	}

	/**
	 * Funzione che richiede ai giocatori la scelta della modalità e del rispettivo
	 * livello di difficoltà
	 * 
	 * @return restituisce la modalità di gioco scelta, a cui è associato il livello
	 */
	public ModalitaGioco getModalita() {
		// preparo il menu per scegliere la modalità
		String[] menuModalita = { "Volo Singolo", "Trasvolata Intergalattica" };
		gestoreIO.stampa("Scegli la modalità di gioco:");
		int sceltaModalita = gestoreIO.stampaMenu(menuModalita);

		ModalitaGioco modalita = null;
		switch (sceltaModalita) {
		case 0:
			modalita = ModalitaGioco.VOLO_SINGOLO;
			break;
		case 1:
			modalita = ModalitaGioco.TRASVOLATA_INTERGALATTICA;
			break;
		}

		// se si gioca a "Volo Singolo", chiedo anche il livello
		if (modalita == ModalitaGioco.VOLO_SINGOLO) {
			String[] menuLivelli = { "Livello 1", "Livello 2", "Livello 3" };
			gestoreIO.stampa("Scegli il livello del gioco:");
			int sceltaLivello = gestoreIO.stampaMenu(menuLivelli);

			switch (sceltaLivello) {
			case 0:
				modalita.setlivelloPartita(LivelliPartita.LIVELLO_1);
				break;
			case 1:
				modalita.setlivelloPartita(LivelliPartita.LIVELLO_2);
				break;
			case 2:
				modalita.setlivelloPartita(LivelliPartita.LIVELLO_3);
				break;
			}
		} else
			modalita.setlivelloPartita(LivelliPartita.LIVELLO_1);

		return modalita;
	}
}