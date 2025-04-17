package grafica;

import java.util.Arrays;
import java.util.List;

import componenti.CabinaPartenza;
import componenti.Cannone;
import componenti.Componente;
import componenti.GeneratoreDiScudi;
import componenti.Motore;
import componenti.Stiva;
import componenti.TipoComponente;
import componenti.VanoBatteria;
import model.enums.Direzione;

public class ComponenteRenderer {
	
	public final static int LARGHEZZA_COMPONENTE = 5; // Numero spazi per rappresentare un componente
	public final static int ALTEZZA_COMPONENTE = 3; // Num righe per rappresentare ogni componente
	public final static String COMPONENTE_NULL  = "O";
	
	public static final String FRECCIA_SOPRA = "^";
	public static final String FRECCIA_SOTTO = "v";
	public static final String FRECCIA_SINISTRA = "<";
	public static final String FRECCIA_DESTRA = ">";
	
	private final TextAligner textAligner = new TextAligner();
	
	public String[] rappresentaComponente(Componente componente) {

		/*
		 * Esempio di output:
		 *   || 
		 *  #MD = 
		 *   | 
		 */
		String[] rappresentazioneComponente = new String[ALTEZZA_COMPONENTE];

		// se il componente è null rappresento O
		if (componente == null) {
			rappresentazioneComponente[0] = textAligner.centraTestoInLarghezza("", LARGHEZZA_COMPONENTE);
			rappresentazioneComponente[1] = textAligner.centraTestoInLarghezza(COMPONENTE_NULL , LARGHEZZA_COMPONENTE);
			rappresentazioneComponente[2] = textAligner.centraTestoInLarghezza("", LARGHEZZA_COMPONENTE);
			return rappresentazioneComponente;
		}

        String sopra = componente.getTubo(Direzione.SOPRA).getSimbolo(Direzione.SOPRA); // tubo sopra
        String sinistra = componente.getTubo(Direzione.SINISTRA).getSimbolo(Direzione.SINISTRA); // tubo a sx
        String tipo = formattaComponente(componente); // componente
        String destra = componente.getTubo(Direzione.DESTRA).getSimbolo(Direzione.DESTRA); // tubo a dx
        String sotto = componente.getTubo(Direzione.SOTTO).getSimbolo(Direzione.SOTTO); // tubo sotto

        rappresentazioneComponente[0] = textAligner.centraTestoInLarghezza(sopra, LARGHEZZA_COMPONENTE);
        rappresentazioneComponente[1] = sinistra + textAligner.centraTestoInLarghezza(tipo, LARGHEZZA_COMPONENTE - sinistra.length() - destra.length()) + destra;
        rappresentazioneComponente[2] = textAligner.centraTestoInLarghezza(sotto, LARGHEZZA_COMPONENTE);
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
				case SOPRA -> siglaComponente + FRECCIA_SOPRA;
				case SOTTO -> siglaComponente + FRECCIA_SOTTO;
				case SINISTRA -> FRECCIA_SINISTRA + siglaComponente;
				case DESTRA -> siglaComponente + FRECCIA_DESTRA;
			};	
		}	
		
		private String aggiungiCapacita(String siglaComponente, Integer capacita) {
			return siglaComponente + capacita.toString();
		}
	

	public String[] rappresentaComponenti(List<Componente> lista) {
		
		if(lista == null) { return null;}
		
		final int SPAZIO_TRA_COMPONENTI = 4;
		
		// Calcolo se i pezzi occupano piu di una riga	
		int larghezzaTotaleComponenti = lista.size() * (LARGHEZZA_COMPONENTE + SPAZIO_TRA_COMPONENTI);		
        int righeTotali = 1; // Di default considero che 1 riga sia sufficiente per rappresentare tutti i componenti        
        int numComponentiPerRiga; // Numero di componenti presente su ogni ria
        
        righeTotali =  (int) Math.ceil((double) larghezzaTotaleComponenti / GraficaConfig.LARGHEZZA_SCHERMO) ;
		        
        // Calcolo quanti componenti verranno stampati per riga
        numComponentiPerRiga = (int) Math.ceil((double) lista.size() / righeTotali);
        
    	String[] singoloComponente = new String[ALTEZZA_COMPONENTE];
        String[] righeComponenti = new String[ALTEZZA_COMPONENTE* righeTotali];
        
    	// Inizializzo righeComponenti
        Arrays.fill(righeComponenti, "");

        int riga = -1; // Tiene conto del numero di righe stampate
        for(Integer n = 0; n < lista.size(); n ++) {
			
        	// Vado a capo se ho stampato tutti i componenti della riga
        	if(n % numComponentiPerRiga == 0) {
        		riga++;
        	}
        			
    		// scrivo il numero che corrisponde al componente
    		righeComponenti[0 + riga*ALTEZZA_COMPONENTE] += textAligner.estendiStringa(" "+(n+1) , SPAZIO_TRA_COMPONENTI); 
    		for(int j = 1; j < ALTEZZA_COMPONENTE; j++) {
    			righeComponenti[j + riga*ALTEZZA_COMPONENTE] += textAligner.estendiStringa("" , SPAZIO_TRA_COMPONENTI); 
	    		
    		}
    		
        	singoloComponente = rappresentaComponente(lista.get(n));

        	// concateno il componente alla riga
        	for(int i = 0; i < ALTEZZA_COMPONENTE; i++) {
        		righeComponenti[i + riga*ALTEZZA_COMPONENTE] += singoloComponente[i];
        	}
			
		}	
		return righeComponenti;
		
	}
	
}
