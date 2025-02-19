package liga;

import personal.Equipo;

import java.util.Arrays;
import java.util.Objects;

public class Partido {
    // Variables declaradas
    private Equipo equipoLocal;
    private Equipo equipoVisitante;
    private int[] resultado;
    private int numeroDeGoles;

    // Constructor
    public Partido(Equipo equipoLocal, Equipo equipoVisitante, int[] resultado, int numeroDeGoles) {
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.resultado = resultado;
        this.numeroDeGoles = numeroDeGoles;
    }

    // Getters y setters
    public Equipo getEquipoLocal() {
        return equipoLocal;
    }

    public void setEquipoLocal(Equipo equipoLocal) {
        this.equipoLocal = equipoLocal;
    }

    public Equipo getEquipoVisitante() {
        return equipoVisitante;
    }

    public void setEquipoVisitante(Equipo equipoVisitante) {
        this.equipoVisitante = equipoVisitante;
    }

    public int[] getResultado() {
        return resultado;
    }

    public void setResultado(int[] resultado) {
        this.resultado = resultado;
    }

    public int getNumeroDeGoles() {
        return numeroDeGoles;
    }

    public void setNumeroDeGoles(int numeroDeGoles) {
        this.numeroDeGoles = numeroDeGoles;
    }


    /* Metodos y funciones */
    void simularPartido(){ 

    }

    // Método equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Partido partido = (Partido) o;
        return numeroDeGoles == partido.numeroDeGoles &&
                Objects.equals(equipoLocal, partido.equipoLocal) &&
                Objects.equals(equipoVisitante, partido.equipoVisitante) &&
                Arrays.equals(resultado, partido.resultado);
    }

    // Método toString
    @Override
    public String toString() {
        return "Partido{" +
                "equipoLocal=" + equipoLocal +
                ", equipoVisitante=" + equipoVisitante +
                ", resultado=" + Arrays.toString(resultado) +
                ", numeroDeGoles=" + numeroDeGoles +
                '}';
    }
}