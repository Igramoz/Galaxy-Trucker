package model.nave;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import eccezioni.ComponentePienoException;
import eccezioni.ComponenteVuotoException;
import grafica.formattatori.FormattatoreGrafico;
import grafica.renderer.ComponenteRenderer;
import io.GestoreIO;
import model.componenti.*;
import model.enums.*;
import util.layout.Coordinate;
import util.layout.Direzione;

public class GestoreComponenti {
// classe che interagisce con i setter e i getter per gestire l'inserimento o la rimozione di merci/energia/equipaggio dalla nave

	// funzione per scegliere un componente della nave
	private final Nave nave;
	private GestoreIO io = new GestoreIO();
	private FormattatoreGrafico formattatore = new FormattatoreGrafico();
	private ComponenteRenderer componentiRender = new ComponenteRenderer();

	protected GestoreComponenti(Nave nave) {
		this.nave = nave;
	}

	

	// restituisce false se non posiziona tutte le merci
	public boolean posizionaMerciInNave(List<TipoMerce> merci) {

		if (merci == null || merci.size() == 0) {
			return false;
		}
		io.stampa("Posiziona le merci in stiva una ad una");

		boolean output = true;
		for (TipoMerce merce : merci) {
			// se anche una sola merce non viene posizionata la funzione restituisce false
			if (!posizionaMerce(merce))
				output = false;
		}
		return output;
	}

	private boolean posizionaMerce(TipoMerce merce) {
		boolean sceltaValida;
		do {
			io.stampa("Posiziona la merce " + formattatore.formatta(merce));
			sceltaValida = true;
			Coordinate posizione = null;
			if (merce == TipoMerce.ROSSO) {
				posizione = nave.getAnalizzatoreNave().scegliComponente(TipoComponente.STIVA_SPECIALE);
			} else {
				posizione = nave.getAnalizzatoreNave().scegliComponente(TipoComponente.STIVA_SPECIALE, TipoComponente.STIVA);
			}

			if (posizione == null) {
				io.stampa("Il giocatore non dispone di stive adatte per immagazzinare la merce in questione");
				return false;
			}

			try {
				// provo a posizionare la merce
				nave.forzaMerce(merce, posizione);
			} catch (ComponentePienoException e) {

				io.stampa("Non è possibile posizionare la merce in questa posizione");
				String[] menu = { "Riprova", "Rimuovi merce da una stiva", "Scarta merce" };
				int scelta = io.stampaMenu(menu);

				switch (scelta) {
				case 0:
					sceltaValida = false;
					break;
				case 1:
					if (!rimuoviMerceDaNave()) {
						io.stampa("Si deve rimuovere una merce per posizionare la nuova");
					}
					sceltaValida = false;
					break;
				case 2:
					// merce non posizionata
					return false;
				}
			}
		} while (!sceltaValida);
		return true;
	}

	// rimuove un definito numero di merci dalla nave
	public boolean rimuoviMerceDaNave(int numero) {

		int merciRimosse = 0;
		for (int i = 0; i < nave.getMerci().size(); i++) {
			// parto dalle merci più costose (da regolamento)
			for (TipoMerce merce : TipoMerce.values()) {
				if (rimuoviMerceDaNave(merce)) {
					merciRimosse++;
					if (merciRimosse == numero)
						return true;
				}
			}
		}
		// se non ci sono abbastanza merci bisogna rimuovere le batterie
		for (int i = 0; i < numero - merciRimosse; i++) {
			if (!consumaEnergia()) {
				return false; // non ci sono abbastanza batterie
			}
		}
		return true;
	}

