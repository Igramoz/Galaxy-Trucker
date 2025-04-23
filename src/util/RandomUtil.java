package util;

import java.util.Map;

import model.enums.Direzione;

public class RandomUtil {

	// escludere max
	public int randomInt(int min, int max) {
		// Restituisce un numero intero casuale compreso tra min e max (escluso)
		return (int) (Math.random() * (max - min)) + min;
	}

	public int randomInt(int max) {
		// Restituisce un numero intero casuale compreso tra 0 e max (escluso)
		return randomInt(0, max);
	}

	// Genera una direzione randomicamente
	public Direzione randomDirezione() {
		Direzione[] valori = Direzione.values();
		return valori[randomInt(valori.length)];
	}

	// Genera randomicamente un enum tra 2 opzioni
	public <E extends Enum<E>> E getEnumValueByProbability(Map<E, Integer> collezione) {
		int somma = 0;
		for (int num : collezione.values()) {
			somma += num;
		}
		int random = randomInt(somma);

		int totale = 0;
		for (Map.Entry<E, Integer> entry : collezione.entrySet()) {
			totale += entry.getValue();
			if (random < totale) {
				return entry.getKey();
			}
		}
		throw new IllegalStateException("Si è verificato un errore imprevisto.");
	}

//	// Genera randomicamente un enum tra 2 opzioni
//    public <E extends Enum<E>> E randomEnum(int soglia1, int soglia2, int soglia3, int totale, E primo, E secondo, E terzo, E quarto) {
//    	
//        int numeroCasuale = randomInt(totale); // Genera un numero tra 0 e totale        
//        return numeroCasuale < soglia ? primo : secondo; // se num < soglia allora genera il primo
//    }

}
