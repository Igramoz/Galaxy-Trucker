package model.carte;
import java.util.Objects;

import model.carte.criteriEffetti.Effetto;
import partita.fasiGioco.volo.ManagerDiVolo;


public class SpazioAperto extends Carta {
	
	private final Effetto effetto = Effetto.GIORNI_VOLO;
	public SpazioAperto() {
		super(TipoCarta.SPAZIO_APERTO);
	}
	
	@Override
	public void eseguiEvento(ManagerDiVolo[] managerDiVolo) {
		
		for (ManagerDiVolo manager : managerDiVolo) {
			
			super.io.stampa("Turno di " + super.formattatoreGrafico.formatta(manager.getGiocatore()) + " - Spazio Aperto");
			
			super.io.aCapo();
			super.io.stampa(super.naveRenderer.rappresentazioneNave(manager.getGiocatore().getNave()));
			
			
			int potenzaMotrice = manager.getGiocatore().getNave().getPotenzaMotrice();
			
			effetto.applica(manager, potenzaMotrice);

		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(effetto);
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
		SpazioAperto other = (SpazioAperto) obj;
		return effetto == other.effetto;
	}
	
}
