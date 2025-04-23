package model.planciaDiVolo;

import model.Giocatore;



public class Plancia {
	
	private Giocatore[] giocatori; // lista dei giocatori in gioco
	private final Giocatore[] plancia;// lista dei giocatori nei loro giorni di volo in gioco che si aggiorna in base alla modalita di gioco
	private final TipoPlancia tipoPlancia; // tipo di plancia in base alla modalita di gioco
	
	
	public Plancia(Giocatore[] giocatori, TipoPlancia tipoPlancia) {
		
		this.giocatori = giocatori;
		this.tipoPlancia = tipoPlancia;

		this.plancia = new Giocatore[tipoPlancia.getLunghezza()]; // inizializza la plancia in base alla lunghezza della plancia
		
		// metto i giocatori nella plancia in base alla modalita di gioco
		
		

		for(int i =0; i<tipoPlancia.getPosizioni().length && i<tipoPlancia.getLunghezza(); i++) {

			plancia[tipoPlancia.getPosizioni()[i]] = giocatori[i]; // metto i giocatori nella plancia in base alla modalita di gioco
			
		}
		
	}

	public Giocatore[] getGiocatori() {
		return giocatori;
	}

	//sposta un giocatore per n giorni di volo
	public void spostaGiocatore(int giorniVolo, Giocatore giocatore) {

		// Controlla se il numero di giorni di volo è positivo

		if(giorniVolo < 0) {
			// Se i giorni di volo sono negativi, sposta indietro
			for (int i = 0; i < Math.abs(giorniVolo); i++) {
				spostaIndietro(giocatore);
			}
		} else if(giorniVolo > 0) {
			// Se i giorni di volo sono positivi, sposta avanti
			for (int i = 0; i < giorniVolo; i++) {
				spostaAvanti(giocatore);
			}
		}
		
	}

	public Giocatore[] getPlancia() {
		return plancia;
	}

	//sposta un giocatore indietro di un giorno di volo
	private void spostaIndietro(Giocatore giocatore) {
	    int pos = -1;

	    // Trova la posizione attuale del giocatore
	    for (int i = 0; i < tipoPlancia.getLunghezza(); i++) {
	        if (plancia[i] == giocatore) {
	            pos = i;
	            break;
	        }
	    }

	    if (pos == -1) {
	        throw new IllegalArgumentException("Il giocatore non è presente sulla plancia.");
	    }

	    int nuovaPos = (pos - 1 + tipoPlancia.getLunghezza()) % tipoPlancia.getLunghezza();
	    int tentativi = 0;

	    // Cerca la prima posizione libera indietro, saltando quelle occupate
	    while (plancia[nuovaPos] != null && tentativi < tipoPlancia.getLunghezza()) {
	        nuovaPos = (nuovaPos - 1 + tipoPlancia.getLunghezza()) % tipoPlancia.getLunghezza();
	        tentativi++;
	    }

	    // Se la plancia è piena (nessuna posizione libera), non fare nulla
	    if (tentativi == tipoPlancia.getLunghezza()) {
	        return;
	    }

	    // Sposta il giocatore alla nuova posizione libera
	    
	    plancia[nuovaPos] = giocatore;
	    plancia[pos] = null;
	}


	//sposta un giocatore avanti di un giorno di volo
	private void spostaAvanti(Giocatore giocatore) {
	    int pos = -1;

	    // Trova la posizione attuale del giocatore
	    for (int i = 0; i < tipoPlancia.getLunghezza(); i++) {
	        if (plancia[i] == giocatore) {
	            pos = i;
	            break;
	        }
	    }

	    if (pos == -1) {
	        throw new IllegalArgumentException("Il giocatore non è presente sulla plancia.");
	    }

	    int nuovaPos = (pos + 1) % tipoPlancia.getLunghezza();
	    int tentativi = 0;

	    // Cerca la prima posizione libera, saltando quelle occupate
	    while (plancia[nuovaPos] != null && tentativi < tipoPlancia.getLunghezza()) {
	        nuovaPos = (nuovaPos + 1) % tipoPlancia.getLunghezza();
	        tentativi++;
	    }

	    // Se la plancia è piena (nessuna posizione libera), non fare nulla
	    if (tentativi == tipoPlancia.getLunghezza()) {
	        return;
	    }

	    // Sposta il giocatore alla nuova posizione libera
	    
	    plancia[nuovaPos] = giocatore;
	    plancia[pos] = null;
	}
}