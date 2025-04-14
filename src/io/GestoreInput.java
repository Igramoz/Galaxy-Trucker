package io;

import java.util.Scanner;

public class GestoreInput {

	private final Scanner scanner = new Scanner(System.in);
	private final GestoreOutput gestoreOutput = new GestoreOutput();

	public int leggiIntero() {
		while (true) {
			try {
				return scanner.nextInt();
			} catch (java.util.InputMismatchException e) {
				gestoreOutput.stampa("Errore: Per favore inserire un numero intero valido.");
				scanner.nextLine(); // Consuma l'input non valido
			}
		}
	}

}
