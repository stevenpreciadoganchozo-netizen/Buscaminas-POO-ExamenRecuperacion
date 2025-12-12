package modelo;

import java.io.*;

public class PartidaGuardada implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Juego juego;

    public PartidaGuardada(Juego juego) {
        this.juego = juego;
    }

    public Juego getJuego() { return juego; }

    public static void guardar(Juego juego, String archivo) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(new PartidaGuardada(juego));
        }
    }

    public static Juego cargar(String archivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            PartidaGuardada pg = (PartidaGuardada) ois.readObject();
            return pg.getJuego();
        }
    }
}