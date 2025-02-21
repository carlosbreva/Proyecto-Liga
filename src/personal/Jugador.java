package personal;

import java.util.Objects;

public class Jugador extends Persona {

    private Posicion posicion;
    private int statMedia;
    private int tarjetasAmarillas;
    private int tarjetasRojas;
    private int golesAnotados;
    private boolean piernaBuena;
    private int velocidad;

    //Estadísticas de Jugador de Campo
    private int ritmo;
    private int pase;
    private int tiros;
    private int defensa;
    private int regate;
    private int fisico;



    public Jugador(String nombre, int edad, Posicion posicion, int statMedia, int tarjetasAmarillas, int tarjetasRojas, boolean piernaBuena, int golesAnotados, int velocidad, int ritmo, int pase, int tiros, int defensa, int regate, int fisico) {
        super(nombre, edad);
        this.posicion = posicion;
        this.statMedia = statMedia;
        this.tarjetasAmarillas = tarjetasAmarillas;
        this.tarjetasRojas = tarjetasRojas;
        this.golesAnotados = golesAnotados;
        this.piernaBuena = piernaBuena;
        this.velocidad = velocidad;
        this.ritmo = ritmo;
        this.pase = pase;
        this.tiros = tiros;
        this.defensa = defensa;
        this.regate = regate;
        this.fisico = fisico;
    }


    public int getRitmo() {
    	return this.ritmo;
    }
    public void setRitmo(int ritmo) {
    	this.ritmo = ritmo;
    }


    public int getPase() {
    	return this.pase;
    }
    public void setPase(int pase) {
    	this.pase = pase;
    }


    public int getTiros() {
    	return this.tiros;
    }
    public void setTiros(int tiros) {
    	this.tiros = tiros;
    }


    public int getDefensa() {
    	return this.defensa;
    }
    public void setDefensa(int defensa) {
    	this.defensa = defensa;
    }


    public int getRegate() {
    	return this.regate;
    }
    public void setRegate(int regate) {
    	this.regate = regate;
    }


    public int getFisico() {
    	return this.fisico;
    }
    public void setFisico(int fisico) {
    	this.fisico = fisico;
    }

    

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Jugador that = (Jugador) o;
        return ritmo == that.ritmo && pase == that.pase && tiros == that.tiros && defensa == that.defensa && regate == that.regate && fisico == that.fisico;
    }

    @Override
    public String toString() {
        return "JugadorDeCampo{" +
                "ritmo=" + ritmo +
                ", pase=" + pase +
                ", tiros=" + tiros +
                ", defensa=" + defensa +
                ", regate=" + regate +
                ", fisico=" + fisico +
                '}';
    }
}
