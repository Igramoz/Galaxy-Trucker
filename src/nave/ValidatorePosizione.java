package nave;

import java.util.Map;
import java.util.EnumMap;

import componenti.*;
import model.Coordinate;
import model.enums.Direzione;
import model.enums.TipoTubo;

public interface ValidatorePosizione {
	// Si occupa di controllare se una è possibile insrire un oggetto in una
	// determinata posizione della nave

	public default boolean valida(Componente[][] griglia, Componente nuovoComponente, Coordinate coord) {

		Map<Direzione, Componente> adiacenti = getComponentiAdiacenti(griglia, coord);

		// Controllo che siano presenti dei pezzi attorno
		if (checkNull(adiacenti))
			return false;

		// Itero in ogni direzione
		for (Map.Entry<Direzione, Componente> entry : adiacenti.entrySet()) {

			// Se il componente è presente controllo i tubi 
			if (entry.getValue() != null) {
				TipoTubo tuboNuovoComponente = nuovoComponente.getTubo(entry.getKey());
				TipoTubo tuboComponenteAdiacente = entry.getValue().getTubo(entry.getKey().opposta());

				// Controllo i tubi attorno al componente
				if (!tuboNuovoComponente.isCompatibileCon(tuboComponenteAdiacente)) {
					return false;
				}
			}
		}
		
		if(!controllaCannoniOMotoriAttorno(adiacenti)) return false;

		// Controllo i casi particolari
		return isPosizionamentoValidoPerCannoniEMotori(adiacenti, nuovoComponente);
	}

	private Map<Direzione, Componente> getComponentiAdiacenti(Componente[][] griglia, Coordinate coord) {

		Map<Direzione, Componente> adiacenti = new EnumMap<>(Direzione.class);

		// Coord del componente
		int x = coord.getX();
		int y = coord.getY();

		adiacenti.put(Direzione.SOPRA, griglia[x][y - 1]);
		adiacenti.put(Direzione.SINISTRA, griglia[x - 1][y]);
		adiacenti.put(Direzione.SOTTO, griglia[x][y + 1]);
		adiacenti.put(Direzione.DESTRA, griglia[x + 1][y]);

		return adiacenti;
	}

	// Restituisce vero se tutti i componenti sono null
	private boolean checkNull(Map<Direzione, Componente> adiacenti) {

		for (Map.Entry<Direzione, Componente> entry : adiacenti.entrySet()) {
			if (entry.getValue() != null)
				return false;
		}

		return true;
	}

	// Nel caso in cui viene posizionato un cannone o un motore controlla i pezzi adiacenti
	private boolean isPosizionamentoValidoPerCannoniEMotori(Map<Direzione, Componente> adiacenti, Componente nuovoComponente) {

		if (nuovoComponente.getTipo() == TipoComponente.CANNONE_SINGOLO
				|| nuovoComponente.getTipo() == TipoComponente.CANNONE_DOPPIO) {
			// Il cannone non può aver nulla nella direzione in cui spara
			Direzione direzioneFuoco = ((Cannone) nuovoComponente).getDirezioneFuoco();
			if (adiacenti.get(direzioneFuoco) != null)
				return false;
		}

		if (nuovoComponente.getTipo() == TipoComponente.MOTORE_SINGOLO
				|| nuovoComponente.getTipo() == TipoComponente.MOTORE_DOPPIO) {
			// Il motore non può aver nulla nella direzione verso cui è orientato
			Direzione direzioneMotore = ((Motore) nuovoComponente).getDirezioneMotore();
			if (adiacenti.get(direzioneMotore) != null)
				return false;
		}

		return true;
	}
	
	// restituisce vero se è posizionabile
	private boolean controllaCannoniOMotoriAttorno(Map<Direzione, Componente> adiacenti) {
	    // Controllo specifico per i motori sopra
	    Componente sopra = adiacenti.get(Direzione.SOPRA);
	    if (sopra != null && 
	        (sopra.getTipo() == TipoComponente.MOTORE_SINGOLO || sopra.getTipo() == TipoComponente.MOTORE_DOPPIO)) {
	        return false; // se sopra ha un motore non è posizionabile
	    }

	    // Controllo i cannoni
	    for (Map.Entry<Direzione, Componente> entry : adiacenti.entrySet()) {
	        Componente comp = entry.getValue();

	        if (comp != null && 
	            (comp.getTipo() == TipoComponente.CANNONE_SINGOLO || comp.getTipo() == TipoComponente.CANNONE_DOPPIO)) {
	            Direzione direzioneFuoco = ((Cannone) comp).getDirezioneFuoco();

	            // Se il cannone punta verso il pezzo non è posizionabile
	            if (direzioneFuoco.opposta() == entry.getKey()) {
	                return false;
	            }
	        }
	    }
	    return true;
	}

	
	
}
