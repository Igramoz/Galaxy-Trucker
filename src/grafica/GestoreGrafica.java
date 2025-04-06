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
    
    // TODO definire una funzione per pulire lo schermo (deve funzionare per tutti i sistemi operativi e per la console di Eclipse
    // salvare la schermata in quella precedente prima di aggiornarla
}
