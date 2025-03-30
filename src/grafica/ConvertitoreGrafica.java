package grafica;

import componenti.Componente;
import model.enums.Direzione;


public class ConvertitoreGrafica {
	public final int LARGHEZZA_COMPONENTE = 5; // Numero spazi per raprresentare un componente
	
	// Classe che si occupa di convertire i componenti di gioco in rappresentazioni grafiche testuali
	
	// TODO: valutare se c'è la possiblità di renderlo un toString
	protected String[] rappresentaComponente(Componente componente) {
		
		/*Esempio di output:
		*
		*  ||   
		* #MD =
        *  |   		
		*/
		
        String[] rappresentazioneComponente = new String[3];
		
		// Rappresento il tubo sopra "  ||  "
        String tuboSopra = componente.getTubo(Direzione.SOPRA).toString();
        rappresentazioneComponente[0] = TextAligner.centraTestoInLarghezza(tuboSopra, LARGHEZZA_COMPONENTE);
        
        // Rappresento il corpo del componente con i tubi a sinistra e destra "#MD ="
        String tuboSinistra = componente.getTubo(Direzione.SINISTRA).toString();
        String tipoComponente = componente.getTipo().toString();
        String tuboDestra = componente.getTubo(Direzione.DESTRA).toString();
        rappresentazioneComponente[1] = tuboSinistra + 
        								TextAligner.centraTestoInLarghezza(tipoComponente, LARGHEZZA_COMPONENTE - tuboSinistra.length() - tuboDestra.length()) +
        								tuboDestra;
        
        // Rappresento il tubo sotto "  |  "
        String tuboSotto = componente.getTubo(Direzione.SOTTO).toString();
        rappresentazioneComponente[2] = TextAligner.centraTestoInLarghezza(tuboSotto, LARGHEZZA_COMPONENTE);
        
        return rappresentazioneComponente;		
	}	
}
