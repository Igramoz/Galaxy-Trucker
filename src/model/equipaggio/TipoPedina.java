package model.equipaggio;

import componenti.TipoComponente;
import java.util.Objects;

public class TipoPedina {

    public enum Colore {
        BIANCO(255, 255, 255),  // Astronauta
        VIOLA(128, 0, 128),     // Alieno viola
        MARRONE(139, 69, 19);   // Alieno marrone

        private final int r, g, b;

        Colore(int r, int g, int b) {
            this.r = r;
            this.g = g;
            this.b = b;
        }

        public int getR() {
            return r;
        }

        public int getG() {
            return g;
        }

        public int getB() {
            return b;
        }

        public String getRGBString() {
            return "(" + r + ", " + g + ", " + b + ")";
        }
    }

    private final Colore colore;
    private final int maxQuantita;

    public TipoPedina(Colore colore) {
        this.colore = Objects.requireNonNull(colore, "Il colore non può essere nullo");

        switch (colore) {
            case BIANCO -> this.maxQuantita = TipoComponente.CABINA_EQUIPAGGIO.getMaxIstanze();
            case VIOLA -> this.maxQuantita = TipoComponente.SOVRASTRUTTURA_ALIENA_VIOLA.getMaxIstanze();
            case MARRONE -> this.maxQuantita = TipoComponente.SOVRASTRUTTURA_ALIENA_MARRONE.getMaxIstanze();
            default -> throw new IllegalArgumentException("Colore non valido");
        }
    }

    public Colore getColore() {
        return colore;
    }

    public int getMaxQuantita() {
        return maxQuantita;
    }

    public String getRGBString() {
        return colore.getRGBString();
    }

    @Override
    public String toString() {
        return "TipoPedina{" +
                "colore=" + colore +
                ", maxQuantita=" + maxQuantita +
                ", RGB=" + getRGBString() +
                '}';
    }
}
