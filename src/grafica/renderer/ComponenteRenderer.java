package grafica.renderer;

import java.util.Arrays;
import java.util.List;

import grafica.CostantiGrafica;
import grafica.GraficaConfig;
import grafica.TextAligner;
import model.componenti.CabinaPartenza;
import model.componenti.Cannone;
import model.componenti.Componente;
import model.componenti.GeneratoreDiScudi;
import model.componenti.Motore;
import model.componenti.Stiva;
import model.componenti.VanoBatteria;
import model.enums.Direzione;
import model.enums.TipoComponente;

public class ComponenteRenderer {
	

	
	private final TextAligner textAligner = new TextAligner();
	
	public String[] rappresentaComponente(Componente componente) {

		/*
		 * Esempio di output:
		 *   || 
		 *  #MD = 
		 *   | 
		 */
		String[] rappresentazioneComponente = new String[CostantiGrafica.ALTEZZA_COMPONENTE];

		// se il componente è null rappresento O
		if (componente == null) {
			rappresentazioneComponente[0] = textAligner.centraTestoInLarghezza("", CostantiGrafica.LARGHEZZA_COMPONENTE);
			rappresentazioneComponente[1] = textAligner.centraTestoInLarghezza(CostantiGrafica.COMPONENTE_NULL , CostantiGrafica.LARGHEZZA_COMPONENTE);
			rappresentazioneComponente[2] = textAligner.centraTestoInLarghezza("", CostantiGrafica.LARGHEZZA_COMPONENTE);
			return rappresentazioneComponente;
		}

        String sopra = componente.getTubo(Direzione.SOPRA).getSimbolo(Direzione.SOPRA); // tubo sopra
        String sinistra = componente.getTubo(Direzione.SINISTRA).getSimbolo(Direzione.SINISTRA); // tubo a sx
        String tipo = formattaComponente(componente); // componente
        String destra = componente.getTubo(Direzione.DESTRA).getSimbolo(Direzione.DESTRA); // tubo a dx
        String sotto = componente.getTubo(Direzione.SOTTO).getSimbolo(Direzione.SOTTO); // tubo sotto

        rappresentazioneComponente[0] = textAligner.centraTestoInLarghezza(sopra, CostantiGrafica.LARGHEZZA_COMPONENTE);
        rappresentazioneComponente[1] = sinistra + textAligner.centraTestoInLarghezza(tipo, CostantiGrafica.LARGHEZZA_COMPONENTE - sinistra.length() - destra.length()) + destra;
        rappresentazioneComponente[2] = textAligner.centraTestoInLarghezza(sotto, CostantiGrafica.LARGHEZZA_COMPONENTE);
		return rappresentazioneComponente;
	}
	
	// Gestisce la grafica di componenti particolari
		private String formattaComponente(Componente componente) {
			
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
			
			else if(componente.getTipo() == TipoComponente.CABINA_PARTENZA) {
				output = ((CabinaPartenza) componente).getColore().getCodice() + output;
			}
			
			return output;
		}
		
		
		private String aggiungiFrecciaDirezione(String siglaComponente, Direzione direzione) {
			
			return switch (direzione) {
				case SOPRA -> siglaComponente + CostantiGrafica.FRECCIA_SOPRA;
				case SOTTO -> siglaComponente + CostantiGrafica.FRECCIA_SOTTO;
				case SINISTRA -> CostantiGrafica.FRECCIA_SINISTRA + siglaComponente;
				case DESTRA -> siglaComponente + CostantiGrafica.FRECCIA_DESTRA;
			};	
		}	
		
		private String aggiungiCapacita(String siglaComponente, Integer capacita) {
			return siglaComponente + capacita.toString();
		}
	

	public String[] rappresentaComponenti(List<Componente> lista) {
		
		if(lista == null) { return null;}
		
		final int SPAZIO_TRA_COMPONENTI = 4;
		
		// Calcolo se i pezzi occupano piu di una riga	
		int larghezzaTotaleComponenti = lista.size() * (CostantiGrafica.LARGHEZZA_COMPONENTE + SPAZIO_TRA_COMPONENTI);		
        int righeTotali = 1; // Di default considero che 1 riga sia sufficiente per rappresentare tutti i componenti        
        int numComponentiPerRiga; // Numero di componenti presente su ogni ria
        
        righeTotali =  (int) Math.ceil((double) larghezzaTotaleComponenti / GraficaConfig.LARGHEZZA_SCHERMO) ;
		        
        // Calcolo quanti componenti verranno stampati per riga
        numComponentiPerRiga = (int) Math.ceil((double) lista.size() / righeTotali);
        
    	String[] singoloComponente = new String[CostantiGrafica.ALTEZZA_COMPONENTE];
        String[] righeComponenti = new String[CostantiGrafica.ALTEZZA_COMPONENTE* righeTotali];
        
    	// Inizializzo righeComponenti
        Arrays.fill(righeComponenti, "");

        int riga = -1; // Tiene conto del numero di righe stampate
        for(Integer n = 0; n < lista.size(); n ++) {
			
        	// Vado a capo se ho stampato tutti i componenti della riga
        	if(n % numComponentiPerRiga == 0) {
        		riga++;
        	}
        			
    		// scrivo il numero che corrisponde al componente
    		righeComponenti[0 + riga*CostantiGrafica.ALTEZZA_COMPONENTE] += textAligner.estendiStringa(" "+(n+1) , SPAZIO_TRA_COMPONENTI); 
    		for(int j = 1; j < CostantiGrafica.ALTEZZA_COMPONENTE; j++) {
    			righeComponenti[j + riga*CostantiGrafica.ALTEZZA_COMPONENTE] += textAligner.estendiStringa("" , SPAZIO_TRA_COMPONENTI); 
	    		
    		}
    		
        	singoloComponente = rappresentaComponente(lista.get(n));

        	// concateno il componente alla riga
        	for(int i = 0; i < CostantiGrafica.ALTEZZA_COMPONENTE; i++) {
        		righeComponenti[i + riga*CostantiGrafica.ALTEZZA_COMPONENTE] += singoloComponente[i];
        	}
			
		}	
		return righeComponenti;
		
	}
	
}
