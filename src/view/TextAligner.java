package view;

import eccezioni.StringaTroppoLungaException;

public class TextAligner {

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

	public String[] estendiStringhe(String[] testo, int lunghezzaStringa) {
		String[] testoEsteso = new String[testo.length];
		for (int i = 0; i < testo.length; i++) {
			testoEsteso[i] = estendiStringa(testo[i], lunghezzaStringa);
		}
		return testoEsteso;
	}

	public String centraTestoInLarghezza(String testo, int larghezza) {

		int lunghezzaVisiva = lunghezzaVisivaTestoCheck(testo, larghezza);

		int leftPadding = (larghezza - lunghezzaVisiva) / 2;
		return estendiStringa(" ".repeat(leftPadding) + testo, larghezza);
	}

	public String alignCenter(String testo) { // Centra il testo nel prompt
		return centraTestoInLarghezza(testo, GraficaConfig.LARGHEZZA_SCHERMO);
	}

	public String[] alignCenter(String[] testo) {
		String[] out = new String[testo.length];

		for (int i = 0; i < testo.length; i++) {
			out[i] = alignCenter(testo[i]);
		}

		return out;
	}

	// Stampa i testi affiancati
	public String[] affiancaStringhe(String[] testoSinistra, String[] testoDestra) {

		// Spazi tra il centro della pagina e il testo a destra
		int margineSinistra = 1;

		// cerco la riga più lunga del testo a sinistra e a destra
		int maxLunghezzaSinistra = maxLunghezzaRiga(testoSinistra);
		int maxLunghezzaDestra = maxLunghezzaRiga(testoDestra);
		TextAligner txtAligner = new TextAligner();

		if (maxLunghezzaSinistra + maxLunghezzaDestra >= GraficaConfig.LARGHEZZA_SCHERMO) {
			throw new StringaTroppoLungaException(
					"La somma delle righe a sinistra e a destra supera la larghezza dello schermo");
		}

		// Calcolo quante righe occupano ciascuno dei due testi
		int righeSinistra = testoSinistra.length;
		int righeDestra = testoDestra.length;

		// Aggiungo righe sopra e sotto al testo con meno righe
		if (righeSinistra > righeDestra) {
			testoDestra = aggiungiRighe(testoDestra, righeSinistra);
		} else if (righeDestra > righeSinistra) {
			testoSinistra = aggiungiRighe(testoSinistra, righeDestra);
		}

		// Calcolo lo spazio disponibile per il testo a sinistra e a destra
		int spazioDisponibile = (GraficaConfig.LARGHEZZA_SCHERMO - maxLunghezzaDestra - maxLunghezzaSinistra
				- margineSinistra - GraficaConfig.A_CAPO.length()) / 2;

		int spaziDisponibiliSinistra = spazioDisponibile + maxLunghezzaSinistra;
		// Rendo tutte le stringhe della stessa lunghezza a sinistra
		testoSinistra = txtAligner.estendiStringhe(testoSinistra, spaziDisponibiliSinistra);

		// Se c'è la possibilià di lasciare spazi tra il testo e il centro
		if (GraficaConfig.LARGHEZZA_SCHERMO - spazioDisponibile - maxLunghezzaDestra <= 2) {
			margineSinistra = 0;
		}

		// Rendo tutte le stringhe della stessa lunghezza a destra
		int spaziDisponibiliDestra = GraficaConfig.LARGHEZZA_SCHERMO - spaziDisponibiliSinistra
				- GraficaConfig.A_CAPO.length() - margineSinistra;
		testoDestra = txtAligner.estendiStringhe(testoDestra, GraficaConfig.LARGHEZZA_SCHERMO - spaziDisponibiliDestra);

		// Unisco i 2 array
		String[] righeUnite = new String[Math.max(righeSinistra, righeDestra)];

		for (int i = 0; i < righeUnite.length; i++) {
			righeUnite[i] = testoSinistra[i] + GraficaConfig.A_CAPO + " ".repeat(margineSinistra) + testoDestra[i];
		}

		return righeUnite;
	}

