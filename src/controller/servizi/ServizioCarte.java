package controller.servizi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controller.partita.LivelliPartita;
import util.layout.Direzione;
import util.random.RandomUtil;
import view.Colore;
import model.Giocatore;
import model.carte.*;
import model.carte.colpo.*;
import model.carte.colpo.Colpo.*;
import model.carte.criteriEffetti.*;
import model.carte.pianeti.*;
import model.carte.nemici.*;
import model.enums.TipoMerce;

public class ServizioCarte {
	// Genera randomicamente le carte

	public final int numMazziNoti = 3; // numero di mazzi noti
	public final int numMazziIgnoti = 1;

	@SuppressWarnings("unchecked")
	private List<Carta>[] mazziNoti = new List[numMazziNoti];
	private List<Carta> mazzoIgnoto;
	private RandomUtil random = new RandomUtil();

	public ServizioCarte(LivelliPartita livello) {
		// inizializzo mazziNoti e mazzoIgnoto
		for (int i = 0; i < mazziNoti.length; i++) {
			mazziNoti[i] = new ArrayList<>();
		}
		mazzoIgnoto = new ArrayList<>();

		generaCarte(livello);
	}

	private void generaCarte(LivelliPartita livello) {
		LivelliPartita[] livelli = LivelliPartita.values();
		// bisogna generare le carte per ogni livello
		for (LivelliPartita livelloCarte : livelli) {
			// se il livello raggiuntò è maggiore di quello passato alla funzione ho
			// generato tutte le carte
			if (livelloCarte.ordinal() > livello.ordinal())
				break;

			List<Carta> mazzoPerLivello = generaCartePerLivello(livelloCarte, livello);
			separaMazzetti(mazzoPerLivello, livelloCarte);

		}
	}

	// genera tutte le carte di un livello
	private List<Carta> generaCartePerLivello(LivelliPartita livelloCarte, LivelliPartita livelliPartita) {
		List<Carta> mazzoPerLivello = new ArrayList<>();

		// genero tutte le carte di quel livello
		mazzoPerLivello.addAll(generaMeteoriti(livelloCarte));
		mazzoPerLivello.addAll(generaZonaDiGuerra(livelloCarte));
		mazzoPerLivello.addAll(generaCartaPianeti(livelloCarte));
		mazzoPerLivello.addAll(generaNemici(livelloCarte));
		mazzoPerLivello.addAll(generaPolvereStellare(livelloCarte));
		mazzoPerLivello.addAll(generaSabotaggio(livelloCarte));
		mazzoPerLivello.addAll(generaEpidemia(livelloCarte));
		mazzoPerLivello.addAll(generaNaveAbbandonata(livelloCarte));
		// TODO controllare che ci siano tutte le carte

		// mischio le carte
		Collections.shuffle(mazzoPerLivello);
		int numCarte = livelliPartita.getCartePerLivello(livelloCarte) * (numMazziNoti + numMazziIgnoti);

		if (mazzoPerLivello.size() < numCarte) {
			throw new IllegalStateException(
					"Sono state generate troppe poche carte per il livello " + livelliPartita.name());
		}
		return mazzoPerLivello;
	}

	// seprara il mazzo in mazzetti noti e ignoti
	private void separaMazzetti(List<Carta> mazzo, LivelliPartita livello) {
		if (mazzo.size() < (numMazziNoti + numMazziIgnoti) * livello.getCartePerLivello(livello)) {
			throw new IllegalStateException("Numero insufficiente di carte per separare i mazzetti");
		}

		for (int i = 0; i < livello.getCartePerLivello(livello); i++) {
			for (List<Carta> mazzoNoto : mazziNoti) {
				mazzoNoto.add(mazzo.remove(0));
			}
			mazzoIgnoto.add(mazzo.remove(0));
		}
	}

	public List<Carta> getMazzoIgnoto() {
		return mazzoIgnoto;
	}

	public List<Carta> getMazzoNoto(int num) {
		if (num >= mazziNoti.length || num < 0) {
			throw new IndexOutOfBoundsException(
					"Indice non valido: " + num + ". Deve essere compreso tra 0 e " + (mazziNoti.length - 1));
		}
		return mazziNoti[num];
	}

	public List<Carta> getMazzoCompleto() {
		List<Carta> mazzoCompleto = new ArrayList<>();
		for (int i = 0; i < mazziNoti.length; i++) {
			mazzoCompleto.addAll(mazziNoti[i]);
		}
		mazzoCompleto.addAll(mazzoIgnoto);
		Collections.shuffle(mazzoCompleto);
		return mazzoCompleto;
	}

