package main;

import servizi.ServizioAssemblaggio;
import componenti.*;
import fasidigioco.Inizializzazione;
import grafica.ConvertitoreGrafica;
import grafica.GestoreGrafica;
import nave.*;

public class Main {

	public static void main(String[] args) {

		ConvertitoreGrafica grafica = new ConvertitoreGrafica();
		ServizioAssemblaggio servizio = new ServizioAssemblaggio();
		GestoreGrafica gestoreGrafica = new GestoreGrafica();

		Nave n = new Nave(TipoNave.NAVE_1);
		gestoreGrafica.stampa( grafica.rappresentazioneNave(n) );

		
		 
		
		Inizializzazione ini = new Inizializzazione();
		// ini.start();
	}
}