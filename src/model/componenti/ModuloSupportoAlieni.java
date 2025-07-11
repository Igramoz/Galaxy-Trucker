package model.componenti;

import java.util.Map;

import eccezioni.ComponenteNonIstanziabileException;
import model.enums.TipoPedina;
import model.enums.TipoTubo;
import util.layout.Direzione;
import view.Colore;

public class ModuloSupportoAlieni extends Componente {

	private final TipoPedina alienoSupportato;
	private final Colore colore; 

	public ModuloSupportoAlieni(Map<Direzione, TipoTubo> tubiIniziali, TipoComponente tipoSovrastruttura) {
		super(tipoSovrastruttura, tubiIniziali);
		alienoSupportato = gestisciAlieno(tipoSovrastruttura);
		colore = gestisciColore(tipoSovrastruttura);
	}

	private TipoPedina gestisciAlieno(TipoComponente tipoSovrastruttura) {
		switch (tipoSovrastruttura) {
		case SOVRASTRUTTURA_ALIENA_MARRONE:
			return TipoPedina.ALIENO_MARRONE;
		case SOVRASTRUTTURA_ALIENA_VIOLA:
			return TipoPedina.ALIENO_VIOLA;
		default:
			throw new ComponenteNonIstanziabileException("Il tipo di questo modulo di supporto alieni non è permesso.");
		}
	}
	
	private Colore gestisciColore(TipoComponente tipoSovrastruttura) {
		switch (tipoSovrastruttura) {
		case SOVRASTRUTTURA_ALIENA_MARRONE:
			return Colore.MARRONE;
		case SOVRASTRUTTURA_ALIENA_VIOLA:
			return Colore.VIOLA;
		default:
			throw new IllegalArgumentException("Tipo di componente non permesso");
		}
	}

	// costruttore di copia
	public ModuloSupportoAlieni(ModuloSupportoAlieni msa) {
		this(msa.tubi, msa.tipo);
		this.setPosizione(msa.getPosizione());
	}

	public TipoPedina getAlienoSupportato() {
		return alienoSupportato;
	}
	
	public Colore getColore() {
		return this.colore;
	}

	@Override
	public Componente clone() {
		return new ModuloSupportoAlieni(this);
	}
}