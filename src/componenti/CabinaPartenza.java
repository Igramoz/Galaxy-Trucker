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
		super(TipoComponente.CABINA_PARTENZA, tubiIniziali, equipaggioIniziale);
		
		this.colore = colore;
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
    	return super.aggiungiEquipaggio(pedina);
    }
	

	@Override
    public CabinaPartenza clone() {    	
    	return new CabinaPartenza(this); 
    }
	
	public Colore getColore() {
		return colore;
	}

}
