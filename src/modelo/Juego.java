package modelo;

import excepciones.NivelInvalidoException;
import excepciones.CoordenadaInvalidaException;
import excepciones.CasillaYaDescubiertaException;
import excepciones.CasillaMarcadaException;

public class Juego implements java.io.Serializable {
 /**
	 * 
	 */
	private static final long serialVersionUID = 9197890209969002728L;
 private Tablero nivel1;
 private Tablero nivel2;
 private int minasExplotadasNivel2;

 public Juego() {
     this.nivel1 = new Tablero();
     this.nivel2 = new Tablero();
     this.minasExplotadasNivel2 = 0;
 }

 // Getters
 public Tablero getNivel1() { return nivel1; }
 public Tablero getNivel2() { return nivel2; }
 public int getMinasExplotadasNivel2() { return minasExplotadasNivel2; }

 public boolean descubrir(int nivel, int fila, int col)
	        throws NivelInvalidoException, CoordenadaInvalidaException,
	               CasillaYaDescubiertaException, CasillaMarcadaException {

	    if (nivel != 1 && nivel != 2) {
	        throw new NivelInvalidoException();
	    }
	    if (fila < 0 || fila > 9 || col < 0 || col > 9) {
	        throw new CoordenadaInvalidaException("Coordenada fuera del tablero");
	    }

	    boolean tocoMina = false;

	    if (nivel == 1) {
	        tocoMina = nivel1.descubrir(fila, col);
	        if (tocoMina) {
	            return true; // pierde inmediatamente
	        }
	    } else { // nivel 2
	        tocoMina = nivel2.descubrir(fila, col);
	        if (tocoMina) {
	            minasExplotadasNivel2++;
	            if (minasExplotadasNivel2 >= 2) {
	                return true; // pierde al tocar la segunda
	            }
	        }
	    }
	    return false; // no perdió todavía
	}

 public void marcar(int nivel, int fila, int col) {
     if (nivel == 1) {
         nivel1.toggleMarca(fila, col);
     } else if (nivel == 2) {
         nivel2.toggleMarca(fila, col);
     }
 }

 public boolean haGanado() {
     return nivel1.todasSegurasDescubiertas() && nivel2.todasSegurasDescubiertas();
 }
}
