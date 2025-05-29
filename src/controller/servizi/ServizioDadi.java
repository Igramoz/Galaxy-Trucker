package controller.servizi;

import model.Giocatore;
import util.random.Dado;
import view.formattatori.FormattatoreGrafico;
import view.io.GestoreIO;

public class ServizioDadi {
    // funzione per lanciare i dadi (giocatore che lancia i dadi)
    public int lancia2Dadi(Giocatore giocatore) {
    	GestoreIO io = new GestoreIO();
    	FormattatoreGrafico formattatoreGrafico = new FormattatoreGrafico();
    	
    	Dado dado1 = Dado.getIstanza();
    	Dado dado2 = Dado.getIstanza();
    	
    	io.stampa( formattatoreGrafico.formatta(giocatore) + " premi un tasto per tirare i 2 dadi");
    	io.leggiTesto();
    	
    	int num1 = dado1.lancia();
    	int num2 = dado2.lancia();
    	int somma = num1 + num2;
    	io.stampa("Sono usciti i numeri: " + num1 + " e " + num2);
    	io.stampa("La somma dei dadi risulta: " + somma);
    	
    	return somma - 2;  // i dadi partono da 1  	
    }
}
