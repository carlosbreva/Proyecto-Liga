import personal.*;
import liga.*;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  // Un solo Scanner para toda la aplicación
        boolean seguirJugando = true;
        List<Equipo> EquiposEuropa= new ArrayList<>();

        while (seguirJugando && EquiposEuropa.size() < 8) {
            System.out.println("=== XTART FURBOL SIMULATOR ===\n");
            System.out.println("=== OPCIONES DISPONIBLES (Recomendamos jugar todas las ligas para obtener una experiencia sorpresa...): ===\n");
            System.out.println("1. Bundesliga");
            System.out.println("2. La Liga");
            System.out.println("3. Serie A");
            System.out.println("4. Ligue 1");
            System.out.println("5. Salir");


            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    System.out.println("Has seleccionado la Bundesliga");
                    String Entrenadores_Bundesliga = "src/Documentos_Ligas/Bundesliga/Entrenadores_Bundesliga.txt";
                    String Porteros_Bundesliga = "src/Documentos_Ligas/Bundesliga/Porteros_Bundesliga.txt";
                    String Jugadores_Bundesliga = "src/Documentos_Ligas/Bundesliga/RestoDeJugadores_Bundesliga.txt";
                    String Nombres_Equipos_Bundesliga = "src/Documentos_Ligas/Bundesliga/Equipos_Bundesliga.txt";
                    String Calendario_Partidos_Bundesliga = "src/Documentos_Ligas/Bundesliga/Calendario_Partidos_Bundesliga.txt";
                    List<Entrenador> entrenadores_bundesliga = Entrenador.crearEntrenadores(Entrenadores_Bundesliga);
                    List<Portero> porteros_bundesliga = Portero.crearPorteros(Porteros_Bundesliga);
                    List<Jugador> jugadores_bundesliga = Jugador.crearJugadores(Jugadores_Bundesliga);
                    List<Equipo> equipos_bundesliga = Equipo.crearEquipos(Nombres_Equipos_Bundesliga, entrenadores_bundesliga, porteros_bundesliga, jugadores_bundesliga);
                    Liga liga_bundesliga = new Liga("Bundesliga", equipos_bundesliga, Calendario_Partidos_Bundesliga);
                    liga_bundesliga.jugarLiga();
                    Premios.darPremios(jugadores_bundesliga, equipos_bundesliga, porteros_bundesliga);

                    
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

                case 2:
                    System.out.println("Has seleccionado la La Liga");
                    String Entrenadores_LaLiga = "src/Documentos_Ligas/LaLiga/Entrenadores_LaLiga.txt";
                    String Porteros_LaLiga = "src/Documentos_Ligas/LaLiga/Porteros_LaLiga.txt";
                    String Jugadores_LaLiga = "src/Documentos_Ligas/LaLiga/RestoDeJugadores_LaLiga.txt";
                    String Nombres_Equipos_LaLiga = "src/Documentos_Ligas/LaLiga/Equipos_LaLiga.txt";
                    String Calendario_Partidos_LaLiga = "src/Documentos_Ligas/LaLiga/Calendario_Partidos_LaLiga.txt";
                    List<Entrenador> entrenadores_laliga = Entrenador.crearEntrenadores(Entrenadores_LaLiga);
                    List<Portero> porteros_laliga = Portero.crearPorteros(Porteros_LaLiga);
                    List<Jugador> jugadores_laliga = Jugador.crearJugadores(Jugadores_LaLiga);
                    List<Equipo> equipos_laliga = Equipo.crearEquipos(Nombres_Equipos_LaLiga, entrenadores_laliga, porteros_laliga, jugadores_laliga);
                    Liga liga_laliga = new Liga("La Liga", equipos_laliga, Calendario_Partidos_LaLiga);
                    liga_laliga.jugarLiga();
                    Premios.darPremios(jugadores_laliga, equipos_laliga, porteros_laliga);

                    System.out.println("¿Quieres jugar otra liga? (1. Si 2. No)");
                    int respuesta2 = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea
                    
                    if (respuesta2 != 1) {
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
            }
        }
 