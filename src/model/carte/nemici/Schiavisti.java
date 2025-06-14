package model.carte.nemici;

import java.util.List;
import java.util.Objects;

import controller.partita.fasiGioco.volo.ManagerDiVolo;
import model.carte.TipoCarta;
import model.carte.criteriEffetti.Effetto;
import view.formattatori.FormattatoreGrafico;
import view.io.GestoreIO;

public class Schiavisti extends Nemico {

	private final int numeroCrediti;
	private final int equipaggioPerso;

	private final GestoreIO io = new GestoreIO();

	public Schiavisti(int forza, int numCrediti, int perditaGiorniDiVolo, int equipaggioPerso) {
		super(TipoCarta.SCHIAVISTI, forza, Effetto.GUADAGNA_CREDITI, Effetto.PERDITA_EQUIPAGGIO, perditaGiorniDiVolo);
		this.numeroCrediti = numCrediti;
		this.equipaggioPerso = equipaggioPerso;
	}

	@Override
	public void applicaVittoria(ManagerDiVolo manager) {
		FormattatoreGrafico formattatoreGrafico = new FormattatoreGrafico();
		
		io.stampa(formattatoreGrafico.formatta(manager.getGiocatore()) + " ha vinto contro gli schiavisti!");
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
	public void spiegaVittoria() {
		io.aCapo();
		io.stampa("Chi sconfigge gli schiavisti guadagna " + numeroCrediti + " crediti.");
	}

	@Override
	public void applicaSconfitta(List<ManagerDiVolo> sconfitti) {
		for(ManagerDiVolo sconfitto: sconfitti)
			super.getEffettoSconfitta().applica(sconfitto, equipaggioPerso);
	}

	@Override
	public void spiegaSconfitta() {
		io.aCapo();
		io.stampa("Chi viene sconfitto dagli schiavisti perde " + equipaggioPerso + " membri dell'equipaggio.");
	}

	public int getNumeroCrediti() {
		return numeroCrediti;
	}

	public int getEquipaggioPerso() {
		return equipaggioPerso;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(equipaggioPerso, numeroCrediti);
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
		Schiavisti other = (Schiavisti) obj;
		return equipaggioPerso == other.equipaggioPerso && numeroCrediti == other.numeroCrediti;
	}
}
