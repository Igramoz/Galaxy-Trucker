package partita.fasiGioco.volo;

import eccezioni.GiocatoreNonSpostabile;
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
		
		try {
			plancia.spostaGiocatore(giorniDiVolo, giocatore);
		}catch( GiocatoreNonSpostabile e) {
			return false; // se il giocatore non può essere spostato restituisce false
		}
		
		return true; // se il giocatore può essere spostato restituisce true

	}
	
	public Integer getGiorniDiVoloGiocatore() {
		return plancia.getGiorniDiVoloGiocatore(giocatore); // restituisce i giorni di volo del giocatore
	}

	public Giocatore getGiocatore() {
		return giocatore;
	}
	
    // Metodi pezzi distrutti
    public int getPezziDistrutti() {
    	return this.pezziDistrutti;
    }
    
    public void incrementaPezziDistrutti(int numPezziDistrutti) {
        this.pezziDistrutti += numPezziDistrutti;
    }
    
    public boolean isDoppiato() {	
		// controlla se il giocatore è doppiato
		
		float giroGiocatore = plancia.getNumeroGiroGiocatore(giocatore); // numero di giro del giocatore	
		
		for (Giocatore giocatore : plancia.getGiocatori()) {
	
			if(plancia.getNumeroGiroGiocatore(giocatore) - giroGiocatore > 1) {
				return true; // giornidivolo/lunghezzaPlancia = numero giro, se numero giro > giroGiocatore + 1 vuol dire che il giocatore è doppiato
			}
		}
		
		return false; // se il giocatore non è doppiato restituisce false
		
	}
    
    public void AbbandonaVolo() {
		plancia.abbandonaVolo(giocatore); // il giocatore abbandona il volo
	}
}
