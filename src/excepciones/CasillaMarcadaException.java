package excepciones;

public class CasillaMarcadaException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = -773071160811092889L;

	public CasillaMarcadaException() {
    	
        super("No puedes descubrir una casilla marcada con bandera.");
    }
}