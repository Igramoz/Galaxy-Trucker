package controller.partita.fasiGioco.composizioneNave;

import model.Giocatore;
import model.componenti.Componente;
import util.layout.Coordinate;
import view.*;
import view.formattatori.FormattatoreGrafico;
import view.io.GestoreIO;
import view.renderer.CarteRenderer;
import view.renderer.ComponenteRenderer;
import view.renderer.NaveRenderer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import controller.partita.LivelliPartita;
import controller.servizi.ServizioAssemblaggio;
import controller.servizi.ServizioCarte;

public class ManagerTurnoComposizione {

	private final Giocatore giocatore;
	private final ServizioAssemblaggio servizioAssemblaggio;
	private final ServizioCarte servizioCarte;
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
			ServizioAssemblaggio servizioAssemblaggio, ServizioCarte servizioCarte) {
		this.componentiScartati = componentiScartati;
		this.giocatore = giocatore;
		this.servizioAssemblaggio = servizioAssemblaggio;
		this.servizioCarte = servizioCarte;
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
			case 3 -> posizionaComponenteScartato();
			case 4 -> posizionaComponentePrenotato();
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
		io.stampa(txtAligner.alignCenter("Turno di: " + formattatore.formatta(giocatore)));
		io.aCapo();
		io.stampa(naveRenderer.rappresentazioneNave(giocatore.getNave()));
		io.aCapo();
		io.stampa("Componenti prenotati:");
		componenteRenderer.rappresentaComponenti(componentiPrenotati);
		io.stampa("Componenti scartati:");
		componenteRenderer.rappresentaComponenti(componentiScartati);
		io.aCapo();
	}

	private int mostraMenu() {
		final String[] azioni = { "Nave completata", "Guarda carte", "Estrai componente nuovo",
				"Posiziona un componente scartato", "Posiziona un componente prenotato" };

		return io.stampaMenu(azioni);
	}

	// Azioni corrispondenti alle scelte del menu

	private void guardaCarte() {
		CarteRenderer carteRenderer = new CarteRenderer();
		
		// da implementare: interazione con servizio carte e selezione mazzetti
		io.stampa("Scegli quale tra i tre mazzi noti guardare");
		String[] opzioni = {"Mazzo 0", "Mazzo 1", "Mazzo 2"};
		int scelta = io.stampaMenu(opzioni);
		
		carteRenderer.rappresentaCarte(servizioCarte.getMazzoNoto(scelta));
		
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

	private void posizionaComponenteScartato() {
		String[] azioniDisponibili = Arrays.copyOf(azioniDisponibiliSuComponente, azioniDisponibiliSuComponente.length + 1);
		azioniDisponibili[azioniDisponibili.length - 1] = "Scarta";
		scegliComponenteDaLista(componentiScartati, azioniDisponibili);
	}

	private void posizionaComponentePrenotato() {
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