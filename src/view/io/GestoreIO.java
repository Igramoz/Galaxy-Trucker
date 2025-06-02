package view.io;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import eccezioni.StringaTroppoLungaException;
import model.componenti.Componente;
import util.layout.Coordinate;
import view.Colore;
import view.GraficaConfig;
import view.TextAligner;
import view.formattatori.Formattabile;
import view.formattatori.FormattatoreGrafico;
import view.renderer.ComponenteRenderer;

public class GestoreIO implements InterfacciaUtente {

	private final static Scanner scanner = new Scanner(System.in);

	private TextAligner aligner = new TextAligner();

	/**
	 * {@inheritDoc}
	 */
	public int leggiIntero() {
		while (true) {
			try {
				int numero = scanner.nextInt();
				scanner.nextLine(); // Pulisce l'input
				return numero;
			} catch (java.util.InputMismatchException e) {
				stampa("Errore: Per favore inserire un numero intero valido.");
				scanner.nextLine(); // Consuma l'input errato
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public String leggiTesto() {
		String input;
		do {
			input = scanner.nextLine().trim();
			if (input.isEmpty()) {
				stampa("Errore: Inserire un testo non vuoto."); // anche se l'utente scrive solo spazi
			}
		} while (input.isEmpty());
		return input;
	}

	/**
	 * {@inheritDoc}
	 */
	public Coordinate leggiCoordinate() {

		int x, y;
		x = leggiCoordinata("X");
		y = leggiCoordinata("Y");

		return new Coordinate(x - GraficaConfig.OFFSET, y - GraficaConfig.OFFSET);
	}

	// Controlla la coordinata
	private int leggiCoordinata(String nomeCoordinata) {

		final int MIN_VALUE = Coordinate.MIN_COORDINATA + GraficaConfig.OFFSET;
		final int MAX_VALUE = Coordinate.MAX_COORDINATA + GraficaConfig.OFFSET;

		int valore;
		do {
			stampa("Inserisci la coordinata " + nomeCoordinata + " (" + MIN_VALUE + "-" + MAX_VALUE + "): ");
			valore = leggiIntero();

			if (valore < MIN_VALUE || valore >= MAX_VALUE)
				stampa("Valore non valido. Riprova");

		} while (valore < MIN_VALUE || valore >= MAX_VALUE);
		return valore;
	}

	/**
	 * {@inheritDoc}	 *
	 */
	public void aCapo() {
		stampa("");
	}

	/**
	 * {@inheritDoc}
	 * 
	 * BISOGNA PRIMA STAMPARE L'OUTPUT per indicare all'utente di quale azione si
	 * tratta. LA funzione stampa solo:
	 * <p>
	 * Scrivere 1 per eseguire l'azione
	 * </p>
	 * 
	 */
	public boolean leggiBoolean() {
		this.stampa("Scrivere 1 per eseguire l'azione");
		int scelta = leggiIntero();
		if (scelta == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	public void stampa(String riga) {

		if (riga == null) {
			// non è un errore così critico da lanciare un eccezione
			System.err.println(
					Colore.ROSSO.getCodice() + "Errore: La riga da stampare è null." + Colore.DEFAULT.getCodice());
			return;
		}

		TextAligner txtAligner = new TextAligner();
		try {
			riga = txtAligner.alignLeft(riga);
			riga += GraficaConfig.A_CAPO;
		} catch (StringaTroppoLungaException e) {
			System.err.println(
					"Aumentare il valore della costante LARGHEZZA_SCHERMO nella classe GraficaConfig nel package grafica a "
							+ riga.length());
		}
		System.out.println(riga);
	}

	/**
	 * {@inheritDoc}
	 */
	public void stampa(String[] righeDaStampare) {
		stampa(Arrays.asList(righeDaStampare));
	}

	/**
	 * {@inheritDoc}
	 */
	public void stampa(List<String> righeDaStampare) {

		for (String riga : righeDaStampare) {
			stampa(riga);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public int stampaMenu(String[] menu) {

		if (menu == null || menu.length == 0) {
			return -1;
		} else if (menu.length == 1) {
			stampa(menu[0]);
			return 0;
		}
		int scelta;
		boolean sceltaValida;
		String[] menuStampare = new String[menu.length];
		// in modo automatico stampa il numerp abbinato all'azione corrispondente
		for (int i = 0; i < menu.length; i++) {
			menuStampare[i] = i + " - " + menu[i];
		}

		do {
			sceltaValida = true;
			stampa(menuStampare);
			stampa("Inserire il numero corrispondente all'azione:");

			scelta = leggiIntero();

			if (scelta < 0 || scelta >= menu.length) {
				stampa("Inserire un numero compreso tra 0 e " + (menu.length - 1));
				sceltaValida = false;
			}

		} while (!sceltaValida);
		return scelta;
	}

	/**
	 * {@inheritDoc}
	 */
	public <T extends Enum<T>> T scegliEnum(Class<T> enumerato) {
		FormattatoreGrafico formattatore = new FormattatoreGrafico();
		T[] elementiEnum = enumerato.getEnumConstants();
		String[] menu = new String[elementiEnum.length];
		for (int i = 0; i < elementiEnum.length; i++) {
			if (elementiEnum[i] instanceof Formattabile) {
				menu[i] = formattatore.formatta((Formattabile) elementiEnum[i]);
			} else {
				menu[i] = elementiEnum[i].name();
			}
		}
		int scelta = stampaMenu(menu);
		return elementiEnum[scelta];
	}

	/**
	 * {@inheritDoc}
	 */
	public Componente menuComponenti(List<Componente> componenti) {

		if (componenti == null || componenti.isEmpty()) {
			return null;
		} else if (componenti.size() == 1) {
			return componenti.get(0);
		}

		FormattatoreGrafico formattatoreGrafico = new FormattatoreGrafico();
		ComponenteRenderer componenteRenderer = new ComponenteRenderer();
		String[] menu = new String[componenti.size()];
		for (int i = 0; i < componenti.size(); i++) {
			menu[i] = formattatoreGrafico.formatta(componenti.get(i).getPosizione()) + " "
					+ componenteRenderer.rappresentazioneCompletaComponente(componenti.get(i));
		}

		stampa("Scegli il componente scrivendo la posizione");
		int scelta = stampaMenu(menu);
		return componenti.get(scelta);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * Suggerisce all'utente come impostare lo schermo
	 * 
	 */
	public void aperturaGioco() {
		stampa(aligner.alignCenter(
				"Benvenuti a " + Colore.VIOLA_LILLA.getCodice() + "Galaxy Trucker" + Colore.DEFAULT.getCodice()));
		aCapo();
		stampa("In questo gioco dovrete costruire la vostra nave, affrontare le sfide poste dalle carte, e cercare di arrivare a destinazione vivi.");
		aCapo();
		stampa("Attenzione! Consigliamo di adattare la larghezza dello schermo affinchè la riga a destra non sia più visibile.");
		aCapo();
		stampa("Premi un tasto qualsiasi per iniziare...");
		scanner.nextLine();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * ringrazia l'utente e chiude lo scanner. 
	 */
	public void chiusuraGioco() {
		stampa("Grazie per aver giocato.");
		scanner.close();
	}
}
