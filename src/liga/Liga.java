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

//Constructor//
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

  public Liga(){
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

    public static Liga crearLiga(String nombreLiga, Paises pais){
        String Entrenadores = "src/Documentos_Ligas/" + nombreLiga + "/Entrenadores_" + nombreLiga + ".txt";
        String Porteros = "src/Documentos_Ligas/" + nombreLiga + "/Porteros_" + nombreLiga + ".txt";
        String Jugadores = "src/Documentos_Ligas/" + nombreLiga + "/RestoDeJugadores_" + nombreLiga + ".txt";
        String Nombres_Equipos = "src/Documentos_Ligas/" + nombreLiga + "/Equipos_" + nombreLiga + ".txt";
        List<Entrenador> entrenadores = Entrenador.crearEntrenadores(Entrenadores, pais);
        List<Portero> porteros = Portero.crearPorteros(Porteros, pais);
        List<Jugador> jugadores = Jugador.crearJugadores(Jugadores, pais);
        List<Equipo> equipos = Equipo.crearEquipos(Nombres_Equipos, entrenadores, porteros, jugadores, pais);
        Liga liga = new Liga(nombreLiga, equipos);
        return liga;
    }


    public void jugarLiga() {
        if (equipos == null || equipos.isEmpty()) {
            System.out.println("No hay equipos suficientes para jugar la jornada.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=== INICIO DE LA LIGA ===\n");
        for (Jornada jornada : jornadas) {
            System.out.println("\n=== JORNADA " + jornada.getNumeroJornada() + " ===\n");
            jornada.jugarPartidos(equipos);
            if (jornada.getNumeroJornada() < jornadas.length) {
                System.out.println("\n=== CLASIFICACIÓN TRAS LA JORNADA " + jornada.getNumeroJornada() + " ===");
                VerClasificacion();
                System.out.println("\nPulsa ENTER para continuar...");
                scanner.nextLine();
            } else {
                System.out.println("\n=== CLASIFICACIÓN FINAL DE LA LIGA ===");
                VerClasificacion();
                System.out.println("\nPulsa ENTER para continuar...");
                scanner.nextLine();
            }
        }
        System.out.println("\n=== FIN DE LA LIGA ===\n");
    }


public void VerClasificacion(){
    System.out.println("\n=== CLASIFICACIÓN ===");
    System.out.println("Pos  Equipo                  PJ   PG   PE   PP   GF   GC   DG   Pts");
    System.out.println("----------------------------------------------------------------");

    equipos.sort((e1, e2) -> {
        if (e2.getPuntos() != e1.getPuntos()) {
            return e2.getPuntos() - e1.getPuntos();
        }
        return e2.getDiferenciaGoles() - e1.getDiferenciaGoles();
    });

    for (int i = 0; i < equipos.size(); i++) {
        Equipo e = equipos.get(i);
        int partidosJugados = e.getPartidosJugados();
        int partidosGanados = e.getPuntos() / 3;
        int partidosEmpatados = e.getPuntos() % 3;
        int partidosPerdidos = partidosJugados - partidosGanados - partidosEmpatados;

        System.out.printf("%-4d %-20s %4d %4d %4d %4d %4d %4d %4d %4d%n",
            i + 1,
            e.getNombre(),
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
    System.out.println("----------------------------------------------------------------");
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
