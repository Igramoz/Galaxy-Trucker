package model.carte;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import controller.partita.fasiGioco.volo.ManagerDiVolo;
import model.carte.colpo.Colpo;
import model.carte.criteriEffetti.Criterio;
import model.carte.criteriEffetti.CriterioConEffetto;
import model.carte.criteriEffetti.Effetto;

public class ZonaDiGuerra extends Carta {

	List<CriterioConEffetto> criteriEpenalita;

	public ZonaDiGuerra(List<CriterioConEffetto> criteriEpenalita, List<Colpo> cannonate) {
		super(TipoCarta.ZONA_DI_GUERRA);
		this.criteriEpenalita = criteriEpenalita;
	}

	public List<CriterioConEffetto> getCriteriEpenalita() {
		return new ArrayList<>(criteriEpenalita);
	}

	@Override
	public void eseguiEvento(ManagerDiVolo[] listaManager) {

		io.stampa(textAligner.alignCenter("Carta zona di guerra"));
		io.aCapo();
		for (CriterioConEffetto criterioEpenalita : criteriEpenalita) {

			Criterio criterio = criterioEpenalita.getCriterio();
			Effetto effetto = criterioEpenalita.getPenalita();
			Object valore = criterioEpenalita.getValore();

			ManagerDiVolo peggiorManager = criterio.trovaPeggiore(listaManager);
			io.stampa("Il giocatore risultato peggiore secondo il criterio: " + criterio.name() + " è "
					+ super.formattatoreGrafico.formatta(peggiorManager.getGiocatore()));
			effetto.applica(peggiorManager, valore);
		}
		io.aCapo();
		io.stampa("Fine della zona di guerra");
		io.aCapo();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(criteriEpenalita);
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
		ZonaDiGuerra other = (ZonaDiGuerra) obj;
		return Objects.equals(criteriEpenalita, other.criteriEpenalita);
	}
}
