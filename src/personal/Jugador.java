package personal;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

public class Jugador extends Persona {

    /* Variables */
    private Posicion posicion;
    private int statMediaJugador;
    private int tarjetasAmarillas;
    private int tarjetasRojas;
    private int golesAnotados;
    private int velocidad;
    private static Random random = new Random();

    /* Estadísticas exclusivas de Jugador de Campo */
    private int ritmo;
    private int pase;
    private int tiros;
    private int defensa;
    private int regate;
    private int fisico;


    /* Constructor */
    public Jugador(String nombre, int edad, Posicion posicion, int tarjetasAmarillas, int tarjetasRojas, int golesAnotados, int velocidad, int ritmo, int pase, int tiros, int defensa, int regate, int fisico,int statMediaJugador, Paises pais) {
        super(nombre, edad, pais);
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

    /* Getters y Setters */
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

    public int getTarjetasAmarillas() {
        return this.tarjetasAmarillas;
    }
    public void setTarjetasAmarillas(int tarjetasAmarillas) {
        this.tarjetasAmarillas = tarjetasAmarillas;
    }

    public int getTarjetasRojas() {
        return this.tarjetasRojas;
    }
    public void setTarjetasRojas(int tarjetasRojas) {
        this.tarjetasRojas = tarjetasRojas;
    }

    public int getGolesAnotados() {
        return this.golesAnotados;
    }
    public void setGolesAnotados(int golesAnotados) {
        this.golesAnotados = golesAnotados;
    }

    public int getVelocidad() {
        return this.velocidad;
    }
    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }


    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }
    
    public int getStatMediaJugador() {
        return this.statMediaJugador;
    }
    public void setStatMediaJugador(int statMediaJugador) {
        this.statMediaJugador = statMediaJugador;
    }


    /* Métodos */
    public static List<Jugador> crearJugadores(String rutaFichero, Paises pais) {
        List<Jugador> jugadores = new ArrayList<>();
        List<String> nombresJugadores = new ArrayList<>();
        
        // Primero leemos todos los nombres
        try (BufferedReader br = new BufferedReader(new FileReader(rutaFichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    nombresJugadores.add(linea.trim());
                }
            }
        } catch (FileNotFoundException e) {
            //si no se encuentra el archivo de nombres
            System.err.println("No se encontró el archivo de nombres: " + rutaFichero);
            return jugadores;
        } catch (IOException e) {
            //si hay un error al leer el archivo
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return jugadores;
        }

        // Mezclar la lista de nombres aleatoriamente
        for (int i = nombresJugadores.size() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            String temp = nombresJugadores.get(i);
            nombresJugadores.set(i, nombresJugadores.get(j));
            nombresJugadores.set(j, temp);
        }

        //contadores de jugadores necesarios
        int defensasNecesarios = 8;
        int mediocentrosNecesarios = 8;
        int delanterosNecesarios = 4;
        int jugadoresCreados = 0;

        // Crear jugadores con posiciones aleatorias
        while (jugadoresCreados < nombresJugadores.size() && 
               (defensasNecesarios > 0 || mediocentrosNecesarios > 0 || delanterosNecesarios > 0)) {
            
            // Determinar qué posiciones aún necesitan jugadores
            List<Posicion> posicionesDisponibles = new ArrayList<>();
            if (defensasNecesarios > 0) posicionesDisponibles.add(Posicion.DEFENSA);
            if (mediocentrosNecesarios > 0) posicionesDisponibles.add(Posicion.MEDIOCENTRO);
            if (delanterosNecesarios > 0) posicionesDisponibles.add(Posicion.DELANTERO);

            // Seleccionar una posición aleatoria de las disponibles
            Posicion posicion = posicionesDisponibles.get(random.nextInt(posicionesDisponibles.size()));

            // Actualizar contadores
            switch (posicion) {
                case DEFENSA:
                    defensasNecesarios--;
                    break;
                case MEDIOCENTRO:
                    mediocentrosNecesarios--;
                    break;
                case DELANTERO:
                    delanterosNecesarios--;
                    break;
                case PORTERO:
                    // Los porteros se manejan en una clase separada
                    break;
                default:
                    //si no es ninguna de las posiciones anteriores
                    break;
            }

            // Crear jugador con estadísticas aleatorias
            int edad = 18 + random.nextInt(22); // Entre 18 y 39 años
            int ritmo = 40 + random.nextInt(61); // Entre 40-100
            int pase = 40 + random.nextInt(61); // Entre 40-100
            int tiros = 40 + random.nextInt(61); // Entre 40-100
            int defensa = 40 + random.nextInt(61); // Entre 40-100
            int regate = 40 + random.nextInt(61); // Entre 40-100
            int fisico = 40 + random.nextInt(61); // Entre 40-100
            int velocidad = 40 + random.nextInt(61); // Entre 40-100
            //calcula la media de las estadísticas
            int statMediaJugador = (ritmo + pase + tiros + defensa + regate + fisico + velocidad) / 7;

            //crea el jugador
            Jugador jugador = new Jugador(
                nombresJugadores.get(jugadoresCreados),
                edad,
                posicion,
                0, 0, 0, // tarjetas y goles
                velocidad,
                ritmo,
                pase,
                tiros,
                defensa,
                regate,
                fisico,
                statMediaJugador,
                pais
            );
            //añade el jugador a la lista
            jugadores.add(jugador);
            jugadoresCreados++;
        }

        // Verificar que se crearon todos los jugadores necesarios
        System.out.println("\nDistribución de jugadores creados:");
        System.out.println("Defensas: " + (8 - defensasNecesarios) + "/8");
        System.out.println("Mediocentros: " + (8 - mediocentrosNecesarios) + "/8");
        System.out.println("Delanteros: " + (4 - delanterosNecesarios) + "/4");
        //devuelve la lista de jugadores
        return jugadores;  
    }

    /* Equals y toString */
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
