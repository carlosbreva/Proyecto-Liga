package personal;

import java.util.Objects;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

public class Portero extends Persona {
    private Posicion posicion;
    private int numeroDeParadas;
    private int estirada;
    private int agarre;
    private int saqueLargo;
    private int reflejos;
    private int posicionamiento;
    private int statMediaJugador;
    private static String rutaFichero = "src/Nombres_Jugadores.txt";
    private static Random random = new Random();

    public Portero(String nombre, int edad, Posicion posicion, int numeroDeParadas, int estirada, int agarre, int saqueLargo, int reflejos, int posicionamiento, int statMediaJugador) {
        super(nombre, edad);
        this.posicion = posicion;
        this.numeroDeParadas = numeroDeParadas;
        this.estirada = estirada;
        this.agarre = agarre;
        this.saqueLargo = saqueLargo;
        this.reflejos = reflejos;
        this.posicionamiento = posicionamiento;
        this.statMediaJugador = statMediaJugador;
    }

    public int getNumeroDeParadas() {
        return numeroDeParadas;
    }

    public void setNumeroDeParadas(int numeroDeParadas) {
        this.numeroDeParadas = numeroDeParadas;
    }

    public int getEstirada() {
        return estirada;
    }

    public void setEstirada(int estirada) {
        this.estirada = estirada;
    }

    public int getAgarre() {
        return agarre;
    }

    public void setAgarre(int agarre) {
        this.agarre = agarre;
    }

    public int getSaqueLargo() {
        return saqueLargo;
    }

    public void setSaqueLargo(int saqueLargo) {
        this.saqueLargo = saqueLargo;
    }

    public int getReflejos() {
        return reflejos;
    }

    public void setReflejos(int reflejos) {
        this.reflejos = reflejos;
    }

    public int getPosicionamiento() {
        return posicionamiento;
    }

    public void setPosicionamiento(int posicionamiento) {
        this.posicionamiento = posicionamiento;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    public int getStatMediaJugador() {
        return statMediaJugador;
    }

    public void setStatMediaJugador(int statMediaJugador) {
        this.statMediaJugador = statMediaJugador;
    }
    public static List<Portero> crearPorteros() {
        List<Portero> porteros = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(rutaFichero))) {
            String linea;
            int lineaActual = 1;
            while ((linea = br.readLine()) != null) {
                if (lineaActual >= 21 && lineaActual <= 40) {
                    if (!linea.trim().isEmpty()) {
                        int edad = 18 + random.nextInt(22); // Entre 18 y 39 años
                        int estirada = 60 + random.nextInt(41); // Entre 60-100
                        int agarre = 60 + random.nextInt(41);   // Entre 60-100
                        int saqueLargo = 60 + random.nextInt(41); // Entre 60-100
                        int reflejos = 60 + random.nextInt(41);   // Entre 60-100
                        int posicionamiento = 60 + random.nextInt(41); // Entre 60-100
                        int statMedia = (estirada + agarre + saqueLargo + reflejos + posicionamiento) / 5;
                        Portero portero = new Portero(linea.trim(), edad, Posicion.PORTERO, 0, estirada, agarre, saqueLargo, reflejos, posicionamiento, statMedia);
                        porteros.add(portero);
                    }
                }
                lineaActual++;
            }
        } catch (FileNotFoundException e) {
            System.err.println("No se encontró el archivo de nombres de equipos: " + rutaFichero);
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        
        return porteros;  
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Portero portero = (Portero) o;
        return numeroDeParadas == portero.numeroDeParadas && estirada == portero.estirada && agarre == portero.agarre && saqueLargo == portero.saqueLargo && reflejos == portero.reflejos && posicionamiento == portero.posicionamiento && statMediaJugador == portero.statMediaJugador;
    }

    @Override
    public String toString() {
        return  "Jugador: " + super.toString() + " posicion=" + posicion + " numeroDeParadas=" + numeroDeParadas +
                ", estirada=" + estirada +
                ", agarre=" + agarre +
                ", saqueLargo=" + saqueLargo +
                ", reflejos=" + reflejos +
                ", posicionamiento=" + posicionamiento +
                ", statMediaJugador=" + statMediaJugador;
    }
}

