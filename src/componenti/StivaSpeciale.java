package componenti;

import java.util.Map;
import model.enums.*;

public class StivaSpeciale extends Stiva {

	public StivaSpeciale(Map<Direzione, TipoTubo> tubiIniziali, int scomparti) {
		super(TipoComponente.STIVA_SPECIALE, tubiIniziali, scomparti); // Chiamo il costruttore restricted di Stiva

		if (scomparti < 1 || scomparti > 2) {
			throw new IllegalArgumentException("Le stive speciali devono avere 1 o 2 scomparti.");
		}
	}

	// Costruttore di copia
	public StivaSpeciale(StivaSpeciale stiva) {
		this(stiva.tubi, stiva.scomparti);
		for (int i = 0; i < scomparti; i++) {
			this.merci[i] = stiva.merci[i];
		}
		
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

}
