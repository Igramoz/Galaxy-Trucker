package main;

import partita.Partita;

public class MainFromArgs {

	public static void main(String[] args) {
		Partita p = new Partita(args);
		p.gioca();
	}
}