package model.carte;

import java.util.ArrayList;
import java.util.List;

import model.Colpo;
import model.Giocatore;
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
	public void eseguiEvento(Giocatore[] giocatori) {

		for (CriterioConEffetto criterioEpenalita : criteriEpenalita) {

			Criterio criterio = criterioEpenalita.getCriterio();
			Effetto effetto = criterioEpenalita.getPenalita();
			Object valore = criterioEpenalita.getValore();

			Giocatore peggiorGiocatore = criterio.trovaPeggiore(giocatori);
			io.stampa("Il giocatore risultato peggiore secondo il criterio: " + criterio.name() + " è "
					+ super.formattatoreGrafico.formattaGiocatore(peggiorGiocatore));
			effetto.applica(peggiorGiocatore, valore);
		}
		io.aCapo();
		io.stampa("Fine della zona di guerra");
		io.aCapo();
	}
}
