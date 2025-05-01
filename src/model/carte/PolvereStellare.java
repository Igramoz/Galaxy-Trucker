package model.carte;

import io.GestoreIO;
import grafica.FormattatoreGrafico;
import grafica.TextAligner;
import model.carte.criteriEffetti.Effetto;
import partita.fasiGioco.ManagerDiVolo;

public class PolvereStellare extends Carta {

	private final int penalitaTempoVoloPerConnettoreEsposto;
	private final Effetto effetto = Effetto.GIORNI_VOLO;
		
	public PolvereStellare(int penalitaTempoVoloPerConnettoreEsposto) {
		super(TipoCarta.POLVERE_STELLARE);
		this.penalitaTempoVoloPerConnettoreEsposto = penalitaTempoVoloPerConnettoreEsposto;
	}

	@Override
	public void eseguiEvento(ManagerDiVolo[] listaManager) {
		GestoreIO io = new GestoreIO();
		TextAligner txtAligner = new TextAligner();
		FormattatoreGrafico formattatoreGrafico = new FormattatoreGrafico();
		
		io.stampa(txtAligner.alignCenter("Carta POLVERE DI STELLE"));
		io.stampa("Ogni giocatore perderà " + penalitaTempoVoloPerConnettoreEsposto + " per ogni connettore esposto");

		for(ManagerDiVolo manager : listaManager){
			int connettoriEsposti = manager.getGiocatore().getNave().getNumConnettoriEsposti();
			io.aCapo();
			io.stampa(formattatoreGrafico.formattaGiocatore(manager.getGiocatore()) + " ha " + connettoriEsposti + " connettori esposti");
			effetto.applica(manager, penalitaTempoVoloPerConnettoreEsposto);
		}
		io.aCapo();
		io.stampa("Carta POLVERE DI STELLE risolta");
	}

	public int getPenalitaTempoVoloPerConnettoreEsposto() {
		return penalitaTempoVoloPerConnettoreEsposto;
	}

	public Effetto getEffetto() {
		return effetto;
	}
	
}
