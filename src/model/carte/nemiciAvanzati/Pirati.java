package model.carte.nemiciAvanzati;

import java.util.List;

import grafica.TextAligner;
import grafica.renderer.CarteRenderer;
import io.GestoreIO;
import model.Giocatore;
import model.carte.TipoCarta;
import model.carte.colpo.Colpo;
import model.carte.criteriEffetti.*;
import partita.fasiGioco.ManagerDiVolo;

public class Pirati extends NemicoAvanzato {

	private final int numeroCrediti;
	private final List<Colpo> cannonate;

	private final GestoreIO io = new GestoreIO();

	public Pirati(int forza, int numCrediti, int perditaGiorniDiVolo, List<Colpo> cannonate) {
		super(TipoCarta.PIRATI, forza, Effetto.GUADAGNA_CREDITI, Effetto.COLPI, perditaGiorniDiVolo);
		this.numeroCrediti = numCrediti;
		this.cannonate = cannonate;
	}

	@Override
	public void applicaVittoria(ManagerDiVolo manager) {
		io.stampa("Hai vinto contro i pirati!");
		io.stampa("Scrivere 1 se sei disposto a perdere " + getPerditaGiorniDiVolo() + " giorni di volo per guadagnare "
				+ numeroCrediti + " crediti.");
		int scelta = io.leggiIntero();
		// se sì: giocatore arretra e guadagna crediti
		if (scelta == 1) {
			super.getEffettoVittoria().applica(manager, numeroCrediti);
			Effetto.GIORNI_VOLO.applica(manager, super.getPerditaGiorniDiVolo());
		}
		// se no: niente premio, niente perdita di giorni
	}

	@Override
	public void applicaSconfitta(ManagerDiVolo manager, List<ManagerDiVolo> sconfitti) {
		// aggiungi il giocatore alla lista degli sconfitti
		sconfitti.add(manager);
		// più avanti: danni comuni
	}

	@Override
	public void spiegaVittoria() {
		io.aCapo();
		io.stampa("Se vinci contro i pirati, puoi guadagnare " + numeroCrediti + " crediti,");
	}

	@Override
	public void spiegaSconfitta() {
		CarteRenderer carteRenderer = new CarteRenderer();
		TextAligner textAligner = new TextAligner();
		io.aCapo();
		io.stampa(textAligner.alignCenter("e subiranno queste cannonate: "));
		io.stampa(carteRenderer.rappresentaColpi(cannonate));

	}

}
