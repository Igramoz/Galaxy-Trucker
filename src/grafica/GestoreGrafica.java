package grafica;


public class GestoreGrafica {
	// è necessaria un'unica istanza per farsì che tutte gli oggetti puntino alla stessa schermata
	

    
    // Metodo da usare per stampare 
    public void stampa(String riga) {
    	TextAligner txtAligner = new TextAligner();
    	riga = txtAligner.alignLeft(riga) + GraficaConfig.A_CAPO;    	
    	
    	System.out.println(riga);
    }
    
    public void stampa(String[] righeDaStampare) {
    	for(String riga : righeDaStampare) {
    		stampa(riga);
    	}    	
    }
    
    public void stampaAffiancata(String[] testoSinistra, String[] testoDestra) {
    	
    	// cerco la riga più lunga del testo a sinistra e a destra
    	int maxLunghezzaSinista = maxLunghezzaRiga(testoSinistra);
    	int maxLunghezzaDestra = maxLunghezzaRiga(testoDestra);
    	TextAligner txtAligner = new TextAligner();

    	
    	if(maxLunghezzaSinista + maxLunghezzaDestra > GraficaConfig.LARGHEZZA_SCHERMO) {
			throw new IllegalArgumentException("La somma delle righe a sinistra e a destra supera la larghezza dello schermo");
		}
    	
    	// Calcolo quante righe occupano ciascuno dei due testi
    	int righeSinistra = testoSinistra.length;
    	int righeDestra = testoDestra.length;
    	
    	// Aggiungo righe sopra e sotto al testo con meno righe
    	if(righeSinistra > righeDestra) {
            aggiungiRighe(testoDestra, righeSinistra);
    	}else if(righeDestra > righeSinistra) {
			aggiungiRighe(testoSinistra, righeDestra);
		}
    	
    	// TODO:
    	// Calcolare quanti spazi dedicare al testo di sinistra e a quello di destra.
    	// estendere tutte le stringhe in modo che siano della stessa lunghezza per entrambi i testi
    	// unire i 2 array dividendoli col separatore A_CAPO
    	// chiamare la funzione che stampa array

    	
    	
    	
    	
    }
    
    // Resituisce la lunghezza massima dato un array di righe
    private int maxLunghezzaRiga(String[] righe) {
  		int max = 0;
		for(String riga : righe) {
			if(riga.length() > max) {
				max = riga.length();
			}
		}
		return max;
	}
    
    // Aggiunge righe sopra e sotto
    private String [] aggiungiRighe(String[] righe, int righeDaRaggiungere) {
		
        if (righe == null) {
            throw new IllegalArgumentException("L'array di righe non può essere null.");
        }

        if (righeDaRaggiungere < righe.length) {
            throw new IllegalArgumentException("Il numero di righe da raggiungere non può essere minore della dimensione attuale.");
        }
    	
    	String[] output = new String[ righeDaRaggiungere];
		
    	int righeDaAggiungerePrima = (righeDaRaggiungere - righe.length)/2;

    	
		for(int i = 0; i < righeDaAggiungerePrima; i++) {
			output[i] = "";
		}
		
		for(int i = righeDaAggiungerePrima; i < righe.length; i++) {
			output[i] = " ";
		}
		
		for(int i = righeDaAggiungerePrima + righe.length; i < righeDaRaggiungere; i++) {
			output[i] = "";
		}
		
		return output;
	}
    
    // TODO definire una funzione per pulire lo schermo (deve funzionare per tutti i sistemi operativi e per la console di Eclipse
    // salvare la schermata in quella precedente prima di aggiornarla
    
//    private void clearScreen() {
//		//System.out.print("\033[H\033[2J");
//		//System.out.flush();
//	}
    
    
}
