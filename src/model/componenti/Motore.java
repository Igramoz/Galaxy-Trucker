package model.componenti;

import java.util.Map;

import model.enums.Direzione;
import model.enums.TipoComponente;
import model.enums.TipoTubo;

public class Motore extends Componente{
	
	private final Direzione direzioneMotore = Direzione.SOTTO;
	
	public Motore(Map<Direzione, TipoTubo> tubiIniziali) {
		this(TipoComponente.MOTORE_SINGOLO, tubiIniziali);		
	}
	
	// Scrivere quà le modifiche riguardanti l'istanza sia di motore che di motore doppio.
	protected Motore(TipoComponente tipo, Map<Direzione, TipoTubo> tubiIniziali) {
		super(tipo, tubiIniziali);
		this.tubi.put(Direzione.SOTTO, TipoTubo.NESSUNO); // Non può avere tubi sotto
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
	
	@Override
	public void ruota() {
		// Il motore non può ruotare, quindi non va eseguito nulla
	}
}
