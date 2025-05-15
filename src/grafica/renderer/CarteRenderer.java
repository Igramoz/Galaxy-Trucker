package grafica.renderer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.GestoreIO;
import model.carte.*;
import model.carte.colpo.Colpo;
import model.carte.pianeti.*;
import util.layout.Direzione;
import grafica.CostantiGrafica;
import grafica.TextAligner;
import grafica.formattatori.FormattatoreGrafico;

public class CarteRenderer {

	private final GestoreIO io = new GestoreIO();
	private final TextAligner textAligner = new TextAligner();
	private final FormattatoreGrafico formattatore = new FormattatoreGrafico();

	public void rappresentaCarte(List<Carta> mazzo) {
		List<String> output = new ArrayList<>();

		for (Carta carta : mazzo) {
			output.add(carta.getTipoCarta().name());
		}
		io.stampa(output);
	}

	public void rappresentaColpi(List<Colpo> colpi) {

		// Calcolo padding per l’allineamento orizzontale
		final int padding = textAligner.lunghezzaVisivaTestoCheck(CostantiGrafica.COLPO_GROSSO)
				+ CostantiGrafica.FRECCIA_DESTRA.length() + 1;

		int[] dimensioni = calcolaMaxColpi(colpi);

		final int righe = dimensioni[0];
		final int colonne = dimensioni[1];

		String[] out = new String[righe + 2 * padding];
		Arrays.fill(out, "");

		// Aggiunge cannonate che provengono dall'alto e dal basso
		String[] cannonateSopra = rappresentaColpiVerticali(Colpo.getColpiByDirezione(colpi, Direzione.SOPRA), padding,
				colonne, Direzione.SOTTO);
		out[0] = cannonateSopra[0];
		out[1] = cannonateSopra[1];
		out[2] = cannonateSopra[2];

		String[] cannonateSotto = rappresentaColpiVerticali(Colpo.getColpiByDirezione(colpi, Direzione.SOTTO), padding,
				colonne, Direzione.SOPRA);
		out[out.length - 3] = cannonateSotto[0];
		out[out.length - 2] = cannonateSotto[1];
		out[out.length - 1] = cannonateSotto[2];

		// Aggiunge cannonate orizzontali da destra verso sinistra
		List<Colpo> daSinistra = Colpo.getColpiByDirezione(colpi, Direzione.SINISTRA);
		for (int i = padding; i < out.length - padding; i++) {
			if (!daSinistra.isEmpty())
				out[i] += rappresentaColpoOrizzontale(daSinistra.remove(0), Direzione.SINISTRA);
			out[i] = textAligner.estendiStringa(out[i], colonne + padding);
		}

		// Aggiunge cannonate orizzontali da sinistra verso destra
		List<Colpo> daDestra = Colpo.getColpiByDirezione(colpi, Direzione.DESTRA);
		for (int i = padding; i < out.length - padding; i++) {
			if (daDestra.isEmpty())
				out[i] += " ".repeat(padding);
			else
				out[i] += rappresentaColpoOrizzontale(daDestra.remove(0), Direzione.DESTRA);
		}

		out = textAligner.alignCenter(out);
		io.stampa(out);
	}

	// Calcola il numero massimo di righe e colonne necessarie per rappresentare i
	// cannonate (senza padding)
	private int[] calcolaMaxColpi(List<Colpo> colpi) {
		int cannonateDestra = Colpo.getColpiByDirezione(colpi, Direzione.DESTRA).size();
		int cannonateSinistra = Colpo.getColpiByDirezione(colpi, Direzione.SINISTRA).size();
		int cannonateSotto = Colpo.getColpiByDirezione(colpi, Direzione.SOTTO).size();
		int cannonateSopra = Colpo.getColpiByDirezione(colpi, Direzione.SOPRA).size();

		int colonne = Math.max(cannonateSopra, cannonateSotto);
		int righe = Math.max(cannonateSinistra, cannonateDestra);

		return new int[] { righe, colonne };
	}

	// Rappresenta i cannonate che si muovono verticalmente (sopra o sotto).
	private String[] rappresentaColpiVerticali(List<Colpo> colpi, int padding, int colonne, Direzione dir) {
		String[] out = new String[padding];
		Arrays.fill(out, " ".repeat(padding));

		for (Colpo colpo : colpi) {

			if (dir == Direzione.SOTTO) {
				out[0] += convertiColpo(colpo);
				out[1] += "|";
				out[2] += CostantiGrafica.FRECCIA_SOTTO;
			} else {
				out[0] += CostantiGrafica.FRECCIA_SOPRA;
				out[1] += "|";
				out[2] += convertiColpo(colpo);
			}
		}

		for (int i = 0; i < padding; i++) {
			out[i] = textAligner.estendiStringa(out[i], colonne + 2 * padding);
		}
		return out;
	}

	// rappresenta O > oppure < o (DIREZIONE da cui proviene il cannonata)
	private String rappresentaColpoOrizzontale(Colpo colpo, Direzione direzioneEntrata) {
		return switch (direzioneEntrata) {
		case DESTRA -> CostantiGrafica.FRECCIA_SINISTRA + "-" + convertiColpo(colpo);
		case SINISTRA -> convertiColpo(colpo) + "-" + CostantiGrafica.FRECCIA_DESTRA;
		default -> " ";
		};
	}

	// Restituisce il simbolo che corrisponde al cannonata
	private String convertiColpo(Colpo colpo) {
		return switch (colpo.getDimensioniColpo()) {
		case GROSSO -> CostantiGrafica.COLPO_GROSSO;
		case PICCOLO -> CostantiGrafica.COLPO_PICCOLO;
		default -> " ";
		};
	}

	public void rappresentaCarta(CartaPianeti carta) {
		List<Pianeta> pianeti = carta.getPianeti();
		textAligner.alignCenter("Carta pianeti");
		io.stampa("Pianeti non occupati:");
		for (int i = 0; i < pianeti.size(); i++) {
			Pianeta p = pianeti.get(i);
			if (!p.isOccupato()) {
				io.stampa("Pianeta " + formattatore.formatta(p.getColore()) + ", merci disponibili: ");
				formattatore.formattaEStampaMerci(p.getMerciDisponibili());
			}
		}
		io.stampa("Sono richiesti " + carta.getGiorniVoloPersi() + " giorni di volo per ciascun pianeta");
	}

	public void rappresentaCarta(StazioneAbbandonata stazioneAbbandonata) {
		io.stampa(textAligner.alignCenter("Carta stazione abbandonata"));
		io.stampa("Chi ha almeno: " + stazioneAbbandonata.getNumEquipaggio()
				+ " membri dell'equipaggio può stipare le seguenti merci:");
		formattatore.formattaEStampaMerci(stazioneAbbandonata.getMerci());
		io.stampa("scegliere di stipare la merce comporta la perdita di " + stazioneAbbandonata.getTempoDiVolo());
	}

	public void rappresentaCarta(NaveAbbandonata naveAbbandonata) {
		io.stampa(textAligner.alignCenter("Carta nave abbandonata"));
		io.stampa("Perdendo " + naveAbbandonata.getEquipaggioPerso() + " membri dell'equipaggio");
		io.stampa("si può guadagnare " + naveAbbandonata.getCrediti() + " crediti");
		io.stampa("scegliere guadagnare i crediti comporta la perdita di " + naveAbbandonata.getTempoDiVolo());
	}
}