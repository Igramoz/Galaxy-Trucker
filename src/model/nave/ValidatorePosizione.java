package model.nave;

import java.util.Map;

import model.componenti.*;
import model.enums.TipoTubo;
import util.layout.Coordinate;
import util.layout.Direzione;

public class ValidatorePosizione {
	// Si occupa di controllare se una è possibile insrire un oggetto in una
	// determinata posizione della nave
	
	private final Nave nave;
	
	protected ValidatorePosizione(Nave nave) {
		this.nave = nave;
	}

	public  boolean valida( Componente nuovoComponente, Coordinate coord) {
		Map<Direzione, Componente> adiacenti = nave.getCopiaComponentiAdiacenti(coord);

		// Controllo che non ci sia nessun componente in quella posizione nella nave
		if(nave.getCopiaComponente(coord) == null)
			return false;
		
		// Controllo che siano presenti dei pezzi attorno
		if (checkNull(adiacenti))
			return false;

		boolean isCollegato = false;
		
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
				
				// se il tubo è nessuno è compatibile, ma non è sufficiente per attaccare il pezzo alla nave
				if(tuboNuovoComponente != TipoTubo.NESSUNO) {
					isCollegato = true;
				}
			}
		}
		
		// gli unici tubi compatibili sono NESSUNO-NESSUNO
		if(!isCollegato) return false;
		
		if(!controllaCannoniOMotoriAttorno(adiacenti)) return false;

		// Controllo i casi particolari
		return isPosizionamentoValidoPerCannoniEMotori(adiacenti, nuovoComponente);
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
