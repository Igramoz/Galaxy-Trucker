package cartaAvventura;

import grafica.CarteRenderer;
import grafica.FormattatoreGrafico;
import grafica.NaveRenderer;
import grafica.TextAligner;
import io.GestoreIO;
import model.Giocatore;
import model.Dado;

public abstract class Carta {
    private final TipoCarta tipoCarta;
    protected final CarteRenderer carteRenderer = new CarteRenderer();
    protected final NaveRenderer naveRenderer = new NaveRenderer();
    protected final GestoreIO io = new GestoreIO();
    protected final FormattatoreGrafico formattatoreGrafico = new FormattatoreGrafico();
    protected final TextAligner textAligner = new TextAligner();
    
    public Carta(TipoCarta tipoCarta) {
    	this.tipoCarta = tipoCarta;
    }
    
    public TipoCarta getTipoCarta() {
    	return tipoCarta;
    }
    
    abstract void eseguiEvento(Giocatore[] giocatori);
    
    // funzione per lanciare i dadi (giocatore che lancia i dadi)
    protected int lancia2Dadi(Giocatore giocatore) {
    	Dado dado1 = Dado.getIstanza();
    	Dado dado2 = Dado.getIstanza();
    	
    	io.stampa( formattatoreGrafico.formattaGiocatore(giocatore) + " premi un tasto per tirare i 2 dadi");
    	io.leggiTesto();
    	
    	int num1 = dado1.lancia();
    	int num2 = dado2.lancia();
    	
    	io.stampa("Sono usciti i numeri: " + num1 + " e " + num2);
    	
    	return num1 + num2 - 2;  // i dadi partono da 1  	
    }
    
}
