package partita.fasiGioco.composizioneNave;

import java.util.ArrayList;
import java.util.List;

import io.GestoreIO;
import model.Giocatore;
import model.carte.Carta;
import model.componenti.Componente;
import partita.LivelliPartita;
import servizi.ServizioAssemblaggio;
import servizi.ServizioCarte;

// Fase del gioco in cui si compongno le navi
public class ComposizioneNave {

	private final ServizioCarte servizioCarte;
	private final ManagerTurnoComposizione[] manager;
	private List<Giocatore> ordineFine;
	private List<Componente> componentiScartati = new ArrayList<>();
	private final LivelliPartita livello;

	public ComposizioneNave(Giocatore[] giocatori, LivelliPartita livello) {

		this.manager = new ManagerTurnoComposizione[giocatori.length];
		this.ordineFine = new ArrayList<>();
		this.livello = livello;		
		this.servizioCarte = new ServizioCarte(livello);
		
		ServizioAssemblaggio servizioAssemblaggio = new ServizioAssemblaggio(); // Inizializza il servizio di assemblaggio
		
		for (int i = 0; i < giocatori.length; i++) {
			manager[i] = new ManagerTurnoComposizione(componentiScartati, giocatori[i], livello, servizioAssemblaggio, servizioCarte);
		}
	}

	public List<Giocatore> start() {
		// Ogni giocatore compone la propria nave

		GestoreIO io = new GestoreIO();
		io.stampa("E' ora di comporre le navi");

		final int turniTotali = livello.getTipoNave().getCapacitaComponenti();

		for (int turno = 0; turno < turniTotali; turno++) {
			io.stampa("Turno numero: " + (turno + 1) + " di " + turniTotali);

			for (int i = 0; i < manager.length; i++) {

				// Non eseguo il codice nel caso i giocatori abbiano terminato la composizione
				if (manager[i].getTurnoTerminato()) {
					continue;
				}

				// Gestisco il turno, se hanno concluso la costruzione aggiungo il giocatore
				// alla lista dei giocatori che hanno finito
				if (manager[i].gestisciTurno()) {
					ordineFine.add(manager[i].getGiocatore());
				}

				if (ordineFine.size() == manager.length) {
					return ordineFine;
				}
			}
		}

		// I pezzi prenotati e non usati sono considerati pezzi distrutti
		for (ManagerTurnoComposizione m : manager) {
			m.getGiocatore().incrementaPezziDistrutti(m.getPezziPrenotatiSize());
		}

		return ordineFine;
	}
	
	public List<Carta> getMazzoDiGioco(){
		return servizioCarte.getMazzoCompleto();
	}
}
