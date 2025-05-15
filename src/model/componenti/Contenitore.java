package model.componenti;

import eccezioni.ComponentePienoException;
import eccezioni.ComponenteVuotoException;

public interface Contenitore<T> {
	void aggiungi(T elemento) throws ComponentePienoException;

	boolean rimuovi(T elemento) throws ComponenteVuotoException;

	boolean isEmpty();

	boolean isFull();
}