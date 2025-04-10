package fasidigioco;

import java.util.Scanner;

import model.Giocatore;

public class Inizializzazione {

	private Giocatore[] giocatori;
	private int numGiocatori;

	public void start() {
		System.out.println("Inserisci il numero dei giocatori (minimo due, massimo quattro)");
		do {
			Scanner sc = new Scanner(System.in);
			numGiocatori = sc.nextInt();
			if (numGiocatori < 2 || numGiocatori > 4)
				System.out.println("Numero giocatori non valido");
		} while (numGiocatori < 2 || numGiocatori > 4);

		giocatori = new Giocatore[numGiocatori];
	}
}