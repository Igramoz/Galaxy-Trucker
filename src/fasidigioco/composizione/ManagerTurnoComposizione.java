package fasidigioco.composizione;

import componenti.Componente;
import model.Giocatore;
import model.enums.LivelloPartita;
import servizi.ServizioAssemblaggio;
import grafica.*;
import io.*;
import nave.Nave;

import java.util.ArrayList;
import java.util.List;

public class ManagerTurnoComposizione {

	private final Giocatore giocatore;
	private final ServizioAssemblaggio servizioAssemblaggio;
	private final GestoreOutput output = new GestoreOutput();
	private final GestoreInput input = new GestoreInput();
	private final FormattatoreGrafico formattatore = new FormattatoreGrafico();
	private final ConvertitoreGrafica convertitore = new ConvertitoreGrafica();
	private final TextAligner txtAligner = new TextAligner();

	private List<Componente> componentiPrenotati = new ArrayList<>();
	private List<Componente> componentiScartati;
	private boolean turnoTerminato;

	public ManagerTurnoComposizione(List<Componente> componentiScartati, Giocatore giocatore, LivelloPartita livello,
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
			case 3 -> usaComponenteEstratto();
			case 4 -> usaComponentePrenotato();
			case 5 -> rimuoviComponente();
			case 0 -> {
				turnoTerminato = true;
				return true;
			}
			default -> {
				output.stampa("Scelta non valida, scegliere nuovamente:");
				sceltaValida = false;
			}
			}
		} while (!sceltaValida);

		return false;
	}

	private void mostraStato() {
		output.aCapo();
		output.stampa(txtAligner.alignCenter("Turno di: " + formattatore.formattaGiocatore(giocatore)));
		output.aCapo();
		output.stampa(convertitore.rappresentazioneNave(giocatore.getNave()));
		output.aCapo();
		output.stampa("Componenti prenotati:");
		output.stampa(convertitore.rappresentaComponenti(componentiPrenotati));
		output.aCapo();
		output.stampa("Componenti scartati:");
		output.stampa(convertitore.rappresentaComponenti(componentiScartati));
		output.aCapo();
	}

	private int mostraMenu() {
		final String[] azioni = { "1 - Guarda carte", "2 - Estrai componente nuovo",
				"3 - Usa un componente gia estratto", "4 - Usa un componente prenotato",
				"5 - Rimuovi un componente dalla nave", "0 - Nave completata" };

		return output.stampaMenu(azioni);
	}

	// Azioni corrispondenti alle scelte del menu

	private void guardaCarte() {
		// da implementare: interazione con servizio carte e selezione mazzetti
		System.out.println("Mostro le carte disponibili...");
	}

	private void estraiNuovoComponente() {
		Componente estratto = servizioAssemblaggio.estraiComponente();

		output.stampa("Hai estratto questo componente");
		eseguiAzioneSuComponente(estratto);
	}

	private void eseguiAzioneSuComponente(Componente estratto) {
		final String[] azioniDisponibili = { "0 - Ruota", "1 - Posiziona", "2 - Prenota", "3 - Scarta" };

		boolean sceltaValida;
		int scelta;
		do {
			sceltaValida = true;

			output.stampa(convertitore.rappresentaComponente(estratto));
			output.aCapo();
			scelta = output.stampaMenu(azioniDisponibili);

			switch (scelta) {
			case 0 -> {
				estratto.ruota();
				sceltaValida = false;
			}
			case 1 -> sceltaValida = posizionaComponente(estratto);
			case 2 -> sceltaValida = prenotaComponente(estratto);
			case 3 -> componentiScartati.add(estratto);
			default -> {
				output.stampa("Scelta non valida, scegliere nuovamente:");
				sceltaValida = false;
			}
			}
		} while (!sceltaValida);
	}

	private boolean posizionaComponente(Componente c) {

		// TODO: da implementare

		return true;
	}

	private boolean prenotaComponente(Componente c) {
		if (componentiPrenotati.size() >= 2) {
			output.stampa("Non si possono prenotare più di 2 componenti");
			return false;
		}

		componentiPrenotati.add(c);
		return true;
	}

	private void usaComponenteEstratto() {
		// da implementare: mostrare pool di componenti già estratti dai mazzetti
		System.out.println("Scegli un componente estratto da usare...");
	}

	private void usaComponentePrenotato() {
		if (componentiPrenotati.isEmpty()) {
			output.stampa("Nessun componente prenotato");
			return;
		}
		output.stampa("Scrivere il numero corrispondente al componente:");

		int scelta;
		boolean sceltaValida;
		do {
			sceltaValida = true;
			scelta = input.leggiIntero();
			if (scelta < 1 || scelta > componentiPrenotati.size()) {
				output.stampa("Scrivere un numero compreso tra 1 e " + componentiPrenotati.size());
				sceltaValida = false;
			}

		} while (!sceltaValida);

		scelta--; // scelta deve essere 0 based
		Componente scelto = componentiPrenotati.remove(scelta);
		eseguiAzioneSuComponente(scelto);

	}

	private void rimuoviComponente() {
		// da implementare: scelta coordinate e rimozione da nave
		System.out.println("Seleziona componente da rimuovere...");
	}
}