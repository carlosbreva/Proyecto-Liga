import personal.Persona;
import personal.Equipo;
import personal.Jugador;
import personal.Entrenador;
import java.util.List;
import personal.Portero;
import liga.Partido;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== CREANDO LIGA ===\n");

        List<Entrenador> entrenadores = Entrenador.crearEntrenadores();
        System.out.println("Entrenadores creados: " + entrenadores.size());

        List<Portero> porteros = Portero.crearPorteros();
        System.out.println("Porteros creados: " + porteros.size());
        
        List<Jugador> jugadores = Jugador.crearJugadores();
        System.out.println("Jugadores creados: " + jugadores.size());


        System.out.println("\n=== EQUIPOS DE LA LIGA ===\n");
        List<Equipo> equipos = Equipo.crearEquipos(entrenadores, porteros, jugadores);
        for (Equipo equipo : equipos) {
            System.out.println("==========================================");
            System.out.println(equipo);
            System.out.println("==========================================\n");
        }
        System.out.println("Equipos creados: " + equipos.size());

        Partido partido = new Partido(equipos.get(18), equipos.get(19), new int[] {0, 0}, 0);
        partido.simularPartido(equipos.get(18), equipos.get(19), jugadores);
        System.out.println(partido);
    }

}

