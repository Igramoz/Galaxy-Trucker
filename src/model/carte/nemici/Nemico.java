package model.carte.nemici;

import java.util.List;

import model.Giocatore;
import model.carte.Carta;
import model.carte.TipoCarta;
import model.carte.criteriEffetti.Effetto;
import partita.fasiGioco.volo.ManagerDiVolo;

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
		io.stampa("Carta: " + this.getTipoCarta().name());
		io.stampa("Tutti i giocatori che hanno una nave con potenza di fuoco minore di " + getForzaNemico()
				+ " verrano sconfitti.");
		spiegaSconfitta();
		spiegaVittoria();
		io.stampa("ma perderai " + GiorniDiVoloPersi + " giorni di volo.");

		for (ManagerDiVolo m : listaManager) {
			Giocatore giocatore = m.getGiocatore();
			if (giocatore.getNave().getPotenzaFuoco() < getForzaNemico()) {
				// applica sconfitta
				io.stampa(formattatoreGrafico.formatta(giocatore) + " ha perso contro il nemico avanzato.");
			} else {
				// applica vittoria
				applicaVittoria(m);
			}
		}
		applicaSconfitta(sconfitti);
		io.stampa("La carta: " + this.getTipoCarta().name() + " è stata risolta." );		
	}

}
