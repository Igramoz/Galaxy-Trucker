package model.carte.colpo;

import java.util.ArrayList;
import java.util.List;

import model.enums.Direzione;

public class Colpo {

	public enum TipoColpo {
		METEORITE, CANNONATA;
	}

	public enum DimensioniColpo {
		GROSSO, PICCOLO;
	}

	private final TipoColpo tipoColpo;
	private final DimensioniColpo dimensioniColpo;
	private final Direzione direzione;

	public Colpo(TipoColpo tipoColpo, DimensioniColpo dimensioniColpo, Direzione direzione) {
		this.tipoColpo = tipoColpo;
		this.dimensioniColpo = dimensioniColpo;
		this.direzione = direzione;
	}

	// Ordina i meteoriti in base alle direzioni
	public static List<Colpo> ordinaPerDirezione(List<Colpo> lista) {
		int n = lista.size();

		// Bubble sort basato sulla direzione
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				Colpo m1 = lista.get(j);
				Colpo m2 = lista.get(j + 1);

				if (m1.getDirezione().ordinal() > m2.getDirezione().ordinal()) {
					// Scambio
					lista.set(j, m2);
					lista.set(j + 1, m1);
				}
			}
		}

		return lista;
	}

	public TipoColpo getTipoColpo() {
		return tipoColpo;
	}

	public DimensioniColpo getDimensioniColpo() {
		return dimensioniColpo;
	}

	public Direzione getDirezione() {
		return direzione;
	}

	// Restituisce tutti i colpi che arrivano da una direzione
	public static List<Colpo> getColpiByDirezione(List<Colpo> colpi, Direzione direzione) {

		List<Colpo> out = new ArrayList<>();

		for (Colpo elemento : colpi) {
			if (elemento.getDirezione() == direzione)
				out.add(elemento);
		}
		return out;
	}

}