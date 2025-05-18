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
				if (c != null && isCorridoio(c))
					listaComponentiCorridoio.add(c);
			}
		}

//		int maxLunghezza = 0;
//		for (Componente c : listaComponentiCorridoio) {
//			int temp = calcolaLunghezzaCorridoio(c);
//			if (temp > maxLunghezza)
//				maxLunghezza = temp;
//		}
//		return maxLunghezza;
		return calcolaLunghezzaCorridoio(n, listaComponentiCorridoio);
	}

	// un componente è un corridoio se il numero dei suoi tubi è esattamente 1 o 2
	private boolean isCorridoio(Componente c) {
		int numTubi = 0;
		Map<Direzione, TipoTubo> tubi = c.getTubi();
		for (Direzione d : Direzione.values()) {
			if (tubi.get(d) == TipoTubo.NESSUNO)
				numTubi++;
		}

		return numTubi == 1 || numTubi == 2;
	}

	// calcola e restituisce la lunghezza max del corridoio del componente
	private int calcolaLunghezzaCorridoio(Nave n, List<Componente> listaComponentiCorridoio) {
//		List<Coordinate> coordinateGiaEsaminate = new ArrayList<>();
		int lunghezza = 0;
		for (Componente c : listaComponentiCorridoio) {
			Map<Direzione, Componente> adiacenti = n.getCopiaComponentiAdiacenti(c.getPosizione());
			// TODO finire
		}
		return lunghezza;
	}
}