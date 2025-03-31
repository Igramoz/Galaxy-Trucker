package componenti;

import java.util.ArrayList;
import java.util.List;
import model.equipaggio.TipoPedina;

public class ModuloSupportoAlieni { // TODO: extends Componente !!!

    private static int istanze = 0;
    private final boolean supportaMarrone; // TODO: scrovo private final TipoPedina alineoSupportato; e dento ci metti l'alieno viola o marrone
    private final List<TipoPedina> alieni; // TODO: il supporto alieni non ospita nessun alieno. bisogna cancellare.

    // TODO: la funzione deve richiedere come argomento il tipo di pedina supportato, quindi controlli se la pedina va bene o meno
    // ModuloSupportoAlieni ( tipoPedina pedina) // poi se pedina == TipoPedina.ASRTONAUTA lanci l'errore altimenti la agggiungi alla variabile alienoSupporato
    // controllare se è sono state istanziate troppi moduli
    //	if (this.getIstanze() == this.getMaxIstanze()) {
    //throw new IllegalStateException("Limite massimo di istanze raggiunto per il tipo: "+ tipo.name());
    
    public ModuloSupportoAlieni(boolean supportaMarrone) {
        this.supportaMarrone = supportaMarrone;
        this.alieni = new ArrayList<>();
        istanze++;
    }

    // TODO: CANCELLARE, la funzione genera un nuovo tipoPedina, ma non l'aggiunge al Modulo. è possibile aggiungere la pedina solo nel costruttore
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

    // TODO: se cancalli la lista sopra non ha senso questo
    public List<TipoPedina> getAlieni() {
        return new ArrayList<>(alieni);
    }

    // deve chiamare l'equal del padre e deve controllare che l'alieno supportato sia lo stesso
    // se sistemi l'enum per controllare che il tipo di alieno supportato sia lo stesso basta fare:
    // this.al
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
    // TODO: manca il metodo clone() e il costruttore di copia (chiamato dal metodo clone)
    /*	@Override
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
	} */
    
    
}

