package model.carte.criteriEffetti;

import java.util.List;

import grafica.FormattatoreGrafico;
import grafica.renderer.NaveRenderer;
import io.GestoreIO;
import model.Giocatore;
import model.carte.colpo.Colpo;
import model.enums.TipoMerce;
import partita.fasiGioco.volo.ManagerDiVolo;
import servizi.ServizioDadi;

public enum Effetto {
	/**
	 * Applica una serie di colpi alla nave del giocatore.
	 * <p>
	 * <b>Tipo richiesto per {@code valore}:</b> {@code List<Colpo>}
	 * </p>
	 * 
	 * @param manager il gestore del giocatore da colpire
	 * @param lista   di colpi che la nave deve subire
	 */
	COLPI {
		@Override // giocatore che subisce i colpi e lista dei colpi subiti
		public void applica(ManagerDiVolo manager, Object valore) {
			ServizioDadi servizioDadi = new ServizioDadi();
			NaveRenderer renderer = new NaveRenderer();
			Giocatore g = manager.getGiocatore();
			@SuppressWarnings("unchecked")
			List<Colpo> colpi = (List<Colpo>) valore;
			for (Colpo c : colpi) {
				int posizioneColpo = servizioDadi.lancia2Dadi(g);
				int pezziDistrutti = g.getNave().subisciImpatto(c, posizioneColpo);
				manager.incrementaPezziDistrutti(pezziDistrutti);
			}
			io.aCapo();
			io.stampa("Questa è la nave di " + formattatoreGrafico.formattaGiocatore(g) + " dopo aver subito i colpi:");
			io.stampa(renderer.rappresentazioneNave(g.getNave()));
		}
	},
	/**
	 * Rimuove un certo numero di membri dell'equipaggio dalla nave del giocatore.
	 * <p>
	 * <b>Tipo richiesto per {@code valore}:</b> {@code Integer}
	 * </p>
	 * 
	 * @param manager il gestore del giocatore penalizzato
	 * @param numero  di membri dell'equipaggio da rimuovere
	 */
	PERDITA_EQUIPAGGIO {
		@Override // giocatore che perde le merci e numero di membri dell'equipaggio persi
		public void applica(ManagerDiVolo manager, Object valore) {
			Giocatore g = manager.getGiocatore();
			int n = (Integer) valore;
			io.stampa(formattatoreGrafico.formattaGiocatore(g) + " perderà " + n + " membri dell'equipaggio");
			g.getNave().rimuoviEquipaggio(n);
		}
	},
	/**
	 * Rimuove un certo numero di merci dalla nave del giocatore.
	 * <p>
	 * <b>Tipo richiesto per {@code valore}:</b> {@code Integer}
	 * </p>
	 * 
	 * @param manager il gestore del giocatore penalizzato
	 * @param numero  di merci da rimuovere
	 */
	PERDITA_MERCE {
		@Override // giocatore che perde le merci e numero di merci perse
		public void applica(ManagerDiVolo manager, Object valore) {
			Giocatore g = manager.getGiocatore();
			int n = (Integer) valore;
			io.stampa(formattatoreGrafico.formattaGiocatore(g) + " perderà " + n + " merci");
			g.getNave().rimuoviMerce(n);
		}
	},
	/**
	 * Riduce i giorni di volo disponibili per il giocatore.
	 * <p>
	 * <b>Tipo richiesto per {@code valore}:</b> {@code Integer}
	 * </p>
	 * 
	 * @param manager il gestore del giocatore penalizzato
	 * @param numero  di giorni di volo da sottrarre
	 */
	GIORNI_VOLO {
		@Override // giocatore che perde i giorni di volo e numero di giorni di volo persi
		public void applica(ManagerDiVolo manager, Object valore) {
			int n = (Integer) valore;
			io.stampa(formattatoreGrafico.formattaGiocatore(manager.getGiocatore()) + " perderà " + n
					+ " giorni di volo");
			manager.spostaGiocatore(-n);
		}
	},
	/**
	 * Aggiunge crediti al giocatore.
	 * <p>
	 * <b>Tipo richiesto per {@code valore}:</b> {@code Integer}
	 * </p>
	 * 
	 * @param manager il gestore del giocatore premiato
	 * @param numero  di crediti da aggiungere
	 */
	GUADAGNA_CREDITI {
		@Override // giocatore che guadagna i crediti e numero di crediti guadagnati
		public void applica(ManagerDiVolo manager, Object valore) {
			Giocatore g = manager.getGiocatore();
			int n = (Integer) valore;
			io.stampa(formattatoreGrafico.formattaGiocatore(g) + " guadagna " + n + " crediti");
			g.aggiungiCrediti(n);
		}
	},
	/**
	 * Aggiunge una lista di merci alla nave del giocatore.
	 * <p>
	 * <b>Tipo richiesto per {@code valore}:</b> {@code List<TipoMerce>}
	 * </p>
	 * 
	 * @param manager il gestore del giocatore premiato
	 * @param lista   di merci da aggiungere
	 */
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

	/**
	 * Posizionare il cursore sulla costante per vedere il tipo della seconda
	 * costante. Applica l'effetto della carta. Il tipo del secondo argomento
	 * {@code Object valore} deve coincidere con quello nella javadoc, altrimenti
	 * verrà lanciato {@code ClassCastException} a runtime.
	 *
	 * @param manager il {@code ManagerDiVolo} del giocatore a cui applicare
	 *                l'effetto
	 * @param valore  il parametro dell'effetto che dipende dalla costante
	 */
	public abstract void applica(ManagerDiVolo manager, Object valore);

	protected GestoreIO io = new GestoreIO();
	protected FormattatoreGrafico formattatoreGrafico = new FormattatoreGrafico();
}
