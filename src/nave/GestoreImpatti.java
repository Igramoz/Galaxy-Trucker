package nave;

import model.Coordinate;
import model.colpi.TipiMeteorite;
import model.enums.TipoTubo;
import model.enums.Direzione;
import util.Coppia;

public interface GestoreImpatti {
	// l'interfaccia controlla se il colpo distrugge o meno la nave

	
    default boolean subisciImpatto(Nave nave, Coppia<TipiMeteorite, Direzione> meteorite, Coordinate coordinate) {
    	
    	// Controllo che tipo di meteorite è
    	if(meteorite.getElemento1() == TipiMeteorite.PICCOLO) {
    		return colpiPiccoli(nave, meteorite.getElemento2(), coordinate);
    	}
    	
    	return true;
    }
    
    // TODO cannonata
    //default int subisciImpatto(Nave nave, Coppia<TipiMeteorite, Direzione> meteorite, Coordinate coordinate) {ù
    
    private boolean colpiPiccoli(Nave nave, Direzione direzione, Coordinate coordinate) {
    	if(nave.getComponente(coordinate).getTubo(direzione) == TipoTubo.NESSUNO) return false;
    	
    	return true;
    }
}
