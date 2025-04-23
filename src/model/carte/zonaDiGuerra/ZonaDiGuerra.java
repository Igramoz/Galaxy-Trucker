package model.carte.zonaDiGuerra;

import java.util.ArrayList;
import java.util.List;

import model.Colpo;
import model.Giocatore;
import model.carte.Carta;
import model.enums.TipoCarta;

public class ZonaDiGuerra extends Carta {

	List<PenalitaConCriterio> criteriEpenalita;

	public ZonaDiGuerra(List<PenalitaConCriterio> criteriEpenalita, List<Colpo> cannonate) {
		super(TipoCarta.ZONA_DI_GUERRA);
		this.criteriEpenalita = criteriEpenalita;
	}

	public List<PenalitaConCriterio> getCriteriEpenalita() {
		return new ArrayList<>(criteriEpenalita);
	}

	@Override
	public void eseguiEvento(Giocatore[] giocatori) {

		for (PenalitaConCriterio criterioEpenalita : criteriEpenalita) {

			Criterio criterio = criterioEpenalita.getCriterio();
			Penalita penalita = criterioEpenalita.getPenalita();
			Object valore = criterioEpenalita.getValore();

			Giocatore peggiorGiocatore = criterio.trovaPeggiore(giocatori);
			io.stampa("Il giocatore risultato peggiore secondo il criterio: " + criterio.name() + " è "
					+ super.formattatoreGrafico.formattaGiocatore(peggiorGiocatore));
			penalita.applica(peggiorGiocatore, valore);
		}
		io.aCapo();
		io.stampa("Fine della zona di guerra");
		io.aCapo();
	}
}
