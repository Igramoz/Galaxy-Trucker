package model.titoli;

import java.util.ArrayList;
import java.util.List;

import model.Giocatore;
import model.componenti.CabinaDiEquipaggio;
import model.componenti.Componente;
import model.componenti.TipoComponente;
import model.enums.TipoPedina;
import model.nave.Nave;
import util.layout.Coordinate;

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
		Componente[][] griglia = n.getGrigliaComponentiCloni();
		for (CabinaDiEquipaggio cabina : cabineConAlieni) {
			int distanza = 0;
			// devo poi controllare se i tubi delle due cabine corrispondono
//			int temp = getDistanzaEffettivaTraCabine(griglia, cabina);
			// TODO: finire
			sommaDistanze += distanza;
		}

		return sommaDistanze;
	}

//	private int getDistanzaEffettivaTraCabine(Componente[][] griglia, CabinaDiEquipaggio cabina) {
//		int distanza = 0;
//
//		return distanza;
//	}
}
