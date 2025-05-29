package model.carte.nemici;

import java.util.List;
import java.util.Objects;

import controller.partita.fasiGioco.volo.ManagerDiVolo;
import model.Giocatore;
import model.carte.Carta;
import model.carte.TipoCarta;
import model.carte.criteriEffetti.Effetto;

public abstract class Nemico extends Carta {

	private final int forzaNemico;
	private final int GiorniDiVoloPersi;
	private final Effetto effettoVittoria;
	private final Effetto effettoSconfitta;

	public Nemico(TipoCarta tipoCarta, int forzaNemico, Effetto effettoVittoria, Effetto effettoSconfitta,
			int GiorniDiVoloPersi) {
		super(tipoCarta);
		this.forzaNemico = forzaNemico;
		this.effettoVittoria = effettoVittoria;
		this.effettoSconfitta = effettoSconfitta;
		this.GiorniDiVoloPersi = GiorniDiVoloPersi;
	}

	public abstract void applicaVittoria(ManagerDiVolo manager);

	public abstract void spiegaVittoria();

	public abstract void applicaSconfitta(List<ManagerDiVolo> sconfitti);

	public abstract void spiegaSconfitta();

	public int getForzaNemico() {
		return forzaNemico;
	}

	public int getPerditaGiorniDiVolo() {
		return GiorniDiVoloPersi;
	}

	public Effetto getEffettoVittoria() {
		return effettoVittoria;
	}

	public Effetto getEffettoSconfitta() {
		return effettoSconfitta;
	}

	@Override
	public void eseguiEvento(ManagerDiVolo[] listaManager) {
		List<ManagerDiVolo> sconfitti = new java.util.ArrayList<>();
		io.aCapo();
		io.stampa(textAligner.alignCenter("Carta: " + this.getTipoCarta().name()));
		io.stampa("Tutti i giocatori che hanno una nave con potenza di fuoco minore di " + getForzaNemico()
				+ " verrano sconfitti.");
		spiegaSconfitta();
		spiegaVittoria();
		io.stampa("ma perderai " + GiorniDiVoloPersi + " giorni di volo.");

		for (ManagerDiVolo m : listaManager) {
			Giocatore giocatore = m.getGiocatore();
			if (giocatore.getNave().getPotenzaFuoco() < getForzaNemico()) {
				// aggiungo agli sconfitti
				sconfitti.add(m);
				io.stampa(formattatoreGrafico.formatta(giocatore) + " ha perso contro il nemico avanzato.");
			} else {
				// applica vittoria
				applicaVittoria(m);
			}
		}
		
		// applico la sconfitta
		if(!sconfitti.isEmpty())
			applicaSconfitta(sconfitti);
		
		io.stampa("La carta: " + this.getTipoCarta().name() + " è stata risolta." );		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(GiorniDiVoloPersi, effettoSconfitta, effettoVittoria, forzaNemico);
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
		Nemico other = (Nemico) obj;
		return GiorniDiVoloPersi == other.GiorniDiVoloPersi && effettoSconfitta == other.effettoSconfitta
				&& effettoVittoria == other.effettoVittoria && forzaNemico == other.forzaNemico;
	}

}
