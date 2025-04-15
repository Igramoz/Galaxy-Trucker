package grafica;

import java.util.ArrayList;
import java.util.List;

import componenti.Componente;
import componenti.TipoComponente;
import model.enums.*;
import model.Coordinate;
import nave.Nave;
import nave.TipoNave;
import util.Util;

public class ConvertitoreGrafica {
	// Classe che si occupa di convertire i componenti di gioco in rappresentazioni
	// grafiche testuali

	public final static int LARGHEZZA_COMPONENTE = 5; // Numero spazi per rappresentare un componente
	public final static int ALTEZZA_COMPONENTE = 3; // Num righe per rappresentare ogni componente
	private final String ComponenteNull = "O";

	private TextAligner textAligner;
	private FormattatoreGrafico formattatoreGrafico;

	public ConvertitoreGrafica() {
		textAligner = new TextAligner();
		formattatoreGrafico = new FormattatoreGrafico();
	}

	public String[] rappresentaComponente(Componente componente) {

		/*
		 * Esempio di output: || #MD = |
		 */
		String[] rappresentazioneComponente = new String[ALTEZZA_COMPONENTE];

		// se il componente è null rappresento O
		if (componente == null) {
			rappresentazioneComponente[0] = textAligner.centraTestoInLarghezza("", LARGHEZZA_COMPONENTE);
			rappresentazioneComponente[1] = textAligner.centraTestoInLarghezza(ComponenteNull, LARGHEZZA_COMPONENTE);
			rappresentazioneComponente[2] = textAligner.centraTestoInLarghezza("", LARGHEZZA_COMPONENTE);
			return rappresentazioneComponente;
		}

		// Rappresento il tubo sopra " || "
		String tuboSopra = componente.getTubo(Direzione.SOPRA).rappresentazione(Direzione.SOPRA);
		rappresentazioneComponente[0] = textAligner.centraTestoInLarghezza(tuboSopra, LARGHEZZA_COMPONENTE);

		// Rappresento il corpo del componente con i tubi a sinistra e destra "#MD ="
		String tuboSinistra = componente.getTubo(Direzione.SINISTRA).rappresentazione(Direzione.SINISTRA);
		String tipoComponente = formattatoreGrafico.formattaComponente(componente);
		String tuboDestra = componente.getTubo(Direzione.DESTRA).rappresentazione(Direzione.DESTRA);
		rappresentazioneComponente[1] = tuboSinistra + textAligner.centraTestoInLarghezza(tipoComponente,
				LARGHEZZA_COMPONENTE - tuboSinistra.length() - tuboDestra.length()) + tuboDestra;

		// Rappresento il tubo sotto " | "
		String tuboSotto = componente.getTubo(Direzione.SOTTO).rappresentazione(Direzione.SOTTO);
		rappresentazioneComponente[2] = textAligner.centraTestoInLarghezza(tuboSotto, LARGHEZZA_COMPONENTE);

		return rappresentazioneComponente;
	}

	// Restituisce la rappresentazione della nave.
	public String[] rappresentazioneNave(Nave nave) {

		String[] rappresentazioneNave = new String[	ALTEZZA_COMPONENTE * Util.SIZE];
		Componente[][] grigliaComponenti = nave.getGrigliaComponenti();

		// rappresento l'intera nave riga per riga
		for (int rigaGriglia = 0; rigaGriglia < Util.SIZE; rigaGriglia++) {

			String[] rappresentazioneRiga = rappresentaRigaNave(grigliaComponenti[rigaGriglia], rigaGriglia,
					nave.getLivelloNave());

			// rappresento la singola riga
			for (int rigaComponente = 0; rigaComponente < ALTEZZA_COMPONENTE; rigaComponente++) {

				rappresentazioneNave[rigaGriglia * ALTEZZA_COMPONENTE
						+ rigaComponente] = rappresentazioneRiga[rigaComponente];
			}
		}

		rappresentazioneNave = aggiungiCoordinateANave(rappresentazioneNave);

		rappresentazioneNave = textAligner.affiancaStringhe(legendaComponenti(), rappresentazioneNave);

		return rappresentazioneNave;
	}

	// Scrive le coordinate a sinistra e sotto alla nave
	private String[] aggiungiCoordinateANave(String[] rappresentazioneNave) {

		int altezzaTotale = Util.SIZE * ALTEZZA_COMPONENTE +2; // Aggiungo uno spazio e la riga delle coordinate
		int spazioOrdinate = 3; // Spazio da lasciare tra i componenti e le ordinate a sinistra

		// La riga è formata dalla nave, lo spazio tra la nave e le ordinate e le
		// ordinate
		int larghezzaTotale = Util.SIZE * LARGHEZZA_COMPONENTE + spazioOrdinate;

		String[] naveConCoordinate = new String[altezzaTotale];

		// Inizializzo le righe
		for (int riga = 0; riga < altezzaTotale; riga++) {
			naveConCoordinate[riga] = "";
		}

		Integer ordinate = GraficaConfig.OFFSET;
		for (int riga = 0; riga < Util.SIZE * ALTEZZA_COMPONENTE; riga++) {

			// Scrivo le coordinate a sinistra, con lo spazio
			if ((riga - 1) % ALTEZZA_COMPONENTE == 0) { // ogni ALTEZZA_COMPONENTE righe bisogna scrivere l'ordinata
				naveConCoordinate[riga] = textAligner.estendiStringa(ordinate.toString(), spazioOrdinate);
				ordinate++;
			} else {
				naveConCoordinate[riga] = textAligner.estendiStringa(naveConCoordinate[riga], spazioOrdinate);

			}
		}
		
		for (int i = 0; i < rappresentazioneNave.length; i++) {
			naveConCoordinate[i] += rappresentazioneNave[i];
		}

		// Scrivo la penultima riga
		naveConCoordinate[altezzaTotale- 2] = " ".repeat(larghezzaTotale);

		// Scrivo l'ultima riga
		// inizio a scrivere le ascisse da ascisse offsett, colonna delle ordinate (1) +
		// spazio tra ascisse e nave + metà componente
		int ascisseOffset = spazioOrdinate + (int) Math.ceil((double) LARGHEZZA_COMPONENTE / 2);

		// riempio l'offsett di spazi
		naveConCoordinate[altezzaTotale - 1] += " ".repeat(ascisseOffset);

		Integer ascisse = GraficaConfig.OFFSET; // Integer per non fare il cast, ma usare toString

		// Scrivo le ascisse sotto alla nave, partendo da ascisseOffset spazi
		for(int i= 0 ; i < Util.SIZE; i++) {
			naveConCoordinate[altezzaTotale - 1] += textAligner.estendiStringa(ascisse.toString(), LARGHEZZA_COMPONENTE);
			ascisse ++;
		}

		return naveConCoordinate;
	}

