package componenti;

import java.util.Map;

import model.enums.*;

public class CabinaPartenza extends Componente{ // TODO: exteds CabinaDiEquipaggio
	
	private static int istanze = 0;
	private int equipaggio; // non serve, già il padre Cabina di equipaggio ha tutte le funzionalità che servono a questa classe
	// TODO: dovremo capire come assegnare il colore a questa cabina
	
	
	public CabinaPartenza(Map<Direzione, TipoTubo> tubiIniziali) {
		super(TipoComponente.CABINA_PARTENZA, tubiIniziali);
		if(istanze >= TipoComponente.CABINA_PARTENZA.getMaxIstanze() || checkTubi(tubiIniziali)) {
			throw new IllegalStateException("Limite massimo di istanze raggiunto per CabinaPartenza");
		}
		
		this.equipaggio = 2;
		incrementaIstanze();
	}
	
	// TODO: questa funzione non va bene, devi sfruttare la funzione del padre che si occupa di asegnare i membri dell'equipaggio
	// ma prima controlla che i membri dell'equipaggio siano tutti ASTRONAUTI
	public CabinaPartenza(Map<Direzione, TipoTubo> tubiIniziali, int equipaggio) {
		super(TipoComponente.CABINA_PARTENZA, tubiIniziali);
		if(istanze >= TipoComponente.CABINA_PARTENZA.getMaxIstanze() || checkTubi(tubiIniziali)) {
			throw new IllegalStateException("Limite massimo di istanze raggiunto per CabinaPartenza");
		}
		
		if(equipaggio > 2 || equipaggio < 0) {
			throw new IllegalArgumentException("L'equipaggio deve essere compreso tra 1 e 2");
		}
		incrementaIstanze();
	}
	
	public CabinaPartenza(CabinaPartenza cabinaPartenza) { // costruttore di copia
		super(cabinaPartenza);
		// TODO: prima di decrementare le istanze, controlla che venga chiamata una funzione che le aggiorna.
	}
	
	// TODO: non serve sapere quanti sono i membri dell'equipaggio, serve sapere quali sono
	public int getEquipaggio() {
		return this.equipaggio;
	}
	
	// TODO: il padre ha una fuznione che se ne occupa, questa non serve
	public boolean decrementaEquipaggio() {
		if(this.equipaggio > 0) {
			this.equipaggio--;
			return true;
		} return false;
	}
	
	// TODO: il padre ha una fuznione che se ne occupa, questa non serve
	public boolean incrementaEquipaggio() {
		if(this.equipaggio < 2) {
			this.equipaggio++;
			return true;
		} return false;
	}
		
	@Override
    public CabinaPartenza clone() {    	
    	return new CabinaPartenza(this); 
    }
    
    @Override
    public  int getIstanze() {
    	
    	return istanze;
    	
    }

    @Override
	public  void resetIstanze() {
		istanze=0;
	}
    
    @Override
	protected  void incrementaIstanze() {
		istanze++;
	}

    @Override
	protected  void decrementaIstanze() {
		istanze--;
	}
    
    
    // TODO: la classe componente si occupa di controllare i tubi, cancella
    //controllo se tutti i tubi sono di tipo universale
    private boolean checkTubi(Map<Direzione, TipoTubo> tubiIniziali) {
    	
    	for(Direzione direzione : Direzione.values()) {
			if(tubiIniziali.get(direzione) != TipoTubo.UNIVERSALE) {
				return true;
			}
		}
		return false;
		
	}
}
