package grafica;


import componenti.Componente;
import model.enums.Direzione;
import util.Util;
import model.Nave;


public class ConvertitoreGrafica {
	public final static int LARGHEZZA_COMPONENTE = 5; // Numero spazi per raprresentare un componente
	public final static int ALTEZZA_COMPONENTE = 3; // Num righe per rappresentare ogni componente
	
	// Classe che si occupa di convertire i componenti di gioco in rappresentazioni grafiche testuali
	
	// TODO: valutare se c'è la possiblità di renderlo un toString nel componente
	protected String[] rappresentaComponente(Componente componente) {
		
		/*Esempio di output:
		*
		*  ||   
		* #MD =
        *  |   		
		*/
		
        String[] rappresentazioneComponente = new String[ALTEZZA_COMPONENTE];

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
	
	// Restituisce la rappresentazione della nave.
	public String[] raprresentaNave(Nave nave) {
		
		String[] rappresentazioneNave = new String[Util.SIZE * ALTEZZA_COMPONENTE];
		Componente[][] grigliaComponenti = nave.getGrigliaComponenti();
		
		// rappresento l'intera nave riga per riga
		for(int rigaGriglia = 0; rigaGriglia < Util.SIZE; rigaGriglia++) {
			
			String[] rappresentazioneRiga = rappresentaRigaNave(grigliaComponenti[rigaGriglia]);
			
			// rappresento la singola riga
			for(int rigaComponente = 0; rigaComponente < ALTEZZA_COMPONENTE; rigaComponente++) {
				
				rappresentazioneNave[rigaGriglia*ALTEZZA_COMPONENTE + rigaComponente] = rappresentazioneRiga[rigaComponente];
			}			
		}
		
		return rappresentazioneNave;		
	}
	
	
	
	
	public String[] aggiungiCoordinateANave(String[] rappresentazioneNave) {
		
		int altezzaTotale = Util.SIZE * ALTEZZA_COMPONENTE + 2; // Aggiungo uno spazio e la riga delle coordinate
		String stringTraNaveEOrdinate = " "; // Spazio da lasciare tra i componenti e le ordinate
		
		// La riga è formata dalla nave, lo spazio tra la nave e le ordinate e le ordinate
		int larghezzaTotale = Util.SIZE * LARGHEZZA_COMPONENTE + stringTraNaveEOrdinate.length() + 1; 		

		String[] naveConCoordinate = new String[altezzaTotale]; 
		
		// Inizializzo le righe
		for(int riga = 0; riga < altezzaTotale; riga++) {
			naveConCoordinate[riga] = "";
		}
		
		int ordinate = FormatterGrafico.OFFSET; 
		for(int riga = 0 ; riga < Util.SIZE * ALTEZZA_COMPONENTE ; riga++) {
			
			// Scrivo le coordinate a sinistra
			if((riga-1)%ALTEZZA_COMPONENTE == 0) { // ogni ALTEZZA_COMPONENTE righe bisogna scrivere l'ordinata
				naveConCoordinate[riga] += ordinate;
				ordinate ++;
			}else {
				naveConCoordinate[riga] += " "; // Così le colonne della nave rimangono allineate				
			}
			
			// Spazio tra ordinate e nave
			naveConCoordinate[riga] += " ";
			naveConCoordinate[riga] = rappresentazioneNave[riga];			
		}

		// Scrivo la penultima riga
		naveConCoordinate[ALTEZZA_COMPONENTE] = " ".repeat(larghezzaTotale);
		
		
		// Scrivo l'ultima riga
		// inizio a scrivere le ascisse da ascisse offsett, colonna delle ordinate (1) + spazio tra ascisse e nave + metà componente
		int ascisseOffset =  1 + stringTraNaveEOrdinate.length() + (int) Math.ceil((double) LARGHEZZA_COMPONENTE / 2);
		
		// riempio l'offsett di spazi
		rappresentazioneNave[altezzaTotale - 1] += " ".repeat(ascisseOffset );
		
		// Scrivo le ascisse sotto alla nave, partendo da ascisseOffset spazi
		for(int col = 0, ascisse = FormatterGrafico.OFFSET; col < larghezzaTotale-ascisseOffset; col++) {
			
			if(col % LARGHEZZA_COMPONENTE == 0) {
				rappresentazioneNave[altezzaTotale - 1]  += ascisse;
				ascisse++;
			}else {
				rappresentazioneNave[altezzaTotale - 1] += " "; // Spazi tra un ascissa e l'altra
			}
		}

		return naveConCoordinate;
	}
	
	
	// Rende la riga di comopnenti nelle tre righe che rappresentano i componenti
	private String[] rappresentaRigaNave(Componente[] componente) {		
		
		String[] rappresentazioneRiga = new String[ALTEZZA_COMPONENTE];
		
	    // Inizializzo le righe vuote
        for (int i = 0; i < ALTEZZA_COMPONENTE; i++) {
            rappresentazioneRiga[i] = "";
        }

		
		// Itera su ogni componente della riga
		for(int colonna = 0; colonna < Util.SIZE; colonna++) {
			String[] rappresentazioneComponente = rappresentaComponente(componente[colonna]);
			
			// riga tiene conto della riga alla quale stiamo concatenando il componente
			for(int riga = 0; riga < ALTEZZA_COMPONENTE; riga++) {
				rappresentazioneRiga[riga] += rappresentazioneComponente[riga];
			}			
		}
		
		return rappresentazioneRiga;
	}

}
