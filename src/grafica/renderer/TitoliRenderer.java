package grafica.renderer;


import grafica.formattatori.FormattatoreGrafico;
import io.GestoreIO;
import model.Giocatore;
import model.titoli.TipoTitolo;

public class TitoliRenderer {
	GestoreIO io = new GestoreIO();
	FormattatoreGrafico formattatore = new FormattatoreGrafico();

	public void stampaAssegnazioneTitolo(Giocatore giocatore, TipoTitolo titolo) {
		io.aCapo();
		io.stampa("A " + formattatore.formatta(giocatore) + " ha guadagnato il titolo: " + formattatore.formatta(titolo));
	}
	
	public void stampaDifesaTitolo(Giocatore giocatore) {
		io.aCapo();
		TipoTitolo titolo = giocatore.getTipoTitolo();
		io.stampa(formattatore.formatta(giocatore) + " ha difeso il titolo: " + formattatore.formatta(titolo) + " che è diventato oro");
	}
	
}
