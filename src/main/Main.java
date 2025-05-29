package main;

import controller.partita.Partita;

public class Main {

	public static void main(String[] args) {
		Partita p = new Partita(args);
		p.gioca();
	}
}
