package cartaAvventura;

import java.util.EnumMap;
import java.util.Map;

import partita.LivelliPartita;


public enum TipoCarta {
    PIANETI(4, 4, 4),
    STAZIONE_ABBANDONATA(2, 2, 2),
    NAVE_ABBANDONATA(2, 2, 2),
    CONTRABBANDIERI(1, 1, 1),
    SPAZIO_APERTO(4, 3, 3),
    PIOGGIA_DI_METEORITI(3, 3, 3),
    ZONA_DI_GUERRA(1, 1, 1),
    POLVERE_STELLARE(2, 1, 0),
    PIRATI(1, 1, 1),
    SCHIAVISTI(1, 1, 1),
    EPIDEMIA(0, 1, 1),
    SABOTAGGIO(0, 0, 1);
	
    private final Map<LivelliPartita, Integer> numeroCartePerLivello;
    private final int numeroTotaleCarte;
    
    // Costruttore effettivo (chiamato dall'altro costruttore)
    TipoCarta(Map<LivelliPartita, Integer> numeroCartePerLivello) {
        this.numeroCartePerLivello = numeroCartePerLivello;
        this.numeroTotaleCarte = calcolaTotaleCarte(numeroCartePerLivello);
    }
    
    // Costruttore creato per semplificare il salvataggio del numero di carte
    TipoCarta(int livello1, int livello2, int livello3) {
    	// Chiamo l'altro costruttore
        Map<LivelliPartita, Integer> mapTemporaneo = new EnumMap<>(LivelliPartita.class);
        mapTemporaneo.put(LivelliPartita.LIVELLO_1, livello1);
        mapTemporaneo.put(LivelliPartita.LIVELLO_2, livello2);
        mapTemporaneo.put(LivelliPartita.LIVELLO_3, livello3);
        this.numeroCartePerLivello = mapTemporaneo;
        this.numeroTotaleCarte = calcolaTotaleCarte(numeroCartePerLivello);
    }
    
    public int getNumeroCarte(LivelliPartita livello) {
        return numeroCartePerLivello.get(livello) ;
    }

    public Map<LivelliPartita, Integer> getNumeroCartePerLivello() {
        return numeroCartePerLivello;
    }
    
    private int calcolaTotaleCarte(Map<LivelliPartita, Integer> mappa) {
    	int counter = 0;    	
    	for(int numCarte : mappa.values()) {
    		counter += numCarte;
    	}    	
        return counter;
    }   
    
    public int getNumeroTotaleCarte() {
    	return numeroTotaleCarte;
    }
}
