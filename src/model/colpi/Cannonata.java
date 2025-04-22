package model.colpi;

import java.util.List;

import model.enums.Direzione;

public enum Cannonata {
	PICCOLO, GROSSO;

	private Direzione direzione;

	public Direzione getDirezione() {
		return direzione;
	}

	public void setDirezione(Direzione direzione) {
		this.direzione = direzione;
	}

	// Ordina le cannonate in base alle direzioni
	public static List<Cannonata> ordinaPerDirezione(List<Cannonata> lista) {
	    int n = lista.size();

	    // Bubble sort basato sulla direzione
	    for (int i = 0; i < n - 1; i++) {
	        for (int j = 0; j < n - i - 1; j++) {
	            Cannonata m1 = lista.get(j);
	            Cannonata m2 = lista.get(j + 1);

	            if (m1.getDirezione().ordinal() > m2.getDirezione().ordinal()) {
	                // Scambio
	                lista.set(j, m2);
	                lista.set(j + 1, m1);
	            }
	        }
	    }

	    return lista;
	}
}
