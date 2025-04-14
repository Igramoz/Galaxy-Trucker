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
import java.util.Scanner;

public class GestoreComposizioneNave {

	private final Giocatore giocatore;
	private List<Componente> componentiPrenotati;
	private final ServizioAssemblaggio servizioAssemblaggio;
	private final GestoreOutput gestoreOutput = new GestoreOutput();;
	private final GestoreInput gestoreInput = new GestoreInput();
	private final FormattatoreGrafico formattatore = new FormattatoreGrafico();
	private final ConvertitoreGrafica convertitore = new ConvertitoreGrafica();
	private final TextAligner txtAligner = new TextAligner();

	private boolean turnoTerminato;

	public GestoreComposizioneNave(Giocatore giocatore, LivelloPartita livello,
			ServizioAssemblaggio servizioAssemblaggio) {
		this.giocatore = giocatore;
		this.servizioAssemblaggio = servizioAssemblaggio;

		giocatore.setNave(livello.getTipoNave());
		turnoTerminato = false;

	}
	
	public boolean getTurnoTerminato() {
		return turnoTerminato;
	}

	public Giocatore getGiocatore() {
		return giocatore;
	}

	// Restituisce vero se il goicatore ha finito la nave
	public boolean gestisciTurno(List<Componente> componentiScartati) {

		// Se il giocatore ha già terminato il turno non eseguo nulla
		if (turnoTerminato)
			return true;

		// stampo:
		gestoreOutput.aCapo();
		gestoreOutput.stampa(txtAligner.alignCenter("Turno di: " + formattatore.formattaGiocatore(giocatore)));
		gestoreOutput.stampa(convertitore.rappresentazioneNave(giocatore.getNave()));
		gestoreOutput.aCapo();
		gestoreOutput.stampa("Componenti prenotati:");
		gestoreOutput.stampa(convertitore.rappresentaComponenti(componentiPrenotati));
		gestoreOutput.aCapo();
		gestoreOutput.stampa("Componenti scartati:");
		gestoreOutput.stampa(convertitore.rappresentaComponenti(componentiScartati));

		boolean sceltaValida;
		do {
			sceltaValida = true;

			switch (mostraMenu()) {
			case 1 -> guardaCarte();
			case 2 -> estraiNuovoComponente(componentiScartati);
			case 3 -> usaComponenteEstratto(componentiScartati);
			case 4 -> usaComponentePrenotato();
			case 5 -> rimuoviComponente();
			case 0 -> {
				turnoTerminato = true;
				return true;
			}
			default -> {
				gestoreOutput.stampa("Scelta non valida, scegliere nuovamente:");
				sceltaValida = false;
			}
			}
		} while (!sceltaValida);

		return false;
	}	

	private int mostraMenu() {

		final String[] azioniDisponibili = { "1 - Guarda carte", "2 - Estrai componente nuovo",
				"3 - Usa un componente già estratto", "4 - Usa un componente prenotato",
				"5 - Rimuovi un componente dalla nave", "0 - Nave completata" };
		gestoreOutput.stampa(azioniDisponibili);

		gestoreOutput.aCapo();
		gestoreOutput.stampa("Inserire il numero corrispondente all'azione:");
		return gestoreInput.leggiIntero();
	}

	// Azioni corrispondenti alle scelte del menu
	private void guardaCarte() {
		// da implementare: interazione con servizio carte e selezione mazzetti
		System.out.println("Mostro le carte disponibili...");
	}

	private void estraiNuovoComponente(List<Componente> componentiScartati) {
		Componente estratto = servizioAssemblaggio.estraiComponente();
		final String[] azioniDisponibili = { "0 - Ruota", "1 - Posiziona", "2 - Prenota", "3 - Scarta"};

		gestoreOutput.stampa("Hai estratto questo componente");

		boolean sceltaValida;
		do {
			sceltaValida = true;

			gestoreOutput.stampa(convertitore.rappresentaComponente(estratto));
			gestoreOutput.aCapo();
			gestoreOutput.stampa(azioniDisponibili);
			gestoreOutput.stampa("Inserire il numero corrispondente all'azione:");

			switch (gestoreInput.leggiIntero()) {
			case 0 -> estratto.ruota();
			case 1 -> sceltaValida = posizionaComponente(estratto);
			case 2 -> sceltaValida = prenotaComponente(estratto);
			case 3 -> componentiScartati.add(estratto);
			default -> {
				gestoreOutput.stampa("Scelta non valida, scegliere nuovamente:");
				sceltaValida = false;
				}
			}
		} while (!sceltaValida);

		componentiPrenotati.add(estratto); // in attesa di posizionamento
	}

	private boolean posizionaComponente(Componente c) {
		// da implementare

		return true;
	}

	private boolean prenotaComponente(Componente c) {
		if(componentiPrenotati.size() == 2) {
			gestoreOutput.stampa("Non si possono prenotare più di 2 componenti");
			return false;
		}
		
		componentiPrenotati.add(c);
		return true;
	}

	private void usaComponenteEstratto(List<Componente> componentiScartati) {
		// da implementare: mostrare pool di componenti già estratti dai mazzetti
		System.out.println("Scegli un componente estratto da usare...");
	}

	private void usaComponentePrenotato() {
		if (componentiPrenotati.isEmpty()) {
			System.out.println("Nessun componente prenotato.");
			return;
		}

		System.out.println("Componenti prenotati disponibili:");
		for (int i = 0; i < componentiPrenotati.size(); i++) {
			System.out.println((i + 1) + ": " + componentiPrenotati.get(i));
		}

		int scelta = 0;// InputUtil.leggiInt("Scegli il componente da usare: ") - 1;

		if (scelta >= 0 && scelta < componentiPrenotati.size()) {
			Componente scelto = componentiPrenotati.remove(scelta);
			// TODO: ruotare, posizionare, validare
			System.out.println("Hai selezionato: " + scelto);
		} else {
			System.out.println("Scelta non valida.");
		}
	}

	private void rimuoviComponente() {
		// da implementare: scelta coordinate e rimozione da nave
		System.out.println("Seleziona componente da rimuovere...");
	}
}