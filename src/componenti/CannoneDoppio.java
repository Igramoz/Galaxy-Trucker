package componenti;

import java.util.Map;
import model.enums.Direzione;
import model.enums.TipoTubo;

public class CannoneDoppio extends Cannone {

	private static int istanze = 0;

	public CannoneDoppio(Map<Direzione, TipoTubo> tubiIniziali, int x, int y) {
		super(TipoComponente.CANNONE_DOPPIO, tubiIniziali, x, y);
		this.incrementaIstanze();
	}

	public CannoneDoppio(CannoneDoppio can) {
		this(can.tubi, can.posizione.getX(), can.posizione.getY());
	}

	public boolean spara(int numBatterie, Componente[][] griglia) {
		if (numBatterie < 1)
			return false;
		else {
			// TODO: diminuire di 1 le batterie
			super.spara(griglia);
		}
		return true;
	}

	@Override
	public Componente clone() {
		return new CannoneDoppio(this);
	}
}