package view.io;

import java.util.List;

import model.componenti.Componente;
import util.layout.Coordinate;

/**
 * Interfaccia per la gestione dell'input e output dell'utente.
 * Fornisce metodi per favorire l'interazione con l'utente.
 */
public interface InterfacciaUtente {

	// Metodi da chiamare all'inizio del gioco e alla fine del gioco
	
	/**
	 * Metodo da chiamare all'inizio del gioco,
	 * stampa le informazioni essenziali per l'utente
	 * inizializza la classe che si occupa della grafica
	 */
	public void aperturaGioco();

	/**
	 * Metodo da chiamare alla fine del gioco.
	 */
	public void chiusuraGioco();

	
    // Metodi di stampa
	
	/**
	 * Metodo per stampare a schermo un riga di testo.
	 */
    void stampa(String riga);
    
	/**
	 * Stampa un array di stringhe, ciascuna stringa su una riga
	 */
    void stampa(String[] righeDaStampare);
    
	/**
	 * Stampa una lista di righe, ciascuna stringa su una riga
	 */
    void stampa(List<String> righeDaStampare);
    
    /**
     * Va a capo per migliorare la leggibilità
     */
    void aCapo();

    
    // Menu e selezione
    
	/**
	 * stampa il menu e riporta la risposta dell'utente sia compresa tra 0 e
	 * menu.length - 1
	 * 
	 * @param menu, ciascun elemento dell'array deve essere un'opzione
	 * @param il numero corrispondente all'azione scelta dall'utente
	 */
    int stampaMenu(String[] menu);
    
	/**
	 * Funzione per fare scegliere all'utente un valore di un enum
	 * 
	 * @param enumerato da stampare
	 * @param costante dell'enum scelta dall'utente
	 */
    <T extends Enum<T>> T scegliEnum(Class<T> enumerato);
    
	/**
	 * Permette all'utente di scegliere un componente da una lista
	 * 
	 * @param lista dal quale scegliere un componente
	 * @return il compoenente scelto dall'utente.
	 */
    Componente menuComponenti(java.util.List<Componente> componenti);
    
    
    // Input da utente
    
    /**
	 * Legge un numero intero dall'input dell'utente.
	 * 
	 * @return il numero intero inserito dall'utente.
	 */
    int leggiIntero();
    
	/**
	 * Legge un input dell'utente, controlla che non sia vuoto
	 * 
	 * @return la stringa inserita dall'utente. 
	 */
    String leggiTesto();
    
	/**
	 * Legge le coordinate X e Y da input dell'utente.
	 * 
	 * @return un oggetto Coordinate con le coordinate lette (0 based).
	 */
    Coordinate leggiCoordinate();
    
	/**
	 * Metodo che permette di leggere un booleano da input dell'utente,
	 * si consiglia di usarlo per lasciare all'utente la scelta di eseguire o meno un'azione.
	 * 
	 * @return vero se l'utente ha deciso di eseguire l'azione
	 */
    boolean leggiBoolean();

}
