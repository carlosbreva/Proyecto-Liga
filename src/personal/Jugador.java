package personal;

import java.util.Objects;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

public class Jugador extends Persona {

    private Posicion posicion;
    private int statMedia;
    private int tarjetasAmarillas;
    private int tarjetasRojas;
    private int golesAnotados;
    private boolean piernaBuena;
    private int velocidad;
    private static String rutaFichero = "src/Nombres_Jugadores.txt";
    private static Random random = new Random();
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

    public static List<Jugador> crearJugadores() {
        List<Jugador> jugadores = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(rutaFichero))) {
            String linea;
            while ((linea = br.readLine()) != null && jugadores.size() <20) {
                if (!linea.trim().isEmpty()) {

                    int añosExperiencia = 5 + random.nextInt(10); // Entre 5 y 14 años de experiencia
                    int edad = 30 + random.nextInt(30); // Entre 30 y 59 años
                    Jugador jugador = new Jugador(linea.trim(), edad, añosExperiencia);
                    jugadores.add(jugador);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("No se encontró el archivo de nombres de equipos: " + rutaFichero);
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        
        return jugadores;  
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
