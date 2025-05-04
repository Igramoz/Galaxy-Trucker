package model.carte.nemici;

import java.util.List;

import grafica.TextAligner;
import grafica.formattatori.FormattatoreGrafico;
import grafica.renderer.CarteRenderer;
import io.GestoreIO;
import model.carte.TipoCarta;
import model.carte.colpo.*;
import model.carte.criteriEffetti.*;
import partita.fasiGioco.volo.ManagerDiVolo;

public class Pirati extends Nemico implements GestoreColpi {

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
		FormattatoreGrafico formattatoreGrafico = new FormattatoreGrafico();
		io.stampa(formattatoreGrafico.formatta(manager.getGiocatore()) + " ha vinto contro i pirati!");
		io.stampa("Scrivere 1 se sei disposto a perdere " + getPerditaGiorniDiVolo() + " giorni di volo per guadagnare "
				+ numeroCrediti + " crediti.");
		int scelta = io.leggiIntero();
		// se sì: giocatore arretra e guadagna crediti
		if (scelta == 1) {
			super.getEffettoVittoria().applica(manager, numeroCrediti);
			Effetto.GIORNI_VOLO.applica(manager, super.getPerditaGiorniDiVolo());
		}
		// se no: niente premio, ma niente perdita di giorni
	}

	@Override
	public void applicaSconfitta(List<ManagerDiVolo> sconfitti) {
		// subiscono cannonate
		gestioneColpi(sconfitti, cannonate);
	}

	@Override
	public void spiegaVittoria() {
		io.aCapo();
		io.stampa("Chi sconfigge i pirati guadagna " + numeroCrediti + " crediti.");
	}

	@Override
	public void spiegaSconfitta() {
		CarteRenderer carteRenderer = new CarteRenderer();
		TextAligner textAligner = new TextAligner();
		io.aCapo();
		io.stampa(textAligner.alignCenter("e subiranno queste cannonate: "));
		carteRenderer.rappresentaColpi(cannonate);
	}

	public int getNumeroCrediti() {
		return numeroCrediti;
	}

	public List<Colpo> getCannonate() {
		return cannonate;
	}
}
