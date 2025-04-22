package nave;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import componenti.*;
import grafica.Colore;
import grafica.FormattatoreGrafico;
import io.GestoreIO;
import model.colpi.Meteorite;
import model.enums.*;
import util.*;

public class Nave implements Distruttore, VerificatoreImpatti, ValidatorePosizione {

	private final GestoreIO io = new GestoreIO();
	private final FormattatoreGrafico formattatoreGrafico = new FormattatoreGrafico();

	private Componente[][] grigliaComponenti;
	private final TipoNave livelloNave;

	private int potenzaMotrice;
	private float potenzaFuoco;
	private List<TipoPedina> equipaggio;

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
		}
	}

	public boolean setComponente(Componente componente, Coordinate coordinate) {
		// Controllo se il tipo di nave ammette componenti in quella posizione
		if (!livelloNave.isPosizionabile(coordinate)) {
			return false;
		}

		// Controllo se il pezzo si collega agli altri
		if (valida(grigliaComponenti, componente, coordinate)) {
			forzaComponente(componente, coordinate);
			return true;
		}
		return false;
	}

	// setto il componente senza controlli, usato dalle classi/interfacce di
	// supporto della nave
	protected void forzaComponente(Componente componente, Coordinate coordinate) {
		grigliaComponenti[coordinate.getX()][coordinate.getY()] = componente;
		componente.setPosizione(coordinate);
	}

	public int subisciImpatto(Meteorite meteorite, int coordinata) {
		Coordinate coordinate = verificaImpatto(this, meteorite, coordinata);

		if (coordinate != null) {
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
		this.equipaggio = out;
		return out;
	}

	public boolean rimuoviEquipaggio(int numero) {
		if (getEquipaggio().isEmpty())
			return false;

		int equipaggioRimosso = 0;

		// salvo le cabine
		List<Componente> cabine = this.getComponenti(TipoComponente.CABINA_EQUIPAGGIO);
		cabine.addAll(this.getComponenti(TipoComponente.CABINA_PARTENZA));

		// lascio all'utente scegliere quale pedina rimuovere
		String[] menu = new String[TipoPedina.values().length];
		for (int i = 0; i < TipoPedina.values().length; i++) {
			menu[i] = TipoPedina.values()[i].name();
		}
		io.stampa("Scegliere la pedina da rimuovere: ");
		TipoPedina pedinaDaRimuovere = TipoPedina.values()[io.stampaMenu(menu)];

		// rimuovo le pedine del tipo scelto dall'utente
		for (Componente cabina : cabine) {
			List<TipoPedina> equipaggio = ((CabinaDiEquipaggio) cabina).getEquipaggio();

			for (int i = 0; i < equipaggio.size(); i++) {
				if (equipaggio.get(i) == pedinaDaRimuovere) {
					equipaggio.remove(i);
					equipaggioRimosso++;
					i--; // lista modificata, rimango con lo stesso indice
					if (equipaggioRimosso == numero)
						return true;
				}
			}
		}
		// rimuovo qualsiasi pedina
		for (Componente cabina : cabine) {
			List<TipoPedina> equipaggio = ((CabinaDiEquipaggio) cabina).getEquipaggio();

			while (!equipaggio.isEmpty()) {
				equipaggio.remove(0);
				equipaggioRimosso++;
				if (equipaggioRimosso == numero)
					return true;
			}
		}
		return false; // non sono riuscito a rimuovere abbastanza pedine
	}

	public List<TipoMerce> getMerci() {
		List<Componente> stive = this.getComponenti(TipoComponente.STIVA);
		stive.addAll(this.getComponenti(TipoComponente.STIVA_SPECIALE));
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

	// True se la merce è stata rimossa, false se non ha abbastanza merce
	public boolean rimuoviMerce(int numero) {

		if (getMerci().size() == 0)
			return false;

		int merciRimosse = 0;

		// salvo le stive
		List<Componente> stive = this.getComponenti(TipoComponente.STIVA);
		stive.addAll(this.getComponenti(TipoComponente.STIVA_SPECIALE));

		// parto dalle merci più costose (da regolamento)
		for (TipoMerce merce : TipoMerce.values()) {

			for (Componente stiva : stive) {
				TipoMerce[] merci = ((Stiva) stiva).getMerci(); // salvo le merci della stiva

				if (merci != null) {
					for (int i = 0; i < merci.length; i++) {
						if (merci[i] == merce) {
							merci[i] = null;
							merciRimosse++;
							if (merciRimosse == numero)
								return true;
						}
					}
				}
			}
		}

		// se non ci sono abbastanza merci bisogna rimuovere le batterie
		for (int i = 0; i < numero - merciRimosse; i++) {
			if (!usaEnergia()) {
				return false; // non ci sono abbastanza batterie
			}
		}
		return true;
	}

	// indicare la direzione verso cui si vuole attivare lo scudo
	protected boolean attivaScudo(Direzione dir) {
		if (getEnergia() <= 0)
			return false;

		List<Componente> scudi = this.getComponenti(TipoComponente.SCUDO);

		for (Componente scudo : scudi) {
			Direzione[] direzioni = ((GeneratoreDiScudi) scudo).getDirezioni();
			if (Arrays.asList(direzioni).contains(dir)) {

				io.stampa("scrivere 1 se si vuole attivare lo scudo");
				if (io.leggiIntero() == 1) {
					return usaEnergia(); // usaEnergia valuta se è possibile o meno usare 1 di energia e la consuma
				}
				return false; // scudo non attivato per scelta dell'utente
			}
		}
		// non sono presenti scudi
		return false;
	}

	// spara e restituisce false se non spara
	protected boolean spara(Cannone cannone) {
		if (cannone.getTipo() == TipoComponente.CANNONE_DOPPIO) {
			if (getEnergia() <= 0)
				return false;

			io.stampa("scrivere 1 se si vuole sparare con il " + cannone.getTipo().name());
			if (io.leggiIntero() == 1) {
				return usaEnergia(); // sparo se l'utente è d'accordo
			}
			return false; // fuoco non attivato per scelta dell'utente
		} else {
			// se è un cannone singolo può sicuramente sparare
			return true;
		}
	}

	// se è possibile consuma l'energia altrimenti restituisce false
	private boolean usaEnergia() {
		if (getEnergia() <= 0)
			return false;

		List<Componente> vaniBatterie = this.getComponenti(TipoComponente.VANO_BATTERIA);

		// TODO lasciare all'utente la possibilita di scegliere da quale vano rimuovere
		// la batteria
		for (Componente vano : vaniBatterie) {
			// scarica batteria
			if (((VanoBatteria) vano).scaricaBatteria()) {
				return true;
			}
		}
		return false;
	}

	// calcola la potenza motrice
	public int getPotenzaMotrice() {
		int potenzaMotrice = 0;

		// contro i motori singoli
		List<Componente> motori = this.getComponenti(TipoComponente.MOTORE_SINGOLO);
		potenzaMotrice += motori.size();

		// conto i motori doppi
		motori = this.getComponenti(TipoComponente.MOTORE_DOPPIO);
		for (Componente motore : motori) {
			io.stampa("scrivere 1 se si vuole usare il motore in posizione: "
					+ formattatoreGrafico.formattaCoordinate(motore.getPosizione()));
			if (io.leggiIntero() == 1) {
				if (usaEnergia()) { // uso il motore doppio
					potenzaMotrice += 2;
				} else {
					break; // energia finita
				}
			}
		}

		// conto gli alieni marroni
		List<TipoPedina> equipaggio = this.getEquipaggio();
		for (TipoPedina pedina : equipaggio) {
			if (pedina == TipoPedina.ALIENO_MARRONE) {
				potenzaMotrice++;
			}
		}
		this.potenzaMotrice = potenzaMotrice;
		return potenzaMotrice;
	}

	public float getPotenzaFuoco() {
		float potenzaFuoco = 0;

		// contro i cannoni singoli
		List<Componente> cannoni = this.getComponenti(TipoComponente.CANNONE_SINGOLO);
		for (Componente cannone : cannoni) {
			potenzaFuoco += ((Cannone) cannone).getPotenzaFuoco();
		}

		// conto i cannoni doppi
		cannoni = this.getComponenti(TipoComponente.CANNONE_DOPPIO);
		for (Componente cannone : cannoni) {
			io.stampa("scrivere 1 se si vuole usare il cannone in posizione: "
					+ formattatoreGrafico.formattaCoordinate(cannone.getPosizione()));
			if (io.leggiIntero() == 1) {
				if (usaEnergia()) { // uso il cannone doppio
					potenzaFuoco += ((Cannone) cannone).getPotenzaFuoco();
				} else {
					break; // energia finita
				}
			}
		}

		// conto gli alieni viola
		List<TipoPedina> equipaggio = this.getEquipaggio();
		for (TipoPedina pedina : equipaggio) {
			if (pedina == TipoPedina.ALIENO_VIOLA) {
				potenzaFuoco++;
			}
		}

		this.potenzaFuoco = potenzaFuoco;
		return potenzaFuoco;
	}

	// TODO fare i setter di merci, equipaggio, batteria, fare le funzioni per
	// rimuovere l'energia e le merci.

}