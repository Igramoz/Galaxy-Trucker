package componenti;

import model.equipaggio.TipoPedina;
import model.enums.Direzione;
import model.enums.TipoTubo;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CabinaDiEquipaggio extends Componente {
	
    
    private final List<TipoPedina> equipaggio;

    
    public CabinaDiEquipaggio(Map<Direzione, TipoTubo> tubiIniziali) {
        super(TipoComponente.CABINA_EQUIPAGGIO, tubiIniziali);

        

        this.equipaggio = new ArrayList<>();
        
    }

    public CabinaDiEquipaggio(Map<Direzione, TipoTubo> tubiIniziali, List<TipoPedina> equipaggioIniziale) {
        this(tubiIniziali);

        for (TipoPedina pedina : equipaggioIniziale) {
            aggiungiEquipaggio(pedina);
        }
    }

    public CabinaDiEquipaggio(CabinaDiEquipaggio altra) {
        this(altra.tubi, altra.equipaggio);
    }

    // TODO: controlla che sia possibile aggiungere o meno la pedina (possono esserci al massimo un alieno o 2 ASTRONAUTI
    // dopo aver sistemato l'enum ti basterà fare: equipaggio.add(pedina)
    public boolean aggiungiEquipaggio(TipoPedina pedina) {
    	
    	
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

