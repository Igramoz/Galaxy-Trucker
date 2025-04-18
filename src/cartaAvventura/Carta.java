package cartaAvventura;

import grafica.CarteRenderer;
import io.GestoreIO;

public abstract class Carta {
    private final TipoCarta tipoCarta;
    protected final CarteRenderer carteRenderer = new CarteRenderer();
    protected final GestoreIO io = new GestoreIO();
    
    public Carta(TipoCarta tipoCarta) {
    	this.tipoCarta = tipoCarta;
    }
    
    public TipoCarta getTipoCarta() {
    	return tipoCarta;
    }
    
    abstract void eseguiEvento();
    
}
