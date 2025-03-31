package model.equipaggio;

public class TipoPedina {

    public enum Colore {
        VIOLA,  // Alieno viola
        MARRONE // Alieno marrone
    }

    public static final TipoPedina ALIENO_VIOLA = new TipoPedina(Colore.VIOLA);
    public static final TipoPedina ALIENO_MARRONE = new TipoPedina(Colore.MARRONE);

    public final Colore colore; 

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


