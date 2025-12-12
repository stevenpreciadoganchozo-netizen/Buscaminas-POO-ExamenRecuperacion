package controlador;

import modelo.*;
import vista.VistaConsola;
import excepciones.*;
import java.util.Scanner;

public class ControladorJuego {
    private Juego juego;
    private VistaConsola vista;
    private Scanner scanner;
    private static final String ARCHIVO_GUARDADO = "partida.dat";

    public ControladorJuego() {
        this.vista = new VistaConsola();
        this.scanner = new Scanner(System.in);
        cargarOEmpezarNueva();
    }

    private void cargarOEmpezarNueva() {
        System.out.print("¿Quieres cargar partida guardada? (s/N): ");
        String respuesta = scanner.nextLine().trim();
        if (respuesta.equalsIgnoreCase("s")) {
            try {
                juego = PartidaGuardada.cargar(ARCHIVO_GUARDADO);
                vista.mostrarMensaje("Partida cargada correctamente.");
            } catch (Exception e) {
                vista.mostrarError("No se pudo cargar. Empezando partida nueva.");
                juego = new Juego();
            }
        } else {
            juego = new Juego();
        }
    }

    public void iniciar() {
        while (true) {
            vista.mostrarTableros(juego);
            vista.mostrarInstrucciones();

            String input = scanner.nextLine().trim().toUpperCase();

            if (input.equals("S")) {
                guardarPartida();
                System.out.println("¡Hasta luego!");
                break;
            }

            if (input.equals("G")) {
                guardarPartida();
                continue;
            }

            try {
                if (input.startsWith("M ")) {
                    procesarMarca(input.substring(2));
                } else {
                	boolean perdio = procesarDescubrimiento(input);
                	if (perdio) {
                	    vista.mostrarTableros(juego);
                	    vista.mostrarDerrota();
                	    break;
                	}
                }

                if (juego.haGanado()) {
                    vista.mostrarTableros(juego);
                    vista.mostrarVictoria();
                    break;
                }

            } catch (NivelInvalidoException e) {
                vista.mostrarError(e.getMessage());
            } catch (CoordenadaInvalidaException | NumberFormatException e) {
                vista.mostrarError("Coordenada inválida. Ej: 1 A5 o 2 J10");
            } catch (CasillaYaDescubiertaException | CasillaMarcadaException e) {
                vista.mostrarError(e.getMessage());
            } catch (Exception e) {
                vista.mostrarError("Error inesperado.");
            }
        }
    }

    private boolean procesarDescubrimiento(String input) throws Exception {
        String[] partes = input.split(" ");
        int nivel = Integer.parseInt(partes[0]);
        String coord = partes[1];
        int[] pos = convertirCoordenada(coord);

        boolean perdio = juego.descubrir(nivel, pos[0], pos[1]);

        if (nivel == 2 && juego.getMinasExplotadasNivel2() == 1) {
            vista.mostrarAdvertencia("¡Primera mina en Nivel 2! Te queda solo una vida.");
        }

        return perdio;  // ahora sí devuelve boolean
    }

    private void procesarMarca(String input) throws Exception {
        String[] partes = input.split(" ");
        int nivel = Integer.parseInt(partes[0]);
        String coord = partes[1];
        int[] pos = convertirCoordenada(coord);
        juego.marcar(nivel, pos[0], pos[1]);
    }

    private int[] convertirCoordenada(String coord) throws CoordenadaInvalidaException {
        if (coord.length() < 2) throw new CoordenadaInvalidaException("Formato incorrecto");
        int fila = coord.charAt(0) - 'A';
        int col = Integer.parseInt(coord.substring(1)) - 1;
        if (fila < 0 || fila > 9 || col < 0 || col > 9) {
            throw new CoordenadaInvalidaException("Fuera del tablero");
        }
        return new int[]{fila, col};
    }

    private void guardarPartida() {
        try {
            PartidaGuardada.guardar(juego, ARCHIVO_GUARDADO);
            vista.mostrarMensaje("Partida guardada correctamente.");
        } catch (Exception e) {
            vista.mostrarError("No se pudo guardar la partida.");
        }
    }
}