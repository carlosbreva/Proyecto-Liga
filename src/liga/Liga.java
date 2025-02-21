package liga;
import personal.Equipo;
import personal.Persona;

import java.util.Arrays;
import java.util.Objects;

public class Liga {

    /* Variables */
    private String nombre;
    private Equipo[] equipos;
    private Jornada[] jornadas;
    private Persona[] jugadores;

//Constructor//
 public Liga(String nombre){
    this.nombre = nombre;

 }





    /* Getters y setters  */

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Equipo[] getEquipos() {
        return equipos;
    }

    public void setEquipos(Equipo[] equipos) {
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


    public  void jugarJornada(){

        



    }
    



    public void verClasificacion(Equipo[]equipo){

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
                ", equipos=" + Arrays.toString(equipos) +
                ", jornadas=" + Arrays.toString(jornadas) +
                ", jugadores=" + Arrays.toString(jugadores) +
                '}';
    }
}
