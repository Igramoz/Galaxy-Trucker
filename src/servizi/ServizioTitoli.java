package servizi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import partita.LivelliPartita;
import partita.fasiGioco.volo.ManagerDiVolo;

/**
 *  Classe che gestisce i titoli e li assegna ai giocatori
 *  la classe è monouso, va istanziata ogni volta che cambia i livello
 */
public class ServizioTitoli {

	private final ManagerDiVolo[] tuttiManagers; // tutti i managers (anche quelli che hanno abbandonato il gioco
	private final ManagerDiVolo[] managersInVolo; // managers che hanno terminato il volo
	private final LivelliPartita livello; // livello del turno appena finito
	
	public ServizioTitoli( ManagerDiVolo[] managers, LivelliPartita livello) {
		this.tuttiManagers = managers;
		this.managersInVolo = filtraManagerInVolo(managers);
		this.livello = livello;
	}
	
	private ManagerDiVolo[] filtraManagerInVolo( ManagerDiVolo[] managers) {
		// converto in lista per usare la funzione removeIf
	    List<ManagerDiVolo> lista = new ArrayList<>(Arrays.asList(managers));
	    
	    // rimuovo i manager non in volo
	    lista.removeIf(manager -> !manager.isInVolo());
	    
	    // restituisco l'array
		return lista.toArray(new ManagerDiVolo[0]);
	}
	
	public void assegnaTitoli() {
		// TODO implementare
	}
	
	
	
	
	
	// getter
	public LivelliPartita getLivello() {
		return livello;
	}

	public ManagerDiVolo[] getTuttiManagers() {
		return tuttiManagers;
	}

	public ManagerDiVolo[] getManagersInVolo() {
		return managersInVolo;
	}
	
}
