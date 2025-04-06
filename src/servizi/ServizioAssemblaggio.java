package servizi;

import java.util.*;

import componenti.*;
import model.enums.TipoTubo;
import model.enums.Direzione;
import util.RandomUtil;

public class ServizioAssemblaggio {
	// Genera ed estrae randomicamente i componenti della nave

	private List<Componente> componentiGenerati = new ArrayList<>();
	private RandomUtil random = new RandomUtil();

	// Di default ha già la lista di compoenenti generata, bisogna solo estrarli
	public ServizioAssemblaggio() {
		generaComponenti();
	}
	
	public Componente estraiComponente() {
		
		// Estrae un componente randomicamente
		if (componentiGenerati.isEmpty()) {
			return null; // Se non ci sono più componenti, restituisco null
		}
		
		int randomNum = random.randomInt(componentiGenerati.size());
		return componentiGenerati.remove(randomNum); // Rimuovo il componente dalla lista
	}
	

	private void generaComponenti() {

		// Genero tutti i componenti per ciascun tipo
		for (TipoComponente tipo : TipoComponente.values()) {
			if (tipo == TipoComponente.CABINA_PARTENZA)
				continue;

			// Genero i componenti per ciascun tipo
			for (int nOggettiPerTipo = 0; nOggettiPerTipo < tipo.getMaxIstanze(); nOggettiPerTipo++) {
				Componente componente = null;
				
				// Rigenero il compoennte fin quando è diverso da quelli precedenti
				do {
					componente = generaComponente(tipo);

				} while (componentiGenerati.contains(componente));

				componentiGenerati.add(componente);
			}

		}

	}

	private Componente generaComponente(TipoComponente tipo) {
		// Genera un componente in base al tipo

		switch (tipo) {
		case CABINA_EQUIPAGGIO:
			return new CabinaDiEquipaggio(tubiCasuali());
		case CANNONE_SINGOLO:
			return new Cannone(tubiCasuali());
		case CANNONE_DOPPIO:
			return new CannoneDoppio(tubiCasuali());
		case MOTORE_SINGOLO:
			return new Motore(tubiCasuali());
		case MOTORE_DOPPIO:
			return new MotoreDoppio(tubiCasuali());
		case SCUDO:
			return new GeneratoreDiScudi(tubiCasuali());
		case STIVA:
			return new Stiva(tubiCasuali(), random.randomInt(Stiva.MIN_SCOMPARTI, Stiva.MAX_SCOMPARTI));
		case STIVA_SPECIALE:
			return new StivaSpeciale(tubiCasuali(),
					random.randomInt(StivaSpeciale.MIN_SCOMPARTI, StivaSpeciale.MAX_SCOMPARTI));
		case VANO_BATTERIA:
			return new VanoBatteria(tubiCasuali(),
					random.randomInt(VanoBatteria.MIN_BATTERIE, VanoBatteria.MAX_BATTERIE));
		case SOVRASTRUTTURA_ALIENA_VIOLA:
			return new ModuloSupportoAlieni(tubiCasuali(), TipoComponente.SOVRASTRUTTURA_ALIENA_VIOLA);
		case SOVRASTRUTTURA_ALIENA_MARRONE:
			return new ModuloSupportoAlieni(tubiCasuali(), TipoComponente.SOVRASTRUTTURA_ALIENA_MARRONE);
		case TUBO:
			return new Tubo(tubiCasuali());
		default:
			throw new IllegalArgumentException("Tipo non supportato: " + tipo);
		}

	}

	private TipoTubo generaTubo() {
		// Genera un tubo randomicamente
		int randomNum = random.randomInt(TipoTubo.values().length);
		return TipoTubo.values()[randomNum];
	}

	private Map<Direzione, TipoTubo> tubiCasuali() {
		// genera i 4 tubi
		Map<Direzione, TipoTubo> tubi = new EnumMap<>(Direzione.class);
		for (Direzione direzione : Direzione.values()) {
			tubi.put(direzione, generaTubo());
		}
		return tubi;
	}

}
