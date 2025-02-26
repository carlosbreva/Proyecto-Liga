package Champions;
import java.util.List;
import java.util.ArrayList;
import liga.*;
import personal.*;
import java.util.Collections;

public class Fase_Final {
    private List<Equipo> equipos;
    private List<Partido> partidos;
    private List<Equipo> octavos;
    private List<Equipo> cuartos;
    private List<Equipo> semifinales;
    private List<Equipo> finales;
    private List<Equipo> ganadorChampions;

    public Fase_Final(List<Equipo> equipos) {
        this.equipos = equipos;
        this.partidos = new ArrayList<>();
        this.octavos = new ArrayList<>();
        this.cuartos = new ArrayList<>();
        this.semifinales = new ArrayList<>();
        this.finales = new ArrayList<>();
        this.ganadorChampions = new ArrayList<>();
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }

    public List<Partido> getPartidos() {
        return partidos;
    }

    public void setPartidos(List<Partido> partidos) {
        this.partidos = partidos;
    }

    public List<Equipo> getOctavos() {
        return octavos;
    }

    public void setOctavos(List<Equipo> octavos) {
        this.octavos = octavos;
    }

    public List<Equipo> getCuartos() {
        return cuartos;
    }

    public void setCuartos(List<Equipo> cuartos) {
        this.cuartos = cuartos;
    }

    public List<Equipo> getSemifinales() {
        return semifinales;
    }

    public void setSemifinales(List<Equipo> semifinales) {
        this.semifinales = semifinales;
    }

    public List<Equipo> getFinales() {
        return finales;
    }

    public void setFinales(List<Equipo> finales) {
        this.finales = finales;
    }

    public List<Equipo> getGanadorChampions() {
        return ganadorChampions;
    }

    public void setGanadorChampions(List<Equipo> ganadorChampions) {
        this.ganadorChampions = ganadorChampions;
    }

    public void JugarOctavos(List<Equipo> equipos){
        for (Equipo equipo : equipos){
            equipo.setPuntos(0);
        }
        Collections.shuffle(equipos);
        for(int i=0; i<equipos.size(); i+=2){
            for(int j=1; j<equipos.size(); j+=2){
                if(i!=j){

                    Partido PartdoIda = new Partido(equipos.get(i), equipos.get(j), new int[]{0,0}, 0);
                    PartdoIda.simularPartido(equipos.get(i), equipos.get(j));
                    if (PartdoIda.getEquipoLocal().getPuntos() > PartdoIda.getEquipoVisitante().getPuntos()){
                        cuartos.add(PartdoIda.getEquipoLocal());
                    } else {
                        cuartos.add(PartdoIda.getEquipoVisitante());
                    }
                    Partido partidoVuelta = new Partido(equipos.get(j), equipos.get(i), new int[]{0,0}, 0);
                    partidoVuelta.simularPartido(equipos.get(j), equipos.get(i));
                    if (partidoVuelta.getEquipoLocal().getPuntos() > partidoVuelta.getEquipoVisitante().getPuntos()){
                        cuartos.add(partidoVuelta.getEquipoLocal());
                    } else {
                        cuartos.add(partidoVuelta.getEquipoVisitante());
                    }

                }
            }
        }
    }

    public void JugarCuartos(List<Equipo> equipos){
        for (Equipo equipo : equipos){
            equipo.setPuntos(0);
        }
        for(int i=0; i<equipos.size(); i+=2){
            for(int j=1; j<equipos.size(); j+=2){
                if(i!=j){

                    Partido PartdoIda = new Partido(equipos.get(i), equipos.get(j), new int[]{0,0}, 0);
                    PartdoIda.simularPartido(equipos.get(i), equipos.get(j));
                    if (PartdoIda.getEquipoLocal().getPuntos() > PartdoIda.getEquipoVisitante().getPuntos()){
                        semifinales.add(PartdoIda.getEquipoLocal());
                    } else {
                        semifinales.add(PartdoIda.getEquipoVisitante());
                    }
                    Partido partidoVuelta = new Partido(equipos.get(j), equipos.get(i), new int[]{0,0}, 0);
                    partidoVuelta.simularPartido(equipos.get(j), equipos.get(i));
                    if (partidoVuelta.getEquipoLocal().getPuntos() > partidoVuelta.getEquipoVisitante().getPuntos()){
                        semifinales.add(partidoVuelta.getEquipoLocal());
                    } else {
                        semifinales.add(partidoVuelta.getEquipoVisitante());
                    }

                }
            }
        }
    }

    public void JugarSemifinales(List<Equipo> equipos){
        for (Equipo equipo : equipos){
            equipo.setPuntos(0);
        }
        for(int i=0; i<equipos.size(); i+=2){
            for(int j=1; j<equipos.size(); j+=2){
                if(i!=j){

                    Partido PartdoIda = new Partido(equipos.get(i), equipos.get(j), new int[]{0,0}, 0);
                    PartdoIda.simularPartido(equipos.get(i), equipos.get(j));
                    if (PartdoIda.getEquipoLocal().getPuntos() > PartdoIda.getEquipoVisitante().getPuntos()){
                        finales.add(PartdoIda.getEquipoLocal());
                    } else {
                        finales.add(PartdoIda.getEquipoVisitante());
                    }
                    Partido partidoVuelta = new Partido(equipos.get(j), equipos.get(i), new int[]{0,0}, 0);
                    partidoVuelta.simularPartido(equipos.get(j), equipos.get(i));
                    if (partidoVuelta.getEquipoLocal().getPuntos() > partidoVuelta.getEquipoVisitante().getPuntos()){
                        finales.add(partidoVuelta.getEquipoLocal());
                    } else {
                        finales.add(partidoVuelta.getEquipoVisitante());
                    }

                }
            }
        }
    }


    public void JugarFinal(List<Equipo> equipos){
        for (Equipo equipo : equipos){
            equipo.setPuntos(0);
        }
        Partido PartidoFinal = new Partido(finales.get(0), finales.get(1), new int[]{0,0}, 0);
        PartidoFinal.simularPartido(finales.get(0), finales.get(1));
        if (PartidoFinal.getEquipoLocal().getPuntos() > PartidoFinal.getEquipoVisitante().getPuntos()){
            ganadorChampions.add(PartidoFinal.getEquipoLocal());
        } else {
            ganadorChampions.add(PartidoFinal.getEquipoVisitante());
        }
    }
}
