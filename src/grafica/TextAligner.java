package grafica;

public class TextAligner {

	private void validareLunghezzaTesto(String testo) {
		if (testo == null) {
			throw new IllegalArgumentException("Il testo non può essere null.");
		}

		if (testo.length() > FormatterGrafico.LARGHEZZA_SCHERMO) {
			throw new IllegalArgumentException("Il testo è troppo lungo per lo schermo.");
		}
	}

	// TODO: valutare se rendere protected.
	// Allineo il testo a sinistra e aggiunga a capo alla fine.
	public String alignLeft(String testo) {

		validareLunghezzaTesto(testo);

		int rightPadding = FormatterGrafico.LARGHEZZA_SCHERMO - testo.length();
		return testo + " ".repeat(rightPadding) + FormatterGrafico.A_CAPO;
	}

	public String alignCenter(String testo) { // Centra il testo nel prompt

		validareLunghezzaTesto(testo);

		int leftPadding = (FormatterGrafico.LARGHEZZA_SCHERMO - testo.length()) / 2;
		int rightPadding = FormatterGrafico.LARGHEZZA_SCHERMO - testo.length() - leftPadding;
		return " ".repeat(leftPadding) + testo + " ".repeat(rightPadding) + FormatterGrafico.A_CAPO;

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
}
