package partita;

import java.util.List;
import java.util.Map;

import model.Giocatore;
import model.carte.Carta;
import partita.fasiGioco.*;
import partita.fasiGioco.composizioneNave.ComposizioneNave;
import partita.fasiGioco.volo.Volo;

public class Partita {
	// CLASSE CHE GESTISCE LA PARTITA
	private Giocatore[] giocatori;
	private ModalitaGioco modalita;

	private Inizializzazione inizializzazione = new Inizializzazione();
	private FineGioco fine;

	public Partita() {
		giocatori = inizializzazione.getGiocatori();
		modalita = inizializzazione.getModalita();

	}

	public void gioca() {

		// fase composizione nave
		ComposizioneNave composizione = new ComposizioneNave(giocatori, modalita.getlivelloPartita());
		composizione.start();
		
		// salvo le informazioni che servono per la fase di volo
		List<Carta> mazzoDiGioco = composizione.getMazzoDiGioco();
		Map<Giocatore, Integer> numPezziDistrutti = composizione.getNumPezziPrenotati();
		
		// fase di volo
		Volo volo = new Volo(modalita, giocatori, mazzoDiGioco);
		volo.inizializzaPezziDistrutti(numPezziDistrutti);
		volo.iniziaVolo();
		
		// salvo le informazioni che servono per la fase fine volo
		// TODO get.managerTurnoDiVolo
		
		// fine del volo, assegno i crediti
		// FineVolo fineVolo = new FineVolo(modalita, managerTurni);
		
		// fine del gioco
		fine = new FineGioco(giocatori);
		fine.start();
	}

}
