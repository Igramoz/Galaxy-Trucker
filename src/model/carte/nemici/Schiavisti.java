package model.carte.nemici;

import java.util.List;

import grafica.formattatori.FormattatoreGrafico;
import io.GestoreIO;
import model.carte.TipoCarta;
import model.carte.criteriEffetti.Effetto;
import partita.fasiGioco.volo.ManagerDiVolo;

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
			Effetto.GIORNI_VOLO.applica(manager, super.getPerditaGiorniDiVolo());
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
}
