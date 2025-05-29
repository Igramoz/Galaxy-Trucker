package model.carte;

import java.util.Objects;

import controller.partita.fasiGioco.volo.ManagerDiVolo;
import view.TextAligner;
import view.formattatori.FormattatoreGrafico;
import view.io.GestoreIO;
import view.renderer.CarteRenderer;
import view.renderer.NaveRenderer;

public abstract class Carta {
	private final TipoCarta tipoCarta;
	protected final CarteRenderer carteRenderer = new CarteRenderer();
	protected final NaveRenderer naveRenderer = new NaveRenderer();
	protected final GestoreIO io = new GestoreIO();
	protected final FormattatoreGrafico formattatoreGrafico = new FormattatoreGrafico();
	protected final TextAligner textAligner = new TextAligner();

	public Carta(TipoCarta tipoCarta) {
		this.tipoCarta = tipoCarta;
	}

	public TipoCarta getTipoCarta() {
		return tipoCarta;
	}

	public abstract void eseguiEvento(ManagerDiVolo[] listaManager);

	@Override
	public int hashCode() {
		return Objects.hash(tipoCarta);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carta other = (Carta) obj;
		return tipoCarta == other.tipoCarta;
	}
}
