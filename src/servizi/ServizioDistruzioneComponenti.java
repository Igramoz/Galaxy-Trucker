package servizi;

import model.*;
import model.enums.*;
import componenti.Componente;
import util.*;

import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

public class ServizioDistruzioneComponenti {
	/**Distruggo un componente della nave e verifica i tronconi che si formano.
     * Permette al giocatore di scegliere quale troncone tenere e distrugge gli altri. */         
    public void distruggiComponenti(Nave nave, Coordinate coordinate) {

        // Distruggo il componente colpito
        nave.distruggiSingoloComponente(coordinate);      

        // Identifico i tronconi
        List<Set<Coordinate>> tronconiNave= new ArrayList<>();
                
        // Aggiungo ai set tutti i pezzi della nave cosicché possa controllare tutti i tronconi
        // Ogni set è un troncone
        for (int x = 0; x < Util.SIZE; x++) {
            for (int y = 0; y < Util.SIZE; y++) {
                Coordinate coord = new Coordinate(x, y);
                if (Util.contieneCoordinata(tronconiNave, coord)) continue;

                Set<Coordinate> nuovoSet = new HashSet<>();
                controllaConnessioneComponente(nave, coord, nuovoSet);

                if (!nuovoSet.isEmpty()) tronconiNave.add(nuovoSet);
            }
        }
                
        // Creo una nave per ciascun troncone.
        Nave[] tronconi= generaTronconi(nave,tronconiNave);
        
        //TODO STAMPARE CIASCUN TRONCONE A VIDEO
        
        int tronconeDaTenere=0; //TODO fare scegliere all'utente quale troncone tenere. CONTROLLARE SE IL NUMERO è 0 BASED O 1 BASED     
        
                
        // Distruggo i tronconi scartati
        for(int indiceTroncone = 0; indiceTroncone < tronconi.length; indiceTroncone++) {
        	if(indiceTroncone != tronconeDaTenere)
        		distruggiTroncone(nave, tronconiNave.get(indiceTroncone));
        }        
    }

    
    /** Controlla la connessione di un componente e raccoglie le coordinate
     * di tutti i componenti collegati al suo troncone. */     
    private void controllaConnessioneComponente(Nave nave, Coordinate coordinate, Set<Coordinate> coordinateComponentiControllati) {
        Componente[][] grigliaComponenti = nave.getGrigliaComponenti();
        Componente componente = grigliaComponenti[coordinate.getX()][coordinate.getY()];
        
        // Se non c'è nessun componente o se è già stato visitato
        if (componente == null || coordinateComponentiControllati.contains(coordinate)) {
            return;
        }
        // Salvo le coordinate del componente così non passiamo più volte nello stesso punto
        coordinateComponentiControllati.add(coordinate);

        // Controlla connessioni nelle quattro direzioni
        for (Direzione direzione : Direzione.values()) {
            controllaConnessioneInDirezione(nave, componente, coordinate, direzione, coordinateComponentiControllati);
        } 
    }

    // Verifica se un componente è connesso ad altri componenti nelle quattro direzioni.
    private void controllaConnessioneInDirezione(Nave nave, Componente componente, Coordinate coordinate, Direzione direzione, Set<Coordinate> coordinateComponentiControllati) {
        if (componente.getTubo(direzione) == TipoTubo.NESSUNO) {
            return;
        }

     // Determina le nuove coordinate in base alla direzione
        int deltaX = 0, deltaY = 0;
        switch (direzione) {
            case Direzione.SOPRA:    deltaY = -1; break;
            case Direzione.SOTTO:    deltaY = 1; break;
            case Direzione.SINISTRA: deltaX = -1; break;
            case Direzione.DESTRA:   deltaX = 1; break;
        }

        // chiamo ricorsivamente la funzione per controllare il componente successivo
        Coordinate prossimoComponente = new Coordinate(coordinate.getX() + deltaX, coordinate.getY() + deltaY);
        controllaConnessioneComponente(nave, prossimoComponente, coordinateComponentiControllati);
    }

    // Genera una lista di navi, ognuna delle quali rappresenta un troncone della nave originale.
    private Nave[] generaTronconi(Nave naveOriginale, List<Set<Coordinate>> listaCoordinateControllate) {
    	
    	int numTronconi = listaCoordinateControllate.size();    	   	
    	Nave[] tronconi = new Nave[numTronconi];
    	
    	// Creo ogni troncone
    	for(int indiceTroncone = 0; indiceTroncone < numTronconi; indiceTroncone++) {
    		
    		tronconi[indiceTroncone] = new Nave();    		
    		
            // Copio i componenti nel nuovo troncone
    		for (Coordinate coord : listaCoordinateControllate.get(indiceTroncone)) {
                Componente componente = naveOriginale.getComponente(coord);
                tronconi[indiceTroncone].setComponente(componente, coord);
            }    		
    	}    	
    	return tronconi;    			
    }
    
    // Distrugge un intero troncone dalla nave originale
    private void distruggiTroncone(Nave nave, Set<Coordinate> coordinateComponentiDaDistruggere) {
    	for (Coordinate coord : coordinateComponentiDaDistruggere) {
            nave.distruggiSingoloComponente(coord);
        }
    }

}
