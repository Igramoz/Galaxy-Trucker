package model.carte;

import java.util.Objects;

import model.carte.criteriEffetti.Criterio;
import partita.fasiGioco.volo.ManagerDiVolo;
import servizi.ServizioDadi;
import util.layout.Coordinate;

public class Sabotaggio extends Carta {

	private ServizioDadi servizioDadi = new ServizioDadi();

	private final Criterio criterio = Criterio.EQUIPAGGIO;

	public Sabotaggio() {
		super(TipoCarta.SABOTAGGIO);
	}

	@Override
	public void eseguiEvento(ManagerDiVolo[] arrayManager) {
		io.stampa(textAligner.alignCenter("Carta: Sabotaggio"));
		io.stampa("La nave con il minor equipaggio verrà sabotata.");

		ManagerDiVolo giocatoreSabotato = criterio.trovaPeggiore(arrayManager);
		io.stampa("La nave con il minor equipaggio verrà sabotata.");
		io.stampa(formattatoreGrafico.formatta(giocatoreSabotato.getGiocatore()) + " è stato sabotato");
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
						"la nave è stata sabotata in posizione: " + formattatoreGrafico.formatta(coordinate));
				io.stampa("sono stati distrutti " + pezziDistrutti + " componenti");
				io.aCapo();
				io.stampa("nave dopo essere stata sabotata");
				io.stampa(naveRenderer.rappresentazioneNave(giocatoreSabotato.getGiocatore().getNave()));
				giocatoreSabotato.incrementaPezziDistrutti(pezziDistrutti);
				break;
			}
		}
		io.stampa("Carta sabotaggio risolta");

	}

	public Criterio getCriterio() {
		return criterio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(criterio);
		return result;
	}

	/**
	 * La carta sabotaggio non ha attributi particolari, quindi,
	 * a meno che la reference sia la stessa, 2 carte sabotaggio sono diverse
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		return false;
	}

}
