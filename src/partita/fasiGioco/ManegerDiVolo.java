package partita.fasiGioco;

import model.Giocatore;
import model.planciaDiVolo.*;



public class ManegerDiVolo {
	
	private final Giocatore giocatore;
	private int giorniDiVolo;
	private final Plancia plancia;
	
	public ManegerDiVolo(Giocatore giocatore, Plancia plancia) {
		this.giocatore = giocatore;
		this.plancia = plancia;
		this.giorniDiVolo = plancia.getGiorniDiVoloGiocatore(giocatore); // inizializza i giorni di volo del giocatore in base alla plancia
		
	}

	public boolean spostaGiocatore(int giorniDiVolo){

		return plancia.spostaGiocatore(giorniDiVolo, giocatore);

	}

	public int getGiorniDiVoloGiocatore(){
		return plancia.getGiorniDiVoloGiocatore(giocatore); // restituisce i giorni di volo del giocatore
	}

	public Giocatore getGiocatore() {
		return giocatore;
	}
	

}
