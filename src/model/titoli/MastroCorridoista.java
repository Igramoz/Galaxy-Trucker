package model.titoli;

import java.util.List;
import model.Giocatore;
import model.nave.Nave;
import model.componenti.Componente;

public class MastroCorridoista extends Titolo {

	@Override
	public Giocatore valutaTitolo(List<Giocatore> giocatori) {

	}

	private int LunghezzaCorridoio(Nave n) {

		Componente[][] c = n.getGrigliaComponentiCloni();
		// per ogni componente della nave verifico se è un corridoio e se lo è calcolo
		// la lunghezza del corridoio(basta vedere se ha uno o due tubi)
		int lunghezza = 0;

	}
}