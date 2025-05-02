package model.carte;

import java.util.ArrayList;
import java.util.List;

import grafica.FormattatoreGrafico;
import grafica.renderer.CarteRenderer;
import model.enums.TipoMerce;
import partita.fasiGioco.volo.ManagerDiVolo;
import io.GestoreIO;
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
		GestoreIO io = new GestoreIO();
		CarteRenderer renderer = new CarteRenderer();
		FormattatoreGrafico formattatoreGrafico = new FormattatoreGrafico();
		
		renderer.rappresentaCarta(this);
		for(ManagerDiVolo manager : listaManager) {
			if(manager.getGiocatore().getNave().getEquipaggio().size() >= numEquipaggio) {
				io.stampa(formattatoreGrafico.formattaGiocatore(manager.getGiocatore()) + " premere 1 per prendere la merce.");
				if(io.leggiIntero() == 1) {
					effetto.applica(manager, merci);
					Effetto.GIORNI_VOLO.applica(manager, tempoDiVolo);
					io.stampa("carta Stazione Abbandonata risolta");
					io.aCapo();
					return;
				}
				
			}else {
				io.stampa(formattatoreGrafico.formattaGiocatore(manager.getGiocatore()) + " equipaggio insufficiente per prendere la merce.");
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

}
