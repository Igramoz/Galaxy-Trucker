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

	// Ruota la direzione in senso antiorario
	public static Direzione ruotaDirezione(Direzione direzione) {

		switch (direzione) {
		case SOPRA:
			return Direzione.SINISTRA;
		case SINISTRA:
			return Direzione.SOTTO;
		case SOTTO:
			return Direzione.DESTRA;
		case DESTRA:
			return Direzione.SOPRA;
		default:
			return null;
		}
	}
	
	
	
}
