package model.nave;

import java.util.ArrayList;
import java.util.List;

import grafica.FormattatoreGrafico;
import io.GestoreIO;
import model.componenti.Componente;
import model.enums.TipoComponente;
import model.enums.TipoMerce;
import util.Coordinate;

public interface GestoreComponenti {
// interfaccia che interagisce con i setter e i getter per gestire l'inserimento o la rimozione di merci/energia/equipaggio dalla nave

	// funzione per scegliere un componente della nave
	default Coordinate scegliComponente(Nave nave, TipoComponente tipoComponente1, TipoComponente tipoComponente2) {

		GestoreIO io = new GestoreIO();
		FormattatoreGrafico formattatoreGrafico = new FormattatoreGrafico();
		List<Componente> componenti = nave.getComponenti(tipoComponente1);
		if (tipoComponente2 != null) {
			componenti.addAll(nave.getComponenti(tipoComponente2));
		}
		componenti.addAll(nave.getComponenti(tipoComponente2));

		String[] menu = new String[componenti.size()];
		for (int i = 0; i < componenti.size(); i++) {
			menu[i] = componenti.get(i).getTipo().name() + " "
					+ formattatoreGrafico.formattaCoordinate(componenti.get(i).getPosizione());
		}

		io.stampa("Scegli il componente in base alla posizione");
		int scelta = io.stampaMenu(menu);

		return componenti.get(scelta).getPosizione();
	}

	default Coordinate scegliComponente(Nave nave, TipoComponente tipoComponente1) {
		return scegliComponente(nave, tipoComponente1, null);
	}
	

	default List<Coordinate> posizionaMerciInStiva(Nave nave,List<TipoMerce> merci) {
				
		if(merci.contains(TipoMerce.ROSSO)) {
			List<TipoMerce> merciRosse = new ArrayList<>();

		    for (TipoMerce merce : merci) {
		        if (merce == TipoMerce.ROSSO) {
		            merciRosse.add(merce);
		        }
		    }
		    
		    
			// scegli dove posizionare la merce Rossa:
		    scegliComponente(nave, TipoComponente.STIVA_SPECIALE);
		}
		
		// TODO fare scegliere all'utente la posizione della merce, se la posizione è piena deve poter scegliere un altra posizione o rimuovere la merce
		return null;
	}
	
	
}
