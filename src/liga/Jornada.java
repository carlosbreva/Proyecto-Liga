package liga;

import java.util.Arrays;
import java.util.Objects;
import personal.Equipo;
import liga.Partido;
import java.util.List;
import personal.Jugador;

public class Jornada {
    /* Variables generales*/
    private int numeroJornada;
    private Partido[] partidos;

    /* Constructor*/
    public Jornada(int numeroJornada, Partido[] partidos) {
        this.numeroJornada = numeroJornada;
        this.partidos = partidos;
    }

    /* Getters y Setters*/
    public int getNumeroJornada() {
        return numeroJornada;
    }

    public void setNumeroJornada(int numeroJornada) {
        this.numeroJornada = numeroJornada;
    }

    public Partido[] getPartidos() {
        return partidos;
    }

    public void setPartidos(Partido[] partidos) {
        this.partidos = partidos;
    }

    /*Metodos y funciones  */

public static void emparejamientos(List<Equipo> equipos){
    for (int i = 0 ; i < equipos.size();i++){
           for ( int j = i + 1 ; j < equipos.size(); j++){
            Partido partido = new Partido();
            partido.simularPartido(equipos.get(i), equipos.get(j), equipos.get(i).getJugadores());// ida
            partido.simularPartido(equipos.get(j),equipos.get(i),equipos.get(j).getJugadores());// vuelta


            /* lo que jugamos  */


            
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
                ", partidos=" + Arrays.toString(partidos) +
                '}';
    }
}

