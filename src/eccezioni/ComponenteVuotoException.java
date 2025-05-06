package eccezioni;

// Eccezione lanciata quando si svuota un componente vuoto
public class ComponenteVuotoException extends Exception{

	private static final long serialVersionUID = 1L;

	public ComponenteVuotoException() {
	     super("Il componente è vuoto, non può essere svuotato ulteriormente.");
	 }

	 public ComponenteVuotoException(String message) {
	     super(message);
	 }
}
