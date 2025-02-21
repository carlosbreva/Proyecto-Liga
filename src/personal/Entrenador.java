package personal;

import java.util.Objects;
import java.util.ArrayList;
import java.util.Random;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.List;

public class Entrenador extends Persona {

    private int añosDeExperiencia;
    private static String rutaFichero = "src/Nombres_Jugadores.txt";
    private static Random random = new Random();

    
    

    public Entrenador(String nombre, int edad, int añosDeExperiencia) {
        super(nombre, edad);
        this.añosDeExperiencia = añosDeExperiencia;
    }

    public int getAñosDeExperiencia() {
        return añosDeExperiencia;
    }
    public void setAñosDeExperiencia(int añosDeExperiencia) {
        this.añosDeExperiencia = añosDeExperiencia;
    }

    public String getRutaFichero() {
        return rutaFichero;
    }

    public void setRutaFichero(String rutaFichero) {
        this.rutaFichero = rutaFichero;
    }

    // métodos 
    public static List<Entrenador> crearEntrenadores() {
        List<Entrenador> entrenadores = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(rutaFichero))) {
            String linea;
            while ((linea = br.readLine()) != null && entrenadores.size() <20) {
                if (!linea.trim().isEmpty()) {
                    int añosExperiencia = 5 + random.nextInt(10); // Entre 5 y 14 años de experiencia
                    int edad = 30 + random.nextInt(30); // Entre 30 y 59 años
                    Entrenador entrenador = new Entrenador(linea.trim(), edad, añosExperiencia);
                    entrenadores.add(entrenador);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("No se encontró el archivo de nombres de equipos: " + rutaFichero);
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        
        return entrenadores;  
    }

    @Override
    public String toString() {
        return  "Entrenador: " + super.toString() + " añosDeExperiencia= " + añosDeExperiencia;
    }
    
}

