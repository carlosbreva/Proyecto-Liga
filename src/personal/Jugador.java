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
            System.err.println("No se encontró el archivo de nombres: " + rutaFichero);
            return jugadores;
        } catch (IOException e) {
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

        // Determinar el número de equipos según el país
        int numEquipos;
        switch (pais) {
            case ALEMANIA:
                numEquipos = 18; // Bundesliga
                break;
            case ESPAÑA:
                numEquipos = 20; // LaLiga
                break;
            case ITALIA:
                numEquipos = 20; // Serie A
                break;
            case FRANCIA:
                numEquipos = 18; // Ligue 1
                break;
            case INGLATERRA:
                numEquipos = 20; // Premier League
                break;
            case PORTUGAL:
                numEquipos = 18; // Primeira Liga
                break;
            case HOLANDA:
                numEquipos = 18; // Eredivisie
                break;
            case BELGICA:
                numEquipos = 16; // Belgian Pro League
                break;
            default:
                numEquipos = 18;
        }

        // Calcular jugadores necesarios según el número de equipos
        int defensasNecesarios = numEquipos * 8;  // 8 defensas por equipo
        int mediocentrosNecesarios = numEquipos * 8;  // 8 mediocentros por equipo
        int delanterosNecesarios = numEquipos * 4;  // 4 delanteros por equipo
        int jugadoresCreados = 0;

        System.out.println("\nCreando jugadores para " + numEquipos + " equipos de " + pais + ":");
        System.out.println("Necesitamos: " + defensasNecesarios + " defensas, " + 
                          mediocentrosNecesarios + " mediocentros, " + 
                          delanterosNecesarios + " delanteros");

        // Crear jugadores con posiciones aleatorias
        while (jugadoresCreados < nombresJugadores.size() && 
               (defensasNecesarios > 0 || mediocentrosNecesarios > 0 || delanterosNecesarios > 0)) {
            
            // Determinar qué posiciones aún necesitan jugadores
            List<Posicion> posicionesDisponibles = new ArrayList<>();
            if (defensasNecesarios > 0) posicionesDisponibles.add(Posicion.DEFENSA);
            if (mediocentrosNecesarios > 0) posicionesDisponibles.add(Posicion.MEDIOCENTRO);
            if (delanterosNecesarios > 0) posicionesDisponibles.add(Posicion.DELANTERO);

            if (posicionesDisponibles.isEmpty()) break;

            // Seleccionar una posición aleatoria de las disponibles
            Posicion posicion = posicionesDisponibles.get(random.nextInt(posicionesDisponibles.size()));

            // Crear jugador con estadísticas aleatorias
            String nombre = nombresJugadores.get(jugadoresCreados);
            int edad = random.nextInt(23) + 18; // Edad entre 18 y 40
            int velocidad = random.nextInt(41) + 60; // Velocidad entre 60 y 100
            int ritmo = random.nextInt(41) + 60; // Ritmo entre 60 y 100
            int pase = random.nextInt(41) + 60; // Pase entre 60 y 100
            int tiros = random.nextInt(41) + 60; // Tiros entre 60 y 100
            int defensa = random.nextInt(41) + 60; // Defensa entre 60 y 100
            int regate = random.nextInt(41) + 60; // Regate entre 60 y 100
            int fisico = random.nextInt(41) + 60; // Físico entre 60 y 100
            int statMediaJugador = (ritmo + pase + tiros + defensa + regate + fisico) / 6;

            Jugador jugador = new Jugador(nombre, edad, posicion, 0, 0, 0, velocidad, ritmo, pase, tiros, defensa, regate, fisico, statMediaJugador, pais);
            jugadores.add(jugador);
            jugadoresCreados++;

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
            }
        }

        // Mostrar distribución de jugadores creados
        System.out.println("\nDistribución de jugadores creados:");
        System.out.println("Defensas: " + (144 - defensasNecesarios) + "/144");
        System.out.println("Mediocentros: " + (144 - mediocentrosNecesarios) + "/144");
        System.out.println("Delanteros: " + (72 - delanterosNecesarios) + "/72");

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
