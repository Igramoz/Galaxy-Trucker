package util;

import java.util.List;

import util.layout.Coordinate;

public class Util {

	public final static int SIZE = 11; // Le coordinate vanno da 0 a 10, i dadi vanno da 2 a 12.

	// isEmpty ma per gli array
	public static <T> boolean isArrayEmpty(T[] array) {
	    // L'array è null
		if (array == null) {
	        return true; 
	    }
		// contiene un'elemento
	    for (T element : array) {
	        if (element != null) {
	            return false; 
	        }
	    }
	    return true;
	}
	
	// Controlla se nel set di coordinate è presente una coordinata. True se è
		// presente
		public static boolean contieneCoordinata(List<List<Coordinate>> listaCoordinateComponentiControllati,
				Coordinate coordinate) {

			// Controllo una lista della lista alla volta
			for (List<Coordinate> listaIesima : listaCoordinateComponentiControllati) {
			    if (listaIesima.contains(coordinate)) {
			        return true;
			    }
			}
			return false;
		}
}
