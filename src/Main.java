import personal.Persona;
import personal.Equipo;
import personal.Jugador;
import personal.Entrenador;
import java.util.List;
import personal.Persona;


public class Main {
    public static void main(String[] args) {
    String rutaFichero = "src/Nombres_jugadores.txt"; 
    String rutaFicheroEquipos = "src/Nombres_Equipos.txt";

        List<Entrenador> entrenadores = Entrenador.crearEntrenadores();
        for (Entrenador entrenador : entrenadores) {
            System.out.println(entrenador);
        }
    }
}