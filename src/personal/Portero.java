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
    private int numeroDeParadas;
    private int estirada;
    private int agarre;
    private int saqueLargo;
    private int reflejos;
    private int posicionamiento;
    private static String rutaFichero = "src/Nombres_Jugadores.txt";
    private static Random random = new Random();

    public Portero(String nombre, int edad, int numeroDeParadas, int estirada, int agarre, int saqueLargo, int reflejos, int posicionamiento) {
        super(nombre, edad);
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


    public static List<Portero> crearPorteros() {
        List<Portero> porteros = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(rutaFichero))) {
            String linea;
            while ((linea = br.readLine()) != null && porteros.size() <20) {
                if (!linea.trim().isEmpty()) {

                    int añosExperiencia = 5 + random.nextInt(10); // Entre 5 y 14 años de experiencia
                    int edad = 30 + random.nextInt(30); // Entre 30 y 59 años
                    Portero portero = new Portero(linea.trim(), edad, añosExperiencia);
                    porteros.add(portero);
                }
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
        return numeroDeParadas == portero.numeroDeParadas && estirada == portero.estirada && agarre == portero.agarre && saqueLargo == portero.saqueLargo && reflejos == portero.reflejos && posicionamiento == portero.posicionamiento;
    }

    @Override
    public String toString() {
        return "Portero{" +
                "numeroDeParadas=" + numeroDeParadas +
                ", estirada=" + estirada +
                ", agarre=" + agarre +
                ", saqueLargo=" + saqueLargo +
                ", reflejos=" + reflejos +
                ", posicionamiento=" + posicionamiento +
                '}';
    }
}

