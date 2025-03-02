package Champions;
import java.util.List;
import java.util.ArrayList;
import partido.Partido;
import personal.Equipo;
import java.util.Scanner;
import java.util.Random;

public class Fase_Final {
    /* Variables */
    private List<Equipo> equipos;
    private List<Partido> partidos;
    private List<Equipo> octavos;
    private List<Equipo> cuartos;
    private List<Equipo> semifinales;
    private List<Equipo> finales;
    private List<Equipo> ganadorChampions;

    /* Constructor */
    public Fase_Final(List<Equipo> equipos) {
        this.equipos = equipos;
        this.partidos = new ArrayList<>();
        this.octavos = new ArrayList<>();
        this.cuartos = new ArrayList<>();
        this.semifinales = new ArrayList<>();
        this.finales = new ArrayList<>();
        this.ganadorChampions = new ArrayList<>();
    }

    /* Getters y setters */
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

    /* Simular tanda de penaltis */
    private Equipo simularPenaltis(Equipo equipo1, Equipo equipo2) {
        System.out.println("\n=== TANDA DE PENALTIS ===");
        int penaltisEquipo1 = 0;
        int penaltisEquipo2 = 0;
        Random random = new Random();

        // Primera ronda de 5 penaltis por equipo
        for (int i = 0; i < 5; i++) {
            // Penalti equipo 1
            if (random.nextDouble() < 0.75) {
                penaltisEquipo1++;
                System.out.println("¡GOL! " + equipo1.getNombre() + " (" + penaltisEquipo1 + "-" + penaltisEquipo2 + ")");
            } else {
                System.out.println("¡FALLO! " + equipo1.getNombre());
            }
            
            // Penalti equipo 2
            if (random.nextDouble() < 0.75) {
                penaltisEquipo2++;
                System.out.println("¡GOL! " + equipo2.getNombre() + " (" + penaltisEquipo1 + "-" + penaltisEquipo2 + ")");
            } else {
                System.out.println("¡FALLO! " + equipo2.getNombre());
            }
        }

        // Si hay empate, muerte súbita
        while (penaltisEquipo1 == penaltisEquipo2) {
            // Penalti equipo 1
            if (random.nextDouble() < 0.75) {
                penaltisEquipo1++;
                System.out.println("¡GOL! " + equipo1.getNombre() + " (" + penaltisEquipo1 + "-" + penaltisEquipo2 + ")");
            } else {
                System.out.println("¡FALLO! " + equipo1.getNombre());
            }
            
            // Si el segundo equipo falla, gana el primero
            if (penaltisEquipo1 > penaltisEquipo2) {
                if (random.nextDouble() < 0.75) {
                    penaltisEquipo2++;
                    System.out.println("¡GOL! " + equipo2.getNombre() + " (" + penaltisEquipo1 + "-" + penaltisEquipo2 + ")");
                } else {
                    System.out.println("¡FALLO! " + equipo2.getNombre());
                    System.out.println("\n¡" + equipo1.getNombre() + " gana en los penaltis! (" + penaltisEquipo1 + "-" + penaltisEquipo2 + ")");
                    return equipo1;
                }
            }
        }

        // Determinar ganador
        if (penaltisEquipo1 > penaltisEquipo2) {
            System.out.println("\n¡" + equipo1.getNombre() + " gana en los penaltis! (" + penaltisEquipo1 + "-" + penaltisEquipo2 + ")");
            return equipo1;
        } else {
            System.out.println("\n¡" + equipo2.getNombre() + " gana en los penaltis! (" + penaltisEquipo1 + "-" + penaltisEquipo2 + ")");
            return equipo2;
        }
    }

