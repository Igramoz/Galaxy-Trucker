package componenti;

import java.util.Map;

import model.enums.*;
import util.Util;

public class CabinaPartenza extends Componente{
	
	private static int istanze = 0;
	private int equipaggio;
	//TODO Aggiungere un attributo colore????
	
	
	public CabinaPartenza(Map<Direzione, TipoTubo> tubiIniziali) {
		super(TipoComponente.CABINA_PARTENZA, tubiIniziali);
		if(istanze >= TipoComponente.CABINA_PARTENZA.getMaxIstanze() || checkTubi(tubiIniziali)) {
			throw new IllegalStateException("Limite massimo di istanze raggiunto per CabinaPartenza");
		}
		
		this.equipaggio = 2;
		incrementaIstanze();
	}
	
	public CabinaPartenza(Map<Direzione, TipoTubo> tubiIniziali, int equipaggio) {
		super(TipoComponente.CABINA_PARTENZA, tubiIniziali);
		if(istanze >= TipoComponente.CABINA_PARTENZA.getMaxIstanze() || checkTubi(tubiIniziali)) {
			throw new IllegalStateException("Limite massimo di istanze raggiunto per CabinaPartenza");
		}
		
		if(equipaggio > 2 || equipaggio < 0) {
			throw new IllegalArgumentException("L'equipaggio deve essere compreso tra 1 e 2");
		}
		incrementaIstanze();
	}
	
	public CabinaPartenza(CabinaPartenza cabinaPartenza) { // costruttore di copia
		super(cabinaPartenza);
		decrementaIstanze();
	}
	
	public int getEquipaggio() {
		return this.equipaggio;
	}
	
	public boolean decrementaEquipaggio() {
		if(this.equipaggio > 0) {
			this.equipaggio--;
			return true;
		} return false;
	}
	
	public boolean incrementaEquipaggio() {
		if(this.equipaggio < 2) {
			this.equipaggio++;
			return true;
		} return false;
	}
		
	@Override
    public Componente clone() {
    	
    	return new CabinaPartenza(this); 
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
    
    
    //controllo se tutti i tubi sono di tipo universale
    private boolean checkTubi(Map<Direzione, TipoTubo> tubiIniziali) {
    	
    	for(Direzione direzione : Direzione.values()) {
			if(tubiIniziali.get(direzione) != TipoTubo.UNIVERSALE) {
				return true;
			}
		}
		return false;
		
	}
	
	

}
