package nave;

import java.util.List;

import componenti.Cannone;
import componenti.Componente;
import componenti.TipoComponente;
import model.Coordinate;
import model.colpi.TipiMeteorite;
import model.enums.TipoTubo;
import model.enums.Direzione;
import util.Coppia;

public interface VerificatoreImpatti {
	// l'interfaccia controlla se il colpo distrugge o meno la nave

	// restituisce false se il componente non è da distruggere
	default boolean verificaImpatto(Nave nave, Coppia<TipiMeteorite, Direzione> meteorite, Coordinate coordinate) {

		// Controllo che tipo di meteorite è
		if (meteorite.getElemento1() == TipiMeteorite.PICCOLO) {
			return gestisciMeteoritePiccolo(nave, meteorite.getElemento2(), coordinate);
		} else {
			return gestisciMeteoriteGrosso(nave, meteorite.getElemento2(), coordinate);
		}
	}

	// TODO cannonata
	// default int subisciImpatto(Nave nave, Coppia<TipiMeteorite, Direzione>
	// meteorite, Coordinate coordinate) 

	private boolean gestisciMeteoritePiccolo(Nave nave, Direzione direzione, Coordinate coordinate) {
		if (nave.getComponente(coordinate).getTubo(direzione) == TipoTubo.NESSUNO)
			return false;

		// Controllo che ci siano degli scudi
		return !nave.attivaScudo(direzione);
	}

	private boolean gestisciMeteoriteGrosso(Nave nave, Direzione direzione, Coordinate coordinate) {

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
