package partita;

import model.Giocatore;
import partita.fasiGioco.*;
import partita.fasiGioco.composizioneNave.ComposizioneNave;

public class Partita {
	// CLASSE CHE GESTISCE LA PARTITA
	private Giocatore[] giocatori;
	private ModalitaGioco modalita;
	
	private Inizializzazione inizializzazione = new Inizializzazione();
	private ComposizioneNave composizione;
	
	public Partita() {
		giocatori = inizializzazione.start();
		modalita = inizializzazione.getModalita();
		
		
	}
	
	public void gioca(){
		
		// fase composizione nave
		composizione = new ComposizioneNave(giocatori, modalita.getlivelloPartita());
		composizione.start();
	}
	
	
}
