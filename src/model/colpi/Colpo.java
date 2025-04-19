package model.colpi;

import componenti.Componente;
import model.Coordinate;
import model.enums.Direzione;
import nave.Nave;
import util.Util;

public interface Colpo {

	void aggiornaComponentiDistrutti();
	
	// Restituisce null se la nave non viene colpita
	default Coordinate calcolaCoordinateColpite(Nave nave, Direzione direzione, int coordinata) {
		
		Componente[][] griglia = nave.getGrigliaComponenti();
		
		switch(direzione) {
		case SOPRA:
			for(int i = 0; i < Util.SIZE; i++) {
				if(griglia[coordinata][i] != null) {
					return new Coordinate(coordinata, i);
				}
			}
            break;
		case SOTTO:
			for(int i = Util.SIZE - 1; i >= 0 ; i--) {
				if(griglia[coordinata][i] != null) {
					return new Coordinate(coordinata, i);
				}
			}
            break;
		case SINISTRA:
			for(int i = 0; i < Util.SIZE; i++) {
				if(griglia[i][coordinata] != null) {
					return new Coordinate(i, coordinata);
				}
			}
	        break;
		case DESTRA:
            for (int i = Util.SIZE - 1; i >= 0; i--) {
				if(griglia[i][coordinata] != null) {
					return new Coordinate(i, coordinata);
				}
			}
	        break;
		}
		return null; // nessun componente lungo quella direzione
	}
}
