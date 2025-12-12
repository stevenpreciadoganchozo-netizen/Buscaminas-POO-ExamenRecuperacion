package modelo;

import java.util.Random;
import excepciones.CasillaYaDescubiertaException;
import excepciones.CasillaMarcadaException;

public class Tablero implements java.io.Serializable {
 /**
	 * 
	 */
	private static final long serialVersionUID = -3565609144080766735L;
 private static final int TAMANO = 10;
 private static final int MINAS = 10;
 private Casilla[][] casillas;

 public Tablero() {
     casillas = new Casilla[TAMANO][TAMANO];
     for (int i = 0; i < TAMANO; i++) {
         for (int j = 0; j < TAMANO; j++) {
             casillas[i][j] = new Casilla();
         }
     }
     colocarMinasAleatorias();
     calcularNumerosAdyacentes();
 }

 private void colocarMinasAleatorias() {
     Random rand = new Random();
     int colocadas = 0;
     while (colocadas < MINAS) {
         int f = rand.nextInt(TAMANO);
         int c = rand.nextInt(TAMANO);
         if (!casillas[f][c].tieneMina()) {
             casillas[f][c].colocarMina();
             colocadas++;
         }
     }
 }

 private void calcularNumerosAdyacentes() {
     for (int i = 0; i < TAMANO; i++) {
         for (int j = 0; j < TAMANO; j++) {
             if (!casillas[i][j].tieneMina()) {
                 casillas[i][j].setNumMinasAdyacentes(contarMinasAdyacentes(i, j));
             }
         }
     }
 }

 private int contarMinasAdyacentes(int fila, int col) {
     int count = 0;
     for (int df = -1; df <= 1; df++) {
         for (int dc = -1; dc <= 1; dc++) {
             if (df == 0 && dc == 0) continue;
             int nf = fila + df;
             int nc = col + dc;
             if (nf >= 0 && nf < TAMANO && nc >= 0 && nc < TAMANO && casillas[nf][nc].tieneMina()) {
                 count++;
             }
         }
     }
     return count;
 }

 public boolean descubrir(int fila, int col) throws CasillaYaDescubiertaException, CasillaMarcadaException {
	    if (casillas[fila][col].estaDescubierta()) {
	        throw new CasillaYaDescubiertaException();
	    }
	    if (casillas[fila][col].estaMarcada()) {
	        throw new CasillaMarcadaException();
	    }

	    casillas[fila][col].descubrir();

	    if (casillas[fila][col].tieneMina()) {
	        return true;  // tocó mina
	    }

	    // Revelación automática si es vacío
	    if (casillas[fila][col].getNumMinasAdyacentes() == 0) {
	        for (int df = -1; df <= 1; df++) {
	            for (int dc = -1; dc <= 1; dc++) {
	                int nf = fila + df;
	                int nc = col + dc;
	                if (nf >= 0 && nf < TAMANO && nc >= 0 && nc < TAMANO && !casillas[nf][nc].estaDescubierta()) {
	                    descubrir(nf, nc);  // aquí vuelve a llamar al mismo método → no hay problema
	                }
	            }
	        }
	    }
	    return false;
	}

 public void toggleMarca(int fila, int col) {
     if (!casillas[fila][col].estaDescubierta()) {
         casillas[fila][col].toggleMarca();
     }
 }

 public boolean todasSegurasDescubiertas() {
     for (int i = 0; i < TAMANO; i++) {
         for (int j = 0; j < TAMANO; j++) {
             if (!casillas[i][j].tieneMina() && !casillas[i][j].estaDescubierta()) {
                 return false;
             }
         }
     }
     return true;
 }

 public Casilla getCasilla(int fila, int col) {
     return casillas[fila][col];
 }

 public void imprimirConsola() {
     System.out.println("   1 2 3 4 5 6 7 8 9 10");
     for (int i = 0; i < TAMANO; i++) {
         System.out.printf("%c ", 'A' + i);
         for (int j = 0; j < TAMANO; j++) {
             System.out.print(casillas[i][j] + " ");
         }
         System.out.println();
     }
 }
}