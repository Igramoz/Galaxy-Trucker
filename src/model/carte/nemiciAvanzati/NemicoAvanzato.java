package model.carte.nemiciAvanzati;

import java.util.List;

import model.Giocatore;
import model.carte.Carta;
import model.carte.TipoCarta;
import model.carte.criteriEffetti.Effetto;

public abstract class NemicoAvanzato extends Carta {
	
	private final int forzaNemico;
	private final int GiorniDiVoloPersi;
	private final Effetto effettoVittoria;
	private final Effetto effettoSconfitta;

	
	public NemicoAvanzato(TipoCarta tipoCarta, int forzaNemico, Effetto effettoVittoria, Effetto effettoSconfitta, int GiorniDiVoloPersi) {
		super(tipoCarta);
		this.forzaNemico = forzaNemico;
		this.effettoVittoria =  effettoVittoria;
		this.effettoSconfitta=  effettoSconfitta;
		this.GiorniDiVoloPersi = GiorniDiVoloPersi;
	}
	
	public abstract void applicaVittoria(Giocatore giocatore);
	public abstract void spiegaVittoria();
	public abstract void applicaSconfitta(Giocatore giocatore, List<Giocatore> sconfitti);
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
	public void eseguiEvento(Giocatore[] giocatori) {
		List<Giocatore> sconfitti = new java.util.ArrayList<>();
		io.aCapo();
		io.stampa("Carta: " + this.getTipoCarta().name());
		io.stampa("Tutti i giocatori che hanno una nave con potenza di fuoco minore di " + getForzaNemico() + " verrano sconfitti.");
		spiegaSconfitta();
		spiegaVittoria();
		io.stampa("ma perderai " + GiorniDiVoloPersi + " giorni di volo.");
		
		for (Giocatore giocatore : giocatori) {
			if (giocatore.getNave().getPotenzaFuoco() < getForzaNemico()) {
				// applica sconfitta
				io.stampa(formattatoreGrafico.formattaGiocatore(giocatore) + " ha perso contro il nemico avanzato.");
				applicaSconfitta(giocatore, sconfitti);
			} else {
				// applica vittoria
				applicaVittoria(giocatore);
			}
		}
		
	}

	
}
