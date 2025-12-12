package excepciones;

public class CasillaYaDescubiertaException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8141410183511373661L;

	public CasillaYaDescubiertaException() {
        super("Esta casilla ya fue descubierta.");
    }
}