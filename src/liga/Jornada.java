package liga;

import java.util.Arrays;
import java.util.Objects;

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

public void jugarPartidos(){
    for (int i = 0; i < partidos.length; i++) {
        partidos[i].simularPartido();
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

