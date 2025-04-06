package main;


import servizi.ServizioAssemblaggio;
import componenti.*;
import grafica.ConvertitoreGrafica;
import grafica.GestoreGrafica;

public class Main {

	public static void main(String[] args) {
		System.out.println("Ciao Mondo!!");
		int d = 2;
		ConvertitoreGrafica grafica = new ConvertitoreGrafica();
		ServizioAssemblaggio servizio = new ServizioAssemblaggio();
		GestoreGrafica gestoreGrafica = new GestoreGrafica();
		
		Componente c = null;

		do {
			c = servizio.estraiComponente();
			
			if(c != null) {
				
				gestoreGrafica.stampa(grafica.rappresentaComponente(c)) ;
			}
		}while(c != null);
		
		
		System.out.println("Fine!!");
	}

}
