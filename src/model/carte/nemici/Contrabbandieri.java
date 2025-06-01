package model.carte.nemici;

import java.util.List;
import java.util.Objects;

import controller.partita.fasiGioco.volo.ManagerDiVolo;
import model.carte.TipoCarta;
import model.carte.criteriEffetti.Effetto;
import model.enums.TipoMerce;
import view.formattatori.FormattatoreGrafico;
import view.io.GestoreIO;

public class Contrabbandieri extends Nemico{
	
	private final List<TipoMerce> merci; // merci in palio
	private final int mercePersa; // numero merci da cedere in caso di sconfitta

	private final GestoreIO io = new GestoreIO();
	private final FormattatoreGrafico formattatoreGrafico = new FormattatoreGrafico();
	
	public Contrabbandieri(int forza, List<TipoMerce> merci, int perditaGiorniDiVolo, int mercePersa) {
		super(TipoCarta.CONTRABBANDIERI, forza, Effetto.GUADAGNA_MERCE, Effetto.PERDITA_MERCE, perditaGiorniDiVolo);
		this.merci = merci;
		this.mercePersa = mercePersa;
	}

	@Override
	public void applicaVittoria(ManagerDiVolo manager) {
		io.stampa(formattatoreGrafico.formatta(manager.getGiocatore()) + " ha vinto contro i contrabbandieri!");
		io.stampa("Scrivere 1 se sei disposto a perdere " + getPerditaGiorniDiVolo() + " giorni di volo per guadagnare le seguenti merci");
		formattatoreGrafico.formattaEStampaMerci(merci);
		int scelta = io.leggiIntero();
		// se sì: giocatore arretra e guadagna crediti
		if (scelta == 1) {
			super.getEffettoVittoria().applica(manager, merci);
			Effetto.GIORNI_VOLO.applica(manager, -super.getPerditaGiorniDiVolo());
		}
		// se no: niente premio, ma niente perdita di giorni
	}

	@Override
	public void spiegaVittoria() {
		io.stampa("Chi sconfigge i contrabbandieri potrà caricare le seguenti merci:");
		formattatoreGrafico.formattaEStampaMerci(merci);
	}

	@Override
	public void applicaSconfitta(List<ManagerDiVolo> sconfitti) {
		for(ManagerDiVolo sconfitto : sconfitti) {
			super.getEffettoSconfitta().applica(sconfitto, mercePersa);
		}
	}

	@Override
	public void spiegaSconfitta() {
		io.aCapo();
		io.stampa("Chi viene sconfitto dai contrabbandieri perde " + mercePersa + " merci");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(mercePersa, merci);
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
		Contrabbandieri other = (Contrabbandieri) obj;
		return mercePersa == other.mercePersa && Objects.equals(merci, other.merci);
	}
}
