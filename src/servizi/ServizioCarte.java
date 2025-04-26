package servizi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import grafica.Colore;
import util.*;
import model.Colpo;
import model.Colpo.DimensioniColpo;
import model.Colpo.TipoColpo;
import model.Giocatore;
import model.carte.*;
import model.carte.pianeti.*;
import model.carte.zonaDiGuerra.*;
import model.enums.Direzione;
import model.enums.TipoMerce;
import partita.LivelliPartita;

public class ServizioCarte {
	// Genera randomicamente le carte

	private List<Carta> carteGenerate = new ArrayList<>();
	private RandomUtil random = new RandomUtil();

	public ServizioCarte(LivelliPartita livello) {
		generaCarte(livello);
		Collections.shuffle(carteGenerate);
	}

	private void generaCarte(LivelliPartita livello) {
	}

	// TODO mettere privato
	public List<PioggiaDiMeteoriti> generaMeteoriti(LivelliPartita lvl) {

		/*
		 * livello Nasteroidi rapporto Grandi su totali 1 3 - 4 2 su 12 2 4 - 5 4 su 14
		 * 3 5 - 6 6 su 16
		 */

		final int asteroidiTotaliLivelloBase = 10; // Asteroidi totali nel livello base

		int nAsteoridi = 2 + lvl.getNumeroLivello();
		int nAsteroidiGrandi = 2 * lvl.getNumeroLivello();
		int asteroidiTotali = asteroidiTotaliLivelloBase + nAsteroidiGrandi;

		// Numero di asteroidi da generare in base al livello
		int asteroidiDaGenerare = random.randomInt(nAsteoridi, nAsteoridi + 2);
		List<PioggiaDiMeteoriti> out = new ArrayList<>();

		for (int nCarta = 0; nCarta < TipoCarta.PIOGGIA_DI_METEORITI.getNumeroCarte(lvl); nCarta++) {

			List<Colpo> listaAsteroidi = new ArrayList<>();
			for (int nAsteroide = 0; nAsteroide < asteroidiDaGenerare; nAsteroide++) {

				Map<DimensioniColpo, Integer> collezione = Map.of(DimensioniColpo.GROSSO, nAsteroidiGrandi,
						DimensioniColpo.PICCOLO, asteroidiTotali - nAsteroidiGrandi);

				// Sceglie casualmente un Colpo grosso o piccolo
				DimensioniColpo dimensioneTemp = random.getEnumValueByProbability(collezione);
				Direzione direzioneTemp = random.randomDirezione();
				Colpo temp = new Colpo(TipoColpo.METEORITE, dimensioneTemp, direzioneTemp);

				listaAsteroidi.add(temp);

			}

			listaAsteroidi = Colpo.ordinaPerDirezione(listaAsteroidi);
			out.add(new PioggiaDiMeteoriti(listaAsteroidi));
		}

		return out;
	}

