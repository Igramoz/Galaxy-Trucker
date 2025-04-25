package model.componenti;

import java.util.Map;

import model.enums.Direzione;
import model.enums.TipoTubo;

public class Tubo extends Componente {

	public Tubo(Map<Direzione, TipoTubo> tubiIniziali) {
		super(TipoComponente.TUBO, tubiIniziali);
	}
	
	// Costruttore di copia
	public Tubo(Tubo tubo) {
		this(tubo.tubi);
	}
	
	@Override
	public Tubo clone() {
		return new Tubo(this);
	}
	
}
