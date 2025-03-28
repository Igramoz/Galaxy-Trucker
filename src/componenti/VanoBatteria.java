package componenti;

import java.util.Map;

import model.enums.Direzione;
import model.enums.TipoTubo;

public class VanoBatteria extends Componente {


    private static int istanze = 0;
	
	private final int capacitaMassima;// TODO capacita massima batteria privata è pubblica?
	private int batterie;
	
	public VanoBatteria(Map<Direzione, TipoTubo> tubiIniziali, int capacitaMassima) {
		super(TipoComponente.VANO_BATTERIA, tubiIniziali);
		
		if(capacitaMassima < 2 || capacitaMassima > 3) {
			throw new IllegalArgumentException("La capacità massima della batteria deve essere compresa tra 1 e 3.");
		}
		
		if(istanze >= this.getMaxIstanze()) {
			throw new IllegalStateException("Limite massimo di istanze raggiunto per VanoBatteria");
		}
		this.capacitaMassima = capacitaMassima;
		this.batterie = 0;
		
		incrementaIstanze();
	}
	
	public VanoBatteria(Map<Direzione, TipoTubo> tubiIniziali, int capacitaMassima, int batterie) {
		this(tubiIniziali, capacitaMassima); // Chiamata al costruttore principale
		this.batterie = batterie;
	}

	// Costruttore di copia
	public VanoBatteria(VanoBatteria vanoBatteria) {
		this(vanoBatteria.tubi, vanoBatteria.capacitaMassima, vanoBatteria.getBatterie());
		decrementaIstanze();
	}
	
	@Override
	public Componente clone() {
		return new VanoBatteria(this);
	}
	
	// Controllo se è piena
	public boolean isFull() {
		return batterie == capacitaMassima;
	}
	
	// Carico la batteria
	public boolean caricaBatteria() {
		if(isFull()) {
			return false;
		}
		batterie++;
		return true;
	}
	
	// Scarico la batteria
	public boolean scaricaBatteria() {
		if(batterie == 0) {
			return false;
		}
		batterie--;
		return true;
	}	
	
	public int getBatterie() {
		return batterie;
	}
	
	@Override
	protected void incrementaIstanze() {
		istanze++;
	}
	
	@Override
	public int getIstanze() {
		return istanze;
	}
	
	@Override
	public void decrementaIstanze() {
		istanze --;
	}
	
	@Override
	public void resetIstanze() {
		istanze = 0;
	}
	
}