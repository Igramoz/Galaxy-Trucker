package componenti;

import java.util.Map;
import model.enums.Direzione;
import model.enums.TipoTubo;
import util.Util;

public class Cannone extends Componente {

	public static final int limiteIstanziabili = 36;
	public static int istanze = 0;
	private float potenzaFuoco;
	private Direzione direzione; // Direzione in cui il cannone spara

	public Cannone(TipoComponente tipo, Map<Direzione, TipoTubo> tubiIniziali) {
		super(tipo, tubiIniziali);
		if (istanze >= limiteIstanziabili) {
			throw new IllegalStateException("Limite massimo di istanze raggiunto per Cannone");
		}

		potenzaFuoco = gestisciPotenzaDiFuoco(tipo);
		if (potenzaFuoco == -1)
			return;
		direzione = Direzione.SOPRA;
	}

	private int gestisciPotenzaDiFuoco(TipoComponente tipo) {
		if (tipo == TipoComponente.CANNONE_SINGOLO)
			return 1;
		else if (tipo == TipoComponente.CANNONE_DOPPIO)
			return 2;
		else
			return -1; // -1 corrisponde ad un errore (TODO: è da gestire?)
	}

	@Override
	public void ruota() {
		Direzione nuovaDirezione = Util.ruotaDirezione(direzione);
		
		if (direzione == Direzione.SOPRA)
			potenzaFuoco /= 2;
		if (nuovaDirezione == Direzione.SOPRA)
			potenzaFuoco *= 2;

		super.ruota();
		this.direzione = nuovaDirezione;
	}

	public Direzione getDirezione() {
		return direzione;
	}

	public float getPotenzaFuoco() {
		return potenzaFuoco;
	}

	// TODO: da sistemare, la classe Cannone non dovrebbe stampare nulla, non spara e non toglie batterie
//	public boolean spara(int batterieDisponibili) {
//		// Solo il cannone doppio necessita di batteria
//		if (tipo == TipoComponente.CANNONE_DOPPIO) {
//			if (batterieDisponibili < 1) {
//				System.out.println("Impossibile sparare, batteria insufficiente"); // TODO: ECCEZIONE
//				return false;
//			} else {
//				// TODO: bisogna diminuire la batteria di 1
//			}
//		}
//		System.out.println("Il cannone ha sparato con potenza: " + potenzaFuoco); // TODO: non stampare dalla
//																					// classe
//		return true;
//	}

	@Override
	public int getIstanze() {
		return istanze;
	}

	@Override
	protected void incrementaIstanze() {
		istanze++;
	}

	@Override
	public void resetIstanze() {
		istanze = 0;

	}

	@Override
	protected void decrementaIstanze() {
		istanze--;
	}
}