package model.titoli;

import java.util.ArrayList;
import java.util.List;

import model.Giocatore;
import model.componenti.CabinaDiEquipaggio;
import model.componenti.Componente;
import model.componenti.TipoComponente;
import model.enums.TipoPedina;
import model.nave.Nave;

public class Xenoquartiermastro extends Titolo {

	public Xenoquartiermastro() {
		super();
	}

	@Override
	public Giocatore valutaTitolo(List<Giocatore> giocatori) {
		Giocatore out = giocatori.get(0);
		for (Giocatore g : giocatori) {
			if (sommaDistanzeCabineAlieni(g.getNave()) > sommaDistanzeCabineAlieni(out.getNave()))
				out = g;
		}
		return out;
	}

	private int sommaDistanzeCabineAlieni(Nave n) {
		List<Componente> cabine = n.getCopiaComponenti(TipoComponente.CABINA_EQUIPAGGIO);
		List<CabinaDiEquipaggio> cabineConAlieni = new ArrayList<>();
		int numeroAlieni = 0;
		for (Componente comp : cabine) {
			CabinaDiEquipaggio c = (CabinaDiEquipaggio) comp;
			List<TipoPedina> equipaggio = c.getEquipaggio();
			for (TipoPedina pedina : equipaggio) {
				if (pedina == TipoPedina.ALIENO_MARRONE || pedina == TipoPedina.ALIENO_VIOLA) {
					cabineConAlieni.add(c);
					numeroAlieni++;
				}
			}
		}
		if (numeroAlieni == 0)
			return numeroAlieni;

		int sommaDistanze = 0;
		for (CabinaDiEquipaggio cabina : cabineConAlieni) {
			sommaDistanze += getDistanzaTraCabine(cabine, cabina);
		}
		
		return sommaDistanze;
	}

	private int getDistanzaTraCabine(List<Componente> listaCabine, CabinaDiEquipaggio cabina) {
		int minorDistanza = 100;
		for (Componente c : listaCabine) {
			int distanza = Math.abs(cabina.getPosizione().getX() - c.getPosizione().getX())
					+ Math.abs(cabina.getPosizione().getY() - c.getPosizione().getY());
			if (distanza < minorDistanza)
				minorDistanza = distanza;
		}
		return minorDistanza;
	}
}
