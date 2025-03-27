package componenti;

import java.util.Map;

import model.enums.*;

public class StivaSpeciale extends Stiva {

	private static int istanze = 0;

	public StivaSpeciale(Map<Direzione, TipoTubo> tubiIniziali, int scomparti) {
		super(TipoComponente.STIVA_SPECIALE, tubiIniziali); // Chiamo il costruttore restricted di Stiva

		if (istanze >= this.getTipo().getMaxIstanze()) {
			throw new IllegalStateException("Limite massimo di istanze raggiunto per Stiva");
		}

		if (scomparti < 1 || scomparti > 2) {
			throw new IllegalArgumentException("Le stive speciali devono avere 1 o 2 scomparti.");
		}

		this.scomparti = scomparti;
		this.merci = new TipoMerce[scomparti];
		incrementaIstanze();
	}

	// Costruttore di copia
	public StivaSpeciale(StivaSpeciale stiva) {
		this(stiva.getTubi(), stiva.getScomparti());
		for (int i = 0; i < scomparti; i++) {
			this.merci[i] = stiva.merci[i];
		}
		decrementaIstanze();
	}

	@Override
	public Componente clone() {
		return new StivaSpeciale(this);
	}

	@Override
	public boolean setMerci(TipoMerce merce) {

		// Controllo se la merce è già presente
		for (int i = 0; i < scomparti; i++) {
			if (merci[i] == merce) {
				return false;
			}
		}

		// Aggiungo la merce
		for (int i = 0; i < scomparti; i++) {
			if (merci[i] == null) {
				merci[i] = merce;
				return true;
			}
		}
		return false; // Stiva piena
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
