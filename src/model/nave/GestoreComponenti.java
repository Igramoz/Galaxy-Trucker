package model.nave;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import io.GestoreIO;
import model.componenti.*;
import model.enums.*;
import util.layout.Coordinate;
import util.layout.Direzione;

public class GestoreComponenti {
// classe che interagisce con i setter e i getter per gestire l'inserimento o la rimozione di merci/energia/equipaggio dalla nave

	
	// funzione per scegliere un componente della nave
	private Nave nave;
	GestoreIO io = new GestoreIO();

	public GestoreComponenti(Nave nave) {
		this.nave = nave;
	}
	
	public Coordinate scegliComponente(TipoComponente tipoComponente1, TipoComponente tipoComponente2) {
		List<Componente> componenti = nave.getCopiaComponenti(tipoComponente1);
		if (tipoComponente2 != null) {
			componenti.addAll(nave.getCopiaComponenti(tipoComponente2));
		}
		componenti.addAll(nave.getCopiaComponenti(tipoComponente2));

		if (componenti.isEmpty())
			return null;

		return io.menuComponenti(componenti).getPosizione();
	}
	
	private Coordinate scegliComponente(TipoComponente tipoComponente1) {
		return scegliComponente(tipoComponente1, null);
	}


	// restituisce false se non posiziona tutte le merci
	public boolean posizionaMerciInNave( List<TipoMerce> merci) {
 

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
			io.stampa("Posiziona la merce " + merce.name());
			sceltaValida = true;
			Coordinate posizione = null;
			if (merce == TipoMerce.ROSSO) {
				posizione = scegliComponente(TipoComponente.STIVA_SPECIALE);
			} else {
				posizione = scegliComponente(TipoComponente.STIVA_SPECIALE, TipoComponente.STIVA);
			}

			if (!nave.forzaMerce(merce, posizione)) {
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
				posizione = scegliComponente(TipoComponente.STIVA_SPECIALE);
			} else {
				posizione = scegliComponente(TipoComponente.STIVA_SPECIALE, TipoComponente.STIVA);
			}

			Stiva stiva = (Stiva) nave.getOriginaleComponente(posizione);

			if (stiva.eliminaMerce(merce)) {
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
				posizione = scegliComponente( TipoComponente.CABINA_EQUIPAGGIO, TipoComponente.CABINA_PARTENZA);
			} else {
				posizione = scegliComponente( TipoComponente.CABINA_EQUIPAGGIO);
			}

			if (!nave.forzaEquipaggio(pedinaDaRimuovere, posizione)) {
				io.stampa("Non è possibile rimuovere la pedina da questo componente");
				String[] menu = { "Riprova", "Non rimuovere pedina" };
				int scelta = io.stampaMenu(menu);
				if (scelta == 1)
					return false;
			}
		} while (!sceltaValida);
		return true;
	}

	public boolean posizionaAlienoInNave(TipoPedina pedina) {
 
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
			cabineCollegate.addAll(ottieniCabineEquipaggioCollegate(nave, sovrastruttura));
		}

		boolean sceltaValida;
		do {
			// lascio all'utente la possibilità di scegliere in quale cabina posizionare
			// l'alieno
			Componente cloneCabina = io.menuComponenti(cabineCollegate);
			if (cloneCabina == null) {
				return false;
			}
			Componente cabina = nave.getOriginaleComponente(cloneCabina.getPosizione());
			if (((CabinaDiEquipaggio) cabina).aggiungiEquipaggio(pedina)) {
				return true; // alieno posizionato correttamente
			} else {
				io.stampa("Non è possibile posizionare l'alieno in questa cabina");
				String[] menu = { "Riprova", "Non posizionare l'alieno" };
				int scelta = io.stampaMenu(menu);
				if (scelta == 1)
					return false;
				else
					sceltaValida = false;
			}
		} while (!sceltaValida);
		return false;
	}

	// funzione che data una sovrastruttura restitusce una lista con tutte le cabine
	// di equipaggio collegate
	private List<Componente> ottieniCabineEquipaggioCollegate(Nave nave, Componente sovrastruttura) {
		List<Componente> cabineCollegate = new ArrayList<>();

		Map<Direzione, Componente> componentiAdiacenti = nave
				.getCopiaComponentiAdiacenti(sovrastruttura.getPosizione());

		// rimuovo i componenti non collegati da tubi
		Map<Direzione, TipoTubo> tubiSovrastruttura = sovrastruttura.getTubi();

		for (Map.Entry<Direzione, TipoTubo> entry : tubiSovrastruttura.entrySet()) {
			if (entry.getValue() != TipoTubo.NESSUNO) {
				if (componentiAdiacenti.get(entry.getKey()).getTipo() == TipoComponente.CABINA_EQUIPAGGIO) {
					cabineCollegate.add(componentiAdiacenti.get(entry.getKey()));
				}
			}
		}
		return cabineCollegate;
	}

	// posiziona l'altronauta in una qualunque cabina d'equipaggio o di partenza
	public boolean posizionaAstronatuaInNave() {
		if (nave.isEquipaggioCompleto())
			return false;

		List<Componente> cabine = nave.getComponentiOriginali(TipoComponente.CABINA_EQUIPAGGIO);
		cabine.addAll(nave.getComponentiOriginali(TipoComponente.CABINA_PARTENZA));

		for (Componente cabina : cabine) {
			if (((CabinaDiEquipaggio) cabina).aggiungiEquipaggio(TipoPedina.ASTRONAUTA)) {
				return true;
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

			Coordinate posizioneVanoBatteria = scegliComponente(TipoComponente.VANO_BATTERIA);

			if (posizioneVanoBatteria == null)
				return false;

			Componente vanoBatteria = nave.getOriginaleComponente(posizioneVanoBatteria);

			if (!((VanoBatteria) vanoBatteria).scaricaBatteria()) {
				io.stampa("Non è possibile rimuovere energia da questo vano");
				io.stampa("scegliere un vano carico");
				sceltaValida = false;
			}
		} while (!sceltaValida);

		return true;
	}

	//indicare la direzione verso cui si vuole attivare lo scudo
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
		posizionaAlienoInNave( TipoPedina.ALIENO_MARRONE);
		posizionaAlienoInNave( TipoPedina.ALIENO_VIOLA);

		// carico gli astronauti
		posizionaAstronatuaInNave();
	}
	
	// carica al massimo tutte le batterie
	public void ricaricaBatterie() {
		List<Componente> batterie = nave.getCopiaComponenti(TipoComponente.VANO_BATTERIA);
		for (Componente batteria : batterie) {
			((VanoBatteria) batteria).caricaInteramenteBatteria();
		}
	}
}
