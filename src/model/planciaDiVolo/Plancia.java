package model.planciaDiVolo;
import partita.LivelliPartita;
import Eccezioni.GiocatoreNonSpostabile;
import model.Giocatore;
import java.util.HashMap;
import java.util.Map;

/*
 * @throwable GiocatoreNonSpostabile
 * Eccezione lanciata quando un giocatore non può essere spostato
 * 
 */



public class Plancia {
	
	private final HashMap<Giocatore, Integer> GiorniDiVoloGiocatori =  new  HashMap<>(); // mappa che tiene traccia dei giorni di volo dei giocatori
	private final Giocatore[] plancia;// lista dei giocatori nei loro giorni di volo in gioco che si aggiorna in base alla modalita di gioco
	private final TipoPlancia tipoPlancia; // tipo di plancia in base alla modalita di gioco
	
	
	public Plancia(Giocatore[] giocatori, LivelliPartita livello) {
		
		this.tipoPlancia = livello.getTipoPlancia();

		this.plancia = new Giocatore[tipoPlancia.getLunghezza()]; // inizializza la plancia in base alla lunghezza della plancia
		
		// metto i giocatori nella plancia in base alla modalita di gioco
		
		

		for(int i =0; i<tipoPlancia.getPosizioni().length && i<tipoPlancia.getLunghezza(); i++) {

			plancia[tipoPlancia.getPosizioni()[i]] = giocatori[i]; // metto i giocatori nella plancia in base alla modalita di gioco
			
		}

		//memorizzo i giorni di volo dei giocatori nella mappa
		for(int i =0; i<tipoPlancia.getPosizioni().length && i<tipoPlancia.getLunghezza(); i++) {
			GiorniDiVoloGiocatori.put(giocatori[i], tipoPlancia.getPosizioni()[i]); // metto i giorni di volo dei giocatori nella mappa
		}
		
	}

	public int getGiorniDiVoloGiocatore(Giocatore giocatore) {
		 // restituisce i giorni di volo del giocatore se è presente nella mappa, se non è presente restituisce null

		if(GiorniDiVoloGiocatori.containsKey(giocatore)){
		
			return GiorniDiVoloGiocatori.get(giocatore); // restituisce i giorni di volo del giocatore
		}else {
			return (Integer) null;
		}
	}

	public Giocatore[] getGiocatori() {
		// Restituisce i giocatori nella plancia in ordine di giorni di volo chi ha piu giorni di volo è in cima all' array
		return GiorniDiVoloGiocatori.entrySet()
            .stream()
            .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue())) // Ordina per giorni di volo decrescenti
            .map(Map.Entry::getKey) // Ottieni solo i giocatori
            .toArray(Giocatore[]::new); // Converti in array
	}

	public float getNumeroGiroGiocatore(Giocatore giocatore) {
		// Restituisce il numero di giorni di volo del giocatore diviso per la lunghezza della plancia(se è uno vuol dire che il giocatore ha effettuato un giro completo della plancia )
		return (float)GiorniDiVoloGiocatori.getOrDefault(giocatore, 0)/tipoPlancia.getLunghezza(); // restituisce il numero di giorni di volo del giocatore
	}
	
	

	//sposta un giocatore per n giorni di volo
	public void spostaGiocatore(int giorniVolo, Giocatore giocatore) throws GiocatoreNonSpostabile {

		// Controlla se il numero di giorni di volo è positivo

		Integer pos = 0;

		if(giorniVolo < 0) {
			// Se i giorni di volo sono negativi, sposta indietro
			for (; pos > giorniVolo; pos--) {
				spostaIndietro(giocatore, pos);
			}
		} else if(giorniVolo > 0) {
			// Se i giorni di volo sono positivi, sposta avanti
			for (; pos < giorniVolo; pos++) {
				spostaAvanti(giocatore, pos);
				
			}
		}

		GiorniDiVoloGiocatori.put(giocatore, GiorniDiVoloGiocatori.getOrDefault(giocatore, 0) + giorniVolo + pos); // aggiorna i giorni di volo del giocatore


	}

	public Giocatore[] getPlancia() {
		return plancia;
	}

	//sposta un giocatore indietro di un giorno di volo
	private void  spostaIndietro(Giocatore giocatore, Integer posizioneFin) throws GiocatoreNonSpostabile{
	    int pos = -1;

	    // Trova la posizione attuale del giocatore
	    for (int i = 0; i < tipoPlancia.getLunghezza(); i++) {
	        if (plancia[i] == giocatore) {
	            pos = i;
	            break;
	        }
	    }

	    if (pos == -1) {
	    	throw new GiocatoreNonSpostabile("Giocatore non trovato nella plancia");
	        
	    }

	    int nuovaPos = (pos - 1 + tipoPlancia.getLunghezza()) % tipoPlancia.getLunghezza();
	    int tentativi = 0;

	    // Cerca la prima posizione libera indietro, saltando quelle occupate
	    while (plancia[nuovaPos] != null && tentativi < tipoPlancia.getLunghezza()) {
	        nuovaPos = (nuovaPos - 1 + tipoPlancia.getLunghezza()) % tipoPlancia.getLunghezza();
	        tentativi++;
			posizioneFin--;
	    }

	    // Se la plancia è piena (nessuna posizione libera), non fare nulla
	    if (tentativi == tipoPlancia.getLunghezza()) {
	        
	        throw new GiocatoreNonSpostabile("Giocatore non spostabile");
	    }

	    // Sposta il giocatore alla nuova posizione libera
	    
	    plancia[nuovaPos] = giocatore;
	    plancia[pos] = null;

		
	}


	//sposta un giocatore avanti di un giorno di volo
	private void  spostaAvanti(Giocatore giocatore, Integer posizioneFin) throws GiocatoreNonSpostabile{
	    int pos = -1;

	    // Trova la posizione attuale del giocatore
	    for (int i = 0; i < tipoPlancia.getLunghezza(); i++) {
	        if (plancia[i] == giocatore) {
	            pos = i;
	            break;
	        }
	    }

	    if (pos == -1) {
	    	throw new GiocatoreNonSpostabile("Giocatore non trovato nella plancia"); // Giocatore non trovato nella plancia
	    }

	    int nuovaPos = (pos + 1) % tipoPlancia.getLunghezza();
	    int tentativi = 0;

	    // Cerca la prima posizione libera, saltando quelle occupate
	    while (plancia[nuovaPos] != null && tentativi < tipoPlancia.getLunghezza()) {
	        nuovaPos = (nuovaPos + 1) % tipoPlancia.getLunghezza();
	        tentativi++;
			posizioneFin++;
	    }

	    // Se la plancia è piena (nessuna posizione libera), non fare nulla
	    if (tentativi == tipoPlancia.getLunghezza()) {
	    	throw new GiocatoreNonSpostabile("Giocatore non spostabile");
	    }

	    // Sposta il giocatore alla nuova posizione libera
	    
	    plancia[nuovaPos] = giocatore;
	    plancia[pos] = null;

	}
	
	public void abbandonaVolo(Giocatore giocatore) {
		
		// Trova la posizione attuale del giocatore
	    int pos = -1;
	    for (int i = 0; i < tipoPlancia.getLunghezza(); i++) {
	        if (plancia[i] == giocatore) {
	            pos = i;
	            break;
	        }
	    }

	    if (pos != -1) {
	        plancia[pos] = null; // Rimuovi il giocatore dalla plancia
	        GiorniDiVoloGiocatori.remove(giocatore); // Rimuovi il giocatore dalla mappa dei giorni di volo
	    }
		
		
	}
}