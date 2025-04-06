package util;

public class RandomUtil {

	// TODO: escludere max
	public int randomInt(int min, int max) {
		// Restituisce un numero intero casuale compreso tra min e max (escluso)
	    return (int) (Math.random() * (max - min)) + min;
	}
	
	public int randomInt(int max) {
		// Restituisce un numero intero casuale compreso tra 0 e max (escluso)
		return randomInt(0, max);
	}
	
}
