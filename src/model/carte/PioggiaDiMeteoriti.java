package model.carte;

import java.util.ArrayList;
import java.util.List;

import model.Giocatore;
import model.enums.Direzione;
import model.enums.TipoCarta;
import model.colpi.Meteorite;

public class PioggiaDiMeteoriti extends Carta {

	// Lista che contiene i meteoriti associati alla direzione da cui arrivano
	private final List<Meteorite> meteoriti;

	public PioggiaDiMeteoriti(List<Meteorite> meteoriti) {
		super(TipoCarta.PIOGGIA_DI_METEORITI);
		this.meteoriti = meteoriti;
	}

	@Override
	public void eseguiEvento(Giocatore[] giocatori) {
		// Stampo tutti i meteoriti
		super.io.stampa(super.carteRenderer.rappresentaMeteoriti(this));

		int indice = 0;
		for (Meteorite meteorite : meteoriti) {
			indice++;
			io.aCapo();
			io.stampa("Meteorite numero " + (indice) + ": " + meteorite.name() + " da "	+ meteorite.getDirezione().name());
			
			int posizioneColpo = super.lancia2Dadi(giocatori[0]);
			
			io.stampa("I giocatori in volo verranno colpiti alla coordinata " + (posizioneColpo + 2));

			for(Giocatore giocatore : giocatori) {
				
				// nave colpita
				int pezziDistrutti = giocatore.getNave().subisciImpatto(meteorite, posizioneColpo);
				// se la nave è cambiata stampare:
				if(pezziDistrutti != 0) {
					io.stampa("Nave di " + super.formattatoreGrafico.formattaGiocatore(giocatore) + " dopo essere stata colpita");
					io.stampa(super.naveRenderer.rappresentazioneNave(giocatore.getNave()));
					giocatore.incrementaPezziDistrutti(pezziDistrutti);
				}else {
					io.stampa("Nave di " + super.formattatoreGrafico.formattaGiocatore(giocatore) + " si è salvata");
				}
			}	
		}
		io.stampa("Fine della pioggia di meteoriti");
		io.aCapo();
	}

	public List<Meteorite> getMeteoriti() {
		return new ArrayList<>(meteoriti);
	}

	// Restituisce tutti i meteoriti che arrivano da una direzione
	public List<Meteorite> getMeteoritiByDirezione(Direzione direzione) {

		List<Meteorite> out = new ArrayList<>();

		for (Meteorite elemento : meteoriti) {
			if (elemento.getDirezione() == direzione)
				out.add(elemento);
		}

		return out;
	}
}
