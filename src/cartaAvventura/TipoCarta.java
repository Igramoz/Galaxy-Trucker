package cartaAvventura;

import java.util.EnumMap;
import java.util.Map;

import partita.LivelliPartita;


public enum TipoCarta {
    PIANETI,
    STAZIONE_ABBANDONATA,
    NAVE_ABBANDONATA,
    CONTRABBANDIERI,
    SPAZIO_APERTO(4, 3, 3),
    PIOGGIA_DI_METEORITI,
    ZONA_DI_GUERRA,
    POLVERE_STELLARE,
    PIRATI,
    SCHIAVISTI,
    EPIDEMIA,
    SABOTAGGIO;
	
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
}
