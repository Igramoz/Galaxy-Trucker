package grafica.renderer;

import java.util.Arrays;
import java.util.List;

import io.GestoreIO;
import model.Colpo;
import model.carte.PioggiaDiMeteoriti;
import model.enums.Direzione;
import grafica.CostantiGrafica;
import grafica.TextAligner;

public class CarteRenderer {

	private final GestoreIO io = new GestoreIO();
	private final TextAligner textAligner = new TextAligner();

	// TODO riscrivere usando i generici.
	public String[] rappresentaMeteoriti(PioggiaDiMeteoriti pioggiaDiMeteoriti) {

		// Calcolo padding per l’allineamento orizzontale
		final int padding = textAligner.lunghezzaVisivaTestoCheck(CostantiGrafica.METEORITE_GROSSO)
				+ CostantiGrafica.FRECCIA_DESTRA.length() + 1;

		io.stampa(textAligner.alignCenter("Pioggia di meteoriti"));
		io.aCapo();

		int[] dimensioni = calcolaMaxMeteoriti(pioggiaDiMeteoriti);

		final int righe = dimensioni[0];
		final int colonne = dimensioni[1];

		String[] out = new String[righe + 2 * padding];
		Arrays.fill(out, "");

		// Aggiunge meteoriti che provengono dall'alto e dal basso
		String[] meteoritiSopra = rappresentaMeteoritiVerticali(
				pioggiaDiMeteoriti.getMeteoritiByDirezione(Direzione.SOPRA), padding, colonne, Direzione.SOTTO);
		out[0] = meteoritiSopra[0];
		out[1] = meteoritiSopra[1];
		out[2] = meteoritiSopra[2];

		String[] meteoritiSotto = rappresentaMeteoritiVerticali(
				pioggiaDiMeteoriti.getMeteoritiByDirezione(Direzione.SOTTO), padding, colonne, Direzione.SOPRA);
		out[out.length - 3] = meteoritiSotto[0];
		out[out.length - 2] = meteoritiSotto[1];
		out[out.length - 1] = meteoritiSotto[2];

		// Aggiunge meteoriti orizzontali da destra verso sinistra
		List<Colpo> daSinistra = pioggiaDiMeteoriti.getMeteoritiByDirezione(Direzione.SINISTRA);
		for (int i = padding; i < out.length - padding; i++) {
			if (!daSinistra.isEmpty())
				out[i] += rappresentaMeteoriteOrizzontale(daSinistra.remove(0), Direzione.SINISTRA);
			out[i] = textAligner.estendiStringa(out[i], colonne + padding);
		}

//		List<TipiMeteorite> daDestra = pioggiaDiMeteoriti.getMeteoritiByDirezione(Direzione.DESTRA);
//		for (int i = padding; i < out.length - padding; i++) {
//			if (!daDestra.isEmpty())
//				out[i] += rappresentaMeteoriteOrizzontale(daDestra.remove(0), Direzione.SINISTRA);
//			out[i] = textAligner.estendiStringa(out[i], colonne + padding);
//		}
		
		// Aggiunge meteoriti orizzontali da sinistra verso destra
		List<Colpo> daDestra = pioggiaDiMeteoriti.getMeteoritiByDirezione(Direzione.DESTRA);
		for (int i = padding; i < out.length - padding; i++) {
			if (daDestra.isEmpty())
				out[i] += " ".repeat(padding);
			else
				out[i] += rappresentaMeteoriteOrizzontale(daDestra.remove(0), Direzione.DESTRA);
		}
		
		out = textAligner.alignCenter(out);
		return out;
	}

	// Calcola il numero massimo di righe e colonne necessarie per rappresentare i
	// meteoriti (senza padding)
	private int[] calcolaMaxMeteoriti(PioggiaDiMeteoriti pioggia) {
		int meteoritiDestra = pioggia.getMeteoritiByDirezione(Direzione.DESTRA).size();
		int meteoritiSinistra = pioggia.getMeteoritiByDirezione(Direzione.SINISTRA).size();
		int meteoritiSotto = pioggia.getMeteoritiByDirezione(Direzione.SOTTO).size();
		int meteoritiSopra = pioggia.getMeteoritiByDirezione(Direzione.SOPRA).size();

		int colonne = Math.max(meteoritiSopra, meteoritiSotto);
		int righe = Math.max(meteoritiSinistra, meteoritiDestra);

		return new int[] { righe, colonne };
	}

	// Rappresenta i meteoriti che si muovono verticalmente (sopra o sotto).
	private String[] rappresentaMeteoritiVerticali(List<Colpo> meteoriti, int padding, int colonne,
			Direzione dir) {
		String[] out = new String[padding];
		Arrays.fill(out, " ".repeat(padding));

		for (Colpo meteorite : meteoriti) {

			if (dir == Direzione.SOTTO) {
				out[0] += convertiMeteorite(meteorite);
				out[1] += "|";
				out[2] += CostantiGrafica.FRECCIA_SOTTO;
			} else {
				out[0] += CostantiGrafica.FRECCIA_SOPRA;
				out[1] += "|";
				out[2] += convertiMeteorite(meteorite);
			}
		}

		for (int i = 0; i < padding; i++) {
			out[i] = textAligner.estendiStringa(out[i], colonne + 2 * padding);
		}
		return out;
	}

	// TODO riscrivere usando i generici.

	// rappresenta O > oppure < o (DIREZIONE da cui proviene il meteorite)
	private String rappresentaMeteoriteOrizzontale(Colpo meteorite, Direzione direzioneEntrata) {
		return switch (direzioneEntrata) {
		case DESTRA -> CostantiGrafica.FRECCIA_SINISTRA + "-" + convertiMeteorite(meteorite);
		case SINISTRA -> convertiMeteorite(meteorite) + "-" + CostantiGrafica.FRECCIA_DESTRA;
		default -> " ";
		};
	}

	// TODO fare overload.

	// Restituisce il simbolo che corrisponde al meteorite
	private String convertiMeteorite(Colpo meteorite) {
		return switch (meteorite.getDimensioniColpo()) {
		case GROSSO -> CostantiGrafica.METEORITE_GROSSO;
		case PICCOLO -> CostantiGrafica.METEORITE_PICCOLO;
		default -> " ";
		};
	}

}
