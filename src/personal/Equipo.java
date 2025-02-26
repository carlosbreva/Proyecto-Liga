package personal;

import liga.Partido;
import java.util.Arrays;
import java.util.Objects;
import java.util.ArrayList;
import java.util.List;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;


public class Equipo {
    private String nombre;
    private String nombreEntrenador;
    private List<Jugador> jugadores = new ArrayList<>();
    private Paises pais;
    private int golesAfavor;
    private int golesEnContra;
    private int diferenciaGoles;
    private int mediaStatsEquipo;
    private int puntos;
    private Partido[] partidos;
    private int partidosJugados;

    public Equipo(String nombre, String nombreEntrenador, List<Jugador> jugadores, Paises pais, int golesAfavor, int golesEnContra, int diferenciaGoles, int mediaStatsEquipo, int puntos, Partido[] partidos, int partidosJugados) {
        this.nombre = nombre;
        this.nombreEntrenador = nombreEntrenador;
        this.jugadores = jugadores;
        this.pais = pais;
        this.golesAfavor = golesAfavor;
        this.golesEnContra = golesEnContra;
        this.diferenciaGoles = diferenciaGoles;
        this.mediaStatsEquipo = mediaStatsEquipo;
        this.puntos = puntos;
        this.partidos = partidos;
        this.partidosJugados = partidosJugados;
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

    public int getPartidosJugados() {
        return partidosJugados;
    }

    public void setPartidosJugados(int partidosJugados) {
        this.partidosJugados = partidosJugados;
    }

    public Paises getPais() {
        return pais;
    }

    public void setPais(Paises pais) {
        this.pais = pais;
    }
public static List<Equipo> crearEquipos(String rutaFichero, List<Entrenador> entrenadores, List<Portero> porteros, List<Jugador> jugadores, Paises pais) {
    List<Equipo> equipos = new ArrayList<>();
    int indiceEntrenador = 0;
    
    try (BufferedReader br = new BufferedReader(new FileReader(rutaFichero))) {
        String linea;
        while ((linea = br.readLine()) != null && indiceEntrenador < entrenadores.size()) { 
            if (!linea.trim().isEmpty()) {
                String nombreEntrenador = entrenadores.get(indiceEntrenador).getNombre();
                List<Jugador> jugadoresEquipo = new ArrayList<>();
                
                // Agregar 2 porteros
                for (int i = 0; i < 2 && i < porteros.size(); i++) {
                    jugadoresEquipo.add(porteros.get(i));
                }
                
                // Agregar 8 defensas
                int defensasAgregados = 0;
                for (Jugador j : jugadores) {
                    if (j.getPosicion() == Posicion.DEFENSA && defensasAgregados < 8) {
                        jugadoresEquipo.add(j);
                        defensasAgregados++;
                    }
                }
                
                // Agregar 8 mediocentros
                int mediocentrosAgregados = 0;
                for (Jugador j : jugadores) {
                    if (j.getPosicion() == Posicion.MEDIOCENTRO && mediocentrosAgregados < 8) {
                        jugadoresEquipo.add(j);
                        mediocentrosAgregados++;
                    }
                }
                
                // Agregar 4 delanteros
                int delanterosAgregados = 0;
                for (Jugador j : jugadores) {
                    if (j.getPosicion() == Posicion.DELANTERO && delanterosAgregados < 4) {
                        jugadoresEquipo.add(j);
                        delanterosAgregados++;
                    }
                }
                
                // Calcular la media de estadísticas del equipo
                int sumaStats = 0;
                for (Jugador j : jugadoresEquipo) {
                    int mediaJugador = (j.getRitmo() + j.getPase() + j.getTiros() + 
                                      j.getDefensa() + j.getRegate() + j.getFisico()) / 6;
                    sumaStats += mediaJugador;
                }
                int mediaStats = sumaStats / jugadoresEquipo.size();
                
                Equipo equipo = new Equipo(linea.trim(), nombreEntrenador, jugadoresEquipo, pais, 0, 0, 0, mediaStats, 0, null, 0);
                equipos.add(equipo);
                indiceEntrenador++;

                // Verificar que el equipo tiene todos los jugadores necesarios
                if (jugadoresEquipo.size() < 22) {
                    System.err.println("Advertencia: El equipo " + linea.trim() + " no tiene todos los jugadores necesarios.");
                    System.err.println("Jugadores totales: " + jugadoresEquipo.size());
                    System.err.println("Defensas: " + defensasAgregados + "/8");
                    System.err.println("Mediocentros: " + mediocentrosAgregados + "/8");
                    System.err.println("Delanteros: " + delanterosAgregados + "/4");
                }
            }
        }
    } catch (FileNotFoundException e) {
        System.err.println("No se encontró el archivo de nombres de equipos: " + rutaFichero);
    } catch (IOException e) {
        System.err.println("Error al leer el archivo: " + e.getMessage());
    }
    
    if (equipos.isEmpty()) {
        System.err.println("No se pudieron crear equipos. Verifica que hay suficientes jugadores y porteros.");
    }
    
    return equipos;  
}

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Equipo equipo = (Equipo) o;
        return golesAfavor == equipo.golesAfavor && golesEnContra == equipo.golesEnContra && diferenciaGoles == equipo.diferenciaGoles && mediaStatsEquipo == equipo.mediaStatsEquipo && puntos == equipo.puntos && Objects.equals(nombre, equipo.nombre) && Objects.deepEquals(jugadores, equipo.jugadores) && Objects.deepEquals(partidos, equipo.partidos) && partidosJugados == equipo.partidosJugados;
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
               "\nPartidos: " + Arrays.toString(partidos) +
               "\nPartidos jugados: " + partidosJugados;
    }
}








