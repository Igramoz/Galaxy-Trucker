package model;

import componenti.Componente;
import util.*;
import servizi.ServizioDistruzioneComponenti;

public class Nave {
    
    private Componente[][] grigliaComponenti; 

    
    // Costruttore
    public Nave() {
        grigliaComponenti = new Componente[Util.SIZE][Util.SIZE];
    }
    
    // Costruttore di copia
    public Nave(Nave naveOriginale) {
    	this();
    	for(int i = 0; i < Util.SIZE; i++)
    		for(int j = 0; j < Util.SIZE; j++) {
    			this.grigliaComponenti[i][j] = naveOriginale.grigliaComponenti[i][j].clone();
    		}    	
    }
    
    public Nave clone() {
    	Nave clone = new Nave();
    	for(int i = 0; i < Util.SIZE; i++)
    		for(int j = 0; j < Util.SIZE; j++) {
    			clone.grigliaComponenti[i][j] = this.grigliaComponenti[i][j].clone();
    		} 
    	return clone;
    }
    
    // Metodi griglia componenti
    public Componente[][] getGrigliaComponenti() {
        Componente[][] copiaGrigliaComponenti = new Componente[Util.SIZE][Util.SIZE];
        for (int x = 0; x < Util.SIZE; x++) {
            for (int y = 0; y < Util.SIZE; y++) {
                if (grigliaComponenti[x][y] != null) {
                    copiaGrigliaComponenti[x][y] = grigliaComponenti[x][y].clone();
                }
            }
        }       
        return copiaGrigliaComponenti;
    }
    
    public Componente getComponente(Coordinate coordinate) {
    	Componente copiaComponente = grigliaComponenti[coordinate.getX()][coordinate.getY()].clone();
        return copiaComponente;
    }
    
    public void setComponente(Componente componente, Coordinate coordinate) {
        grigliaComponenti[coordinate.getX()][coordinate.getY()] = componente;
    }
    
    
    
    
    
    // Il metodo può essere chiamto solo dalla funzione distruggiComponenti della classe EventService
    public void distruggiSingoloComponente(Coordinate coordinate) {
    	 if (isCalledByEventService()) {
             if (grigliaComponenti[coordinate.getX()][coordinate.getY()] != null) {
                 grigliaComponenti[coordinate.getX()][coordinate.getY()] = null;
//                 incrementaPezziDistrutti(); //TODO aggiornare il numero di pezzi distrutti nel giocatore
             }
         } else {
             throw new SecurityException("Accesso non autorizzato al metodo distruggiSingoloComponente");
         }
    }
    
    private boolean isCalledByEventService() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement element : stackTrace) {
            if (element.getClassName().equals(ServizioDistruzioneComponenti.class.getName())) {
                return true;
            }
        }
        return false;
    }
}