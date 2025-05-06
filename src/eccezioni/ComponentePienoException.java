package eccezioni;

public class ComponentePienoException extends Exception {

	private static final long serialVersionUID = 1L;

	public ComponentePienoException() {
        super("Il componente è pieno, non può essere riempito ulteriormente.");
    }

    public ComponentePienoException(String message) {
        super(message);
    }
}
