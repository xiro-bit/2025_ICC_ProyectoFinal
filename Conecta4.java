import java.util.Scanner;

/**
 * Clase Conecta 4.
 */
public class Conecta4 extends Juego {
    private char[][] tablero; 
    private final int filas = 6; 
    private final int columnas = 7; 

    public Conecta4() {
        super("Conecta 4", 15);
        tablero = new char[filas][columnas];
        inicializarTablero();
    }

    private void inicializarTablero() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                tablero[i][j] = ' '; 
            }
        }
    }

    @Override
    public int jugar(Jugador jugador) {
        if (jugador.getCreditos() < getCosto()) {
            System.out.println("No tienes suficientes créditos para jugar.");
            return 0; 
        }

        jugador.restarCreditos(getCosto()); 

        Scanner scanner = new Scanner(System.in);
        char simboloJugador1 = 'X'; // jugador 1
        char simboloJugador2 = 'O'; // jugador 2
        boolean juegoActivo = true;
        int turno = 0;

        while (juegoActivo) {
            mostrarTablero();
            char simboloActual = (turno % 2 == 0) ? simboloJugador1 : simboloJugador2; 
            System.out.print("Turno del jugador " + simboloActual + ". Elija una columna (0-6): ");
            int columna = scanner.nextInt();

            if (columna < 0 || columna >= columnas) {
                System.out.println("Columna inválida. Intente nuevamente.");
                continue;
            }

            if (!colocarFicha(columna, simboloActual)) {
                System.out.println("Columna llena. Intente con otra columna.");
                continue;
            }

            if (verificarGanador(simboloActual)) {
                mostrarTablero();
                System.out.println("¡El jugador " + simboloActual + " ha ganado!");
                jugador.sumarPuntos(10); // Sumar puntos por ganar
                return 10; // Puntos por ganar
            }

            if (tableroLleno()) {
                mostrarTablero();
                System.out.println("¡Es un empate!");
                jugador.sumarPuntos(5); // Sumar puntos por empate
                return 5; // Puntos por empate
            }

            turno++;
        }

        return 0; // Sin puntos si se pierde
    }

    private void mostrarTablero() {
        System.out.println("\nEstado actual del tablero:");
        for (int i = filas - 1; i >= 0; i--) { // Mostrar desde la fila más baja a la más alta
            for (int j = 0; j < columnas; j++) {
                System.out.print("|" + tablero[i][j]); // Mostrar cada celda del tablero
            }
            System.out.println("|");
        }
        System.out.println("-----------------------------");
    }

    private boolean colocarFicha(int columna, char simbolo) {
        for (int i = filas - 1; i >= 0; i--) { // Colocar la ficha en la posición más baja disponible
            if (tablero[i][columna] == ' ') { // Si la celda está vacía
                tablero[i][columna] = simbolo;
                return true;
            }
        }
        return false; // Columna llena
    }

    private boolean verificarGanador(char simbolo) {
        return verificarHorizontal(simbolo) || verificarVertical(simbolo) || verificarDiagonal(simbolo);
    }

    private boolean verificarHorizontal(char simbolo) {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas - 3; j++) { // Solo hasta la cuarta columna
                if (tablero[i][j] == simbolo && tablero[i][j + 1] == simbolo &&
                    tablero[i][j + 2] == simbolo && tablero[i][j + 3] == simbolo) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean verificarVertical(char simbolo) {
        for (int j = 0; j < columnas; j++) {
            for (int i = 0; i < filas - 3; i++) { // Solo hasta la cuarta fila
                if (tablero[i][j] == simbolo && tablero[i + 1][j] == simbolo &&
                    tablero[i + 2][j] == simbolo && tablero[i + 3][j] == simbolo) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean verificarDiagonal(char simbolo) {
        // Verificar diagonales de izquierda a derecha
        for (int i = 0; i < filas - 3; i++) {
            for (int j = 0; j < columnas - 3; j++) { 
                if (tablero[i][j] == simbolo && tablero[i + 1][j + 1] == simbolo &&
                    tablero[i + 2][j + 2] == simbolo && tablero[i + 3][j + 3] == simbolo) {
                    return true;
                }
            }
        }

        // Verificar diagonales de derecha a izquierda
        for (int i = filas -1 ;i >2 ;i--) {  
           for(int j=3;j<columnas;j++){  
               if(tablero[i][j]==simbolo && tablero[i-1][j-1]==simbolo &&  
                  tablero[i-2][j-2]==simbolo && tablero[i-3][j-3]==simbolo){  
                   return true ;  
               }  
           }  
       }  

      return false ;  
   }  

   private boolean tableroLleno() {  
      for(int j=0;j<columnas;j++){  
          if(tablero[filas-1][j]==' '){ 
              return false ;  
          }  
      }  
      return true ; // Todas las celdas están llenas  
   }  

}

