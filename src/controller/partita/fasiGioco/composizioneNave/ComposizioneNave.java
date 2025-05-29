package controller.partita.fasiGioco.composizioneNave;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import controller.partita.LivelliPartita;
import controller.servizi.ServizioAssemblaggio;
import controller.servizi.ServizioCarte;
import model.Giocatore;
import model.carte.Carta;
import model.componenti.Componente;
import view.io.GestoreIO;

// Fase del gioco in cui si compongno le navi
public class ComposizioneNave {

	private final ServizioCarte servizioCarte;
	private final ManagerTurnoComposizione[] managers;
	private Set<Giocatore> ordineFine;
	private List<Componente> componentiScartati = new ArrayList<>();
	private final LivelliPartita livello;

	public ComposizioneNave(Giocatore[] giocatori, LivelliPartita livello) {

		this.managers = new ManagerTurnoComposizione[giocatori.length];
		this.ordineFine = new LinkedHashSet<>();
		this.livello = livello;		
		this.servizioCarte = new ServizioCarte(livello);
		
		ServizioAssemblaggio servizioAssemblaggio = new ServizioAssemblaggio(); // Inizializza il servizio di assemblaggio
		
		for (int i = 0; i < giocatori.length; i++) {
			managers[i] = new ManagerTurnoComposizione(componentiScartati, giocatori[i], livello, servizioAssemblaggio, servizioCarte);
		}
	}

	public void start() {
		// Ogni giocatore compone la propria nave

		GestoreIO io = new GestoreIO();
		io.aCapo();
		io.stampa("E' ora di comporre le navi");

		final int turniTotali = livello.getTipoNave().getCapacitaComponenti();

		for (int turno = 0; turno < turniTotali; turno++) {
			io.stampa("Turno numero: " + (turno + 1) + " di " + turniTotali);

			for (int i = 0; i < managers.length; i++) {

				// Non eseguo il codice nel caso i giocatori abbiano terminato la composizione
				if (managers[i].getTurnoTerminato()) {
					continue;
				}

				// Gestisco il turno, se hanno concluso la costruzione aggiungo il giocatore
				// alla lista dei giocatori che hanno finito
				if (managers[i].gestisciTurno()) {
					ordineFine.add(managers[i].getGiocatore());
				}

				if (ordineFine.size() == managers.length) {
					return;
				}
			}
		}
		
		// Aggiungo i giocatori che non hanno completato il volo al ordine fine
		for(ManagerTurnoComposizione manager : managers) {
			ordineFine.add(manager.getGiocatore());
		}
	}
	
	public List<Carta> getMazzoDiGioco(){
		return servizioCarte.getMazzoCompleto();
	}
	
	/**
	 * getter per leggere il numero di pezzi prenotati en non usati
	 * 
	 * @return mappa che associa ad ogni giocatore il numero di compoenenti prenotati e non usati
	 */
	public Map<Giocatore, Integer> getNumPezziPrenotati() {
	    Map<Giocatore, Integer> numPezziDistrutti = new HashMap<>();

	    for (ManagerTurnoComposizione m : managers) {
	        Giocatore giocatore = m.getGiocatore();
	        int pezziPrenotati = m.getPezziPrenotatiSize();

	        numPezziDistrutti.put(giocatore, pezziPrenotati);
	    }
	    return numPezziDistrutti;
	}	
	
	/**
	 * Funzione per capire l'ordine di volo
	 * @return lista di giocatori nell'ordine con cui hanno concluso le navi
	 */
	public Giocatore[] getOrdineFine() {
		return ordineFine.toArray(new Giocatore[0]);
	}
	
}
