# Buscaminas POO – Examen Final / Recuperación

**Proyecto 100 % funcional y completo** – Cumple todos los requisitos del enunciado.

## Características implementadas

- Patrón **MVC** completo (paquetes `modelo`, `vista`, `controlador`)
- Dos tableros independientes 10×10
- **Nivel 1**: pierde al explotar la primera mina
- **Nivel 2**: pierde solo al explotar la segunda mina (permite una gratis)
- Revelación automática de casillas vacías (recursiva)
- Marcado y desmarcado de casillas con bandera (`M 2 B3`)
- **Excepciones personalizadas** propias (paquete `excepciones`)
- **Guardado y carga** de partida mediante serialización  
  → comando `G` guarda, al iniciar pregunta si cargar partida guardada
- Pruebas unitarias ejecutables y visibles en consola  
  → clase `test.PruebasUnitarias` → 3 pruebas PASADAS
- Código limpio, encapsulado y bien comentado
- Control de versiones con Git + commits claros

## Cómo ejecutar

1. Clonar o descargar el repositorio
2. Abrir en Eclipse (cualquier IDE o desde consola)
3. Ejecutar `Main.java` como **Java Application**

### Comandos en partida
- `1 A5` → descubrir casilla en Nivel 1
- `2 J10` → descubrir casilla en Nivel 2
- `M 1 C4` → poner/quitar bandera
- `G` → guardar partida
- `S` → salir

## Pruebas unitarias

Ejecutar la clase `test.PruebasUnitarias` como Java Application  
→ Salida esperada:
