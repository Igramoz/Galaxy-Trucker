package nave;

import componenti.Componente;
import componenti.CabinaPartenza;
import util.*;
import grafica.Colore;
import model.Coordinate;

public class Nave implements Distruttore {
	
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
                    copiaGrigliaComponenti[x][y] = grigliaComponenti[x][y];
                }
            }
        }       
        return copiaGrigliaComponenti;
    }
    
    public Componente getComponente(Coordinate coordinate) {
    	Componente copiaComponente = grigliaComponenti[coordinate.getX()][coordinate.getY()].clone();
        return copiaComponente;
    }
        
    public TipoNave getLivelloNave() {
		return livelloNave;
	}   
    
    public void distruggiComponente(Coordinate coordinate) {
    	Distruttore distruzioneComponenti = new Distruttore() {};
        distruzioneComponenti.distruggiComponenti(this, coordinate);
    }
    
    // Il metodo può essere chiamto solo dalla funzione distruggiComponenti della classe EventService
    protected void distruggiSingoloComponente(Coordinate coordinate) {
             if (grigliaComponenti[coordinate.getX()][coordinate.getY()] != null) {
                 grigliaComponenti[coordinate.getX()][coordinate.getY()] = null;
                 //TODO aggiornare il numero di pezzi distrutti nel giocatore
             }
    }
    
    public boolean setComponente(Componente componente, Coordinate coordinate) {
    	
    	// Controllo se il tipo di nave ammette componenti in quella posizione
    	if(!livelloNave.isPosizionabile(coordinate)) {
            return false;
        }
    	
    	// Controllo se il pezzo si collega agli altri
    	ValidatorePosizione validatore = new ValidatorePosizione() {};
    	if (validatore.valida(grigliaComponenti, componente, coordinate)) {
            grigliaComponenti[coordinate.getX()][coordinate.getY()] = componente;
            return true;
        }
        return false;
    }
    
}