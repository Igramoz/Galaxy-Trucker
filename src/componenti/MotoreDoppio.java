package componenti;

import java.util.Map;

import model.enums.Direzione;
import model.enums.TipoTubo;

public class MotoreDoppio extends Motore {
	
	private static int istanze = 0;

	public MotoreDoppio(Map<Direzione, TipoTubo> tubiIniziali) {
		super(TipoComponente.MOTORE_DOPPIO, tubiIniziali);		
	}
	
	public MotoreDoppio(MotoreDoppio m) {
		this(m.tubi);
		this.decrementaIstanze();
	}
	
	@Override
	public MotoreDoppio clone() {
		return new MotoreDoppio(this);
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
