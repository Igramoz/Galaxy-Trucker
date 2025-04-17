package util;

public class Coppia <T1, T2> {
	// Classe che mi permette di salvare 2 valori in una lista come se fossere un arraybidimensionale
	public final T1 elemento1;
	public final T2 elemento2;
	
	 // Costruttore pubblico per permettere la creazione dell'oggetto
    public Coppia(T1 elemento1, T2 elemento2) {
        this.elemento1 = elemento1;
        this.elemento2 = elemento2;
    }
    
    // Metodi getter (opzionali, ma utili se non vuoi accedere direttamente ai campi)
    public T1 getElemento1() {
        return elemento1;
    }

    public T2 getElemento2() {
        return elemento2;
    }
	
}
