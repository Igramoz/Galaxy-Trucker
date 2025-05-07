package model.carte;
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
	
}
