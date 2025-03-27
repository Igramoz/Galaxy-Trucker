package componenti;

import java.util.Map; // Libreria standard di java
import java.util.EnumMap;

import model.enums.*;

public abstract class Componente {
	
	protected final TipoComponente tipo;
	protected Map <Direzione, TipoTubo> tubi;
	
	
    public Componente(TipoComponente tipo, Map<Direzione, TipoTubo> tubiIniziali) {
		this.tipo = tipo;
        this.tubi = new EnumMap<>(Direzione.class);        
        
        // Inizializza le direzioni con i tipi di tubi specificati
        for (Direzione direzione : Direzione.values()) {        
        	
        	// setto i tubi, se manca la direzione, di default nessuno
            this.tubi.put(direzione, tubiIniziali.getOrDefault(direzione, TipoTubo.NESSUNO)); 
            }
	}
    
    // Costruttore di copia
    public Componente(Componente componente) {
		this(componente.tipo, componente.tubi); // chiama l'altro costruttore 	
    }
    
    // Metodo che dovrà essere Override dalle sottoclassi
    @Override
    public Componente clone() {
    	return null; // la funzione non verrà mai chiamata
    }
    
    
    public boolean equals(Componente altroComponente) {
    	if(this == altroComponente) return true; // confronto i riferimenti
    	if(altroComponente == null || this.tipo != altroComponente.getTipo()) return false; // se l'altro oggetto è null o se sono di tipi diversi 
    	
    	Direzione[] direzione = Direzione.values();
    	
    	int offset = -1; // I tubi sono uguali anche se ruotati di offset volte
    	
    	// Cerco se altroComponente ha un tubo uguale a quello in alto di this
    	for(int i = 0; i < direzione.length; i++) {
			if(this.getTubo(direzione[0]) == altroComponente.getTubo(direzione[i])) {
				offset = i;
				break;
				
			}
		}
		if(offset == -1) return false; // Non hanno neanche un tubo uguale
    	
    	// Gli oggetti sono uguali anche se hanno gli stessi tubi ruotati
    	for (int i = 0; i < direzione.length; i++)
    		if( this.getTubo(direzione[i]) != altroComponente.getTubo(direzione[(i + offset) % direzione.length]) ) // % direzione.length per far ciclare i valori tra 0 e 3
    			return false; // Se dopo l'offset hanno un tubo diverso i 2 componenti non sono uguali
    	
		return true;
	}
    
    public void ruota() {
		// Ruota i tubi in senso antorario
		TipoTubo tempTubo = tubi.get(Direzione.SOPRA);
		tubi.put(Direzione.SOPRA, tubi.get(Direzione.DESTRA));
		tubi.put(Direzione.DESTRA, tubi.get(Direzione.SOTTO));
		tubi.put(Direzione.SOTTO, tubi.get(Direzione.SINISTRA));
		tubi.put(Direzione.SINISTRA, tempTubo);
	}	    
    
    // Metodi astratti per controllare il numero di istanze delle sottoclassi
    public abstract int getIstanze();
    public abstract void resetIstanze();
    protected abstract void incrementaIstanze();
    protected abstract void decrementaIstanze();
    
	public TipoComponente getTipo() {
		return tipo;
	}
	
	
	public TipoTubo getTubo(Direzione direzione) {
		return tubi.get(direzione);
	}
	
	// genera uncopia dei tubi
	public Map<Direzione, TipoTubo> getTubi() {
		Map<Direzione, TipoTubo> copiaTubi = new EnumMap<>(Direzione.class);
		Direzione[] direzione = Direzione.values();
		for (int i = 0; i < direzione.length; i++) {
			tubi.put(direzione[i], this.tubi.get(direzione[i]));
		}		
		return copiaTubi;
	}
	
	public int getMaxIstanze() {
		return tipo.getMaxIstanze();
	}
	
}