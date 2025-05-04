package model.carte;

import io.GestoreIO;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import grafica.TextAligner;
import grafica.formattatori.FormattatoreGrafico;
import model.carte.criteriEffetti.Effetto;
import partita.fasiGioco.volo.ManagerDiVolo;

public class PolvereStellare extends Carta {

	private final int penalitaTempoVoloPerConnettoreEsposto;
	private final Effetto effetto = Effetto.GIORNI_VOLO;
		
	public PolvereStellare(int penalitaTempoVoloPerConnettoreEsposto) {
		super(TipoCarta.POLVERE_STELLARE);
		this.penalitaTempoVoloPerConnettoreEsposto = penalitaTempoVoloPerConnettoreEsposto;
	}

	@Override
	public void eseguiEvento(ManagerDiVolo[] arrayManager) {
		GestoreIO io = new GestoreIO();
		TextAligner txtAligner = new TextAligner();
		FormattatoreGrafico formattatoreGrafico = new FormattatoreGrafico();
		
		io.stampa(txtAligner.alignCenter("Carta POLVERE DI STELLE"));
		io.stampa("Ogni giocatore perderà " + penalitaTempoVoloPerConnettoreEsposto + " per ogni connettore esposto");
		
		// i manager vanno letti in ordine inverso di rotta
		// inverto l'array
		ManagerDiVolo[] arrayInverso = Arrays.copyOf(arrayManager, arrayManager.length);
		List<ManagerDiVolo> listaInversa = Arrays.asList(arrayInverso);
		Collections.reverse(listaInversa);

		// itero nella lista invertita
		for(ManagerDiVolo manager : listaInversa){
			int connettoriEsposti = manager.getGiocatore().getNave().getNumConnettoriEsposti();
			io.aCapo();
			io.stampa(formattatoreGrafico.formatta(manager.getGiocatore()) + " ha " + connettoriEsposti + " connettori esposti");
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
