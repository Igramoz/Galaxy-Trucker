package grafica;

public class TextAligner {
	
	// TODO: valutare se rendere protected e statiche.
	// Allineo il testo a sinistra e aggiunga a capo alla fine.
	public String alignLeft(String testo) {

		validareLunghezzaTesto(testo);

		int rightPadding = FormatterGrafico.LARGHEZZA_SCHERMO - testo.length();
		return testo + " ".repeat(rightPadding) + FormatterGrafico.A_CAPO;
	}

	// Allineo il testo a destra e aggiunga a capo alla fine.
	public String alignRight(String testo) {

		validareLunghezzaTesto(testo);

		int leftPadding = FormatterGrafico.LARGHEZZA_SCHERMO - testo.length();

		// leftPadding è >= 0 perché, altrimenti, verificaLunghezzaTesto avrebbe
		// lanciato un eccezione.
		// Se c'è la possibilità, lascio spazi tra A_CAPO e il testo
		if (leftPadding > 2) {
			return " ".repeat(leftPadding - 2) + testo + "  " + FormatterGrafico.A_CAPO;
		} else {
			return " ".repeat(leftPadding) + testo + FormatterGrafico.A_CAPO;
		}
	}
	
	public String centraTestoInLarghezza(String testo, int larghezza) {
		
		validareLunghezzaTesto(testo, larghezza);
		
		int leftPadding = (larghezza - testo.length()) / 2;
		int rightPadding = larghezza - testo.length() - leftPadding;
		return " ".repeat(leftPadding) + testo + " ".repeat(rightPadding);		
	}
	
	public String alignCenter(String testo) { // Centra il testo nel prompt
		return centraTestoInLarghezza(testo, FormatterGrafico.LARGHEZZA_SCHERMO)+ FormatterGrafico.A_CAPO;
	}
	
	// valida lo schermo per una lunghezza definita
	private static void validareLunghezzaTesto(String testo, int spaziDisponibili) {
		if (testo == null) {
			throw new IllegalArgumentException("Il testo non può essere null.");
		}

		if (testo.length() > spaziDisponibili) {
	        throw new IllegalArgumentException("Il testo \"" + testo + "\" è troppo lungo per lo spazio disponibile di " + spaziDisponibili + " caratteri.");
		}
	}
	
	// valida la lunghezza per tutto lo schermo
	private void validareLunghezzaTesto(String testo) {
		validareLunghezzaTesto(testo, FormatterGrafico.LARGHEZZA_SCHERMO);
	}
	
}
