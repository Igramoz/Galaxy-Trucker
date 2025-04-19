package cartaAvventura;

import java.util.ArrayList;
import java.util.List;

import util.Coppia;
import model.Coordinate;
import model.Giocatore;
import model.enums.Direzione;
import model.colpi.TipiMeteorite;
import model.colpi.Colpo;

public class PioggiaDiMeteoriti extends Carta implements Colpo {

	// Lista che contiene i meteoriti associati alla direzione da cui arrivano
	private final List<Coppia<TipiMeteorite, Direzione>> meteoriti;

	public PioggiaDiMeteoriti(List<Coppia<TipiMeteorite, Direzione>> meteoriti) {
		super(TipoCarta.PIOGGIA_DI_METEORITI);
		this.meteoriti = meteoriti;
	}

	@Override
	public void eseguiEvento(Giocatore[] giocatori) {
		// Stampo tutti i meteoriti
		super.io.stampa(super.carteRenderer.rappresentaMeteoriti(this));

		int indice = 0;
		for (Coppia<TipiMeteorite, Direzione> meteorite : meteoriti) {
			indice++;
			io.stampa("Meteorite numero " + (indice) + ": " + meteorite.getElemento1().name() + " da "
					+ meteorite.getElemento2().name());
			
			int posizioneColpo = super.lancia2Dadi(giocatori[0]);
			
			io.stampa("I giocatori in volo verranno colpiti alla coordinata " + posizioneColpo);

			Coordinate coordColpite;
			for(Giocatore giocatore : giocatori) {
				coordColpite = calcolaCoordinateColpite(giocatore.getNave(), meteorite.getElemento2(), posizioneColpo);
				if(coordColpite == null) {
					// il colpo ha mancato la nave
					io.stampa(super.formattatoreGrafico.formattaGiocatore(giocatore) + " non è stato colpito");
				}else {
					// nave colpita
					int pezziDistrutti = 0;// TODO chiamare funzione per colpire la nave
					// se la nave è cambiata stampare:
					if(pezziDistrutti != 0) {
						io.stampa(textAligner.alignCenter("Nave di " + super.formattatoreGrafico.formattaGiocatore(giocatore) + " dopo essere stato colpito"));
						io.stampa(super.naveRenderer.rappresentazioneNave(giocatore.getNave()));
						// TODO aggiornare i pezzi distrutti
					}
					
				}
			}
			
		}

	}

	public List<Coppia<TipiMeteorite, Direzione>> getMeteoriti() {
		return new ArrayList<>(meteoriti);
	}

	// Restituisce tutti i meteoriti che arrivano da una direzione
	public List<TipiMeteorite> getMeteoritiByDirezione(Direzione direzione) {

		List<TipiMeteorite> out = new ArrayList<>();

		for (Coppia<TipiMeteorite, Direzione> elemento : meteoriti) {
			if (elemento.getElemento2() == direzione)
				out.add(elemento.getElemento1());
		}

		return out;
	}
	
	@Override
	public void aggiornaComponentiDistrutti() {
		// TODO istanziare
	}

}
