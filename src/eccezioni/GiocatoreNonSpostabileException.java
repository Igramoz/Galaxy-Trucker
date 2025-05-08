package eccezioni;

public class GiocatoreNonSpostabileException extends  RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	
	public GiocatoreNonSpostabileException() {
		super("Il giocatore non può essere spostato");
	}
	
	public GiocatoreNonSpostabileException(String message) {
		super(message);
	}
	
}
