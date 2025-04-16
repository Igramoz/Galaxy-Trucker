package grafica;

import model.Giocatore;

public class FormattatoreGrafico {
	
	public String formattaGiocatore(Giocatore giocatore) {
		return giocatore.getColore().getCodice()+ giocatore.getNome() + Colore.DEFAULT.getCodice();
	}
	
}
