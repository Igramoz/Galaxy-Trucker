package model.carte;

import java.util.List;

import grafica.renderer.CarteRenderer;
import model.enums.TipoMerce;
import partita.fasiGioco.ManagerDiVolo;
import io.GestoreIO;

public class StazioneAbbandonata extends Carta {

	private final List<TipoMerce> merci;
	private final int tempoDiVolo;
	private final int numEquipaggio;

	public StazioneAbbandonata(TipoCarta tipoCarta, List<TipoMerce> merci, int tempoDiVolo, int numEquipaggio) {
		super(tipoCarta);
		this.merci = merci;
		this.tempoDiVolo = tempoDiVolo;
		this.numEquipaggio = numEquipaggio;
	}

	@Override
	public void eseguiEvento(ManagerDiVolo[] listaManager) {
		GestoreIO io = new GestoreIO();
		CarteRenderer renderer = new CarteRenderer();
		
		io.stampa(renderer.rappresentaStazioneAbbandonata());
		// TODO implementare
		
	}

	public List<TipoMerce> getMerci() {
		return merci;
	}

	public int getTempoDiVolo() {
		return tempoDiVolo;
	}
	
	public int getNumEquipaggio() {
		return numEquipaggio;
	}

}
