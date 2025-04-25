package model.componenti;

import java.util.Map;

import model.enums.Direzione;
import model.enums.TipoTubo;


public class GeneratoreDiScudi extends Componente {

	private Direzione[] direzione = new Direzione[2]; // array di direzioni degli scudi

	public GeneratoreDiScudi(Map<Direzione, TipoTubo> tubiIniziali) {

		super(TipoComponente.SCUDO, tubiIniziali);

		this.direzione[0] = Direzione.SOPRA;
		this.direzione[1] = Direzione.SINISTRA;
	}

	public GeneratoreDiScudi(Map<Direzione, TipoTubo> tubiIniziali, Direzione[] direzione) {

		super(TipoComponente.SCUDO, tubiIniziali);
		// controllo che l'array di direzioni sia lungo 2 e che le direzioni siano
		// valide

		if (direzione.length != 2 || direzione[0] == null || direzione[1] == null || checkDirezioni(direzione)) {
			throw new IllegalArgumentException("L'array di direzioni è errato");
		}

		this.direzione[0] = direzione[0];
		this.direzione[1] = direzione[1];
	}

	public GeneratoreDiScudi(GeneratoreDiScudi g) { // costruttore di copia

		super(g);

		this.direzione[0] = g.direzione[0];
		this.direzione[1] = g.direzione[1];
	}

	public Direzione[] getDirezioni() {
		return new Direzione[] { direzione[0], direzione[1] };
	}

	@Override
	public void ruota() {

		super.ruota();

		this.direzione[0] = direzione[0].ruota();
		this.direzione[1] = direzione[1].ruota();
	}

	@Override
	public GeneratoreDiScudi clone() {
		return new GeneratoreDiScudi(this);
	}

	private boolean checkDirezioni(Direzione[] direzioni) {
		// Controlla che le direzioni siano valide (non possono essere uguali e non
		// possono essere opposte)
		return !((direzioni[0] == direzioni[1]) || // UGUALI
				(direzioni[0] == Direzione.SOPRA && direzioni[1] == Direzione.SOTTO)
				|| (direzioni[0] == Direzione.SOTTO && direzioni[1] == Direzione.SOPRA)
				|| (direzioni[0] == Direzione.SINISTRA && direzioni[1] == Direzione.DESTRA)
				|| (direzioni[0] == Direzione.DESTRA && direzioni[1] == Direzione.SINISTRA));

	}

}
