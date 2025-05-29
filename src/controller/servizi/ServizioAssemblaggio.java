package controller.servizi;

import java.util.*;

import model.componenti.*;
import model.componenti.cabine.CabinaDiEquipaggio;
import model.componenti.cannoni.Cannone;
import model.componenti.cannoni.CannoneDoppio;
import model.componenti.motori.Motore;
import model.componenti.motori.MotoreDoppio;
import model.componenti.stive.Stiva;
import model.componenti.stive.StivaSpeciale;
import model.enums.TipoTubo;
import util.layout.Direzione;
import util.random.RandomUtil;

public class ServizioAssemblaggio {
	// Genera ed estrae randomicamente i componenti della nave

	private List<Componente> componentiGenerati = new ArrayList<>();
	private RandomUtil random = new RandomUtil();

	// Di default ha già la lista di compoenenti generata, bisogna solo estrarli
	public ServizioAssemblaggio() {
		generaComponenti();
		Collections.shuffle(componentiGenerati);
	}

	public Componente estraiComponente() {

		// Estrae un componente randomicamente
		// restituisce null se la lista è vuota
		if (componentiGenerati.isEmpty()) {
			return null; // Se non ci sono più componenti, restituisco null
		}
		return componentiGenerati.remove(0); // Rimuovo il componente dalla lista
	}

	private void generaComponenti() {

		// Genero tutti i componenti per ciascun tipo
		for (TipoComponente tipo : TipoComponente.values()) {
			if (tipo == TipoComponente.CABINA_PARTENZA)
				continue;

			Direzione direzioneDaIgnorare = direzioneDaIgnorare(tipo); // Direzione dove il compoenente non ha tubi
			// Genero i componenti per ciascun tipo
			for (int nOggettiPerTipo = 0; nOggettiPerTipo < tipo.getMaxIstanze(); nOggettiPerTipo++) {
				Componente componente = null;
				Map<Direzione, TipoTubo> tubi = null;

				// Genero il componente fin quando è diverso da quelli precedenti o non ha tutti
				// i tubi nulli
				do {
					tubi = tubiCasuali(); // Genero i tubi randomicamente
					componente = generaComponente(tipo, tubi);

				} while (!tubiNonNulli(tubi, direzioneDaIgnorare) || componentiGenerati.contains(componente));

				componentiGenerati.add(componente);
			}
		}
	}

	private Direzione direzioneDaIgnorare(TipoComponente tipo) {
		// Restituisce la direzione che non ha il tubo in base al componente

		switch (tipo) {

		case MOTORE_SINGOLO:
		case MOTORE_DOPPIO:
			return Direzione.SOTTO; // Il motore non ha tubi sotto
		case CANNONE_SINGOLO:
		case CANNONE_DOPPIO:
			return Direzione.SOPRA; // Il cannone non ha tubi sopra
		default:
			return null; // I tubi sono validi in tutte le direzioni
		}
	}

	private Componente generaComponente(TipoComponente tipo, Map<Direzione, TipoTubo> tubi) {
		// Genera un componente in base al tipo

		switch (tipo) {
		case CABINA_EQUIPAGGIO:
			return new CabinaDiEquipaggio(tubi);
		case CANNONE_SINGOLO:
			return new Cannone(tubi);
		case CANNONE_DOPPIO:
			return new CannoneDoppio(tubi);
		case MOTORE_SINGOLO:
			return new Motore(tubi);
		case MOTORE_DOPPIO:
			return new MotoreDoppio(tubi);
		case SCUDO:
			return new GeneratoreDiScudi(tubi);
		case STIVA:
			return new Stiva(tubi, random.randomInt(Stiva.MIN_SCOMPARTI, Stiva.MAX_SCOMPARTI));
		case STIVA_SPECIALE:
			return new StivaSpeciale(tubi, random.randomInt(StivaSpeciale.MIN_SCOMPARTI, StivaSpeciale.MAX_SCOMPARTI));
		case VANO_BATTERIA:
			return new VanoBatteria(tubi, random.randomInt(VanoBatteria.MIN_BATTERIE, VanoBatteria.MAX_BATTERIE));
		case SOVRASTRUTTURA_ALIENA_VIOLA:
			return new ModuloSupportoAlieni(tubi, TipoComponente.SOVRASTRUTTURA_ALIENA_VIOLA);
		case SOVRASTRUTTURA_ALIENA_MARRONE:
			return new ModuloSupportoAlieni(tubi, TipoComponente.SOVRASTRUTTURA_ALIENA_MARRONE);
		case TUBO:
			return new Tubo(tubi);
		default:
			throw new IllegalArgumentException("Tipo non supportato: " + tipo);
		}

	}

	private Map<Direzione, TipoTubo> tubiCasuali() {

		// genera i 4 tubi
		Map<Direzione, TipoTubo> tubi = new EnumMap<>(Direzione.class);

		do {
			for (Direzione direzione : Direzione.values()) {
				tubi.put(direzione, random.randomEnum(TipoTubo.class));
			}
		} while (!tubiNonNulli(tubi)); // Controlla che almeno un tubo sia diverso da NESSUNO

		return tubi;
	}

	private boolean tubiNonNulli(Map<Direzione, TipoTubo> tubi) {
		// Controlla che un pezzo non venga generato senza tubi, in tutte le direzioni
		return tubiNonNulli(tubi, null);
	}

	private boolean tubiNonNulli(Map<Direzione, TipoTubo> tubi, Direzione direzioneDaNonControllare) {
		// Alcuni pezzi non hanno tubi una direzione, bisogna verificare che nelle altre
		// direzioni non siano NESSUNO.
		// Se direzioneDaNonControllare == null controllo tutte le direzioni

		for (Direzione direzione : Direzione.values()) {

			if (direzioneDaNonControllare != null && direzione == direzioneDaNonControllare) {
				continue; // Salta la direzione da non controllare
			}

			// Confronto il tubo nella direzione corrente
			if (tubi.get(direzione) != TipoTubo.NESSUNO) {
				return true;
			}
		}
		return false;
	}
}
