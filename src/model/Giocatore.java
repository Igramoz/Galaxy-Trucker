package model;

import java.util.Arrays;

import componenti.Componente;

public class Giocatore {
	private static int giocatoriMax = 4; // Numero massimo di giocatori
	public static int numeroGiocatori = 0;
	
	// TODO assegnare colore
	// TODO assegnare titolo
	private final String nome;
	
	private Nave nave;
    private Componente[] componentiPrenotati = new Componente[2]; // TODO ha senso questo quà
    private int pezziDistrutti = 0; 
	
	private int crediti = 0;
	
	
	
	public Giocatore(String nome) {
		if(numeroGiocatori >= giocatoriMax) {
			throw new IllegalStateException("Si può giocare massimo in 4");
		}
		this.nome = nome;
		this.nave = new Nave();
		
		numeroGiocatori++;
	}
	
	public String getNome() {
		return nome;
	}
	
	public Nave getNave() {
		// NON CREO UNA COPIA della nave, se creassi una copia, il gioco non potrebbe più modificare quella originale
		// la nave ha i suoi attributi protetti correttamente.
		return nave;		
	}
		
	// Metodi componenti prenotati
    public boolean setComponentiPrenotati(Componente componente) {
        for (int i = 0; i < componentiPrenotati.length; i++) {
            if (componentiPrenotati[i] == null) {
                componentiPrenotati[i] = componente;
                return true;
            }
        }
        return false;
    }
	
    public Componente[] getComponentiPrenotati() {
        return Arrays.copyOf(componentiPrenotati, componentiPrenotati.length);
    }
    
    public boolean rimuoviComponentiPrenotati(Componente componente) {
        for (int i = 0; i < componentiPrenotati.length; i++) {
            if (componente.equals(componentiPrenotati[i])) {
                componentiPrenotati[i] = null;
                return true;
            }
        }
        return false;
    }
    
    // Metodi pezzi distrutti
    public int getPezziDistrutti() {
    	return pezziDistrutti;
    }
    
    public void incrementaPezziDistrutti() {
        pezziDistrutti++;
    }
    
    // Metodi crediti
    public void aggiungiCrediti(int crediti) {
    	this.crediti += crediti;
    }
    
    public int getCrediti() {
    	return crediti;
    }
    
}
