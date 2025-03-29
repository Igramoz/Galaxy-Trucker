package componenti;

import java.util.Map;
import model.enums.Direzione;
import model.enums.TipoTubo;

public class CannoneDoppio extends Cannone {

	private static int istanze = 0;

	public CannoneDoppio(Map<Direzione, TipoTubo> tubiIniziali) {
		super(TipoComponente.CANNONE_DOPPIO, tubiIniziali);
		potenzaFuoco = 2;
		this.incrementaIstanze();
	}

	public CannoneDoppio(CannoneDoppio can) {
		this(can.tubi);
		decrementaIstanze();
	}

	@Override
	public Componente clone() {
		return new CannoneDoppio(this);
	}
}