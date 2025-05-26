package util;

import java.util.Objects;

/**
 * Classe generica che contiene una coppia di oggetti, utilizzata in sostituzione agli array paralleli
 * La classe è utile per appaiare valori.
 * 
 * @param <T1> il tipo del primo elemento della coppia
 * @param <T2> il tipo del secondo elemento della coppia
 */
public class Coppia<T1, T2> {
	// Classe che mi permette di salvare 2 valori in una lista come se fossere un
	// arraybidimensionale
	public final T1 elemento1;
	public final T2 elemento2;

	// Costruttore pubblico per permettere la creazione dell'oggetto
	public Coppia(T1 elemento1, T2 elemento2) {
		this.elemento1 = elemento1;
		this.elemento2 = elemento2;
	}

	// Metodi getter (opzionali, ma utili se non vuoi accedere direttamente ai
	// campi)
	public T1 getElemento1() {
		return elemento1;
	}

	public T2 getElemento2() {
		return elemento2;
	}

	@Override
	public int hashCode() {
		return Objects.hash(elemento1, elemento2);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coppia<?, ?> other = (Coppia<?, ?>) obj;
		return Objects.equals(elemento1, other.elemento1) && Objects.equals(elemento2, other.elemento2);
	}
}
