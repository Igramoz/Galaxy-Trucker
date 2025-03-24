package componenti;

import java.util.Map;
import model.enums.Direzione;
import model.enums.TipoTubo;

public class Cannone extends Componente {

	private final float potenzaFuoco;
	private Direzione direzione; // Direzione in cui il cannone spara
	private boolean integro; // true = integro false = distrutto

	public Cannone(TipoComponente tipo, Map<Direzione, TipoTubo> tubiIniziali, Direzione direzioneIniziale) {
		super(tipo, tubiIniziali);
		potenzaFuoco = gestisciPotenzaDiFuoco(tipo);
		if (potenzaFuoco == -1)
			return;
		direzione = direzioneIniziale;
		integro = true;
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
			System.out.println("Rotazione bloccata! Il cannone colpirebbe un altro componente ");
			return; // Blocca la rotazione
		}

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

	public boolean isIntegro() {
		return integro;
	}

	public void distruggi() {
		integro = false;
	}

	public float getPotenzaEffettiva() {
		// Se il cannone non è rivolto in avanti, la potenza è dimezzata
		return (direzione == Direzione.SOPRA) ? potenzaFuoco : potenzaFuoco / 2.0f;
	}

	public boolean spara(int batterieDisponibili) {
		if (!integro)
			return false;

		// Solo il cannone doppio necessita di batteria
		if (tipo == TipoComponente.CANNONE_DOPPIO) {
			if (batterieDisponibili < 1) {
				System.out.println("Impossibile sparare, batteria insufficiente");
				return false;
			} else {
				// TODO: bisogna diminuire la batteria di 1
			}
		}
		System.out.println("Il cannone ha sparato con potenza: " + getPotenzaEffettiva());
		return true;
	}
}