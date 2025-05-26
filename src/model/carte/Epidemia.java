package model.carte;

import model.Giocatore;
import partita.fasiGioco.volo.ManagerDiVolo;

public class Epidemia extends Carta {

	public Epidemia() {
		super(TipoCarta.EPIDEMIA);
	}

	/**
	 * La carta epidemia elimina un membro dell'equipaggio da tutte le cabine
	 * adiacenti tra di loro.
	 */
	@Override
	public void eseguiEvento(ManagerDiVolo[] listaManager) {
		io.stampa(textAligner.alignCenter("Carta epidemia"));
		io.stampa("L'epidemia elimina un membro dell'equipaggio da tutte le cabine adiacenti tra di loro");

		for (ManagerDiVolo m : listaManager) {
			Giocatore g = m.getGiocatore();
			int membriPersi = g.getNave().eliminaEquipaggioDaCabineCollegate();

			io.stampa(formattatoreGrafico.formatta(g) + " ha perso " + membriPersi
					+ " membri totali dal proprio equipaggio");
		}
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	/**
	 * La carta epidemia non ha attributi particolari, quindi, a meno che la
	 * reference sia la stessa, 2 carte epidemia sono diverse
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		return false;
	}
}