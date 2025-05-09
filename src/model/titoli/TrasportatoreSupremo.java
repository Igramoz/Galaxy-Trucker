package model.titoli;

import java.util.List;

import model.Giocatore;
import model.componenti.Componente;
import model.componenti.Stiva;
import model.componenti.TipoComponente;
import model.nave.Nave;

public class TrasportatoreSupremo extends Titolo {

	public TrasportatoreSupremo() {
		super();
	}

	@Override
	public Giocatore valutaTitolo(List<Giocatore> giocatori) {
		Giocatore out = giocatori.get(0);
		for (Giocatore g : giocatori) {
			if (contaStiveNonVuote(g.getNave()) > contaStiveNonVuote(out.getNave()))
				out = g;
		}
		return out;
	}

	private int contaStiveNonVuote(Nave n) {
		int contatore = 0;
		List<Componente> stive = n.getCopiaComponenti(TipoComponente.STIVA);
		stive.addAll(n.getCopiaComponenti(TipoComponente.STIVA_SPECIALE));

		for (int i = 0; i < stive.size(); i++) {
			Stiva s = (Stiva) stive.get(i);
			if (!s.isEmpty())
				contatore++;
		}
		return contatore;
	}
}