package liga;
import java.util.List;
import personal.Jugador;
import personal.Equipo;
import personal.Portero;
import java.util.ArrayList;
public class Premios  {
    
    private String MVP;
    private String Goleador;
    private String PorteroTorneo;
    private String EquipoCampeon;


    public Premios(String MVP, String Goleador, String PorteroTorneo, String EquipoCampeon) {
        this.MVP = MVP;
        this.Goleador = Goleador;
        this.PorteroTorneo = PorteroTorneo;
        this.EquipoCampeon = EquipoCampeon;

    }

    public Premios(){

    }
    
    public String getMVP() {
        return MVP;
    }

    public void setMVP(String MVP) {
        this.MVP = MVP;
    }

    public String getGoleador() {
        return Goleador;
    }

    public void setGoleador(String Goleador) {
        this.Goleador = Goleador;
    }

    public String getPorteroTorneo() {
        return PorteroTorneo;
    }

    public void setPorteroTorneo(String PorteroTorneo) {
        this.PorteroTorneo = PorteroTorneo;
    }   

    public String getEquipoCampeon() {
        return EquipoCampeon;
    }

    public void setEquipoCampeon(String EquipoCampeon) {    
        this.EquipoCampeon = EquipoCampeon;
    }


    public static void darPremios(List<Jugador> jugadores, List<Equipo> equipos, List<Portero> porteros) {
        //LIGA
        Equipo campeon = equipos.get(0);
        for (Equipo equipo : equipos) {
            if (equipo.getPuntos() > campeon.getPuntos()) {
                campeon = equipo;
            }
        }
        System.out.println("Equipo campeón: " + campeon.getNombre());
        // MVP
        Jugador mvp = jugadores.get(0);
        for (Jugador jugador : jugadores) {
            if (jugador.getGolesAnotados() > mvp.getGolesAnotados() && jugador.getTarjetasRojas() < mvp.getTarjetasRojas() && jugador.getTarjetasAmarillas() < mvp.getTarjetasAmarillas()) {
                mvp = jugador;
            }
        }
        System.out.println("MVP: " + mvp.getNombre());
        // Goleador
        Jugador goleador = jugadores.get(0);
        for (Jugador jugador : jugadores) {
            if (jugador.getGolesAnotados() > goleador.getGolesAnotados()) {
                goleador = jugador;
            }
        }
        System.out.println("Goleador: " + goleador.getNombre());

        // Portero del torneo
        Portero mejorPortero = porteros.get(0);
        for (Portero p : porteros) {
            if (p.getNumeroDeParadas() > mejorPortero.getNumeroDeParadas()) {
                mejorPortero = p;
            }
        }
        System.out.println("Portero del torneo: " + mejorPortero.getNombre());  

        

    }

    public static List<Equipo> EquiposAEuropa(List<Equipo> equipos) {
        List<Equipo> equiposEuropa = new ArrayList<>();
        Equipo primerEquipoEuropa = equipos.get(0);
        Equipo segundoEquipoEuropa = equipos.get(0);
        Equipo tercerEquipoEuropa = equipos.get(0);
        for (Equipo equipo : equipos) {
            if (equipo.getPuntos() > primerEquipoEuropa.getPuntos()) {
                primerEquipoEuropa = equipo;
            }
        }
        equiposEuropa.add(primerEquipoEuropa);
        for (Equipo equipo : equipos) {
            if (equipo.getPuntos() > segundoEquipoEuropa.getPuntos() && equipo != primerEquipoEuropa) {
                segundoEquipoEuropa = equipo;
            }
        }
        equiposEuropa.add(segundoEquipoEuropa);
        for (Equipo equipo : equipos) {
            if (equipo.getPuntos() > tercerEquipoEuropa.getPuntos() && equipo != primerEquipoEuropa && equipo != segundoEquipoEuropa) {
                tercerEquipoEuropa = equipo;
            }
        }
        equiposEuropa.add(tercerEquipoEuropa);
        return equiposEuropa;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Premios other = (Premios) obj;
        if (MVP == null) {
            if (other.MVP != null)
                return false;
        } else if (!MVP.equals(other.MVP))
            return false;
        if (Goleador == null) {
            if (other.Goleador != null)
                return false;
        } else if (!Goleador.equals(other.Goleador))
            return false;
        if (PorteroTorneo == null) {
            if (other.PorteroTorneo != null)
                return false;
        } else if (!PorteroTorneo.equals(other.PorteroTorneo))
            return false;
        if (EquipoCampeon == null) {
            if (other.EquipoCampeon != null)
                return false;
        } else if (!EquipoCampeon.equals(other.EquipoCampeon))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Premios [MVP=" + MVP + ", Goleador=" + Goleador + ", PorteroTorneo=" + PorteroTorneo
                + ", EquipoCampeon=" + EquipoCampeon + "]";
    }

   
    
}
