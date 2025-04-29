package model.carte;

import java.util.ArrayList;
import java.util.List;

import model.carte.colpo.*;
import model.carte.criteriEffetti.CriterioConEffetto;
import model.enums.Direzione;
import partita.fasiGioco.ManagerDiVolo;

public class PioggiaDiMeteoriti extends Carta implements GestoreColpi {

	// Lista che contiene i meteoriti associati alla direzione da cui arrivano
	private final List<Colpo> meteoriti;

	@SuppressWarnings("unchecked")
	public PioggiaDiMeteoriti(CriterioConEffetto effetto) {
		super(TipoCarta.PIOGGIA_DI_METEORITI);
		this.meteoriti = ((List<Colpo>) effetto.getValore());
	}

	@Override
	public void eseguiEvento(ManagerDiVolo[] listaManager) {
		// Stampo tutti i meteoriti
		io.stampa(super.textAligner.alignCenter("Pioggia di meteoriti"));
		gestioneColpi(listaManager, meteoriti);
		io.aCapo();
		io.stampa("Fine della pioggia di meteoriti");
		io.aCapo();
	}

	public List<Colpo> getMeteoriti() {
		return new ArrayList<>(meteoriti);
	}

	public List<Colpo> getMeteoritiPerDirezione(Direzione direzione) {
		return Colpo.getColpiByDirezione(meteoriti, direzione);
	}

}