    /* Jugar octavos */
    public List<Equipo> JugarOctavos(List<Equipo> equipos) {
        List<Equipo> clasificados = new ArrayList<>();
        
        for (int i = 0; i < equipos.size(); i += 2) {
            Equipo equipo1 = equipos.get(i);
            Equipo equipo2 = equipos.get(i + 1);
            
            System.out.println("\n=== OCTAVOS DE FINAL - PARTIDO DE IDA ===");
            Partido PartidoIda = new Partido(equipo1, equipo2, new int[]{0, 0}, 0);
            PartidoIda.simularPartido(equipo1, equipo2, null);
            
            System.out.println("\n=== OCTAVOS DE FINAL - PARTIDO DE VUELTA ===");
            Partido PartidoVuelta = new Partido(equipo2, equipo1, new int[]{0, 0}, 0);
            PartidoVuelta.simularPartido(equipo2, equipo1, null);
            
            // Calcular resultado global
            int golesEquipo1 = PartidoIda.getResultado()[0] + PartidoVuelta.getResultado()[1];
            int golesEquipo2 = PartidoIda.getResultado()[1] + PartidoVuelta.getResultado()[0];
            
            System.out.println("\nRESULTADO GLOBAL: " + equipo1.getNombre() + " " + golesEquipo1 + 
                             " - " + golesEquipo2 + " " + equipo2.getNombre());
            
            // Si hay empate, penaltis
            if (golesEquipo1 == golesEquipo2) {
                System.out.println("\n¡EMPATE EN EL GLOBAL! Se decidirá en los penaltis");
                Equipo ganador = simularPenaltis(equipo1, equipo2);
                clasificados.add(ganador);
            } else {
                // Clasificar al ganador
                if (golesEquipo1 > golesEquipo2) {
                    System.out.println("\n¡" + equipo1.getNombre() + " se clasifica para cuartos de final!");
                    clasificados.add(equipo1);
                } else {
                    System.out.println("\n¡" + equipo2.getNombre() + " se clasifica para cuartos de final!");
                    clasificados.add(equipo2);
                }
            }
        }
        return clasificados;
    }

    /* Jugar cuartos */
    public List<Equipo> JugarCuartos(List<Equipo> equipos) {
        List<Equipo> clasificados = new ArrayList<>();
        
        for (int i = 0; i < equipos.size(); i += 2) {
            Equipo equipo1 = equipos.get(i);
            Equipo equipo2 = equipos.get(i + 1);
            
            System.out.println("\n=== CUARTOS DE FINAL - PARTIDO DE IDA ===");
            Partido PartidoIda = new Partido(equipo1, equipo2, new int[]{0, 0}, 0);
            PartidoIda.simularPartido(equipo1, equipo2, null);
            
            System.out.println("\n=== CUARTOS DE FINAL - PARTIDO DE VUELTA ===");
            Partido PartidoVuelta = new Partido(equipo2, equipo1, new int[]{0, 0}, 0);
            PartidoVuelta.simularPartido(equipo2, equipo1, null);
            
            // Calcular resultado global
            int golesEquipo1 = PartidoIda.getResultado()[0] + PartidoVuelta.getResultado()[1];
            int golesEquipo2 = PartidoIda.getResultado()[1] + PartidoVuelta.getResultado()[0];
            
            System.out.println("\nRESULTADO GLOBAL: " + equipo1.getNombre() + " " + golesEquipo1 + 
                             " - " + golesEquipo2 + " " + equipo2.getNombre());
            
            // Si hay empate, penaltis
            if (golesEquipo1 == golesEquipo2) {
                System.out.println("\n¡EMPATE EN EL GLOBAL! Se decidirá en los penaltis");
                Equipo ganador = simularPenaltis(equipo1, equipo2);
                clasificados.add(ganador);
            } else {
                // Clasificar al ganador
                if (golesEquipo1 > golesEquipo2) {
                    System.out.println("\n¡" + equipo1.getNombre() + " se clasifica para semifinales!");
                    clasificados.add(equipo1);
                } else {
                    System.out.println("\n¡" + equipo2.getNombre() + " se clasifica para semifinales!");
                    clasificados.add(equipo2);
                }
            }
        }
        return clasificados;
    }

