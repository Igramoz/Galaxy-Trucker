package servizi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import grafica.renderer.TitoliRenderer;
import model.Giocatore;
import model.titoli.TipoTitolo;
import model.titoli.Titolo;
import partita.LivelliPartita;
import partita.fasiGioco.volo.ManagerDiVolo;
import util.random.RandomUtil;

/**
 * Classe che gestisce i titoli e li assegna ai giocatori la classe è monouso,
 * va istanziata ogni volta che cambia i livello
 */
public class ServizioTitoli {

	private final TitoliRenderer  renderer = new TitoliRenderer();

	private final ManagerDiVolo[] tuttiManagers; // tutti i managers (anche quelli che hanno abbandonato il gioco
	private final List<Giocatore> giocatoriInVolo; // managers che hanno terminato il volo
	private final LivelliPartita livello; // livello del turno appena finito

	public ServizioTitoli(ManagerDiVolo[] managers, LivelliPartita livello) {
		this.tuttiManagers = managers;

		this.giocatoriInVolo = filtraGiocatoriInVolo(managers);
		this.livello = livello;
	}

	// restituisce la lista dei giocatori in volo
	private List<Giocatore> filtraGiocatoriInVolo(ManagerDiVolo[] managers) {

		List<Giocatore> lista = new ArrayList<>();

		for (ManagerDiVolo manager : managers) {
			if (manager.isInVolo()) {
				lista.add(manager.getGiocatore());
			}
		}
		return lista;
	}

	public void gestisciTitoli() {
		if(livello == LivelliPartita.LIVELLO_1)
			assegnaTitoli();
		else
			valutaDifesaTitoli();		
	}

	private void assegnaTitoli() {

		// se nessun manager è in volo:
		if (giocatoriInVolo.size() == 0) {
			assegnaTitoloRandom();
			return;
		}

		// lista contenente i titoli non assegnati
		List<TipoTitolo> titoliNonAssegnati = new ArrayList<>();

		for (TipoTitolo titolo : TipoTitolo.values()) {

			Giocatore vincitoreTitolo = titolo.getTitolo().valutaTitolo(giocatoriInVolo);
			try {
				vincitoreTitolo.setTipoTitolo(titolo);
				renderer.stampaAssegnazioneTitolo(vincitoreTitolo, titolo);
			} catch (IllegalStateException e) {
				titoliNonAssegnati.add(titolo);
			}
		}

		List<Giocatore> giocatoriSenzaTitolo = giocatoriSenzaTitolo(giocatoriInVolo);
		Collections.shuffle(titoliNonAssegnati);

		// Assegno ai giocatori in volo senza titolo un titolo
		for (Giocatore giocatore : giocatoriSenzaTitolo) {
			TipoTitolo titolo = titoliNonAssegnati.remove(0);
			giocatore.setTipoTitolo(titolo);
			renderer.stampaAssegnazioneTitolo(giocatore, titolo);
		}

		assegnaCreditiPerTitoli(giocatoriInVolo);
	}

	private void valutaDifesaTitoli() {
		// alla fine del secondo volo chi ha completato il volo può difendere il titolo
		
		List<Giocatore> difensoriTitolo = new ArrayList<>();
		for (Giocatore giocatore : giocatoriInVolo) {

			// solo i giocatori in volo possono difendere il titolo
		    if (giocatore.getTipoTitolo() == null)
		        continue;
		
			Titolo titolo = giocatore.getTipoTitolo().getTitolo();
		    // controllo se il giocatore ha difeso il titolo
		    if(giocatore.equals(titolo.valutaTitolo(giocatoriInVolo))) {
		    	// chi difende il titolo rende il proprio titolo oro e guadagna crediti
		    	giocatore.getTipoTitolo().getTitolo().rendiOro();
		    	difensoriTitolo.add(giocatore);
		    	
		    	renderer.stampaDifesaTitolo(giocatore);
		    }
		}
		assegnaCreditiPerTitoli(difensoriTitolo);		
	}

	/**
	 * assegna i crediti ai giocatori nella lista
	 * 
	 * @param giocatori
	 */
	public void assegnaCreditiPerTitoli(List<Giocatore> giocatori) {
		giocatori.forEach(
				giocatore -> giocatore.aggiungiCrediti(giocatore.getTipoTitolo().getTitolo().getCrediti(livello)));
	}

	/**
	 * Associa ad ogni giocatore un titolo randomicamente, viene chiamate se nessuno
	 * ha finito il volo.
	 */
	public void assegnaTitoloRandom() {
		RandomUtil random = new RandomUtil();
		TipoTitolo titolo;
		List<TipoTitolo> titoliAssegnati = new ArrayList<>();

		for (ManagerDiVolo manager : tuttiManagers) {

			// Genero randomicamente un titolo, 2 giocatori non possono avere lo stesso
			// titolo
			do {
				titolo = random.randomEnum(TipoTitolo.class);
			} while (titoliAssegnati.contains(titolo));

			manager.getGiocatore().setTipoTitolo(titolo);
			renderer.stampaAssegnazioneTitolo(manager.getGiocatore(), titolo);

			titoliAssegnati.add(titolo);
		}
	}

	// restituisce una lista dei giocatori in volo che non hanno ricevuto un titolo
	public List<Giocatore> giocatoriSenzaTitolo(List<Giocatore> giocatori) {
		List<Giocatore> giocatoriSenzaTitolo = new ArrayList<>(giocatori);
		giocatoriSenzaTitolo.removeIf(giocatore -> giocatore.getTipoTitolo() != null);
		return giocatoriSenzaTitolo;
	}

	// getter
	public LivelliPartita getLivello() {
		return livello;
	}

	public ManagerDiVolo[] getTuttiManagers() {
		return tuttiManagers;
	}

}
