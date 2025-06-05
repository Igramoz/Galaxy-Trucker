package model.carte.colpo;

import java.util.List;

import controller.partita.fasiGioco.volo.ManagerDiVolo;
import controller.servizi.ServizioDadi;
import model.Giocatore;
import view.formattatori.FormattatoreGrafico;
import view.io.GestoreIO;
import view.renderer.CarteRenderer;
import view.renderer.NaveRenderer;

public class GestoreColpi {
	GestoreIO io = new GestoreIO();
	CarteRenderer carteRenderer = new CarteRenderer();
	FormattatoreGrafico formattatoreGrafico = new FormattatoreGrafico();
	NaveRenderer naveRenderer = new NaveRenderer();
	ServizioDadi servizioDadi = new ServizioDadi();
	
	public void gestioneColpi(ManagerDiVolo[] listaManager, List<Colpo> colpi) {
		carteRenderer.rappresentaColpi(colpi);

		int indice = 0;
		for (Colpo colpo : colpi) {
			indice++;
			io.aCapo();
			io.stampa("colpo numero " + (indice) + ": " + colpo.getTipoColpo().name() + " da "
					+ colpo.getDirezione().name());

			int posizioneColpo = servizioDadi.lancia2Dadi(listaManager[0].getGiocatore());

			io.stampa("I giocatori in volo verranno colpiti alla coordinata " + (posizioneColpo + 2));

			for (ManagerDiVolo m : listaManager) {
				Giocatore giocatore = m.getGiocatore();

				io.aCapo();
				io.stampa("stiamo valutando se la nave di " + formattatoreGrafico.formatta(giocatore) + " è stata colpita...");
				// nave colpita
				int pezziDistrutti = giocatore.getNave().subisciImpatto(colpo, posizioneColpo);
				// se la nave è cambiata stampare:
				if (pezziDistrutti != 0) {
					io.stampa("Nave di " + formattatoreGrafico.formatta(giocatore)
							+ " dopo essere stata colpita");
					io.stampa(naveRenderer.rappresentazioneNave(giocatore.getNave()));
					m.incrementaPezziDistrutti(pezziDistrutti);
				} else {
					io.stampa("Nave di " + formattatoreGrafico.formatta(giocatore) + " si è salvata");
				}
			}
		}
	}
	public void gestioneColpi(List<ManagerDiVolo> listaManager, List<Colpo> colpi) {
	    ManagerDiVolo[] array = listaManager.toArray(new ManagerDiVolo[listaManager.size()]);
		gestioneColpi(array, colpi);
	}
}
