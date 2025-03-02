package personal;

import java.util.ArrayList;
import java.util.Random;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.List;

public class Entrenador extends Persona {

     /* Variables*/
    private int añosDeExperiencia;
    private static Random random = new Random();


    
    /* Constructor */
    public Entrenador(String nombre, int edad, int añosDeExperiencia, Paises pais) {
        super(nombre, edad, pais);
        this.añosDeExperiencia = añosDeExperiencia;
    }


    /* Getters y Setters */
    public int getAñosDeExperiencia() {
        return añosDeExperiencia;
    }
    public void setAñosDeExperiencia(int añosDeExperiencia) {
        this.añosDeExperiencia = añosDeExperiencia;
    }






    /* Métodos */
    public static List<Entrenador> crearEntrenadores(String rutaFichero, Paises pais) {
        List<Entrenador> entrenadores = new ArrayList<>();
        //lee el archivo de entrenadores
        try (BufferedReader br = new BufferedReader(new FileReader(rutaFichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    //genera un entrenador con valores aleatorios
                    int añosExperiencia = 5 + random.nextInt(10); // Entre 5 y 14 años de experiencia
                    int edad = 30 + random.nextInt(30); // Entre 30 y 59 años
                    Entrenador entrenador = new Entrenador(linea.trim(), edad, añosExperiencia, pais);
                    //añade el entrenador a la lista
                    entrenadores.add(entrenador);
                }
            }
        } catch (FileNotFoundException e) {
            //si no se encuentra el archivo de entrenadores
            System.err.println("No se encontró el archivo de nombres de equipos: " + rutaFichero);
        } catch (IOException e) {
            //si hay un error al leer el archivo
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        
        return entrenadores;  
    }

    
   
  /* Equals y toString */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Entrenador other = (Entrenador) obj;
        if (añosDeExperiencia != other.añosDeExperiencia)
            return false;
        return true;
    }


    @Override
    public String toString() {
        return  "Entrenador: " + super.toString() + " añosDeExperiencia= " + añosDeExperiencia;
    }
    
}

