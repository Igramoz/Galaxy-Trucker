package model.componenti.motori;

import java.util.Map;

import model.componenti.TipoComponente;
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