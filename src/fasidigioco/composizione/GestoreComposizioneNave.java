package fasidigioco.composizione;

import componenti.Componente;
import model.Giocatore;
import model.enums.LivelloPartita;
import servizi.ServizioAssemblaggio;
import grafica.*;
import nave.Nave;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class GestoreComposizioneNave {

	private final Giocatore giocatore;
	private List<Componente> pezziPrenotati;
	private final ServizioAssemblaggio servizioAssemblaggio;
	private final GestoreGrafica gestoreGrafica;
	
	public GestoreComposizioneNave(Giocatore giocatore, LivelloPartita livello, ServizioAssemblaggio servizioAssemblaggio) {
		this.giocatore = giocatore;
		this.servizioAssemblaggio = servizioAssemblaggio;
		
		giocatore.setNave(livello.getTipoNave());
		gestoreGrafica = new GestoreGrafica();
		
	}
	
	public boolean gestisciTurno(List<Componente> componentiScartati) {
		
		TextAligner txtAligner = new TextAligner();
		ConvertitoreGrafica convertitore = new ConvertitoreGrafica(); 
		gestoreGrafica.stampa(convertitore.rappresentazioneNave(giocatore.getNave()));
		
		return true;		
	}
	
	private int mostraMenu() {

		String[] azioniDisponibili = {
		            "1 - Guarda carte",
		            "2 - Estrai componente nuovo",
		            "3 - Usa un componente già estratto",
		            "4 - Usa un componente prenotato",
		            "5 - Rimuovi un componente dalla nave",
		            "0 - Passa"
		        };
		gestoreGrafica.stampa(azioniDisponibili);
		
		// Leggi la scelta dell'utente
        Scanner scanner = new Scanner(System.in);
        System.out.print("Scegli un'opzione: ");
        return scanner.nextInt();
	}
	
	  // Azioni corrispondenti alle scelte del menu

    private void guardaCarte() {
        // da implementare: interazione con servizio carte e selezione mazzetti
        System.out.println("Mostro le carte disponibili...");
    }

    private void estraiNuovoComponente() {
        Componente estratto = servizioAssemblaggio.estraiComponente();
        System.out.println("Hai estratto: " + estratto);
        // rotazione, posizionamento, conferma
        pezziPrenotati.add(estratto); // in attesa di posizionamento
    }

    private void usaComponenteEstratto() {
        // da implementare: mostrare pool di componenti già estratti dai mazzetti
        System.out.println("Scegli un componente estratto da usare...");
    }

    private void usaComponentePrenotato() {
        if (pezziPrenotati.isEmpty()) {
            System.out.println("Nessun componente prenotato.");
            return;
        }

        System.out.println("Componenti prenotati disponibili:");
        for (int i = 0; i < pezziPrenotati.size(); i++) {
            System.out.println((i + 1) + ": " + pezziPrenotati.get(i));
        }

        int scelta = 0;// InputUtil.leggiInt("Scegli il componente da usare: ") - 1;

        if (scelta >= 0 && scelta < pezziPrenotati.size()) {
            Componente scelto = pezziPrenotati.remove(scelta);
            // TODO: ruotare, posizionare, validare
            System.out.println("Hai selezionato: " + scelto);
        } else {
            System.out.println("Scelta non valida.");
        }
    }

    private void rimuoviComponente() {
        // da implementare: scelta coordinate e rimozione da nave
        System.out.println("Seleziona componente da rimuovere...");
    }
}