package model.equipaggio;

public class TipoPedina {
	
	// L'ENUM non ci serve per il colore, dovresti devi fare così:
//	  ASTRONAUTA,  // Astronauta (Bianco)
//    ALIENO_VIOLA,  // Alieno Viola
//    ALIENO_MARRONE; // Alieno Marrone
	
	
	

	// tutta questa parte non ha senso, però bella l'idea di fare un enum colore, magari ci servirà
	// per ora elimina tutto ciò che centra col colore, salva in un file txt che carichi nella cartella principale,
	// useremo la tua idea per le prossime classi, abbiamo già altre classi che si occupano di grafica. 
    public enum Colore {
        ASTRONAUTA(255, 255, 255),  // Astronauta (Bianco)
        ALIENO_VIOLA(128, 0, 128),  // Alieno Viola
        ALIENO_MARRONE(139, 69, 19); // Alieno Marrone

        private final int r, g, b;

        Colore(int r, int g, int b) {
            this.r = r;
            this.g = g;
            this.b = b;
        }

        public int getR() { return r; }
        public int getG() { return g; }
        public int getB() { return b; }
    }

    private final Colore colore; 

    public TipoPedina(Colore colore) {
        this.colore = colore;
    }

    public Colore getColore() {
        return colore;
    }

    // non ha senso fare gli equals per l'enum se lo fai come scritto sopra.
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        TipoPedina other = (TipoPedina) obj;
        return colore == other.colore;
    }
}


