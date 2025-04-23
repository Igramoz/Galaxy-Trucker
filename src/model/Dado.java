package model;

import grafica.FormattatoreGrafico;
import io.GestoreIO;
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
		}
		Dado dado = istanze[count % 2];
		count++;
		return dado;
	}

	// Metodo per simulare il lancio del dado
	public int lancia() {
		RandomUtil random = new RandomUtil();
		valore = random.randomInt(1, 7); // Restituisce un numero casuale tra 1 e 6
		return valore;
	}

	// Getter per il valore del dado
	public int getValore() {
		return valore;
	}
	
    // funzione per lanciare i dadi (giocatore che lancia i dadi)
    public static int lancia2Dadi(Giocatore giocatore) {
    	GestoreIO io = new GestoreIO();
    	FormattatoreGrafico formattatoreGrafico = new FormattatoreGrafico();
    	
    	Dado dado1 = Dado.getIstanza();
    	Dado dado2 = Dado.getIstanza();
    	
    	io.stampa( formattatoreGrafico.formattaGiocatore(giocatore) + " premi un tasto per tirare i 2 dadi");
    	io.leggiTesto();
    	
    	int num1 = dado1.lancia();
    	int num2 = dado2.lancia();
    	int somma = num1 + num2;
    	io.stampa("Sono usciti i numeri: " + num1 + " e " + num2);
    	io.stampa("La somma dei dadi risulta: " + somma);
    	
    	return somma - 2;  // i dadi partono da 1  	
    }

}