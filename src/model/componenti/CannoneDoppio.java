package model.componenti;

import java.util.Map;

import model.enums.Direzione;
import model.enums.TipoTubo;

public class CannoneDoppio extends Cannone {

	public CannoneDoppio(Map<Direzione, TipoTubo> tubiIniziali) {
		super(TipoComponente.CANNONE_DOPPIO, tubiIniziali, Direzione.SOPRA);
		potenzaFuoco = 2;
	}

	public CannoneDoppio(CannoneDoppio can) {
		super(TipoComponente.CANNONE_DOPPIO, can.tubi, can.direzioneFuoco);
	}

	@Override
	public CannoneDoppio clone() {
		return new CannoneDoppio(this);
	}

	@Override
	protected void aggiornaPotenzaFuoco() {
		if (this.getDirezioneFuoco() == Direzione.SOPRA) {
			potenzaFuoco = 2.0f;
		} else {
			potenzaFuoco = 1.0f;
		}
	}
}