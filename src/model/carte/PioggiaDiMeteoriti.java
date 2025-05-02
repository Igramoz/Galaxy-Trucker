package model.carte;

import java.util.ArrayList;
import java.util.List;

import model.carte.colpo.*;
import model.carte.criteriEffetti.CriterioConEffetto;
import model.carte.criteriEffetti.Effetto;
import partita.fasiGioco.volo.ManagerDiVolo;
import util.layout.Direzione;

public class PioggiaDiMeteoriti extends Carta implements GestoreColpi {

	// Lista che contiene i meteoriti associati alla direzione da cui arrivano
	private final List<Colpo> meteoriti;
	private final Effetto effetto = Effetto.COLPI;
	
	public Effetto getEffetto() {
		return effetto;
	}

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
