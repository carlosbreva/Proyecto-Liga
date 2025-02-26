package Champions;
import java.util.List;
import personal.*;
import Champions.*;

public class Champions {
    private List<Equipo> equipos;

    public Champions(List<Equipo> equipos) {
        this.equipos = equipos;
    }
    
    public List<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }

    public void jugarChampions() {
        Fase_De_Grupos faseGrupos = new Fase_De_Grupos(equipos);
        faseGrupos.AsignarGrupos();
        faseGrupos.JugarGrupos();
        faseGrupos.VerClasificacion();
        List<Equipo> clasificadosOctavos = faseGrupos.getClasificadosOctavos();
        Fase_Final faseFinal = new Fase_Final(clasificadosOctavos);
        List<Equipo> clasificadosCuartos = faseFinal.JugarOctavos(clasificadosOctavos);
        List<Equipo> clasificadosSemifinales = faseFinal.JugarCuartos(clasificadosCuartos);
        List<Equipo> clasificadosFinal = faseFinal.JugarSemifinales(clasificadosSemifinales);
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
