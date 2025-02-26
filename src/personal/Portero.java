package personal;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

public class Portero extends Jugador {
    private int numeroDeParadas;
    private int estirada;
    private int agarre;
    private int saqueLargo;
    private int reflejos;
    private int posicionamiento;
    private static Random random = new Random();

    public Portero(String nombre, int edad, Posicion posicion, int numeroDeParadas, int estirada, int agarre, int saqueLargo, int reflejos, int posicionamiento, int statMediaJugador, Paises pais) {
        super(nombre, edad, posicion, 0, 0, 0, reflejos, estirada, agarre, saqueLargo, posicionamiento, reflejos, agarre, statMediaJugador, pais);
        this.numeroDeParadas = numeroDeParadas;
        this.estirada = estirada;
        this.agarre = agarre;
        this.saqueLargo = saqueLargo;
        this.reflejos = reflejos;
        this.posicionamiento = posicionamiento;
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


    public static List<Portero> crearPorteros(String rutaFichero, Paises pais) {
        List<Portero> porteros = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(rutaFichero))) {
            String linea;
            int lineaActual = 1;
            
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    int edad = 18 + random.nextInt(22); // Entre 18 y 39 años
                    int estirada = 40 + random.nextInt(61); // Entre 40-100
                    int agarre = 40 + random.nextInt(61);   // Entre 40-100
                    int saqueLargo = 40 + random.nextInt(61); // Entre 40-100
                    int reflejos = 40 + random.nextInt(61);   // Entre 40-100
                    int posicionamiento = 40 + random.nextInt(61); // Entre 40-100
                    int statMedia = (estirada + agarre + saqueLargo + reflejos + posicionamiento) / 5;
                    
                    Portero portero = new Portero(
                        linea.trim(),
                        edad,
                        Posicion.PORTERO,
                        0, // numeroDeParadas inicial
                        estirada,
                        agarre,
                        saqueLargo,
                        reflejos,
                        posicionamiento,
                        statMedia,
                        pais
                    );
                    porteros.add(portero);
                } else {
                    System.out.println("La linea " + lineaActual + " no es válida");
                }
                lineaActual++;
            }
        } catch (FileNotFoundException e) {
            System.err.println("No se encontró el archivo de nombres: " + rutaFichero);
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
        return numeroDeParadas == portero.numeroDeParadas && estirada == portero.estirada && agarre == portero.agarre && saqueLargo == portero.saqueLargo && reflejos == portero.reflejos && posicionamiento == portero.posicionamiento;
    }

    @Override
    public String toString() {
        return  "Jugador: " + super.toString() + " numeroDeParadas=" + numeroDeParadas +
                ", estirada=" + estirada +
                ", agarre=" + agarre +
                ", saqueLargo=" + saqueLargo +
                ", reflejos=" + reflejos +
                ", posicionamiento=" + posicionamiento;
    }
}

