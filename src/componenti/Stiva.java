package componenti;

import java.util.Map;

import model.enums.*;

public class Stiva extends Componente {

	private static int istanze = 0;

	protected final int scomparti; // numero di scomparti della stiva, devono essere ereditati
	protected TipoMerce[] merci; // lista delle merci presenti nella stiva, devono essere ereditati

	public Stiva(Map<Direzione, TipoTubo> tubiIniziali, int scomparti) {
		this(TipoComponente.STIVA, tubiIniziali, scomparti);

		// Controllo che abbia un numero di scomparti valido
		if (scomparti < 2 || scomparti > 3) {
			throw new IllegalArgumentException("Le stive normali devono avere 2 o 3 scomparti.");
		}
		incrementaIstanze();
	}

	// Costruttore senza controlli usato sia da Stiva che da StivaSpeciale
	protected Stiva(TipoComponente tipo, Map<Direzione, TipoTubo> tubiIniziali, int scomparti) {
		super(tipo, tubiIniziali);

		if (istanze >= tipo.getMaxIstanze()) {
			throw new IllegalStateException("Limite massimo di istanze raggiunto per Stiva");
		}

		this.scomparti = scomparti; // non posso metterlo in inizializzaStiva perché è final
		this.merci = new TipoMerce[scomparti];
	}

	// Costruttore di copia
	public Stiva(Stiva stiva) {
		this(stiva.getTubi(), stiva.getScomparti());
		for (int i = 0; i < scomparti; i++) {
			this.merci[i] = stiva.merci[i];
		}
		decrementaIstanze();
	}

	@Override
	public Componente clone() {
		return new Stiva(this);
	}

	// Restituisco false se non è possibile aggiungere la merce
	public boolean setMerci(TipoMerce merce) {

		// Controllo se la merce può essere immagazzinata in stiva
		 if (!isMerceAggiungibile(merce)) {
		        return false;
		 }

		// Carico la merce
		for (int i = 0; i < scomparti; i++) {
			if (merci[i] == null) {
				merci[i] = merce;
				return true;
			}
		}
		return false; // la stiva è piena
	}
	
	// Funzione override perché nel figlio le condizioni cambiano
	protected boolean isMerceAggiungibile(TipoMerce merce) {
		if (merce == TipoMerce.ROSSO) {
			return false;
		}
		return true;
	}

	// Elimino la merce dalla stiva, false se non è presente
	public boolean eliminaMerci(TipoMerce merce) {

		// Controllo se la merce è presente
		for (int i = 0; i < scomparti; i++) {
			if (merci[i] == merce) {
				merci[i] = null;
				return true;
			}
		}
		return false;
	}

	// Elimino la merce dalla stiva, false se non è presente
	public boolean eliminaMerci(int index) {

		// Controllo se l'indice è accettabile
		if (index < 0 || index >= scomparti) {
			return false;
		}

		// Controllo se la merce è presente
		if (merci[index] != null) {
			merci[index] = null;
			return true;
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

	public boolean isStivaPiena() {
		for (int i = 0; i < scomparti; i++) {
			if (merci[i] == null) {
				return false;
			}
		}
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
