package io;

import java.util.Scanner;

import grafica.GraficaConfig;
import grafica.TextAligner;
import util.Coordinate;

public class GestoreIO {

	private final Scanner scanner = new Scanner(System.in);

	public int leggiIntero() {
		while (true) {
			try {
				int numero = scanner.nextInt();
				scanner.nextLine(); // Pulisce l'input
				return numero;
			} catch (java.util.InputMismatchException e) {
				stampa("Errore: Per favore inserire un numero intero valido.");
				scanner.nextLine(); // Consuma l'input errato
			}
		}
	}

	public String leggiTesto() {
		// TODO: si potrebbe aggiungere un parametro boolean nel caso in cui ci servisse
		// mantenere gli spazi nella stringa letta
		String input;
		do {
			input = scanner.nextLine().trim();
			if (input.isEmpty()) {
				stampa("Errore: Inserire un testo non vuoto."); // anche se l'utente scrive solo spazi
			}
		} while (input.isEmpty());
		return input;
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

			if (valore < MIN_VALUE || valore > MAX_VALUE)
				stampa("Valore non valido. Riprova");

		} while (valore < MIN_VALUE || valore > MAX_VALUE);
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
		String[] menuStampare = new String[menu.length];
		// in modo automatico stampa il numerp abbinato all'azione corrispondente
		for (int i = 0; i < menu.length; i++) {
			menuStampare[i] = i + " - " + menu[i];
		}

		do {
			sceltaValida = true;
			stampa(menuStampare);
			stampa("Inserire il numero corrispondente all'azione:");

			scelta = leggiIntero();

			if (scelta < 0 || scelta >= menu.length) {
				stampa("Inserire un numero compreso tra 0 e " + (menu.length - 1));
				sceltaValida = false;
			}

		} while (!sceltaValida);
		return scelta;
	}
	
	// funzione per fare scegliere all'utente un valore di un enum
	public <T extends Enum<T>> T leggiEnum(Class<T> enumClass) {
		T[] enumConstants = enumClass.getEnumConstants();
		String[] menu = new String[enumConstants.length];
		for (int i = 0; i < enumConstants.length; i++) {
			menu[i] = enumConstants[i].name();
		}
		int scelta = stampaMenu(menu);
		return enumConstants[scelta];
	}
	
}
