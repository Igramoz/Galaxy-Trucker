package util;

import model.*;
import model.enums.Direzione;

import java.util.List;

public class Util {

	public final static int SIZE = 11; // Le coordinate vanno da 0 a 10, i dadi vanno da 2 a 12.

	// Controlla se nel set di coordinate è presente una coordinata. True se è
	// presente
	public static boolean contieneCoordinata(List<List<Coordinate>> listaCoordinateComponentiControllati,
			Coordinate coordinate) {

		List<Coordinate> listaIesima;
		// Controllo una lista della lista alla volta
		for (int i = 0; i < listaCoordinateComponentiControllati.size(); i++) {

			listaIesima = listaCoordinateComponentiControllati.get(i);
			if (listaIesima.contains(coordinate)) {
				return true;
			}
		}
		return false;
	}
}
