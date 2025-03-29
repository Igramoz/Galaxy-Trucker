package componenti;

import java.util.Map;

import model.Coordinate;
import model.enums.Direzione;
import model.enums.TipoTubo;
import util.Util;

public class Cannone extends Componente {

	public static int istanze = 0;
	private float potenzaFuoco;
	private Direzione direzione; // Direzione in cui il cannone spara
	protected Coordinate posizione;

	public Cannone(TipoComponente tipo, Map<Direzione, TipoTubo> tubiIniziali, int x, int y) {
		super(tipo, tubiIniziali);
		if (istanze == getMaxIstanze()) {
			throw new IllegalStateException("Limite massimo di istanze raggiunto per Cannone");
		}

		potenzaFuoco = gestisciPotenzaDiFuoco(tipo);
		if (potenzaFuoco == -1)
			throw new IllegalStateException("");
		direzione = Direzione.SOPRA;
		posizione = new Coordinate(x, y);

		incrementaIstanze();
	}

	public Cannone(Cannone can) {
		this(can.tipo, can.tubi, can.posizione.getX(), can.posizione.getY());
	}

	private int gestisciPotenzaDiFuoco(TipoComponente tipo) {
		if (tipo == TipoComponente.CANNONE_SINGOLO)
			return 1;
		else if (tipo == TipoComponente.CANNONE_DOPPIO)
			return 2;
		else
			return -1;
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

	public boolean spara(Componente[][] griglia) {
		int checkX = posizione.getX(), checkY = posizione.getY();
		switch (direzione) {
		case SOPRA:
			checkY--;
			break;
		case SINISTRA:
			checkX--;
			break;
		case SOTTO:
			checkY++;
			break;
		case DESTRA:
			checkX++;
			break;
		}
		// il cannone non può sparare se la casella davanti è occupata
		if (griglia[checkX][checkY] == null)
			return true;
		else
			return false;
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