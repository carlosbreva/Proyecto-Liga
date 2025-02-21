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
    private int statMediaJugador;
    private int tarjetasAmarillas;
    private int tarjetasRojas;
    private int golesAnotados;
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



    public Jugador(String nombre, int edad, Posicion posicion, int tarjetasAmarillas, int tarjetasRojas, int golesAnotados, int velocidad, int ritmo, int pase, int tiros, int defensa, int regate, int fisico,int statMediaJugador) {
        super(nombre, edad);
        this.posicion = posicion;
        this.statMediaJugador = statMediaJugador;
        this.tarjetasAmarillas = tarjetasAmarillas;
        this.tarjetasRojas = tarjetasRojas;
        this.golesAnotados = golesAnotados;
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

    public int getStatMediaJugador() {
        return statMediaJugador;
    }

    public void setStatMediaJugador(int statMediaJugador) {
        this.statMediaJugador = statMediaJugador;
    }

    public static List<Jugador> crearJugadores() {
        List<Jugador> jugadores = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(rutaFichero))) {
            String linea;
            int lineaActual = 1;
            while ((linea = br.readLine()) != null) {
                // Saltamos las primeras 40 líneas que son para entrenadores (1-20) y porteros (21-40)
                if (lineaActual > 40) {
                    if (!linea.trim().isEmpty()) {
                        Posicion posicion = Posicion.values()[random.nextInt(Posicion.values().length - 1)];
                        int edad = 18 + random.nextInt(22); // Entre 18 y 39 años
                        int ritmo = 60 + random.nextInt(41); // Entre 60-100
                        int pase = 60 + random.nextInt(41); // Entre 60-100
                        int tiros = 60 + random.nextInt(41); // Entre 60-100
                        int defensa = 60 + random.nextInt(41); // Entre 60-100
                        int regate = 60 + random.nextInt(41); // Entre 60-100
                        int fisico = 60 + random.nextInt(41); // Entre 60-100
                        int velocidad = 60 + random.nextInt(41); // Entre 60-100
                        int statMediaJugador = (ritmo + pase + tiros + defensa + regate + fisico + velocidad) / 7;
                        Jugador jugador = new Jugador(linea.trim(), edad, posicion, 0, 0, 0, velocidad, ritmo, pase, tiros, defensa, regate, fisico, statMediaJugador);
                        jugadores.add(jugador);
                    }
                }
                lineaActual++;
            }
        } catch (FileNotFoundException e) {
            System.err.println("No se encontró el archivo de nombres: " + rutaFichero);
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
        return ritmo == that.ritmo && pase == that.pase && tiros == that.tiros && defensa == that.defensa && regate == that.regate && fisico == that.fisico && statMediaJugador == that.statMediaJugador;
    }


    @Override
    public String toString() {
        return  "Jugador: " + super.toString() + " posicion=" + posicion + ", tarjetasAmarillas=" + tarjetasAmarillas
                + ", tarjetasRojas=" + tarjetasRojas + ", golesAnotados=" + golesAnotados + ", velocidad=" + velocidad
                + ", ritmo=" + ritmo + ", pase=" + pase + ", tiros=" + tiros + ", defensa=" + defensa + ", regate="
                + regate + ", fisico=" + fisico + ", statMediaJugador=" + statMediaJugador;
    }


}
