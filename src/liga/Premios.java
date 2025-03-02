package liga;
import java.util.List;
import personal.Jugador;
import personal.Equipo;
import personal.Portero;
import java.util.ArrayList;
public class Premios  {
    /* Variables */
    private String MVP;
    private String Goleador;
    private String PorteroTorneo;
    private String EquipoCampeon;

    /* Constructor */
    public Premios(String MVP, String Goleador, String PorteroTorneo, String EquipoCampeon) {
        this.MVP = MVP;
        this.Goleador = Goleador;
        this.PorteroTorneo = PorteroTorneo;
        this.EquipoCampeon = EquipoCampeon;

    }

    /* Getters y setters */
    
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


    /* Dar premios */
    public static void darPremios(List<Jugador> jugadores, List<Equipo> equipos, List<Portero> porteros) {
        System.out.println("\n=== PREMIOS DE LA TEMPORADA ===\n");
        
        //Equipo campeón
        Equipo campeon = equipos.get(0);
        for (Equipo equipo : equipos) {
            if (equipo.getPuntos() > campeon.getPuntos() || 
               (equipo.getPuntos() == campeon.getPuntos() && equipo.getDiferenciaGoles() > campeon.getDiferenciaGoles())) {
                campeon = equipo;
            }
        }
        System.out.println("CAMPEÓN DE LIGA: " + campeon.getNombre());
        
        // MVP - considerando goles, tarjetas y media de stats
        Jugador mvp = jugadores.get(0);
        for (Jugador jugador : jugadores) {
            int puntosMVP_actual = jugador.getGolesAnotados()*3 - jugador.getTarjetasRojas()*5 - 
                                  jugador.getTarjetasAmarillas() + jugador.getStatMediaJugador();
            int puntosMVP_mejor = mvp.getGolesAnotados()*3 - mvp.getTarjetasRojas()*5 - 
                                 mvp.getTarjetasAmarillas() + mvp.getStatMediaJugador();
            
            if (puntosMVP_actual > puntosMVP_mejor) {
                mvp = jugador;
            }
        }
        System.out.println("MVP: " + mvp.getNombre() + " (" + mvp.getGolesAnotados() + " goles)");
        
        // Goleador
        Jugador goleador = jugadores.get(0);
        for (Jugador jugador : jugadores) {
            if (jugador.getGolesAnotados() > goleador.getGolesAnotados()) {
                goleador = jugador;
            }
        }
        System.out.println("PICHICHI: " + goleador.getNombre() + " (" + goleador.getGolesAnotados() + " goles)");

        // Portero del torneo
        Portero zamorano = porteros.get(0);
        for (Portero p : porteros) {
            if (p.getNumeroDeParadas() > zamorano.getNumeroDeParadas()) {
                zamorano = p;
            }
        }
        System.out.println("ZAMORA: " + zamorano.getNombre() + " (" + zamorano.getNumeroDeParadas() + " paradas)");

        // Equipos a Champions
        List<Equipo> equiposChampions = EquiposAEuropa(equipos);
        System.out.println("\n=== EQUIPOS CLASIFICADOS A CHAMPIONS ===");
        for (int i = 0; i < equiposChampions.size(); i++) {
            System.out.println((i+1) + ". " + equiposChampions.get(i).getNombre() + 
                             " (" + equiposChampions.get(i).getPuntos() + " pts)");
        }

        // Equipos descendidos
        System.out.println("\n=== EQUIPOS DESCENDIDOS ===");
        List<Equipo> equiposOrdenados = new ArrayList<>(equipos);
        equiposOrdenados.sort((e1, e2) -> {
            if (e1.getPuntos() != e2.getPuntos()) {
                return e1.getPuntos() - e2.getPuntos();
            }
            return e1.getDiferenciaGoles() - e2.getDiferenciaGoles();
        });

        for (int i = 0; i < 3 && i < equiposOrdenados.size(); i++) {
            Equipo descendido = equiposOrdenados.get(i);
            System.out.println(descendido.getNombre() + 
                             " (" + descendido.getPuntos() + " pts, " + 
                             descendido.getDiferenciaGoles() + " dif. goles)");
        }
    }

    /* Equipos a Europa */
    public static List<Equipo> EquiposAEuropa(List<Equipo> equipos) {
        List<Equipo> equiposOrdenados = new ArrayList<>(equipos);
        equiposOrdenados.sort((e1, e2) -> {
            if (e2.getPuntos() != e1.getPuntos()) {
                return e2.getPuntos() - e1.getPuntos();
            }
            return e2.getDiferenciaGoles() - e1.getDiferenciaGoles();
        });

        List<Equipo> equiposEuropa = new ArrayList<>();
        for (int i = 0; i < 4 && i < equiposOrdenados.size(); i++) {
            equiposEuropa.add(equiposOrdenados.get(i));
        }
        return equiposEuropa;
    }

    /* Equals */
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
