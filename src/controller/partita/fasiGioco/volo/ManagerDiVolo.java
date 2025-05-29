package controller.partita.fasiGioco.volo;

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

	public void spostaGiocatore(int giorniDiVolo){
		
			plancia.spostaGiocatore(giorniDiVolo, giocatore);

	}
	/**
	 * funzione per ottenere i giorni di volo del giocatore
	 * @param giocatore
	 * @return null se il giocatore non è in volo, altrimenti il numero di giorni di volo
	 */
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
    	
    	if(!plancia.isInVolo(giocatore)) return false;
		
		float giroGiocatore = plancia.getNumeroGiroGiocatore(giocatore); // numero di giro del giocatore
		
		
		for (Giocatore g1 : plancia.getGiocatori()) {
			if (g1.equals(giocatore)) continue;
			if (!plancia.isInVolo(g1)) continue;

			float giroAltro = plancia.getNumeroGiroGiocatore(g1);
			if (giroAltro - giroGiocatore > 1) {
				return true;
			}
		}
		
		return false; // se il giocatore non è doppiato restituisce false
		
	}
    
    public void AbbandonaVolo() {
		plancia.abbandonaVolo(giocatore); // il giocatore abbandona il volo
	}
    
    /**
     * controlla se io giocatore ha abbandonato il volo o meno
     * @return true se è ancora in volo, false se ha abbanato il volo
     */
    public boolean isInVolo() {
    	if(getGiorniDiVoloGiocatore() ==  null)
    		return false;
    	else 
    		return true;
    }
    
}
