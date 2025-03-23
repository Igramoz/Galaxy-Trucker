package services;

import model.*;
import componenti.Componente;
import util.*;

import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

public class EventService {
    
    public void distruggiComponenti(Nave nave, Coordinate coordinate) {

        // Distruggo il componente colpito
        nave.distruggiSingoloComponente(coordinate);      

        // Salvo tutti i tronconi che si formano dopo la distruzione del pezzo in una lista di set.
        // La lista è di tipo Set che è a sua volta di tipo Coordinate
        // <> con quest'espressione il compilatore automaticamente capisce il tipo della lista
        // List è un interfaccia fornita dalla classe ArrayList
        List<Set<Coordinate>> listaCoordinateControllate = new ArrayList<>();
        
        Set<Coordinate> nuovoSet; // Set che verrà aggiunto alla lista

        Coordinate cordinateDaControllare;// Controllo se a queste coordinate c'è un componente e se è già stato controllato 
        
        // Aggiungo ai set tutti i pezzi della nave cosicché possa controllare tutti i tronconi
        // Ogni set è un troncone
        for(int x =0; x < 12; x++)
        	for(int y = 0; y < 12; y++) {
        		cordinateDaControllare = new Coordinate(x, y);
        		
        		// Controllo se la coordinata è già stata aggiunta ad un set
        		if(!Util.contieneCoordinata(listaCoordinateControllate, cordinateDaControllare)) {
        	        
        			nuovoSet = new HashSet<>(); // Creo il set per il nuovo troncone
        			
        			// Assegno a nuovo set le coordinate di tutti i pezzi del troncone
        			controllaConnessioneComponente(nave, cordinateDaControllare, nuovoSet);

        			// Controllo se il set contiene almeno un elemento e aggiungo alla lista
        			if (!nuovoSet.isEmpty()) {
        			    listaCoordinateControllate.add(nuovoSet);
        			}        			
        		}        		
        	}
        
        // Creo un array di navi per far vedere all'utente ciascun troncone.
        Nave[] array
        
        //TODO creare array di navi, una per ogni troncone, stamparle a video, chiedere all'utente quale nave vuole tenere, distruggere tutti i tronconi diversi da quella che ha tenuto
        // Distruggo i componenti non collegati
        distruggiComponentiNonCollegate(nave, coordinateComponentiControllati);
    }

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
        controllaConnessioneInDirezione(nave, componente, coordinate, Componente.DirezioneTubo.SOPRA, coordinateComponentiControllati);
        controllaConnessioneInDirezione(nave, componente, coordinate, Componente.DirezioneTubo.SINISTRA, coordinateComponentiControllati);
        controllaConnessioneInDirezione(nave, componente, coordinate, Componente.DirezioneTubo.SOTTO, coordinateComponentiControllati);
        controllaConnessioneInDirezione(nave, componente, coordinate, Componente.DirezioneTubo.DESTRA, coordinateComponentiControllati);
    }

    private void controllaConnessioneInDirezione(Nave nave, Componente componente, Coordinate coordinate, Componente.DirezioneTubo direzione, Set<Coordinate> coordinateComponentiControllati) {
        if (componente.getTubo(direzione) == TipoTubo.NESSUNO) {
            return;
        }

        int deltaX = 0, deltaY = 0;
        if (direzione == Componente.DirezioneTubo.SOPRA) deltaY = -1;
        else if (direzione == Componente.DirezioneTubo.SOTTO) deltaY = 1;
        else if (direzione == Componente.DirezioneTubo.SINISTRA) deltaX = -1;
        else if (direzione == Componente.DirezioneTubo.DESTRA) deltaX = 1;

        // chiamo ricorsivamente la funzione per controllare il componente successivo
        Coordinate prossimoComponente = new Coordinate(coordinate.getX() + deltaX, coordinate.getY() + deltaY);
        controllaConnessioneComponente(nave, prossimoComponente, coordinateComponentiControllati);
    }

    private void distruggiComponentiNonCollegate(Nave nave, Set<Coordinate> coordinateComponentiControllati) {
        Componente[][] grigliaComponenti = nave.getGrigliaComponenti();
        for (int x = 0; x < Util.SIZE; x++) {
            for (int y = 0; y < Util.SIZE; y++) {
                Coordinate coord = new Coordinate(x, y);
                if (grigliaComponenti[x][y] != null && !coordinateComponentiControllati.contains(coord)) {
                    nave.distruggiSingoloComponente(coord);
                }
            }
        }
    }
    
    
}
// TODO al posto di eliminare le celle non salvate, posso provare a generare un'array di set. cioè a generare un'altro set dove salvare gli elementi distrutti non attaccati alla cabina di 
// pilotaggio. In questo modo risolverei anche il caso in cui essa è distrutta. Bisogna chiedere all'utente quale tra i vari set tenere, e dopo si distruggono tutti gli elementi presenti negli altri set.