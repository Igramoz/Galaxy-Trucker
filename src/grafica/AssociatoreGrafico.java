package grafica;

import componenti.TipoComponente;
import model.enums.Direzione;
import model.enums.TipoTubo;

public class AssociatoreGrafico {
	
	public String getSimbolo(TipoTubo tipoTubo, Direzione direzione) {
		
		String output = "";
		
		if (tipoTubo == TipoTubo.NESSUNO) {
			output = " ";
			
		}else if(tipoTubo == TipoTubo.UNIVERSALE) {
			output = "#";
		}
		
		if(tipoTubo == TipoTubo.SINGOLO) {
			if( direzione == Direzione.SOPRA || direzione == Direzione.SOTTO ) {
				output = "|";
			}else {
				output = "-";
			}
		}
		
		if(tipoTubo == TipoTubo.DOPPIO) {
			if( direzione == Direzione.SOPRA || direzione == Direzione.SOTTO ) {
				output = "||";
			}else {
				output = "=";
			}
		}

		if(direzione == Direzione.SOPRA || direzione == Direzione.SOTTO) {
			if(output.length() == 1) {
				output = " " + output + " ";
			}else {
				output = output + " " ;
			}
		}
		return output;
	}
	
	public String getSimbolo(TipoComponente tipoComponente) {
	    switch (tipoComponente) {
	        case CABINA_EQUIPAGGIO:
	            return "CE ";
	        case CABINA_PARTENZA:
	            return "CP "; 
	        case CANNONE_SINGOLO:
	            return "CS"; // TODO cambiare a seconda della direzione in cui spara
	        case CANNONE_DOPPIO:
	            return "CD"; // TODO cambiare a seconda della direzione in cui spara
	        case MOTORE_SINGOLO:
	            return "MSv";
	        case MOTORE_DOPPIO:
	            return "MDv";
	        case SCUDO:
	            return " S "; // TODO cambiare con direzioni in cui protegge
	        case STIVA:
	            return " M "; // TODO cambiare a seconda che sia singoolo, doppio o triplo e il colore
	        case VANO_BATTERIA:
	            return " B "; // TODO cambiare a seconda che sia doppio o triplo
	        case MODULO_SUPPORTO_ALIENI:
	            return "MSA";
	        case TUBO:
	            return " T ";
	        default:
	            return "   ";
	    }
	}	
}
