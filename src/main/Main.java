package main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

import servizi.*;
import model.componenti.*;
import grafica.*;
import grafica.renderer.ComponenteRenderer;
import grafica.renderer.NaveRenderer;
import io.GestoreIO;
import model.Giocatore;
import model.carte.PioggiaDiMeteoriti;
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

		// esempio di com'è la parte di composizione Nave
		/*
		 * Giocatore[] g = new Giocatore[3];
		 * 
		 * g[0] = new Giocatore("Giovanni", Colore.ROSSO); g[1] = new
		 * Giocatore("Francesco", Colore.GIALLO); g[2] = new Giocatore("Matteo",
		 * Colore.VERDE);
		 * 
		 * List<PioggiaDiMeteoriti> l =
		 * servizio.generaMeteoriti(LivelliPartita.LIVELLO_3); PioggiaDiMeteoriti p =
		 * l.remove(0); PioggiaDiMeteoriti p1 = l.remove(0);
		 * 
		 * // Per testare la pioggia di meteoriti ComposizioneNave composizione = new
		 * ComposizioneNave(g, LivelliPartita.LIVELLO_3);
		 * 
		 * composizione.start();
		 * 
		 * p.eseguiEvento(g); p1.eseguiEvento(g);
		 */

		Partita p = new Partita();
		p.gioca();
	}
}