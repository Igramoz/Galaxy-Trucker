package componenti;

import model.equipaggio.TipoPedina;
import model.equipaggio.TipoPedina.Colore;
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

        if (istanze >= TipoComponente.CABINA_EQUIPAGGIO.getMaxIstanze() || checkTubi(tubiIniziali)) {
            throw new IllegalStateException("Limite massimo di istanze raggiunto per CabinaDiEquipaggio");
        }

        this.equipaggio = new ArrayList<>();
        incrementaIstanze();
    }

    public CabinaDiEquipaggio(Map<Direzione, TipoTubo> tubiIniziali, List<TipoPedina> equipaggioIniziale) {
        this(tubiIniziali);

        if (equipaggioIniziale.size() > TipoComponente.CABINA_EQUIPAGGIO.getMaxIstanze()) {
            throw new IllegalArgumentException("Troppi membri dell'equipaggio in cabina");
        }

        for (TipoPedina pedina : equipaggioIniziale) {
            aggiungiEquipaggio(pedina.getColore());
        }
    }

    // Costruttore di copia
    public CabinaDiEquipaggio(CabinaDiEquipaggio altra) {
        this(altra.tubi, altra.equipaggio);
        decrementaIstanze();
    }

    // Aggiunge una pedina all'equipaggio
    public boolean aggiungiEquipaggio(Colore colore) {
        if (equipaggio.size() >= TipoComponente.CABINA_EQUIPAGGIO.getMaxIstanze()) {
            return false; // Cabina piena
        }

        equipaggio.add(new TipoPedina(colore));
        return true;
    }

    // Rimuove una pedina dall'equipaggio
    public boolean rimuoviEquipaggio(Colore colore) {
        return equipaggio.removeIf(p -> p.getColore() == colore);
    }

    // Restituisce l'elenco dell'equipaggio
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

    // Controlla se tutti i tubi sono universali
    private boolean checkTubi(Map<Direzione, TipoTubo> tubiIniziali) {
        return tubiIniziali.values().stream().anyMatch(tubo -> tubo != TipoTubo.UNIVERSALE);
    }

    @Override
    public String toString() {
        return "CabinaDiEquipaggio{" +
                "equipaggio=" + equipaggio +
                ", istanze=" + istanze +
                '}';
    }

    @Override
    public Componente clone() {
        // TODO Implementabile il metodo 'clone'
        throw new UnsupportedOperationException("");
    }
}