	// rimuove un particolare tipo di merce da una stiva della nave
	public boolean rimuoviMerceDaNave(TipoMerce merce) {

		if (nave.getMerci().size() == 0) {
			io.stampa("Non ci sono merci da rimuovere");
			return false;
		}

		io.stampa("Rimuovi la merce " + merce.name());
		do {
			Coordinate posizione = null;
			if (merce == TipoMerce.ROSSO) {
				posizione = nave.getAnalizzatoreNave().scegliComponente(TipoComponente.STIVA_SPECIALE);
			} else {
				posizione = nave.getAnalizzatoreNave().scegliComponente(TipoComponente.STIVA_SPECIALE, TipoComponente.STIVA);
			}

			if (posizione == null) {
				io.stampa("La nave non ha stive adatte ad immagazzinare merce " + formattatore.formatta(merce));
				return false;
			}

			Stiva stiva = (Stiva) nave.getOriginaleComponente(posizione);

			if (stiva.rimuovi(merce)) {
				io.stampa("Merce rimossa");
				return true;
			} else {
				io.stampa("Non è possibile rimuovere la merce in questa posizione");
				io.stampa("scegliere nuovamente");
				String[] menu = { "Riprova", "Non rimuovere merce" };
				int scelta = io.stampaMenu(menu);
				if (scelta == 1)
					return false;
			}
		} while (true);
	}

	// lascia all'utente la possibilità di rimuovere la merce che vuole dalla stiva
	public boolean rimuoviMerceDaNave() {
		io.stampa("Scegli quanta merce rimuovere, scrivere 0 per non rimuovere nulla");
		int quantita = io.leggiIntero();
		if (quantita == 0)
			return false;
		boolean output = true;
		for (int i = 0; i < quantita; i++) {
			io.stampa((i + 1) + " scegli il tipo di merce da rimuovere.");

			TipoMerce merce = io.scegliEnum(TipoMerce.class);
			if (!rimuoviMerceDaNave(merce))
				output = false;
		}
		return output;
	}

	/**
	 * Rimuove l'equipaggio dalla nave.
	 * 
	 * @param numero di pedine da rimuovere
	 * @return true se sono state rimosse abbastanza pedine, false altrimenti
	 */
	public boolean rimuoviEquipaggioDaNave(int numero) {
		int equipaggioRimosso = 0;
		for (int i = 0; i < nave.getEquipaggio().size(); i++) {
			if (rimuoviEquipaggioDaNave()) {
				equipaggioRimosso++;
				if (equipaggioRimosso == numero)
					return true;
			}
		}
		return false; // non sono riuscito a rimuovere abbastanza pedine
	}

	public boolean rimuoviEquipaggioDaNave() {

		// salvo le cabine
		List<Componente> cabine = nave.getComponentiOriginali(TipoComponente.CABINA_EQUIPAGGIO);
		cabine.addAll(nave.getComponentiOriginali(TipoComponente.CABINA_PARTENZA));

		if (nave.getEquipaggio().isEmpty() || cabine.isEmpty())
			return false;

		boolean sceltaValida;
		do {
			sceltaValida = true;

			// lascio all'utente scegliere quale pedina rimuovere
			io.stampa("Scegliere la pedina da rimuovere: ");
			TipoPedina pedinaDaRimuovere = io.scegliEnum(TipoPedina.class);
			// rimuovo le pedine del tipo scelto dall'utente

			// lascio scegliere all'utente da quale cabina rimuovere la pedina
			io.stampa("Scegliere la cabina da cui rimuovere la pedina: ");
			Coordinate posizione = null;
			if (pedinaDaRimuovere == TipoPedina.ASTRONAUTA) {
				posizione = nave.getAnalizzatoreNave().scegliComponente(TipoComponente.CABINA_EQUIPAGGIO, TipoComponente.CABINA_PARTENZA);
			} else {
				posizione = nave.getAnalizzatoreNave().scegliComponente(TipoComponente.CABINA_EQUIPAGGIO);
			}
			
			if (posizione == null) {
				io.stampa("Il giocatore ha delle cabina contenenti questo tipo di pedina");
				return false;
			}
			
			CabinaDiEquipaggio cabina = (CabinaDiEquipaggio) nave.getOriginaleComponente(posizione);

			if (!cabina.rimuovi(pedinaDaRimuovere)) {
				io.stampa("Non è possibile rimuovere la pedina da questo componente");
				String[] menu = { "Riprova", "Non rimuovere pedina" };
				int scelta = io.stampaMenu(menu);
				if (scelta == 1)
					return false;
				else {
					sceltaValida = false;
				}
			}
		} while (!sceltaValida);
		return true;

	}

