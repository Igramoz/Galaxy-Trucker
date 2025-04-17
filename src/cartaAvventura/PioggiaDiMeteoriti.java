package cartaAvventura;

import java.util.List;

import util.Coppia;
import model.enums.Direzione;
import model.enums.TipiMeteorite;

public class PioggiaDiMeteoriti extends Carta {

	// Lista che contiene i meteoriti associati alla direzione verso cui colpiscono
	private final List<Coppia<TipiMeteorite, Direzione>> meteoriti;

	public PioggiaDiMeteoriti(List<Coppia<TipiMeteorite, Direzione>> meteoriti) {
		super(TipoCarta.PIOGGIA_DI_METEORITI);
		this.meteoriti = meteoriti;
	}
	
	@Override
	public void eseguiEvento() {
		
	}
	
}
