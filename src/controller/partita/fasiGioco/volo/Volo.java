package controller.partita.fasiGioco.volo;

import java.util.List;

import java.util.Map;
import java.util.Set;
import model.componenti.Componente;
import model.componenti.TipoComponente;
import controller.partita.LivelliPartita;
import java.util.HashSet;
import model.Giocatore;
import model.planciaDiVolo.Plancia;
import view.formattatori.FormattatoreGrafico;
import view.io.GestoreIO;
import view.renderer.PlanciaRenderer;
import model.carte.TipoCarta;
import model.nave.Nave;
import model.carte.Carta;

public class Volo {

	private final GestoreIO io = new GestoreIO();
	private Giocatore[] giocatori;
	private final Plancia plancia;
	private List<Carta> carte;
	private Set<ManagerDiVolo> managerInVolo; // manager di volo che gestisce solo giocatori in volo
	private Set<ManagerDiVolo> managerDiVolo; // manager di volo che gestisce tutti i giocatori
	PlanciaRenderer planciaRenderer = new PlanciaRenderer();
	GestoreIO gestoreIO = new GestoreIO();
	FormattatoreGrafico formattatore = new FormattatoreGrafico();

	public Volo(LivelliPartita livello, Giocatore[] giocatori, List<Carta> carte) {
		this.giocatori = giocatori;
		this.plancia = new Plancia(giocatori, livello);
		this.carte = carte;
		this.managerInVolo = new HashSet<>();
		this.managerDiVolo = new HashSet<>();
		for (Giocatore giocatore : giocatori) {
			ManagerDiVolo manager = new ManagerDiVolo(giocatore, plancia);
			managerInVolo.add(manager);
			managerDiVolo.add(manager);
		}

	}

	public void iniziaVolo() {

		// caricare le navi dei giocatori con batteria e equipaggiamento

		for (Giocatore giocatore : giocatori) {
			io.aCapo();
			io.stampa("Il giocatore " + formattatore.formatta(giocatore) + " sta preparando la nave al volo");
			giocatore.getNave().preparaAlVolo();
		}
		io.aCapo();

		ordinaManegerDiVolo();
		while (!carte.isEmpty() && !managerInVolo.isEmpty()) {
				
			gestoreIO.stampa(planciaRenderer.rappresentaPlancia(plancia));
			
			gestoreIO.stampa("premi un tasto per proseguire : ");
			gestoreIO.leggiTesto();
			
			ManagerDiVolo[] managers = managerInVolo.toArray(new ManagerDiVolo[0]);
			carte.get(0).eseguiEvento(managers);
			
			
			rimuoviManagerInVolo(); // controllo i giocatori che hanno abbandonato la corsa

			ordinaManegerDiVolo();  // ripristino l' ordine dei giocatori in base a chi è più avanti nel volo
			carte.remove(0);
		}
		
	}

	private void ordinaManegerDiVolo() {
		// ordina i manager di volo in base alla posizione dei giocatori nella plancia
		Set<ManagerDiVolo> ordinati = new HashSet<>();
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

		for (Map.Entry<Giocatore, Integer> entry : pezziDistrutti.entrySet()) {
			Giocatore giocatore = entry.getKey();
			for (ManagerDiVolo manager : managerDiVolo) {
				if (manager.getGiocatore().equals(giocatore)) {
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

	    for (ManagerDiVolo manager : managerInVolo) {
	        boolean daRimuovere = false;

	        if (manager.isDoppiato()) {
	            gestoreIO.stampa("Il giocatore " + formattatore.formatta(manager.getGiocatore())
	                    + " ha abbandonato il volo siccome è stato doppiato");
	            gestoreIO.aCapo();
	            daRimuovere = true;
	        }
	        
	        else if (manager.getGiocatore().getNave().getEquipaggio().isEmpty()) {
	            gestoreIO.stampa("Il giocatore " + formattatore.formatta(manager.getGiocatore())
	                    + " ha abbandonato il volo siccome ha perso tutti gli umani dell' equipaggio");
	            gestoreIO.aCapo();
	            daRimuovere = true;
	        }
	        
	        else if (!carte.isEmpty() && carte.get(0).getTipoCarta() == TipoCarta.SPAZIO_APERTO && !potenzaMotrice(manager.getGiocatore().getNave())) {
	            gestoreIO.stampa("Il giocatore " + formattatore.formatta(manager.getGiocatore())
	                    + " ha abbandonato il volo siccome è in uno spazio aperto senza potenza motrice");
	            gestoreIO.aCapo();
	            daRimuovere = true;
	        }

	        if (daRimuovere) {
	            manager.abbandonaVolo();
	            managerDaRimuovere.add(manager);
	        }
	    }

	    managerInVolo.removeAll(managerDaRimuovere);
	}
	
	private boolean potenzaMotrice(Nave nave) {
		List<Componente> ms = nave.getCopiaComponenti(TipoComponente.MOTORE_SINGOLO);
		List<Componente> md = nave.getCopiaComponenti(TipoComponente.MOTORE_DOPPIO);
		
		if(ms.isEmpty() && md.isEmpty()) {
			return false; // Se non ha motori singoli o doppi, non può volare
		}
		return true; // Altrimenti ha motori e può volare
	}
	

}
