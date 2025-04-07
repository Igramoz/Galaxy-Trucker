package grafica;

import componenti.*;
import model.enums.Direzione;

public class FormattatoreGrafico {
	// Classe per formattare i componenti grafici
	
	public static final String FRECCIA_SOPRA = "^";
	public static final String FRECCIA_SOTTO = "v";
	public static final String FRECCIA_SINISTRA = "<";
	public static final String FRECCIA_DESTRA = ">";
	
	// funzione che aggiunge < ^ > v se necessario
	public String stampaComponente(Componente componente) {
		
		String output = componente.getTipo().toString();
		
		if(componente.getTipo() == TipoComponente.MOTORE_SINGOLO || componente.getTipo() == TipoComponente.MOTORE_DOPPIO) {
			output = aggiungiFrecciaDirezione(output, ((Motore) componente).getDirezioneMotore());
		}
		else if(componente.getTipo() == TipoComponente.CANNONE_SINGOLO || componente.getTipo() == TipoComponente.CANNONE_DOPPIO) {
			output = aggiungiFrecciaDirezione(output, ((Cannone) componente).getDirezioneFuoco());
		}
		else if(componente.getTipo() == TipoComponente.SCUDO) {
			Direzione[] direzioni = ((GeneratoreDiScudi) componente).getDirezioni();
			output = aggiungiFrecciaDirezione(output, direzioni[0]);
			output = aggiungiFrecciaDirezione(output, direzioni[1]);
			
			
		}else if(componente.getTipo() == TipoComponente.STIVA || componente.getTipo() == TipoComponente.STIVA_SPECIALE) {
			output = aggiungiCapacita(output, ((Stiva) componente).getScomparti());
		}
		else if(componente.getTipo() == TipoComponente.VANO_BATTERIA) {
			output = aggiungiCapacita(output, ((VanoBatteria) componente).getCapacitaMassima());
		}
		
		return output;
	}
	
	
	private String aggiungiFrecciaDirezione(String siglaComponente, Direzione direzione) {
		
		return switch (direzione) {
			case SOPRA -> siglaComponente + FRECCIA_SOPRA;
			case SOTTO -> siglaComponente + FRECCIA_SOTTO;
			case SINISTRA -> FRECCIA_SINISTRA + siglaComponente;
			case DESTRA -> siglaComponente + FRECCIA_DESTRA;
		};	
	}	
	
	private String aggiungiCapacita(String siglaComponente, Integer capacita) {
		return siglaComponente + capacita.toString();
	}
	
	
	
}
