package model.componenti;

import java.util.Map;

import model.enums.TipoTubo;
import util.layout.Direzione;

public class Tubo extends Componente {

	public Tubo(Map<Direzione, TipoTubo> tubiIniziali) {
		super(TipoComponente.TUBO, tubiIniziali);
	}
	
	// Costruttore di copia
	public Tubo(Tubo tubo) {
		this(tubo.tubi);
		this.setPosizione(tubo.getPosizione());
	}
	
	@Override
	public Tubo clone() {
		return new Tubo(this);
	}
	
}
