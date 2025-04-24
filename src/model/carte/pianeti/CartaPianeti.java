package model.carte.pianeti;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Giocatore;
import model.carte.Carta;
import model.enums.TipoCarta;
import model.enums.TipoMerce;
import util.RandomUtil;

public class CartaPianeti extends Carta {

	private final List<Pianeta> pianeti; // lista dei pianeti di questa carta

	public CartaPianeti(int numGiocatori) {
		super(TipoCarta.PIANETI);
		pianeti = generaPianeti(numGiocatori);
	}

	@Override
	public void eseguiEvento(Giocatore[] giocatori) {
		// TODO: gestisci la scelta dei pianeti da parte dei giocatori
		// (in ordine di rotta, una sola nave per pianeta)

		io.stampa("Pianeti disponibili: ");
		for (int i = 0; i < pianeti.size(); i++) {
			Pianeta p = pianeti.get(i);
			io.stampa("Pianeta " + (i + 1) + ": " + p.getGiorniDiVolo()
					+ " giorni di volo richiesti, merci disponibili: " + p.getMerciDisponibili());
		}
	}

	public List<Pianeta> getPianeti() {
		return pianeti;
	}

	private List<Pianeta> generaPianeti(int numGiocatori) {
		RandomUtil random = new RandomUtil();
		List<Pianeta> lista = new ArrayList<>();

		int numeroPianeti = random.randomInt(2, numGiocatori + 1); // +1 perché il numero max + escluso
		for (int i = 0; i < numeroPianeti; i++) {
			int giorni = random.randomInt(1, 4); // 1-3 giorni
			List<TipoMerce> merci = new ArrayList<>();

			int numeroMerci = random.randomInt(2, 6); // 2-5 merci
			for (int j = 0; j < numeroMerci; j++) {
				int index = random.randomInt(TipoMerce.values().length);
				TipoMerce merce = TipoMerce.values()[index];
				merci.add(merce);
			}

			lista.add(new Pianeta(giorni, merci));
		}
		return lista;
	}
}