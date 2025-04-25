package model.componenti;

import java.util.Map;

import model.enums.Direzione;
import model.enums.TipoTubo;

public class MotoreDoppio extends Motore {
	
	public MotoreDoppio(Map<Direzione, TipoTubo> tubiIniziali) {
		super(TipoComponente.MOTORE_DOPPIO, tubiIniziali);		
	}
	
	public MotoreDoppio(MotoreDoppio m) {
		this(m.tubi);
	}
	
	@Override
	public MotoreDoppio clone() {
		return new MotoreDoppio(this);
	}
}