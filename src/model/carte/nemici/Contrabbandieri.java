package model.carte.nemici;

import java.util.List;

import grafica.FormattatoreGrafico;
import io.GestoreIO;
import model.carte.TipoCarta;
import model.carte.criteriEffetti.Effetto;
import model.enums.TipoMerce;
import partita.fasiGioco.ManagerDiVolo;

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
		io.stampa(formattatoreGrafico.formattaGiocatore(manager.getGiocatore()) + " ha vinto contro i contrabbandieri!");
		io.stampa("Scrivere 1 se sei disposto a perdere " + getPerditaGiorniDiVolo() + " giorni di volo per guadagnare le seguenti merci");
		io.stampa(formattatoreGrafico.formattaMerce(merci));
		int scelta = io.leggiIntero();
		// se sì: giocatore arretra e guadagna crediti
		if (scelta == 1) {
			super.getEffettoVittoria().applica(manager, merci);
			Effetto.GIORNI_VOLO.applica(manager, super.getPerditaGiorniDiVolo());
		}
		// se no: niente premio, ma niente perdita di giorni
	}

	@Override
	public void spiegaVittoria() {
		io.stampa("Chi sconfigge i contrabbandieri potrà caricare le seguenti merci:");
		io.stampa(formattatoreGrafico.formattaMerce(merci));
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
}
