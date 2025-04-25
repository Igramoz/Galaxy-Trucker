package grafica;

import model.Giocatore;
import util.Coordinate;

public class FormattatoreGrafico {

	public String formattaGiocatore(Giocatore giocatore) {
		return giocatore.getColore().getCodice() + giocatore.getNome() + Colore.DEFAULT.getCodice();
	}

	// ( x , y )
	public String formattaCoordinate(Coordinate coord) {
		return "( " + coord.getX() + " , " + coord.getY() + " )";
	}

	public String formattaColore(Colore colore) {
		String str = colore.toString().toLowerCase();
		return Character.toUpperCase(str.charAt(0)) + str.substring(1);
	}
}
