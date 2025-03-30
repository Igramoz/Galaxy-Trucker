package componenti;

import java.util.Map;
import model.enums.Direzione;
import model.enums.TipoTubo;

public class CannoneDoppio extends Cannone {

	private static int istanze = 0;

	public CannoneDoppio(Map<Direzione, TipoTubo> tubiIniziali) {
		super(TipoComponente.CANNONE_DOPPIO, tubiIniziali, Direzione.SOPRA);
		potenzaFuoco = 2;
	}

	public CannoneDoppio(CannoneDoppio can) {
		super(TipoComponente.CANNONE_DOPPIO, can.tubi, can.direzioneFuoco);
		this.decrementaIstanze();
	}

	@Override
	public CannoneDoppio clone() {
		return new CannoneDoppio(this);
	}
	
	@Override
	protected void aggiornaPotenzaFuoco() {
		if(this.getDirezioneFuoco() == Direzione.SOPRA) {
			potenzaFuoco = 2.0f;
		}else {
			potenzaFuoco = 1.0f;
		}		
	}	
	
	@Override
	public int getIstanze() {
		return istanze;
	}

	@Override
	public void incrementaIstanze() {
		istanze++;
	}

	@Override
	public void decrementaIstanze() {
		istanze--;
	}

	@Override
	public void resetIstanze() {
		istanze = 0;
	}
}