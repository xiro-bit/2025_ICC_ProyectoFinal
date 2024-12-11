import java.util.Scanner;

/**
 * Clase Salvado.
 */
public class Salvado extends Juego {
    private final int TOTAL_PERSONAS = 100; 
    private int[] personas; 
    private Scanner scanner; 

    public Salvado() {
        super("Salvado", 15); 
        personas = new int[TOTAL_PERSONAS]; 
        for (int i = 0; i < TOTAL_PERSONAS; i++) {
            personas[i] = i + 1; // Asignar números a las sillas (1 a 100)
        }
        scanner = new Scanner(System.in); 
    }

    @Override
    public int jugar(Jugador jugador) {
        if (jugador.getCreditos() < getCosto()) {
            System.out.println("No tienes suficientes créditos para jugar.");
            return 0; 
        }

        jugador.restarCreditos(getCosto());

        System.out.print("Ingrese un número entre 1 y 100 para adivinar la última silla ocupada: ");
        int adivinanza = scanner.nextInt();

        if (adivinanza < 1 || adivinanza > TOTAL_PERSONAS) {
            System.out.println("Número inválido. Debe estar entre 1 y 100.");
            return 0; // Sin puntos si la adivinanza es inválida
        }

        int posicionSalida = eliminarPersonas(); // Determinar la posición de la última persona

        if (posicionSalida == adivinanza) {
            System.out.println("¡Correcto! Has adivinado la silla salvada.");
            jugador.sumarPuntos(12); // Sumar puntos por ganar
            return 12; // Puntos por ganar
        } else {
            System.out.println("Incorrecto. La silla salvada era: " + posicionSalida);
            jugador.sumarPuntos(2); // Sumar puntos por respuesta incorrecta
            return 2; // Puntos por respuesta incorrecta
        }
    }

    private int eliminarPersonas() {
        int index = 0; // Índice actual en el arreglo
        int numEliminados = 0; // Contador de personas eliminadas
        int i = (int) (Math.random() * TOTAL_PERSONAS); // Elegir un número aleatorio entre 0 y 99

        while (numEliminados < TOTAL_PERSONAS - 1) {
            index = (index + i) % (TOTAL_PERSONAS - numEliminados); // Calcular la siguiente posición a eliminar
            for (int j = index; j < TOTAL_PERSONAS - numEliminados - 1; j++) {
                personas[j] = personas[j + 1]; // Mover las personas hacia adelante en el arreglo
            }
            numEliminados++; // Incrementar el contador de eliminados
        }

        return personas[0]; // Retornar la última persona que queda
    }
}

