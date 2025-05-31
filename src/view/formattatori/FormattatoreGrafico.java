package view.formattatori;


import model.enums.TipoMerce;
import util.layout.Coordinate;
import view.Colore;
import view.io.GestoreIO;

public class FormattatoreGrafico {

	private GestoreIO io = new GestoreIO();

	// ( x , y )
	public String formatta(Coordinate coord) {
		return "( " + coord.getX()+ GraficaConfig.OFFSET + " , " + coord.getY()+ GraficaConfig.OFFSET + " )";
	}
	
	public <T extends Formattabile> String formatta(T formattabile) {
		return formattabile.getColore().getCodice() + formattabile.getNome() + Colore.DEFAULT.getCodice();
	}
	
	// non posso fare overload perché i generici vengono tolti in compilazione quindi non disingue quela formatta e stampa
	public void formattaEStampaMerci(List<TipoMerce> merci) {
		String out = "";
		for (TipoMerce merce : merci) {
			out += formatta(merce) + " ";
		}
		io.stampa(out);
	}


}
