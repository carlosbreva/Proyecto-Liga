package Champions;
import java.util.List;
import java.util.ArrayList;
import partido.Partido;
import personal.Equipo;
import java.util.Scanner;

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

    /* Jugar octavos */
    public List<Equipo> JugarOctavos(List<Equipo> equipos) {
        cuartos.clear();
        /* Resetear estadisticas de los equipos */
        for (Equipo equipo : equipos) {
            equipo.setPuntos(0);
            equipo.setGolesAfavor(0);
            equipo.setGolesEnContra(0);
        }
        
        /* Emparejar equipos para octavos */
        for (int i = 0; i < equipos.size(); i += 2) {
            if (i + 1 < equipos.size()) {
                Equipo equipo1 = equipos.get(i);
                Equipo equipo2 = equipos.get(i + 1);
                
                System.out.println("\n=== OCTAVOS DE FINAL - PARTIDO " + ((i/2) + 1) + " ===");
                System.out.println(equipo1.getNombre() + " vs " + equipo2.getNombre());
                System.out.println("\nPresiona ENTER para jugar el partido de IDA...");
                Scanner scanner = new Scanner(System.in);
                scanner.nextLine();
                
                // Partido de ida
                Partido partidoIda = new Partido(equipo1, equipo2, new int[]{0,0}, 0);
                partidoIda.simularPartido(equipo1, equipo2, null);
                int golesIda1 = partidoIda.getResultado()[0];
                int golesIda2 = partidoIda.getResultado()[1];
                
                System.out.println("\nPresiona ENTER para jugar el partido de VUELTA...");
                scanner.nextLine();
                
                // Partido de vuelta
                Partido partidoVuelta = new Partido(equipo2, equipo1, new int[]{0,0}, 0);
                partidoVuelta.simularPartido(equipo2, equipo1, null);
                int golesVuelta1 = partidoVuelta.getResultado()[1];
                int golesVuelta2 = partidoVuelta.getResultado()[0];
                
                // Calcular resultado global
                int golesGlobales1 = golesIda1 + golesVuelta1;
                int golesGlobales2 = golesIda2 + golesVuelta2;
                
                System.out.println("\nRESULTADO GLOBAL: " + equipo1.getNombre() + " " + golesGlobales1 + 
                                 " - " + golesGlobales2 + " " + equipo2.getNombre());
                
                /* Avanzar equipo */
                if (golesGlobales1 > golesGlobales2) {
                    cuartos.add(equipo1);
                    System.out.println("\n¡" + equipo1.getNombre() + " avanza a cuartos de final!");
                } else if (golesGlobales2 > golesGlobales1) {
                    cuartos.add(equipo2);
                    System.out.println("\n¡" + equipo2.getNombre() + " avanza a cuartos de final!");
                } else {
                    // En caso de empate, el equipo con más goles fuera gana
                    if (golesIda2 > golesVuelta1) {
                        cuartos.add(equipo2);
                        System.out.println("\n¡" + equipo2.getNombre() + " avanza a cuartos por goles fuera de casa!");
                    } else {
                        cuartos.add(equipo1);
                        System.out.println("\n¡" + equipo1.getNombre() + " avanza a cuartos por goles fuera de casa!");
                    }
                }
                
                System.out.println("\nPresiona ENTER para continuar...");
                scanner.nextLine();
                scanner.close();
                
            }
        }
        return cuartos;
    }

    /* Jugar cuartos */
    public List<Equipo> JugarCuartos(List<Equipo> equipos) {
        semifinales.clear();
        for (Equipo equipo : equipos) {
            equipo.setPuntos(0);
            equipo.setGolesAfavor(0);
            equipo.setGolesEnContra(0);
        }
        
        // Emparejar equipos para cuartos
        for (int i = 0; i < equipos.size(); i += 2) {
            if (i + 1 < equipos.size()) {
                Equipo equipo1 = equipos.get(i);
                Equipo equipo2 = equipos.get(i + 1);
                
                System.out.println("\n=== CUARTOS DE FINAL - PARTIDO " + ((i/2) + 1) + " ===");
                System.out.println(equipo1.getNombre() + " vs " + equipo2.getNombre());
                System.out.println("\nPresiona ENTER para jugar el partido de IDA...");
                Scanner scanner = new Scanner(System.in);
                scanner.nextLine();
                
                // Partido de ida
                Partido partidoIda = new Partido(equipo1, equipo2, new int[]{0,0}, 0);
                partidoIda.simularPartido(equipo1, equipo2, null);
                int golesIda1 = partidoIda.getResultado()[0];
                int golesIda2 = partidoIda.getResultado()[1];
                
                System.out.println("\nPresiona ENTER para jugar el partido de VUELTA...");
                scanner.nextLine();
                
                // Partido de vuelta
                Partido partidoVuelta = new Partido(equipo2, equipo1, new int[]{0,0}, 0);
                partidoVuelta.simularPartido(equipo2, equipo1, null);
                int golesVuelta1 = partidoVuelta.getResultado()[1];
                int golesVuelta2 = partidoVuelta.getResultado()[0];
                
                // Calcular resultado global
                int golesGlobales1 = golesIda1 + golesVuelta1;
                int golesGlobales2 = golesIda2 + golesVuelta2;
                
                System.out.println("\nRESULTADO GLOBAL: " + equipo1.getNombre() + " " + golesGlobales1 + 
                                 " - " + golesGlobales2 + " " + equipo2.getNombre());
                
                if (golesGlobales1 > golesGlobales2) {
                    semifinales.add(equipo1);
                    System.out.println("\n¡" + equipo1.getNombre() + " avanza a semifinales!");
                } else if (golesGlobales2 > golesGlobales1) {
                    semifinales.add(equipo2);
                    System.out.println("\n¡" + equipo2.getNombre() + " avanza a semifinales!");
                } else {
                    // En caso de empate, el equipo con más goles fuera gana
                    if (golesIda2 > golesVuelta1) {
                        semifinales.add(equipo2);
                        System.out.println("\n¡" + equipo2.getNombre() + " avanza a semifinales por goles fuera de casa!");
                    } else {
                        semifinales.add(equipo1);
                        System.out.println("\n¡" + equipo1.getNombre() + " avanza a semifinales por goles fuera de casa!");
                    }
                }
                
                System.out.println("\nPresiona ENTER para continuar...");
                scanner.nextLine();
                scanner.close();
            }
        }
        return semifinales;
    }

    /* Jugar semifinales */
    public List<Equipo> JugarSemifinales(List<Equipo> equipos) {
        finales.clear();
        for (Equipo equipo : equipos) {
            equipo.setPuntos(0);
            equipo.setGolesAfavor(0);
            equipo.setGolesEnContra(0);
        }
        
        /* Emparejar equipos para semifinales */
        for (int i = 0; i < equipos.size(); i += 2) {
            if (i + 1 < equipos.size()) {
                Equipo equipo1 = equipos.get(i);
                Equipo equipo2 = equipos.get(i + 1);
                
                System.out.println("\n=== SEMIFINAL - PARTIDO " + ((i/2) + 1) + " ===");
                System.out.println(equipo1.getNombre() + " vs " + equipo2.getNombre());
                System.out.println("\nPresiona ENTER para jugar el partido de IDA...");
                Scanner scanner = new Scanner(System.in);
                scanner.nextLine();
                
                // Partido de ida
                Partido partidoIda = new Partido(equipo1, equipo2, new int[]{0,0}, 0);
                partidoIda.simularPartido(equipo1, equipo2, null);
                int golesIda1 = partidoIda.getResultado()[0];
                int golesIda2 = partidoIda.getResultado()[1];
                
                System.out.println("\nPresiona ENTER para jugar el partido de VUELTA...");
                scanner.nextLine();
                
                // Partido de vuelta
                Partido partidoVuelta = new Partido(equipo2, equipo1, new int[]{0,0}, 0);
                partidoVuelta.simularPartido(equipo2, equipo1, null);
                int golesVuelta1 = partidoVuelta.getResultado()[1];
                int golesVuelta2 = partidoVuelta.getResultado()[0];
                
                // Calcular resultado global
                int golesGlobales1 = golesIda1 + golesVuelta1;
                int golesGlobales2 = golesIda2 + golesVuelta2;
                
                System.out.println("\nRESULTADO GLOBAL: " + equipo1.getNombre() + " " + golesGlobales1 + 
                                 " - " + golesGlobales2 + " " + equipo2.getNombre());
                
                if (golesGlobales1 > golesGlobales2) {
                    finales.add(equipo1);
                    System.out.println("\n¡" + equipo1.getNombre() + " avanza a la final!");
                } else if (golesGlobales2 > golesGlobales1) {
                    finales.add(equipo2);
                    System.out.println("\n¡" + equipo2.getNombre() + " avanza a la final!");
                } else {
                    // En caso de empate, el equipo con más goles fuera gana
                    if (golesIda2 > golesVuelta1) {
                        finales.add(equipo2);
                        System.out.println("\n¡" + equipo2.getNombre() + " avanza a la final por goles fuera de casa!");
                    } else {
                        finales.add(equipo1);
                        System.out.println("\n¡" + equipo1.getNombre() + " avanza a la final por goles fuera de casa!");
                    }
                }
                
                System.out.println("\nPresiona ENTER para continuar...");
                scanner.nextLine();
                scanner.close();
            }
        }
        return finales;
    }

    /* Jugar final */
    public void JugarFinal(List<Equipo> equipos) {
        if (equipos.size() != 2) {
            System.out.println("Error: Se necesitan exactamente 2 equipos para la final");
            return;
        }
        
        ganadorChampions.clear();
        Equipo equipo1 = equipos.get(0);
        Equipo equipo2 = equipos.get(1);
        
        equipo1.setPuntos(0);
        equipo1.setGolesAfavor(0);
        equipo1.setGolesEnContra(0);
        equipo2.setPuntos(0);
        equipo2.setGolesAfavor(0);
        equipo2.setGolesEnContra(0);

        System.out.println("\n=== FINAL DE LA UEFA CHAMPIONS LEAGUE ===");
        System.out.println(equipo1.getNombre() + " vs " + equipo2.getNombre());
        System.out.println("\nPresiona ENTER para comenzar la final...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        
        Partido partidoFinal = new Partido(equipo1, equipo2, new int[]{0,0}, 0);
        partidoFinal.simularPartido(equipo1, equipo2, null);
        /* Verificar ganador */
        if (partidoFinal.getEquipoLocal().getPuntos() > partidoFinal.getEquipoVisitante().getPuntos()) {
            ganadorChampions.add(partidoFinal.getEquipoLocal());
        } else {
            ganadorChampions.add(partidoFinal.getEquipoVisitante());
        }
        /* Mostrar ganador */
        System.out.println("\n¡" + ganadorChampions.get(0).getNombre() + " ES EL CAMPEÓN DE LA UEFA CHAMPIONS LEAGUE!");
        scanner.close();
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
