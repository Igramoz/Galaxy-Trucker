package model.titoli;

import java.util.List;

import model.Giocatore;
import model.componenti.Componente;
import model.componenti.TipoComponente;
import model.nave.Nave;

public class Batterista extends Titolo {

	public Batterista() {
		super();
	}

	@Override
	public Giocatore valutaTitolo(List<Giocatore> giocatori) {
		Giocatore out = giocatori.get(0);
		for (Giocatore g : giocatori) {
			if (contaComponentiEnergetici(g.getNave()) > contaComponentiEnergetici(out.getNave()))
				out = g;
		}
		return out;
	}

	private int contaComponentiEnergetici(Nave n) {
		List<Componente> componentiEnergetici = n.getCopiaComponenti(TipoComponente.CANNONE_DOPPIO);
		componentiEnergetici.addAll(n.getCopiaComponenti(TipoComponente.MOTORE_DOPPIO));
		componentiEnergetici.addAll(n.getCopiaComponenti(TipoComponente.SCUDO));
		return componentiEnergetici.size();
	}
}