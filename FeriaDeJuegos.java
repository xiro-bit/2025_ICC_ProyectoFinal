import java.io.*;
import java.util.Scanner;

/**
 * Clase feria de juegos.
 * 
 */
public class FeriaDeJuegos {
    private static Jugador[] jugadores = new Jugador[10]; 
    private static int contadorJugadores = 0; 
    private static final String NOMBRE_ARCHIVO_JUGADORES = "jugadores.dat";
    private static int diaActual; 

    public static void main(String[] args) {
        cargarJugadores(); // Cargar jugadores desde archivo al inicio
        Scanner scanner = new Scanner(System.in); 
        
        // Seleccionar el día de la feria
        System.out.print("Seleccione el día de la feria (1 o 2): ");
        diaActual = scanner.nextInt();
        scanner.nextLine(); 

        String opcion;
        do {
            System.out.println("\n--- Menú de Feria de Juegos ---");
            System.out.println("1. Registrar Jugador");
            if (diaActual == 1) {
                System.out.println("2. Jugar Cuadrado Mágico");
                System.out.println("3. Jugar Conecta 4");
            } else if (diaActual == 2) {
                System.out.println("2. Jugar Salvado");
                System.out.println("3. Jugar Torres de Hanoi");
            }
            System.out.println("4. Ver Mejores Jugadores");
            System.out.println("5. Ver Puntos Acumulados");
            System.out.println("6. Guardar y Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    registrarJugador(scanner); // Registrar un nuevo jugador
                    break;
                case "2":
                    if (diaActual == 1) {
                        jugarCuadradoMagico(scanner); // Jugar Cuadrado Mágico
                    } else {
                        jugarSalvado(scanner); // Jugar Salvado
                    }
                    break;
                case "3":
                    if (diaActual == 1) {
                        jugarConecta4(scanner); // Jugar Conecta 4
                    } else {
                        jugarTorresDeHanoi(scanner); // Jugar Torres de Hanoi
                    }
                    break;
                case "4":
                    verMejoresJugadores(); // Mostrar los mejores jugadores
                    break;
                case "5":
                    verPuntosAcumulados(scanner); // Ver puntos acumulados por un jugador
                    break;
                case "6":
                    guardarJugadores(); // Guardar datos antes de salir
                    System.out.println("Datos guardados. Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (!opcion.equals("6"));
        
        scanner.close(); 
    }

    /**
     * Método para registrar un nuevo jugador.
     *
     * @param scanner Scanner para la entrada del usuario.
     */
    private static void registrarJugador(Scanner scanner) {
        if (contadorJugadores >= jugadores.length) {
            System.out.println("No se pueden registrar más jugadores.");
            return;
        }
        System.out.print("Ingrese su nombre: ");
        String nombreJugador = scanner.nextLine();
        
        for (int i = 0; i < contadorJugadores; i++) {
            if (jugadores[i].getNombre().equalsIgnoreCase(nombreJugador)) {
                System.out.println("El jugador ya está registrado con " + jugadores[i].getCreditos() + " créditos.");
                return;
            }
        }
        
        Jugador nuevoJugador = new Jugador(nombreJugador);
        jugadores[contadorJugadores++] = nuevoJugador; // Agregar nuevo jugador al arreglo y aumentar contador
        System.out.println("Jugador registrado: " + nuevoJugador.getNombre());
    }

    /**
     * Método para iniciar el juego Cuadrado Mágico.
     *
     * @param scanner Scanner para la entrada del usuario.
     */
    private static void jugarCuadradoMagico(Scanner scanner) {
        System.out.print("Ingrese su nombre: ");
        String nombreJugador = scanner.nextLine();
        Jugador jugadorActual = buscarJugador(nombreJugador);
        
        if (jugadorActual == null) {
            System.out.println("El jugador no está registrado. Regístrese primero.");
            return;
        }
        
        CuadradoMagico juegoCuadradoMagico = new CuadradoMagico();
        juegoCuadradoMagico.jugar(jugadorActual); // Iniciar el juego
    }

    /**
     * Método para iniciar el juego Conecta 4.
     *
     * @param scanner Scanner para la entrada del usuario.
     */
    private static void jugarConecta4(Scanner scanner) {
        System.out.print("Ingrese su nombre: ");
        String nombreJugador = scanner.nextLine();
        Jugador jugadorActual = buscarJugador(nombreJugador);
        
        if (jugadorActual == null) {
            System.out.println("El jugador no está registrado. Regístrese primero.");
            return;
        }
        
        Conecta4 juegoConecta4 = new Conecta4();
        juegoConecta4.jugar(jugadorActual); // Iniciar el juego
    }

    /**
     * Método para iniciar el juego Salvado.
     *
     * @param scanner Scanner para la entrada del usuario.
     */
    private static void jugarSalvado(Scanner scanner) {
        System.out.print("Ingrese su nombre: ");
        String nombreJugador = scanner.nextLine();
        Jugador jugadorActual = buscarJugador(nombreJugador);
        
        if (jugadorActual == null) {
            System.out.println("El jugador no está registrado. Regístrese primero.");
            return;
        }
        
        Salvado juegoSalvado = new Salvado();
        juegoSalvado.jugar(jugadorActual); // Iniciar el juego
    }

    /**
     * Método para iniciar el juego Torres de Hanoi.
     *
     * @param scanner Scanner para la entrada del usuario.
     */
    private static void jugarTorresDeHanoi(Scanner scanner) {
        System.out.print("Ingrese su nombre: ");
        String nombreJugador = scanner.nextLine();
        Jugador jugadorActual = buscarJugador(nombreJugador);
        
        if (jugadorActual == null) {
            System.out.println("El jugador no está registrado. Regístrese primero.");
            return;
        }
        
        TorresDeHanoi juegoTorresDeHanoi = new TorresDeHanoi(); 
        juegoTorresDeHanoi.jugar(jugadorActual); // Iniciar el juego
    }

    /**
     * Busca un jugador por su nombre en el arreglo de jugadores.
     *
     * @param nombre Nombre del jugador a buscar.
     * @return El objeto Jugador si se encuentra, null en caso contrario.
     */
    private static Jugador buscarJugador(String nombre) {
        for (int i = 0; i < contadorJugadores; i++) {
            if (jugadores[i].getNombre().equalsIgnoreCase(nombre)) {
                return jugadores[i]; // Retorna el jugador encontrado
            }
        }
        return null; 
    }

    /**
     * Muestra los mejores jugadores en función de sus puntos acumulados.
     */
    private static void verMejoresJugadores() {
         Jugador[] mejoresJugadores = new Jugador[3];
         
         for(int i=0;i<contadorJugadores;i++){
             if(mejoresJugadores[0]==null || jugadores[i].getPuntos()>mejoresJugadores[0].getPuntos()){
                 mejoresJugadores[2]=mejoresJugadores[1];
                 mejoresJugadores[1]=mejoresJugadores[0];
                 mejoresJugadores[0]=jugadores[i];
             }else if(mejoresJugadores[1]==null || jugadores[i].getPuntos()>mejoresJugadores[1].getPuntos()){
                 mejoresJugadores[2]=mejoresJugadores[1];
                 mejoresJugadores[1]=jugadores[i];
             }else if(mejoresJugadores[2]==null || jugadores[i].getPuntos()>mejoresJugadores[2].getPuntos()){
                 mejoresJugadores[2]=jugadores[i];
             }
         }
         
         System.out.println("--- Mejores Jugadores ---");
         
         for(int i=0;i<mejoresJugadores.length;i++){
             if(mejoresJugadores[i]!=null){
                 System.out.println((i + 1) + ". " + mejoresJugadores[i].getNombre() + ": " + mejoresJugadores[i].getPuntos() + " puntos");
             }
         }
    }

    /**
     * Muestra los puntos acumulados por un jugador específico.
     *
     * @param scanner Scanner para la entrada del usuario.
     */
    private static void verPuntosAcumulados(Scanner scanner) {
         System.out.print("Ingrese su nombre: ");
         String nombreJugador = scanner.nextLine();
         
         for (int i=0;i<contadorJugadores;i++){
             if (jugadores[i].getNombre().equalsIgnoreCase(nombreJugador)) {
                 System.out.println(jugadores[i].getNombre() + ", Puntos acumulados: " + jugadores[i].getPuntos() + ", Créditos restantes: " + jugadores[i].getCreditos());
                 return; 
             } 
         }
         
         System.out.println("El jugador no está registrado."); 
    }

   /**
    * Guarda los datos de los jugadores en un archivo.
    */
   private static void guardarJugadores() { 
       ObjectOutputStream oos = null; 
       try { 
           oos = new ObjectOutputStream(new FileOutputStream(NOMBRE_ARCHIVO_JUGADORES)); 
           oos.writeInt(contadorJugadores); // Guardar el contador también 
           for(int i=0;i<contadorJugadores;i++){ 
               oos.writeObject(jugadores[i]); // Guardar cada jugador individualmente 
           } 
           System.out.println("Datos guardados correctamente.");
       } catch(IOException e){ 
           System.out.println("Error al guardar los datos de los jugadores."); 
       } finally { 
           if(oos != null){ 
               try { 
                   oos.close(); 
               } catch(IOException e){ 
                   System.out.println("Error al cerrar el archivo de jugadores."); 
               } 
           } 
       } 
   }

   /**
    * Carga los datos de los jugadores desde un archivo.
    */
   private static void cargarJugadores() { 
       File archivoJugadores = new File(NOMBRE_ARCHIVO_JUGADORES); 
       if(archivoJugadores.exists()) { 
           ObjectInputStream ois = null; 
           try { 
               ois = new ObjectInputStream(new FileInputStream(NOMBRE_ARCHIVO_JUGADORES)); 
               contadorJugadores=ois.readInt(); // Cargar el contador primero 
               for(int i=0;i<contadorJugadores;i++){ 
                   jugadores[i]=(Jugador) ois.readObject(); // Cargar cada jugador individualmente 
               } 
               System.out.println("Datos cargados correctamente.");
           } catch(IOException e){ 
               System.out.println("Error al cargar los datos de los jugadores."); 
           } catch(ClassNotFoundException e){ 
               System.out.println("Error: Clase de jugador no encontrada."); 
           } finally { 
               if(ois != null){ 
                   try { 
                       ois.close(); 
                   } catch(IOException e){ 
                       System.out.println("Error al cerrar el archivo de jugadores."); 
                   } 
               } 
           } 
       } else { 
           System.out.println("El archivo de jugadores no existe. Se comenzará con un nuevo registro."); 
       } 
   }
}
