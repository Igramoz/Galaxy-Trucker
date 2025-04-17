package cartaAvventura;

public abstract class Carta {
    private final TipoCarta tipoCarta;
    
    public Carta(TipoCarta tipoCarta) {
    	this.tipoCarta = tipoCarta;
    }
    
    public TipoCarta getTipoCarta() {
    	return tipoCarta;
    }
    
    abstract void eseguiEvento();
    
}
