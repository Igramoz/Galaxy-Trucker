package componenti;

import java.util.Map;

import model.enums.*;
import util.Util;

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
    	
    	this.direzione1 = Util.ruotaDirezione(direzione1);
    	this.direzione2 = Util.ruotaDirezione(direzione2);
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

