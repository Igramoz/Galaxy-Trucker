package model;

import java.util.Arrays;
import java.util.List;

import grafica.Colore;
import nave.Nave;
import nave.TipoNave;

public class Giocatore  {
	private final static int giocatoriMax = 4; // Numero massimo di giocatori
	public static int numeroGiocatori = 0;
	public static final List<Colore> coloriDisponibiliGiocatori = Arrays.asList(Colore.ROSSO, Colore.BLU, Colore.VERDE,
			Colore.GIALLO);

	private final String nome;
	private final Colore colore;
	private int pezziDistrutti = 0;

	// TODO assegnare titolo
	private Nave nave;

	private int crediti = 0;

	public Giocatore(String nome, Colore colore) {
		if (numeroGiocatori >= giocatoriMax) {
			throw new IllegalStateException("Si può giocare massimo in 4");
		}
		if (!coloriDisponibiliGiocatori.contains(colore)) {
			throw new IllegalArgumentException("Colore non valido. Deve essere uno tra: " + coloriDisponibiliGiocatori);
		}

		this.nome = nome;
		this.colore = colore;

		numeroGiocatori++;
	}

	public String getNome() {
		return nome;
	}

	public Colore getColore() {
		return colore;
	}

	public void setNave(TipoNave tipoNave) {
		if (this.nave != null) {
			throw new IllegalStateException("La nave è già stata assegnata");
		}
		this.nave = new Nave(tipoNave, colore);
	}

	public Nave getNave() {
		// NON CREO UNA COPIA della nave, se creassi una copia, il gioco non potrebbe
		// più modificare quella originale
		// la nave ha i suoi attributi protetti correttamente.
		return nave;
	}

    // Metodi pezzi distrutti
    public int getPezziDistrutti() {
    	return this.pezziDistrutti;
    }
    
    public void incrementaPezziDistrutti(int numPezziDistrutti) {
        this.pezziDistrutti += numPezziDistrutti;
    }
    
	// Metodi crediti
	public void aggiungiCrediti(int crediti) {
		this.crediti += crediti;
	}
	
	public void azzeraPezziDistrutti() {
		this.pezziDistrutti = 0;
	}

	public int getCrediti() {
		return crediti;
	}

	public static void resetNumeroGiocatori() {
		numeroGiocatori = 0;
	}
}
