package model.componenti;

import java.util.Map;

import model.enums.TipoTubo;
import util.layout.Direzione;

public class MotoreDoppio extends Motore {
	
	public MotoreDoppio(Map<Direzione, TipoTubo> tubiIniziali) {
		super(TipoComponente.MOTORE_DOPPIO, tubiIniziali);		
	}
	
	public MotoreDoppio(MotoreDoppio m) {
		this(m.tubi);
		this.setPosizione(m.getPosizione());
	}
	
	@Override
	public MotoreDoppio clone() {
		return new MotoreDoppio(this);
	}
}