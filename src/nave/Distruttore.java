package nave;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import componenti.Componente;
import model.Coordinate;
import model.enums.Direzione;
import model.enums.TipoTubo;
import util.Util;

public interface Distruttore {
	// TODO fare in modo che vengano stampati i tronconi e che l'utente possa sceglierli.
	  default void distruggiComponenti(Nave nave, Coordinate coordinate) {
	        nave.distruggiSingoloComponente(coordinate);

	        List<Set<Coordinate>> tronconiNave = new ArrayList<>();

	        for (int x = 0; x < Util.SIZE; x++) {
	            for (int y = 0; y < Util.SIZE; y++) {
	                Coordinate coord = new Coordinate(x, y);
	                if (Util.contieneCoordinata(tronconiNave, coord)) continue;

	                Set<Coordinate> nuovoSet = new HashSet<>();
	                controllaConnessioneComponente(nave, coord, nuovoSet);

	                if (!nuovoSet.isEmpty()) tronconiNave.add(nuovoSet);
	            }
	        }

	        Nave[] tronconi = generaTronconi(nave, tronconiNave);

	        int tronconeDaTenere = 0;

	        for (int i = 0; i < tronconi.length; i++) {
	            if (i != tronconeDaTenere) {
	                distruggiTroncone(nave, tronconiNave.get(i));
	            }
	        }
	    }

	    private void controllaConnessioneComponente(Nave nave, Coordinate coordinate, Set<Coordinate> visitati) {
	        Componente[][] griglia = nave.getGrigliaComponenti();
	        Componente c = griglia[coordinate.getX()][coordinate.getY()];

	        if (c == null || visitati.contains(coordinate)) return;

	        visitati.add(coordinate);

	        for (Direzione dir : Direzione.values()) {
	            controllaConnessioneInDirezione(nave, c, coordinate, dir, visitati);
	        }
	    }

	    private void controllaConnessioneInDirezione(Nave nave, Componente c, Coordinate coord, Direzione dir, Set<Coordinate> visitati) {
	        if (c.getTubo(dir) == TipoTubo.NESSUNO) return;

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

	    private Nave[] generaTronconi(Nave nave, List<Set<Coordinate>> coordinateTronconi) {
	        Nave[] tronconi = new Nave[coordinateTronconi.size()];
	        for (int i = 0; i < coordinateTronconi.size(); i++) {
	            tronconi[i] = new Nave(nave.getLivelloNave());
	            for (Coordinate coord : coordinateTronconi.get(i)) {
	                Componente comp = nave.getComponente(coord);
	                tronconi[i].setComponente(comp, coord);
	            }
	        }
	        return tronconi;
	    }

	    private void distruggiTroncone(Nave nave, Set<Coordinate> daDistruggere) {
	        for (Coordinate coord : daDistruggere) {
	            nave.distruggiSingoloComponente(coord);
	        }
	    }
}
