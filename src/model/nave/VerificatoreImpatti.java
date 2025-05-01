package model.nave;

import java.util.List;

import model.carte.colpo.Colpo;
import model.carte.colpo.Colpo.DimensioniColpo;
import model.carte.colpo.Colpo.TipoColpo;
import model.componenti.Cannone;
import model.componenti.Componente;
import model.componenti.TipoComponente;
import model.enums.TipoTubo;
import util.Util;
import util.layout.Coordinate;
import util.layout.Direzione;

public class VerificatoreImpatti {
	// l'interfaccia controlla se il colpo distrugge o meno la nave

	// VERIFICATORE IMPATTI PER METEORITI

	// restituisce calcola le coordinate del componente da distruggere ( coordinata
	// è la x o y lungo la quale si muove il colpo)
	private GestoreComponenti gestoreComponenti;

	
	private Nave nave;
	
	protected VerificatoreImpatti(Nave nave ) {
		this.nave = nave;
		this.gestoreComponenti = new GestoreComponenti(nave);
	}
	
	
	protected Coordinate verificaImpatto(Colpo colpo, int coordinata) {

		Coordinate coordinateColpite = calcolaCoordinateColpite(nave, colpo.getDirezione(), coordinata);

		if (coordinateColpite == null) {
			return null; // nessun componente lungo quella direzione
		}

		if (colpo.getTipoColpo() == TipoColpo.METEORITE) {
			// Controllo che tipo di meteorite è
			if (colpo.getDimensioniColpo() == DimensioniColpo.PICCOLO) {
				if (meteoritePiccoloColpisce(nave, colpo.getDirezione(), coordinateColpite)) {
					return coordinateColpite;
				}
			} else {
				if (meteoriteGrossoColpisce(nave, colpo.getDirezione(), coordinateColpite)) {
					return coordinateColpite;
				}
			}
		}else {
			// lo scudo difende dalle cannonate piccole
			if (colpo.getDimensioniColpo() == DimensioniColpo.PICCOLO && gestoreComponenti.attivaScudo(colpo.getDirezione())) {
				return null;
			}
			// la cannonata grossa colpisce oggi
			return coordinateColpite;
		}
		return null;
	}

	// Restituisce null se la nave non viene colpita ( coordinata è la x o y lungo
	// la quale si muove il colpo)
	private Coordinate calcolaCoordinateColpite(Nave nave, Direzione direzione, int coordinata) {

		Componente[][] griglia = nave.getGrigliaComponentiCloni();

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

	private boolean meteoritePiccoloColpisce(Nave nave, Direzione direzione, Coordinate coordinate) {
		if (nave.getCopiaComponente(coordinate).getTubo(direzione) == TipoTubo.NESSUNO)
			return false;

		// Controllo che ci siano degli scudi
		return !gestoreComponenti.attivaScudo(direzione);
	}

	private boolean meteoriteGrossoColpisce(Nave nave, Direzione direzione, Coordinate coordinate) {

		// se il meteorite arriva da davanti può essere fatto espoldere solo con un
		// cannone che punta in avanti nella stessa colonna
		List<Componente> cannoni = nave.getCopiaComponenti(TipoComponente.CANNONE_SINGOLO);
		cannoni.addAll(nave.getCopiaComponenti(TipoComponente.CANNONE_DOPPIO));

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
