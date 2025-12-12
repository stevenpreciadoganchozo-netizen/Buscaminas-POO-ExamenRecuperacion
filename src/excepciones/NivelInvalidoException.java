package excepciones;

public class NivelInvalidoException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = -1559249147777959157L;

	public NivelInvalidoException() {
        super("Nivel debe ser 1 o 2.");
    }
}