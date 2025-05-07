package model.carte;

import model.Giocatore;
import partita.fasiGioco.volo.ManagerDiVolo;


public class Epidemia extends Carta {

	public Epidemia() {
		super(TipoCarta.EPIDEMIA);
	}

	@Override
	public void eseguiEvento(ManagerDiVolo[] listaManager) {
		io.stampa(textAligner.alignCenter("Carta epidemia"));
		io.stampa("L'epidemia elimina un membro dell'equipaggio da tutte le cabine adiacenti tra di loro");

		for (ManagerDiVolo m : listaManager) {
			
			Giocatore g = m.getGiocatore();
			int membriPersi = g.getNave().eliminaEquipaggioDaCabineCollegate();
			
			io.stampa(formattatoreGrafico.formatta(g) + " ha perso " + membriPersi + " membri totali dal proprio equipaggio");
		}
	}
}