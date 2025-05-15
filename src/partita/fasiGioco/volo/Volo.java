package partita.fasiGioco.volo;

import java.util.List;
import io.GestoreIO;
import grafica.renderer.PlanciaRenderer;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
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

		ordinaManegerDiVolo();
		
		while(game) {
			
			//aggiorno i manager di volo in base alla posizione dei giocatori nella plancia
			rimuoviManagerInVolo();
			
			ordinaManegerDiVolo();
			//controllo se i giocatori sono doppiati o le altre condizioni per abbandonare la corsa
			
			//stampa la plancia
			
			PlanciaRenderer planciaRenderer = new PlanciaRenderer();
			GestoreIO gestoreIO = new GestoreIO();
			
			gestoreIO.stampa(planciaRenderer.rappresentaPlancia(plancia));

			ManagerDiVolo[] managers = managerInVolo.toArray(new ManagerDiVolo[0]);
			
			//tolgo la prima carta dalla lista delle carte
			
			carte.getFirst().eseguiEvento(managers);
			carte.remove(0);
			
			if(carte.isEmpty()) {
				game = false; // se non ci sono più carte il volo è finito
			}
		}
	}
	
	private void ordinaManegerDiVolo() {
		// ordina i manager di volo in base alla posizione dei giocatori nella plancia
		List<ManagerDiVolo> ordinati = new ArrayList<>();
		Giocatore[] giocatoriOrdinati = plancia.getGiocatori();

		for (Giocatore g : giocatoriOrdinati) {
		    for (ManagerDiVolo m : managerInVolo) {
		        if (m.getGiocatore().equals(g)) {
		            ordinati.add(m);
		            break;
		        }
		    }
		}
		managerInVolo = ordinati;
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
		
		Set<ManagerDiVolo> managerDaRimuovere = new HashSet<>();
		
		//controllo se i giocatori sono doppiati
		for(ManagerDiVolo manager : managerInVolo) {
			if(manager.isDoppiato()) {
				managerDaRimuovere.add(manager);
				manager.AbbandonaVolo();
			}
		}
		//se perdi tutti gli umani dell' equipaggio
		for(ManagerDiVolo manager : managerInVolo) {
			if(manager.getGiocatore().getNave().getEquipaggio().isEmpty()) {
				managerDaRimuovere.add(manager);
				manager.AbbandonaVolo();
			}
		}
		
		//se ti imbatti in un’avventura Spazio Aperto, devi dichiarare una potenza motrice maggiore di zero o abbandonare la corsa.
		if(!carte.isEmpty() && carte.getFirst().getTipoCarta() == TipoCarta.SPAZIO_APERTO) {
			
			for(ManagerDiVolo manager : managerInVolo) {
				if(manager.getGiocatore().getNave().getPotenzaMotrice() <= 0) {
					managerDaRimuovere.add(manager);
					manager.AbbandonaVolo();		
				}
			}	
		}
		//rimuovo effettivamente i manager di volo da managerInVolo
		
		for(ManagerDiVolo manager : managerDaRimuovere) {
			managerInVolo.remove(manager);
			//TODO decidere se scrivere un messaggio di avviso per il giocatore che ha abbandonato la corsa
		}

	}
	
	
}
