package model.carte;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import partita.fasiGioco.volo.ManagerDiVolo;
import util.layout.Coordinate;

public class Epidemia extends Carta {

	public Epidemia() {
		super(TipoCarta.EPIDEMIA);
	}

	@Override
	public void eseguiEvento(ManagerDiVolo[] listaManager) {
		Map<ManagerDiVolo[], Integer> equipaggioPersoPerManager = new HashMap<>();
		for (ManagerDiVolo m : listaManager) {
			List<Coordinate> coordinateGiaEsaminate = new ArrayList<>();
			int membriPersi = m.getGiocatore().getNave().eliminaEquipaggioDaCabineCollegate(coordinateGiaEsaminate);

			equipaggioPersoPerManager.put(listaManager, membriPersi);
		}
		carteRenderer.rappresentaCarta(equipaggioPersoPerManager);
	}
}