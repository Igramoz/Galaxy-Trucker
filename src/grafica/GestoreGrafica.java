package grafica;

import java.util.ArrayList;
import java.util.List;

public class GestoreGrafica {
	// è necessaria un'unica istanza per farsì che tutte gli oggetti puntino alla stessa schermata
    private static GestoreGrafica instanza; // Unica istanza della classe
	
	private List<String> schermataPrecedente;
	private List<String> schermataAttuale;

    // Costruttore privato per impedire istanze
    private GestoreGrafica() {
        schermataPrecedente = new ArrayList<>();
        schermataAttuale = new ArrayList<>();
    }
	
    // Unico modo per accedere all'unica istanza
    public static GestoreGrafica getInstance() {
        if (instanza == null) {
            instanza = new GestoreGrafica();
        }
        return instanza;
    }
    
    // Metodo da usare per stampare 
    public void stampa(String riga) {
    	TextAligner txtAligner = new TextAligner();
    	riga = txtAligner.alignLeft(riga) + GraficaConfig.A_CAPO;    	
    	schermataAttuale.add(riga);
    	
    	System.out.println(riga);
    }
    
    public void stampa(String[] righeDaStampare) {
    	for(String riga : righeDaStampare) {
    		stampa(riga);
    	}    	
    }
    
    // TODO definire una funzione per pulire lo schermo (deve funzionare per tutti i sistemi operativi e per la console di Eclipse
    // salvare la schermata in quella precedente prima di aggiornarla
}
