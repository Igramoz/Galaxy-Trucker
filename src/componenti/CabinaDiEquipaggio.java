package componenti;

import model.equipaggio.TipoPedina;
import model.enums.Direzione;
import model.enums.TipoTubo;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CabinaDiEquipaggio extends Componente {

    private static int istanze = 0;
    private final List<TipoPedina> equipaggio;

    public CabinaDiEquipaggio(Map<Direzione, TipoTubo> tubiIniziali) {
        super(TipoComponente.CABINA_EQUIPAGGIO, tubiIniziali);

        if (istanze >= TipoComponente.CABINA_EQUIPAGGIO.getMaxIstanze()) {
            throw new IllegalStateException("Limite massimo di istanze raggiunto per CabinaDiEquipaggio");
        }

        this.equipaggio = new ArrayList<>();
        incrementaIstanze();
    }

    public CabinaDiEquipaggio(Map<Direzione, TipoTubo> tubiIniziali, List<TipoPedina> equipaggioIniziale) {
        this(tubiIniziali);

        for (TipoPedina pedina : equipaggioIniziale) {
            aggiungiEquipaggio(pedina.getColore());
        }
    }

    public CabinaDiEquipaggio(CabinaDiEquipaggio altra) {
        this(altra.tubi, altra.equipaggio);
        decrementaIstanze();
    }

    public boolean aggiungiEquipaggio(TipoPedina.Colore colore) {
        equipaggio.add(new TipoPedina(colore));
        return true;
    }

    public boolean rimuoviEquipaggio(TipoPedina.Colore colore) {
        return equipaggio.removeIf(p -> p.getColore() == colore);
    }

    public List<TipoPedina> getEquipaggio() {
        return new ArrayList<>(equipaggio);
    }

    @Override
    public int getIstanze() {
        return istanze;
    }

    @Override
    public void incrementaIstanze() {
        istanze++;
    }

    @Override
    public void decrementaIstanze() {
        istanze--;
    }

    @Override
    public void resetIstanze() {
        istanze = 0;
    }

    @Override
    public CabinaDiEquipaggio clone() {
        return new CabinaDiEquipaggio(this);
    }
}
