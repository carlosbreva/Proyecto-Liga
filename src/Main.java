import personal.*;
import liga.*;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  // Un solo Scanner para toda la aplicación
        boolean seguirJugando = true;
        List<Equipo> equiposEuropa = new ArrayList<>(Arrays.asList(new Equipo[32])); // Inicializar con 32 espacios nulos
        
        // Definir índices para cada liga (4 equipos por liga)
        final int BUNDESLIGA_START = 0;   // 0-3
        final int LALIGA_START = 4;       // 4-7
        final int SERIEA_START = 8;       // 8-11
        final int LIGUE1_START = 12;      // 12-15
        final int PREMIER_START = 16;     // 16-19
        final int PRIMEIRA_START = 20;    // 20-23
        final int EREDIVISIE_START = 24;  // 24-27
        final int BELGIAN_START = 28;     // 28-31
        final int BUNDESLIGA_AUSTRALIA_START = 32; // 32-35

        while (seguirJugando) {
            System.out.println("=== XTART fÚTBOL  SIMULATOR ===\n");
            System.out.println("=== OPCIONES DISPONIBLES (Recomendamos jugar todas las ligas para obtener una experiencia sorpresa...): ===\n");
            System.out.println("1. Bundesliga (Alemania)");
            System.out.println("2. La Liga (España)");
            System.out.println("3. Serie A (Italia)");
            System.out.println("4. Ligue 1 (Francia)");
            System.out.println("5. Premier League (Inglaterra)");
            System.out.println("6. Primeira Liga (Portugal)");
            System.out.println("7. Eredivisie (Holanda)");
            System.out.println("8. Belgian Pro League (Bélgica)");
            System.out.println("9. Bundesliga De Australia");
            System.out.println("10. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            Liga ligaActual = null;
            int startIndex = 0;

            switch (opcion) {
                case 1:
                    System.out.println("Has seleccionado la Bundesliga");
                    ligaActual = Liga.crearLiga("Bundesliga");
                    startIndex = BUNDESLIGA_START;
                    break;
                case 2:
                    System.out.println("Has seleccionado la La Liga");
                    ligaActual = Liga.crearLiga("LaLiga");
                    startIndex = LALIGA_START;
                    break;
                case 3:
                    System.out.println("Has seleccionado la Serie A");
                    ligaActual = Liga.crearLiga("SerieA");
                    startIndex = SERIEA_START;
                    break;
                case 4:
                    System.out.println("Has seleccionado la Ligue 1");
                    ligaActual = Liga.crearLiga("Ligue1");
                    startIndex = LIGUE1_START;
                    break;
                case 5:
                    System.out.println("Has seleccionado la Premier League");
                    ligaActual = Liga.crearLiga("PremierLeague");
                    startIndex = PREMIER_START;
                    break;
                case 6:
                    System.out.println("Has seleccionado la Primeira Liga");
                    ligaActual = Liga.crearLiga("PrimeiraLiga");
                    startIndex = PRIMEIRA_START;
                    break;
                case 7:
                    System.out.println("Has seleccionado la Eredivisie");
                    ligaActual = Liga.crearLiga("Eredivisie");
                    startIndex = EREDIVISIE_START;
                    break;
                case 8:
                    System.out.println("Has seleccionado la Belgian Pro League");
                    ligaActual = Liga.crearLiga("BelgianProLeague");
                    startIndex = BELGIAN_START;
                    break;
                case 9:
                    System.out.println("Has seleccionado la Bundesliga De Australia");
                    ligaActual = Liga.crearLiga("BundesligaDeAustralia");
                    startIndex = BUNDESLIGA_AUSTRALIA_START;
                    break;
                case 10:
                    seguirJugando = false;
                    System.out.println("Gracias por jugar!");
                    break;
            }

            if (ligaActual != null) {
                ligaActual.jugarLiga();
                List<Equipo> equipos = ligaActual.getEquipos();
                List<Jugador> jugadores = new ArrayList<>();
                List<Portero> porteros = new ArrayList<>();
                
                // Separar jugadores y porteros del equipo
                for (Equipo equipo : equipos) {
                    for (Jugador jugador : equipo.getJugadores()) {
                        if (jugador instanceof Portero) {
                            porteros.add((Portero) jugador);
                        } else {
                            jugadores.add(jugador);
                        }
                    }
                }
                
                // Dar premios y obtener equipos para Europa
                Premios.darPremios(jugadores, equipos, porteros);
                List<Equipo> equiposParaEuropa = Premios.EquiposAEuropa(equipos);
                
                // Asegurar exactamente 4 equipos para Europa
                for (int i = 0; i < 4 && i < equiposParaEuropa.size(); i++) {
                    equiposEuropa.set(startIndex + i, equiposParaEuropa.get(i));
                }
            }

            if (seguirJugando) {
                System.out.println("¿Quieres jugar otra liga? (1. Si 2. No)");
                int respuesta = scanner.nextInt();
                scanner.nextLine();
                if (respuesta != 1) {
                    seguirJugando = false;
                }
            }
        }
        scanner.close();
    }
}
 