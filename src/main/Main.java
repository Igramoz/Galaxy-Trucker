package main;

import java.util.*;

import servizi.ServizioAssemblaggio;
import componenti.*;
import fasidigioco.Inizializzazione;
import grafica.ConvertitoreGrafica;
import io.GestoreOutput;
import nave.*;

public class Main {

	public static void main(String[] args) {

		ConvertitoreGrafica grafica = new ConvertitoreGrafica();
		ServizioAssemblaggio servizio = new ServizioAssemblaggio();
		GestoreOutput gestoreOutput = new GestoreOutput();

		List<Componente> lista = new ArrayList<>();
		
		Nave n = new Nave(TipoNave.NAVE_1);
		gestoreOutput.stampa(grafica.rappresentazioneNave(n));
		gestoreOutput.aCapo();
		// Estrarre e rappresentare tutti i componenti
		
		for(int i = 0; i < 35; i++) {
			lista.add( servizio.estraiComponente());

		}
		gestoreOutput.stampa(grafica.rappresentaComponenti(lista));

		
		
		
	}
}