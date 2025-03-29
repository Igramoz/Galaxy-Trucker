package componenti;

import java.util.Map;
import model.enums.Direzione;
import model.enums.TipoTubo;
import util.Util;

public class Cannone extends Componente {

	public static int istanze = 0;
	protected float potenzaFuoco = 1;
	private Direzione direzione; // Direzione in cui il cannone spara

	public Cannone(Map<Direzione, TipoTubo> tubiIniziali) {
		this(TipoComponente.CANNONE_SINGOLO, tubiIniziali);
	}

	protected Cannone(TipoComponente tipo, Map<Direzione, TipoTubo> tubiIniziali) {
		super(tipo, tubiIniziali);
		if (istanze == getMaxIstanze()) {
			throw new IllegalStateException("Limite massimo di istanze raggiunto per Cannone");
		}
		direzione = Direzione.SOPRA;
		incrementaIstanze();
	}

	public Cannone(Cannone can) {
		this(can.tipo, can.tubi);
		decrementaIstanze();
	}

	@Override
	public void ruota() {
		Direzione nuovaDirezione = Util.ruotaDirezione(direzione);

		if (direzione == Direzione.SOPRA)
			potenzaFuoco /= 2;
		if (nuovaDirezione == Direzione.SOPRA)
			potenzaFuoco *= 2;

		super.ruota();
		this.direzione = nuovaDirezione;
	}

	public Direzione getDirezione() {
		return direzione;
	}

	public float getPotenzaFuoco() {
		return potenzaFuoco;
	}

	@Override
	public Componente clone() {
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