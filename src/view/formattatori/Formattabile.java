package view.formattatori;

import view.Colore;

public interface Formattabile {
	public Colore getColore(); // Colore da usare per formattare
	// Nome da usare, se è enum o classe
	default String getNome() {
	    if (this instanceof Enum<?>) {
	        return ((Enum<?>) this).name().toLowerCase();
	    }

	    // Il comportamento di default è ammesso solo per gli enum
	    throw new UnsupportedOperationException(
	            "Implementa getNome() nella classe " + this.getClass().getSimpleName()
	        );	}

}
