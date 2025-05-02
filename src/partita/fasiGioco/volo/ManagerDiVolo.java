package partita.fasiGioco.volo;

import model.Giocatore;
import model.planciaDiVolo.*;

public class ManagerDiVolo {

	private final Giocatore giocatore;
	private final Plancia plancia;
	private int pezziDistrutti = 0;

	public ManagerDiVolo(Giocatore giocatore, Plancia plancia) {
		this.giocatore = giocatore;
		this.plancia = plancia;
		plancia.getGiorniDiVoloGiocatore(giocatore); // inizializza i giorni di volo del giocatore in base alla plancia

	}

	public boolean spostaGiocatore(int giorniDiVolo) {

		return plancia.spostaGiocatore(giorniDiVolo, giocatore);

	}
// TODO Integer, vale null se il giocatore ha abbandonato il volo
	public int getGiorniDiVoloGiocatore() {
		return plancia.getGiorniDiVoloGiocatore(giocatore); // restituisce i giorni di volo del giocatore
	}

	public Giocatore getGiocatore() {
		return giocatore;
	}

	public void aumentaGiorniDiVolo(int giorni) {
		plancia.spostaGiocatore(giorni, giocatore);
	}
	
    // Metodi pezzi distrutti
    public int getPezziDistrutti() {
    	return this.pezziDistrutti;
    }
    
    public void incrementaPezziDistrutti(int numPezziDistrutti) {
        this.pezziDistrutti += numPezziDistrutti;
    }
}
