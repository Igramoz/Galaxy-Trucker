package grafica.renderer;

import java.util.Arrays;
import java.util.List;

import grafica.*;
import grafica.formattatori.FormattatoreGrafico;
import io.GestoreIO;
import model.componenti.*;
import model.enums.*;
import util.Util;
import util.layout.Direzione;

public class ComponenteRenderer {

	private final TextAligner textAligner = new TextAligner();
	private final FormattatoreGrafico formattatoreGrafico = new FormattatoreGrafico();
	private final GestoreIO io = new GestoreIO();

	public String[] rappresentaComponente(Componente componente) {

		/*
		 * Esempio di output: || #MD = |
		 */
		String[] rappresentazioneComponente = new String[CostantiGrafica.ALTEZZA_COMPONENTE];

		// se il componente è null rappresento O
		if (componente == null) {
			rappresentazioneComponente[0] = textAligner.centraTestoInLarghezza("",
					CostantiGrafica.LARGHEZZA_COMPONENTE);
			rappresentazioneComponente[1] = textAligner.centraTestoInLarghezza(CostantiGrafica.COMPONENTE_NULL,
					CostantiGrafica.LARGHEZZA_COMPONENTE);
			rappresentazioneComponente[2] = textAligner.centraTestoInLarghezza("",
					CostantiGrafica.LARGHEZZA_COMPONENTE);
			return rappresentazioneComponente;
		}

		String sopra = componente.getTubo(Direzione.SOPRA).getSimbolo(Direzione.SOPRA); // tubo sopra
		String sinistra = componente.getTubo(Direzione.SINISTRA).getSimbolo(Direzione.SINISTRA); // tubo a sx
		String tipo = formattaComponente(componente); // componente
		String destra = componente.getTubo(Direzione.DESTRA).getSimbolo(Direzione.DESTRA); // tubo a dx
		String sotto = componente.getTubo(Direzione.SOTTO).getSimbolo(Direzione.SOTTO); // tubo sotto

		rappresentazioneComponente[0] = textAligner.centraTestoInLarghezza(sopra, CostantiGrafica.LARGHEZZA_COMPONENTE);
		rappresentazioneComponente[1] = sinistra + textAligner.centraTestoInLarghezza(tipo,
				CostantiGrafica.LARGHEZZA_COMPONENTE - sinistra.length() - destra.length()) + destra;
		rappresentazioneComponente[2] = textAligner.centraTestoInLarghezza(sotto, CostantiGrafica.LARGHEZZA_COMPONENTE);
		return rappresentazioneComponente;
	}

	// Gestisce la grafica di componenti particolari
	private String formattaComponente(Componente componente) {

		String output = componente.getTipo().toString();

		if (componente.getTipo() == TipoComponente.MOTORE_SINGOLO
				|| componente.getTipo() == TipoComponente.MOTORE_DOPPIO) {
			output = aggiungiFrecciaDirezione(output, ((Motore) componente).getDirezioneMotore());
		} else if (componente.getTipo() == TipoComponente.CANNONE_SINGOLO
				|| componente.getTipo() == TipoComponente.CANNONE_DOPPIO) {
			output = aggiungiFrecciaDirezione(output, ((Cannone) componente).getDirezioneFuoco());
		} else if (componente.getTipo() == TipoComponente.SCUDO) {
			Direzione[] direzioni = ((GeneratoreDiScudi) componente).getDirezioni();
			output = aggiungiFrecciaDirezione(output, direzioni[0]);
			output = aggiungiFrecciaDirezione(output, direzioni[1]);

		} else if (componente.getTipo() == TipoComponente.STIVA
				|| componente.getTipo() == TipoComponente.STIVA_SPECIALE) {
			output = aggiungiCapacita(output, ((Stiva) componente).getScomparti());
		} else if (componente.getTipo() == TipoComponente.VANO_BATTERIA) {
			output = aggiungiCapacita(output, ((VanoBatteria) componente).getCapacitaMassima());
		}

		else if (componente.getTipo() == TipoComponente.CABINA_PARTENZA) {
			output = ((CabinaPartenza) componente).getColore().getCodice() + output;
		}

		return output;
	}

	private String aggiungiFrecciaDirezione(String siglaComponente, Direzione direzione) {

		return switch (direzione) {
		case SOPRA -> siglaComponente + CostantiGrafica.FRECCIA_SOPRA;
		case SOTTO -> siglaComponente + CostantiGrafica.FRECCIA_SOTTO;
		case SINISTRA -> CostantiGrafica.FRECCIA_SINISTRA + siglaComponente;
		case DESTRA -> siglaComponente + CostantiGrafica.FRECCIA_DESTRA;
		};
	}

