package partita.fasiGioco.composizioneNave;

import componenti.Componente;
import model.Giocatore;
import servizi.ServizioAssemblaggio;
import util.Coordinate;
import grafica.*;
import grafica.renderer.ComponenteRenderer;
import grafica.renderer.NaveRenderer;
import io.GestoreIO;
import partita.LivelliPartita;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ManagerTurnoComposizione {

	private final Giocatore giocatore;
	private final ServizioAssemblaggio servizioAssemblaggio;
	private final GestoreIO io= new GestoreIO();
	private final FormattatoreGrafico formattatore = new FormattatoreGrafico();
	private final NaveRenderer naveRenderer = new NaveRenderer();
	private final ComponenteRenderer componenteRenderer = new ComponenteRenderer();
	private final TextAligner txtAligner = new TextAligner();
	private final String[] azioniDisponibiliSuComponente = { "Ruota", "Posiziona", "Prenota"};

	private List<Componente> componentiPrenotati = new ArrayList<>();
	private List<Componente> componentiScartati;
	private boolean turnoTerminato;

	public ManagerTurnoComposizione(List<Componente> componentiScartati, Giocatore giocatore, LivelliPartita livello,
			ServizioAssemblaggio servizio) {
		this.componentiScartati = componentiScartati;
		this.giocatore = giocatore;
		this.servizioAssemblaggio = servizio;
		turnoTerminato = false;
		giocatore.setNave(livello.getTipoNave());
	}

	public boolean getTurnoTerminato() {
		return turnoTerminato;
	}

	public Giocatore getGiocatore() {
		return giocatore;
	}

	public int getPezziPrenotatiSize() {
		return componentiPrenotati.size();
	}
	// Restituisce vero se il goicatore ha finito la nave
	public boolean gestisciTurno() {

		// Se il giocatore ha già terminato il turno non eseguo nulla
		if (turnoTerminato)
			return true;

		mostraStato();

		boolean sceltaValida;
		do {
			sceltaValida = true;

			switch (mostraMenu()) {
			case 1 -> guardaCarte();
			case 2 -> estraiNuovoComponente();
			case 3 -> usaComponenteScartato();
			case 4 -> usaComponentePrenotato();
			case 0 -> {
				turnoTerminato = true;
				return true;
			}
			default -> {
				io.stampa("Scelta non valida, scegliere nuovamente:");
				sceltaValida = false;
			}
			}
		} while (!sceltaValida);

		return false;
	}

	private void mostraStato() {
		io.aCapo();
		io.stampa(txtAligner.alignCenter("Turno di: " + formattatore.formattaGiocatore(giocatore)));
		io.aCapo();
		io.stampa(naveRenderer.rappresentazioneNave(giocatore.getNave()));
		io.aCapo();
		io.stampa("Componenti prenotati:");
		io.stampa(componenteRenderer.rappresentaComponenti(componentiPrenotati));
		io.aCapo();
		io.stampa("Componenti scartati:");
		io.stampa(componenteRenderer.rappresentaComponenti(componentiScartati));
		io.aCapo();
	}

	private int mostraMenu() {
		final String[] azioni = { "Nave completata", "Guarda carte", "Estrai componente nuovo",
				"Usa un componente scartato", "Usa un componente prenotato" };

		return io.stampaMenu(azioni);
	}

	// Azioni corrispondenti alle scelte del menu

	private void guardaCarte() {
		// TODO
		// da implementare: interazione con servizio carte e selezione mazzetti
		System.out.println("Mostro le carte disponibili...");
	}

	private void estraiNuovoComponente() {
		String[] azioniDisponibili = Arrays.copyOf(azioniDisponibiliSuComponente, azioniDisponibiliSuComponente.length + 1);
		azioniDisponibili[azioniDisponibili.length - 1] = "Scarta";
		Componente estratto = servizioAssemblaggio.estraiComponente();

		io.stampa("Hai estratto questo componente");
		eseguiAzioneSuComponente(estratto, azioniDisponibili);
	}

	private void eseguiAzioneSuComponente(Componente estratto, String[] azioniDisponibili) {

		boolean sceltaValida;
		int scelta;
		do {
			sceltaValida = true;

			io.stampa(componenteRenderer.rappresentaComponente(estratto));
			io.aCapo();
			scelta = io.stampaMenu(azioniDisponibili);

			switch (scelta) {
			case 0 -> {
				estratto.ruota();
				sceltaValida = false;
			}
			case 1 -> sceltaValida = posizionaComponente(estratto);
			case 2 -> sceltaValida = prenotaComponente(estratto);
			case 3 -> componentiScartati.add(estratto);
			default -> {
				io.stampa("Scelta non valida, scegliere nuovamente:");
				sceltaValida = false;
			}
			}
		} while (!sceltaValida);
	}

	private boolean posizionaComponente(Componente c) {

		io.stampa("Scrivere le coordinate in cui posizionare il componente");
		Coordinate coord = io.leggiCoordinate();
		
		if(!giocatore.getNave().setComponente(c, coord)) {			
			io.stampa("Non è possibile inserire il componente in questa posizione");
			return false;
		}

		return true;
	}

	private boolean prenotaComponente(Componente c) {
		if (componentiPrenotati.size() >= 2) {
			io.stampa("Non si possono prenotare più di 2 componenti");
			return false;
		}

		componentiPrenotati.add(c);
		return true;
	}

	private void usaComponenteScartato() {
		String[] azioniDisponibili = Arrays.copyOf(azioniDisponibiliSuComponente, azioniDisponibiliSuComponente.length + 1);
		azioniDisponibili[azioniDisponibili.length - 1] = "Scarta";
		scegliComponenteDaLista(componentiScartati, azioniDisponibili);
	}

	private void usaComponentePrenotato() {
		scegliComponenteDaLista(componentiPrenotati, azioniDisponibiliSuComponente);
	}
	
	private void scegliComponenteDaLista(List<Componente> lista, String[] azioniDisponibili) {
		if (lista.isEmpty()) {
			io.stampa("La lista è vuota");
			return;
		}
		io.stampa("Scrivere il numero corrispondente al componente:");

		// Sceglie il componente prenotato
		int scelta;
		boolean sceltaValida;
		do {
			sceltaValida = true;
			scelta = io.leggiIntero();
			if (scelta < 1 || scelta > lista.size()) {
				io.stampa("Scrivere un numero compreso tra 1 e " + lista.size());
				sceltaValida = false;
			}

		} while (!sceltaValida);

		scelta--; // scelta deve essere 0 based
		Componente scelto = lista.remove(scelta);
		eseguiAzioneSuComponente(scelto, azioniDisponibili);
		
	}
	
}