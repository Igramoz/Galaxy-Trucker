package model.carte;


import grafica.FormattatoreGrafico;
import grafica.TextAligner;
import grafica.renderer.CarteRenderer;
import grafica.renderer.NaveRenderer;
import io.GestoreIO;
import model.Giocatore;

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
    
    public abstract void eseguiEvento(Giocatore[] giocatori);    
}
