package componenti;

import java.util.Map;
import model.enums.Direzione;
import model.enums.TipoTubo;
import util.Util;

public class Cannone extends Componente {

	private static int istanze = 0;
	protected float potenzaFuoco;
	protected Direzione direzioneFuoco; // Direzione in cui il cannone spara

	public Cannone(Map<Direzione, TipoTubo> tubiIniziali) {
		this(TipoComponente.CANNONE_SINGOLO, tubiIniziali, Direzione.SOPRA);
		potenzaFuoco = 1;
	}

	protected Cannone(TipoComponente tipo, Map<Direzione, TipoTubo> tubiIniziali, Direzione direzioneFuoco) {
		super(tipo, tubiIniziali);
		
		// devo usare il get perché così, a seconda dei casi accedo al n max di istanze del figlio o del padre
		if (this.getIstanze() == tipo.getMaxIstanze()) {
			throw new IllegalStateException("Limite massimo di istanze raggiunto per il tipo: " + tipo.name());
		}
		this.direzioneFuoco = direzioneFuoco;
		this.aggiornaPotenzaFuoco();
		this.incrementaIstanze();
	}

	public Cannone(Cannone can) {
		this(can.tipo, can.tubi, can.direzioneFuoco);
		this.decrementaIstanze();
	}
	
	protected void aggiornaPotenzaFuoco() {
		if(this.direzioneFuoco == Direzione.SOPRA) {
			potenzaFuoco = 1.0f;
		}else {
			potenzaFuoco = 0.5f;
		}		
	}

	@Override
	public void ruota() {
		Direzione nuovaDirezione = Util.ruotaDirezione(direzioneFuoco);

		super.ruota();
		this.direzioneFuoco = nuovaDirezione;		
		this.aggiornaPotenzaFuoco();
	}

	public Direzione getDirezioneFuoco() {
		return direzioneFuoco;
	}

	public float getPotenzaFuoco() {
		return potenzaFuoco;
	}

	@Override
	public Cannone clone() {
		return new Cannone(this);
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