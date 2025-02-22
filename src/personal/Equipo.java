package personal;

import liga.Partido;
import personal.Entrenador;
import java.util.Arrays;
import java.util.Objects;
import java.util.ArrayList;
import java.util.List;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Random;
import java.io.FileNotFoundException;
import personal.Jugador;
import personal.Portero;

public class Equipo {
    private String nombre;
    private String nombreEntrenador;
    private List<Jugador> jugadores = new ArrayList<>();
    private int golesAfavor;
    private int golesEnContra;
    private int diferenciaGoles;
    private int mediaStatsEquipo;
    private int puntos;
    private Partido[] partidos;
    private static String rutaFichero = "src/Nombres_Equipos.txt";
    private static Random random = new Random();

    public Equipo(String nombre, String nombreEntrenador, List<Jugador> jugadores, int golesAfavor, int golesEnContra, int diferenciaGoles, int mediaStatsEquipo, int puntos, Partido[] partidos) {
        this.nombre = nombre;
        this.nombreEntrenador = nombreEntrenador;
        this.jugadores = jugadores;
        this.golesAfavor = golesAfavor;
        this.golesEnContra = golesEnContra;
        this.diferenciaGoles = diferenciaGoles;
        this.mediaStatsEquipo = mediaStatsEquipo;
        this.puntos = puntos;
        this.partidos = partidos;
    }

