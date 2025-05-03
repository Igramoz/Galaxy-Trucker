package model.carte;

import partita.fasiGioco.volo.ManagerDiVolo;

public class Epidemia extends Carta {

	public Epidemia() {
		super(TipoCarta.EPIDEMIA);
	}

	@Override
	public void eseguiEvento(ManagerDiVolo[] listaManager) {
		for (ManagerDiVolo m : listaManager) {
			m.getGiocatore().getNave().eliminaEquipaggioDaCabineCollegate();
		}
	}
}