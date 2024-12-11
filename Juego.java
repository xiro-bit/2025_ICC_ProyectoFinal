/**
 * Clase abstracta Juego
 * 
 */
public abstract class Juego {
    private String nombre; // Nombre del juego
    private int costo; // Costo en créditos para jugar

    /**
     * Constructor de la clase Juego.
     * @param nombre El nombre del juego.
     * @param costo El costo en créditos para jugar.
     */
    public Juego(String nombre, int costo) {
        this.nombre = nombre;
        this.costo = costo;
    }

    /**
     * Obtiene el nombre del juego.
     * @return El nombre del juego.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el costo del juego.
     * @return El costo en créditos para jugar.
     */
    public int getCosto() {
        return costo;
    }

    /**
     * Método abstracto que debe ser implementado por cada juego.
     * @param jugador El jugador que está jugando.
     * @return Puntos ganados por el jugador al finalizar el juego.
     */
    public abstract int jugar(Jugador jugador); // Método abstracto para jugar
}
