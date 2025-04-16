package partita.fasiGioco;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import grafica.Colore;
import io.*;
import model.Giocatore;
import nave.TipoNave;
import partita.LivelloPartita;
import partita.ModalitaGioco;

public class Inizializzazione {

	private Giocatore[] giocatori;
	private int numGiocatori;
	private GestoreOutput gestoreOutput;
	private GestoreInput gestoreInput;
	private Scanner sc;

	private static List<Colore> COLORI_UTILIZZATI;

	public Inizializzazione() {
		gestoreOutput = new GestoreOutput();
		gestoreInput = new GestoreInput();
		sc = new Scanner(System.in);
		COLORI_UTILIZZATI = new ArrayList<>();
	}

	public Giocatore[] start() {
		// gestione numero dei giocatori
		gestoreOutput.stampa("Inserisci il numero dei giocatori (minimo due, massimo quattro)");
		do {
			numGiocatori = gestoreInput.leggiIntero();
			if (numGiocatori < 2 || numGiocatori > 4)
				gestoreOutput.stampa("Inserisci il numero dei giocatori (minimo due, massimo quattro).");
		} while (numGiocatori < 2 || numGiocatori > 4);
		giocatori = new Giocatore[numGiocatori];

		// gestione nome e colore dei giocatori
		for (int i = 0; i < numGiocatori; i++) {
			gestoreOutput.stampa("Scrivi il nome del giocatore " + (i + 1));
			String nome = sc.nextLine();

			Colore colore = null;
			do {
				gestoreOutput.stampa("Colori disponibili: " + Giocatore.coloriDisponibiliGiocatori);
				gestoreOutput.stampa("Scegli il colore del giocatore " + (i + 1));
				String col = sc.nextLine().toUpperCase();

				try {
					colore = Colore.valueOf(col);
					if (COLORI_UTILIZZATI.contains(colore)) {
						gestoreOutput.stampa("Colore già scelto.");

					}
				} catch (IllegalArgumentException e) {
					gestoreOutput.stampa("Colore non valido. Riprova.");
				} finally {
					colore = null;
				}
			} while (colore == null);
			COLORI_UTILIZZATI.add(colore);

			giocatori[i] = new Giocatore(nome, colore);
		}

		sc.close();
		return giocatori;
	}

	public ModalitaGioco getModalita() {
		String scelta = "";
		ModalitaGioco modalita = null;
		gestoreOutput.stampa("Vuoi giocare a \"Volo Singolo\" o a \"Trasvolata Intergalattica\"?");
		gestoreOutput.stampa("Scrivi semplicemente \"volo\" oppure \"trasvolata\".");
		do {
			scelta = sc.nextLine().toLowerCase();
			switch (scelta) {
			case "volo":
				modalita = ModalitaGioco.VOLO_SINGOLO;
				break;
			case "trasvolata":
				modalita = ModalitaGioco.TRASVOLATA_INTERGALATTICA;
				break;
			default:
				gestoreOutput.stampa("Opzione non valida.");
			}
		} while (scelta == "");

		if (modalita == ModalitaGioco.VOLO_SINGOLO) {
			int livello = 0;
			gestoreOutput.stampa("Scegli il livello del gioco, da 1 a 3.");
			do {
				livello = gestoreInput.leggiIntero();
				switch (livello) {
				case 1:
					modalita.setlivelloPartita(LivelloPartita.LIVELLO_1);
					break;
				case 2:
					modalita.setlivelloPartita(LivelloPartita.LIVELLO_2);
					break;
				case 3:
					modalita.setlivelloPartita(LivelloPartita.LIVELLO_3);
					break;
				default:
					gestoreOutput.stampa("Opzione non valida.");
				}

			} while (livello == 0);
		} else
			modalita.setlivelloPartita(LivelloPartita.LIVELLO_1);

		return modalita;
	}
}