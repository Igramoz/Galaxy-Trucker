package controller.partita.fasiGioco;

import java.util.List;

import controller.partita.ModalitaGioco;
import controller.partita.fasiGioco.volo.ManagerDiVolo;
import controller.servizi.ServizioTitoli;
import model.enums.TipoMerce;
import view.formattatori.FormattatoreGrafico;
import view.io.GestoreIO;

/**
 * La classe conta i punteggi alla fine di ciascun volo e assegna i crediti 
 */
public class FineVolo {

	private final ModalitaGioco modalita;
	private final ManagerDiVolo[] arrayManager;

	private final GestoreIO io = new GestoreIO();
	private final FormattatoreGrafico formattatoreGrafico = new FormattatoreGrafico();

	/**
	 * Costruttore della clase FineVolo
	 * 
	 * @param modalita di gioco
	 * @param array    con TUTTI i manager, anche quelli RITIRATI
	 */
	public FineVolo(ModalitaGioco modalita, ManagerDiVolo[] arrayManager) {
		this.modalita = modalita;
		this.arrayManager = arrayManager;
	}

	public void assegnaRicompense() {
		io.aCapo();
		io.stampa("È giunta l'ora di assegnare le ricompense");
		io.aCapo();

		// assegno i crediti in base al criterio
		ordineArrivo();
		venditaMerci();
		perdite();
		if (modalita == ModalitaGioco.VOLO_SINGOLO)
			bellezzaNave();
		else
			assegnaTitoli();
	}

	// assegna i crediti secondo il criterio dell'ordine d'arrivo
	private void ordineArrivo() {
		io.stampa("assegnazione crediti in base all'ordine d'arrivo");
		final int maxCrediti = 4; // crediti per chi arriva primo
		int crediti = maxCrediti;

		for (ManagerDiVolo manager : arrayManager) {
			if (manager.getGiorniDiVoloGiocatore() != null) {
				io.aCapo();
				io.stampa(formattatoreGrafico.formatta(manager.getGiocatore()) + " riceve " + crediti + " crediti");

				manager.getGiocatore().aggiungiCrediti(crediti);
				crediti--;
			}
		}
	}

	// assegna i crediti in base alle merci possedute dai giocatori
	private void venditaMerci() {
		io.aCapo();
		io.stampa("assegnazione crediti in base alle merci trasportate");
		for (ManagerDiVolo manager : arrayManager) {
			List<TipoMerce> merci = manager.getGiocatore().getNave().getMerci();

			int crediti = 0;
			for (TipoMerce merce : merci) {
				crediti += merce.getValore();
			}

			io.stampa(formattatoreGrafico.formatta(manager.getGiocatore()) + " riceve " + crediti + " crediti");
			manager.getGiocatore().aggiungiCrediti(crediti);
		}
	}

	// assegna i crediti in base ai componenti persi
	private void perdite() {
		io.aCapo();
		io.stampa("rimozione crediti in base al numero di componenti persi");
		for (ManagerDiVolo manager : arrayManager) {
			int pezziDistrutti = manager.getPezziDistrutti();

			io.stampa(formattatoreGrafico.formatta(manager.getGiocatore()) + " perde " + pezziDistrutti + " crediti");
			manager.getGiocatore().aggiungiCrediti(-pezziDistrutti);
		}
	}

	// assegna i crediti in base ai connettori esposti se la mod è volo singolo
	private void bellezzaNave() {
		final int creditiVoloSingolo = 4;
		int numCrediti = 0;

		if (modalita == ModalitaGioco.VOLO_SINGOLO) {
			numCrediti = creditiVoloSingolo;
		}

		io.aCapo();
		io.stampa("assegnazione crediti in base alla nave più bella");

		int minorConnettoriEsposti = Integer.MAX_VALUE;
		ManagerDiVolo managerMinorConnettoriEsposti = null;

		for (ManagerDiVolo manager : arrayManager) {
			int connettoriEspostiManager = manager.getGiocatore().getNave().getNumConnettoriEsposti();

			io.stampa(formattatoreGrafico.formatta(manager.getGiocatore()) + " ha " + connettoriEspostiManager
					+ " connettori esposti");

			if (connettoriEspostiManager < minorConnettoriEsposti) {
				minorConnettoriEsposti = connettoriEspostiManager;
				managerMinorConnettoriEsposti = manager;
			}
		}
		io.aCapo();
		io.stampa("la nave più bella è quella di "
				+ formattatoreGrafico.formatta(managerMinorConnettoriEsposti.getGiocatore()));
		io.stampa("che guadagna " + numCrediti + " crediti");
		io.aCapo();

		managerMinorConnettoriEsposti.getGiocatore().aggiungiCrediti(numCrediti);
	}

	// assegna i titoli
	private void assegnaTitoli() {
		ServizioTitoli servizioTitoli = new ServizioTitoli(arrayManager, modalita.getlivelloPartita());
		servizioTitoli.gestisciTitoli();
	}

}
