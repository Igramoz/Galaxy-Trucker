package model.carte.criteriEffetti;

import java.util.List;

import grafica.FormattatoreGrafico;
import io.GestoreIO;
import model.Colpo;
import model.Dado;
import model.Giocatore;

public enum Effetto {
	COLPI {
		@Override
		public void applica(Giocatore g, Object valore) {
			@SuppressWarnings("unchecked")
			List<Colpo> colpi = (List<Colpo>) valore;
			for (Colpo c : colpi) {
				int posizioneColpo = Dado.lancia2Dadi(g);
				int pezziDistrutti = g.getNave().subisciImpatto(c, posizioneColpo);
				g.incrementaPezziDistrutti(pezziDistrutti);
			}
		}
	},
	EQUIPAGGIO {
		@Override
		public void applica(Giocatore g, Object valore) {
			int n = (Integer) valore;
			io.stampa(formattatoreGrafico.formattaGiocatore(g) + " perderà " + n + " membri dell'equipaggio");
			g.getNave().rimuoviEquipaggio(n);
		}
	},
	MERCE {
		@Override
		public void applica(Giocatore g, Object valore) {
			int n = (Integer) valore;
			io.stampa(formattatoreGrafico.formattaGiocatore(g) + " perderà " + n + " merci");
			g.getNave().rimuoviMerce(n);
		}
	},
	GIORNI_VOLO {
		@Override
		public void applica(Giocatore g, Object valore) {
			int n = (Integer) valore;
			io.stampa(formattatoreGrafico.formattaGiocatore(g) + " perderà " + n + " giorni di volo");
			// TODO rimuovere giorni di volo
		}
	};

	public abstract void applica(Giocatore g, Object valore);

	protected GestoreIO io = new GestoreIO();
	protected FormattatoreGrafico formattatoreGrafico = new FormattatoreGrafico();
}
