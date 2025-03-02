package Champions;
import java.util.List;

import personal.Equipo;
import personal.Jugador;

public class Champions {
    /* Variables */
    private List<Equipo> equipos;

    /* Constructor */
    public Champions(List<Equipo> equipos) {
        this.equipos = equipos;
    }

    /* Getters y setters */
    public List<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }
 
    /* Jugar champions */
    public void jugarChampions() {
        // Verificar que hay suficientes equipos
        if (equipos == null || equipos.size() != 32) {
            System.out.println("Error: Se necesitan exactamente 32 equipos para jugar la Champions League");
            return;
        }

        System.out.println("\n=== COMIENZA LA UEFA CHAMPIONS LEAGUE ===\n");

        // Reiniciar estadísticas de los equipos para la Champions
        for (Equipo equipo : equipos) {
            equipo.setGolesAfavor(0);
            equipo.setGolesEnContra(0);
            equipo.setDiferenciaGoles(0);
            equipo.setPuntos(0);
            equipo.setPartidosJugados(0);
            for (Jugador jugador : equipo.getJugadores()) {
                jugador.setGolesAnotados(0);
                jugador.setTarjetasAmarillas(0);
                jugador.setTarjetasRojas(0);
            }
        }

        // Fase de grupos
        System.out.println("=== FASE DE GRUPOS ===\n");
        Fase_De_Grupos faseGrupos = new Fase_De_Grupos(equipos);
        faseGrupos.AsignarGrupos();
        faseGrupos.JugarGrupos();
        faseGrupos.VerClasificacion();
        List<Equipo> clasificadosOctavos = faseGrupos.getClasificadosOctavos();

        if (clasificadosOctavos.size() != 16) {
            System.out.println("Error: No hay suficientes equipos clasificados para octavos");
            return;
        }

        // Fase final
        System.out.println("\n=== FASE ELIMINATORIA ===\n");
        Fase_Final faseFinal = new Fase_Final(clasificadosOctavos);
        
        System.out.println("=== OCTAVOS DE FINAL ===\n");
        List<Equipo> clasificadosCuartos = faseFinal.JugarOctavos(clasificadosOctavos);
        
        if (clasificadosCuartos.size() != 8) {
            System.out.println("Error: No hay suficientes equipos clasificados para cuartos");
            return;
        }

        System.out.println("\n=== CUARTOS DE FINAL ===\n");
        List<Equipo> clasificadosSemifinales = faseFinal.JugarCuartos(clasificadosCuartos);
        
        if (clasificadosSemifinales.size() != 4) {
            System.out.println("Error: No hay suficientes equipos clasificados para semifinales");
            return;
        }

        System.out.println("\n=== SEMIFINALES ===\n");
        List<Equipo> clasificadosFinal = faseFinal.JugarSemifinales(clasificadosSemifinales);
        
        if (clasificadosFinal.size() != 2) {
            System.out.println("Error: No hay suficientes equipos clasificados para la final");
            return;
        }

        System.out.println("\n=== FINAL DE LA UEFA CHAMPIONS LEAGUE ===\n");
        faseFinal.JugarFinal(clasificadosFinal);
    }



    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Champions other = (Champions) obj;
        if (equipos == null) {
            if (other.equipos != null)
                return false;
        } else if (!equipos.equals(other.equipos))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Champions [equipos=" + equipos + "]";
    }


    
}
