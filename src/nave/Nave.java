package nave;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import componenti.*;
import grafica.Colore;
import io.GestoreIO;
import model.Coordinate;
import model.colpi.TipiMeteorite;
import model.enums.*;
import util.*;

public class Nave implements Distruttore, GestoreImpatti, ValidatorePosizione {

	private GestoreIO io = new GestoreIO();
	
	private Componente[][] grigliaComponenti;
	private final TipoNave livelloNave;
	

	// Costruttore
	public Nave(TipoNave livelloNave) {
		grigliaComponenti = new Componente[Util.SIZE][Util.SIZE];
		this.livelloNave = livelloNave;
	}

	// Costruttore
	public Nave(TipoNave livelloNave, Colore colore) {
		this(livelloNave);
		grigliaComponenti[Util.SIZE / 2][Util.SIZE / 2] = new CabinaPartenza(colore);
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

	@Override
	public Nave clone() {
		return new Nave(this);
	}

	// Metodi griglia componenti
	public Componente[][] getGrigliaComponenti() {
		Componente[][] copiaGrigliaComponenti = new Componente[Util.SIZE][Util.SIZE];
		for (int x = 0; x < Util.SIZE; x++) {
			for (int y = 0; y < Util.SIZE; y++) {
				if (grigliaComponenti[x][y] != null) {
					copiaGrigliaComponenti[x][y] = grigliaComponenti[x][y];
				}
			}
		}
		return copiaGrigliaComponenti;
	}

	public Componente getComponente(Coordinate coordinate) {
		Componente copiaComponente = grigliaComponenti[coordinate.getX()][coordinate.getY()].clone();
		return copiaComponente;
	}

	public TipoNave getLivelloNave() {
		return livelloNave;
	}

	// Il metodo può essere chiamato solo dalla funzione distruggiComponenti della
	// classe EventService
	protected void distruggiSingoloComponente(Coordinate coordinate) {
		if (grigliaComponenti[coordinate.getX()][coordinate.getY()] != null) {
			grigliaComponenti[coordinate.getX()][coordinate.getY()] = null;
			// TODO aggiornare il numero di pezzi distrutti nel giocatore
		}
	}

	public boolean setComponente(Componente componente, Coordinate coordinate) {
		// Controllo se il tipo di nave ammette componenti in quella posizione
		if (!livelloNave.isPosizionabile(coordinate)) {
			return false;
		}

		// Controllo se il pezzo si collega agli altri
		if (valida(grigliaComponenti, componente, coordinate)) {
			grigliaComponenti[coordinate.getX()][coordinate.getY()] = componente;
			componente.setPosizione(coordinate);
			return true;
		}
		return false;
	}

	public int subisciImpatto(Coppia<TipiMeteorite, Direzione> meteorite, Coordinate coordinate) {
		if (this.getComponente(coordinate) == null) {
			return 0;
		}
		if (subisciImpatto(this, meteorite, coordinate)) {
			return distruggiComponenti(this, coordinate);
		}
		return 0;
	}

	// public int subisciImpatto(Coppia<TipiCannonate, Direzione>, Coordinate
	// coordinate) {
	// return 0;
	// }

	public List<Componente> getComponenti(TipoComponente componente) {

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
		List<Componente> batterie = this.getComponenti(TipoComponente.VANO_BATTERIA);
		int counter = 0;
		for (Componente batteria : batterie) {
			counter += ((VanoBatteria) batteria).getBatterie();
		}
		return counter;
	}

	public List<TipoPedina> getEquipaggio() {
		List<Componente> cabine = this.getComponenti(TipoComponente.CABINA_EQUIPAGGIO);
		cabine.addAll(this.getComponenti(TipoComponente.CABINA_PARTENZA));
		List<TipoPedina> out = new ArrayList<>();
		for (Componente cabina : cabine) {
			// Controllo che l'equipaggio non sia null prima di settarlo
			List<TipoPedina> equipaggio = ((CabinaDiEquipaggio) cabina).getEquipaggio();
			if (equipaggio != null) {
				out.addAll(equipaggio);
			}
		}
		return out;
	}

	public List<TipoMerce> getMerci() {
	    List<Componente> stive = this.getComponenti(TipoComponente.STIVA);
	    List<TipoMerce> out = new ArrayList<>();

	    for (Componente stiva : stive) {
	        TipoMerce[] merci = ((Stiva) stiva).getMerci();

	        if (merci != null) {
	            for (TipoMerce merce : merci) {
	                if (merce != null) {
	                    out.add(merce);
	                }
	            }
	        }
	    }
	    return out;
	}
	
	// indicare la direzione verso cui si vuole attivare lo scudo
	protected boolean attivaScudo(Direzione dir) {
	    List<Componente> scudi = this.getComponenti(TipoComponente.SCUDO);
	    
	    for (Componente scudo : scudi) {
	        Direzione[] direzioni = ((GeneratoreDiScudi) scudo).getDirezioni();
	        if (Arrays.asList(direzioni).contains(dir)) {
	        	
	        	io.stampa("scrivere 1 se si vuole attivare lo scudo");
	        	if(io.leggiIntero() == 1) {
		        	return usaEnergia(); // usaEnergia valuta se è possibile o meno usare 1 di energia e la consuma
	        	}
	        	return false; // scudo non attivato per scelta dell'utente
	        }
	    }
	    io.stampa("Non ci sono scudi che difendono " + dir.name());
	    return false;
	}
	
	// spara e restituisce false se non spara
	protected boolean spara(Cannone cannone) {
		if(cannone.getTipo() == TipoComponente.CANNONE_DOPPIO) {
			io.stampa("scrivere 1 se si vuole sparare con il " + cannone.getTipo().name());
        	if(io.leggiIntero() == 1) {
	        	return usaEnergia(); // sparo se l'utente è d'accordo
        	}
        	return false; // fuoco non attivato per scelta dell'utente
		}else {
			// se è un cannone singolo può sicuramente sparare
			return true;
		}
	}
	
	// se è possibile consuma l'energia altrimenti restituisce false
	private boolean usaEnergia() {
		if(getEnergia() <= 0) return false;
		
	    List<Componente> vaniBatterie = this.getComponenti(TipoComponente.VANO_BATTERIA);
	    
	    // TODO lasciare all'utente la possibilita di scegliere da quale vano rimuovere la batteria
	    for(Componente vano : vaniBatterie) {
	    	// scarica batteria
	    	if(((VanoBatteria) vano).scaricaBatteria()){
	    		return true;
	    	}
	    }
	    return false;
	}
	
	// TODO fare i setter di merci, equipaggio, batteria, fare le funzioni per rimuovere l'energia e le merci.
	
	
	
	
	

}