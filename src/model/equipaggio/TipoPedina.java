package model.equipaggio;

public class TipoPedina {

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        TipoPedina other = (TipoPedina) obj;
        return colore == other.colore;
    }
}


