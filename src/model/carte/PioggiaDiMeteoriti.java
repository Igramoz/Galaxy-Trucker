package model.carte;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import controller.partita.fasiGioco.volo.ManagerDiVolo;
import model.carte.colpo.*;
import model.carte.criteriEffetti.CriterioConEffetto;
import model.carte.criteriEffetti.Effetto;
import util.layout.Direzione;

public class PioggiaDiMeteoriti extends Carta{

	// Lista che contiene i meteoriti associati alla direzione da cui arrivano
	private final List<Colpo> meteoriti;
	private final Effetto effetto = Effetto.COLPI;
	private final GestoreColpi gestoreColpi = new GestoreColpi();
	
	
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
		gestoreColpi.gestioneColpi(listaManager, meteoriti);
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(effetto, gestoreColpi, meteoriti);
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
		PioggiaDiMeteoriti other = (PioggiaDiMeteoriti) obj;
		return effetto == other.effetto && Objects.equals(gestoreColpi, other.gestoreColpi)
				&& Objects.equals(meteoriti, other.meteoriti);
	}

}