	// Rende la riga di comopnenti nelle tre righe che rappresentano i componenti
	private String[] rappresentaRigaNave(Componente[] componente, int rigaNave, TipoNave livelloNave) {

		String[] rappresentazioneRiga = new String[ALTEZZA_COMPONENTE];

		// Inizializzo le righe vuote
		for (int i = 0; i < ALTEZZA_COMPONENTE; i++) {
			rappresentazioneRiga[i] = "";
		}

		// Itera su ogni componente della riga
		for (int colonna = 0; colonna < Util.SIZE; colonna++) {
			
			String[] rappresentazioneComponente;
			
			Coordinate c = new Coordinate(rigaNave, colonna);
			if(livelloNave.isPosizionabile(c)) {
				rappresentazioneComponente= rappresentaComponente(componente[colonna]);				
			}
			else {
				rappresentazioneComponente = new String[ALTEZZA_COMPONENTE];
				
				// Se il pezzo non è posizionabile lasico vuoto
				for(int i = 0 ; i < ALTEZZA_COMPONENTE; i++) {
					rappresentazioneComponente[i] = textAligner.estendiStringa("", LARGHEZZA_COMPONENTE);
				}
				
			}		
			// riga tiene conto della riga alla quale stiamo concatenando il componente
			for (int riga = 0; riga < ALTEZZA_COMPONENTE; riga++) {
				rappresentazioneRiga[riga] += rappresentazioneComponente[riga];
			}
		}
		return rappresentazioneRiga;
	}

	// restituisce la legenda dei componenti
	public String[] legendaComponenti() {

		List<String> legenda = new ArrayList<>();

		// legenda dei componenti
		legenda.add("Legenda dei componenti: ");
		legenda.add("Spazio vuoto: " + ComponenteNull);
		
		for (TipoComponente componente : TipoComponente.values()) {
			String voceLegenda = componente.name() + ": " + componente.toString();
			legenda.add(voceLegenda);
		}

		// legenda dei tubi
		legenda.add("");
		legenda.add("Legenda dei tubi: ");

		for (TipoTubo tubo : TipoTubo.values()) {
			String legendaTubo = "Tubo " + tubo.name() + " in verticale: " + tubo.rappresentazione(Direzione.SOPRA)
					+ "  orizzontale: " + tubo.rappresentazione(Direzione.SINISTRA);
			legenda.add(legendaTubo);
		}

		// legenda delle direzioni
		legenda.add("");
		legenda.add("Legenda delle direzioni: ");

		legenda.add("Direzioni: " + Direzione.SOPRA.name() + " " + FormattatoreGrafico.FRECCIA_SOPRA);
		legenda.add(Direzione.SINISTRA.name() + " " + FormattatoreGrafico.FRECCIA_SINISTRA);
		legenda.add(Direzione.SOTTO.name() + " " + FormattatoreGrafico.FRECCIA_SOTTO);
		legenda.add(Direzione.DESTRA.name() + " " + FormattatoreGrafico.FRECCIA_DESTRA);

		return legenda.toArray(new String[0]);

	}
	
	public String[] rappresentaComponenti(List<Componente> lista) {
		
		if(lista == null) { return null;}
		
		final int SPAZIO_TRA_COMPONENTI = 4;
		
		// Calcolo se i pezzi occupano piui di una riga	
		int larghezzaTotaleComponenti = lista.size() * (LARGHEZZA_COMPONENTE + SPAZIO_TRA_COMPONENTI);		
        int righeTotali = 1; // Di default considero che 1 riga sia sufficiente per rappresentare tutti i componenti        
        int numComponentiPerRiga; // Numero di componenti presente su ogni ria
        
        righeTotali =  (int) Math.ceil((double) larghezzaTotaleComponenti / GraficaConfig.LARGHEZZA_SCHERMO) ;
		        
        // Calcolo quanti componenti verranno stampati per riga
        numComponentiPerRiga = (int) Math.ceil((double) lista.size() / righeTotali);
        
    	String[] singoloComponente = new String[ALTEZZA_COMPONENTE];
        String[] righeComponenti = new String[ALTEZZA_COMPONENTE* righeTotali];
    	// Inizializzo righeComponenti
    	for(int i = 0; i < righeComponenti.length; i++) {
    		righeComponenti[i] = "";
    	}
    	
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
