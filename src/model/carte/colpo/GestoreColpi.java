package model.carte.colpo;

import java.util.List;

import grafica.FormattatoreGrafico;
import grafica.renderer.CarteRenderer;
import grafica.renderer.NaveRenderer;
import io.GestoreIO;
import model.Giocatore;
import partita.fasiGioco.ManagerDiVolo;
import servizi.ServizioDadi;

public interface GestoreColpi {
	public default void gestioneColpi(ManagerDiVolo[] listaManager, List<Colpo> colpi) {
		GestoreIO io = new GestoreIO();
		CarteRenderer carteRenderer = new CarteRenderer();
		FormattatoreGrafico formattatoreGrafico = new FormattatoreGrafico();
		NaveRenderer naveRenderer = new NaveRenderer();
		ServizioDadi servizioDadi = new ServizioDadi();

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

				// nave colpita
				int pezziDistrutti = giocatore.getNave().subisciImpatto(colpo, posizioneColpo);
				// se la nave è cambiata stampare:
				if (pezziDistrutti != 0) {
					io.stampa("Nave di " + formattatoreGrafico.formattaGiocatore(giocatore)
							+ " dopo essere stata colpita");
					io.stampa(naveRenderer.rappresentazioneNave(giocatore.getNave()));
					giocatore.incrementaPezziDistrutti(pezziDistrutti);
				} else {
					io.stampa("Nave di " + formattatoreGrafico.formattaGiocatore(giocatore) + " si è salvata");
				}
			}
		}
	}
	public default void gestioneColpi(List<ManagerDiVolo> listaManager, List<Colpo> colpi) {
	    ManagerDiVolo[] array = listaManager.toArray(new ManagerDiVolo[listaManager.size()]);
		gestioneColpi(array, colpi);
	}
}
