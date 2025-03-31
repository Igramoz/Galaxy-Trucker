package componenti;

import model.equipaggio.TipoPedina;
import model.equipaggio.TipoPedina.Tipo;
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
            aggiungiEquipaggio(pedina.getTipo());
        }
    }

    // Costruttore di copia
    public CabinaDiEquipaggio(CabinaDiEquipaggio altra) {
        this(altra.tubi, altra.equipaggio);
        decrementaIstanze();
    }

    // Aggiunge un membro dell'equipaggio
    public boolean aggiungiEquipaggio(Tipo tipo) {
        equipaggio.add(new TipoPedina(tipo));
        return true;
    }

    // Rimuove un membro dell'equipaggio
    public boolean rimuoviEquipaggio(Tipo tipo) {
        return equipaggio.removeIf(p -> p.getTipo() == tipo);
    }

    // Restituisce la lista dell'equipaggio
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
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CabinaDiEquipaggio that = (CabinaDiEquipaggio) obj;
        return equipaggio.equals(that.equipaggio);
    }

    @Override
    public int hashCode() {
        return equipaggio.hashCode();
    }

    @Override
    public CabinaDiEquipaggio clone() {
        return new CabinaDiEquipaggio(this);
    }
}

