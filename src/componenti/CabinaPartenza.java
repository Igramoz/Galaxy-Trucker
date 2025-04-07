package componenti;

import java.util.List;
import java.util.Map;
import model.enums.*;
import model.equipaggio.TipoPedina;
import grafica.Colore;

public class CabinaPartenza extends CabinaDiEquipaggio{

	final Colore colore;

	private static final Map<Direzione, TipoTubo> tubiIniziali = Map.of(
		Direzione.SOPRA, TipoTubo.UNIVERSALE,
		Direzione.SOTTO, TipoTubo.UNIVERSALE,
		Direzione.DESTRA, TipoTubo.UNIVERSALE,
		Direzione.SINISTRA, TipoTubo.UNIVERSALE
	);
	
	public CabinaPartenza(Colore colore) {
		this(null, colore);				
	}
	
	public CabinaPartenza(List<TipoPedina> equipaggioIniziale, Colore colore) {
		super(tubiIniziali, equipaggioIniziale);
		
		this.colore = colore;
		for (TipoPedina pedina : equipaggioIniziale) {
			if(pedina != TipoPedina.ASTRONAUTA) {
				throw new IllegalStateException("Nella cabina di partenza possono esserci solo astronauti");
			}
		}
	}
	
	public CabinaPartenza(CabinaPartenza cabinaPartenza) { // costruttore di copia
		this(cabinaPartenza.getEquipaggio(), cabinaPartenza.colore);
	}
	
	@Override
    public boolean aggiungiEquipaggio(TipoPedina pedina) {
    	
		// La cabina di partenza ospita solo astronauti
    	if(pedina != TipoPedina.ASTRONAUTA) {
    		return false;
    	}
    		super.aggiungiEquipaggio(pedina);
        return true;
    }
	

	@Override
    public CabinaPartenza clone() {    	
    	return new CabinaPartenza(this); 
    }
	
	public Colore getColore() {
		return colore;
	}

}
