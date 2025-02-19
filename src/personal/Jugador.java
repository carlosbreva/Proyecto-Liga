package personal;

import java.util.Objects;

public class Jugador extends Persona {
    /* Variables*/
    private Posicion posicion;
    private int statMedia;
    private int tarjetasAmarillas;
    private int tarjetasRojas;
    private int golesAnotados;
    private boolean piernaBuena;
    private int velocidad;



    /* Constructor */
    public Jugador(String nombre, int edad, Posicion posicion, int statMedia, int tarjetasAmarillas, int tarjetasRojas, int golesAnotados, boolean piernaBuena, int velocidad) {
        super(edad, nombre);
        this.posicion = posicion;
        this.statMedia = statMedia;
        this.tarjetasAmarillas = tarjetasAmarillas;
        this.tarjetasRojas = tarjetasRojas;
        this.golesAnotados = golesAnotados;
        this.piernaBuena = piernaBuena;
        this.velocidad = velocidad;
    }

    /* Getters y Setters */
    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    public int getStatMedia() {
        return statMedia;
    }

    public void setStatMedia(int statMedia) {
        this.statMedia = statMedia;
    }

    public int getTarjetasAmarillas() {
        return tarjetasAmarillas;
    }

    public void setTarjetasAmarillas(int tarjetasAmarillas) {
        this.tarjetasAmarillas = tarjetasAmarillas;
    }

    public int getTarjetasRojas() {
        return tarjetasRojas;
    }

    public void setTarjetasRojas(int tarjetasRojas) {
        this.tarjetasRojas = tarjetasRojas;
    }

    public int getGolesAnotados() {
        return golesAnotados;
    }

    public void setGolesAnotados(int golesAnotados) {
        this.golesAnotados = golesAnotados;
    }

    public boolean isPiernaBuena() {
        return piernaBuena;
    }

    public void setPiernaBuena(boolean piernaBuena) {
        this.piernaBuena = piernaBuena;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }


    /* Equals y ToString */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Jugador jugador = (Jugador) o;
        return statMedia == jugador.statMedia && tarjetasAmarillas == jugador.tarjetasAmarillas && tarjetasRojas == jugador.tarjetasRojas && posicion == jugador.posicion && golesAnotados == jugador.golesAnotados && piernaBuena == jugador.piernaBuena && velocidad == jugador.velocidad;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "posicion=" + posicion +
                ", statMedia=" + statMedia +
                ", tarjetasAmarillas=" + tarjetasAmarillas +
                ", tarjetasRojas=" + tarjetasRojas + 
                ", golesAnotados=" + golesAnotados +
                ", piernaBuena=" + piernaBuena +
                ", velocidad=" + velocidad +
                '}';
    }
}
