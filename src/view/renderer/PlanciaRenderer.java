package view.renderer;

import model.Giocatore;
import model.planciaDiVolo.Plancia;
import view.Colore;
import view.TextAligner;
import view.formattatori.FormattatoreGrafico;


public class PlanciaRenderer {
	
		private final TextAligner textAligner = new TextAligner();
		

    public String[] rappresentaPlancia(Plancia plancia) {
        Giocatore[] arrayPlancia = plancia.getPlancia();
        int lunghezza = arrayPlancia.length; //lunghezza della plancia 18 24 34
        int righe =  plancia.getGiocatori().length + 4; //numero di giocatori aggiunti a riga per creare la leggenda  1 riga per spazio e 3 righe per la plancia
        //ogni giorno di volo della plancia occupa 3 caratteri quindi lunghezza *
        
        
        String[] righePlancia = new String[righe+1];

        // Inizializzo le righe per i giocatori
        FormattatoreGrafico formattatore = new FormattatoreGrafico();
        for (int i = 0; i < plancia.getGiocatori().length; i++) {
            righePlancia[i] = formattatore.formatta(plancia.getGiocatori()[i]) + " " + SimboloGiornoDiVolo(plancia.getGiocatori()[i]);
        }
        // righa vuota
        righePlancia[plancia.getGiocatori().length] = ""; //riga vuota
        //prima riga
        
        righePlancia[plancia.getGiocatori().length+1] = "";
        
        for(int i = 0; i < (lunghezza - 2)/2; i++) {
        	
        	righePlancia[plancia.getGiocatori().length+1] += SimboloGiornoDiVolo(arrayPlancia[i]);
		}

        
        //riga in mezzo	
        righePlancia[plancia.getGiocatori().length+2] = "";
       
        
        //prendo la lunghezza e gli tolgo 6 spazi (che sono per per i due giorni di volo dei due lati)
        
			righePlancia[plancia.getGiocatori().length+2] += SimboloGiornoDiVolo(arrayPlancia[arrayPlancia.length -1]);
			
			for(int i = 0; i < ((lunghezza - 2)/2)-2; i++) {
	        	
	        	righePlancia[plancia.getGiocatori().length+2] += "   ";	
			}
			
			righePlancia[plancia.getGiocatori().length+2] += SimboloGiornoDiVolo(arrayPlancia[(lunghezza)/2 - 1]);
			
		//riga finale
			
			righePlancia[plancia.getGiocatori().length+3] = "";
			
			for(int i = lunghezza - 2; i > (lunghezza - 2)/2; i--) {
	        	
	        	righePlancia[plancia.getGiocatori().length+3] += SimboloGiornoDiVolo(arrayPlancia[i]);
	        }
			
			//a capo
			
			righePlancia[righe] = "";
			
			
        


        return textAligner.alignCenter(righePlancia);
        
        
        
        
  
    }
    
    
    //metodo per formattare il simbolo del giocatore
    private String SimboloGiornoDiVolo(Giocatore giocatore) {
		//return "[" + giocatore.getColore().getCodice() + giocatore.getNome().charAt(0) + Colore.DEFAULT.getCodice() + "]";
	
    	if(giocatore == null) {
			return "[_]";
		}
    	return "[" + giocatore.getColore().getCodice() + giocatore.getNome().charAt(0) + Colore.DEFAULT.getCodice() + "]";
    	
    }
    
    
     

}
