import java.util.Scanner;

/**
 * Clase Cuadrado Mágico.
 * 
 */
public class CuadradoMagico extends Juego {
    private int[][] tablero; // Tablero del juego donde se colocan los números
    private final int tamanio = 4; // Tamaño del tablero (4x4)
    private final int constanteMagica = 34; // Suma mágica para un cuadrado 4x4

    /**
     * Constructor de la clase CuadradoMagico.
     */
    public CuadradoMagico() {
        super("Cuadrado Mágico", 15);
        tablero = new int[tamanio][tamanio]; 
        inicializarTablero(); 
    }

    /**
     * Inicializa el tablero poniendo las celdas en cero.
     */
    private void inicializarTablero() {
        for (int i = 0; i < tamanio; i++) {
            for (int j = 0; j < tamanio; j++) {
                tablero[i][j] = 0; 
            }
        }
    }

    /**
     * Método que inicia el juego.
     * Permite al jugador colocar números en el tablero y verifica si ha ganado o perdido.
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

        Scanner scanner = new Scanner(System.in);
        boolean juegoActivo = true;

        while (juegoActivo) {
            mostrarTablero(); // Mostrar el estado actual del tablero
            System.out.print("Ingrese fila (0-3) y columna (0-3) para colocar un número (ejemplo: 1 2): ");
            int fila = scanner.nextInt();
            int columna = scanner.nextInt();

            // Validar posición ingresada por el usuario
            if (fila < 0 || fila >= tamanio || columna < 0 || columna >= tamanio) {
                System.out.println("Posición inválida. Intente nuevamente.");
                continue;
            }

            if (tablero[fila][columna] != 0) {
                System.out.println("La celda ya está ocupada. Intente nuevamente.");
                continue;
            }

            System.out.print("Ingrese el número a colocar (1-16): ");
            int numero = scanner.nextInt();

            // Validar número ingresado por el usuario
            if (numero < 1 || numero > 16 || !esNumeroValido(numero)) {
                System.out.println("Número inválido o ya utilizado. Intente nuevamente.");
                continue;
            }

            tablero[fila][columna] = numero; // Colocar el número en la posición elegida

            // Verificar si se puede completar el cuadrado mágico o si se ha ganado
            if (!puedeCompletarCuadradoMagico()) {
                System.out.println("Has perdido. La disposición actual no permite completar el cuadrado mágico.");
                juegoActivo = false;
            } else if (esVictoria()) {
                System.out.println("¡Felicidades! Has completado el cuadrado mágico.");
                jugador.sumarPuntos(10); 
                return 10; 
            }
        }

        return 0; 
    }

    /**
     * Muestra el estado actual del tablero 
     */
    private void mostrarTablero() {
        System.out.println("\nEstado actual del tablero:");
        for (int i = 0; i < tamanio; i++) {
            for (int j = 0; j < tamanio; j++) {
                if (tablero[i][j] == 0) {
                    System.out.print(". "); 
                } else {
                    System.out.print(tablero[i][j] + " ");
                }
            }
            System.out.println(); 
        }
    }

    /**
     * Verifica si un número es válido 
     *
     * @param numero El número a verificar.
     * @return true si el número es válido.
     */
    private boolean esNumeroValido(int numero) {
        for (int i = 0; i < tamanio; i++) {
            for (int j = 0; j < tamanio; j++) {
                if (tablero[i][j] == numero) {
                    return false; 
                }
            }
        }
        return true;
    }

    /**
     * Verifica si se puede completar el cuadrado mágico.
     *
     * @return true si se puede completar, false en caso contrario.
     */
    private boolean puedeCompletarCuadradoMagico() {
        return true; 
    }

    /**
     * Verifica si el jugador ha ganado.
     *
     * @return true
     */
    private boolean esVictoria() {
        for (int i = 0; i < tamanio; i++) {
            if (sumaFila(i) != constanteMagica || sumaColumna(i) != constanteMagica) {
                return false;
            }
        }
        
        if (sumaDiagonalPrincipal() != constanteMagica || sumaDiagonalSecundaria() != constanteMagica) {
            return false;
        }

        return true;
    }

    /**
     * Calcula la suma de los elementos en una fila específica del tablero.
     *
     * @param fila La fila a sumar.
     * @return La suma de los elementos 
     */
    private int sumaFila(int fila) {
        int suma = 0;
        for (int j = 0; j < tamanio; j++) {
            suma += tablero[fila][j];
        }
        return suma;
    }

    /**
     * Calcula la suma de los elementos en una columna específica del tablero.
     *
     * @param columna La columna a sumar.
     * @return La suma 
     */
    private int sumaColumna(int columna) {
        int suma = 0;
        for (int i = 0; i < tamanio; i++) {
            suma += tablero[i][columna];
        }
        return suma;
    }

    /**
     * Calcula la suma de la diagonal principal d
     *
     * @return 
     */
    private int sumaDiagonalPrincipal() {
        int suma = 0;
        for (int i = 0; i < tamanio; i++) {
            suma += tablero[i][i];
        }
        return suma;
    }

    /**
     * Calcula la suma de la diagonal secundarua
     *
     * @return 
     */
    private int sumaDiagonalSecundaria() {
        int suma = 0;
        for (int i = 0; i < tamanio; i++) {
            suma += tablero[i][tamanio - 1 - i];
        }
        return suma;
    }
}
