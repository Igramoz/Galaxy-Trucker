package eccezioni;
/**
 * Eccezione lanciata quando non è possibile istanziare un componente
 */
public class ComponenteNonIstanziabileException extends RuntimeException  {
 
	private static final long serialVersionUID = 1L;

	/**
     * Costruisce una nuova eccezione senza messaggio.
     */
    public ComponenteNonIstanziabileException() {
        super("Impossibile istanziare il componente");
    }

    public ComponenteNonIstanziabileException(String message) {
        super(message);
    }
	
}
