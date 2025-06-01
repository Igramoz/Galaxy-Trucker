package model.carte;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import controller.partita.fasiGioco.volo.ManagerDiVolo;
import model.enums.TipoMerce;
import view.formattatori.FormattatoreGrafico;
import view.io.GestoreIO;
import view.renderer.CarteRenderer;
import model.carte.criteriEffetti.Effetto;

public class StazioneAbbandonata extends Carta {

	private final List<TipoMerce> merci;
	private final int tempoDiVolo;
	private final int numEquipaggio;
	private final Effetto effetto = Effetto.GUADAGNA_MERCE;
	
	public StazioneAbbandonata(List<TipoMerce> merci, int tempoDiVolo, int numEquipaggio) {
		super(TipoCarta.STAZIONE_ABBANDONATA);
		
		this.merci = merci;
		this.tempoDiVolo = tempoDiVolo;
		this.numEquipaggio = numEquipaggio;
	}

	@Override
	public void eseguiEvento(ManagerDiVolo[] listaManager) {
		carteRenderer.rappresentaCarta(this);
		
		for(ManagerDiVolo manager : listaManager) {
			if(manager.getGiocatore().getNave().getEquipaggio().size() >= numEquipaggio) {
				io.stampa(formattatoreGrafico.formatta(manager.getGiocatore()) + " premere 1 per prendere la merce.");
				if(io.leggiIntero() == 1) {
					effetto.applica(manager, merci);
					Effetto.GIORNI_VOLO.applica(manager, tempoDiVolo);
					io.stampa("carta Stazione Abbandonata risolta");
					io.aCapo();
					return;
				}
				
			}else {
				io.stampa(formattatoreGrafico.formatta(manager.getGiocatore()) + " equipaggio insufficiente per prendere la merce.");
			}
		}
		
	}

	public List<TipoMerce> getMerci() {
	    return new ArrayList<>(merci);
	}

	public int getTempoDiVolo() {
		return tempoDiVolo;
	}

	public int getNumEquipaggio() {
		return numEquipaggio;
	}

	public Effetto getEffetto() {
		return effetto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(effetto, merci, numEquipaggio, tempoDiVolo);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		StazioneAbbandonata other = (StazioneAbbandonata) obj;
		return effetto == other.effetto && Objects.equals(merci, other.merci) && numEquipaggio == other.numEquipaggio
				&& tempoDiVolo == other.tempoDiVolo;
	}

}
