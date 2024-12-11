import java.util.Scanner;

/**
 * Clase Torres de Hanoi.
 *
 */
public class TorresDeHanoi extends Juego {
    private int totalDiscos; 
    private int movimientos;
    private int[][] torres; 
    private int[] tope; 
    private Scanner scanner; 

    /**
     * Constructor de la clase TorresDeHanoi.
     */
    public TorresDeHanoi() {
        super("Torres de Hanoi", 15); 
        this.totalDiscos = 6; 
        this.movimientos = 0; 
        this.torres = new int[3][totalDiscos]; 
        this.tope = new int[3]; 

        // Inicializar las torres
        for (int i = 0; i < 3; i++) {
            tope[i] = 0; // Establecer el tope inicial en cero
        }
        for (int i = 0; i < totalDiscos; i++) {
            torres[0][i] = totalDiscos - i; // Llenar la torre 1 con discos (de mayor a menor)
            tope[0]++; // Incrementar el tope de la torre 1
        }
        this.scanner = new Scanner(System.in); // Inicializar el scanner para entradas del usuario
    }

    /**
     *
     * @param jugador El jugador que está jugando.
     * @return Puntos ganados por el jugador al finalizar el juego.
     */
    @Override
    public int jugar(Jugador jugador) {
        if (jugador.getCreditos() < getCosto()) {
            System.out.println("No tienes suficientes créditos para jugar.");
            return 0; // Sin puntos si no hay suficientes créditos
        }

        jugador.restarCreditos(getCosto()); // Restar el costo del juego

        System.out.println("Resuelve el juego de Torres de Hanoi con " + totalDiscos + " discos.");
        
        while (!isJuegoTerminado()) { // Mientras no se haya completado el juego
            mostrarEstado(); // Mostrar el estado actual de las torres
            System.out.print("Selecciona el poste de origen (1-3): ");
            int origen = scanner.nextInt() - 1; // Leer poste de origen
            System.out.print("Selecciona el poste de destino (1-3): ");
            int destino = scanner.nextInt() - 1; // Leer poste de destino

            if (moverDisco(origen, destino)) { // Intentar mover el disco
                System.out.println("Movimiento realizado.");
            } else {
                System.out.println("Movimiento inválido. Intenta nuevamente.");
            }
        }

        return mostrarResultado(jugador); // Mostrar resultado al finalizar el juego
    }

    /**
     * Mueve un disco desde una torre a otra.
     *
     * @param origen El índice del poste de origen.
     * @param destino El índice del poste de destino.
     * @return true si el movimiento fue exitoso, false en caso contrario.
     */
    private boolean moverDisco(int origen, int destino) {
        if (origen < 0 || origen >= 3 || destino < 0 || destino >= 3) {
            return false; // Poste inválido
        }

        if (tope[origen] > 0) { // Si hay discos en la torre de origen
            int discoMovido = torres[origen][tope[origen] - 1]; // Obtener el disco superior
            if (tope[destino] == 0 || torres[destino][tope[destino] - 1] > discoMovido) {
                torres[destino][tope[destino]] = discoMovido; // Mover el disco al destino
                tope[destino]++; // Incrementar el tope de la torre destino
                tope[origen]--; // Decrementar el tope de la torre origen
                movimientos++; // Incrementar contador de movimientos
                return true;
            }
        }
        return false; // Movimiento no permitido
    }

    /**
     * Verifica si se ha completado el juego.
     *
     * @return true si se han movido todos los discos a la torre destino, false en caso contrario.
     */
    private boolean isJuegoTerminado() {
        return tope[2] == totalDiscos; // El juego termina cuando todos los discos están en la torre 3
    }

    private void mostrarEstado() {
        System.out.println("Estado actual:");
        for (int i = 0; i < 3; i++) {
            System.out.print("Torre " + (i + 1) + ": ");
            for (int j = 0; j < tope[i]; j++) {
                System.out.print(torres[i][j] + " "); // Mostrar los discos en cada torre
            }
            System.out.println(); 
        }
    }

    /**
     * Muestra un mensaje del resultado
     */
    private int mostrarResultado(Jugador jugador) {
        System.out.println("¡Has completado el juego!");
        return calcularPuntos(jugador); // Calcular y retornar puntos obtenidos por el jugador
    }

    /**
     * Calcula los puntos obtenidos por el jugador 
     *
     * @param jugador 
     */
    private int calcularPuntos(Jugador jugador) {
        int movimientosMinimos = (int) (Math.pow(2, totalDiscos) - 1);
        int puntos = 0;

        if (movimientos == movimientosMinimos) {
            System.out.println("¡Has resuelto el juego en el número mínimo de movimientos! Puntos: +10");
            puntos = 10;
        } else if (movimientos <= movimientosMinimos + 10) {
            System.out.println("¡Has resuelto el juego con algunos movimientos adicionales! Puntos: +5");
            puntos = 5;
        } else {
            System.out.println("Has completado el juego, pero con muchos movimientos. Puntos: +2");
            puntos = 2;
        }

        jugador.sumarPuntos(puntos); 

        return puntos;
    }
}
