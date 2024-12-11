import java.io.Serializable;

/**
 * Clase que representa a un jugador en el sistema de feria de juegos.
 */
public class Jugador implements Serializable {
    private String nombre; // Nombre del jugador
    private int creditos; // Créditos disponibles para jugar
    private int puntos; // Puntos acumulados por el jugador

    /**
     * Constructor de la clase Jugador.
     * @param nombre El nombre del jugador.
     */
    public Jugador(String nombre) {
        this.nombre = nombre;
        this.creditos = 100; // Créditos iniciales
        this.puntos = 0; // Puntos iniciales
    }

    /**
     * Obtiene el nombre del jugador.
     * @return El nombre del jugador.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene los créditos disponibles del jugador.
     * @return Los créditos disponibles.
     */
    public int getCreditos() {
        return creditos;
    }

    /**
     * Obtiene los puntos acumulados por el jugador.
     * @return Los puntos acumulados.
     */
    public int getPuntos() {
        return puntos;
    }

    /**
     * Resta una cantidad de créditos al jugador.
     * @param cantidad La cantidad de créditos a restar.
     */
    public void restarCreditos(int cantidad) {
        this.creditos -= cantidad;
    }

    /**
     * Suma una cantidad de puntos al jugador.
     * @param cantidad La cantidad de puntos a sumar.
     */
    public void sumarPuntos(int cantidad) {
        this.puntos += cantidad;
    }
}
