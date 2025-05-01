package servizi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import grafica.Colore;
import util.*;
import util.layout.Direzione;
import util.random.RandomUtil;
import model.Giocatore;
import model.carte.*;
import model.carte.colpo.*;
import model.carte.colpo.Colpo.*;
import model.carte.criteriEffetti.*;
import model.carte.pianeti.*;
import model.carte.nemici.*;
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
				Direzione direzioneTemp = random.randomEnum(Direzione.class);
				Colpo temp = new Colpo(TipoColpo.METEORITE, dimensioneTemp, direzioneTemp);

				listaAsteroidi.add(temp);

			}

			listaAsteroidi = Colpo.ordinaPerDirezione(listaAsteroidi);
			CriterioConEffetto criterioEpenalita = new CriterioConEffetto(null, Effetto.COLPI, listaAsteroidi);
			
			out.add(new PioggiaDiMeteoriti(criterioEpenalita));
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
		final List<CriterioConEffetto> criteriEpenalitaLivello1 = List.of(
				new CriterioConEffetto(Criterio.EQUIPAGGIO, Effetto.GIORNI_VOLO, 3),
				new CriterioConEffetto(Criterio.POTENZA_MOTRICE, Effetto.PERDITA_EQUIPAGGIO, 2),
				new CriterioConEffetto(Criterio.POTENZA_FUOCO, Effetto.COLPI, cannonateLivello1));

		final List<CriterioConEffetto> criteriEpenalitaLivello2 = List.of(
				new CriterioConEffetto(Criterio.POTENZA_FUOCO, Effetto.GIORNI_VOLO, 4),
				new CriterioConEffetto(Criterio.POTENZA_MOTRICE, Effetto.PERDITA_MERCE, 3),
				new CriterioConEffetto(Criterio.EQUIPAGGIO, Effetto.COLPI, cannonateLivello2));

		final List<CriterioConEffetto> criteriEpenalitaLivello3 = List.of(
				new CriterioConEffetto(Criterio.EQUIPAGGIO, Effetto.PERDITA_MERCE, 4),
				new CriterioConEffetto(Criterio.POTENZA_FUOCO, Effetto.PERDITA_EQUIPAGGIO, 4),
				new CriterioConEffetto(Criterio.POTENZA_MOTRICE, Effetto.COLPI, cannonateLivello3));

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

		int minimoMerci = 1;
		if (livello == LivelliPartita.LIVELLO_3)
			minimoMerci = 2;

		List<Colore> colori = new ArrayList<>(Giocatore.coloriDisponibiliGiocatori);
		Collections.shuffle(colori);
		List<Pianeta> pianeti = new ArrayList<>();
		int numeroPianeti = random.randomInt(2, 5);

		for (int i = 0; i < numeroPianeti; i++) {
			int numeroMerci = random.randomInt(minimoMerci, 6);
			List<TipoMerce> merci = new ArrayList<>();
			Colore colorePianeta = colori.get(i);

			Map<Colore, Integer> probabilita = generaProbabilitaMerci(colorePianeta);
			for (int j = 0; j < numeroMerci; j++) {
				Colore coloreMerce = random.getEnumValueByProbability(probabilita);

				List<TipoMerce> merciCompatibili = new ArrayList<>();
				for (TipoMerce m : TipoMerce.values()) {
					if (m.getColore() == coloreMerce) {
						merciCompatibili.add(m);
					}
				}

				if (merciCompatibili.isEmpty()) {
					// se la lista è vuota, NON vado avanti, altrimenti avrei un errore
					// quindi torno indietro per rigenerare questa merce
					j--;
					continue;
				}

				int index = random.randomInt(merciCompatibili.size());
				TipoMerce merce = merciCompatibili.get(index);
				merci.add(merce);
			}
			pianeti.add(new Pianeta(merci, colorePianeta));
		}

		List<CartaPianeti> carte = new ArrayList<>();
		carte.add(new CartaPianeti(pianeti, giorni));
		return carte;
	}

	private Map<Colore, Integer> generaProbabilitaMerci(Colore colorePianeta) {
		Map<Colore, Integer> probabilita = new HashMap<>();
		for (Colore colore : Colore.values()) {
			if (colore == colorePianeta) {
				probabilita.put(colore, 50); // molto probabile
			} else {
				probabilita.put(colore, 10); // poco probabile
			}
		}
		return probabilita;
	}
	
	private List<Nemico> generaNemici(LivelliPartita lvl){
		// Istanzio gli schiavisti
		final Schiavisti[] schiavisti = new Schiavisti[3];
		schiavisti[0] = new Schiavisti(6, 5, 1, 3);
		schiavisti[1] = new Schiavisti(7, 8, 2, 4);
		schiavisti[2] = new Schiavisti(8, 10,2, 5);

		// Istanzio i pirati
		@SuppressWarnings("unchecked")
		final List<Colpo>[] colpiPirati = (List<Colpo>[]) new ArrayList[3];
		List<Colpo> listaColpi = new ArrayList<>();
		listaColpi.add(new Colpo(TipoColpo.CANNONATA, DimensioniColpo.PICCOLO, Direzione.SOPRA));
		listaColpi.add(new Colpo(TipoColpo.CANNONATA, DimensioniColpo.GROSSO, Direzione.SOPRA));
		listaColpi.add(new Colpo(TipoColpo.CANNONATA, DimensioniColpo.PICCOLO, Direzione.SOPRA));
		colpiPirati[0] = listaColpi;
		
		listaColpi = new ArrayList<Colpo>();
		listaColpi.add(new Colpo(TipoColpo.CANNONATA, DimensioniColpo.GROSSO, Direzione.SOPRA));
		listaColpi.add(new Colpo(TipoColpo.CANNONATA, DimensioniColpo.PICCOLO, Direzione.SOPRA));
		listaColpi.add(new Colpo(TipoColpo.CANNONATA, DimensioniColpo.GROSSO, Direzione.SOPRA));
		colpiPirati[1] = listaColpi;

		listaColpi = new ArrayList<Colpo>();
		listaColpi.add(new Colpo(TipoColpo.CANNONATA, DimensioniColpo.GROSSO, Direzione.SOPRA));
		listaColpi.add(new Colpo(TipoColpo.CANNONATA, DimensioniColpo.PICCOLO, Direzione.SOPRA));
		listaColpi.add(new Colpo(TipoColpo.CANNONATA, DimensioniColpo.GROSSO, Direzione.SOPRA));
		listaColpi.add(new Colpo(TipoColpo.CANNONATA, DimensioniColpo.PICCOLO, Direzione.SINISTRA));
		listaColpi.add(new Colpo(TipoColpo.CANNONATA, DimensioniColpo.PICCOLO, Direzione.DESTRA));
		colpiPirati[2] = listaColpi;

		final Pirati[] pirati = new Pirati[3];
		pirati[0] = new Pirati(5, 4, 1, colpiPirati[0]);
		pirati[1] = new Pirati(6, 7, 2, colpiPirati[1]);
		pirati[2] = new Pirati(10,12,2, colpiPirati[2]);
		 
		// Istanzio i contrabbandieri
		@SuppressWarnings("unchecked")
		final List<TipoMerce>[] merci = (List<TipoMerce>[]) new ArrayList[3];
		List<TipoMerce> listaMerci = List.of(TipoMerce.GIALLO, TipoMerce.VERDE, TipoMerce.BLU);
		merci[0] = listaMerci;
		
		listaMerci = List.of(TipoMerce.ROSSO, TipoMerce.GIALLO, TipoMerce.GIALLO);
		merci[1] = listaMerci;

		listaMerci = List.of(TipoMerce.ROSSO, TipoMerce.ROSSO, TipoMerce.GIALLO, TipoMerce.GIALLO, TipoMerce.VERDE);
		merci[2] = listaMerci;
		
		final Contrabbandieri[] contrabbandieri = new Contrabbandieri[3];
		contrabbandieri[0] = new Contrabbandieri(4, merci[0], 2, 1);
		contrabbandieri[1] = new Contrabbandieri(8, merci[1], 1, 3);
		contrabbandieri[2] = new Contrabbandieri(9, merci[2], 2, 4);

		List<Nemico> out = new ArrayList<>();
		switch (lvl) {
		case LIVELLO_1: {
			out.add(schiavisti[0]);
			out.add(pirati[0]);
			out.add(contrabbandieri[0]);
			break;
		}
		case LIVELLO_2: {
			out.add(schiavisti[1]);
			out.add(pirati[1]);
			out.add(contrabbandieri[1]);	
			break;
		}
		case LIVELLO_3: {
			out.add(schiavisti[0]);
			out.add(pirati[0]);
			out.add(contrabbandieri[0]);	
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + lvl.name());
		}
		
		return out;
	}
	
}
