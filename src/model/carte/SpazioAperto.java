package model.carte;
import partita.fasiGioco.ManegerDiVolo;





public class SpazioAperto extends Carta {
	
	public SpazioAperto() {
		super(TipoCarta.SPAZIO_APERTO);
	}
	
	@Override
	public void eseguiEvento(ManegerDiVolo[] managerDiVolo) {
		
		for (ManegerDiVolo manager : managerDiVolo) {
			
			super.io.stampa("Turno di " + super.formattatoreGrafico.formattaGiocatore(manager.getGiocatore()) + " - Spazio Aperto");
			
			super.io.aCapo();
			super.io.stampa(super.naveRenderer.rappresentazioneNave(manager.getGiocatore().getNave()));
			
			
			int potenzaMotrice = manager.getGiocatore().getNave().getPotenzaMotrice();
			
			manager.spostaGiocatore(potenzaMotrice);

		}
	}
	
}
