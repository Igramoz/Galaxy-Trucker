package fasidigioco;

import java.util.Scanner;

import io.GestoreOutput;
import model.Giocatore;

public class Inizializzazione {

	private Giocatore[] giocatori;
	private int numGiocatori;
	private GestoreOutput gestoreOutput;

	public Inizializzazione() {
		this.gestoreOutput = new GestoreOutput();
	}
	
	public void start() {
		gestoreOutput.stampa("Inserisci il numero dei giocatori (minimo due, massimo quattro)");
		do {
			Scanner sc = new Scanner(System.in);
			numGiocatori = sc.nextInt();
			if (numGiocatori < 2 || numGiocatori > 4)
				gestoreOutput.stampa("Inserisci il numero dei giocatori (minimo due, massimo quattro)");
		} while (numGiocatori < 2 || numGiocatori > 4);

		giocatori = new Giocatore[numGiocatori];
	}
}