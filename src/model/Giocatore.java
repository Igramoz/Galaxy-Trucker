package model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import grafica.Colore;
import grafica.formattatori.Formattabile;
import model.nave.Nave;
import model.nave.TipoNave;
import model.titoli.TipoTitolo;

public class Giocatore implements Formattabile {
	private final static int giocatoriMax = 4; // Numero massimo di giocatori
	public static int numeroGiocatori = 0;
	public static final List<Colore> coloriDisponibiliGiocatori = Arrays.asList(Colore.ROSSO, Colore.BLU, Colore.VERDE,
			Colore.GIALLO);

	private final String nome;
	private final Colore colore;
	private TipoTitolo tipoTitolo;

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
		this.nave = new Nave(tipoNave, colore);
	}

	public Nave getNave() {
		// NON CREO UNA COPIA della nave, se creassi una copia, il gioco non potrebbe
		// più modificare quella originale
		// la nave ha i suoi attributi protetti correttamente.
		return nave;
	}

	// Metodi crediti
	public void aggiungiCrediti(int crediti) {
		this.crediti += crediti;
	}

	public int getCrediti() {
		return crediti;
	}

	public static void resetNumeroGiocatori() {
		numeroGiocatori = 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(colore, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Giocatore other = (Giocatore) obj;
		return colore == other.colore && Objects.equals(nome, other.nome);
	}

	public TipoTitolo getTipoTitolo() {
		return tipoTitolo;
	}

	/**
	 * Assegna un titolo al giocatore se non è stato già assegnato.
	 * 
	 * @param titolo da assegnare
	 * @throws IllegalStateException Se un titolo è già stato assegnato.
	 */
	public void setTipoTitolo(TipoTitolo titolo) {
		if (this.tipoTitolo == null) {
			this.tipoTitolo = titolo;
		} else {
			if(titolo == this.tipoTitolo)
				return;
			throw new IllegalStateException("Il titolo è già stato assegnato.");
		}

	}
}
