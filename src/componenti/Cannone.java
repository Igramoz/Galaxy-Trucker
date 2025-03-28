package componenti;

import java.util.Map;
import model.enums.Direzione;
import model.enums.TipoTubo;

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
		// Calcola la nuova direzione dopo la rotazione
		Direzione nuovaDirezione = switch (direzione) {
		case SOPRA -> Direzione.SINISTRA;
		case SINISTRA -> Direzione.SOTTO;
		case SOTTO -> Direzione.DESTRA;
		case DESTRA -> Direzione.SOPRA;
		};

		// Controlla se c'è un tubo nella nuova direzione
		if (super.getTubo(nuovaDirezione) != TipoTubo.NESSUNO) {
			System.out.println("Rotazione bloccata! Il cannone colpirebbe un altro componente"); // TODO: eccezione
			return; // Blocca la rotazione
		}

		if (direzione == Direzione.SOPRA)
			potenzaFuoco /= 2;
		if (nuovaDirezione == Direzione.SOPRA)
			potenzaFuoco *= 2;

		// Se non ci sono ostacoli, ruota normalmente
		super.ruota();
		this.direzione = nuovaDirezione;
	}

	public Direzione getDirezione() {
		return direzione;
	}

	public float getPotenzaFuoco() {
		return potenzaFuoco;
	}

	public boolean spara(int batterieDisponibili) {
		// Solo il cannone doppio necessita di batteria
		if (tipo == TipoComponente.CANNONE_DOPPIO) {
			if (batterieDisponibili < 1) {
				System.out.println("Impossibile sparare, batteria insufficiente"); // TODO: ECCEZIONE
				return false;
			} else {
				// TODO: bisogna diminuire la batteria di 1
			}
		}
		System.out.println("Il cannone ha sparato con potenza: " + potenzaFuoco); // TODO: non stampare dalla
																					// classe
		return true;
	}

	@Override
	public int getIstanze() {
		return istanze;
	}

	@Override
	protected void incrementaIstanze() {
		istanze++;
	}
	
	//@Override
	//public Componente creaCopia() {
	
	//return new Compo 
	//}
}