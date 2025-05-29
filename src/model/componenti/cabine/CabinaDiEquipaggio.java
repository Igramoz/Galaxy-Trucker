package model.componenti.cabine;

import model.componenti.Componente;
import model.componenti.Contenitore;
import model.componenti.TipoComponente;
import model.enums.TipoPedina;
import model.enums.TipoTubo;
import util.layout.Direzione;

import java.util.*;

import eccezioni.ComponenteNonIstanziabileException;
import eccezioni.ComponentePienoException;
import eccezioni.ComponenteVuotoException;

public class CabinaDiEquipaggio extends Componente implements Contenitore<TipoPedina> {

	private final List<TipoPedina> equipaggio = new ArrayList<>();

	public CabinaDiEquipaggio(Map<Direzione, TipoTubo> tubiIniziali) {
		this(TipoComponente.CABINA_EQUIPAGGIO, tubiIniziali, null);
	}

	protected CabinaDiEquipaggio(TipoComponente tipoComponente, Map<Direzione, TipoTubo> tubiIniziali,
			List<TipoPedina> equipaggioIniziale) {
		super(tipoComponente, tubiIniziali);
		if (equipaggioIniziale == null) {
			return;
		}

		for (TipoPedina pedina : equipaggioIniziale) {

			try {
				aggiungi(pedina);
			} catch (ComponentePienoException e) {
				throw new ComponenteNonIstanziabileException(
						"Impossibile creare la cabina: il numero di membri dell'equipaggio supera la capacità massima consentita.");
			}
		}
	}

	public CabinaDiEquipaggio(CabinaDiEquipaggio altra) {
		this(altra.tipo, altra.tubi, altra.equipaggio);
		this.setPosizione(altra.getPosizione());
	}

	public void aggiungi(TipoPedina pedina) throws ComponentePienoException {

		// Se c'è un alieno non si può inserire altro
		if (equipaggio.contains(TipoPedina.ALIENO_MARRONE) || equipaggio.contains(TipoPedina.ALIENO_VIOLA)) {
			throw new ComponentePienoException(
					"La cabina d'equipaggio è piena, non è possibile aggungere membri dell'equipaggio");

			// Se ci sono 2 astronauti non si può inserire altro
		} else if (Collections.frequency(equipaggio, TipoPedina.ASTRONAUTA) > 1) {
			throw new ComponentePienoException(
					"La cabina d'equipaggio è piena, non è possibile aggungere membri dell'equipaggio");
			// Se c'è un astronauta non si può aggiungere un alieno
		} else if (equipaggio.size() >= 1 && (pedina == TipoPedina.ALIENO_MARRONE || pedina == TipoPedina.ALIENO_VIOLA))
			throw new ComponentePienoException(
					"La cabina d'equipaggio è piena, non è possibile aggungere membri dell'equipaggio");

		equipaggio.add(pedina);
	}

	/**
	 * rimuove una perdina specifica dalla cabina
	 * 
	 * @param pedina
	 * @return vero se la pedina è stata rimossa
	 */
	public boolean rimuovi(TipoPedina pedina) {
		return equipaggio.removeIf(p -> p == pedina);
	}

	public boolean rimuoviUnMembro() throws ComponenteVuotoException {
		if (isEmpty()) {
			throw new ComponenteVuotoException(
					"La cabina d'equipaggio è vuota, non è possibile rimuovere membri dell'equipaggio");
		}

		TipoPedina pedina = equipaggio.get(0);
		if (pedina != null) {
			equipaggio.remove(pedina);
			return true;
		}
		return false;
	}

	public List<TipoPedina> getEquipaggio() {
		return new ArrayList<>(equipaggio);
	}

	@Override
	public CabinaDiEquipaggio clone() {
		return new CabinaDiEquipaggio(this);
	}

	public boolean isFull() {
		if (equipaggio.size() >= 2) {
			return true;
		}
		if (equipaggio.contains(TipoPedina.ALIENO_MARRONE) || equipaggio.contains(TipoPedina.ALIENO_VIOLA)) {
			return true;
		}
		return false;
	}

	public boolean isEmpty() {
		if (equipaggio == null || equipaggio.isEmpty())
			return true;
		else
			return false;
	}
}
