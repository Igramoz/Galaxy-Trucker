package model.componenti;

import java.util.EnumMap; // Libreria standard di java
import java.util.Map;

import eccezioni.ComponenteNonIstanziabileException;
import model.enums.*;
import util.layout.Coordinate;
import util.layout.Direzione;

public abstract class Componente {

	protected final TipoComponente tipo;
	protected Map<Direzione, TipoTubo> tubi;
	private Coordinate posizione;

	public Componente(TipoComponente tipo, Map<Direzione, TipoTubo> tubiIniziali) {
		if (tubiIniziali == null)
			throw new ComponenteNonIstanziabileException("Il parametro 'tubiIniziali' non può essere null: ogni componente deve avere una configurazione iniziale dei tubi.");
		this.tipo = tipo;
		this.tubi = new EnumMap<>(Direzione.class);

		// Inizializza le direzioni con i tipi di tubi specificati
		for (Direzione direzione : Direzione.values()) {

			// setto i tubi, se manca la direzione, di default nessuno
			this.tubi.put(direzione, tubiIniziali.getOrDefault(direzione, TipoTubo.NESSUNO));
		}
	}

	// Costruttore di copia
	public Componente(Componente componente) {
		this(componente.tipo, componente.tubi); // chiama l'altro costruttore
	}

	@Override // Devo fare override per fare funzionare le funzioni delle collections
	public boolean equals(Object obj) {
		if (super.equals(obj))
			return true; // confronto i riferimenti
		if (obj == null || getClass() != obj.getClass())
			return false; // se l'altro oggetto è null o se sono di tipi diversi

		Componente altroComponente = (Componente) obj; // cast dell'oggetto
		
		Direzione[] direzione = Direzione.values();

		int offset = -1; // I tubi sono uguali anche se ruotati di offset volte

		// Cerco se altroComponente ha un tubo uguale a quello in alto di this
		for (int i = 0; i < direzione.length; i++) {
			if (this.getTubo(direzione[0]) == altroComponente.getTubo(direzione[i])) {
				offset = i;
				break;
			}
		}
		if (offset == -1)
			return false; // Non hanno neanche un tubo uguale

		// Gli oggetti sono uguali anche se hanno gli stessi tubi ruotati
		for (int i = 0; i < direzione.length; i++)
			if (this.getTubo(direzione[i]) != altroComponente.getTubo(direzione[(i + offset) % direzione.length]))
				// % direzione.length per far ciclare i valori tra 0 e 3

				return false; // Se dopo l'offset hanno un tubo diverso i 2 componenti non sono uguali
		return true;
	}

	public void ruota() {
		// Ruota i tubi in senso antiorario
		Map<Direzione, TipoTubo> tubiRuotati = new EnumMap<>(Direzione.class);

		for (Direzione direzione : Direzione.values()) {
			Direzione nuovaDirezione = direzione.ruota();
			tubiRuotati.put(nuovaDirezione, tubi.get(direzione));
		}
		tubi.putAll(tubiRuotati);
	}

	public TipoComponente getTipo() {
		return tipo;
	}

	public TipoTubo getTubo(Direzione direzione) {
		return tubi.get(direzione);
	}

	// genera uncopia dei tubi
	public Map<Direzione, TipoTubo> getTubi() {
		Map<Direzione, TipoTubo> copiaTubi = new EnumMap<>(Direzione.class);
		Direzione[] direzione = Direzione.values();
		for (int i = 0; i < direzione.length; i++) {
			tubi.put(direzione[i], this.tubi.get(direzione[i]));
		}
		return copiaTubi;
	}

	public int getMaxIstanze() {
		return tipo.getMaxIstanze();
	}

	public Coordinate getPosizione() {
		return posizione;
	}

	/**
	 * Setta la posizione del componente
	 *  
	 * @param posizione
	 * @throws IllegalStateException se il componente è stato già posizionato
	 */
	public void setPosizione(Coordinate posizione) {
		if (this.posizione == null) {
			this.posizione = posizione;
		} else {
			throw new IllegalStateException("Non si può spostare un componente");
		}
	}

	public abstract Componente clone();
}