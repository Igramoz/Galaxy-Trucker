package componenti;

import java.util.Map; // Libreria standard di java
import java.util.EnumMap;

import model.enums.*;
import util.Util;

public abstract class Componente{

	protected final TipoComponente tipo;
	protected Map<Direzione, TipoTubo> tubi;

	public Componente(TipoComponente tipo, Map<Direzione, TipoTubo> tubiIniziali) {
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
	
	@Override // Devo fare override affinché l'hashcode funzioni
	public boolean equals(Object obj) {
		if (super.equals(obj))
			return true; // confronto i riferimenti
		if (obj == null || getClass() != obj.getClass())
			return false; // se l'altro oggetto è null o se sono di tipi diversi

		Componente altroComponente = (Componente) obj; // cast dell'oggetto
		
//		if (this.tipo != altroComponente.tipo) // TODO getClass controlla già che siano della stessa classe
//            return false;
		
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
		
		TipoTubo tempTubo = tubi.get(Direzione.SOPRA);
		tubi.put(Util.ruotaDirezione(Direzione.DESTRA), tubi.get(Direzione.DESTRA));
		tubi.put(Util.ruotaDirezione(Direzione.SOTTO), tubi.get(Direzione.SOTTO));
		tubi.put(Util.ruotaDirezione(Direzione.SINISTRA), tubi.get(Direzione.SINISTRA));
		tubi.put(Util.ruotaDirezione(Direzione.DESTRA), tempTubo);
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
	public abstract Componente clone();		
}