	public int eliminaEquipaggioDaCabineCollegate() {
		int membriEquipaggioEliminati = 0;
		List<Coordinate> coordinateGiaEsaminate = new ArrayList<>();
		List<Componente> cabine = new ArrayList<>();
		cabine.addAll(nave.getComponentiOriginali(TipoComponente.CABINA_EQUIPAGGIO));
		cabine.addAll(nave.getComponentiOriginali(TipoComponente.CABINA_PARTENZA));

		for (Componente cabina : cabine) {
			if (!coordinateGiaEsaminate.contains(cabina.getPosizione())) {
				coordinateGiaEsaminate.add(cabina.getPosizione());
				try {
					((CabinaDiEquipaggio) cabina).rimuoviUnMembro();
				} catch (ComponenteVuotoException e) {
					// Se il componente è vuoto non succede nulla, semplicemente non elimino nessun
					// membro
				}
				membriEquipaggioEliminati++;

				List<Componente> adiacenti = nave.getAnalizzatoreNave().ottieniCabineEquipaggioCollegate(cabina);
				for (Componente adiacente : adiacenti) {
					if (!coordinateGiaEsaminate.contains(adiacente.getPosizione())) {
						coordinateGiaEsaminate.add(adiacente.getPosizione());
						try {
							((CabinaDiEquipaggio) adiacente).rimuoviUnMembro();
						} catch (ComponenteVuotoException e) {
							// Se il componente è vuoto non succede nulla, semplicemente non elimino nessun
							// membro
						}
						membriEquipaggioEliminati++;
					}
				}
			}
		}
		return membriEquipaggioEliminati;
	}

	public boolean posizionaEquipaggioInNave(TipoPedina pedina) {

		List<Componente> cabineCollegate = new ArrayList<>();

		if (pedina == null)
			return false;

		if (nave.isEquipaggioCompleto()) {
			return false;
		}
		if (pedina == TipoPedina.ASTRONAUTA)
			posizionaAstronatuaInNave();

		if (nave.getEquipaggio().contains(pedina)) {
			return false; // si può ospitare massimo un alieno per tipo
		}

		// controllo di quale sovrastruttra ho bisogno
		TipoComponente tipoSovrastruttura = null;
		if (TipoPedina.ALIENO_MARRONE == pedina) {
			tipoSovrastruttura = TipoComponente.SOVRASTRUTTURA_ALIENA_MARRONE;
		} else {
			tipoSovrastruttura = TipoComponente.SOVRASTRUTTURA_ALIENA_VIOLA;
		}

		// cerco la sovrastruttura
		List<Componente> sovrastrutture = nave.getCopiaComponenti(tipoSovrastruttura);

		// non c'è la sovrastruttura
		if (sovrastrutture.isEmpty()) {
			return false;
		}

		// salvo le cabine collegate
		for (Componente sovrastruttura : sovrastrutture) {
			cabineCollegate.addAll(nave.getAnalizzatoreNave().ottieniCabineEquipaggioCollegate(sovrastruttura));
		}

		// la nave non ha cabine d'equipaggio collegate alla sovrastruttura
		if (cabineCollegate.isEmpty()) {
			return false;
		}

		// rimuovo la cabina di partenza, se c'è
		cabineCollegate.removeIf(p -> p.getTipo() == TipoComponente.CABINA_PARTENZA);

		do {
			// lascio all'utente la possibilità di scegliere in quale cabina posizionare
			// l'alieno
			Componente cloneCabina = io.menuComponenti(cabineCollegate);
			if (cloneCabina == null) {
				return false;
			}
			Componente cabina = nave.getOriginaleComponente(cloneCabina.getPosizione());

			try {
				((CabinaDiEquipaggio) cabina).aggiungi(pedina);
				io.stampa("" + formattatore.formatta(pedina) + " posizionato in: " + componentiRender.rappresentazioneCompletaComponente(cabina) );
				return true;
			} catch (ComponentePienoException e) {
				io.stampa("Non è possibile posizionare l'alieno in questa cabina");
				String[] menu = { "Riprova", "Non posizionare l'alieno" };
				int scelta = io.stampaMenu(menu);
				if (scelta == 1)
					return false;
			}
		} while (true);
	}

