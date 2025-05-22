package partita;

import java.util.List;
import java.util.Map;

import model.Giocatore;
import model.carte.Carta;
import partita.fasiGioco.*;
import partita.fasiGioco.composizioneNave.ComposizioneNave;
import partita.fasiGioco.volo.ManagerDiVolo;
import partita.fasiGioco.volo.Volo;
import io.GestoreIO;
import grafica.Colore;
import grafica.TextAligner;

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
	
	public Partita(String args[] ){
		
		 giocatori = new Giocatore[args.length];
		
        Colore[] colori = {Colore.ROSSO, Colore.BLU, Colore.VERDE, Colore.GIALLO};

        for (int i = 0; i < args.length; i++) {
        	if(args[i] != null) {
        		giocatori[i] = new Giocatore(args[i], colori[i]);
        		System.out.println(i);
        	}   
        }
        
        modalita = inizializzazione.getModalita();
		
		
	}

	public void gioca() {

		if(modalita == ModalitaGioco.VOLO_SINGOLO) {
			voloSingolo(modalita.getlivelloPartita());
		}else {
			trasvolataIntergalattica();
		}
		
		// fine del gioco
		fine = new FineGioco(giocatori);
		fine.start();
	}
	
	private ManagerDiVolo[] voloSingolo(LivelliPartita livelllo) {
		
		// fase composizione nave
		ComposizioneNave composizione = new ComposizioneNave(giocatori, modalita.getlivelloPartita());
		composizione.start();
		
		// salvo le informazioni che servono per la fase di volo
		List<Carta> mazzoDiGioco = composizione.getMazzoDiGioco();
		Map<Giocatore, Integer> numPezziDistrutti = composizione.getNumPezziPrenotati();
		giocatori = composizione.getOrdineFine();
		
		// fase di volo
		Volo volo = new Volo(modalita.getlivelloPartita(), giocatori, mazzoDiGioco);
		volo.inizializzaPezziDistrutti(numPezziDistrutti);
		volo.iniziaVolo();
		
		// salvo le informazioni che servono per la fase fine volo
		ManagerDiVolo[] managersVolo = volo.getManagerDiVolo();
		
		// fine del volo, assegno i crediti
		FineVolo fineVolo = new FineVolo(modalita, managersVolo);
		fineVolo.assegnaRicompense();
		
		return managersVolo;
	}
	
	private void trasvolataIntergalattica() {
		
		// si gioca ogni livello uno dopo l'altro
		for(LivelliPartita livelloAttuale :LivelliPartita.values()) {
			
			GestoreIO gestoreIO = new GestoreIO();
			TextAligner a = new TextAligner();
			
			gestoreIO.stampa(a.alignCenter("INIZIO LIVELLO " + livelloAttuale.getNumeroLivello()));
			
			modalita.setlivelloPartita(livelloAttuale);
			
			// eseguo il singolo livello
			ManagerDiVolo[] managers = voloSingolo(livelloAttuale);
			
			//TODO fare in modo che start non restituisccca nulla 
//			//gestisco i titoli
//			ServizioTitoli servizioTitoli = new ServizioTitoli(managers, livelloAttuale);
//			servizioTitoli.gestisciTitoli();
		}		
	}
	

}
