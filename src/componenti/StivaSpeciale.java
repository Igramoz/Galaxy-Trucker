package componenti;

import java.util.Map;

import model.enums.*;

public class StivaSpeciale extends Stiva {

	private static int istanze = 0;

	public StivaSpeciale(Map<Direzione, TipoTubo> tubiIniziali, int scomparti) {
		super(TipoComponente.STIVA_SPECIALE, tubiIniziali, scomparti); // Chiamo il costruttore restricted di Stiva

		if (scomparti < 1 || scomparti > 2) {
			throw new IllegalArgumentException("Le stive speciali devono avere 1 o 2 scomparti.");
		}
		
		incrementaIstanze();
	}

	// Costruttore di copia
	public StivaSpeciale(StivaSpeciale stiva) {
		this(stiva.tubi, stiva.scomparti);
		for (int i = 0; i < scomparti; i++) {
			this.merci[i] = stiva.merci[i];
		}
		decrementaIstanze();
	}

	@Override
	public StivaSpeciale clone() {
		return new StivaSpeciale(this);
	}

	@Override
	public boolean setMerci(TipoMerce merce) {		
		return super.setMerci(merce);
	}
	
	@Override // tutte le merci sono aggiungibili nelle stive speciali
	protected boolean isMerceAggiungibile(TipoMerce merce) {
		return true;
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
