package eccezioni;

import grafica.GraficaConfig;

public class StringaTroppoLungaException extends RuntimeException {
	 
		private static final long serialVersionUID = 1L;

		/**
	     * Costruisce una nuova eccezione senza messaggio.
	     */
	    public StringaTroppoLungaException() {
	        super("Stringa troppo lunga per lo schermo, la lunghezza massima è: " +  GraficaConfig.LARGHEZZA_SCHERMO);
	    }

	    public StringaTroppoLungaException(String message) {
	        super(message);
	    }
}
