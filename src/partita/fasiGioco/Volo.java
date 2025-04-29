package partita.fasiGioco;

import model.Giocatore;
import model.planciaDiVolo.Plancia;
import partita.ModalitaGioco;
import partita.fasiGioco.ManegerDiVolo;
import model.carte.Carta;

public class Volo {
	
	private final ModalitaGioco modalitaGioco;
	private Giocatore[] giocatori;
	private final Plancia plancia;
	
	//TODO lista per carte oppure array di carte ?
	public Volo(ModalitaGioco modalitaGioco, Giocatore[] giocatori, Carta[] carte) {
		this.modalitaGioco = modalitaGioco;
		this.giocatori = giocatori;
		this.plancia = new Plancia(giocatori,modalitaGioco.getlivelloPartita());
		
	}
	
	public void iniziaVolo() {
		
		boolean game = true;
		
		//caricare le navi dei giocatori con batteria e equipaggiamento
		
		for(Giocatore giocatore : giocatori) {
			giocatore.getNave().preparaAlVolo();
		}
		//crea i manager di volo
		ManegerDiVolo[] managerDiVolo = new ManegerDiVolo[giocatori.length];
		for(int i = 0; i < giocatori.length; i++) {
			managerDiVolo[i] = new ManegerDiVolo(giocatori[i], plancia);
		}
		while(game) {
			//TODO implementrare il ciclo di gioco e le carte
			
			
			//condizione di uscita del ciclo se tutte le n carte sono state superate
			
		}
		
		
	}
}
