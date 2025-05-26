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

	public MastroCorridoista() {
		super();
	}

	/**
	 * La funzione stabilisce qual è il giocatore la cui nave contiene il maggior
	 * numero di componenti con almeno una merce presente.
	 * 
	 * @param giocatori: lista dei giocatori valutabili
	 * @return out: il giocatore secondo il criterio già citato
	 */
	@Override
	public Giocatore valutaTitolo(List<Giocatore> giocatori) {
		Giocatore out = giocatori.get(0);
		for (Giocatore g : giocatori) {
			if (getLunghezzaCorridoio(g.getNave()) > getLunghezzaCorridoio(out.getNave()))
				out = g;
		}
		return out;
	}

	/**
	 * Controlla se ogni componente della nave sia un corridoio o meno, e ottiene la
	 * max lunghezza del corridoio trovato.
	 * 
	 * @param n: nave del giocatore
	 * @return maxLunghezza trovata
	 */
	private int getLunghezzaCorridoio(Nave n) {
		List<Componente> listaComponentiCorridoio = new ArrayList<>();
		Componente[][] griglia = n.getGrigliaComponentiCloni();
		for (Componente[] riga : griglia) {
			for (Componente c : riga) {
				if (isCorridoio(c))
					listaComponentiCorridoio.add(c);
			}
		}

		int maxLunghezza = 0;
		for (Componente c : listaComponentiCorridoio) {
			List<Coordinate> coordinateGiaEsaminate = new ArrayList<>();
			int lunghezza = calcolaLunghezzaCorridoio(n, c, coordinateGiaEsaminate);
			if (lunghezza > maxLunghezza)
				maxLunghezza = lunghezza;
		}
		return maxLunghezza;
	}

	/**
	 * Controlla se un componente è un corridoio, cioè se il numero dei suoi tubi è
	 * esattamente 1 o 2
	 * 
	 * @param c: componente da controllare
	 * @return false se è null, oppure se i suoi tubi non sono nè 1 nè 2
	 */
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

	/**
	 * Calcola e restituisce la lunghezza max del corridoio trovato
	 * 
	 * @param n:               nave del giocatore
	 * @param c:               componente corridoio
	 * @param checkCoordinate: lista delle coordinate dei componenti che sono già
	 *                         stati interamente controllati, necessaria per non
	 *                         conteggiare più volte lo stesso componente
	 * @return maxLunghezza + 1: ogni ricorsione di questa funzione aumenta di 1
	 *         questa funzione
	 */
	private int calcolaLunghezzaCorridoio(Nave n, Componente c, List<Coordinate> checkCoordinate) {
		Map<Direzione, Componente> adiacenti = n.getCopiaComponentiAdiacenti(c.getPosizione());
		int maxLunghezza = 0;
		checkCoordinate.add(c.getPosizione());

		for (Direzione d : Direzione.values()) {
			Componente adiacente = adiacenti.get(d);
			if (isCorridoio(adiacente) && !checkCoordinate.contains(adiacente.getPosizione())) {
				// copio la lista per evitare interferenze tra rami
				List<Coordinate> copiaEsaminate = new ArrayList<>(checkCoordinate);
				int lunghezza = calcolaLunghezzaCorridoio(n, adiacente, copiaEsaminate);
				if (lunghezza > maxLunghezza)
					maxLunghezza = lunghezza;
			}
		}
		return maxLunghezza + 1;
	}
}