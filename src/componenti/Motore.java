package componenti;

import java.util.Map;

import model.enums.Direzione;
import model.enums.TipoTubo;

public class Motore extends Componente{
	
	private final Direzione direzioneMotore = Direzione.SOTTO;
	
	public Motore(Map<Direzione, TipoTubo> tubiIniziali) {
		this(TipoComponente.MOTORE_SINGOLO, tubiIniziali);		
	}
	
	// Scrivere quà le modifiche ad entrambi.
	protected Motore(TipoComponente tipo, Map<Direzione, TipoTubo> tubiIniziali) {
		super(tipo, tubiIniziali);
	}
	
	public Motore(Motore m) {		
		this(TipoComponente.MOTORE_SINGOLO, m.tubi);
	}
	
	public Direzione getDirezioneMotore() {
		return this.direzioneMotore;
	}
	
	@Override
	public Motore clone() {
		return new Motore(this);
	}

	
}
