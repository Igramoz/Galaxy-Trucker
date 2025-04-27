package model.carte.nemiciAvanzati;

import java.util.List;

import model.Giocatore;
import model.carte.TipoCarta;

public class Pirati extends NemicoAvanzato{

	public Pirati(int forza, int valoreRicompensa, int perditaGiorniDiVolo) {
		super(TipoCarta.PIRATI, forza, valoreRicompensa, perditaGiorniDiVolo);
	}

	@Override
	public void applicaVittoria(Giocatore giocatore) {
		// scelta: vuoi arretrare per ottenere crediti o no?
		// se sì: giocatore arretra e guadagna crediti
		// se no: niente premio, niente perdita di giorni
	}

	@Override
	public void applicaSconfitta(Giocatore giocatore, List<Giocatore> sconfitti) {
		// aggiungi il giocatore alla lista degli sconfitti
		sconfitti.add(giocatore);
		// più avanti: danni comuni
	}
	

	
}
