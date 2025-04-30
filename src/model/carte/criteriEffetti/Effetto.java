package model.carte.criteriEffetti;

import java.util.List;

import grafica.FormattatoreGrafico;
import grafica.renderer.NaveRenderer;
import io.GestoreIO;
import model.Giocatore;
import model.carte.colpo.Colpo;
import model.enums.TipoMerce;
import partita.fasiGioco.ManagerDiVolo;
import util.random.Dado;

public enum Effetto {
	COLPI {
		@Override // giocatore che subisce i colpi e lista dei colpi subiti
		public void applica(ManagerDiVolo manager, Object valore) {
			NaveRenderer renderer = new NaveRenderer();
			Giocatore g = manager.getGiocatore();
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
		public void applica(ManagerDiVolo manager, Object valore) {
			Giocatore g = manager.getGiocatore();
			int n = (Integer) valore;
			io.stampa(formattatoreGrafico.formattaGiocatore(g) + " perderà " + n + " membri dell'equipaggio");
			g.getNave().rimuoviEquipaggio(n);
		}
	},
	PERDITA_MERCE {
		@Override // giocatore che perde le merci e numero di merci perse
		public void applica(ManagerDiVolo manager, Object valore) {
			Giocatore g = manager.getGiocatore();
			int n = (Integer) valore;
			io.stampa(formattatoreGrafico.formattaGiocatore(g) + " perderà " + n + " merci");
			g.getNave().rimuoviMerce(n);
		}
	},
	GIORNI_VOLO {
		@Override // giocatore che perde i giorni di volo e numero di giorni di volo persi
		public void applica(ManagerDiVolo manager, Object valore) {
			int n = (Integer) valore;
			io.stampa(formattatoreGrafico.formattaGiocatore(manager.getGiocatore()) + " perderà " + n
					+ " giorni di volo");
			manager.aumentaGiorniDiVolo(-n);
		}
	},
	GUADAGNA_CREDITI {
		@Override // giocatore che guadagna i crediti e numero di crediti guadagnati
		public void applica(ManagerDiVolo manager, Object valore) {
			Giocatore g = manager.getGiocatore();
			int n = (Integer) valore;
			io.stampa(formattatoreGrafico.formattaGiocatore(g) + " guadagna " + n + " crediti");
			g.aggiungiCrediti(n);
		}
	},
	GUADAGNA_MERCE {
		@Override // giocatore che guadagna le merci e numero di merci guadagnate
		public void applica(ManagerDiVolo manager, Object valore) {
			Giocatore g = manager.getGiocatore();
			@SuppressWarnings("unchecked")
			List<TipoMerce> merci = (List<TipoMerce>) valore;
			io.stampa(formattatoreGrafico.formattaGiocatore(g) + " ottiene " + merci.size() + " merci");
			g.getNave().setMerci(merci);
		}
	};

	public abstract void applica(ManagerDiVolo manager, Object valore);

	protected GestoreIO io = new GestoreIO();
	protected FormattatoreGrafico formattatoreGrafico = new FormattatoreGrafico();
}
