package planciaDiVolo;

import model.Giocatore;
import partita.ModalitaGioco;


public class Plancia {
	
	private Giocatore[] giocatori;
	private Giocatore[] plancia; // lista dei giocatori in gioco che si aggiorna in base alla modalita di gioco
	private int lunghezza; // lunghezza della plancia in base alla modalita di gioco 
	//posizioni iniziali dei giocatori nella plancia // massimo 4 giocatori


	
	// TODO implementare gli attributi per le carte
	
	
	public Plancia(Giocatore[] giocatori, ModalitaGioco modalitaGioco) {
		
		this.giocatori = giocatori;
		// in base alla modalita di gioco, creo la plancia
		int[] posizioniLivello1 = {4, 2, 1, 0}; // Posizioni per LIVELLO_1
		int[] posizioniLivello2 = {6, 3, 1, 0}; // Posizioni per LIVELLO_2
		int[] posizioniLivello3 = {9, 5, 2, 0}; // Posizioni per LIVELLO_3

    	int[] posizioni;

		switch (modalitaGioco.getlivelloPartita()) {
			case LIVELLO_1 -> {
				/*
				 * Creo la plancia per il livello 1
				 * 18 spazi
				 *
				 * 4 3 2 _ 1 _ _ _
				 * _             _
				 * _ _ _ _ _ _ _ _
				 *
				 */
				lunghezza = 18;
				plancia = new Giocatore[lunghezza];
				posizioni = posizioniLivello1;
				//devo mettere il giocatore 0 in posizione 4, giocatore 1 in posizione 2, 
				//giocatore 2 in posizione 1 e giocatore 3 in posizione 0


			}
			case LIVELLO_2 -> {
				/*
				 * Creo la plancia per il livello 2
				 * 24 spazi
				 *
				 * 4 3 _ 2 _ _ 1 _ _ _
				 * _                 _
				 * _                 _
				 * _ _ _ _ _ _ _ _ _ _
				 *
				 */
				lunghezza = 24;
				plancia = new Giocatore[lunghezza];
				posizioni = posizioniLivello2;
				//devo mettere il giocatore 0 in posizione 6, giocatore 1 in posizione 3,
				//giocatore 2 in posizione 1 e giocatore 3 in posizione 0
			}
			case LIVELLO_3 -> {
				/*
				 * Creo la plancia per il livello 3
				 * 34 spazi
				 *
				 * 4 _ 3 _ _ 2 _ _ _ 1 _ _ _ _
				 * _                         _
				 * _                         _
				 * _                         _
				 * _ _ _ _ _ _ _ _ _ _ _ _ _ _
				 *
				 */
				lunghezza = 34;
				plancia = new Giocatore[lunghezza];
				posizioni = posizioniLivello3;
				//devo mettere il giocatore 0 in posizione 9, giocatore 1 in posizione 5,
				//giocatore 2 in posizione 2 e giocatore 3 in posizione 0
			}	
			default -> throw new IllegalArgumentException("Modalità di gioco non valida: " + modalitaGioco.getlivelloPartita());
		}

		// Posiziona i giocatori
		for (int i = 0; i < giocatori.length && i < posizioni.length; i++) {
			plancia[posizioni[i]] = giocatori[i];
		}
		
	}
	

	//sposta un giocatore per n giorni di volo
	public void spostaGiocatore(int giorniVolo, Giocatore giocatore) {
		
		int pos = -1; // posizione del giocatore nella plancia

		//trova la posizione del giocatore nella plancia

		for (int i = 0; i < lunghezza; i++) {
			if(plancia[i] == giocatore) {
				pos = i;
				break;
			}
		}

		// sposta il giocatore di giorni di volo
		int nuovaPos = pos + giorniVolo;

		if(nuovaPos >= lunghezza) {
			nuovaPos = (pos + giorniVolo) % lunghezza; // se supera la lunghezza della plancia devo cercare di nuovo 
		}
		//verifica se la nuova posizione è occupata da un altro giocatore
		if(plancia[nuovaPos] != null) {
			// TODO gestire il caso in cui la nuova posizione è occupata da un altro giocatore
			//se la nuova posizione è occupata da un altro giocatore, devo spostare il giocatore in pos+1
			// finchè non trovo una posizione libera
			while(plancia[nuovaPos] != null) {
				nuovaPos++;
				if(nuovaPos >= lunghezza) {
					nuovaPos = 0; // se supera la lunghezza della plancia devo cercare di nuovo 
				}
			}
		}

		//sposta il giocatore nella nuova posizione
		plancia[nuovaPos] = giocatore; // metto il giocatore nella nuova posizione
		plancia[pos] = null; // libero la posizione precedente del giocatore
	}

	//TODO implementare i metodi per le carte
	

}
