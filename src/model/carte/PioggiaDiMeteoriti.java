package model.carte;

import java.util.ArrayList;
import java.util.List;

import model.Giocatore;
import model.carte.colpo.Colpo;
import model.carte.criteriEffetti.CriterioConEffetto;
import model.enums.Direzione;
import model.Dado;

public class PioggiaDiMeteoriti extends Carta {

	// Lista che contiene i meteoriti associati alla direzione da cui arrivano
	private final List<Colpo> meteoriti;

	@SuppressWarnings("unchecked")
	public PioggiaDiMeteoriti(CriterioConEffetto effeetto) {
		super(TipoCarta.PIOGGIA_DI_METEORITI);
		this.meteoriti = ((List<Colpo>)effeetto.getValore());
	}

	@Override
	public void eseguiEvento(Giocatore[] giocatori) {
		// Stampo tutti i meteoriti
		io.stampa(super.textAligner.alignCenter("Pioggia di meteoriti"));
		super.io.stampa(super.carteRenderer.rappresentaColpi(meteoriti));
		
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
	
	public List<Colpo> getMeteoritiPerDirezione(Direzione direzione) {
		return Colpo.getColpiByDirezione(meteoriti, direzione);
	}
	
}
