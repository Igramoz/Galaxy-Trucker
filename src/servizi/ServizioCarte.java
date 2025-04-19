package servizi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import util.*;
import cartaAvventura.*;
import model.colpi.TipiMeteorite;
import model.enums.Direzione;
import partita.LivelliPartita;

public class ServizioCarte {
	// Genera randomicamente le carte

	private List<Carta> carteGenerate = new ArrayList<>();
	private RandomUtil random = new RandomUtil();

	public ServizioCarte() {
		generaCarte();
		Collections.shuffle(carteGenerate);
	}

	private void generaCarte() {

	}

	// TODO mettere privato
	public List<PioggiaDiMeteoriti> generaMeteoriti(LivelliPartita lvl) {
		
		/*livello Nasteroidi rapporto Grandi su totali
		*		1	3 - 4 		2 su 12
		*		2	4 - 5 		4 su 14
		*		3	5 - 6 		6 su 16
		 */ 
		
		
	    final int asteroidiTotaliLivelloBase = 10; // Asteroidi totali nel livello base
		
		int nAsteoridi = 2 + lvl.getNumeroLivello();
		int nAsteroidiGrandi = 2 * lvl.getNumeroLivello();
		int asteroidiTotali = asteroidiTotaliLivelloBase + nAsteroidiGrandi;
		
	    // Numero di asteroidi da generare in base al livello
		int asteroidiDaGenerare = random.randomInt(nAsteoridi, nAsteoridi + 2);
		List<PioggiaDiMeteoriti> out = new ArrayList<>();
		
		for(int nCarta = 0; nCarta < TipoCarta.PIOGGIA_DI_METEORITI.getNumeroCarte(lvl); nCarta ++) {
			
			List<Coppia<TipiMeteorite, Direzione>> listaAsteroidi = new ArrayList<>();
			for(int nAsteroide = 0; nAsteroide < asteroidiDaGenerare; nAsteroide++) {
				
	            // Sceglie casualmente un meteorite grosso o piccolo
				TipiMeteorite temp = random.randomEnum(nAsteroidiGrandi, asteroidiTotali, TipiMeteorite.GROSSO, TipiMeteorite.PICCOLO);
				
	            // Aggiungi un meteorite alla lista
				listaAsteroidi.add( new Coppia<TipiMeteorite, Direzione>(temp, random.randomDirezione()));
				
			}
			
			Util.ordinaPerDirezione(listaAsteroidi);
			out.add( new PioggiaDiMeteoriti(listaAsteroidi));
		}
		
		return out;
	}
	

}
