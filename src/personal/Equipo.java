package personal;

import java.util.Arrays;
import java.util.Objects;

import partido.Partido;

import java.util.ArrayList;
import java.util.List;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;


public class Equipo {
    /* Variables */
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

    /* Constructor */
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


    /* Getters y Setters */

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

    /* Métodos */
    public static List<Equipo> crearEquipos(String rutaFichero, List<Entrenador> entrenadores, List<Portero> porteros, List<Jugador> jugadores, Paises pais) {
        List<Equipo> equipos = new ArrayList<>();
        int indiceEntrenador = 0;
        
        // Crear copias de las listas para no modificar las originales
        List<Portero> porterosDisponibles = new ArrayList<>(porteros);
        List<Jugador> jugadoresDisponibles = new ArrayList<>(jugadores);

        try (BufferedReader br = new BufferedReader(new FileReader(rutaFichero))) {
            String linea;
            while ((linea = br.readLine()) != null && indiceEntrenador < entrenadores.size()) { 
                if (!linea.trim().isEmpty()) {
                    String nombreEntrenador = entrenadores.get(indiceEntrenador).getNombre();
                    List<Jugador> jugadoresEquipo = new ArrayList<>();
                    
                    // Agregar 2 porteros
                    for (int i = 0; i < 2 && !porterosDisponibles.isEmpty(); i++) {
                        Portero portero = porterosDisponibles.remove(0);
                        jugadoresEquipo.add(portero);
                    }
                    
                    // Agregar 8 defensas
                    int defensasAgregados = 0;
                    for (int i = 0; i < jugadoresDisponibles.size() && defensasAgregados < 8; i++) {
                        Jugador j = jugadoresDisponibles.get(i);
                        if (j.getPosicion() == Posicion.DEFENSA) {
                            jugadoresEquipo.add(j);
                            jugadoresDisponibles.remove(i);
                            i--; // Ajustar índice después de remover
                            defensasAgregados++;
                        }
                    }
                    
                    // Agregar 8 mediocentros
                    int mediocentrosAgregados = 0;
                    for (int i = 0; i < jugadoresDisponibles.size() && mediocentrosAgregados < 8; i++) {
                        Jugador j = jugadoresDisponibles.get(i);
                        if (j.getPosicion() == Posicion.MEDIOCENTRO) {
                            jugadoresEquipo.add(j);
                            jugadoresDisponibles.remove(i);
                            i--; // Ajustar índice después de remover
                            mediocentrosAgregados++;
                        }
                    }
                    
                    // Agregar 4 delanteros
                    int delanterosAgregados = 0;
                    for (int i = 0; i < jugadoresDisponibles.size() && delanterosAgregados < 4; i++) {
                        Jugador j = jugadoresDisponibles.get(i);
                        if (j.getPosicion() == Posicion.DELANTERO) {
                            jugadoresEquipo.add(j);
                            jugadoresDisponibles.remove(i);
                            i--; // Ajustar índice después de remover
                            delanterosAgregados++;
                        }
                    }
                    
                    // Verificar que el equipo tiene suficientes jugadores
                    if (jugadoresEquipo.size() < 22) {
                        System.out.println("Advertencia: El equipo " + linea.trim() + " no tiene suficientes jugadores (" + jugadoresEquipo.size() + "/22)");
                    }
                    
                    // Calcular la media de estadísticas del equipo
                    int sumaStats = 0;
                    for (Jugador j : jugadoresEquipo) {
                        int mediaJugador = (j.getRitmo() + j.getPase() + j.getTiros() + 
                                          j.getDefensa() + j.getRegate() + j.getFisico()) / 6;
                        sumaStats += mediaJugador;
                    }
                    int mediaStats = jugadoresEquipo.isEmpty() ? 0 : sumaStats / jugadoresEquipo.size();
                    
                    Equipo equipo = new Equipo(linea.trim(), nombreEntrenador, jugadoresEquipo, pais, 0, 0, 0, mediaStats, 0, null, 0);
                    equipos.add(equipo);
                    indiceEntrenador++;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: No se encontró el archivo " + rutaFichero);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo " + rutaFichero);
        }
        
        return equipos;
    }

    /* Equals y toString */
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








