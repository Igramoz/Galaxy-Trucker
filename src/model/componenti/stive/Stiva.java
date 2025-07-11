package model.componenti.stive;

import java.util.Map;

import eccezioni.CaricamentoNonConsentitoException;
import eccezioni.ComponenteNonIstanziabileException;
import eccezioni.ComponentePienoException;
import model.componenti.Componente;
import model.componenti.Contenitore;
import model.componenti.TipoComponente;
import model.enums.*;
import util.layout.Direzione;

public class Stiva extends Componente implements Contenitore<TipoMerce> {

	public static final int MAX_SCOMPARTI = 3; // numero massimo di scomparti della stiva
	public static final int MIN_SCOMPARTI = 2; // numero minimo di scomparti della stiva

	protected final int scomparti; // numero di scomparti della stiva, devono essere ereditati
	protected TipoMerce[] merci; // lista delle merci presenti nella stiva, devono essere ereditati

	public Stiva(Map<Direzione, TipoTubo> tubiIniziali, int scomparti) {
		this(TipoComponente.STIVA, tubiIniziali, scomparti);

		// Controllo che abbia un numero di scomparti valido
		if (scomparti < 2 || scomparti > 3) {
			throw new ComponenteNonIstanziabileException(
					"Impossibile istanziare una stiva semplice con " + scomparti + " scomparti deve averne 2 o 3.");
		}

	}

	// Costruttore senza controlli usato sia da Stiva che da StivaSpeciale
	protected Stiva(TipoComponente tipo, Map<Direzione, TipoTubo> tubiIniziali, int scomparti) {
		super(tipo, tubiIniziali);

		this.scomparti = scomparti; // non posso metterlo in inizializzaStiva perché è final
		this.merci = new TipoMerce[scomparti];

	}

	// Costruttore di copia
	public Stiva(Stiva stiva) {
		this(stiva.tubi, stiva.scomparti);
		for (int i = 0; i < scomparti; i++) {
			this.merci[i] = stiva.merci[i];
		}
		this.setPosizione(stiva.getPosizione());
	}

	@Override
	public Stiva clone() {
		return new Stiva(this);
	}

	// Restituisco false se non è possibile aggiungere la merce
	public void aggiungi(TipoMerce merce) throws ComponentePienoException {

		// Controllo se la merce può essere immagazzinata in stiva
		if (!isMerceAggiungibile(merce)) {
			throw new CaricamentoNonConsentitoException("Non è possibile caricare merce rossa sulla stiva semplice");
		}

		// Carico la merce
		for (int i = 0; i < scomparti; i++) {
			if (merci[i] == null) {
				merci[i] = merce;
				return;
			}
		}
		throw new ComponentePienoException("La stiva è piena non è possibile caricare ulteriore merce");
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || getClass() != obj.getClass())
			return false;

		Stiva stiva = (Stiva) obj;
		if (!super.equals(stiva))
			return false;

		if (this.scomparti == stiva.scomparti) {
			return true; // Se hanno la stessa capacità massima e stessi tubi
		}
		return false;
	}

	// Funzione override perché nel figlio le condizioni cambiano
	protected boolean isMerceAggiungibile(TipoMerce merce) {
		if (merce == TipoMerce.ROSSO) {
			return false;
		}
		return true;
	}

	/**
	 * rimuove una merce specifica dalla stiva
	 * 
	 * @param pedina
	 * @return vero se la pedina è stata rimossa
	 */
	public boolean rimuovi(TipoMerce merce) {

		// Controllo se la merce è presente
		for (int i = 0; i < scomparti; i++) {
			if (merci[i] == merce) {
				merci[i] = null;
				return true;
			}
		}
		return false;
	}

	// Calcolo il valore della merce trasportata
	public int valoreMerci() {
		int valore = 0;
		for (int i = 0; i < scomparti; i++) {
			if (merci[i] != null) {
				valore += merci[i].getValore();
			}
		}
		return valore;
	}

	public TipoMerce[] getMerci() {
		// Genero una copia dell'array di merci
		TipoMerce[] copiaMerci = new TipoMerce[scomparti];

		for (int i = 0; i < scomparti; i++) {
			copiaMerci[i] = merci[i];
		}

		return copiaMerci;
	}

	public int getScomparti() {
		return scomparti;
	}

	public boolean isFull() {
		for (int i = 0; i < scomparti; i++) {
			if (merci[i] == null) {
				return false;
			}
		}
		return true;
	}

	public boolean isEmpty() {
		return merci.length == 0;
	}
}
