package nave;

import java.util.List;

import componenti.Cannone;
import componenti.Componente;
import componenti.TipoComponente;
import model.colpi.Meteorite;
import model.enums.TipoTubo;
import model.enums.Direzione;
import util.Coordinate;
import util.Util;

public interface VerificatoreImpatti {
	// l'interfaccia controlla se il colpo distrugge o meno la nave

	// restituisce calcola le coordinate del componente da distruggere ( coordinata è la x o y lungo la quale si muove il colpo)
	default Coordinate verificaImpatto(Nave nave, Meteorite meteorite, int coordinata) {

		Coordinate coordinateColpite = calcolaCoordinateColpite( nave, meteorite.getDirezione(), coordinata);
		
		if (coordinateColpite == null) {
			return null; // nessun componente lungo quella direzione
		}
		
		// Controllo che tipo di meteorite è
		if (meteorite == Meteorite.PICCOLO) {
			if( meteoritePiccoloColpisce(nave, meteorite.getDirezione(), coordinateColpite) ) {
				return coordinateColpite; 
			}
		} else {
			if( meteoriteGrossoColpisce(nave, meteorite.getDirezione(), coordinateColpite) ) {
				return coordinateColpite; 
			}
		}
		return null;
	}

	// Restituisce null se la nave non viene colpita ( coordinata è la x o y lungo la quale si muove il colpo)
	private Coordinate calcolaCoordinateColpite(Nave nave, Direzione direzione, int coordinata) {

		Componente[][] griglia = nave.getGrigliaComponenti();

		switch (direzione) {
		case SOPRA:
			for (int i = 0; i < Util.SIZE; i++) {
				if (griglia[coordinata][i] != null) {
					return new Coordinate(coordinata, i);
				}
			}
			break;
		case SOTTO:
			for (int i = Util.SIZE - 1; i >= 0; i--) {
				if (griglia[coordinata][i] != null) {
					return new Coordinate(coordinata, i);
				}
			}
			break;
		case SINISTRA:
			for (int i = 0; i < Util.SIZE; i++) {
				if (griglia[i][coordinata] != null) {
					return new Coordinate(i, coordinata);
				}
			}
			break;
		case DESTRA:
			for (int i = Util.SIZE - 1; i >= 0; i--) {
				if (griglia[i][coordinata] != null) {
					return new Coordinate(i, coordinata);
				}
			}
			break;
		}
		return null; // nessun componente lungo quella direzione
	}

	// TODO cannonata
	// default int subisciImpatto(Nave nave, Coppia<TipiMeteorite, Direzione>
	// meteorite, Coordinate coordinate)

	private boolean meteoritePiccoloColpisce(Nave nave, Direzione direzione, Coordinate coordinate) {
		if (nave.getComponente(coordinate).getTubo(direzione) == TipoTubo.NESSUNO)
			return false;

		// Controllo che ci siano degli scudi
		return !nave.attivaScudo(direzione);
	}

	private boolean meteoriteGrossoColpisce(Nave nave, Direzione direzione, Coordinate coordinate) {

		// se il meteorite arriva da davanti può essere fatto espoldere solo con un
		// cannone che punta in avanti nella stessa colonna
		List<Componente> cannoni = nave.getComponenti(TipoComponente.CANNONE_SINGOLO);
		cannoni.addAll(nave.getComponenti(TipoComponente.CANNONE_DOPPIO));

		for (Componente cannone : cannoni) {
			// controllo che il cannone punti nella direzione corretta
			// Verifica direzione corretta
			if (((Cannone) cannone).getDirezioneFuoco() != direzione)
				continue;

			switch (direzione) {
			case SOPRA:
				if (cannone.getPosizione().getX() == coordinate.getX()) {
					return !nave.spara((Cannone) cannone); // se la nave spara, il meteorite non ha colpito
				}
				break;

			case SOTTO:
				if (stessaOAdiacente(cannone.getPosizione().getX(), coordinate.getX())) {
					return !nave.spara((Cannone) cannone);
				}
				break;

			case SINISTRA:
			case DESTRA:
				if (stessaOAdiacente(cannone.getPosizione().getY(), coordinate.getY())) {
					return !nave.spara((Cannone) cannone);
				}
				break;
			}
		}
		return true; // non ci sono cannoni adatti, il meteorite impatta
	}

	private boolean stessaOAdiacente(int a, int b) {
		return a == b || a == b - 1 || a == b + 1;
	}

}
