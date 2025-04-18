package cartaAvventura;

import java.util.ArrayList;
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
		
		// Stampo tutti i meteoriti
		super.io.stampa(super.carteRenderer.rappresentaMeteoriti(this));
		
		for(int i = 0; i < meteoriti.size(); i++) {
			
			
		}
		
		
	}
	
	public List<Coppia<TipiMeteorite, Direzione>> getMeteoriti(){
	    return new ArrayList<>(meteoriti);
	}
	
	// Restituisce tutti i meteoriti che arrivano da una direzione
	public List<TipiMeteorite> getMeteoritiByDirezione(Direzione direzione){
		
		List<TipiMeteorite> out = new ArrayList<>();
		
		for(Coppia<TipiMeteorite, Direzione> elemento : meteoriti ) {
			out.add(elemento.getElemento1());			
		}
		
		return out;
	}
	
}
