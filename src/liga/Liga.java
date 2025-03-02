package liga;
import personal.Equipo;
import personal.Persona;
import java.util.List;
import java.util.Arrays;
import java.util.Objects;
import personal.*;
import java.util.Scanner;

public class Liga {

    /* Variables */
    private String nombre;
    private List<Equipo> equipos;
    private Jornada[] jornadas;
    private Persona[] jugadores;

    /* Constructor */
    public Liga(String nombre, List<Equipo> equipos){
        this.nombre = nombre;
        this.equipos = equipos;
        // Crear jornadas (cada equipo juega contra todos los demás una vez)
        int numeroJornadas = (equipos.size() * 2) - 2; // Multiplicado por 2 para ida y vuelta
        this.jornadas = new Jornada[numeroJornadas];
    for (int i = 0; i < numeroJornadas; i++) {
            this.jornadas[i] = new Jornada(i + 1, this);
        }
    }


    /* Getters y setters  */

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }

    public Jornada[] getJornadas() {
        return jornadas;
    }

    public void setJornadas(Jornada[] jornadas) {
        this.jornadas = jornadas;
    }

    public Persona[] getJugadores() {
        return jugadores;
    }

    public void setJugadores(Persona[] jugadores) {
        this.jugadores = jugadores;
    }




    /*Metodos y funciones  */

    /* Crear liga */
    public static Liga crearLiga(String nombreLiga, Paises pais){
        String Entrenadores = "src/Documentos_Ligas/" + nombreLiga + "/Entrenadores_" + nombreLiga + ".txt";
        String Porteros = "src/Documentos_Ligas/" + nombreLiga + "/Porteros_" + nombreLiga + ".txt";
        String Jugadores = "src/Documentos_Ligas/" + nombreLiga + "/RestoDeJugadores_" + nombreLiga + ".txt";
        String Nombres_Equipos = "src/Documentos_Ligas/" + nombreLiga + "/Equipos_" + nombreLiga + ".txt";
        List<Entrenador> entrenadores = Entrenador.crearEntrenadores(Entrenadores, pais);
        List<Portero> porteros = Portero.crearPorteros(Porteros, pais);
        List<Jugador> jugadores = Jugador.crearJugadores(Jugadores, pais);
        List<Equipo> equipos = Equipo.crearEquipos(Nombres_Equipos, entrenadores, porteros, jugadores, pais);
        if (entrenadores.isEmpty() || porteros.isEmpty() || jugadores.isEmpty() || equipos.isEmpty()) {
            System.out.println("No se pudieron crear equipos. Verifica que hay suficientes jugadores y porteros.");
            return null;
        } else {
            Liga liga = new Liga(nombreLiga, equipos);
            return liga;
        }
    }

    /* Jugar liga */
    public void jugarLiga() {
        if (equipos == null || equipos.isEmpty()) {
            System.out.println("No hay equipos suficientes para jugar la jornada.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        /* Inicio de la liga */
        System.out.println("\n=== INICIO DE LA LIGA ===\n");
        /* Jugar jornadas */
        for (Jornada jornada : jornadas) {
            System.out.println("\n=== JORNADA " + jornada.getNumeroJornada() + " ===\n");
            jornada.jugarPartidos(equipos);
            if (jornada.getNumeroJornada() < jornadas.length) {
                /* Ver clasificacion */
                System.out.println("\n=== CLASIFICACIÓN TRAS LA JORNADA " + jornada.getNumeroJornada() + " ===");
                VerClasificacion();
                System.out.println("\nPresiona ENTER para continuar...");
                try {
                    scanner.nextLine();
                } catch (Exception e) {
                    System.out.println("Error al leer la respuesta");
                }
            } else {
                /* Ver clasificacion final */
                System.out.println("\n=== CLASIFICACIÓN FINAL DE LA LIGA ===");
                VerClasificacion();
                System.out.println("\nPresiona ENTER para continuar...");
                try {
                    scanner.nextLine();
                } catch (Exception e) {
                    System.out.println("Error al leer la respuesta");
                }
            }
        }
        System.out.println("\n=== FIN DE LA LIGA ===\n");
    }


    public void VerClasificacion(){
        System.out.println("\n=== CLASIFICACIÓN ===");
        System.out.println("Pos  Equipo                          PJ   PG   PE   PP   GF   GC   DG   Pts");
        System.out.println("---------------------------------------------------------------------------");
    
        /* Ordenar equipos por puntos y diferencia de goles */
        equipos.sort((e1, e2) -> {
            if (e2.getPuntos() != e1.getPuntos()) {
                return e2.getPuntos() - e1.getPuntos();
            }
            return e2.getDiferenciaGoles() - e1.getDiferenciaGoles();
        });
    
        /* Mostrar clasificacion */
        for (int i = 0; i < equipos.size(); i++) {
            Equipo e = equipos.get(i);
            int partidosJugados = e.getPartidosJugados();
            int partidosGanados = e.getPuntos() / 3;
            int partidosEmpatados = e.getPuntos() % 3;
            int partidosPerdidos = partidosJugados - partidosGanados - partidosEmpatados;
    
            /* Formato de la clasificacion */
            String nombreEquipo = e.getNombre();
            if (nombreEquipo.length() > 30) {
                nombreEquipo = nombreEquipo.substring(0, 27) + "...";
            }
            System.out.printf("%-4d %-30s %4d %4d %4d %4d %4d %4d %4d %4d%n",
                i + 1,
                nombreEquipo,
                partidosJugados,
                partidosGanados,
                partidosEmpatados,
                partidosPerdidos,
                e.getGolesAfavor(),
                e.getGolesEnContra(),
                e.getDiferenciaGoles(),
                e.getPuntos()
            );
        }
        System.out.println("---------------------------------------------------------------------------");
    }




    /* Equals  */

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Liga liga = (Liga) o;
        return Objects.equals(nombre, liga.nombre) && Objects.deepEquals(equipos, liga.equipos) && Objects.deepEquals(jornadas, liga.jornadas) && Objects.deepEquals(jugadores, liga.jugadores);
    }

    /* To string */

    @Override
    public String toString() {
        return "Liga{" +
                "nombre='" + nombre + '\'' +
                ", equipos=" + equipos +
                ", jornadas=" + Arrays.toString(jornadas) +
                ", jugadores=" + Arrays.toString(jugadores) +
                '}';
    }
}
