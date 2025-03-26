package componenti;

import java.util.Map;

import model.enums.*;

public class Stiva extends Componente {

	public static final int LimiteIstanziabili = 24;
	public static int istanze = 0;
	
	private final boolean speciale; // true se può ospitare merce rossa.
	private final int scomparti; // numero di scomparti della stiva
	private TipoMerce[] merci; // lista delle merci presenti nella stiva


    public Stiva(Map<Direzione, TipoTubo> tubiIniziali, int scomparti, boolean speciale) {
        super(TipoComponente.STIVA, tubiIniziali);

        if (istanze >= LimiteIstanziabili) {
            throw new IllegalStateException("Limite massimo di istanze raggiunto per Stiva");
        }
        // Controllo che abbia un numero di scomparti valido
        if (speciale) {
            if (scomparti < 1 || scomparti > 2) {
                throw new IllegalArgumentException("Le stive speciali devono avere 1 o 2 scomparti.");
            }
        } else {
            if (scomparti < 2 || scomparti > 3) {
                throw new IllegalArgumentException("Le stive normali devono avere 2 o 3 scomparti.");
            }
        }
        
        this.scomparti = scomparti;
        this.speciale = speciale;
        this.merci = new TipoMerce[scomparti];
        
        incrementaIstanze();
    }

	// Costruttore di copia
	public Stiva(Stiva stiva) {
		this(stiva.tubi, stiva.scomparti, stiva.speciale);
		for (int i = 0; i < scomparti; i++) {
			this.merci[i] = stiva.merci[i];
		}
	}
	
	@Override
	public Componente creaCopia() {
		// TODO decrementare istanze?
		return new Stiva(this);		
	}
	
   @Override
	public void incrementaIstanze() {
		istanze++;
	}
	
	public TipoMerce[] getMerci() {
		// Genero una copia dell'array di merci
		TipoMerce[] copiaMerci = new TipoMerce[scomparti];
		
		for (int i = 0; i < scomparti; i++) {
			copiaMerci[i] = merci[i];
		}
		
		return copiaMerci;
	}

	// Restituisco false se non è possibile aggiungere la merce
	public boolean setMerci(TipoMerce merce) {
		// Controllo se la stiva è piena
		if(stivaPiena()) {
			return false;
		}
		
		// Controllo se la merce può essere immagazzinata in stiva
		if(TipoMerce.ROSSO.equals(merce) && !speciale) {
			return false;
		}
		
		// Carico la merce
		for(int i = 0; i < scomparti; i++) {
			if(merci[i] == null) {
				merci[i] = merce;
				return true;
			}
		}
		return false; // Non può arrivare qui
	}
	
	// Restituisco true se la stiva è piena
	private boolean stivaPiena() {
		for (int i = 0; i < scomparti; i++) {
			if (merci[i] == null) {
				return false;
			}
		}
		return true;
	}
	
	// Elimino la merce dalla stiva, false se non è presente
	public boolean eliminaMerci(TipoMerce merce) {	
		
		// Controllo se la merce è presente
		for (int i = 0; i < scomparti; i++) {
			if (merci[i] == merce) {
				merci[i] = null;
				return true;
			}
		}
		return false;
	}
	
	// Elimino la merce dalla stiva, false se non è presente
	public boolean eliminaMerci(int index) {
		
		// Controllo se l'indice è accettabile
		if(index < 0 || index >= scomparti) {
			return false;
		}
		
		// Controllo se la merce è presente
		if(merci[index] != null) {
			merci[index] = null;
			return true;
		}else {
			return false;
		}		
	}
	
	// Calcolo il valore della merce trasportata
	public int valoreMerci() {
		int valore = 0;
		for (int i = 0; i < scomparti; i++) {
			if (merci[i] != null) {
				valore += merci[i].getValore();
			}
		}
		return valore;
	}
		
	@Override
	public int getIstanze() {
		return istanze;
	}
	
	
	public int getScomparti() {
		return scomparti;
	}
	
	public boolean isSpeciale() {
		return speciale;
	}	
}
