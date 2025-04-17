package partita.fasiGioco;

import java.util.EnumSet;
import java.util.Scanner;

import grafica.Colore;
import io.GestoreIO;
import model.Giocatore;
import partita.LivelliPartita;
import partita.ModalitaGioco;

public class Inizializzazione {

	private Giocatore[] giocatori;
	private int numGiocatori;
	private GestoreIO gestoreIO;
	private Scanner sc;

	private static EnumSet<Colore> COLORI_RIMASTI;

	public Inizializzazione() {
		gestoreIO = new GestoreIO();
		COLORI_RIMASTI = Giocatore.coloriDisponibiliGiocatori;
	}

	public Giocatore[] getGiocatori() {
		sc = new Scanner(System.in);
		// gestione numero dei giocatori
		gestoreIO.stampa("Inserisci il numero dei giocatori (minimo due, massimo quattro)");
		do {
			numGiocatori = gestoreIO.leggiIntero();
			if (numGiocatori < 2 || numGiocatori > 4)
				gestoreIO.stampa("Inserisci il numero dei giocatori (minimo due, massimo quattro).");
		} while (numGiocatori < 2 || numGiocatori > 4);
		giocatori = new Giocatore[numGiocatori];

		// gestione nome e colore dei giocatori
		for (int i = 0; i < numGiocatori; i++) {
			gestoreIO.stampa("Scrivi il nome del giocatore " + (i + 1));
			String nome = sc.nextLine();

			Colore[] coloriDisponibili = new Colore[COLORI_RIMASTI.size()];
			int index = 0;
			for (Colore c : COLORI_RIMASTI) {
				coloriDisponibili[index++] = c;
			}

			int lunghezza = coloriDisponibili.length;
			String[] menuColori = new String[lunghezza];
			for (int j = 0; j < lunghezza; j++) {
				menuColori[j] = j + " - " + coloriDisponibili[j].toString();
			}

			// ottengo la scelta del colore
			gestoreIO.stampa("Scegli il colore del giocatore " + (i + 1));
			int sceltaColore = gestoreIO.stampaMenu(menuColori);

			// rimuovo il colore dalla lista
			Colore colore = coloriDisponibili[sceltaColore];
			COLORI_RIMASTI.remove(colore);
			giocatori[i] = new Giocatore(nome, colore);
		}

		sc.close();
		return giocatori;
	}

	public ModalitaGioco getModalita() {
		// preparo il menu per scegliere la modalità
		String[] menuModalita = { "0 - Volo Singolo", "1 - Trasvolata Intergalattica" };
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
			String[] menuLivelli = { "0 - Livello 1", "1 - Livello 2", "2 - Livello 3" };
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