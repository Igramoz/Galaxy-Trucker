package model.titoli;

import java.util.List;
import java.util.Map;

import model.Giocatore;
import model.componenti.CabinaDiEquipaggio;
import model.componenti.Componente;
import model.componenti.TipoComponente;
import model.enums.TipoTubo;
import model.nave.Nave;
import util.layout.Direzione;

public class CapitanoDaCrociera extends Titolo {

	public CapitanoDaCrociera() {
		super();
	}

	/**
	 * La funzione stabilisce qual è il giocatore con il maggior numero di cabine
	 * con vista. Una cabina con vista è una cabina con un lato liscio (cioè senza
	 * tubo) e che non abbia nessun componente nella casella adiacente al lato
	 * liscio della cabina.
	 * 
	 * @param giocatori: lista dei giocatori valutabili
	 * @return out: il giocatore secondo il criterio già citato
	 */
	@Override
	public Giocatore valutaTitolo(List<Giocatore> giocatori) {
		Giocatore out = giocatori.get(0);
		for (Giocatore g : giocatori) {
			if (contaCabineConVista(g.getNave()) > contaCabineConVista(out.getNave()))
				out = g;
		}
		return out;
	}

	private int contaCabineConVista(Nave n) {
		int contatore = 0;
		List<Componente> cabine = n.getCopiaComponenti(TipoComponente.CABINA_EQUIPAGGIO);

		for (int i = 0; i < cabine.size(); i++) {
			CabinaDiEquipaggio cabina = (CabinaDiEquipaggio) cabine.get(i);
			Map<Direzione, Componente> adiacenti = n.getCopiaComponentiAdiacenti(cabina.getPosizione());

			// per ogni direzione controllo se la cabina non ha nessun tubo, e se il
			// componente adiacente è null
			for (Direzione dir : Direzione.values()) {
				if (adiacenti.get(dir) == null && cabina.getTubi().get(dir) == TipoTubo.NESSUNO) {
					contatore++;
					break;
				}
			}
		}
		return contatore;
	}
}