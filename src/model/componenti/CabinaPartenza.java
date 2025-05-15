package model.componenti;

import java.util.List;
import java.util.Map;

import eccezioni.CaricamentoNonConsentitoException;
import eccezioni.ComponenteNonIstanziabileException;
import eccezioni.ComponentePienoException;
import model.Giocatore;
import model.enums.*;
import util.Util;
import util.layout.Coordinate;
import util.layout.Direzione;
import grafica.Colore;

public class CabinaPartenza extends CabinaDiEquipaggio {

	private final static Coordinate CENTRO_NAVE = new Coordinate(Util.SIZE / 2, Util.SIZE / 2);// le cabine di partenza
																								// hanno sempre questa
																								// posizione
	private final Colore colore;

	private static final Map<Direzione, TipoTubo> tubiIniziali = Map.of(Direzione.SOPRA, TipoTubo.UNIVERSALE,
			Direzione.SOTTO, TipoTubo.UNIVERSALE, Direzione.DESTRA, TipoTubo.UNIVERSALE, Direzione.SINISTRA,
			TipoTubo.UNIVERSALE);

	public CabinaPartenza(Colore colore) {
		this(colore, null);
	}

	public CabinaPartenza(Colore colore, List<TipoPedina> equipaggioIniziale) {
		super(TipoComponente.CABINA_PARTENZA, tubiIniziali, equipaggioIniziale);

		if (!Giocatore.coloriDisponibiliGiocatori.contains(colore)) {
			throw new ComponenteNonIstanziabileException(
					"Colore non valido. Deve essere uno tra: " + Giocatore.coloriDisponibiliGiocatori);
		}

		this.colore = colore;
	}

	public CabinaPartenza(CabinaPartenza cabinaPartenza) { // costruttore di copia
		this(cabinaPartenza.colore, cabinaPartenza.getEquipaggio());
		this.setPosizione(cabinaPartenza.getPosizione());
	}

	@Override
	public void aggiungiEquipaggio(TipoPedina pedina) throws ComponentePienoException {

		// La cabina di partenza ospita solo astronauti
		if (pedina != TipoPedina.ASTRONAUTA) {
			throw new CaricamentoNonConsentitoException("La cabina di partenza può ospiatre solo astronauti");
		}
		super.aggiungiEquipaggio(pedina);
	}

	@Override
	public CabinaPartenza clone() {
		return new CabinaPartenza(this);
	}

	public Colore getColore() {
		return colore;
	}

	/**
	 * {@inheritDoc} 
	 * 
	 * @throws IllegalArgumentException se la posizione specificata non è il centro della nave	 * 
	 */
	@Override
	public void setPosizione(Coordinate posizione) {
		if (!CENTRO_NAVE.equals(posizione)) {
			throw new IllegalArgumentException("La posizione specificata non è accettabile: " + posizione
					+ " la cabina di partenza può stare solo al centro della nave");
		}
		super.setPosizione(posizione);
	}

}
