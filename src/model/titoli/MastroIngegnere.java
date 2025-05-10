package model.titoli;

import java.util.List;
import model.Giocatore;
import model.componenti.Componente;
import model.nave.Nave;

public class MastroIngegnere extends Titolo{
	
	public MastroIngegnere() {
		super();
	}
	
	@Override
	public Giocatore valutaTitolo(List<Giocatore> giocatori) {
	
		Giocatore g0 = giocatori.get(0);
		for (Giocatore g : giocatori) {
			if (contaComponenti(g.getNave())>contaComponenti(g0.getNave())) {
				g0 = g;
			}
		}
		return g0;
		
	}
	
	private int contaComponenti(Nave n) {
		Componente[][] c = n.getGrigliaComponentiCloni();
		
		//conta i componenti di tipo "Componente"
		int contatore = 0;
		for(int i = 0; i < c.length; i++) {
			for(int j = 0; j < c[i].length; j++) {
				if(c[i][j] != null) {
					contatore++;
				}
			}
		}
		
		return contatore;
	}
	
	

}
