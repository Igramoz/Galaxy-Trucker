package model.nave;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import grafica.formattatori.FormattatoreGrafico;
import io.GestoreIO;
import model.componenti.CabinaDiEquipaggio;
import model.componenti.Cannone;
import model.componenti.Componente;
import model.componenti.Stiva;
import model.componenti.TipoComponente;
import model.componenti.VanoBatteria;
import model.enums.TipoMerce;
import model.enums.TipoPedina;
import model.enums.TipoTubo;
import util.Util;
import util.layout.Coordinate;
import util.layout.Direzione;

/**
 * Classe per calcolare le statisciche della nave (connettori esposti)
 */
public class AnalizzatoreNave {
	private Nave nave;

	private GestoreIO io = new GestoreIO();
	private FormattatoreGrafico formattatoreGrafico = new FormattatoreGrafico();

	public AnalizzatoreNave(Nave nave) {
		if (nave == null) {
			throw new NullPointerException("La nave non può essere null");
		}

		this.nave = nave;
	}

	/**
	 * Sceglie un componente della nave in base ai tipi specificati e restituisce la
	 * posizione di un componente.
	 * 
	 * @param tipoComponente1 un tipo di componente da selezionare.
	 * @param tipoComponente2 (Opzionale) Il secondo tipo di componente da
	 *                        considerare. Se null, viene considerato solo il primo
	 *                        tipo di componente.
	 * @return La posizione del componente scelto come {@link Coordinate}, oppure
	 *         <strong> null </strong> se non ci sono componenti disponibili per i
	 *         tipi specificati.
	 */
	public Coordinate scegliComponente(TipoComponente tipoComponente1, TipoComponente tipoComponente2) {
		List<Componente> componenti = nave.getCopiaComponenti(tipoComponente1);
		if (tipoComponente2 != null) {
			componenti.addAll(nave.getCopiaComponenti(tipoComponente2));
		}

		if (componenti.isEmpty())
			return null;

		return io.menuComponenti(componenti).getPosizione();
	}

	public Coordinate scegliComponente(TipoComponente tipoComponente1) {
		return scegliComponente(tipoComponente1, null);
	}

	public Map<Direzione, Componente> getCopiaComponentiAdiacenti(Coordinate coord) {
		if (coord == null)
			throw new NullPointerException("Non è possibile trovare i componenti adiacenti ad una posizione nulla");

		Map<Direzione, Componente> adiacenti = new EnumMap<>(Direzione.class);
		Componente[][] grigliaComponenti = nave.getGrigliaComponentiCloni();

		// Coord del componente
		int x = coord.getX();
		int y = coord.getY();

		adiacenti.put(Direzione.SOPRA, grigliaComponenti[x][y - 1]);
		adiacenti.put(Direzione.SINISTRA, grigliaComponenti[x - 1][y]);
		adiacenti.put(Direzione.SOTTO, grigliaComponenti[x][y + 1]);
		adiacenti.put(Direzione.DESTRA, grigliaComponenti[x + 1][y]);

		return adiacenti;
	}

	public List<TipoMerce> trovaMerciNave() {
		List<Componente> stive = nave.getCopiaComponenti(TipoComponente.STIVA);
		stive.addAll(nave.getCopiaComponenti(TipoComponente.STIVA_SPECIALE));
		List<TipoMerce> out = new ArrayList<>();

		for (Componente stiva : stive) {
			TipoMerce[] merci = ((Stiva) stiva).getMerci();

			if (merci != null) {
				for (TipoMerce merce : merci) {
					if (merce != null) {
						out.add(merce);
					}
				}
			}
		}
		return out;
	}

	public List<TipoPedina> trovaEquipaggioNave() {
		List<Componente> cabine = nave.getCopiaComponenti(TipoComponente.CABINA_EQUIPAGGIO);
		cabine.addAll(nave.getCopiaComponenti(TipoComponente.CABINA_PARTENZA));
		List<TipoPedina> out = new ArrayList<>();
		for (Componente cabina : cabine) {
			// Controllo che l'equipaggio non sia null prima di settarlo
			List<TipoPedina> equipaggio = ((CabinaDiEquipaggio) cabina).getEquipaggio();
			if (equipaggio != null) {
				out.addAll(equipaggio);
			}
		}
		return out;
	}

	public int connettoriEspostiConuter() {

		int counter = 0;
		Componente[][] griglia = nave.getGrigliaComponentiCloni();
		for (int x = 0; x < Util.SIZE; x++) {
			for (int y = 0; y < Util.SIZE; y++) {
				if (griglia[x][y] != null) {
					counter += connettoriEspostiComponente(griglia[x][y]);
				}
			}
		}
		return counter;
	}

