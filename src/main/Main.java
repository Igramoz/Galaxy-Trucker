package main;

import java.util.*;

import servizi.ServizioAssemblaggio;
import componenti.*;
import fasidigioco.composizione.*;
import grafica.*;
import io.GestoreOutput;
import nave.*;
import model.Giocatore;
import model.enums.LivelloPartita;

public class Main {

	public static void main(String[] args) {

		ConvertitoreGrafica grafica = new ConvertitoreGrafica();
		ServizioAssemblaggio servizio = new ServizioAssemblaggio();
		GestoreOutput gestoreOutput = new GestoreOutput();

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
		

		// esempio di com'è la parte di composizione  Nave
		Giocatore[] g = new Giocatore[3];
		
		g[0] = new Giocatore("Giovanni", Colore.ROSSO );
		g[1] = new Giocatore("Francesco", Colore.GIALLO);
		g[2] = new Giocatore("Matteo", Colore.VERDE);
		
		
		ComposizioneNave composizione = new ComposizioneNave(g, LivelloPartita.LIVELLO_1);
		
		composizione.start();
		
		
	}
}