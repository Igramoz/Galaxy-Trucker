package model.nave;

import java.util.List;

import grafica.*;
import grafica.renderer.ComponenteRenderer;
import io.GestoreIO;
import model.componenti.*;
import model.enums.TipoMerce;
import model.enums.TipoPedina;
import util.Coordinate;

public interface GestoreComponenti {
// interfaccia che interagisce con i setter e i getter per gestire l'inserimento o la rimozione di merci/energia/equipaggio dalla nave

	// funzione per scegliere un componente della nave
	default Coordinate scegliComponente(Nave nave, TipoComponente tipoComponente1, TipoComponente tipoComponente2) {

		GestoreIO io = new GestoreIO();
		FormattatoreGrafico formattatoreGrafico = new FormattatoreGrafico();
		ComponenteRenderer componenteRenderer = new ComponenteRenderer();

		List<Componente> componenti = nave.getCopiaComponenti(tipoComponente1);
		if (tipoComponente2 != null) {
			componenti.addAll(nave.getCopiaComponenti(tipoComponente2));
		}
		componenti.addAll(nave.getCopiaComponenti(tipoComponente2));

		String[] menu = new String[componenti.size()];
		for (int i = 0; i < componenti.size(); i++) {
			menu[i] = formattatoreGrafico.formattaCoordinate(componenti.get(i).getPosizione()) + " "
					+ componenteRenderer.rappresentazioneCompletaComponente(componenti.get(i));
		}

		io.stampa("Scegli il componente in base alla posizione");
		int scelta = io.stampaMenu(menu);

		return componenti.get(scelta).getPosizione();
	}

	default Coordinate scegliComponente(Nave nave, TipoComponente tipoComponente1) {
		return scegliComponente(nave, tipoComponente1, null);
	}

	// restituisce false se non posiziona tutte le merci
	default boolean posizionaMerciInNave(Nave nave, List<TipoMerce> merci) {
		GestoreIO io = new GestoreIO();

		if (merci == null || merci.size() == 0) {
			return false;
		}
		io.stampa("Posiziona le merci in stiva una ad una");

		boolean output = true;
		for (TipoMerce merce : merci) {
			// se anche una sola merce non viene posizionata la funzione restituisce false
			if (!posizionaMerce(nave, merce))
				output = false;
		}
		return output;
	}

	default boolean posizionaMerce(Nave nave, TipoMerce merce) {
		GestoreIO io = new GestoreIO();
		boolean sceltaValida;
		do {
			io.stampa("Posiziona la merce " + merce.name());
			sceltaValida = true;
			Coordinate posizione = null;
			if (merce == TipoMerce.ROSSO) {
				posizione = scegliComponente(nave, TipoComponente.STIVA_SPECIALE);
			} else {
				posizione = scegliComponente(nave, TipoComponente.STIVA_SPECIALE, TipoComponente.STIVA);
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
					while (!rimuoviMerceDaNave(nave)) {
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
	default boolean rimuoviMerceDaNave(Nave nave, TipoMerce merce) {
		GestoreIO io = new GestoreIO();

		if (nave.getMerci().size() == 0) {
			io.stampa("Non ci sono merci da rimuovere");
			return false;
		}

		io.stampa("Rimuovi la merce " + merce.name());
		do {
			Coordinate posizione = null;
			if (merce == TipoMerce.ROSSO) {
				posizione = scegliComponente(nave, TipoComponente.STIVA_SPECIALE);
			} else {
				posizione = scegliComponente(nave, TipoComponente.STIVA_SPECIALE, TipoComponente.STIVA);
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
	default boolean rimuoviMerceDaNave(Nave nave) {
		GestoreIO io = new GestoreIO();

		io.stampa("Scegli quanta merce rimuovere");
		int quantita = io.leggiIntero();
		boolean output = true;
		for (int i = 0; i < quantita; i++) {
			io.stampa("merce numero: " + (i + 1));

			TipoMerce merce = io.leggiEnum(TipoMerce.class);
			if (!rimuoviMerceDaNave(nave, merce))
				output = false;
		}
		return output;
	}

	default boolean rimuoviEquipaggioDaNave(Nave nave) {
		GestoreIO io = new GestoreIO();
		// salvo le cabine
		List<Componente> cabine = nave.getComponentiOriginali(TipoComponente.CABINA_EQUIPAGGIO);
		cabine.addAll(nave.getComponentiOriginali(TipoComponente.CABINA_PARTENZA));

		if (nave.getEquipaggio().isEmpty())
			return false;

		boolean sceltaValida;
		do {
			sceltaValida = true;

			// lascio all'utente scegliere quale pedina rimuovere
			io.stampa("Scegliere la pedina da rimuovere: ");
			TipoPedina pedinaDaRimuovere = io.leggiEnum(TipoPedina.class);
			// rimuovo le pedine del tipo scelto dall'utente

			// lascio scegliere all'utente da quale cabina rimuovere la pedina
			io.stampa("Scegliere la cabina da cui rimuovere la pedina: ");
			Coordinate posizione = null;
			if (pedinaDaRimuovere == TipoPedina.ASTRONAUTA) {
				posizione = scegliComponente(nave, TipoComponente.CABINA_EQUIPAGGIO, TipoComponente.CABINA_PARTENZA);
			} else {
				posizione = scegliComponente(nave, TipoComponente.CABINA_EQUIPAGGIO);
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
}
