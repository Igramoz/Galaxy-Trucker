package grafica;

import model.Giocatore;
import model.enums.TipoMerce;
import model.enums.TipoPedina;
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
	
	public String formattaPedina(TipoPedina tipoPedina) {
		return tipoPedina.getColore().getCodice() + tipoPedina.name() + Colore.DEFAULT.getCodice();
	}
	
	public String formattaMerce(TipoMerce merce) {
		return merce.getColore().getCodice() + merce.name() + Colore.DEFAULT.getCodice();
	}
	
}
