package model.nave;

import eccezioni.CaricamentoNonConsentitoException;
import eccezioni.ComponentePienoException;
import grafica.Colore;
import io.GestoreIO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import model.carte.colpo.Colpo;
import model.componenti.*;
import model.enums.*;
import util.*;
import util.layout.Coordinate;
import util.layout.Direzione;

public class Nave {

	private final GestoreIO io = new GestoreIO();
	private final GestoreComponenti gestoreComponenti;
	private final ValidatorePosizione validatorePosizione;
	private final VerificatoreImpatti verificatoreImpatti;
	private final AnalizzatoreNave analizzatoreNave;
	private final Distruttore distruttore;

	private Componente[][] grigliaComponenti;
	private final TipoNave livelloNave;

	// Costruttore
	public Nave(TipoNave livelloNave) {
		if (livelloNave == null) {
			throw new NullPointerException("Bisogna passare un livello alla nave");
		}

		grigliaComponenti = new Componente[Util.SIZE][Util.SIZE];
		this.livelloNave = livelloNave;
		gestoreComponenti = new GestoreComponenti(this);
		validatorePosizione = new ValidatorePosizione(this);
		verificatoreImpatti = new VerificatoreImpatti(this);
		analizzatoreNave = new AnalizzatoreNave(this);
		distruttore = new Distruttore(this);
	}

	// Costruttore
	public Nave(TipoNave livelloNave, Colore colore) {
		this(livelloNave);
		forzaComponente(new CabinaPartenza(colore), new Coordinate(Util.SIZE / 2, Util.SIZE / 2));
	}

	// Costruttore di copia
	public Nave(Nave naveOriginale) {
		this(naveOriginale.livelloNave,
				((CabinaPartenza) naveOriginale.grigliaComponenti[Util.SIZE / 2][Util.SIZE / 2]).getColore());
		for (int i = 0; i < Util.SIZE; i++) {
			for (int j = 0; j < Util.SIZE; j++) {
				this.grigliaComponenti[i][j] = naveOriginale.grigliaComponenti[i][j].clone();
			}
		}
	}

	// Metodi griglia componenti
	public Componente[][] getGrigliaComponentiCloni() {
		Componente[][] copiaGrigliaComponenti = new Componente[Util.SIZE][Util.SIZE];
		for (int x = 0; x < Util.SIZE; x++) {
			for (int y = 0; y < Util.SIZE; y++) {
				if (grigliaComponenti[x][y] != null) {
					copiaGrigliaComponenti[x][y] = grigliaComponenti[x][y].clone();
				}
			}
		}
		return copiaGrigliaComponenti;
	}

	public Componente getCopiaComponente(Coordinate coordinate) {
		if (coordinate == null) {
			throw new NullPointerException("Bisogna passare delle coordinate alla nave");
		}
		return grigliaComponenti[coordinate.getX()][coordinate.getY()].clone();
	}

	protected Componente getOriginaleComponente(Coordinate coordinate) {
		return grigliaComponenti[coordinate.getX()][coordinate.getY()];
	}

	public TipoNave getLivelloNave() {
		return livelloNave;
	}

	// Il metodo può essere chiamato solo dalla funzione distruggiComponenti della
	// classe EventService
	protected void distruggiSingoloComponente(Coordinate coordinate) {
		if (grigliaComponenti[coordinate.getX()][coordinate.getY()] != null) {
			grigliaComponenti[coordinate.getX()][coordinate.getY()] = null;
		}
	}

	/**
	 * Distrugge il compoenente in posizione coordinate e gli altri ad essi connessi
	 * 
	 * @param coordinate
	 * @return true se il componente a coordiante è stato distrutto
	 */
	public int distruggiComponentiConnessi(Coordinate coordinate) {
		return distruttore.distruggiComponenti(coordinate);
	}

