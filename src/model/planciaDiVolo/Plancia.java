package model.planciaDiVolo;
import controller.partita.LivelliPartita;
import eccezioni.GiocatoreNonSpostabileException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Giocatore;

/*
 * @throwable GiocatoreNonSpostabile Eccezione lanciata quando un giocatore non può essere spostato
 * 
 */

public class Plancia {
	
	private final HashMap<Giocatore, Integer> GiorniDiVoloGiocatori =  new  HashMap<>(); // mappa che tiene traccia dei giorni di volo dei giocatori
	private final Giocatore[] plancia;// lista dei giocatori nei loro giorni di volo in gioco che si aggiorna in base alla modalita di gioco
	private final TipoPlancia tipoPlancia; // tipo di plancia in base alla modalita di gioco
	
	
	public Plancia(Giocatore[] giocatori, LivelliPartita livello) {
		
		this.tipoPlancia = livello.getTipoPlancia();
		this.plancia = new Giocatore[tipoPlancia.getLunghezza()]; // inizializza la plancia in base alla lunghezza della plancia

		for(int i =0; i<giocatori.length; i++) {

			plancia[tipoPlancia.getPosizioni()[i]] = giocatori[i]; // metto i giocatori nella plancia in base alla modalita di gioco
			
		}
		//memorizzo i giorni di volo dei giocatori nella mappa
		for(int i =0; i< giocatori.length; i++) {
			GiorniDiVoloGiocatori.put(giocatori[i], tipoPlancia.getPosizioni()[i]); // metto i giorni di volo dei giocatori nella mappa
		}
	}
	
	
	/**
	 * funzione per ottenere i giorni di volo di un giocatore
	 * @param giocatore
	 * @return null se il giocatore non è in volo, altrimenti il numero di giorni di volo
	 */
	public Integer getGiorniDiVoloGiocatore(Giocatore giocatore) {
		 // restituisce i giorni di volo del giocatore se è presente nella mappa, se non è presente restituisce null

		if(GiorniDiVoloGiocatori.containsKey(giocatore)){
		
			return GiorniDiVoloGiocatori.get(giocatore); // restituisce i giorni di volo del giocatore
		}else {
			return (Integer) null;
		}
	}

	public Giocatore[] getGiocatori() {
		// Restituisce i giocatori nella plancia in ordine di giorni di volo chi ha piu giorni di volo è in cima all' array
		List<Map.Entry<Giocatore, Integer>> entries = new ArrayList<>(GiorniDiVoloGiocatori.entrySet());
		entries.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

		Giocatore[] ordinati = new Giocatore[entries.size()];
		for (int i = 0; i < entries.size(); i++) {
		    ordinati[i] = entries.get(i).getKey();
		}
		return ordinati;
	}

	public float getNumeroGiroGiocatore(Giocatore giocatore) {
		// Restituisce il numero di giorni di volo del giocatore diviso per la lunghezza della plancia(se è uno vuol dire che il giocatore ha effettuato un giro completo della plancia )
		return (float)GiorniDiVoloGiocatori.getOrDefault(giocatore, 0)/tipoPlancia.getLunghezza(); // restituisce il numero di giorni di volo del giocatore
	}
	
	public boolean isInVolo(Giocatore g) {
		return GiorniDiVoloGiocatori.containsKey(g); 
	}

	//sposta un giocatore per n giorni di volo
	public void spostaGiocatore(int giorniVolo, Giocatore giocatore) {
		int saltiExtra = 0;

		if (giorniVolo < 0) {
			for (int i = 0; i < -giorniVolo; i++) {
				saltiExtra -= spostaIndietro(giocatore);
			}
		} else if (giorniVolo > 0) {
			for (int i = 0; i < giorniVolo; i++) {
				saltiExtra += spostaAvanti(giocatore);
			}
		}

		GiorniDiVoloGiocatori.put(giocatore,GiorniDiVoloGiocatori.getOrDefault(giocatore, 0) + giorniVolo + saltiExtra);
	}

	public Giocatore[] getPlancia() {
		return plancia;
	}

	
	


	//sposta un giocatore avanti di un giorno di volo
	
	private int spostaAvanti(Giocatore giocatore) {
		int pos = -1;
		for (int i = 0; i < tipoPlancia.getLunghezza(); i++) {
			if (plancia[i] == giocatore) {
				pos = i;
				break;
			}
		}
		if (pos == -1) throw new GiocatoreNonSpostabileException("Giocatore non trovato");

		int nuovaPos = (pos + 1) % tipoPlancia.getLunghezza();
		int salti = 0;
		while (plancia[nuovaPos] != null && salti < tipoPlancia.getLunghezza()) {
			nuovaPos = (nuovaPos  + 1) % tipoPlancia.getLunghezza();
			salti++;
		}

		if (salti == tipoPlancia.getLunghezza()) {
			throw new GiocatoreNonSpostabileException("Giocatore non spostabile: plancia piena");
		}

		plancia[nuovaPos] = giocatore;
		plancia[pos] = null;

		return salti;
	}
	
	
	//sposta un giocatore indietro di un giorno di volo
	private int spostaIndietro(Giocatore giocatore) {
		int pos = -1;
		
		for (int i = 0; i < tipoPlancia.getLunghezza(); i++) {
			if (plancia[i] == giocatore) {
				pos = i;
				break;
			}
		}
		
		if (pos == -1) { 
			throw new GiocatoreNonSpostabileException("Giocatore non trovato");
		}

		int nuovaPos = (pos - 1 + tipoPlancia.getLunghezza()) % tipoPlancia.getLunghezza();
		int salti = 0;
		while (plancia[nuovaPos] != null && salti < tipoPlancia.getLunghezza()) {
			nuovaPos = (nuovaPos - 1 + tipoPlancia.getLunghezza()) % tipoPlancia.getLunghezza();
			salti++;
		}

		if (salti == tipoPlancia.getLunghezza()) {
			throw new GiocatoreNonSpostabileException("Giocatore non spostabile: plancia piena");
		}

		plancia[nuovaPos] = giocatore;
		plancia[pos] = null;

		return salti;
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
	    }else {
	    	throw new GiocatoreNonSpostabileException("Giocatore non trovato");
	    }
		
		
	}
}