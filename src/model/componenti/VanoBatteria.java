package model.componenti;
import java.util.Map;

import eccezioni.ComponenteNonIstanziabileException;
import model.enums.TipoTubo;
import util.layout.Direzione;

public class VanoBatteria extends Componente{

	public static final int MAX_BATTERIE = 3; // numero massimo di batterie
	public static final int MIN_BATTERIE = 2; // numero minimo di batterie
	
	private final int capacitaMassima;
	private int batterie;

	public VanoBatteria(Map<Direzione, TipoTubo> tubiIniziali, int capacitaMassima) {
		super(TipoComponente.VANO_BATTERIA, tubiIniziali);

		if (capacitaMassima < MIN_BATTERIE || capacitaMassima > MAX_BATTERIE) {
			throw new ComponenteNonIstanziabileException("Impossibile istanziare una batteria con capacità: " + capacitaMassima + " la capacità massima deve essere compresa tra 2 e 3 (inclusi).");
		}

		this.capacitaMassima = capacitaMassima;
		this.batterie = 0;

	}

	public VanoBatteria(Map<Direzione, TipoTubo> tubiIniziali, int capacitaMassima, int batterie) {
		this(tubiIniziali, capacitaMassima); // Chiamata al costruttore principale

		if (!caricaBatterie(batterie)) {
			throw new IllegalArgumentException("Il numero di batterie non è accettabile.");
		}
	}

	// Costruttore di copia
	public VanoBatteria(VanoBatteria vanoBatteria) {
		this(vanoBatteria.tubi, vanoBatteria.capacitaMassima, vanoBatteria.batterie);

	}

	@Override
	public VanoBatteria clone() {
		return new VanoBatteria(this);
	}
	
	@Override
	public boolean equals(Object obj) {
	    
	    if (obj == null || getClass() != obj.getClass()) return false;
	    
	    VanoBatteria altraBatteria = (VanoBatteria) obj;

	    // Confronta tubi e tipo (con rotazione) tramite il metodo equals di Componente
	    if (!super.equals(altraBatteria)) return false;
		if (this.capacitaMassima == ((VanoBatteria) altraBatteria).capacitaMassima) {
			return true; // Se hanno la stessa capacità massima e stessi tubi
		}

		return false;
	}

	// Privato perché nel gioco non è possibile impostare direttamente il numero di
	// batteri
	private boolean caricaBatterie(int batterie) {

		// Controllo se il numero di batterie è compreso tra 0 e la capacità massima
		if (batterie < 0 || batterie > capacitaMassima) {
			return false; // numero di batterie non accettabile
		}

		for (int i = 0; i < batterie; i++) {
			if (!caricaBatteria()) {
				return false; // batteria piena
			}
		}

		this.batterie = batterie;
		return true; // batterie caricate
	}

	public void caricaInteramenteBatteria() {
		caricaBatterie(capacitaMassima);
	}
	
	// Controllo se è piena
	public boolean isFull() {
		return batterie == capacitaMassima;
	}

	// Carico la batteria
	public boolean caricaBatteria() {
		if (isFull()) {
			return false;
		}
		batterie++;
		return true;
	}

	// Scarico la batteria
	public boolean scaricaBatteria() {
		if (batterie == 0) {
			return false;
		}
		batterie--;
		return true;
	}

	public int getBatterie() {
		return batterie;
	}

	public int getCapacitaMassima() {
		return capacitaMassima;
	}

}