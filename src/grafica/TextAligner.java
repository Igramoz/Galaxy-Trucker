package grafica;

public class TextAligner {

	// Rimuove i codici ANSI
	public String rimuoviAnsi(String input) {
		if (input == null)
			return null;
	    return input.replaceAll("(?:(?:\\\\u001B)|(?:\\u001B))\\[[0-9;]*m", "");
	}
	
	// TODO: valutare se rendere protected

	// Aggiunge spazi a sinistra per estendere la stinga
	public String estendiStringa(String testo, int lunghezzaStringa) {

		int lunghezzaVisiva = lunghezzaVisivaTestoCheck(testo, lunghezzaStringa);

		return testo + " ".repeat(lunghezzaStringa - lunghezzaVisiva);
	}

	// Allineo il testo a sinistra e aggiunga a capo alla fine.
	public String alignLeft(String testo) {
		return estendiStringa(testo, GraficaConfig.LARGHEZZA_SCHERMO);
	}

	// Allineo il testo a destra e aggiunga a capo alla fine.
	public String alignRight(String testo) {

		int lunghezzaVisiva = lunghezzaVisivaTestoCheck(testo);

		int leftPadding = GraficaConfig.LARGHEZZA_SCHERMO - lunghezzaVisiva;

		// leftPadding è >= 0 perché, altrimenti, verificaLunghezzaTesto avrebbe
		// lanciato un eccezione.
		// Se c'è la possibilità, lascio spazi tra A_CAPO e il testo
		if (leftPadding > 2) {
			return " ".repeat(leftPadding - 2) + testo + "  ";
		} else {
			return " ".repeat(leftPadding) + testo;
		}
	}

	
	
	public String centraTestoInLarghezza(String testo, int larghezza) {

		int lunghezzaVisiva = lunghezzaVisivaTestoCheck(testo, larghezza);
        
		int leftPadding = (larghezza - lunghezzaVisiva) / 2;
		return estendiStringa(" ".repeat(leftPadding) + testo, larghezza);
	}

	
	
	public String alignCenter(String testo) { // Centra il testo nel prompt
		return centraTestoInLarghezza(testo, GraficaConfig.LARGHEZZA_SCHERMO);
	}

	// valida lo schermo per una lunghezza definita
	private int lunghezzaVisivaTestoCheck(String testo, int spaziDisponibili) {
		if (testo == null) {
			throw new IllegalArgumentException("Il testo non può essere null.");
		}
		int lunghezzaVisiva = rimuoviAnsi(testo).length();
		
		if (lunghezzaVisiva > spaziDisponibili) {
			throw new IllegalArgumentException("Il testo \"" + testo + "\" è troppo lungo per lo spazio disponibile di "
					+ spaziDisponibili + " caratteri.");
		}
		return lunghezzaVisiva;
	}

	// valida la lunghezza per tutto lo schermo
	private int lunghezzaVisivaTestoCheck(String testo) {
		return lunghezzaVisivaTestoCheck(testo, GraficaConfig.LARGHEZZA_SCHERMO);
	}

}
