package view.renderer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.componenti.Componente;
import model.componenti.TipoComponente;
import model.enums.TipoTubo;
import model.nave.Nave;
import model.nave.TipoNave;
import util.Util;
import util.layout.Coordinate;
import util.layout.Direzione;
import view.CostantiGrafica;
import view.GraficaConfig;
import view.TextAligner;

public class NaveRenderer {

	private TextAligner textAligner = new TextAligner();
	private ComponenteRenderer componenteRenderer = new ComponenteRenderer();

	// Restituisce la rappresentazione della nave.
		public String[] rappresentazioneNave(Nave nave) {

			String[] rappresentazioneNave = new String[	CostantiGrafica.ALTEZZA_COMPONENTE * Util.SIZE];
			Componente[][] grigliaComponenti = nave.getGrigliaComponentiCloni();

			// rappresento l'intera nave riga per riga
			for (int y = 0; y < Util.SIZE; y++) {

				String[] rappresentazioneRiga = rappresentaRigaNave(grigliaComponenti, y,
						nave.getLivelloNave());

				// rappresento la singola riga
				for (int rigaComponente = 0; rigaComponente < CostantiGrafica.ALTEZZA_COMPONENTE; rigaComponente++) {

					rappresentazioneNave[y * CostantiGrafica.ALTEZZA_COMPONENTE
							+ rigaComponente] = rappresentazioneRiga[rigaComponente];
				}
			}

			rappresentazioneNave = aggiungiCoordinateANave(rappresentazioneNave);

			rappresentazioneNave = textAligner.affiancaStringhe(legendaComponenti(), rappresentazioneNave);

			return rappresentazioneNave;
		}
		
		// Scrive le coordinate a sinistra e sotto alla nave
		private String[] aggiungiCoordinateANave(String[] rappresentazioneNave) {

			int altezzaTotale = Util.SIZE * CostantiGrafica.ALTEZZA_COMPONENTE +2; // Aggiungo uno spazio e la riga delle coordinate
			int spazioOrdinate = 3; // Spazio da lasciare tra i componenti e le ordinate a sinistra

			// La riga è formata dalla nave, lo spazio tra la nave e le ordinate e le
			// ordinate
			int larghezzaTotale = Util.SIZE * CostantiGrafica.LARGHEZZA_COMPONENTE + spazioOrdinate;

			String[] naveConCoordinate = new String[altezzaTotale];

			// Inizializzo le righe
			Arrays.fill(naveConCoordinate, "");

			Integer ordinate = GraficaConfig.OFFSET;
			for (int riga = 0; riga < Util.SIZE * CostantiGrafica.ALTEZZA_COMPONENTE; riga++) {

				// Scrivo le coordinate a sinistra, con lo spazio
				if ((riga - 1) % CostantiGrafica.ALTEZZA_COMPONENTE == 0) { // ogni CostantiGrafica.ALTEZZA_COMPONENTE righe bisogna scrivere l'ordinata
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
			int ascisseOffset = spazioOrdinate + (int) Math.floor((double) CostantiGrafica.LARGHEZZA_COMPONENTE / 2);

			// riempio l'offsett di spazi
			naveConCoordinate[altezzaTotale - 1] = " ".repeat(ascisseOffset);

			Integer ascisse = GraficaConfig.OFFSET; // Integer per non fare il cast, ma usare toString

			// Scrivo le ascisse sotto alla nave, partendo da ascisseOffset spazi
			for(int i= 0 ; i < Util.SIZE; i++) {
				naveConCoordinate[altezzaTotale - 1] += textAligner.estendiStringa(ascisse.toString(), CostantiGrafica.LARGHEZZA_COMPONENTE);
				ascisse ++;
			}

			return naveConCoordinate;
		}
		
		// Rende la riga di comopnenti nelle tre righe che rappresentano i componenti
		private String[] rappresentaRigaNave(Componente[][]grigliaComponenti, int y, TipoNave livelloNave) {

			String[] rappresentazioneRiga = new String[CostantiGrafica.ALTEZZA_COMPONENTE];

			// Inizializzo le righe vuote
			Arrays.fill(rappresentazioneRiga, "");

			// Itera su ogni componente della riga
			for (int x = 0; x < Util.SIZE; x++) {
				
				String[] rappresentazioneComponente;
				
				Coordinate c = new Coordinate(x, y);
				if(livelloNave.isPosizionabile(c)) {
					rappresentazioneComponente= componenteRenderer.rappresentaComponente(grigliaComponenti[x][y]);				
				}
				else {
					rappresentazioneComponente = new String[CostantiGrafica.ALTEZZA_COMPONENTE];
					
					// Se il pezzo non è posizionabile lasico vuoto
					for(int i = 0 ; i < CostantiGrafica.ALTEZZA_COMPONENTE; i++) {
						rappresentazioneComponente[i] = textAligner.estendiStringa("", CostantiGrafica.LARGHEZZA_COMPONENTE);
					}
					
				}		
				// riga tiene conto della riga alla quale stiamo concatenando il componente
				for (int riga = 0; riga < CostantiGrafica.ALTEZZA_COMPONENTE; riga++) {
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
			legenda.add("Spazio vuoto: " + CostantiGrafica.COMPONENTE_NULL );
			
			for (TipoComponente componente : TipoComponente.values()) {
				String voceLegenda = componente.name() + ": " + componente.toString();
				legenda.add(voceLegenda);
			}

			// legenda dei tubi
			legenda.add("");
			legenda.add("Legenda dei tubi: ");

			for (TipoTubo tubo : TipoTubo.values()) {
				String legendaTubo = "Tubo " + tubo.name() + " in verticale: " + tubo.getSimbolo(Direzione.SOPRA)
						+ "  orizzontale: " + tubo.getSimbolo(Direzione.SINISTRA);
				legenda.add(legendaTubo);
			}

			// legenda delle direzioni
			legenda.add("");
			legenda.add("Legenda delle direzioni: ");

			legenda.add("Direzioni: " + Direzione.SOPRA.name() + " " + CostantiGrafica.FRECCIA_SOPRA);
			legenda.add(Direzione.SINISTRA.name() + " " + CostantiGrafica.FRECCIA_SINISTRA);
			legenda.add(Direzione.SOTTO.name() + " " + CostantiGrafica.FRECCIA_SOTTO);
			legenda.add(Direzione.DESTRA.name() + " " + CostantiGrafica.FRECCIA_DESTRA);

			return legenda.toArray(new String[0]);

		}
		

}
