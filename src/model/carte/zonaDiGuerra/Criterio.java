package model.carte.zonaDiGuerra;

import model.Giocatore;

public enum Criterio {
	// minore membri numero di equipaggio
	EQUIPAGGIO {
		@Override
		public Giocatore trovaPeggiore(Giocatore[] giocatori) {
			int minEquipaggio = Integer.MAX_VALUE; // sono sicuro che i giocatori avranno un equipaggio minore di questa
			Giocatore peggiorGiocatore = null;

			for (Giocatore g : giocatori) {
				int equipaggio = g.getNave().getEquipaggio().size();
				if (equipaggio < minEquipaggio) {
					minEquipaggio = equipaggio;
					peggiorGiocatore = g;
				}
			}

			return peggiorGiocatore;
		}
	},
	// minore potenza motrice
	POTENZA_MOTRICE {
		@Override
		public Giocatore trovaPeggiore(Giocatore[] giocatori) {
			int minPotenza = Integer.MAX_VALUE; // sono sicuro che i giocatori avranno una potenza motrice minore di
												// questa
			Giocatore peggiorGiocatore = null;

			for (Giocatore g : giocatori) {
				int potenza = g.getNave().getPotenzaMotrice();
				if (potenza < minPotenza) {
					minPotenza = potenza;
					peggiorGiocatore = g;
				}
			}
			return peggiorGiocatore;
		}
	},
	// minore potenza di fuoco
	POTENZA_FUOCO {
		@Override
		public Giocatore trovaPeggiore(Giocatore[] giocatori) {
			float minPotenza = Float.MAX_VALUE; // sono sicuro che i giocatori avranno una potenza di fuoco minore di
												// questa
			Giocatore peggiorGiocatore = null;

			for (Giocatore g : giocatori) {
				float potenza = g.getNave().getPotenzaFuoco();
				if (potenza < minPotenza) {
					minPotenza = potenza;
					peggiorGiocatore = g;
				}
			}
			return peggiorGiocatore;
		}
	};

	public abstract Giocatore trovaPeggiore(Giocatore[] giocatori);
}
