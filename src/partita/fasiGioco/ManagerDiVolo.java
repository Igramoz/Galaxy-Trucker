package partita.fasiGioco;

import model.Giocatore;
import model.planciaDiVolo.*;

public class ManagerDiVolo {

	private final Giocatore giocatore;
	private final Plancia plancia;

	public ManagerDiVolo(Giocatore giocatore, Plancia plancia) {
		this.giocatore = giocatore;
		this.plancia = plancia;
		plancia.getGiorniDiVoloGiocatore(giocatore); // inizializza i giorni di volo del giocatore in base alla plancia

	}

	public boolean spostaGiocatore(int giorniDiVolo) {

		return plancia.spostaGiocatore(giorniDiVolo, giocatore);

	}

	public int getGiorniDiVoloGiocatore() {
		return plancia.getGiorniDiVoloGiocatore(giocatore); // restituisce i giorni di volo del giocatore
	}

	public Giocatore getGiocatore() {
		return giocatore;
	}

	public void aumentaGiorniDiVolo(int giorni) {
		plancia.spostaGiocatore(giorni, giocatore);
	}
}
