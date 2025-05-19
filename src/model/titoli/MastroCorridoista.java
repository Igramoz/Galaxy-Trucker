package model.titoli;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.Giocatore;
import model.nave.Nave;
import util.layout.Coordinate;
import util.layout.Direzione;
import model.componenti.Componente;
import model.enums.TipoTubo;

public class MastroCorridoista extends Titolo {

	@Override
	public Giocatore valutaTitolo(List<Giocatore> giocatori) {
		Giocatore out = giocatori.get(0);
		for (Giocatore g : giocatori) {
			if (getLunghezzaCorridoio(g.getNave()) > getLunghezzaCorridoio(out.getNave()))
				out = g;
		}
		return out;
	}

	private int getLunghezzaCorridoio(Nave n) {
		List<Componente> listaComponentiCorridoio = new ArrayList<>();
		Componente[][] griglia = n.getGrigliaComponentiCloni();
		for (Componente[] riga : griglia) {
			for (Componente c : riga) {
				if (isCorridoio(c))
					listaComponentiCorridoio.add(c);
			}
		}

		int maxLunghezza = 1;
		for (Componente c : listaComponentiCorridoio) {
			List<Coordinate> coordinateGiaEsaminate = new ArrayList<>();
			int lunghezza = calcolaLunghezzaCorridoio(n, c, coordinateGiaEsaminate);
			if (lunghezza > maxLunghezza)
				maxLunghezza = lunghezza;
		}
		return maxLunghezza;
	}

	// un componente è un corridoio se il numero dei suoi tubi è esattamente 1 o 2
	private boolean isCorridoio(Componente c) {
		if (c == null)
			return false;

		int numTubi = 0;
		Map<Direzione, TipoTubo> tubi = c.getTubi();
		for (Direzione d : Direzione.values()) {
			if (tubi.get(d) == TipoTubo.NESSUNO)
				numTubi++;
		}

		return numTubi == 1 || numTubi == 2;
	}

	// calcola e restituisce la lunghezza max del corridoio trovato
	private int calcolaLunghezzaCorridoio(Nave n, Componente c, List<Coordinate> coordinateGiaEsaminate) {
		Map<Direzione, Componente> adiacenti = n.getCopiaComponentiAdiacenti(c.getPosizione());
		int maxLunghezza = 1;
		coordinateGiaEsaminate.add(c.getPosizione());

		for (Direzione d : Direzione.values()) {
			Componente adiacente = adiacenti.get(d);
			if (isCorridoio(adiacente) && !coordinateGiaEsaminate.contains(adiacente.getPosizione())) {
				// copio la lista per evitare interferenze tra rami
				List<Coordinate> copiaEsaminate = new ArrayList<>(coordinateGiaEsaminate);
				int lunghezza = calcolaLunghezzaCorridoio(n, adiacente, copiaEsaminate);
				if (lunghezza > maxLunghezza)
					maxLunghezza = lunghezza;
			}
		}
		return maxLunghezza;
	}
}