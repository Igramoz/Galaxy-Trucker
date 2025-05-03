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
	private List<Carta> carte;
	private ManagerDiVolo[] managerDiVolo;
	
	public Volo(ModalitaGioco modalitaGioco, Giocatore[] giocatori, List<Carta> carte) {
		this.modalitaGioco = modalitaGioco;
		this.giocatori = giocatori;
		this.plancia = new Plancia(giocatori,modalitaGioco.getlivelloPartita());
		managerDiVolo = new ManagerDiVolo[giocatori.length];
		for(int i = 0; i < giocatori.length; i++) {
			managerDiVolo[i] = new ManagerDiVolo(giocatori[i], plancia);
		}
		
	}
	
	public void iniziaVolo() {
		
		boolean game = true;
		
		//caricare le navi dei giocatori con batteria e equipaggiamento
		
		for(Giocatore giocatore : giocatori) {
			giocatore.getNave().preparaAlVolo();
		}
		
		while(game) {
			//per ogni giocatore applica le carte a partire dall primo giocatore sulla plancia
			carte.getFirst().eseguiEvento(managerDiVolo);
			
			//aggiorno i manager di volo in base alla posizione dei giocatori nella plancia
			
			ordinaManegerDiVolo();
			
			//controllo se i giocatori sono doppiati
			
			for(ManagerDiVolo manager : managerDiVolo) {
				if(manager.isDoppiato()) {
					manager.AbbandonaVolo();
				}
			}
			
			//tolgo la prima carta dalla lista delle carte
			carte.remove(0);
			//controllo se è finito il volo
			if(carte.isEmpty()) {
				game = false; // se non ci sono più carte il volo è finito
			}
		}
		
		
	}
	
	private void ordinaManegerDiVolo() {
		// ordina i manager di volo in base alla posizione dei giocatori nella plancia
		
		Giocatore[] giocatoriOrdinati = plancia.getGiocatori();
		for(int i = 0; i < giocatoriOrdinati.length; i++){
			for(int j = 0; j < managerDiVolo.length; j++){
				
				if(managerDiVolo[j].getGiocatore().equals(giocatoriOrdinati[i])){
					managerDiVolo[i] = managerDiVolo[j];
					break;
				}
			}
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
