package services;

import model.*;
import util.Util;
import java.util.HashSet;
import java.util.Set;

public class EventService {
    
    public void distruggiComponenti(Nave nave, Coordinate coordinate) {

        // Distruggo il componente colpito
        nave.distruggiSingoloComponente(coordinate);

        Coordinate coordinateDiPartenza = new Coordinate(7, 7);
        // Controllo quali componenti sono rimasti collegati
        Set<Coordinate> coordinateComponentiControllati = new HashSet<>();
        controllaConnessioneComponente(nave, coordinateDiPartenza, coordinateComponentiControllati);

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