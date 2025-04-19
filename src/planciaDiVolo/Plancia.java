package planciaDiVolo;

import model.Giocatore;
import partita.ModalitaGioco;



public class Plancia {
	
	private Giocatore[] giocatori; // lista dei giocatori in gioco
	private final Giocatore[] plancia;// lista dei giocatori in gioco che si aggiorna in base alla modalita di gioco
	private final TipoPlancia tipoPlancia; // tipo di plancia in base alla modalita di gioco
	
	// TODO implementare gli attributi per le carte
	
	
	public Plancia(Giocatore[] giocatori, ModalitaGioco modalitaGioco, TipoPlancia tipoPlancia) {
		
		this.giocatori = giocatori;
		this.tipoPlancia = tipoPlancia;

		this.plancia = new Giocatore[tipoPlancia.getLunghezza()]; // inizializza la plancia in base alla lunghezza della plancia
		
		// metto i giocatori nella plancia in base alla modalita di gioco

		for(int i =0; i<tipoPlancia.getPosizioni().length && i<tipoPlancia.getLunghezza(); i++) {

			plancia[tipoPlancia.getPosizioni()[i]] = giocatori[i]; // metto i giocatori nella plancia in base alla modalita di gioco
			
		}
		
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

	//sposta un giocatore indietro di un giorno di volo
	private void spostaIndietro(Giocatore giocatore) {
		int pos = -1; // Posizione attuale del giocatore nella plancia
	
		// Trova la posizione del giocatore nella plancia
		for (int i = 0; i < tipoPlancia.getLunghezza(); i++) {
			if (plancia[i] == giocatore) {
				pos = i;
				break;
			}
		}
	
		// Calcola la nuova posizione in modo circolare
		int nuovaPos = (pos - 1 + tipoPlancia.getLunghezza()) % tipoPlancia.getLunghezza();
	
		// Continua a cercare una posizione libera andando indietro
		while (plancia[nuovaPos] != null) {
			nuovaPos = (nuovaPos - 1 + tipoPlancia.getLunghezza()) % tipoPlancia.getLunghezza(); // Sposta indietro in modo circolare
		}
		
		// Sposta il giocatore nella nuova posizione
		plancia[nuovaPos] = giocatore;
		plancia[pos] = null; // Libera la posizione precedente
	}


	//sposta un giocatore avanti di un giorno di volo
	private void spostaAvanti(Giocatore giocatore) {
		int pos = -1; // Posizione attuale del giocatore nella plancia
	
		// Trova la posizione del giocatore nella plancia
		for (int i = 0; i < tipoPlancia.getLunghezza(); i++) {
			if (plancia[i] == giocatore) {
				pos = i;
				break;
			}
		}
	
		// Se il giocatore non è stato trovato, lancia un'eccezione
		if (pos == -1) {
			throw new IllegalArgumentException("Il giocatore non è presente sulla plancia.");
		}
	
		// Calcola la nuova posizione in modo circolare
		int nuovaPos = (pos + 1) % tipoPlancia.getLunghezza();
	
		// Continua a cercare una posizione libera andando avanti
		while (plancia[nuovaPos] != null) {
			nuovaPos = (nuovaPos + 1) % tipoPlancia.getLunghezza(); // Sposta avanti in modo circolare
		}
	
		// Sposta il giocatore nella nuova posizione
		plancia[nuovaPos] = giocatore;
		plancia[pos] = null; // Libera la posizione precedente
	}

	//TODO implementare i metodi per le carte
	


}
