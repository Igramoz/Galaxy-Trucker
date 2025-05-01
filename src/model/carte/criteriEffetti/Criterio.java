package model.carte.criteriEffetti;

import partita.fasiGioco.ManagerDiVolo;

public enum Criterio {
	/**
	 * Restituisce il ManagerDiVolo la cui nave ha il minor numero di membri dell'equipaggio.
	 */
	EQUIPAGGIO {
		@Override
		public ManagerDiVolo trovaPeggiore(ManagerDiVolo[] listaManager) {
			int minEquipaggio = Integer.MAX_VALUE; // sono sicuro che i giocatori avranno un equipaggio minore di questa
			ManagerDiVolo peggiorManager = null;

			for (ManagerDiVolo m : listaManager) {
				int equipaggio = m.getGiocatore().getNave().getEquipaggio().size();
				if (equipaggio < minEquipaggio) {
					minEquipaggio = equipaggio;
					peggiorManager = m;
				}
			}

			return peggiorManager;
		}
	},
	/**
	 * Restituisce il ManagerDiVolo la cui nave ha la potenza motrice minore.
	 */	POTENZA_MOTRICE {
		@Override
		public ManagerDiVolo trovaPeggiore(ManagerDiVolo[] listaManager) {
			int minPotenza = Integer.MAX_VALUE; // sono sicuro che i giocatori avranno una potenza motrice minore di
												// questa
			ManagerDiVolo peggiorManager = null;

			for (ManagerDiVolo m : listaManager) {
				int potenza = m.getGiocatore().getNave().getPotenzaMotrice();
				if (potenza < minPotenza) {
					minPotenza = potenza;
					peggiorManager = m;
				}
			}
			return peggiorManager;
		}
	},
		/**
		 * Restituisce il ManagerDiVolo la cui nave ha la potenza di fuoco minore.
		 */
	 POTENZA_FUOCO {
		@Override
		public ManagerDiVolo trovaPeggiore(ManagerDiVolo[] listaManager) {
			float minPotenza = Float.MAX_VALUE; // sono sicuro che i giocatori avranno una potenza di fuoco minore di
												// questa
			ManagerDiVolo peggiorManager = null;

			for (ManagerDiVolo m : listaManager) {
				float potenza = m.getGiocatore().getNave().getPotenzaFuoco();
				if (potenza < minPotenza) {
					minPotenza = potenza;
					peggiorManager = m;
				}
			}
			return peggiorManager;
		}
	};

	public abstract ManagerDiVolo trovaPeggiore(ManagerDiVolo[] listaManager);
}