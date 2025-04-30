package model.nave;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import model.carte.colpo.Colpo;
import model.componenti.*;
import grafica.Colore;
import grafica.FormattatoreGrafico;
import io.GestoreIO;
import model.enums.*;
import util.*;

public class Nave {

	private final GestoreIO io = new GestoreIO();
	private final FormattatoreGrafico formattatoreGrafico = new FormattatoreGrafico();
	private final GestoreComponenti gestoreComponenti;
	private final ValidatorePosizione validatorePosizione;
	private final VerificatoreImpatti verificatoreImpatti;

	private Componente[][] grigliaComponenti;
	private final TipoNave livelloNave;

	private int potenzaMotrice;
	private float potenzaFuoco;
	private List<TipoPedina> equipaggio;

	// Costruttore
	public Nave(TipoNave livelloNave) {
		grigliaComponenti = new Componente[Util.SIZE][Util.SIZE];
		this.livelloNave = livelloNave;
		gestoreComponenti = new GestoreComponenti(this);
		validatorePosizione = new ValidatorePosizione(this);
		verificatoreImpatti = new VerificatoreImpatti(this);
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

	// TODO valutare se elinmiare
	// Metodi griglia componenti
//	protected Componente[][] getGrigliaComponentiOriginali() {
//		Componente[][] copiaGrigliaComponenti = new Componente[Util.SIZE][Util.SIZE];
//		for (int x = 0; x < Util.SIZE; x++) {
//			for (int y = 0; y < Util.SIZE; y++) {
//				if (grigliaComponenti[x][y] != null) {
//					copiaGrigliaComponenti[x][y] = grigliaComponenti[x][y];
//				}
//			}
//		}
//		return copiaGrigliaComponenti;
//	}

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
			return null;
		}
		Componente copiaComponente = grigliaComponenti[coordinate.getX()][coordinate.getY()].clone();
		return copiaComponente;
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

	public boolean setComponente(Componente componente, Coordinate coordinate) {
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
		grigliaComponenti[coordinate.getX()][coordinate.getY()] = componente;
		componente.setPosizione(coordinate);
	}

