package fasidigioco.composizione;


import java.util.ArrayList;
import java.util.List;

import componenti.Componente;
import model.Giocatore;
import model.enums.LivelloPartita;
import servizi.ServizioAssemblaggio;
import grafica.GestoreGrafica;

// Fase del gioco in cui si compongno le navi
public class ComposizioneNave {


	private final ServizioAssemblaggio servizioAssemblaggio; // Servizio di assemblaggio delle navi
    private final GestoreComposizioneNave[] gestori; 
    private List<Giocatore> ordineFine;
    private final LivelloPartita livello;
	private List<Componente> componentiScartati = new ArrayList<>();
	
   
	public ComposizioneNave(Giocatore[] giocatori, LivelloPartita livello) {

		this.gestori = new GestoreComposizioneNave[giocatori.length];
		this.livello = livello;
		this.servizioAssemblaggio = new ServizioAssemblaggio(); // Inizializza il servizio di assemblaggio
		this.ordineFine = new ArrayList<>();
		
	        for (int i = 0; i < giocatori.length; i++) {
	            gestori[i] = new GestoreComposizioneNave(giocatori[i], livello, servizioAssemblaggio);
	        }
	}
	
	public List<Giocatore> start() {
		// Ogni giocatore compone la propria nave
		
		GestoreGrafica gestoreGrafica = new GestoreGrafica();
		gestoreGrafica.stampa("E' ora di comporre le navi");
		
		boolean faseTerminata = false;

        while (!faseTerminata) {        	
        	
            for (int i = 0; i < gestori.length; i++) {
            	if(gestori[i].getTurnoTerminato()) {
            		continue;
            	}
            	
            	if(gestori[i].gestisciTurno(componentiScartati)) {
            		ordineFine.add(gestori[i].getGiocatore());
            	}
            	
            	if(ordineFine.size() == gestori.length) {
            		faseTerminata = true;
            	}            	
            }
        }
    	return ordineFine;
    }
}
