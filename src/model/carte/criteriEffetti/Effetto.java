package model.carte.criteriEffetti;

import java.util.List;

import grafica.FormattatoreGrafico;
import grafica.renderer.NaveRenderer;
import io.GestoreIO;
import model.Dado;
import model.Giocatore;
import model.carte.colpo.Colpo;
import model.enums.TipoMerce;

public enum Effetto {
	COLPI {
		@Override // giocatore che subisce i colpi e lista dei colpi subiti
		public void applica(Giocatore g, Object valore) {
			NaveRenderer renderer = new NaveRenderer();
			@SuppressWarnings("unchecked")
			List<Colpo> colpi = (List<Colpo>) valore;
			for (Colpo c : colpi) {
				int posizioneColpo = Dado.lancia2Dadi(g);
				int pezziDistrutti = g.getNave().subisciImpatto(c, posizioneColpo);
				g.incrementaPezziDistrutti(pezziDistrutti);
			}
			io.aCapo();
			io.stampa("Questa è la nave di " + formattatoreGrafico.formattaGiocatore(g) + " dopo aver subito i colpi:");
			io.stampa(renderer.rappresentazioneNave(g.getNave()));
		}
	},
	PERDITA_EQUIPAGGIO {
		@Override // giocatore che perde le merci e numero di membri dell'equipaggio persi
		public void applica(Giocatore g, Object valore) {
			int n = (Integer) valore;
			io.stampa(formattatoreGrafico.formattaGiocatore(g) + " perderà " + n + " membri dell'equipaggio");
			g.getNave().rimuoviEquipaggio(n);
		}
	},
	PERDITA_MERCE {
		@Override // giocatore che perde le merci e numero di merci perse
		public void applica(Giocatore g, Object valore) {
			int n = (Integer) valore;
			io.stampa(formattatoreGrafico.formattaGiocatore(g) + " perderà " + n + " merci");
			g.getNave().rimuoviMerce(n);
		}
	},
	GIORNI_VOLO {
		@Override // giocatore che perde i giorni di volo e numero di giorni di volo persi
		public void applica(Giocatore g, Object valore) {
			int n = (Integer) valore;
			io.stampa(formattatoreGrafico.formattaGiocatore(g) + " perderà " + n + " giorni di volo");
			// TODO rimuovere giorni di volo
		}
	},
	GUADAGNA_CREDITI {
		@Override // giocatore che guadagna i crediti e numero di crediti guadagnati
		public void applica(Giocatore g, Object valore) {
			int n = (Integer) valore;
			io.stampa(formattatoreGrafico.formattaGiocatore(g) + " guadagna " + n + " crediti");
			g.aggiungiCrediti(n);
		}
	},
	GUADAGNA_MERCE {
		@Override // giocatore che guadagna le merci e numero di merci guadagnate
		public void applica(Giocatore g, Object valore) {
			@SuppressWarnings("unchecked")
			List<TipoMerce> merci = (List<TipoMerce>) valore;
			io.stampa(formattatoreGrafico.formattaGiocatore(g) + " ottiene " + merci.size() + " merci");
			g.getNave().setMerci(merci);
		}
	};

	public abstract void applica(Giocatore g, Object valore);

	protected GestoreIO io = new GestoreIO();
	protected FormattatoreGrafico formattatoreGrafico = new FormattatoreGrafico();
}
