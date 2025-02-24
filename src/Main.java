import personal.Persona;
import personal.Equipo;
import personal.Jugador;
import personal.Entrenador;
import java.util.List;
import personal.Portero;
import liga.Partido;
import liga.Jornada;
import liga.Liga;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  // Un solo Scanner para toda la aplicación
        boolean seguirJugando = true;

        while (seguirJugando) {
            System.out.println("=== XTART FURBOL SIMULATOR ===\n");
            System.out.println("=== OPCIONES DISPONIBLES: ===\n");
            System.out.println("1. Bundesliga");
            System.out.println("2. La Liga");
            System.out.println("3. Serie A");
            System.out.println("4. Ligue 1");
            System.out.println("6. Salir");
            
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    System.out.println("Has seleccionado la Bundesliga");
                    String rutaFichero = "src/Nombres_Jugadores.txt";
                    List<Entrenador> entrenadores = Entrenador.crearEntrenadores(rutaFichero);
                    List<Portero> porteros = Portero.crearPorteros(rutaFichero);
                    List<Jugador> jugadores = Jugador.crearJugadores(rutaFichero);
                    List<Equipo> equipos = Equipo.crearEquipos(entrenadores, porteros, jugadores);
                    Liga liga = new Liga("Bundesliga", equipos);
                    liga.jugarLiga();
                    
                    System.out.println("¿Quieres jugar otra liga? (1. Si 2. No)");
                    int respuesta = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea
                    
                    if (respuesta != 1) {
                        seguirJugando = false;
                        System.out.println("Gracias por jugar");
                    } else {
                        continue; // Volver al menú principal
                    }
                    break;

                case 6:
                    seguirJugando = false;
                    System.out.println("Gracias por jugar");
                    break;
            }
        }
        scanner.close(); // Cerrar el Scanner solo al final del programa
    }
}