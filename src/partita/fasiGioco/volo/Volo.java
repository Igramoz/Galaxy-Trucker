package partita.fasiGioco.volo;

import java.util.List;
import java.util.Map;

import model.Giocatore;
import model.planciaDiVolo.Plancia;
import partita.ModalitaGioco;
import model.carte.Carta;

public class Volo {
	
	private final ModalitaGioco modalitaGioco;
	private Giocatore[] giocatori;
	private final Plancia plancia;
	
	//TODO lista per carte
	//TODO assegna null al tempo di volo dei giocatori che abbandonano il volo
	public Volo(ModalitaGioco modalitaGioco, Giocatore[] giocatori, List<Carta> carte) {
		this.modalitaGioco = modalitaGioco;
		this.giocatori = giocatori;
		this.plancia = new Plancia(giocatori,modalitaGioco.getlivelloPartita());
		// TODO istanzai qui i manager di volo
	}
	
	public void iniziaVolo() {
		
		boolean game = true;
		
		//caricare le navi dei giocatori con batteria e equipaggiamento
		
		for(Giocatore giocatore : giocatori) {
			giocatore.getNave().preparaAlVolo();
		}
		//crea i manager di volo TODO managerDiVolo deve essere un attributo della classe così anche la funzione sotto può accedere
		ManagerDiVolo[] managerDiVolo = new ManagerDiVolo[giocatori.length];
		for(int i = 0; i < giocatori.length; i++) {
			managerDiVolo[i] = new ManagerDiVolo(giocatori[i], plancia);
		}
		while(game) {
			//TODO implementrare il ciclo di gioco e le carte
			
			
			//condizione di uscita del ciclo se tutte le n carte sono state superate
			
		}
		
		
	}
	
	public void inizializzaPezziDistrutti(Map<Giocatore, Integer> pezziDistrutti) {
		
		for(Map.Entry<Giocatore, Integer> entry : pezziDistrutti.entrySet()) {
			Giocatore giocatore = entry.getKey();
			
			for(ManagerDiVolo manager : managerDiVolo) {
				if(manager.getGiocatore().equals(giocatore)) {
					manager.incrementaPezziDistrutti(entry.getValue());
				}
			}
		}
			
	}
	
	// TODO fare il getter per i manager di volo
	
}
