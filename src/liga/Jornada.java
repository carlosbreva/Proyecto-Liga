package liga;

import java.util.Arrays;
import java.util.Objects;
import personal.Equipo;
import java.util.List;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import partido.Partido;

public class Jornada {
    /* Variables generales*/
    private int numeroJornada;
    private List<Partido> partidos;
    private Liga liga;

    /* Constructor*/
    public Jornada(int numeroJornada, Liga liga) {
        this.numeroJornada = numeroJornada;
        this.partidos = new ArrayList<>();
        this.liga = liga;
    }

    /* Getters y Setters*/
    public int getNumeroJornada() {
        return numeroJornada;
    }

    public void setNumeroJornada(int numeroJornada) {
        this.numeroJornada = numeroJornada;
    }

    public List<Partido> getPartidos() {
        return partidos;
    }

    public void setPartidos(List<Partido> partidos) {
        this.partidos = partidos;
    }

    /*Metodos y funciones  */
    public  String leerFichero(int i) {
        String rutaFichero = "src/Documentos_Ligas/" + liga.getNombre() + "/Calendario_Partidos_" + liga.getNombre() + ".txt";
        try (BufferedReader br = new BufferedReader(new FileReader(rutaFichero))) {
            String linea;
            int contador = 0;
            while ((linea = br.readLine()) != null && contador <= i) {
                if (contador == i) {
                    System.out.println("Equipo leído: " + linea);
                    return linea;
                }
                contador++;
            }
            return "";
        } catch (FileNotFoundException e) {
            System.out.println("Error: Archivo no encontrado");
            return "";
        } catch (IOException e) {
            System.out.println("Error de lectura");
            return "";
        }
    }

    public void jugarPartidos(List<Equipo> equipos) {
        if (equipos == null || equipos.size() < 2) {
            System.out.println("No hay suficientes equipos para jugar los partidos.");
            return;
        }

        int numeroPartidosenJornada = equipos.size()/2;
        int offset = (numeroJornada - 1) * numeroPartidosenJornada * 2;
        
        for (int i = 0; i < numeroPartidosenJornada; i++) {
            System.out.println("\nPartido " + (i+1) + ":");
            String nombreEquipoLocal = leerFichero(offset + i*2);
            String nombreEquipoVisitante = leerFichero(offset + i*2 + 1);
            
            // Buscar los equipos por nombre
            Equipo equipoLocal = null;
            Equipo equipoVisitante = null;
            
            for (Equipo e : equipos) {
                if (e.getNombre().equals(nombreEquipoLocal)) {
                    equipoLocal = e;
                }
                if (e.getNombre().equals(nombreEquipoVisitante)) {
                    equipoVisitante = e;
                }
            }
            
            if (equipoLocal != null && equipoVisitante != null) {
                Partido partido = new Partido(equipoLocal, equipoVisitante, new int[]{0, 0}, 0);
                partido.simularPartido(equipoLocal, equipoVisitante, liga);
                partidos.add(partido);
            } else {
                System.out.println("Error: No se encontraron los equipos " + nombreEquipoLocal + " y/o " + nombreEquipoVisitante);
            }
        }
    }

    /* Equals y ToString*/
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Jornada jornada = (Jornada) o;
        return numeroJornada == jornada.numeroJornada && Objects.deepEquals(partidos, jornada.partidos);
    }

    @Override
    public String toString() {
        return "Jornada{" +
                "numeroJornada=" + numeroJornada +
                ", partidos=" + Arrays.toString(partidos.toArray()) +
                '}';
    }
}


