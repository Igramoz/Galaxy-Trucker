package model.nave;

import grafica.renderer.NaveRenderer;
import io.GestoreIO;
import java.util.ArrayList;
import java.util.List;
import model.componenti.Componente;
import model.enums.Direzione;
import model.enums.TipoTubo;
import util.Coordinate;
import util.Util;

//

public class Distruttore {
	// TODO fare in modo che vengano stampati i tronconi e che l'utente possa
	// sceglierli.
	
	private Nave nave;
	private Coordinate coordinate;
	private int pezziDistrutti;
	
	public Distruttore(Nave nave, Coordinate coordinate) {
		
		this.nave = nave;
		this.coordinate = coordinate;
		
	}
	
	public int distruggiComponenti() {
		pezziDistrutti = 1;
		
		nave.distruggiSingoloComponente(coordinate);

		List<List<Coordinate>> tronconiNave = new ArrayList<>();

		// controllo dalle 4 posizioni che circondano il componente distrutto

		Coordinate[] coordinateControllo = { new Coordinate(coordinate.getX(), coordinate.getY() - 1),
				new Coordinate(coordinate.getX(), coordinate.getY() + 1),
				new Coordinate(coordinate.getX() - 1, coordinate.getY()),
				new Coordinate(coordinate.getX() + 1, coordinate.getY()) };

		for (Coordinate coord : coordinateControllo) {
			// controllo che la coordinata non sia già stata salvata
			if (Util.contieneCoordinata(tronconiNave, coord))
				continue;
			
			// scansiono il troncone
			List<Coordinate> nuovaLista = new ArrayList<>();
			controllaConnessioneComponente(nave, coord, nuovaLista);

			if (!nuovaLista.isEmpty())
				tronconiNave.add(nuovaLista);
		}
		
		Nave[] tronconi = generaTronconi(nave, tronconiNave);

		// Se si crea un solo troncone non c'è bisogno di scegliere quale salvare
		if(tronconi.length <= 1) {
			return pezziDistrutti;
		}
		
		int tronconeDaTenere = scegliTroncone(tronconi);

		for (int i = 0; i < tronconi.length; i++) {
			if (i != tronconeDaTenere) {
				pezziDistrutti += distruggiTroncone(nave, tronconiNave.get(i));
			}
		}
		return pezziDistrutti;
		
	}

	private void controllaConnessioneComponente(Nave nave, Coordinate coordinate, List<Coordinate> visitati) {
		Componente[][] griglia = nave.getGrigliaComponentiCloni();
		Componente c = griglia[coordinate.getX()][coordinate.getY()];

		if (c == null || visitati.contains(coordinate))
			return;

		visitati.add(coordinate);

		for (Direzione dir : Direzione.values()) {
			controllaConnessioneInDirezione(nave, c, coordinate, dir, visitati);
		}
	}

	private void controllaConnessioneInDirezione(Nave nave, Componente c, Coordinate coord, Direzione dir,
			List<Coordinate> visitati) {
		if (c.getTubo(dir) == TipoTubo.NESSUNO)
			return;

		int dx = 0, dy = 0;
		switch (dir) {
		case SOPRA -> dy = -1;
		case SOTTO -> dy = 1;
		case SINISTRA -> dx = -1;
		case DESTRA -> dx = 1;
		}

		Coordinate next = new Coordinate(coord.getX() + dx, coord.getY() + dy);
		controllaConnessioneComponente(nave, next, visitati);
	}

	private Nave[] generaTronconi(Nave nave, List<List<Coordinate>> coordinateTronconi) {
		Nave[] tronconi = new Nave[coordinateTronconi.size()];
		for (int i = 0; i < coordinateTronconi.size(); i++) {
			tronconi[i] = new Nave(nave.getLivelloNave());
			for (Coordinate coord : coordinateTronconi.get(i)) {
				Componente comp = nave.getCopiaComponente(coord);
				tronconi[i].forzaComponente(comp, coord); // Posiziono il componente senza controlli
			}
		}
		return tronconi;
	}

	// conta quanti elementi distrugge
	private int distruggiTroncone(Nave nave, List<Coordinate> daDistruggere) {
		for (Coordinate coord : daDistruggere) {
			nave.distruggiSingoloComponente(coord);
		}
		return daDistruggere.size();
	}

	private int scegliTroncone(Nave[] tronconi) {
		GestoreIO io = new GestoreIO();
		NaveRenderer renderer = new NaveRenderer();

		io.stampa("La nave si distruggerà nei seguenti tronconi: ");

		for (int i = 0; i < tronconi.length; i++) {
			io.stampa("TRONCONE NUMERO: " + i);
			io.stampa(renderer.rappresentazioneNave(tronconi[i]));
		}

		io.stampa("Inserire il numero corrispondente al troncone che si vuole salvare ");

		int scelta;
		do {
			scelta = io.leggiIntero();
			if (scelta >= 0 && scelta < tronconi.length) {
				break;
			} else {
				io.stampa("Inserire un numero compresto tra 0 e " + (tronconi.length - 1));
			}

		} while (true);
		return scelta;
	}
}