	public int subisciImpatto(Colpo colpo, int coordinata) {
		Coordinate coordinate = verificatoreImpatti.verificaImpatto(colpo, coordinata);
		Distruttore distruggiComp = new Distruttore(this, coordinate);

		if (coordinate != null) {
			return distruggiComp.distruggiComponenti();
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

	// restituisce una lista dei componenti di un certo tipo
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
		List<Componente> batterie = this.getCopiaComponenti(TipoComponente.VANO_BATTERIA);
		int counter = 0;
		for (Componente batteria : batterie) {
			counter += ((VanoBatteria) batteria).getBatterie();
		}
		return counter;
	}

	public List<TipoPedina> getEquipaggio() {
		List<Componente> cabine = this.getCopiaComponenti(TipoComponente.CABINA_EQUIPAGGIO);
		cabine.addAll(this.getCopiaComponenti(TipoComponente.CABINA_PARTENZA));
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
		int equipaggioRimosso = 0;
		for (int i = 0; i < getEquipaggio().size(); i++) {
			if (gestoreComponenti.rimuoviEquipaggioDaNave()) {
				equipaggioRimosso++;
				if (equipaggioRimosso == numero)
					return true;
			}
		}
		return false; // non sono riuscito a rimuovere abbastanza pedine
	}

	protected boolean forzaEquipaggio(TipoPedina pedina, Coordinate coordinate) {
		if (coordinate == null || pedina == null)
			return false;
		if (this.getCopiaComponente(coordinate).getTipo() == TipoComponente.CABINA_EQUIPAGGIO) {
			return ((CabinaDiEquipaggio) grigliaComponenti[coordinate.getX()][coordinate.getY()])
					.aggiungiEquipaggio(pedina);
		}
		return false;
	}

	public List<TipoMerce> getMerci() {
		List<Componente> stive = this.getCopiaComponenti(TipoComponente.STIVA);
		stive.addAll(this.getCopiaComponenti(TipoComponente.STIVA_SPECIALE));
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

		int merciRimosse = 0;
		for (int i = 0; i < getMerci().size(); i++) {
			// parto dalle merci più costose (da regolamento)
			for (TipoMerce merce : TipoMerce.values()) {
				if (gestoreComponenti.rimuoviMerceDaNave(merce)) {
					merciRimosse++;
					if (merciRimosse == numero)
						return true;
				}
			}
		}
		// se non ci sono abbastanza merci bisogna rimuovere le batterie
		for (int i = 0; i < numero - merciRimosse; i++) {
			if (!gestoreComponenti.consumaEnergia()) {
				return false; // non ci sono abbastanza batterie
			}
		}
		return true;
	}

	public boolean setMerci(List<TipoMerce> merci) {
		return gestoreComponenti.posizionaMerciInNave( merci);
	}

	protected boolean forzaMerce(TipoMerce merce, Coordinate coordinate) {
		if (coordinate == null || merce == null)
			return false;

		if (this.getCopiaComponente(coordinate).getTipo() == TipoComponente.STIVA_SPECIALE) {
			return ((StivaSpeciale) grigliaComponenti[coordinate.getX()][coordinate.getY()]).setMerci(merce);
		}
		return ((Stiva) grigliaComponenti[coordinate.getX()][coordinate.getY()]).setMerci(merce);
	}

	// indicare la direzione verso cui si vuole attivare lo scudo
	protected boolean attivaScudo(Direzione dir) {
		if (getEnergia() <= 0)
			return false;

		List<Componente> scudi = this.getCopiaComponenti(TipoComponente.SCUDO);

		for (Componente scudo : scudi) {
			Direzione[] direzioni = ((GeneratoreDiScudi) scudo).getDirezioni();
			if (Arrays.asList(direzioni).contains(dir)) {

				io.stampa("scrivere 1 se si vuole attivare lo scudo");
				if (io.leggiIntero() == 1) {
					return gestoreComponenti.consumaEnergia(); // usaEnergia valuta se è possibile o meno usare 1 di energia e la consuma
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
		int potenzaMotrice = 0;

		// contro i motori singoli
		List<Componente> motori = this.getCopiaComponenti(TipoComponente.MOTORE_SINGOLO);
		potenzaMotrice += motori.size();

		// conto i motori doppi
		motori = this.getCopiaComponenti(TipoComponente.MOTORE_DOPPIO);
		for (Componente motore : motori) {
			io.stampa("scrivere 1 se si vuole usare il motore in posizione: "
					+ formattatoreGrafico.formattaCoordinate(motore.getPosizione()));
			if (io.leggiIntero() == 1) {
				if (gestoreComponenti.consumaEnergia()) { // uso il motore doppio
					potenzaMotrice += 2;
				} else {
					break; // energia finita
				}
			}
		}

		// potenzaMotrice != 0 affinché l'alieno si attivi
		if (potenzaMotrice == 0) {
			return potenzaMotrice;
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

		// conto i cannoni singoli
		List<Componente> cannoni = this.getCopiaComponenti(TipoComponente.CANNONE_SINGOLO);
		for (Componente cannone : cannoni) {
			potenzaFuoco += ((Cannone) cannone).getPotenzaFuoco();
		}

		// conto i cannoni doppi
		cannoni = this.getCopiaComponenti(TipoComponente.CANNONE_DOPPIO);
		for (Componente cannone : cannoni) {
			io.stampa("scrivere 1 se si vuole usare il cannone in posizione: "
					+ formattatoreGrafico.formattaCoordinate(cannone.getPosizione()));
			if (io.leggiIntero() == 1) {
				if (gestoreComponenti.consumaEnergia()) { // uso il cannone doppio
					potenzaFuoco += ((Cannone) cannone).getPotenzaFuoco();
				} else {
					break; // energia finita
				}
			}
		}

//		se la potenza di fuoco senza l’alieno è 0, l'alieno non si attiva.
//		Non affronterà una battaglia spaziale a tentacoli nudi
		if (potenzaFuoco == 0)
			return potenzaFuoco;
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

	public Map<Direzione, Componente> getCopiaComponentiAdiacenti(Coordinate coord) {

		Map<Direzione, Componente> adiacenti = new EnumMap<>(Direzione.class);

		// Coord del componente
		int x = coord.getX();
		int y = coord.getY();

		adiacenti.put(Direzione.SOPRA, grigliaComponenti[x][y - 1].clone());
		adiacenti.put(Direzione.SINISTRA, grigliaComponenti[x - 1][y].clone());
		adiacenti.put(Direzione.SOTTO, grigliaComponenti[x][y + 1].clone());
		adiacenti.put(Direzione.DESTRA, grigliaComponenti[x + 1][y].clone());

		return adiacenti;
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

	// carica la nave e aggiunge l'equipaggio
	public void preparaAlVolo() {
		preparaEquipaggioAlVolo();
		ricaricaBatterie();
	}

	private void preparaEquipaggioAlVolo() {
		// carico gli alieni
		gestoreComponenti.posizionaAlienoInNave( TipoPedina.ALIENO_MARRONE);
		gestoreComponenti.posizionaAlienoInNave( TipoPedina.ALIENO_VIOLA);

		// carico gli astronauti
		gestoreComponenti.posizionaAstronatuaInNave();
	}
	
	// carica al massimo tutte le batterie
	private void ricaricaBatterie() {
		List<Componente> batterie = this.getCopiaComponenti(TipoComponente.VANO_BATTERIA);
		for (Componente batteria : batterie) {
			((VanoBatteria) batteria).caricaInteramenteBatteria();
		}
	}
}