    /* Jugar semifinales */
    public List<Equipo> JugarSemifinales(List<Equipo> equipos) {
        List<Equipo> clasificados = new ArrayList<>();
        
        for (int i = 0; i < equipos.size(); i += 2) {
            Equipo equipo1 = equipos.get(i);
            Equipo equipo2 = equipos.get(i + 1);
            
            System.out.println("\n=== SEMIFINALES - PARTIDO DE IDA ===");
            Partido PartidoIda = new Partido(equipo1, equipo2, new int[]{0, 0}, 0);
            PartidoIda.simularPartido(equipo1, equipo2, null);
            
            System.out.println("\n=== SEMIFINALES - PARTIDO DE VUELTA ===");
            Partido PartidoVuelta = new Partido(equipo2, equipo1, new int[]{0, 0}, 0);
            PartidoVuelta.simularPartido(equipo2, equipo1, null);
            
            // Calcular resultado global
            int golesEquipo1 = PartidoIda.getResultado()[0] + PartidoVuelta.getResultado()[1];
            int golesEquipo2 = PartidoIda.getResultado()[1] + PartidoVuelta.getResultado()[0];
            
            System.out.println("\nRESULTADO GLOBAL: " + equipo1.getNombre() + " " + golesEquipo1 + 
                             " - " + golesEquipo2 + " " + equipo2.getNombre());
            
            // Si hay empate, penaltis
            if (golesEquipo1 == golesEquipo2) {
                System.out.println("\n¡EMPATE EN EL GLOBAL! Se decidirá en los penaltis");
                Equipo ganador = simularPenaltis(equipo1, equipo2);
                clasificados.add(ganador);
            } else {
                // Clasificar al ganador
                if (golesEquipo1 > golesEquipo2) {
                    System.out.println("\n¡" + equipo1.getNombre() + " se clasifica para la final!");
                    clasificados.add(equipo1);
                } else {
                    System.out.println("\n¡" + equipo2.getNombre() + " se clasifica para la final!");
                    clasificados.add(equipo2);
                }
            }
        }
        return clasificados;
    }

    /* Jugar final */
    public void JugarFinal(List<Equipo> finales) {
        Equipo equipo1 = finales.get(0);
        Equipo equipo2 = finales.get(1);
        
        System.out.println("\n=== FINAL DE LA UEFA CHAMPIONS LEAGUE ===");
        Partido PartidoFinal = new Partido(equipo1, equipo2, new int[]{0, 0}, 0);
        PartidoFinal.simularPartido(equipo1, equipo2, null);
        
        // Si hay empate, penaltis
        if (PartidoFinal.getResultado()[0] == PartidoFinal.getResultado()[1]) {
            System.out.println("\n¡EMPATE! Se decidirá en los penaltis");
            Equipo campeon = simularPenaltis(equipo1, equipo2);
            System.out.println("\n¡" + campeon.getNombre() + " ES EL CAMPEÓN DE LA UEFA CHAMPIONS LEAGUE!");
        } else {
            // Determinar el campeón
            if (PartidoFinal.getResultado()[0] > PartidoFinal.getResultado()[1]) {
                System.out.println("\n¡" + equipo1.getNombre() + " ES EL CAMPEÓN DE LA UEFA CHAMPIONS LEAGUE!");
            } else {
                System.out.println("\n¡" + equipo2.getNombre() + " ES EL CAMPEÓN DE LA UEFA CHAMPIONS LEAGUE!");
            }
        }
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
        Fase_Final other = (Fase_Final) obj;
        if (equipos == null) {
            if (other.equipos != null)
                return false;
        } else if (!equipos.equals(other.equipos))
            return false;
        if (partidos == null) {
            if (other.partidos != null)
                return false;
        } else if (!partidos.equals(other.partidos))
            return false;
        if (octavos == null) {
            if (other.octavos != null)
                return false;
        } else if (!octavos.equals(other.octavos))
            return false;
        if (cuartos == null) {
            if (other.cuartos != null)
                return false;
        } else if (!cuartos.equals(other.cuartos))
            return false;
        if (semifinales == null) {
            if (other.semifinales != null)
                return false;
        } else if (!semifinales.equals(other.semifinales))
            return false;
        if (finales == null) {
            if (other.finales != null)
                return false;
        } else if (!finales.equals(other.finales))
            return false;
        if (ganadorChampions == null) {
            if (other.ganadorChampions != null)
                return false;
        } else if (!ganadorChampions.equals(other.ganadorChampions))
            return false;
        return true;
    }

    /* To string */
    @Override
    public String toString() {
        return "Fase_Final [equipos=" + equipos + ", partidos=" + partidos + ", octavos=" + octavos + ", cuartos="
                + cuartos + ", semifinales=" + semifinales + ", finales=" + finales + ", ganadorChampions="
                + ganadorChampions + "]";
    } 

    
}