	private List<ZonaDiGuerra> generaZonaDiGuerra(LivelliPartita lvl) {

		// cannonate in base al livello
		final List<Colpo> cannonateLivello1 = List.of(
				new Colpo(TipoColpo.CANNONATA, DimensioniColpo.PICCOLO, Direzione.SOTTO),
				new Colpo(TipoColpo.CANNONATA, DimensioniColpo.GROSSO, Direzione.SOTTO));

		final List<Colpo> cannonateLivello2 = List.of(
				new Colpo(TipoColpo.CANNONATA, DimensioniColpo.PICCOLO, Direzione.SOPRA),
				new Colpo(TipoColpo.CANNONATA, DimensioniColpo.PICCOLO, Direzione.SINISTRA),
				new Colpo(TipoColpo.CANNONATA, DimensioniColpo.PICCOLO, Direzione.DESTRA),
				new Colpo(TipoColpo.CANNONATA, DimensioniColpo.GROSSO, Direzione.SOTTO));

		final List<Colpo> cannonateLivello3 = List.of(
				new Colpo(TipoColpo.CANNONATA, DimensioniColpo.PICCOLO, Direzione.SOPRA),
				new Colpo(TipoColpo.CANNONATA, DimensioniColpo.PICCOLO, Direzione.SOPRA),
				new Colpo(TipoColpo.CANNONATA, DimensioniColpo.PICCOLO, Direzione.SINISTRA),
				new Colpo(TipoColpo.CANNONATA, DimensioniColpo.PICCOLO, Direzione.DESTRA),
				new Colpo(TipoColpo.CANNONATA, DimensioniColpo.GROSSO, Direzione.SOTTO),
				new Colpo(TipoColpo.CANNONATA, DimensioniColpo.GROSSO, Direzione.SOTTO));

		// Caratteristiche delle carte
		final List<PenalitaConCriterio> criteriEpenalitaLivello1 = List.of(
				new PenalitaConCriterio(Criterio.EQUIPAGGIO, Penalita.GIORNI_VOLO, 3),
				new PenalitaConCriterio(Criterio.POTENZA_MOTRICE, Penalita.EQUIPAGGIO, 2),
				new PenalitaConCriterio(Criterio.POTENZA_FUOCO, Penalita.CANNONATE, cannonateLivello1));

		final List<PenalitaConCriterio> criteriEpenalitaLivello2 = List.of(
				new PenalitaConCriterio(Criterio.POTENZA_FUOCO, Penalita.GIORNI_VOLO, 4),
				new PenalitaConCriterio(Criterio.POTENZA_MOTRICE, Penalita.MERCE, 3),
				new PenalitaConCriterio(Criterio.EQUIPAGGIO, Penalita.CANNONATE, cannonateLivello2));

		final List<PenalitaConCriterio> criteriEpenalitaLivello3 = List.of(
				new PenalitaConCriterio(Criterio.EQUIPAGGIO, Penalita.MERCE, 4),
				new PenalitaConCriterio(Criterio.POTENZA_FUOCO, Penalita.EQUIPAGGIO, 4),
				new PenalitaConCriterio(Criterio.POTENZA_MOTRICE, Penalita.CANNONATE, cannonateLivello3));

		switch (lvl) {
		case LIVELLO_1 -> {
			return List.of(new ZonaDiGuerra(criteriEpenalitaLivello1, cannonateLivello1));
		}
		case LIVELLO_2 -> {
			return List.of(new ZonaDiGuerra(criteriEpenalitaLivello2, cannonateLivello2));
		}
		case LIVELLO_3 -> {
			return List.of(new ZonaDiGuerra(criteriEpenalitaLivello3, cannonateLivello3));
		}
		default -> throw new IllegalArgumentException("Unexpected value: " + lvl);
		}
	}

	
	public List<CartaPianeti> generaCartaPianeti(LivelliPartita livello) {
		int giorni = 0;
		switch (livello) {
		case LivelliPartita.LIVELLO_1:
			giorni = random.randomInt(1, 4);
			break;
		case LivelliPartita.LIVELLO_2:
			giorni = random.randomInt(2, 5);
			break;
		case LivelliPartita.LIVELLO_3:
			giorni = random.randomInt(2, 6);
			break;
		}

		int numeroMerci = random.randomInt(6, 13);
		List<Colore> colori = new ArrayList<>(Giocatore.coloriDisponibiliGiocatori);
		Collections.shuffle(colori);
		List<Pianeta> pianeti = new ArrayList<>();
		int numeroPianeti = random.randomInt(2, 5);

		for (int i = 0; i < numeroPianeti; i++) {
			List<TipoMerce> merci = new ArrayList<>();
			Colore colorePianeta = colori.get(i);

			// I colori delle merci dipendono dai colori dei pianeti
			List<TipoMerce> merciCompatibili = new ArrayList<>();
			for (TipoMerce m : TipoMerce.values()) {
				if (m.getColore() == colorePianeta) {
					merciCompatibili.add(m);
				}
			}

			for (int j = 0; j < numeroMerci; j++) {
				int index = random.randomInt(merciCompatibili.size());
				TipoMerce merce = merciCompatibili.get(index);
				merci.add(merce);
			}
			pianeti.add(new Pianeta(merci, colorePianeta));
		}

		List<CartaPianeti> carte = new ArrayList<>();
		carte.add(new CartaPianeti(pianeti, giorni, numeroMerci));
		return carte;
	}
}
