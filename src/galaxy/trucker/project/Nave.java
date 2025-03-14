package galaxy.trucker.project;

import java.util.HashSet;
import java.util.Set;


public class Nave {
	
	private Componente[][] grigliaComponenti;
	
	
	public Nave() {
        grigliaComponenti = new Componente[12][12];
    }
	
	
	
	public void distruggiComponenti(Coordinate coordinate) {
		//TODO gestire il caso in cui ad essere disrtutta è la cabina di partenza
		
		// Distruggo il componente colpito
		distruggiSingoloComponente (coordinate);
		
		// Controllo quali componenti sono rimasti collegati 
        Set<Coordinate> coordinateComponentiControllati = new HashSet<>();
        controllaConnessioneComponente(coordinate, coordinateComponentiControllati);
        
        // Distruggo i componenti 
	}
	
	private void distruggiSingoloComponente(Coordinate coordinate) {
		
        Componente componente = grigliaComponenti[coordinate.x][coordinate.y];

        // Controllo se e' presente un componente in quelle coordinate
        if (componente == null) {
            return;
        }        
        // Distruggo il componente
        grigliaComponenti[coordinate.x][coordinate.y] = null;

        // Controlla le connessioni dei componenti circostanti
        controllaConnessioneComponente(coordinate);
	}
	
	
	private void controllaConnessioneComponente(Coordinate coordinate, Set<Coordinate> coordinateComponentiControllati) {

		// Imposto come vero l'essere gia passato da componente
		Componente componente = new Componente(grigliaComponenti[coordinate.x][coordinate.y]);
		TipoTubo tipoTuboComponente = componente.getTubo(Componente.DirezioneTubo.SOPRA);
		Coordinate coordinateProssimoComponente = new Coordinate(coordinate.getX(), coordinate.getY());

		//controllo se è presente un componente in quelle coordinate
		if(componente == null){
			return;
		}
		
		// Controllo se e' gia' stato controllato
		if (coordinateComponentiControllati.contains(coordinate)) {
		    return;
		}
		
		// Aggiungo le coordinate al set delle coord dei componenti controllati		
		coordinateComponentiControllati.add(coordinate);
		
		// Controlla le connessioni in tutte le direzioni
        controllaConnessioneInDirezione(coordinate, componente, Componente.DirezioneTubo.SOPRA, componentiVisitati);
        controllaConnessioneInDirezione(coordinate, componente, Componente.DirezioneTubo.SINISTA, componentiVisitati);
        controllaConnessioneInDirezione(coordinate, componente, Componente.DirezioneTubo.SOTTO, componentiVisitati);
        controllaConnessioneInDirezione(coordinate, componente, Componente.DirezioneTubo.DESTRA, componentiVisitati);		
	}	
	
	private void controllaConnessioneInDirezione(Componente componente, Coordinate coordinate, DirezioneTubo direzione, Set<Coordinate> componentiVisitati) {
		

		int deltaX, deltaY;
		// Verifica se il tubo nella direzione specificata è NESSUNO
		if (componente.getTubo(direzione) == TipoTubo.NESSUNO) {
			return;
		}
		
		if (direzione == Componente.DirezioneTubo.SOPRA) {
			deltaY=1;
		}else if( direzione == Componente.DirezioneTubo.SINISTRA) {
			deltaX = -1;
		}else if( direzione == Componente.DirezioneTubo.SOTTO) {
			deltaY = -1;
		}else {
			deltaX = 1;
		}

		// Calcola le coordinate del prossimo componente
		Coordinate coordinateProssimoComponente = new Coordinate(coordinate.getX() + deltaX, coordinate.getY() + deltaY);

		// Esegui la chiamata ricorsiva alla funzione controllaConnessioneComponente
		controllaConnessioneComponente(coordinateProssimoComponente, componentiVisitati);
}
}