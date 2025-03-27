package util;

import model.*;

import java.util.Set;
import java.util.List;

public class Util {
	
	public final static int SIZE = 10; // Le coordinate vanno da 0 a 10, i dadi vanno da 2 a 12.
	public final static int OFFSET = 2; // Differenza tra numeri visti dall'utente e utilizzati dal computer
	
	// Controlla se nel set di coordinate è presente una coordinata. True se è presente
	public static boolean contieneCoordinata(List<Set<Coordinate>> listaCoordinateComponentiControllati, Coordinate coordinate) {
		
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
}
