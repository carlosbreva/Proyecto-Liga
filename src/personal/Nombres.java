package personal;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Nombres {
    private String[] nombres;
    private String nombreArchivo = "Nombres_Jugadores.txt";
    private File archivo = new File(nombreArchivo);

    public void cargarNombres() {
        if (archivo.exists()) {
            System.out.println("El archivo existe");
            List<String> listaNombres = new ArrayList<>();
            
            try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = lector.readLine()) != null) {
                    if (!linea.trim().isEmpty()) {
                        listaNombres.add(linea.trim());
                        System.out.println(linea.trim());
                    }
                }
                nombres = listaNombres.toArray(new String[0]);
                
            } catch (FileNotFoundException e) {
                System.out.println("El archivo no existe");
            } catch (IOException e) {
                System.out.println("Error al leer el archivo");
            }
        } else {
            System.out.println("El archivo no existe");
        }
    }
    
    public String[] getNombres() {
        return nombres;
    }
}

