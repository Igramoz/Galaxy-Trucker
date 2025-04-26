package main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

import servizi.*;
import model.componenti.*;
import model.nave.TipoNave;
import grafica.*;
import grafica.renderer.ComponenteRenderer;
import grafica.renderer.NaveRenderer;
import io.GestoreIO;
import model.Giocatore;
import model.carte.PioggiaDiMeteoriti;
import model.carte.pianeti.CartaPianeti;
import partita.LivelliPartita;
import partita.fasiGioco.FineGioco;
import partita.fasiGioco.composizioneNave.*;

import partita.Partita;

public class Main {

	public static void main(String[] args) {

		NaveRenderer naveRenderer = new NaveRenderer();
		ComponenteRenderer componenteRenderer = new ComponenteRenderer();
		ServizioCarte servizio = new ServizioCarte(LivelliPartita.LIVELLO_3);

		GestoreIO io = new GestoreIO();

		// esempio di com'è la parte di composizione Nave

		Giocatore[] g = new Giocatore[3];

		g[0] = new Giocatore("Giovanni", Colore.ROSSO);
		g[1] = new Giocatore("Francesco", Colore.GIALLO);
		g[2] = new Giocatore("Matteo", Colore.VERDE);
		/*
		 * List<PioggiaDiMeteoriti> l =
		 * servizio.generaMeteoriti(LivelliPartita.LIVELLO_3); PioggiaDiMeteoriti p =
		 * l.remove(0); PioggiaDiMeteoriti p1 = l.remove(0);
		 * 
		 * // Per testare la pioggia di meteoriti ComposizioneNave composizione = new
		 * ComposizioneNave c = new ComposizioneNave(g, LivelliPartita.LIVELLO_3);
		 * io.stampa("Composizione nave");
		 * 
		 * c.start();
		 * 
		 * p.eseguiEvento(g); p1.eseguiEvento(g);
		 */

		g[0].setNave(TipoNave.NAVE_1);
		g[1].setNave(TipoNave.NAVE_1);
		g[2].setNave(TipoNave.NAVE_1);
		List<CartaPianeti> lista = servizio.generaCartaPianeti(LivelliPartita.LIVELLO_1);
		lista.get(0).eseguiEvento(g);

//		Partita p = new Partita();
//		p.gioca();
	}
}