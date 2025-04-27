package model.carte.nemiciAvanzati;

import java.util.List;

import model.Giocatore;
import model.carte.Carta;
import model.carte.TipoCarta;

public abstract class NemicoAvanzato extends Carta {
	private final int forzaNemico;
	private final int valoreRicompensa;
	private final int perditaGiorniDiVolo;

	public NemicoAvanzato(TipoCarta tipoCarta, int forzaNemico, int valoreRicompensa, int perditaGiorniDiVolo) {
		super(tipoCarta);
		this.forzaNemico = forzaNemico;
		this.valoreRicompensa = valoreRicompensa;
		this.perditaGiorniDiVolo = perditaGiorniDiVolo;
	}

	public abstract void applicaVittoria(Giocatore giocatore);
	public abstract void spiegaVittoria();
	public abstract void applicaSconfitta(Giocatore giocatore, List<Giocatore> sconfitti);
	public abstract void spiegaSconfitta();
	
	public int getForzaNemico() {
		return forzaNemico;
	}

	public int getValoreRicompensa() {
		return valoreRicompensa;
	}

	public int getPerditaGiorniDiVolo() {
		return perditaGiorniDiVolo;
	}
	
	@Override
	public void eseguiEvento(Giocatore[] giocatori) {
		List<Giocatore> sconfitti = new java.util.ArrayList<>();
		io.aCapo();
		io.stampa("Carta: " + this.getTipoCarta().name());
		io.stampa("Tutti i giocatori che hanno una nave con potenza di fuoco minore di " + getForzaNemico() + " verrano sconfitti.");
		spiegaSconfitta();
		io.stampa("Mentre il vincitore avrà la possiblità di ");
		
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
