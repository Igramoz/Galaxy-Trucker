package model.componenti;

import java.util.Map;

import model.enums.TipoTubo;
import util.layout.Direzione;

public class Cannone extends Componente {

	protected float potenzaFuoco;
	protected Direzione direzioneFuoco; // Direzione in cui il cannone spara

	public Cannone(Map<Direzione, TipoTubo> tubiIniziali) {
		this(TipoComponente.CANNONE_SINGOLO, tubiIniziali, Direzione.SOPRA);
		potenzaFuoco = 1;
	}

	protected Cannone(TipoComponente tipo, Map<Direzione, TipoTubo> tubiIniziali, Direzione direzioneFuoco) {
		super(tipo, tubiIniziali);
		this.direzioneFuoco = direzioneFuoco;
		this.tubi.put(direzioneFuoco, TipoTubo.NESSUNO); // Non può avere tubi in direzione di fuoco
		this.aggiornaPotenzaFuoco();
	}

	public Cannone(Cannone can) {
		this(can.tipo, can.tubi, can.direzioneFuoco);
	}

	protected void aggiornaPotenzaFuoco() {
		if (this.direzioneFuoco == Direzione.SOPRA) {
			potenzaFuoco = 1.0f;
		} else {
			potenzaFuoco = 0.5f;
		}
	}

	@Override
	public void ruota() {
		Direzione nuovaDirezione = direzioneFuoco.ruota();

		super.ruota();
		this.direzioneFuoco = nuovaDirezione;
		this.aggiornaPotenzaFuoco();
	}

	public Direzione getDirezioneFuoco() {
		return direzioneFuoco;
	}

	public float getPotenzaFuoco() {
		return potenzaFuoco;
	}

	@Override
	public Cannone clone() {
		return new Cannone(this);
	}
}