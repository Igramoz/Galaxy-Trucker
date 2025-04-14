package io;

import grafica.GraficaConfig;
import grafica.TextAligner;

public class GestoreOutput {
	// Classe che gestisce la stampa	
    
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
    
    public void aCapo() {
        stampa("");
    }
   
    
    
    
    
    
    
    // TODO definire una funzione per pulire lo schermo (deve funzionare per tutti i sistemi operativi e per la console di Eclipse
    // salvare la schermata in quella precedente prima di aggiornarla
    
//    private void clearScreen() {
//		//System.out.print("\033[H\033[2J");
//		//System.out.flush();
//	}
    
    
}
