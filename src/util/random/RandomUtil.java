package util.random;

import java.util.Map;

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

	// Genera un valore casuale da un enum generico
	public <T extends Enum<T>> T randomEnum(Class<T> enumClass) {
		T[] values = enumClass.getEnumConstants();
		return values[randomInt(values.length)];
	}

	/**
	 * Genera randomicamente un valore di un dato enum tramite un numero casuale
	 * generato partendo dalla somma di tutti i valori.
	 * 
	 * @param map: insieme delle coppie chiave-valore, dove l'elemento chiave è il
	 *             valore di un enum, mentre il valore corrisponde ad una quantità.
	 * @return E: l'elemento scelto
	 */
	public <E extends Enum<E>> E getEnumValueByProbability(Map<E, Integer> map) {
		int somma = 0;
		for (int num : map.values()) {
			somma += num;
		}
		int random = randomInt(somma);

		int totale = 0;
		for (Map.Entry<E, Integer> entry : map.entrySet()) {
			totale += entry.getValue();
			if (random < totale) {
				return entry.getKey();
			}
		}
		throw new IllegalStateException("Si è verificato un errore imprevisto.");
	}
}
