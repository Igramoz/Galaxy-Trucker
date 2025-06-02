package view.formattatori;

import java.util.List;

import model.enums.TipoMerce;
import util.layout.Coordinate;
import view.Colore;
import view.GraficaConfig;
import view.io.GestoreIO;

public class FormattatoreGrafico {

	private GestoreIO io = new GestoreIO();

	/**
	 * Formatta delle coordinate in una stringa leggibile.
	 * 
	 * @param le coordinate da formattare
	 * @return la stinga ( x , y ) che indica le coordinate
	 */
	public String formatta(Coordinate coord) {
		return "( " + (coord.getX() + GraficaConfig.OFFSET) + " , " + (coord.getY() + GraficaConfig.OFFSET) + " )";
	}

	/**
	 * Formatta qualsiasi classe che implementa l'interfaccia formattabile in una stringa leggibile.
	 * 
	 * 
	 * @param l'oggetto da formattare
	 * @return la stringa che rappresenta il nome dell'oggetto colorato
	 */
	public <T extends Formattabile> String formatta(T formattabile) {
		return formattabile.getColore().getCodice() + formattabile.getNome() + Colore.DEFAULT.getCodice();
	}

	// non posso fare overload perché i generici vengono tolti in compilazione
	// quindi non disingue quela formatta e stampa
	public void formattaEStampaMerci(List<TipoMerce> merci) {
		String out = "";
		for (TipoMerce merce : merci) {
			out += formatta(merce) + " ";
		}
		io.stampa(out);
	}

}
