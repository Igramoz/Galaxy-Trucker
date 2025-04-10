package model;

import componenti.Componente;
import componenti.CabinaPartenza;
import util.*;
import servizi.ServizioDistruzioneComponenti;
import grafica.Colore;
import model.enums.TipoNave;

public class Nave {
	
    private Componente[][] grigliaComponenti; 
    private final TipoNave livelloNave;
    
    // Costruttore
    public Nave(TipoNave livelloNave) {
        grigliaComponenti = new Componente[Util.SIZE][Util.SIZE];
        this.livelloNave = livelloNave;
    }
    
    
    // Costruttore
    public Nave(TipoNave livelloNave, Colore colore) {
    	this(livelloNave);
    	grigliaComponenti[Util.SIZE/2][Util.SIZE/2] = new CabinaPartenza(colore);
        
    }
    
    // Costruttore di copia
    public Nave(Nave naveOriginale) {
    	this(  naveOriginale.livelloNave, ((CabinaPartenza) naveOriginale.grigliaComponenti[Util.SIZE/2][Util.SIZE/2]).getColore());
    	for(int i = 0; i < Util.SIZE; i++)
    		for(int j = 0; j < Util.SIZE; j++) {
    			this.grigliaComponenti[i][j] = naveOriginale.grigliaComponenti[i][j].clone();
    		}    	
    }
    
    @Override
    public Nave clone() {
    	return new Nave(this);
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
        if(!livelloNave.isPosizionabile(coordinate)) {
            throw new IllegalArgumentException("La posizione " + coordinate + " non è posizionabile nella nave.");
        }
    	grigliaComponenti[coordinate.getX()][coordinate.getY()] = componente;
    }
    
    public TipoNave getLivelloNave() {
		return livelloNave;
	}   
    
    
    // Il metodo può essere chiamto solo dalla funzione distruggiComponenti della classe EventService
    public void distruggiSingoloComponente(Coordinate coordinate) {
    	 if (isCalledByEventService()) {
             if (grigliaComponenti[coordinate.getX()][coordinate.getY()] != null) {
                 grigliaComponenti[coordinate.getX()][coordinate.getY()] = null;
                 //TODO aggiornare il numero di pezzi distrutti nel giocatore
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