
package io;

import grafica.GraficaConfig;
import grafica.TextAligner;

public class GestoreOutput {


	// Classe che gestisce la stampa

	public void aCapo() {
		stampa("");
	}

	// Metodi da usare per stampare a video
	public void stampa(String riga) {

		if (riga == null) {
			return;
		}

		TextAligner txtAligner = new TextAligner();
		riga = txtAligner.alignLeft(riga) + GraficaConfig.A_CAPO;

		System.out.println(riga);
	}

	public void stampa(String[] righeDaStampare) {

		if (righeDaStampare == null) {
			return;
		}

		for (String riga : righeDaStampare) {
			stampa(riga);
		}
	}

	public int stampaMenu(String[] menu) {
		// stampa il menu e riporta la risposta dell'utente sia compresa tra 0 e
		// menu.length - 1
		GestoreInput input = new GestoreInput();
		int scelta;
		boolean sceltaValida;
		do {
			sceltaValida = true;
			stampa(menu);
			stampa("Inserire il numero corrispondente all'azione:");

			scelta = input.leggiIntero();

			if(scelta < 0 || scelta >= menu.length) {
				stampa("Inserire un numero compreso tra 0 e " + (menu.length-1));
				sceltaValida = false;
			}
			
		} while (!sceltaValida);
		return scelta;
	}

	// TODO definire una funzione per pulire lo schermo (deve funzionare per tutti i
	// sistemi operativi e per la console di Eclipse
	// salvare la schermata in quella precedente prima di aggiornarla

//    private void clearScreen() {
//		//System.out.print("\033[H\033[2J");
//		//System.out.flush();
//	}

}
