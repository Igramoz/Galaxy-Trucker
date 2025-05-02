package model.carte;

import grafica.FormattatoreGrafico;
import grafica.TextAligner;
import grafica.renderer.NaveRenderer;
import io.GestoreIO;
import model.carte.criteriEffetti.Criterio;
import partita.fasiGioco.ManagerDiVolo;
import servizi.ServizioDadi;
import util.layout.Coordinate;

public class Sabotaggio extends Carta {

	private GestoreIO io = new GestoreIO();
	private TextAligner textAligner = new TextAligner();
	private FormattatoreGrafico formattatoreGrafico = new FormattatoreGrafico();
	private ServizioDadi servizioDadi = new ServizioDadi();
	private NaveRenderer naveRenderer = new NaveRenderer();

	private final Criterio criterio = Criterio.EQUIPAGGIO;

	public Sabotaggio() {
		super(TipoCarta.SABOTAGGIO);
	}

	@Override
	public void eseguiEvento(ManagerDiVolo[] listaManager) {
		io.stampa(textAligner.alignCenter("Carta: Sabotaggio"));
		io.stampa("La nave con il minor equipaggio verrà sabotata.");

		ManagerDiVolo giocatoreSabotato = criterio.trovaPeggiore(listaManager);
		io.stampa("La nave con il minor equipaggio verrà sabotata.");
		io.stampa(formattatoreGrafico.formattaGiocatore(giocatoreSabotato.getGiocatore()) + " è stato sabotato");
		io.stampa("hai a disposizione 3 lanci per determinare dove la nave verrà sabotata");

		for (int i = 0; i < 3; i++) {
			io.stampa("Lancio numero " + (i + 1));
			int x = servizioDadi.lancia2Dadi(giocatoreSabotato.getGiocatore());
			int y = servizioDadi.lancia2Dadi(giocatoreSabotato.getGiocatore());
			Coordinate coordinate = new Coordinate(x, y);

			int pezziDistrutti = giocatoreSabotato.getGiocatore().getNave().distruggiComponentiConnessi(coordinate);

			if (pezziDistrutti == 0) {
				io.aCapo();
				io.stampa("non è stato distrutto nessun componente");
				if (i != 2) {
					io.stampa("rilanciare i dadi");
				} else {
					io.stampa("I dadi sono stati lanciati 3 volte, nessun componente verrà sabotato");
				}
			} else {
				io.aCapo();
				io.stampa(
						"la nave è stata sabotata in posizione: " + formattatoreGrafico.formattaCoordinate(coordinate));
				io.stampa("sono stati distrutti " + pezziDistrutti + " componenti");
				io.aCapo();
				io.stampa("nave dopo essere stata sabotata");
				io.stampa(naveRenderer.rappresentazioneNave(giocatoreSabotato.getGiocatore().getNave()));
				giocatoreSabotato.getGiocatore().incrementaPezziDistrutti(pezziDistrutti);
				break;
			}
		}
		io.stampa("Carta sabotaggio risolta");

	}

	public Criterio getCriterio() {
		return criterio;
	}

}
