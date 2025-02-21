package personal;

import liga.Partido;

import java.util.Arrays;
import java.util.Objects;
import java.util.ArrayList;
import java.util.List;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;


public class Equipo {
    private String nombre;
    private String nombreEntrenador;
    private Persona [] jugadores= new Persona[21]; 
    private int golesAfavor;
    private int golesEnContra;
    private int diferenciaGoles;
    private int mediaStats;
    private int puntos;
    private Partido [] partidos;

    public Equipo(String nombre, Persona[] jugadores, int golesAfavor, int golesEnContra, int diferenciaGoles, int mediaStats, int puntos, Partido[] partidos) {
        this.nombre = nombre;
        this.jugadores = jugadores;
        this.golesAfavor = golesAfavor;
        this.golesEnContra = golesEnContra;
        this.diferenciaGoles = diferenciaGoles;
        this.mediaStats = mediaStats;
        this.puntos = puntos;
        this.partidos = partidos;
    }

    public Equipo(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Persona[] getJugadores() {
        return jugadores;
    }

    public void setJugadores(Persona[] jugadores) {
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

    public int getMediaStats() {
        return mediaStats;
    }

    public void setMediaStats(int mediaStats) {
        this.mediaStats = mediaStats;
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

   // Método asignar nombres a equipos desde el fichero
   public static List<Equipo> crearEquiposDesdeFichero(String rutaFichero) {
    List<Equipo> equipos = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(rutaFichero))) {
        String linea;
        while ((linea = br.readLine()) != null) {
            Equipo equipo = new Equipo(linea);
            equipos.add(equipo);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return equipos;
}



    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Equipo equipo = (Equipo) o;
        return golesAfavor == equipo.golesAfavor && golesEnContra == equipo.golesEnContra && diferenciaGoles == equipo.diferenciaGoles && mediaStats == equipo.mediaStats && puntos == equipo.puntos && Objects.equals(nombre, equipo.nombre) && Objects.deepEquals(jugadores, equipo.jugadores) && Objects.deepEquals(partidos, equipo.partidos);
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "nombre='" + nombre + '\'' +
                ", jugadores=" + Arrays.toString(jugadores) +
                ", golesAfavor=" + golesAfavor +
                ", golesEnContra=" + golesEnContra +
                ", diferenciaGoles=" + diferenciaGoles +
                ", mediaStats=" + mediaStats +
                ", puntos=" + puntos +
                ", partidos=" + Arrays.toString(partidos) +
                '}';
    }
}





