package model.componenti;

import java.util.Map;

import eccezioni.ComponenteNonIstanziabileException;
import eccezioni.ComponentePienoException;
import model.enums.*;
import util.layout.Direzione;

public class StivaSpeciale extends Stiva {

	public static final int MAX_SCOMPARTI = 2; // numero massimo di scomparti della stiva speciale
	public static final int MIN_SCOMPARTI = 1; // numero minimo di scomparti della stiva speciale

	public StivaSpeciale(Map<Direzione, TipoTubo> tubiIniziali, int scomparti) {
		super(TipoComponente.STIVA_SPECIALE, tubiIniziali, scomparti); // Chiamo il costruttore restricted di Stiva

		if (scomparti < MIN_SCOMPARTI || scomparti > MAX_SCOMPARTI) {
			throw new ComponenteNonIstanziabileException("Le stive speciali devono avere 1 o 2 scomparti.");
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
	public void aggiungi(TipoMerce merce) throws ComponentePienoException {
		super.aggiungi(merce);
	}

	@Override // tutte le merci sono aggiungibili nelle stive speciali
	protected boolean isMerceAggiungibile(TipoMerce merce) {
		return true;
	}

}
