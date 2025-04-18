package util;

import model.*;
import model.enums.Direzione;

import java.util.Set;
import java.util.List;

public class Util {

	public final static int SIZE = 10; // Le coordinate vanno da 0 a 10, i dadi vanno da 2 a 12.

	// Controlla se nel set di coordinate è presente una coordinata. True se è
	// presente
	public static boolean contieneCoordinata(List<Set<Coordinate>> listaCoordinateComponentiControllati,
			Coordinate coordinate) {

		Set<Coordinate> set;
		// Controllo un set della lista alla volta
		for (int i = 0; i < listaCoordinateComponentiControllati.size(); i++) {

			set = listaCoordinateComponentiControllati.get(i);
			if (set.contains(coordinate)) {
				return true;
			}
		}
		return false;
	}
	
	// ordina un generico T1 in ordine in base alle direzioni
	public static <T1> void ordinaPerDirezione(List<Coppia<T1, Direzione>> lista) {

		int n = lista.size();

		// Bubble sort
		for (int i = 0; i < n - 1; i++) {

			// Scorro la lista fino all'elemento nel posto sbagliato
			for (int j = 0; j < n - i - 1; j++) {
				// Conftonto la coppia di elementi
				Coppia<T1, Direzione> c1 = lista.get(j);
				Coppia<T1, Direzione> c2 = lista.get(j + 1);

				// Scambio le coppie nell'ordine sbagliato
				if (c1.getElemento2().ordinal() > c2.getElemento2().ordinal()) {
					// Scambio delle coppie
					lista.set(j, c2);
					lista.set(j + 1, c1);
				}

			}
		}
	}
}
