package model.carte;

import java.util.ArrayList;
import java.util.List;

import model.Giocatore;
import model.enums.Direzione;
import model.Colpo;
import model.Dado;

public class PioggiaDiMeteoriti extends Carta {

	// Lista che contiene i meteoriti associati alla direzione da cui arrivano
	private final List<Colpo> meteoriti;

	public PioggiaDiMeteoriti(List<Colpo> meteoriti) {
		super(TipoCarta.PIOGGIA_DI_METEORITI);
		this.meteoriti = meteoriti;
	}

	@Override
	public void eseguiEvento(Giocatore[] giocatori) {
		// Stampo tutti i meteoriti
		super.io.stampa(super.carteRenderer.rappresentaMeteoriti(this));

		int indice = 0;
		for (Colpo colpo : meteoriti) {
			indice++;
			io.aCapo();
			io.stampa("colpo numero " + (indice) + ": " + colpo.getTipoColpo().name() + " da "	+ colpo.getDirezione().name());
			
			int posizioneColpo = Dado.lancia2Dadi(giocatori[0]);
			
			io.stampa("I giocatori in volo verranno colpiti alla coordinata " + (posizioneColpo + 2));

			for(Giocatore giocatore : giocatori) {
				
				// nave colpita
				int pezziDistrutti = giocatore.getNave().subisciImpatto(colpo, posizioneColpo);
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
		io.aCapo();
		io.stampa("Fine della pioggia di meteoriti");
		io.aCapo();
	}

	public List<Colpo> getMeteoriti() {
		return new ArrayList<>(meteoriti);
	}

	// Restituisce tutti i meteoriti che arrivano da una direzione
	public List<Colpo> getMeteoritiByDirezione(Direzione direzione) {

		List<Colpo> out = new ArrayList<>();

		for (Colpo elemento : meteoriti) {
			if (elemento.getDirezione() == direzione)
				out.add(elemento);
		}
		return out;
	}
}
