package componenti;

import java.util.Map;

import model.enums.Direzione;
import model.enums.TipoTubo;
import model.equipaggio.TipoPedina;

public class ModuloSupportoAlieni extends Componente {

	private final TipoPedina alienoSupportato;

	// TODO: vedere se lasciare il tipocomponente tra i parametri, in caso
	// aggiungiuamo il colore più avanti
	public ModuloSupportoAlieni(TipoComponente tipoSovrastruttura, Map<Direzione, TipoTubo> tubiIniziali) {
		super(tipoSovrastruttura, tubiIniziali);
		alienoSupportato = gestisciAlieno(tipoSovrastruttura);
	}

	private TipoPedina gestisciAlieno(TipoComponente tipoSovrastruttura) {
		switch (tipoSovrastruttura) {
		case SOVRASTRUTTURA_ALIENA_MARRONE:
			return TipoPedina.ALIENO_MARRONE;
		case SOVRASTRUTTURA_ALIENA_VIOLA:
			return TipoPedina.ALIENO_VIOLA;
		default:
			throw new IllegalArgumentException("Tipo di componente non permesso");
		}
	}

	// costruttore di copia
	public ModuloSupportoAlieni(ModuloSupportoAlieni msa) {
		this(msa.tipo, msa.tubi);
	}

	public TipoPedina getAlienoSupportato() {
		return alienoSupportato;
	}

	@Override
	public Componente clone() {
		return new ModuloSupportoAlieni(this);
	}
}