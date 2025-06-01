package model.carte;

import java.util.Objects;

import controller.partita.fasiGioco.volo.ManagerDiVolo;
import model.carte.criteriEffetti.Effetto;
import view.formattatori.FormattatoreGrafico;
import view.io.GestoreIO;
import view.renderer.CarteRenderer;

public class NaveAbbandonata extends Carta{
	
	private final int crediti;
	private final int tempoDiVolo;
	private final int equipaggioPerso;
	private final Effetto effetto = Effetto.GUADAGNA_CREDITI;
	
	
	/**
	 * @param int crediti guadagnati
	 * @param int tempo di volo perso se si prendono i crediti
	 * @param int equipaggio perso
	 * @return true se le merci sono state posizionate correttamente, false altrimenti
	 */
	public NaveAbbandonata(int crediti, int tempoDiVolo, int equipaggioPerso) {
		super(TipoCarta.NAVE_ABBANDONATA);
		
		this.crediti = crediti;
		this.tempoDiVolo = tempoDiVolo;
		this.equipaggioPerso = equipaggioPerso;
	}
	
	@Override
	public void eseguiEvento(ManagerDiVolo[] listaManager) {
		GestoreIO io = new GestoreIO();
		CarteRenderer renderer = new CarteRenderer();
		FormattatoreGrafico formattatoreGrafico = new FormattatoreGrafico();
		
		renderer.rappresentaCarta(this);
		for(ManagerDiVolo manager : listaManager) {
			if(manager.getGiocatore().getNave().getEquipaggio().size() >= equipaggioPerso) {
				io.stampa(formattatoreGrafico.formatta(manager.getGiocatore()) + " premere 1 per prendere i crediti.");
				if(io.leggiIntero() == 1) {
					effetto.applica(manager, crediti);
					Effetto.GIORNI_VOLO.applica(manager, -tempoDiVolo);
					io.stampa("carta Nave Abbandonata risolta");
					io.aCapo();
					return;
				}
				
			}else {
				io.stampa(formattatoreGrafico.formatta(manager.getGiocatore()) + " equipaggio insufficiente");
			}
		}	
	}

	public int getCrediti() {
		return crediti;
	}

	public int getTempoDiVolo() {
		return tempoDiVolo;
	}

	public int getEquipaggioPerso() {
		return equipaggioPerso;
	}

	public Effetto getEffetto() {
		return effetto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(crediti, effetto, equipaggioPerso, tempoDiVolo);
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
		NaveAbbandonata other = (NaveAbbandonata) obj;
		return crediti == other.crediti && effetto == other.effetto && equipaggioPerso == other.equipaggioPerso
				&& tempoDiVolo == other.tempoDiVolo;
	}	
}
