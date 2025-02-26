import personal.*;
import liga.*;
import Champions.*;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.function.BiFunction;

public class Main_copy {
    private static List<Equipo> crearEquiposDirectamente() {
        List<Equipo> equipos = new ArrayList<>();
        Random random = new Random();
        
        // Función auxiliar para crear jugadores aleatorios
        BiFunction<String, Posicion, Jugador> crearJugador = (nombre, posicion) -> {
            int edad = 18 + random.nextInt(22);
            int ritmo = 40 + random.nextInt(61);
            int pase = 40 + random.nextInt(61);
            int tiros = 40 + random.nextInt(61);
            int defensa = 40 + random.nextInt(61);
            int regate = 40 + random.nextInt(61);
            int fisico = 40 + random.nextInt(61);
            int velocidad = 40 + random.nextInt(61);
            int statMedia = (ritmo + pase + tiros + defensa + regate + fisico + velocidad) / 7;
            
            return new Jugador(nombre, edad, posicion, 0, 0, 0, velocidad, ritmo, pase, tiros, defensa, regate, fisico, statMedia);
        };

        // Función auxiliar para crear porteros aleatorios
        BiFunction<String, Posicion, Portero> crearPortero = (nombre, posicion) -> {
            int edad = 18 + random.nextInt(22);
            int estirada = 40 + random.nextInt(61);
            int agarre = 40 + random.nextInt(61);
            int saqueLargo = 40 + random.nextInt(61);
            int reflejos = 40 + random.nextInt(61);
            int posicionamiento = 40 + random.nextInt(61);
            int statMedia = (estirada + agarre + saqueLargo + reflejos + posicionamiento) / 5;
            
            return new Portero(nombre, edad, posicion, 0, estirada, agarre, saqueLargo, reflejos, posicionamiento, statMedia);
        };

        // Función para crear un equipo completo con jugadores
        BiFunction<String, String, Equipo> crearEquipoCompleto = (nombreEquipo, nombreEntrenador) -> {
            Equipo equipo = new Equipo(nombreEquipo, nombreEntrenador);
            List<Jugador> jugadores = new ArrayList<>();
            
            // Añadir 2 porteros
            for (int i = 0; i < 2; i++) {
                jugadores.add(crearPortero.apply(nombreEquipo + "_Portero" + (i+1), Posicion.PORTERO));
            }
            
            // Añadir 8 defensas
            for (int i = 0; i < 8; i++) {
                jugadores.add(crearJugador.apply(nombreEquipo + "_Defensa" + (i+1), Posicion.DEFENSA));
            }
            
            // Añadir 8 mediocentros
            for (int i = 0; i < 8; i++) {
                jugadores.add(crearJugador.apply(nombreEquipo + "_Medio" + (i+1), Posicion.MEDIOCENTRO));
            }
            
            // Añadir 4 delanteros
            for (int i = 0; i < 4; i++) {
                jugadores.add(crearJugador.apply(nombreEquipo + "_Delantero" + (i+1), Posicion.DELANTERO));
            }
            
            equipo.setJugadores(jugadores);
            return equipo;
        };
        
        // Equipos de LaLiga
        equipos.add(crearEquipoCompleto.apply("Real Madrid", "Carlo Ancelotti"));
        equipos.add(crearEquipoCompleto.apply("Barcelona", "Xavi Hernández"));
        equipos.add(crearEquipoCompleto.apply("Atlético Madrid", "Diego Simeone"));
        equipos.add(crearEquipoCompleto.apply("Real Sociedad", "Imanol Alguacil"));
        
        // Equipos de Premier League
        equipos.add(crearEquipoCompleto.apply("Manchester City", "Pep Guardiola"));
        equipos.add(crearEquipoCompleto.apply("Arsenal", "Mikel Arteta"));
        equipos.add(crearEquipoCompleto.apply("Manchester United", "Erik ten Hag"));
        equipos.add(crearEquipoCompleto.apply("Newcastle", "Eddie Howe"));
        
        // Equipos de Bundesliga
        equipos.add(crearEquipoCompleto.apply("Bayern Munich", "Thomas Tuchel"));
        equipos.add(crearEquipoCompleto.apply("Borussia Dortmund", "Edin Terzic"));
        equipos.add(crearEquipoCompleto.apply("RB Leipzig", "Marco Rose"));
        equipos.add(crearEquipoCompleto.apply("Union Berlin", "Urs Fischer"));
        
        // Equipos de Serie A
        equipos.add(crearEquipoCompleto.apply("Napoli", "Rudi Garcia"));
        equipos.add(crearEquipoCompleto.apply("Inter", "Simone Inzaghi"));
        equipos.add(crearEquipoCompleto.apply("Milan", "Stefano Pioli"));
        equipos.add(crearEquipoCompleto.apply("Lazio", "Maurizio Sarri"));
        
        // Equipos de Ligue 1
        equipos.add(crearEquipoCompleto.apply("PSG", "Luis Enrique"));
        equipos.add(crearEquipoCompleto.apply("Marseille", "Marcelino"));
        equipos.add(crearEquipoCompleto.apply("Lens", "Franck Haise"));
        equipos.add(crearEquipoCompleto.apply("Monaco", "Adi Hütter"));
        
        // Equipos de Primeira Liga
        equipos.add(crearEquipoCompleto.apply("Benfica", "Roger Schmidt"));
        equipos.add(crearEquipoCompleto.apply("Porto", "Sérgio Conceição"));
        equipos.add(crearEquipoCompleto.apply("Sporting CP", "Rúben Amorim"));
        equipos.add(crearEquipoCompleto.apply("Braga", "Artur Jorge"));
        
        // Equipos de Eredivisie
        equipos.add(crearEquipoCompleto.apply("Ajax", "Maurice Steijn"));
        equipos.add(crearEquipoCompleto.apply("PSV", "Peter Bosz"));
        equipos.add(crearEquipoCompleto.apply("Feyenoord", "Arne Slot"));
        equipos.add(crearEquipoCompleto.apply("AZ Alkmaar", "Pascal Jansen"));
        
        // Equipos de Belgian Pro League
        equipos.add(crearEquipoCompleto.apply("Genk", "Wouter Vrancken"));
        equipos.add(crearEquipoCompleto.apply("Antwerp", "Mark van Bommel"));
        equipos.add(crearEquipoCompleto.apply("Club Brugge", "Ronny Deila"));
        equipos.add(crearEquipoCompleto.apply("Union SG", "Alexander Blessin"));

        return equipos;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== XTART FÚTBOL SIMULATOR - CHAMPIONS LEAGUE ===\n");
        
        // Crear equipos directamente
        List<Equipo> equiposChampions = crearEquiposDirectamente();
        
        System.out.println("32 equipos han sido clasificados automáticamente para la Champions League.");
        System.out.println("\nPresiona Enter para comenzar el sorteo...");
        scanner.nextLine();
        
        // Crear y jugar la fase de grupos
        Fase_De_Grupos faseGrupos = new Fase_De_Grupos(equiposChampions);
        faseGrupos.AsignarGrupos();
        
        System.out.println("\nPresiona Enter para comenzar los partidos...");
        scanner.nextLine();
        
        faseGrupos.JugarGrupos();
        faseGrupos.VerClasificacion();
        
        List<Equipo> clasificadosOctavos = faseGrupos.getClasificadosOctavos();
        System.out.println("\n=== EQUIPOS CLASIFICADOS A OCTAVOS DE FINAL ===");
        for (Equipo e : clasificadosOctavos) {
            System.out.println("- " + e.getNombre());
        }
        
        scanner.close();
    }
}
 