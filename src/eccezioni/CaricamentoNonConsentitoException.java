package eccezioni;

public class CaricamentoNonConsentitoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CaricamentoNonConsentitoException() {
        super("Il componente non può ospitare l'elemento specificato.");
    }

    public CaricamentoNonConsentitoException(String message) {
        super(message);
    }
}
