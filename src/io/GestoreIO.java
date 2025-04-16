package io;

import java.util.Scanner;

import grafica.GraficaConfig;
import grafica.TextAligner;
import model.Coordinate;

public class GestoreIO {
	
	private final Scanner scanner = new Scanner(System.in);
	public int leggiIntero() {
		while (true) {
			try {
				return scanner.nextInt();
			} catch (java.util.InputMismatchException e) {
				stampa("Errore: Per favore inserire un numero intero valido.");
				scanner.nextLine(); // Consuma l'input non valido
			}
		}
	}

	public Coordinate leggiCoordinate() {

		int x, y;
		x = leggiCoordinata("X");
		y = leggiCoordinata("Y");
				
	    return new Coordinate(x - GraficaConfig.OFFSET, y - GraficaConfig.OFFSET);
	}

	// Controlla la coordinata
	private int leggiCoordinata(String nomeCoordinata) {
		
		final int MIN_VALUE = Coordinate.MIN_COORDINATA + GraficaConfig.OFFSET;
		final int MAX_VALUE = Coordinate.MAX_COORDINATA + GraficaConfig.OFFSET;
		
		int valore;
		do {
	        stampa("Inserisci la coordinata " + nomeCoordinata + " (" + MIN_VALUE + "-" + MAX_VALUE + "): ");
			valore = leggiIntero();

			if (valore < MIN_VALUE ||valore> MAX_VALUE)
				stampa("Valore non valido. Riprova");

		} while (valore < MIN_VALUE ||valore> MAX_VALUE);
		return valore;
	}
	
	public void aCapo() {
		stampa("");
	}

	// Metodi da usare per stampare a video
	public void stampa(String riga) {

		if (riga == null) {
			return;
		}

		TextAligner txtAligner = new TextAligner();
		riga = txtAligner.alignLeft(riga) + GraficaConfig.A_CAPO;

		System.out.println(riga);
	}

	public void stampa(String[] righeDaStampare) {

		if (righeDaStampare == null) {
			return;
		}

		for (String riga : righeDaStampare) {
			stampa(riga);
		}
	}

	public int stampaMenu(String[] menu) {
		// stampa il menu e riporta la risposta dell'utente sia compresa tra 0 e
		// menu.length - 1
		int scelta;
		boolean sceltaValida;
		do {
			sceltaValida = true;
			stampa(menu);
			stampa("Inserire il numero corrispondente all'azione:");

			scelta = leggiIntero();

			if(scelta < 0 || scelta >= menu.length) {
				stampa("Inserire un numero compreso tra 0 e " + (menu.length-1));
				sceltaValida = false;
			}
			
		} while (!sceltaValida);
		return scelta;
	}
}
