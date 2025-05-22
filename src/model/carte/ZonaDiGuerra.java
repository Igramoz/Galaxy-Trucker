package model.carte;

import java.util.ArrayList;
import java.util.List;
import model.carte.colpo.Colpo;
import model.carte.criteriEffetti.Criterio;
import model.carte.criteriEffetti.CriterioConEffetto;
import model.carte.criteriEffetti.Effetto;
import partita.fasiGioco.volo.ManagerDiVolo;

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
}
