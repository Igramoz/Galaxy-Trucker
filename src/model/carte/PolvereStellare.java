package model.carte;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import controller.partita.fasiGioco.volo.ManagerDiVolo;
import model.carte.criteriEffetti.Effetto;
import view.TextAligner;
import view.formattatori.FormattatoreGrafico;
import view.io.GestoreIO;

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
			effetto.applica(manager, connettoriEsposti);
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(effetto, penalitaTempoVoloPerConnettoreEsposto);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PolvereStellare other = (PolvereStellare) obj;
		return effetto == other.effetto
				&& penalitaTempoVoloPerConnettoreEsposto == other.penalitaTempoVoloPerConnettoreEsposto;
	}
	
}
