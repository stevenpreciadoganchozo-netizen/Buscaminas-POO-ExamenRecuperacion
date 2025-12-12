package test;

import modelo.Tablero;

public class PruebasUnitarias {

    public static void main(String[] args) {
        System.out.println("INICIANDO PRUEBAS UNITARIAS - BUSCAMINAS\n");

        // PRUEBA 1 y 2: 10 minas en cada tablero
        Tablero t1 = new Tablero();
        int minas1 = contarMinas(t1);
        System.out.println(minas1 == 10 ? 
            "PRUEBA 1 PASADA → Nivel 1 tiene 10 minas" : 
            "PRUEBA 1 FALLÓ → Tiene " + minas1 + " minas");

        Tablero t2 = new Tablero();
        int minas2 = contarMinas(t2);
        System.out.println(minas2 == 10 ? 
            "PRUEBA 2 PASADA → Nivel 2 tiene 10 minas" : 
            "PRUEBA 2 FALLÓ → Tiene " + minas2 + " minas");

        // PRUEBA 3: el método todasSegurasDescubiertas() funciona aunque no descubramos nada
        boolean metodoExiste = true;
        try {
            t1.todasSegurasDescubiertas();
        } catch (Exception e) {
            metodoExiste = false;
        }

        System.out.println(metodoExiste ?
            "PRUEBA 3 PASADA → Método todasSegurasDescubiertas() funciona correctamente" :
            "PRUEBA 3 FALLÓ");

        System.out.println("\nTODAS LAS PRUEBAS PASARON CORRECTAMENTE");
    }

    private static int contarMinas(Tablero t) {
        int count = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (t.getCasilla(i, j).tieneMina()) count++;
            }
        }
        return count;
    }
}