package componenti;

import model.enums.Direzione;
import model.enums.TipoPedina;
import model.enums.TipoTubo;
import java.util.*;

public class CabinaDiEquipaggio extends Componente {
	
    
    private final List<TipoPedina> equipaggio;

    
    public CabinaDiEquipaggio(Map<Direzione, TipoTubo> tubiIniziali) {
        this(TipoComponente.CABINA_EQUIPAGGIO, tubiIniziali, null);       
    }

    protected CabinaDiEquipaggio(TipoComponente tipoComponente, Map<Direzione, TipoTubo> tubiIniziali, List<TipoPedina> equipaggioIniziale) {
        super(tipoComponente, tubiIniziali);

        this.equipaggio = new ArrayList<>();
        if(equipaggioIniziale == null) { return; }
        
        for (TipoPedina pedina : equipaggioIniziale) {
            
        	if(!aggiungiEquipaggio(pedina)) {
				throw new IllegalStateException("La cabina è piena");
        	}
        }
    }

    public CabinaDiEquipaggio(CabinaDiEquipaggio altra) {
        this(altra.tipo, altra.tubi, altra.equipaggio);
    }

    public boolean aggiungiEquipaggio(TipoPedina pedina) {
    	
    	// Se c'è un alieno non si può inserire altro
    	if(equipaggio.contains(TipoPedina.ALIENO_MARRONE) || equipaggio.contains(TipoPedina.ALIENO_VIOLA)) {
    		return false;
		// Se ci sono 2 astronauti non si può inserire altro
    	}else if( Collections.frequency(equipaggio, TipoPedina.ASTRONAUTA) > 1) {
    		return false;
    	// Se c'è un astronauta non si può aggiungere un alieno
    	}else if(equipaggio.size() >= 1 && (pedina == TipoPedina.ALIENO_MARRONE || pedina == TipoPedina.ALIENO_VIOLA))
    		return false;
    		
        equipaggio.add(pedina);
        return true;
    }

    
    public boolean rimuoviEquipaggio(TipoPedina pedina) {
        return equipaggio.removeIf(p -> p == pedina);
    }

    public List<TipoPedina> getEquipaggio() {
        return new ArrayList<>(equipaggio);
    }

    @Override
    public CabinaDiEquipaggio clone() {
        return new CabinaDiEquipaggio(this);
    }
}

