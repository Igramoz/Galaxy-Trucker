package model.carte;

import java.util.ArrayList;
import java.util.List;

import model.Giocatore;
import partita.fasiGioco.volo.ManagerDiVolo;
import util.layout.Coordinate;

public class Epidemia extends Carta {

	public Epidemia() {
		super(TipoCarta.EPIDEMIA);
	}

	@Override
	public void eseguiEvento(ManagerDiVolo[] listaManager) {
		carteRenderer.rappresentaCarta();
		for (ManagerDiVolo m : listaManager) {
			List<Coordinate> coordinateGiaEsaminate = new ArrayList<>();
			Giocatore g = m.getGiocatore();
			int membriPersi = g.getNave().eliminaEquipaggioDaCabineCollegate(coordinateGiaEsaminate);

			carteRenderer.stampaEquipaggioPersoPerGiocatore(g, membriPersi);
		}
	}
}