    public Equipo(String nombre, String nombreEntrenador) {
        this.nombre = nombre;
        this.nombreEntrenador = nombreEntrenador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public int getGolesAfavor() {
        return golesAfavor;
    }

    public void setGolesAfavor(int golesAfavor) {
        this.golesAfavor = golesAfavor;
    }

    public int getGolesEnContra() {
        return golesEnContra;
    }

    public void setGolesEnContra(int golesEnContra) {
        this.golesEnContra = golesEnContra;
    }

    public int getDiferenciaGoles() {
        return diferenciaGoles;
    }

    public void setDiferenciaGoles(int diferenciaGoles) {
        this.diferenciaGoles = diferenciaGoles;
    }

    public int getMediaStatsEquipo() {
        return mediaStatsEquipo;
    }

    public void setMediaStatsEquipo(int mediaStatsEquipo) {
        this.mediaStatsEquipo = mediaStatsEquipo;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public Partido[] getPartidos() {
        return partidos;
    }

    public void setPartidos(Partido[] partidos) {
        this.partidos = partidos;
    }
    
   public String getNombreEntrenador() {
        return nombreEntrenador;
    }

    public void setNombreEntrenador(String nombreEntrenador) {
        this.nombreEntrenador = nombreEntrenador;
    }

public static List<Equipo> crearEquipos(List<Entrenador> entrenadores, List<Portero> porteros, List<Jugador> jugadores) {
    List<Equipo> equipos = new ArrayList<>();
    int indiceEntrenador = 0;
    int indiceJugador = 0;
    int indicePortero = 0;
    
    try (BufferedReader br = new BufferedReader(new FileReader(rutaFichero))) {
        String linea;
        while ((linea = br.readLine()) != null && indiceEntrenador < entrenadores.size() 
               && indicePortero + 2 <= porteros.size()  
               && indiceJugador + 20 <= jugadores.size()) { 
            
            if (!linea.trim().isEmpty()) {
                String nombreEntrenador = entrenadores.get(indiceEntrenador).getNombre();
                List<Jugador> jugadoresEquipo = new ArrayList<>();
                
                // Agregar 2 porteros
                for (int i = 0; i < 2; i++) {
                    Portero portero = porteros.get(indicePortero++);
                    jugadoresEquipo.add(new Jugador(
                        portero.getNombre(), 
                        portero.getEdad(), 
                        Posicion.PORTERO, 
                        0, 0, 0,  // tarjetas y goles
                        portero.getReflejos(),  // usando reflejos como velocidad
                        portero.getEstirada(),  // usando estirada como ritmo
                        portero.getAgarre(),    // usando agarre como pase
                        portero.getSaqueLargo(),// usando saqueLargo como tiros
                        portero.getPosicionamiento(), // usando posicionamiento como defensa
                        portero.getReflejos(),  // usando reflejos como regate
                        portero.getAgarre(),    // usando agarre como físico
                        portero.getStatMediaJugador()
                    ));
                }
                
                // Agregar 8 defensas
                for (int i = 0; i < 8; i++) {
                    Jugador jugador = jugadores.get(indiceJugador++);
                    jugadoresEquipo.add(new Jugador(
                        jugador.getNombre(),
                        jugador.getEdad(),
                        Posicion.DEFENSA,
                        0, 0, 0,  // Inicializar tarjetas y goles en 0
                        jugador.getRitmo(),
                        jugador.getPase(),
                        jugador.getTiros(),
                        jugador.getDefensa(),
                        jugador.getRegate(),
                        jugador.getFisico(),
                        jugador.getRitmo(),  // velocidad
                        jugador.getStatMediaJugador()
                    ));
                }
                
                // Agregar 8 mediocentros
                for (int i = 0; i < 8; i++) {
                    Jugador jugador = jugadores.get(indiceJugador++);
                    jugadoresEquipo.add(new Jugador(
                        jugador.getNombre(),
                        jugador.getEdad(),
                        Posicion.MEDIOCENTRO,
                        0, 0, 0,  // Inicializar tarjetas y goles en 0
                        jugador.getRitmo(),
                        jugador.getPase(),
                        jugador.getTiros(),
                        jugador.getDefensa(),
                        jugador.getRegate(),
                        jugador.getFisico(),
                        jugador.getRitmo(),  // velocidad
                        jugador.getStatMediaJugador()
                    ));
                }
                
                // Agregar 4 delanteros
                for (int i = 0; i < 4; i++) {
                    Jugador jugador = jugadores.get(indiceJugador++);
                    jugadoresEquipo.add(new Jugador(
                        jugador.getNombre(),
                        jugador.getEdad(),
                        Posicion.DELANTERO,
                        0, 0, 0,  // Inicializar tarjetas y goles en 0
                        jugador.getRitmo(),
                        jugador.getPase(),
                        jugador.getTiros(),
                        jugador.getDefensa(),
                        jugador.getRegate(),
                        jugador.getFisico(),
                        jugador.getRitmo(),  // velocidad
                        jugador.getStatMediaJugador()
                    ));
                }
                
                // Calcular la media de estadísticas del equipo
                int sumaStats = 0;
                for (Jugador j : jugadoresEquipo) {
                    int mediaJugador = (j.getRitmo() + j.getPase() + j.getTiros() + 
                                      j.getDefensa() + j.getRegate() + j.getFisico()) / 6;
                    sumaStats += mediaJugador;
                }
                int mediaStats = sumaStats / jugadoresEquipo.size();
                
                Equipo equipo = new Equipo(linea.trim(), nombreEntrenador, jugadoresEquipo, 0, 0, 0, mediaStats, 0, null);
                equipos.add(equipo);
                indiceEntrenador++;
            }
        }
    } catch (FileNotFoundException e) {
        System.err.println("No se encontró el archivo de nombres de equipos: " + rutaFichero);
    } catch (IOException e) {
        System.err.println("Error al leer el archivo: " + e.getMessage());
    }
    
    if (equipos.isEmpty()) {
        System.err.println("No se pudieron crear equipos. Verifica que hay suficientes jugadores y porteros.");
    } else if (indiceEntrenador < entrenadores.size()) {
        System.err.println("Advertencia: No se pudieron crear todos los equipos por falta de jugadores o porteros.");
        System.err.println("Equipos creados: " + equipos.size());
    }
    
    return equipos;  
}

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Equipo equipo = (Equipo) o;
        return golesAfavor == equipo.golesAfavor && golesEnContra == equipo.golesEnContra && diferenciaGoles == equipo.diferenciaGoles && mediaStatsEquipo == equipo.mediaStatsEquipo && puntos == equipo.puntos && Objects.equals(nombre, equipo.nombre) && Objects.deepEquals(jugadores, equipo.jugadores) && Objects.deepEquals(partidos, equipo.partidos);
    }

    @Override
    public String toString() {
        StringBuilder jugadoresStr = new StringBuilder();
        for (Jugador jugador : jugadores) {
            jugadoresStr.append("\n    - ").append(jugador.getNombre()).append(" (").append(jugador.getPosicion()).append(")");
        }
        
        return "Equipo: " + nombre + 
               "\nEntrenador: " + nombreEntrenador + 
               "\nJugadores:" + jugadoresStr.toString() +
               "\nGoles a favor: " + golesAfavor +
               "\nGoles en contra: " + golesEnContra +
               "\nDiferencia de goles: " + diferenciaGoles +
               "\nMedia stats equipo: " + mediaStatsEquipo +
               "\nPuntos: " + puntos +
               "\nPartidos: " + Arrays.toString(partidos);
    }
}








