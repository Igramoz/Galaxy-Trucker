package eccezioni;

public class GiocatoreNonSpostabile extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	
	public GiocatoreNonSpostabile() {
		super("Il giocatore non può essere spostato");
	}
	
	public GiocatoreNonSpostabile(String message) {
		super(message);
	}
	
}
