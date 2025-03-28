package componenti;

import java.util.Map;

import model.enums.Direzione;
import model.enums.TipoTubo;

public class GeneratoreDiScudi extends Componente {


    private static int istanze = 0;
    private boolean statoScudo;
    private Direzione direzione1;
    private Direzione direzione2;

    public GeneratoreDiScudi(Map<Direzione, TipoTubo> tubiIniziali) {

        
    	super(TipoComponente.SCUDO, tubiIniziali);
    	if(istanze >= TipoComponente.SCUDO.getMaxIstanze()) {
    		throw new IllegalStateException("Limite massimo di istanze raggiunto per GeneratoreDiScudi");
    	}
    	incrementaIstanze();
        this.statoScudo = false;
        this.direzione1 = Direzione.SOPRA;
        this.direzione2 = Direzione.SINISTRA;
    }

    public GeneratoreDiScudi(GeneratoreDiScudi g) { // costruttore di copia

        super(g);
        this.statoScudo = g.statoScudo;
        this.direzione1 = g.direzione1;
        this.direzione2 = g.direzione2;
        decrementaIstanze();
    }

    public void attivaScudo() {
        this.statoScudo = true;
    }

    public void disattivaScudo() {
        this.statoScudo = false;
    }

    public boolean getstatoScudo() {
        return statoScudo;
    }

    public Direzione getDirezione1() {
        return direzione1;
    }

    public Direzione getDirezione2() {
        return direzione2;
    }


    @Override
    public void ruota() {
    	
    	super.ruota();
    	
    	this.direzione1 = nuovaDirezione(direzione1);
    	this.direzione2 = nuovaDirezione(direzione2);
    }
    
    private Direzione nuovaDirezione(Direzione direzione) {
        return switch (direzione) {
            case SOPRA -> Direzione.SINISTRA;
            case SINISTRA -> Direzione.SOTTO;
            case SOTTO -> Direzione.DESTRA;
            case DESTRA -> Direzione.SOPRA;
            default -> null;//TODO eccezzione
        }; 
    }
    
    @Override
    public Componente clone() {
    	
    	return new GeneratoreDiScudi(this); 
    }
    
    @Override
    public  int getIstanze() {
    	
    	return istanze;
    	
    }

    @Override
	public  void resetIstanze() {
		istanze=0;
	}
    
    @Override
	protected  void incrementaIstanze() {
		istanze++;
	}

    @Override
	protected  void decrementaIstanze() {
		istanze--;
	}
    
}

