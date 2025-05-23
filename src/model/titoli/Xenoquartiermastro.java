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

	/**
	 * La funzione stabilisce qual è il giocatore che rispetta il seguente criterio:
	 * per ogni alieno sulla tua nave, somma la sua distanza dalla cabina più
	 * vicina.
	 * 
	 * @param giocatori: lista dei giocatori valutabili
	 * @return out: il giocatore la cui somma delle distanze, secondo il criterio,
	 *         risulta maggiore.
	 */
	@Override
	public Giocatore valutaTitolo(List<Giocatore> giocatori) {
		Giocatore out = giocatori.get(0);
		for (Giocatore g : giocatori) {
			if (sommaDistanzeCabineAlieni(g.getNave()) > sommaDistanzeCabineAlieni(out.getNave()))
				out = g;
		}
		return out;
	}

	/**
	 * Questa funzione somma le distanze tra ciascun alieno, e cabina più vicina.
	 * 
	 * @param n: nave del giocatore
	 * @return sommaDistanze, oppure 0 se non ci sono alieni.
	 */
	private int sommaDistanzeCabineAlieni(Nave n) {
		List<Componente> cabine = n.getCopiaComponenti(TipoComponente.CABINA_EQUIPAGGIO);
		List<CabinaDiEquipaggio> cabineConAlieni = new ArrayList<>();
		for (Componente comp : cabine) {
			CabinaDiEquipaggio c = (CabinaDiEquipaggio) comp;
			List<TipoPedina> equipaggio = c.getEquipaggio();
			for (TipoPedina pedina : equipaggio) {
				if (pedina == TipoPedina.ALIENO_MARRONE || pedina == TipoPedina.ALIENO_VIOLA) {
					cabineConAlieni.add(c);
				}
			}
		}
		if (cabineConAlieni.size() == 0)
			return 0;

		int sommaDistanze = 0;
		for (CabinaDiEquipaggio cabina : cabineConAlieni) {
			sommaDistanze += getDistanzaTraCabine(cabine, cabina);
		}

		return sommaDistanze;
	}

	/**
	 * Trova la distanza tra l'alieno e la cabina più vicina
	 * 
	 * @param listaCabine: cabine di equipaggio totali della nave.
	 * @param cabina:      quella dove si trova l'alieno.
	 * @return minorDistanza
	 */
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
