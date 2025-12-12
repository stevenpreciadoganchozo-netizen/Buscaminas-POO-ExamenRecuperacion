package vista;

import modelo.Juego;
import modelo.Tablero;

public class VistaConsola {

 public void mostrarTableros(Juego juego) {
     System.out.println("\n=== NIVEL 1 ===");
     juego.getNivel1().imprimirConsola();
     System.out.println("\n=== NIVEL 2 ===");
     juego.getNivel2().imprimirConsola();
     System.out.println("\nMinas explotadas en Nivel 2: " + juego.getMinasExplotadasNivel2());
 }

 public void mostrarVictoria() {
     System.out.println("¡FELICIDADES! ¡HAS GANADO EL JUEGO COMPLETANDO AMBOS NIVELES!");
 }

 public void mostrarDerrota() {
     System.out.println("¡GAME OVER! Has perdido.");
 }

 public void mostrarInstrucciones() {
     System.out.print("Acción > Descubrir: nivel coordenada (ej: 1 A5) | Marcar: M nivel coordenada (ej: M 2 B3) | S para salir: ");
 }

 public void mostrarError(String mensaje) {
     System.out.println("Error: " + mensaje);
 }

 public void revelarMinas(Tablero tablero) {
     System.out.println("Minas reveladas:");
     // Podrías mejorar esto mostrando X en todas las minas
     tablero.imprimirConsola();
 }
 public void mostrarAdvertencia(String msg) {
	    System.out.println("¡ATENCIÓN! " + msg);
	}

	public void mostrarMensaje(String msg) {
	    System.out.println("→ " + msg);
	}
}
