package partita.fasiGioco.volo;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import model.Giocatore;
import model.planciaDiVolo.Plancia;
import partita.ModalitaGioco;
import model.carte.TipoCarta;
import model.carte.Carta;

public class Volo {
	
	private Giocatore[] giocatori;
	private final Plancia plancia;
	private List<Carta> carte;
	private List<ManagerDiVolo> managerInVolo; // manager di volo che gestisce solo giocatori in volo
	private List<ManagerDiVolo> managerDiVolo; // manager di volo che gestisce tutti i giocatori
	
	// TODO fare una lista di manager solo con quelli in volo e una con tutti (anche quelli che hanno abbandonato)
	public Volo(ModalitaGioco modalitaGioco, Giocatore[] giocatori, List<Carta> carte) {
		this.giocatori = giocatori;
		this.plancia = new Plancia(giocatori,modalitaGioco.getlivelloPartita());
		this.carte = carte;
		this.managerInVolo = new ArrayList<>();
		this.managerDiVolo = new ArrayList<>();
		for(Giocatore giocatore:giocatori) {
			ManagerDiVolo manager=new ManagerDiVolo(giocatore, plancia);
			managerInVolo.add(manager);
			managerDiVolo.add(manager);
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
			carte.getFirst().eseguiEvento((ManagerDiVolo[])managerInVolo.toArray());
			
			//aggiorno i manager di volo in base alla posizione dei giocatori nella plancia
			
			ordinaManegerDiVolo();
			
			//controllo se i giocatori sono doppiati
			// TODO ci sono altre condizioni per abbandonare il volo, fare una funzione che si occupa di controllarli e rimuovere
			rimuoviManagerInVolo();
			
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
			for(ManagerDiVolo manager: managerInVolo) {
				if(manager.getGiocatore().equals(giocatoriOrdinati[i])) {
					managerInVolo.set(i, manager);
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
	
	
	public ManagerDiVolo[] getManagerDiVolo() {
		return managerDiVolo.toArray(new ManagerDiVolo[managerDiVolo.size()]);	
	}
	
	public ManagerDiVolo[] getManagerInVolo() {
		return managerInVolo.toArray(new ManagerDiVolo[managerInVolo.size()]);	
	}
	
	
	private void rimuoviManagerInVolo() {
		//controllo se i giocatori sono doppiati
		for(ManagerDiVolo manager : managerInVolo) {
			if(manager.isDoppiato()) {
				managerInVolo.remove(manager);
				manager.AbbandonaVolo();
			}
		}
		//se perdi tutti gli umani dell' equipaggio
		for(ManagerDiVolo manager : managerInVolo) {
			if(manager.getGiocatore().getNave().getEquipaggio().isEmpty()) {
				managerInVolo.remove(manager);
				manager.AbbandonaVolo();
			}
		}
		
		//se ti imbatti in un’avventura Spazio Aperto, devi dichiarare una potenza motrice maggiore di zero o abbandonare la corsa.
		if(carte.getFirst().getTipoCarta() == TipoCarta.SPAZIO_APERTO) {
			
			for(ManagerDiVolo manager : managerInVolo) {
				if(manager.getGiocatore().getNave().getPotenzaMotrice() <= 0) {
					managerInVolo.remove(manager);
					manager.AbbandonaVolo();
					
				}
			}
			
		}
	}
	
	
}
