package model.componenti;

import java.util.Map;

import eccezioni.CaricamentoNonConsentitoException;
import eccezioni.ComponenteNonIstanziabileException;
import eccezioni.ComponentePienoException;
import eccezioni.ComponenteVuotoException;
import model.enums.TipoTubo;
import util.layout.Direzione;

public class VanoBatteria extends Componente {

	public static final int MAX_BATTERIE = 3; // numero massimo di batterie
	public static final int MIN_BATTERIE = 2; // numero minimo di batterie

	private final int capacitaMassima;
	private int batterie;

	public VanoBatteria(Map<Direzione, TipoTubo> tubiIniziali, int capacitaMassima) {
		super(TipoComponente.VANO_BATTERIA, tubiIniziali);

		if (capacitaMassima < MIN_BATTERIE || capacitaMassima > MAX_BATTERIE) {
			throw new ComponenteNonIstanziabileException("Impossibile istanziare una batteria con capacità: "
					+ capacitaMassima + " la capacità massima deve essere compresa tra 2 e 3 (inclusi).");
		}

		this.capacitaMassima = capacitaMassima;
		this.batterie = 0;

	}

	public VanoBatteria(Map<Direzione, TipoTubo> tubiIniziali, int capacitaMassima, int batterie) {
		this(tubiIniziali, capacitaMassima); // Chiamata al costruttore principale

		try {
			caricaBatterie(batterie);
		} catch ( ComponentePienoException e) {
			throw new ComponenteNonIstanziabileException("Il numero di batterie non è accettabile.");
		}
	}

	// Costruttore di copia
	public VanoBatteria(VanoBatteria vanoBatteria) {
		this(vanoBatteria.tubi, vanoBatteria.capacitaMassima, vanoBatteria.batterie);
		this.setPosizione(vanoBatteria.getPosizione());
	}

	@Override
	public VanoBatteria clone() {
		return new VanoBatteria(this);
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || getClass() != obj.getClass())
			return false;

		VanoBatteria altraBatteria = (VanoBatteria) obj;

		// Confronta tubi e tipo (con rotazione) tramite il metodo equals di Componente
		if (!super.equals(altraBatteria))
			return false;
		if (this.capacitaMassima == ((VanoBatteria) altraBatteria).capacitaMassima) {
			return true; // Se hanno la stessa capacità massima e stessi tubi
		}

		return false;
	}

	// Privato perché nel gioco non è possibile impostare direttamente il numero di
	// batteri
	private void caricaBatterie(int batterie) throws ComponentePienoException {

		if (batterie < 0) {
			throw new IllegalArgumentException("Il numero di batterie deve essere positivo");
		}

		if (batterie > capacitaMassima) {
			throw new CaricamentoNonConsentitoException(
					"Non è possibile caricare un numero di batterie superiore a: " + capacitaMassima);
		}

		for (int i = 0; i < batterie; i++) {
			caricaBatteria();
		}

		this.batterie = batterie;
	}

	public void caricaInteramenteBatteria() {
		try {
			caricaBatterie(capacitaMassima);
		} catch (ComponentePienoException e) {
			// Non dovrebbe mai accadere
			throw new IllegalStateException("Errore inatteso nel caricamento completo della batteria", e);
		}
	}

	// Controllo se è piena
	public boolean isFull() {
		return batterie == capacitaMassima;
	}

	// Carico la batteria
	public void caricaBatteria() throws ComponentePienoException {
		if (isFull()) {
			throw new ComponentePienoException("Il vano batteria è pieno non è possibile caricare ulteriore merce");
		}
		batterie++;
	}

	// Scarico la batteria
	public void scaricaBatteria() throws ComponenteVuotoException {
		if (batterie == 0) {
			throw new ComponenteVuotoException("Il vano batterie è vuoto non è possibile scaricarla ulteriormente");
		}
		batterie--;
	}

	public int getBatterie() {
		return batterie;
	}

	public int getCapacitaMassima() {
		return capacitaMassima;
	}

}