	/**
	 * Prova a posizionare un componente nelle coordinate specificate.
	 * 
	 * @param componente da posizionare
	 * @param coordinate dove posizionare il componente
	 * @return true se è stato posizionato, false altrimenti
	 * @throws NullPointerException se uno dei 2 param è null
	 */
	public boolean setComponente(Componente componente, Coordinate coordinate) {
		if(componente == null) {
			throw new NullPointerException("Passare un componete alla funzione");
		}
		if(coordinate == null){
			throw new NullPointerException("Passare un delle coordinate valide alla funzione");
		}
		
		// Controllo se il tipo di nave ammette componenti in quella posizione
		if (!livelloNave.isPosizionabile(coordinate)) {
			return false;
		}

		// Controllo se il pezzo si collega agli altri
		if (validatorePosizione.valida(componente, coordinate)) {
			forzaComponente(componente, coordinate);
			return true;
		}
		return false;
	}

	// setto il componente senza controlli, usato dalle classi/interfacce di
	// supporto della nave
	protected void forzaComponente(Componente componente, Coordinate coordinate) {
		if(componente == null) {
			throw new NullPointerException("Passare un componete alla funzione");
		}
		if(coordinate == null){
			throw new NullPointerException("Passare delle coordinate valide alla funzione");
		}
		grigliaComponenti[coordinate.getX()][coordinate.getY()] = componente;
		componente.setPosizione(coordinate);
	}

	public int subisciImpatto(Colpo colpo, int coordinata) {
		Coordinate coordinate = verificatoreImpatti.verificaImpatto(colpo, coordinata);

		if (coordinate != null) {
			return distruttore.distruggiComponenti(coordinate);
		}
		return 0;
	}

	// restituisce una lista di copie dei componenti di un certo tipo
	public List<Componente> getCopiaComponenti(TipoComponente componente) {

		List<Componente> out = new ArrayList<>();
		for (int x = 0; x < Util.SIZE; x++) {
			for (int y = 0; y < Util.SIZE; y++) {
				if (grigliaComponenti[x][y] != null && grigliaComponenti[x][y].getTipo() == componente) {
					out.add(grigliaComponenti[x][y].clone());// aggiungo alla lista LA COPIA del compoenente
				}
			}
		}
		return out;
	}

	// restituisce una lista dei componenti originali di un certo tipo
	protected List<Componente> getComponentiOriginali(TipoComponente componente) {

		List<Componente> out = new ArrayList<>();
		for (int x = 0; x < Util.SIZE; x++) {
			for (int y = 0; y < Util.SIZE; y++) {
				if (grigliaComponenti[x][y] != null && grigliaComponenti[x][y].getTipo() == componente) {
					out.add(grigliaComponenti[x][y]);
				}
			}
		}
		return out;
	}

	public int getEnergia() {
		return analizzatoreNave.energiaCounter();
	}

	public List<TipoPedina> getEquipaggio() {
		return analizzatoreNave.trovaEquipaggioNave();
	}

	/**
	 * Rimuove l'equipaggio dalla nave.
	 * 
	 * @param numero di pedine da rimuovere
	 * @return true se sono state rimosse abbastanza pedine, false altrimenti
	 */
	public boolean rimuoviEquipaggio(int numero) {
		return gestoreComponenti.rimuoviEquipaggioDaNave(numero);
	}

	protected void forzaEquipaggio(TipoPedina pedina, Coordinate coordinate) throws ComponentePienoException {
		if (coordinate == null || pedina == null)
			throw new NullPointerException("Pedina e coordinate non possono essere null");

		if (this.getCopiaComponente(coordinate) instanceof CabinaDiEquipaggio) {
			((CabinaDiEquipaggio) grigliaComponenti[coordinate.getX()][coordinate.getY()]).aggiungiEquipaggio(pedina);
		}
		throw new CaricamentoNonConsentitoException(
				"Il componente alle coordinate " + coordinate + " non è una cabina di equipaggio.");
	}

	public int eliminaEquipaggioDaCabineCollegate() {
		return gestoreComponenti.eliminaEquipaggioDaCabineCollegate();
	}

	public List<TipoMerce> getMerci() {		
		return analizzatoreNave.trovaMerciNave();
	}

