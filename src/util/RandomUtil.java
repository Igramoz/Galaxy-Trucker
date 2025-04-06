package util;

public class RandomUtil {

	// TODO: escludere max
	public int randomInt(int min, int max) {
		// Restituisce un numero intero casuale compreso tra min e max (inclusi)
		return (int) (Math.random() * (max - min + 1)) + min;
	}
	
	public int randomInt(int max) {
		// Restituisce un numero intero casuale compreso tra 0 e max (inclusi)
		return randomInt(0, max);
	}
	
}