	/**
	 * funzione per contare i connettori esposti di un componente
	 * 
	 * @param componete al centro
	 * @return numero componenti esposti
	 */
	private int connettoriEspostiComponente(Componente componente) {

		Map<Direzione, Componente> adiacenti = getCopiaComponentiAdiacenti(componente.getPosizione());
		int counter = 0;

		for (Map.Entry<Direzione, Componente> entry : adiacenti.entrySet()) {
			Direzione direzione = entry.getKey();
			Componente adiacente = entry.getValue();

			// controlla la presenza di un componente
			if (adiacente == null) {
				// Se il componente ha un tubo in quella direzione è esposto
				if (componente.getTubo(direzione) != TipoTubo.NESSUNO) {
					counter++;
				}
			}
		}
		return counter;
	}

	public float potenzaFuocoCounter() {
		float potenzaFuoco = 0;

		// conto i cannoni singoli
		List<Componente> cannoni = nave.getCopiaComponenti(TipoComponente.CANNONE_SINGOLO);
		for (Componente cannone : cannoni) {
			potenzaFuoco += ((Cannone) cannone).getPotenzaFuoco();
		}

		// conto i cannoni doppi
		cannoni = nave.getCopiaComponenti(TipoComponente.CANNONE_DOPPIO);
		for (Componente cannone : cannoni) {
			io.stampa("scrivere 1 se si vuole usare il cannone in posizione: "
					+ formattatoreGrafico.formatta(cannone.getPosizione()));
			if (io.leggiIntero() == 1) {
				if (nave.getGestoreComponenti().consumaEnergia()) { // uso il cannone doppio
					potenzaFuoco += ((Cannone) cannone).getPotenzaFuoco();
				} else {
					break; // energia finita
				}
			}
		}
//		se la potenza di fuoco senza l’alieno è 0, l'alieno non si attiva.
//		Non affronterà una battaglia spaziale a tentacoli nudi
		if (potenzaFuoco == 0)
			return potenzaFuoco;
		// conto gli alieni viola
		List<TipoPedina> equipaggio = nave.getEquipaggio();
		for (TipoPedina pedina : equipaggio) {
			if (pedina == TipoPedina.ALIENO_VIOLA) {
				potenzaFuoco++;
			}
		}
		return potenzaFuoco;
	}

	public int potenzaMotriceCounter() {
		int potenzaMotrice = 0;

		// contro i motori singoli
		List<Componente> motori = nave.getCopiaComponenti(TipoComponente.MOTORE_SINGOLO);
		potenzaMotrice += motori.size();

		// conto i motori doppi
		motori = nave.getCopiaComponenti(TipoComponente.MOTORE_DOPPIO);
		for (Componente motore : motori) {
			io.stampa("scrivere 1 se si vuole usare il motore in posizione: "
					+ formattatoreGrafico.formatta(motore.getPosizione()));
			if (io.leggiIntero() == 1) {
				if (nave.getGestoreComponenti().consumaEnergia()) { // uso il motore doppio
					potenzaMotrice += 2;
				} else {
					break; // energia finita
				}
			}
		}

		// potenzaMotrice != 0 affinché l'alieno si attivi
		if (potenzaMotrice == 0) {
			return potenzaMotrice;
		}

		// conto gli alieni marroni
		List<TipoPedina> equipaggio = nave.getEquipaggio();
		for (TipoPedina pedina : equipaggio) {
			if (pedina == TipoPedina.ALIENO_MARRONE) {
				potenzaMotrice++;
			}
		}
		return potenzaMotrice;

	}

	public int energiaCounter() {
		List<Componente> batterie = nave.getCopiaComponenti(TipoComponente.VANO_BATTERIA);
		int counter = 0;
		for (Componente batteria : batterie) {
			counter += ((VanoBatteria) batteria).getBatterie();
		}
		return counter;
	}

	/**
	 * funzione che dato un componente restitusce una lista con tutte le cabine di
	 * equipaggio collegate (compresa quella di partenza).
	 * 
	 * @param componente: è il componente da cui cerco i componenti adiacenti
	 * @return cabinaCollegate: lista delle cabine collegate
	 */
	public List<Componente> ottieniCabineEquipaggioCollegate(Componente componente) {
		List<Componente> cabineCollegate = new ArrayList<>();

		Map<Direzione, Componente> componentiAdiacenti = nave.getCopiaComponentiAdiacenti(componente.getPosizione());

		// rimuovo i componenti non collegati da tubi
		Map<Direzione, TipoTubo> tubiSovrastruttura = componente.getTubi();

		for (Map.Entry<Direzione, TipoTubo> entry : tubiSovrastruttura.entrySet()) {
			if (entry.getValue() != TipoTubo.NESSUNO) {

				Componente adiacente = componentiAdiacenti.get(entry.getKey());

				if (adiacente == null) {
					continue; // non c'è un componente adiacente in quella direzione
				}

				TipoComponente tipoComponente = adiacente.getTipo();
				if (tipoComponente == TipoComponente.CABINA_EQUIPAGGIO
						|| tipoComponente == TipoComponente.CABINA_PARTENZA) {
					cabineCollegate.add(componentiAdiacenti.get(entry.getKey()));
				}
			}
		}
		return cabineCollegate;
	}
}