	private List<PioggiaDiMeteoriti> generaMeteoriti(LivelliPartita lvl) {

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

	/**
	 * Si occupa di generare delle carte pianeti con caratteristiche differenti in
	 * base al livello della partita.
	 * 
	 * @param livello
	 * @return carte: lista della carte generate
	 */
	private List<CartaPianeti> generaCartaPianeti(LivelliPartita livello) {
		List<CartaPianeti> carte = new ArrayList<>();
		int numeroCarte = TipoCarta.PIANETI.getNumeroCarte(livello);

		int giorni = switch (livello) {
		case LIVELLO_1 -> random.randomInt(1, 4);
		case LIVELLO_2 -> random.randomInt(2, 5);
		case LIVELLO_3 -> random.randomInt(2, 6);
		};

		int minimoMerci = 1;
		if (livello == LivelliPartita.LIVELLO_3)
			minimoMerci = 2;

		for (int c = 0; c < numeroCarte; c++) {
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
			carte.add(new CartaPianeti(pianeti, giorni));
		}
		return carte;
	}

	/**
	 * Associa ad ogni elemento dell'enum colore una probabilità
	 * 
	 * @param colorePianeta
	 * @return probabilita: l'insieme dei valori dell'enum Colore, a cui è stata
	 *         associata una probabilità in base al colore
	 */
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

	private List<Nemico> generaNemici(LivelliPartita lvl) {
		// Istanzio gli schiavisti
		final Schiavisti[] schiavisti = new Schiavisti[3];
		schiavisti[0] = new Schiavisti(6, 5, 1, 3);
		schiavisti[1] = new Schiavisti(7, 8, 2, 4);
		schiavisti[2] = new Schiavisti(8, 10, 2, 5);

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
		pirati[2] = new Pirati(10, 12, 2, colpiPirati[2]);

		// Istanzio i contrabbandieri
		@SuppressWarnings("unchecked")
		final List<TipoMerce>[] merci = (List<TipoMerce>[]) new List[3];
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

	private List<PolvereStellare> generaPolvereStellare(LivelliPartita lvl) {
		List<PolvereStellare> out = new ArrayList<>();
		final int penalitaTempoVoloPerConnettoreEsposto = 1;
		PolvereStellare polvereStellare = null;

		switch (lvl) {
		case LIVELLO_1: {
			polvereStellare = new PolvereStellare(penalitaTempoVoloPerConnettoreEsposto);
			break;
		}
		case LIVELLO_2: {
			polvereStellare = new PolvereStellare(penalitaTempoVoloPerConnettoreEsposto);
			break;
		}
		case LIVELLO_3: {
			// non ho bisogno di fare nulla nel default, nel livello tre non ci sono carte
			// di questo tipo
			break;
		}
		}

		if (polvereStellare != null)
			out.add(polvereStellare);

		return out;
	}

	private List<Sabotaggio> generaSabotaggio(LivelliPartita lvl) {
		List<Sabotaggio> out = new ArrayList<>();

		switch (lvl) {
		case LIVELLO_1, LIVELLO_2:
			// la carta è presente solo a livello 3
			break;
		case LIVELLO_3:
			out.add(new Sabotaggio());
			break;
		}
		return out;
	}

	private List<Epidemia> generaEpidemia(LivelliPartita livello) {
		List<Epidemia> lista = new ArrayList<>();
		switch (livello) {
		// La carta epidemia non esiste per il livello 1
		case LIVELLO_1:
			break;
		case LIVELLO_2, LIVELLO_3:
			lista.add(new Epidemia());
			break;
		}
		return lista;
	}

	private List<NaveAbbandonata> generaNaveAbbandonata(LivelliPartita livello) {
		final int NUMERO_NAVI_ABBANDONATE = 2; // in tutti i livelli ci sono 2 navi abbandonate
		final int MIN_EQUIPAGGIO = 2; // equipaggio minimo da perdere
		final int MIN_CREDITI = 3; // crediti minimi

		List<NaveAbbandonata> lista = new ArrayList<NaveAbbandonata>();
		int crediti, tempoDiVolo, equipaggioPerso;

		for (int i = 0; i < NUMERO_NAVI_ABBANDONATE; i++) {

			equipaggioPerso = random.randomInt(MIN_EQUIPAGGIO * livello.getNumeroLivello(),
					(MIN_EQUIPAGGIO * livello.getNumeroLivello()) + 2);
			crediti = random.randomInt(MIN_CREDITI * livello.getNumeroLivello(),
					MIN_CREDITI * livello.getNumeroLivello() + 3);
			tempoDiVolo = random.randomInt(1, 2);

			lista.add(new NaveAbbandonata(crediti, tempoDiVolo, equipaggioPerso));
		}
		return lista;
	}
	

}