	// Resituisce la lunghezza massima dato un array di righe
	private int maxLunghezzaRiga(String[] righe) {
		int max = 0;
		for (String riga : righe) {
			int lunghezzaVisiva = lunghezzaVisivaTestoCheck(riga);
			if (lunghezzaVisiva > max) {
				max = lunghezzaVisiva;
			}
		}
		return max;
	}

	// Aggiunge righe sopra e sotto
	private String[] aggiungiRighe(String[] righe, int righeDaRaggiungere) {

		if (righe == null) {
			throw new NullPointerException("L'array di righe non può essere null.");
		}

		if (righeDaRaggiungere < righe.length) {
			throw new IllegalArgumentException(
					"Il numero di righe da raggiungere non può essere minore della dimensione attuale.");
		}

		String[] output = new String[righeDaRaggiungere];

		int righeDaAggiungerePrima = (righeDaRaggiungere - righe.length) / 2;

		for (int i = 0; i < righeDaAggiungerePrima; i++) {
			output[i] = "";
		}

		for (int i = 0; i < righe.length; i++) {
			output[i + righeDaAggiungerePrima] = righe[i];
		}

		for (int i = righeDaAggiungerePrima + righe.length; i < righeDaRaggiungere; i++) {
			output[i] = "";
		}

		return output;
	}

	// valida lo schermo per una lunghezza definita
	public int lunghezzaVisivaTestoCheck(String testo, int spaziDisponibili) {
		if (testo == null) {
			return 0;
		}
		int lunghezzaVisiva = rimuoviAnsi(testo).length();

		if (lunghezzaVisiva > spaziDisponibili) {
			throw new StringaTroppoLungaException("Il testo \"" + testo + "\" è troppo lungo per lo spazio disponibile di "
					+ spaziDisponibili + " caratteri.");
		}
		return lunghezzaVisiva;
	}

	// valida la lunghezza per tutto lo schermo
	public int lunghezzaVisivaTestoCheck(String testo) {
		return lunghezzaVisivaTestoCheck(testo, GraficaConfig.LARGHEZZA_SCHERMO);
	}

	// Rimuove i codici ANSI
	private String rimuoviAnsi(String input) {
		if (input == null)
			return null;
		return input.replaceAll("(?:(?:\\\\u001B)|(?:\\u001B))\\[[0-9;]*m", "");
	}
	
	// Sostiuisce e caratteri di una stringa con quelli di un'altra a partire dall'indice
	public String replace(String stringaOriginale, String stringaDaSostituire, int indice) {
	    if (stringaOriginale == null || stringaDaSostituire == null) return stringaOriginale;

	    // L'indice fornito non tiene conto degli ANSI
	    int indiceReale = trovaIndiceReale(stringaOriginale, indice);

	    // Controlla che l'indice sia valido
	    if (indiceReale == -1)
	    	throw new StringaTroppoLungaException("Indice visivo fuori dai limiti per: " + stringaOriginale + " in posizione " + indice);

	    
	    String out = stringaOriginale.substring(0, indiceReale);
	    out += stringaDaSostituire;
	    
	    if(rimuoviAnsi(out).length() < rimuoviAnsi(stringaOriginale).length())  
	    	out += stringaOriginale.substring(rimuoviAnsi(out).length());
	    
	    return out;

	}
	
	// Converte un indice visivo (escludendo ANSI) in un indice reale (inclusi i codici ANSI)
	private int trovaIndiceReale(String stringa, int indiceVisivo) {
	    int visivo = 0;
	    boolean inAnsi = false;
	    for (int i = 0; i < stringa.length(); i++) {
	        char c = stringa.charAt(i);

	        // rendiamo inAnsi vero appena incotriamo l'inizio del codice ansi
	        if (c == '\u001B') {
	            inAnsi = true;
	        }

	        if (!inAnsi) {
	            if (visivo == indiceVisivo) return i;
	            visivo++;
	        }

	        // rendiamo inAnsi falso appena incotriamo la fine del codice ansi
	        if (inAnsi && c == 'm') {
	            inAnsi = false;
	        }
	    }

	    // Se visivo == indiceVisivo alla fine della stringa
	    return visivo == indiceVisivo ? stringa.length() : -1;
	}


}