	/**
	 * Rimuove un certo numero di merci dalla nave. Se non ci sono abbastanza merci,
	 * cerca di rimuovere batterie. (come da regolamento)
	 * 
	 * @param numerodi merci da rimuovere
	 * @return true se sono state rimosse abbastanza risorse, false altrimenti
	 */
	public boolean rimuoviMerce(int numero) {
		return gestoreComponenti.rimuoviMerceDaNave(numero);
	}

	public boolean setMerci(List<TipoMerce> merci) {
		return gestoreComponenti.posizionaMerciInNave(merci);
	}

	protected void forzaMerce(TipoMerce merce, Coordinate coordinate) throws ComponentePienoException {
		if (coordinate == null)
			throw new NullPointerException("coordinate non può essere null");
		if (merce == null)
			throw new NullPointerException("merce non può essere null");

			
		if (this.getCopiaComponente(coordinate) instanceof Stiva) {
			((Stiva) grigliaComponenti[coordinate.getX()][coordinate.getY()]).setMerci(merce);
		} else {
			throw new CaricamentoNonConsentitoException(
					"Il componente alle coordinate " + coordinate + " non è una stiva.");
		}
	}

	// spara e restituisce false se non spara
	protected boolean spara(Cannone cannone) {
		if (cannone.getTipo() == TipoComponente.CANNONE_DOPPIO) {
			if (getEnergia() <= 0)
				return false;

			io.stampa("scrivere 1 se si vuole sparare con il " + cannone.getTipo().name());
			if (io.leggiIntero() == 1) {
				return gestoreComponenti.consumaEnergia(); // sparo se l'utente è d'accordo
			}
			return false; // fuoco non attivato per scelta dell'utente
		} else {
			// se è un cannone singolo può sicuramente sparare
			return true;
		}
	}

	// calcola la potenza motrice
	public int getPotenzaMotrice() {
		return analizzatoreNave.potenzaMotriceCounter();
	}

	public float getPotenzaFuoco() {
		return analizzatoreNave.potenzaFuocoCounter();
	}

	/**
	 * Restituisce una mappa contenente una copia dei componenti adiacenti alla posizione passata alla funzione.
	 * 
	 * La mappa restituita associa ad ogni direzione il componente corrispondente 
	 * 
	 * @param coord le coordinate per le quali cercare i componenti adiacenti (non può essere {@code null})
	 * @return una mappa dei componenti adiacenti
	 * @throws NullPointerException se coord è {@code null}
	 */
	public Map<Direzione, Componente> getCopiaComponentiAdiacenti(Coordinate coord) {
		if(coord == null) {
			throw new NullPointerException("Non è possibile cercare i componenti adiacenti a delle coordinate nulle");
		}
		
		return analizzatoreNave.getCopiaComponentiAdiacenti(coord);
	}

	// funzione per controllare che la nave abbia raggiunto il massimo numero di
	// componenti dell'equipaggio
	public boolean isEquipaggioCompleto() {
		List<Componente> cabine = this.getCopiaComponenti(TipoComponente.CABINA_EQUIPAGGIO);
		cabine.addAll(this.getCopiaComponenti(TipoComponente.CABINA_PARTENZA));
		for (Componente cabina : cabine) {
			if (!((CabinaDiEquipaggio) cabina).isPiena()) {
				return false;
			}
		}
		return true;
	}

	public int getNumConnettoriEsposti() {
		return analizzatoreNave.connettoriEspostiConuter();
	}

	// carica la nave e aggiunge l'equipaggio
	public void preparaAlVolo() {
		gestoreComponenti.preparaEquipaggioAlVolo();
		gestoreComponenti.ricaricaBatterie();
	}

	protected GestoreComponenti getGestoreComponenti() {
		return gestoreComponenti;
	}

	protected AnalizzatoreNave getAnalizzatoreNave() {
		return analizzatoreNave;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(grigliaComponenti);
		result = prime * result + Objects.hash(livelloNave);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Nave other = (Nave) obj;
		return Arrays.deepEquals(grigliaComponenti, other.grigliaComponenti) && livelloNave == other.livelloNave;
	}
}