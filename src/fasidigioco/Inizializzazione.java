package fasidigioco;

import java.util.Scanner;

import model.Giocatore;
import grafica.GestoreGrafica;

public class Inizializzazione {

	private Giocatore[] giocatori;
	private int numGiocatori;
	private GestoreGrafica gestoreGrafica;

	public Inizializzazione() {
		this.gestoreGrafica = new GestoreGrafica();
	}
	
	public void start() {
		gestoreGrafica.stampa("Inserisci il numero dei giocatori (minimo due, massimo quattro)");
		do {
			Scanner sc = new Scanner(System.in);
			numGiocatori = sc.nextInt();
			if (numGiocatori < 2 || numGiocatori > 4)
				gestoreGrafica.stampa("Inserisci il numero dei giocatori (minimo due, massimo quattro)");
		} while (numGiocatori < 2 || numGiocatori > 4);

		giocatori = new Giocatore[numGiocatori];
	}
}