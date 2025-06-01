package controller.partita.fasiGioco.volo;

import java.util.List;
import java.util.Map;
import java.util.Set;

import controller.partita.LivelliPartita;

import java.util.ArrayList;
import java.util.HashSet;
import model.Giocatore;
import model.planciaDiVolo.Plancia;
import view.formattatori.FormattatoreGrafico;
import view.io.GestoreIO;
import view.renderer.PlanciaRenderer;
import model.carte.TipoCarta;
import model.carte.Carta;

public class Volo {

	private final GestoreIO io = new GestoreIO();
	private Giocatore[] giocatori;
	private final Plancia plancia;
	private List<Carta> carte;
	private List<ManagerDiVolo> managerInVolo; // manager di volo che gestisce solo giocatori in volo
	private List<ManagerDiVolo> managerDiVolo; // manager di volo che gestisce tutti i giocatori
	PlanciaRenderer planciaRenderer = new PlanciaRenderer();
	GestoreIO gestoreIO = new GestoreIO();
	FormattatoreGrafico formattatore = new FormattatoreGrafico();

	public Volo(LivelliPartita livello, Giocatore[] giocatori, List<Carta> carte) {
		this.giocatori = giocatori;
		this.plancia = new Plancia(giocatori, livello);
		this.carte = carte;
		this.managerInVolo = new ArrayList<>();
		this.managerDiVolo = new ArrayList<>();
		for (Giocatore giocatore : giocatori) {
			ManagerDiVolo manager = new ManagerDiVolo(giocatore, plancia);
			managerInVolo.add(manager);
			managerDiVolo.add(manager);
		}

	}

	public void iniziaVolo() {

		boolean game = true;

		// caricare le navi dei giocatori con batteria e equipaggiamento

		for (Giocatore giocatore : giocatori) {
			io.aCapo();
			io.stampa("Il giocatore " + formattatore.formatta(giocatore) + " sta preparando la nave al volo");
			giocatore.getNave().preparaAlVolo();
		}
		io.aCapo();

		ordinaManegerDiVolo();

		while (game) {
			
			
			rimuoviManagerInVolo(); // controllo i giocatori che hanno abbandonato la corsa

			ordinaManegerDiVolo();  // ripristino l' ordine dei giocatori in base a chi è più avanti nel volo
			

			// controllo le carte
		    if (carte.isEmpty()) {
		        game = false;
		        break;
		    }
			
		    //controllo se hanno abbandonato tutti il volo
			if(managerInVolo.isEmpty()) {
				game = false;
				break;
			}

			gestoreIO.stampa(planciaRenderer.rappresentaPlancia(plancia));

			ManagerDiVolo[] managers = managerInVolo.toArray(new ManagerDiVolo[0]);


			
			carte.get(0).eseguiEvento(managers);
			carte.remove(0);

			gestoreIO.stampa("premi un tasto per proseguire con la prossima carta: ");
			gestoreIO.leggiTesto();
			
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

		// controllo se i giocatori sono doppiati
		for (ManagerDiVolo manager : managerInVolo) {
			if (manager.isDoppiato()) {
				managerDaRimuovere.add(manager);
				manager.abbandonaVolo();
				gestoreIO.stampa("Il giocatore " + formattatore.formatta(manager.getGiocatore())
						+ " ha abbandonato il volo siccome è stato doppiato");
				gestoreIO.aCapo();

			}
		}
		// se perdi tutti gli umani dell' equipaggio
		for (ManagerDiVolo manager : managerInVolo) {
			if (manager.getGiocatore().getNave().getEquipaggio().isEmpty()) {
				managerDaRimuovere.add(manager);
				manager.abbandonaVolo();
				gestoreIO.stampa("Il giocatore " + formattatore.formatta(manager.getGiocatore())
						+ " ha abbandonato il volo siccome ha perso tutti gli umani dell' equipaggio");
				gestoreIO.aCapo();
			}
		}

		// se ti imbatti in un’avventura Spazio Aperto, devi dichiarare una potenza
		// motrice maggiore di zero o abbandonare la corsa.
		if (!carte.isEmpty() && carte.get(0).getTipoCarta() == TipoCarta.SPAZIO_APERTO) {

			for (ManagerDiVolo manager : managerInVolo) {
				if (manager.getGiocatore().getNave().getPotenzaMotrice() <= 0) {
					managerDaRimuovere.add(manager);
					manager.abbandonaVolo();
					gestoreIO.stampa("Il giocatore " + formattatore.formatta(manager.getGiocatore())
							+ " ha abbandonato il volo siccome è in uno spazio aperto senza potenza motrice");
					gestoreIO.aCapo();
				}
			}
		}
		// rimuovo effettivamente i manager di volo da managerInVolo

		for (ManagerDiVolo manager : managerDaRimuovere) {
			managerInVolo.remove(manager);

		}

	}

}
