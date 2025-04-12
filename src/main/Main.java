package main;

import servizi.ServizioAssemblaggio;
import componenti.*;
import fasidigioco.Inizializzazione;
import grafica.ConvertitoreGrafica;
import grafica.GestoreGrafica;

public class Main {

	public static void main(String[] args) {

		ConvertitoreGrafica grafica = new ConvertitoreGrafica();
		ServizioAssemblaggio servizio = new ServizioAssemblaggio();
		GestoreGrafica gestoreGrafica = new GestoreGrafica();

		gestoreGrafica.stampa( grafica.legendaComponenti() );

		
		
		
		Inizializzazione ini = new Inizializzazione();
		// ini.start();
	}
}