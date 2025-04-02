package componenti;

import java.util.Map;
import java.util.List;
import model.equipaggio.TipoPedina;

import model.enums.*;

public class CabinaPartenza extends CabinaDiEquipaggio{
	
	private static int istanze = 0;
	// TODO: dovremo capire come assegnare il colore a questa cabina
	
	
	public CabinaPartenza(Map<Direzione, TipoTubo> tubiIniziali) {
		super(tubiIniziali);
		if (istanze >= TipoComponente.CABINA_PARTENZA.getMaxIstanze() || checkTubi(tubiIniziali)) {
			throw new IllegalStateException("Limite massimo di istanze raggiunto per CabinaPartenza");
		}
		
		incrementaIstanze();
	}
	
	// TODO: questa funzione non va bene, devi sfruttare la funzione del padre che si occupa di asegnare i membri dell'equipaggio
	// ma prima controlla che i membri dell'equipaggio siano tutti ASTRONAUTI
	public CabinaPartenza(Map<Direzione, TipoTubo> tubiIniziali, List<TipoPedina> equipaggioIniziale) {
		super(tubiIniziali, equipaggioIniziale);
		if(istanze >= TipoComponente.CABINA_PARTENZA.getMaxIstanze() || checkTubi(tubiIniziali)) {
			throw new IllegalStateException("Limite massimo di istanze raggiunto per CabinaPartenza");
		}
		for (TipoPedina pedina : equipaggioIniziale) {
			if(pedina.getColore() != TipoPedina.Colore.ASTRONAUTA) {
				throw new IllegalStateException("Nella cabina di partenza possono esserci solo astronauti");
			}
		}
		incrementaIstanze();
	}

	
	
	public CabinaPartenza(CabinaPartenza cabinaPartenza) { // costruttore di copia
		this(cabinaPartenza.tubi, cabinaPartenza.getEquipaggio());
		// TODO: prima di decrementare le istanze, controlla che venga chiamata una funzione che le aggiorna.
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
	public void incrementaIstanze() {
		istanze++;
	}

    @Override
	public  void decrementaIstanze() {
		istanze--;
	}
    
    
    // TODO: la classe componente si occupa di controllare i tubi, cancella
    
    private boolean checkTubi(Map<Direzione, TipoTubo> tubiIniziali) {
    	
    	for(Direzione direzione : Direzione.values()) {
			if(tubiIniziali.get(direzione) != TipoTubo.UNIVERSALE) {
				return true;
			}
		}
		return false;
		
	}
}
