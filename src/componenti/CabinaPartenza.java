package componenti;

import java.util.List;
import java.util.Map;
import model.enums.*;
import model.equipaggio.TipoPedina;

public class CabinaPartenza extends CabinaDiEquipaggio{
	// TODO: dovremo capire come assegnare il colore a questa cabina



	private static final Map<Direzione, TipoTubo> tubiIniziali = Map.of(
		Direzione.SOPRA, TipoTubo.UNIVERSALE,
		Direzione.SOTTO, TipoTubo.UNIVERSALE,
		Direzione.DESTRA, TipoTubo.UNIVERSALE,
		Direzione.SINISTRA, TipoTubo.UNIVERSALE
	);
	
	public CabinaPartenza() {
		super(tubiIniziali);
				
	}
	
	public CabinaPartenza(List<TipoPedina> equipaggioIniziale) {
		super(tubiIniziali, equipaggioIniziale);
		
		for (TipoPedina pedina : equipaggioIniziale) {
			if(pedina != TipoPedina.ASTRONAUTA) {
				throw new IllegalStateException("Nella cabina di partenza possono esserci solo astronauti");
			}
		}
	}
	
	public CabinaPartenza(CabinaPartenza cabinaPartenza) { // costruttore di copia
		this(cabinaPartenza.getEquipaggio());
	}
	

	@Override
    public CabinaPartenza clone() {    	
    	return new CabinaPartenza(this); 
    }

}
