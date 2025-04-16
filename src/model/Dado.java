package model;

import util.RandomUtil;

public class Dado {
	private static final Dado[] istanze = new Dado[2]; // Array per un massimo di 2 istanze
	private static int count = 0; // Contatore delle istanze create

	private int valore; // Valore del dado

	// Costruttore privato
	private Dado() {
		this.valore = lancia(); // Inizializza con un valore casuale
	}

	// Metodo statico per ottenere un'istanza
	public static Dado getIstanza() {
		if (count < 2) {
			istanze[count] = new Dado();
			count++;
		} else {
			return istanze[count % 2];

		}
	}

	// Metodo per simulare il lancio del dado
	public int lancia() {
		valore = RandomUtil.randomInt(1, 7); // Restituisce un numero casuale tra 1 e 6
		return valore;
	}

	// Getter per il valore del dado
	public int getValore() {
		return valore;
	}

}