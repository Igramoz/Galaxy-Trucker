package model;

import java.util.EnumSet;

import grafica.Colore;
import nave.Nave;
import nave.TipoNave;

public class Giocatore {
	private final static int giocatoriMax = 4; // Numero massimo di giocatori
	public static int numeroGiocatori = 0;
    public final static EnumSet<Colore> coloriDisponibiliGiocatori = EnumSet.of(
            Colore.ROSSO,
            Colore.BLU,
            Colore.VERDE,
            Colore.GIALLO
        );

	private final String nome;
	private final Colore colore;
	
	// TODO assegnare titolo
	private Nave nave;
	
	private int crediti = 0;

	public Giocatore(String nome, Colore colore) {
		if(numeroGiocatori >= giocatoriMax) {
			throw new IllegalStateException("Si può giocare massimo in 4");
		}
		if(!coloriDisponibiliGiocatori.contains(colore)) {
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
		if(this.nave != null) {
			throw new IllegalStateException("La nave è già stata assegnata");
		}
		this.nave = new Nave(tipoNave, colore);
	}
	
	public Nave getNave() {
		// NON CREO UNA COPIA della nave, se creassi una copia, il gioco non potrebbe più modificare quella originale
		// la nave ha i suoi attributi protetti correttamente.
		return nave;		
	}
		
	// Metodi componenti prenotati TODO 
//    public boolean setComponentiPrenotati(Componente componente) {
//        for (int i = 0; i < componentiPrenotati.length; i++) {
//            if (componentiPrenotati[i] == null) {
//                componentiPrenotati[i] = componente;
//                return true;
//            }
//        }
//        return false;
//    }
//	
//    public Componente[] getComponentiPrenotati() {
//        return Arrays.copyOf(componentiPrenotati, componentiPrenotati.length);
//    }
//    
//    public boolean rimuoviComponentiPrenotati(Componente componente) {
//        for (int i = 0; i < componentiPrenotati.length; i++) {
//            if (componente.equals(componentiPrenotati[i])) {
//                componentiPrenotati[i] = null;
//                return true;
//            }
//        }
//        return false;
//    }
//    
//    // Metodi pezzi distrutti
//    public int getPezziDistrutti() {
//    	return pezziDistrutti;
//    }
//    
//    public void incrementaPezziDistrutti() {
//        pezziDistrutti++;
//    }
//    
    // Metodi crediti
    public void aggiungiCrediti(int crediti) {
    	this.crediti += crediti;
    }
    
    public int getCrediti() {
    	return crediti;
    }
    
}
