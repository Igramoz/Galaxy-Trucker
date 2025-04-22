package cartaAvventura;

import java.util.ArrayList;
import java.util.List;

import model.Giocatore;
import model.colpi.Cannonata;
import util.Coppia;

public class ZonaDiGuerra extends Carta {

	
	public enum Criterio {
	// minore membri numero di equipaggio
	    EQUIPAGGIO { 
	        @Override
	        public Giocatore trovaPeggiore(Giocatore[] giocatori) {
	        	int minEquipaggio = Integer.MAX_VALUE; // sono sicuro che i giocatori avranno un equipaggio minore di questa
	        	Giocatore peggiorGiocatore = null;
	        	
	        	for(Giocatore g : giocatori) {
	        		int equipaggio = g.getNave().getEquipaggio().size();
	        		if(equipaggio < minEquipaggio) {
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
	        	int minPotenza = Integer.MAX_VALUE; // sono sicuro che i giocatori avranno una potenza motrice minore di questa
	        	Giocatore peggiorGiocatore = null;
	        	
	        	for(Giocatore g : giocatori) {
	        		int potenza = g.getNave().getPotenzaMotrice();
	        		if(potenza < minPotenza) {
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
	        	float minPotenza = Float.MAX_VALUE; // sono sicuro che i giocatori avranno una potenza di fuoco minore di questa
	        	Giocatore peggiorGiocatore = null;
	        	
	        	for(Giocatore g : giocatori) {
	        		float potenza = g.getNave().getPotenzaFuoco();
	        		if(potenza < minPotenza) {
	        			minPotenza = potenza;
	        			peggiorGiocatore = g;
	        		}
	        	}
	            return peggiorGiocatore;
	        }
	    };

	    public abstract Giocatore trovaPeggiore(Giocatore[] giocatori);
	}

	private enum Penalita {
	    CANNONATE {
	        @Override
	        public <T> void applica(Giocatore g) {
	            List<Cannonata> cannonate = (List<Cannonata>) this.valore;
//	            for (Cannonata c : cannonate) {
//	                g.getNave().subisciCannonata(c, Direzione.DIETRO);
//	            }
	        }
	    },
	    EQUIPAGGIO {
	        @Override
	        public <T> void applica(Giocatore g) {
	            int n = (Integer) this.valore;
	            g.getNave().rimuoviEquipaggio(n);
	        }
	    },
	    MERCE {
	        @Override
	        public <T> void applica(Giocatore g) {
	            int n = (Integer) this.valore;
	            g.getNave().rimuoviMerce(n);
	        }
	    },
	    GIORNI_VOLO {
	        @Override
	        public <T> void applica(Giocatore g) {
	            int n = (Integer) this.valore;
	            // TODO rimuovere giorni di volo
	        }
	    };

	    public abstract <T> void applica(Giocatore g);

	    protected Object valore; // valore da passare alla penalità, ad esempio cannonate o equipaggio perso
	    
	    Penalita() {
	    }
	    
	    Penalita(Object valore) {
	        this.valore = valore;
	    }

	    public Object getValore() {
	        return valore;
	    }

	    public void setValore(Object valore) {
	        this.valore = valore;
	    }
	}
	
	List<Coppia<Criterio, Penalita>> criteriEpenalita;

	public ZonaDiGuerra(List<Coppia<Criterio, Penalita>> criteriEpenalita, List<Cannonata> cannonate) {
		super(TipoCarta.ZONA_DI_GUERRA);
		this.criteriEpenalita = criteriEpenalita;
	}

	public List<Coppia<Criterio, Penalita>> getCriteriEpenalita() {
		return new ArrayList<>(criteriEpenalita);
	}

	@Override
	void eseguiEvento(Giocatore[] giocatori) {

		for (Coppia<Criterio, Penalita> criterioEpenalita : criteriEpenalita) {

			Criterio criterio = criterioEpenalita.getElemento1();
			Penalita penalita = criterioEpenalita.getElemento2();

			Giocatore peggiorGiocatore = criterio.trovaPeggiore(giocatori);
			penalita.applica(peggiorGiocatore);			
		}
	}
}
