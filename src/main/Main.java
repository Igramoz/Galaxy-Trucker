package main;

import java.util.*;

import cartaAvventura.PioggiaDiMeteoriti;
import servizi.*;
import componenti.*;
import grafica.*;
import io.GestoreIO;
import model.Giocatore;
import partita.LivelliPartita;
import partita.fasiGioco.FineGioco;
import partita.fasiGioco.composizioneNave.ComposizioneNave;

import partita.Partita;

public class Main {

	public static void main(String[] args) {

		NaveRenderer naveRenderer = new NaveRenderer();
		ComponenteRenderer componenteRenderer = new ComponenteRenderer();
		ServizioCarte servizio = new ServizioCarte();

		GestoreIO gestoreOutput = new GestoreIO();

		// esempio di alcuni elemnti generati a caso
//		List<Componente> lista = new ArrayList<>();
//		
//		Nave n = new Nave(TipoNave.NAVE_1);
//		gestoreOutput.stampa(grafica.rappresentazioneNave(n));
//		gestoreOutput.aCapo();
//		// Estrarre e rappresentare tutti i componenti
//		
//		for(int i = 0; i < 35; i++) {
//			lista.add( servizio.estraiComponente());
//
//		}
//		gestoreOutput.stampa(grafica.rappresentaComponenti(lista));

		// esempio di come un componente ruota
//		Componente c = servizio.estraiComponente();
//		for(int i = 0 ; i < 6; i++) {
//			c.ruota();
//			gestoreOutput.stampa(grafica.rappresentaComponente(c));
//		}
		// esempio di com'è la parte di composizione Nave
		/*
		 * Giocatore[] g = new Giocatore[3];
		 * 
		 * g[0] = new Giocatore("Giovanni", Colore.ROSSO ); g[1] = new
		 * Giocatore("Francesco", Colore.GIALLO); g[2] = new Giocatore("Matteo",
		 * Colore.VERDE);
		 * 
		 * 
		 * List<PioggiaDiMeteoriti> l =
		 * servizio.generaMeteoriti(LivelliPartita.LIVELLO_3); PioggiaDiMeteoriti p =
		 * l.remove(0);
		 * 
		 * p.eseguiEvento(g);
		 * 
		 * 
		 * 
		 * ComposizioneNave composizione = new ComposizioneNave(g,
		 * LivelliPartita.LIVELLO_3);
		 * 
		 * composizione.start();
		 */

		/*Giocatore[] giocatori = new Giocatore[3];
		giocatori[0] = new Giocatore("Matteo", Colore.ROSSO);
		giocatori[1] = new Giocatore("Henry", Colore.BLU);
		giocatori[2] = new Giocatore("Michele", Colore.VERDE);
		giocatori[0].aggiungiCrediti(2);
		giocatori[1].aggiungiCrediti(4);
		giocatori[2].aggiungiCrediti(6);
		FineGioco fine = new FineGioco(giocatori);
		fine.start();*/

		Partita p = new Partita();
		p.gioca();
	}
}