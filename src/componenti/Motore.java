package componenti;

import java.util.Map;

import model.enums.Direzione;
import model.enums.TipoTubo;

public class Motore extends Componente{
	
	private static int istanze = 0;
	private final Direzione direzioneMotore = Direzione.SOTTO;
	
	public Motore(Map<Direzione, TipoTubo> tubiIniziali) {
		this(TipoComponente.MOTORE_SINGOLO, tubiIniziali);		
	}
	
	protected Motore(TipoComponente tipo, Map<Direzione, TipoTubo> tubiIniziali) {
		super(tipo, tubiIniziali);
		if (this.getIstanze() == this.getMaxIstanze()) {
			throw new IllegalStateException("Limite massimo di istanze raggiunto per il tipo: "+ tipo.name());
		}
		this.incrementaIstanze();
	}
	
	public Motore(Motore m) {		
		this(TipoComponente.MOTORE_SINGOLO, m.tubi);
		this.decrementaIstanze();
	}
	
	public Direzione getDirezioneMotore() {
		return this.direzioneMotore;
	}
	
	@Override
	public Motore clone() {
		return new Motore(this);
	}

	@Override
	public int getIstanze() {
		return istanze;
	}

	@Override
	protected void incrementaIstanze() {
		istanze++;
	}

	@Override
	public void resetIstanze() {
		istanze = 0;
	}

	@Override
	protected void decrementaIstanze() {
		istanze--;
	}
	
}
