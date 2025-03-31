package componenti;

import java.util.ArrayList;
import java.util.List;

import model.equipaggio.TipoPedina;
import model.equipaggio.TipoPedina.Colore;

@SuppressWarnings("unused")
public class ModuloSupportoAlieni {

    private final boolean alienoSupportato;  // true = Marrone, false = Viola
    private final List<TipoPedina> alieni;

    public ModuloSupportoAlieni(boolean alienoMarrone) {
        this.alienoSupportato = alienoMarrone;
        this.alieni = new ArrayList<>();
    }

    public boolean aggiungiAlieno(TipoPedina.Colore colore, int quantita) {
        if (colore == TipoPedina.Colore.BIANCO) {
            throw new IllegalArgumentException("Errore: Non puoi aggiungere astronauti nel modulo alieno!");
        }

        if ((alienoSupportato && colore != TipoPedina.Colore.MARRONE) ||
            (!alienoSupportato && colore != TipoPedina.Colore.VIOLA)) {
            throw new IllegalArgumentException("Errore: Tipo di alieno non supportato da questo modulo!");
        }

        TipoPedina alieno = new TipoPedina(colore);
        if (alieni.size() + quantita > alieno.getMaxQuantita()) {
            return false;  // Superato il limite massimo
        }

        for (int i = 0; i < quantita; i++) {
            alieni.add(new TipoPedina(colore));
        }
        return true;
    }

    public List<TipoPedina> getAlieni() {
        return alieni;
    }

    @Override
    public String toString() {
        return "ModuloSupportoAlieni{" +
                "alienoSupportato=" + (alienoSupportato ? "Marrone" : "Viola") +
                ", alieni=" + alieni +
                '}';
    }
}