	private String aggiungiCapacita(String siglaComponente, Integer capacita) {
		return siglaComponente + capacita.toString();
	}

	public void rappresentaComponenti(List<Componente> lista) {

		if (lista == null) {
			return; // returno e non lancio l'eccezione perché i primi turni la lista è vuota
		}

		final int SPAZIO_TRA_COMPONENTI = 4;

		// Calcolo se i pezzi occupano piu di una riga
		int larghezzaTotaleComponenti = lista.size() * (CostantiGrafica.LARGHEZZA_COMPONENTE + SPAZIO_TRA_COMPONENTI);
		int righeTotali = 1; // Di default considero che 1 riga sia sufficiente per rappresentare tutti i
								// componenti
		int numComponentiPerRiga; // Numero di componenti presente su ogni ria

		righeTotali = (int) Math.ceil((double) larghezzaTotaleComponenti / GraficaConfig.LARGHEZZA_SCHERMO);

		// Calcolo quanti componenti verranno stampati per riga
		numComponentiPerRiga = (int) Math.ceil((double) lista.size() / righeTotali);

		String[] singoloComponente = new String[CostantiGrafica.ALTEZZA_COMPONENTE];
		String[] righeComponenti = new String[CostantiGrafica.ALTEZZA_COMPONENTE * righeTotali];

		// Inizializzo righeComponenti
		Arrays.fill(righeComponenti, "");

		int riga = -1; // Tiene conto del numero di righe stampate
		for (Integer n = 0; n < lista.size(); n++) {

			// Vado a capo se ho stampato tutti i componenti della riga
			if (n % numComponentiPerRiga == 0) {
				riga++;
			}

			// scrivo il numero che corrisponde al componente
			righeComponenti[0 + riga * CostantiGrafica.ALTEZZA_COMPONENTE] += textAligner.estendiStringa(" " + (n + 1),
					SPAZIO_TRA_COMPONENTI);
			for (int j = 1; j < CostantiGrafica.ALTEZZA_COMPONENTE; j++) {
				righeComponenti[j + riga * CostantiGrafica.ALTEZZA_COMPONENTE] += textAligner.estendiStringa("",
						SPAZIO_TRA_COMPONENTI);

			}

			singoloComponente = rappresentaComponente(lista.get(n));

			// concateno il componente alla riga
			for (int i = 0; i < CostantiGrafica.ALTEZZA_COMPONENTE; i++) {
				righeComponenti[i + riga * CostantiGrafica.ALTEZZA_COMPONENTE] += singoloComponente[i];
			}

		}
		io.stampa(righeComponenti);
		io.aCapo();
	}

	// stampa il componente con il nome completo e altre informazioni
	public String rappresentazioneCompletaComponente(Componente componente) {
		String output;
		if (componente.getTipo() == TipoComponente.CABINA_PARTENZA) {
			CabinaPartenza cabina = (CabinaPartenza) componente;
			output = cabina.getColore().getCodice();
		} else
			output = componente.getTipo().getColore().getCodice();

		output = output + componente.getTipo().name() + Colore.DEFAULT.getCodice();

		// Se il componente è una cabina rappresento l'equipaggio
		if (componente instanceof CabinaDiEquipaggio) {
			List<TipoPedina> equipaggio = ((CabinaDiEquipaggio) componente).getEquipaggio();
			if (equipaggio.isEmpty()) {
				output += " vuota";
			} else {
				output += " equipaggio:";
				for (TipoPedina pedina : equipaggio) {
					output += " " + formattatoreGrafico.formatta(pedina);
				}
			}
		} else if (componente instanceof Stiva) {
			TipoMerce[] merci = ((Stiva) componente).getMerci();
			output += " capienza: " + ((Stiva) componente).getScomparti();
			// se merci è null o se vuota
			if (Util.isArrayEmpty(merci)) {
				output += " la stiva è vuota.";
			} else {
				output += " merci:";
				for (TipoMerce merce : merci) {
					if (merce != null) {
						output += " " + formattatoreGrafico.formatta(merce);
					}
				}
			}
		} else if (componente.getTipo() == TipoComponente.VANO_BATTERIA) {
			int carica = ((VanoBatteria) componente).getBatterie();
			if (carica == 0) {
				output += " scarica";
			} else {
				output += " carica: " + carica;
			}
		} else if (componente instanceof Cannone) {
			output += " direzione fuoco: " + ((Cannone) componente).getDirezioneFuoco().name();
		}

		return output;
	}
}
