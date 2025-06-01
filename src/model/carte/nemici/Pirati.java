package model.carte.nemici;

import java.util.List;
import java.util.Objects;

import controller.partita.fasiGioco.volo.ManagerDiVolo;
import model.carte.TipoCarta;
import model.carte.colpo.*;
import model.carte.criteriEffetti.*;
import view.TextAligner;
import view.formattatori.FormattatoreGrafico;
import view.io.GestoreIO;
import view.renderer.CarteRenderer;

public class Pirati extends Nemico {

	private final int numeroCrediti;
	private final List<Colpo> cannonate;

	private final GestoreIO io = new GestoreIO();
	private final GestoreColpi gestoreColpi = new GestoreColpi();
	
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
			Effetto.GIORNI_VOLO.applica(manager, -super.getPerditaGiorniDiVolo());
		}
		// se no: niente premio, ma niente perdita di giorni
	}

	@Override
	public void applicaSconfitta(List<ManagerDiVolo> sconfitti) {
		// subiscono cannonate
		gestoreColpi.gestioneColpi(sconfitti, cannonate);
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(cannonate, numeroCrediti);
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
		Pirati other = (Pirati) obj;
		return Objects.equals(cannonate, other.cannonate) && numeroCrediti == other.numeroCrediti;
	}
}