	// posiziona l'altronauta in una qualunque cabina d'equipaggio o di partenza
	public boolean posizionaAstronatuaInNave() {
		if (nave.isEquipaggioCompleto())
			return false;

		List<Componente> cabine = nave.getComponentiOriginali(TipoComponente.CABINA_EQUIPAGGIO);
		cabine.addAll(nave.getComponentiOriginali(TipoComponente.CABINA_PARTENZA));

		for (Componente cabina : cabine) {
			try {
				((CabinaDiEquipaggio) cabina).aggiungi(TipoPedina.ASTRONAUTA);
				io.stampa("" + formattatore.formatta(TipoPedina.ASTRONAUTA) + " posizionato in: " + componentiRender.rappresentazioneCompletaComponente(cabina) );
				return true;
			} catch (ComponentePienoException e) {
			}
		}
		return false;
	}

	public boolean consumaEnergia() {

		if (nave.getEnergia() <= 0)
			return false;

		boolean sceltaValida;
		do {
			sceltaValida = true;
			io.aCapo();
			io.stampa("Scegliere da quale vano rimuovere l'energia");

			Coordinate posizioneVanoBatteria = nave.getAnalizzatoreNave().scegliComponente(TipoComponente.VANO_BATTERIA);

			if (posizioneVanoBatteria == null)
				return false;

			Componente vanoBatteria = nave.getOriginaleComponente(posizioneVanoBatteria);

			try {
				((VanoBatteria) vanoBatteria).scaricaBatteria();
			} catch (ComponenteVuotoException e) {
				io.stampa("Non è possibile rimuovere energia da questo vano");
				io.stampa("scegliere un vano carico");
				sceltaValida = false;
			}
		} while (!sceltaValida);

		return true;
	}

	// indicare la direzione verso cui si vuole attivare lo scudo
	public boolean attivaScudo(Direzione dir) {
		if (nave.getEnergia() <= 0)
			return false;

		List<Componente> scudi = nave.getCopiaComponenti(TipoComponente.SCUDO);

		for (Componente scudo : scudi) {
			Direzione[] direzioni = ((GeneratoreDiScudi) scudo).getDirezioni();
			if (Arrays.asList(direzioni).contains(dir)) {

				io.stampa("scrivere 1 se si vuole attivare lo scudo");
				if (io.leggiIntero() == 1) {
					return consumaEnergia(); // usaEnergia valuta se è possibile o meno usare 1 di energia e la consuma
				}
				return false; // scudo non attivato per scelta dell'utente
			}
		}
		// non sono presenti scudi
		return false;
	}

	public void preparaEquipaggioAlVolo() {
		// carico gli alieni
		io.stampa("Posizionamento equipaggio in nave");
		posizionaEquipaggioInNave(TipoPedina.ALIENO_MARRONE);
		posizionaEquipaggioInNave(TipoPedina.ALIENO_VIOLA);

		// carico gli astronauti
		posizionaAstronatuaInNave();
	}

	// carica al massimo tutte le batterie
	public void ricaricaBatterie() {
		io.stampa("Ricarica batterie");
		List<Componente> batterie = nave.getComponentiOriginali(TipoComponente.VANO_BATTERIA);
		for (Componente batteria : batterie) {
			((VanoBatteria) batteria).caricaInteramenteBatteria();
		}
	}
}
