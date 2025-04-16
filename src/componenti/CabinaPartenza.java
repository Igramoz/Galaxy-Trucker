package componenti;

import java.util.List;
import java.util.Map;

import model.Giocatore;
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
		this(colore, null);				
	}
	
	public CabinaPartenza(Colore colore, List<TipoPedina> equipaggioIniziale) {
		super(TipoComponente.CABINA_PARTENZA, tubiIniziali, equipaggioIniziale);
		
		if(!Giocatore.coloriDisponibiliGiocatori.contains(colore)) {
            throw new IllegalArgumentException("Colore non valido. Deve essere uno tra: " + Giocatore.coloriDisponibiliGiocatori);
		}
		
		this.colore = colore;
	}
	
	public CabinaPartenza(CabinaPartenza cabinaPartenza) { // costruttore di copia
		this( cabinaPartenza.colore, cabinaPartenza.getEquipaggio());
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
