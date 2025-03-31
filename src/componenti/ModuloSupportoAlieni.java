package componenti;

import java.util.ArrayList;
import java.util.List;
import model.equipaggio.TipoPedina;

public class ModuloSupportoAlieni {

    private static int istanze = 0;
    private final boolean supportaMarrone;
    private final List<TipoPedina> alieni;

    public ModuloSupportoAlieni(boolean supportaMarrone) {
        this.supportaMarrone = supportaMarrone;
        this.alieni = new ArrayList<>();
        istanze++;
    }

    public boolean aggiungiAlieno(TipoPedina alieno, int quantita) {
        if ((supportaMarrone && alieno.getColore() != TipoPedina.Colore.ALIENO_MARRONE) ||
            (!supportaMarrone && alieno.getColore() != TipoPedina.Colore.ALIENO_VIOLA)) {
            return false;
        }
        for (int i = 0; i < quantita; i++) {
            alieni.add(new TipoPedina(alieno.getColore())); 
        }
        return true;
    }

    public List<TipoPedina> getAlieni() {
        return new ArrayList<>(alieni);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ModuloSupportoAlieni that = (ModuloSupportoAlieni) obj;
        return supportaMarrone == that.supportaMarrone && alieni.equals(that.alieni);
    }

    public static int getIstanze() {
        return istanze;
    }
}

