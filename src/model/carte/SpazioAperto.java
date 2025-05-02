package model.carte;
import partita.fasiGioco.volo.ManagerDiVolo;





public class SpazioAperto extends Carta {
	
	public SpazioAperto() {
		super(TipoCarta.SPAZIO_APERTO);
	}
	
	@Override
	public void eseguiEvento(ManagerDiVolo[] managerDiVolo) {
		
		for (ManagerDiVolo manager : managerDiVolo) {
			
			super.io.stampa("Turno di " + super.formattatoreGrafico.formattaGiocatore(manager.getGiocatore()) + " - Spazio Aperto");
			
			super.io.aCapo();
			super.io.stampa(super.naveRenderer.rappresentazioneNave(manager.getGiocatore().getNave()));
			
			
			int potenzaMotrice = manager.getGiocatore().getNave().getPotenzaMotrice();
			
			manager.spostaGiocatore(potenzaMotrice);

		}
	}
	
}
