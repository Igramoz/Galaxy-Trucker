package model.carte;

import java.util.List;

import model.Giocatore;
import model.componenti.CabinaDiEquipaggio;
import model.componenti.Componente;
import model.componenti.TipoComponente;
import model.nave.AnalizzatoreNave;
import model.nave.Nave;
import partita.fasiGioco.ManagerDiVolo;

public class Epidemia extends Carta {

	public Epidemia(TipoCarta tipoCarta) {
		super(tipoCarta);
	}

	@Override
	public void eseguiEvento(ManagerDiVolo[] listaManager) {
		// TODO: rappresenta carta in CarteRenderer??
		for (ManagerDiVolo m : listaManager) {
			Giocatore g = m.getGiocatore();
			Nave n = g.getNave();

			List<Componente> cabine = n.getCopiaComponenti(TipoComponente.CABINA_EQUIPAGGIO);
			Componente c = n.getCopiaComponenti(TipoComponente.CABINA_PARTENZA).get(0);
			cabine.add(c);
			// TODO: proseguire
			AnalizzatoreNave analizzatore = new AnalizzatoreNave(n);
			for (int i = 0; i < cabine.size(); i++) {
//				List<Componente> cabineAdiacenti = analizzatore.ottieniCabineEquipaggioCollegate(n, null);
			}
		}
	}
}