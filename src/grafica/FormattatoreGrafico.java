package grafica;

import model.Coordinate;
import model.Giocatore;

public class FormattatoreGrafico {
	
	public String formattaGiocatore(Giocatore giocatore) {
		return giocatore.getColore().getCodice()+ giocatore.getNome() + Colore.DEFAULT.getCodice();
	}
	
	public String formattaCoordinate(Coordinate coord) {
		return "( " + coord.getX() + " , " + coord.getY() + " )";
	}
	
}
