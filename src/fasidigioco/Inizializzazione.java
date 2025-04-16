package fasidigioco;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import grafica.Colore;
import io.GestoreInput;
import io.GestoreOutput;
import model.Giocatore;

public class Inizializzazione {

	private Giocatore[] giocatori;
	private int numGiocatori;
	private GestoreOutput gestoreOutput;
	private GestoreInput gestoreInput;
	private Scanner sc;

	private static List<Colore> COLORI_RESTANTI;

	public Inizializzazione() {
		gestoreOutput = new GestoreOutput();
		gestoreInput = new GestoreInput();
		sc = new Scanner(System.in); // TODO: usare il gestore input?
		COLORI_RESTANTI = Arrays.asList(Colore.BLU, Colore.ROSSO, Colore.GIALLO, Colore.VERDE);
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
				gestoreOutput.stampa("Colori disponibili: " + COLORI_RESTANTI);
				gestoreOutput.stampa("Scegli il colore del giocatore " + (i + 1));
				String col = sc.nextLine().toUpperCase();

				try {
					colore = Colore.valueOf(col);
					if (!COLORI_RESTANTI.contains(colore)) {
						gestoreOutput.stampa("Scelta del colore non possibile.");
						colore = null;
					}
				} catch (IllegalArgumentException e) {
					gestoreOutput.stampa("Colore non valido. Riprova.");
				}
			} while (colore == null);
			COLORI_RESTANTI.remove(colore);

			giocatori[i] = new Giocatore(nome, colore);
		}

		sc.close();
		return giocatori;
	}
}