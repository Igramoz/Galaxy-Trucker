package model.carte;

import java.util.List;

import grafica.FormattatoreGrafico;
import grafica.renderer.CarteRenderer;
import model.enums.TipoMerce;
import partita.fasiGioco.ManagerDiVolo;
import io.GestoreIO;
import model.carte.criteriEffetti.Effetto;

public class StazioneAbbandonata extends Carta {

	private final List<TipoMerce> merci;
	private final int tempoDiVolo;
	private final int numEquipaggio;
	private final Effetto effetto = Effetto.GUADAGNA_MERCE;
	
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
		FormattatoreGrafico formattatoreGrafico = new FormattatoreGrafico();
		
		io.stampa(renderer.rappresentaStazioneAbbandonata());
		for(ManagerDiVolo manager : listaManager) {
			if(manager.getGiocatore().getNave().getEquipaggio().size() >= numEquipaggio) {
				io.stampa(formattatoreGrafico.formattaGiocatore(manager.getGiocatore()) + " premere 1 per prendere la merce.");
				if(io.leggiIntero() == 1) {
					effetto.applica(manager, merci);
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
		return merci;
	}

	public int getTempoDiVolo() {
		return tempoDiVolo;
	}

	public int getNumEquipaggio() {
		return numEquipaggio;
	}

}
