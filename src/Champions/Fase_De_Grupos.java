package Champions;
import java.util.List;
import liga.*;
import personal.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Arrays;
public class Fase_De_Grupos {

    private List<Equipo> equipos;
    private List<Partido> partidos;
    private List<Equipo> grupoA;
    private List<Equipo> grupoB;
    private List<Equipo> grupoC;
    private List<Equipo> grupoD;
    private List<Equipo> grupoE;
    private List<Equipo> grupoF;
    private List<Equipo> grupoG;
    private List<Equipo> grupoH;

    public Fase_De_Grupos(List<Equipo> equipos) {
        this.equipos = equipos;
        this.partidos = new ArrayList<>();
        this.grupoA = new ArrayList<>();
        this.grupoB = new ArrayList<>();
        this.grupoC = new ArrayList<>();
        this.grupoD = new ArrayList<>();
        this.grupoE = new ArrayList<>();
        this.grupoF = new ArrayList<>();
        this.grupoG = new ArrayList<>();
        this.grupoH = new ArrayList<>();
    }

    /*Metodos get y set*/
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

    // Metodo asginar grupos   
    public void AsignarGrupos() {
        // Limpiar grupos anteriores
        grupoA.clear(); grupoB.clear(); grupoC.clear(); grupoD.clear();
        grupoE.clear(); grupoF.clear(); grupoG.clear(); grupoH.clear();

        for (Equipo equipo : equipos){
            equipo.setPartidosJugados(0);
            equipo.setPuntos(0);
            equipo.setGolesAfavor(0);
            equipo.setGolesEnContra(0);
            equipo.setDiferenciaGoles(0);
        }

        System.out.println("\n=== SORTEO DE GRUPOS DE LA CHAMPIONS LEAGUE ===\n");
        
        Collections.shuffle(equipos);
        
        for (int i = 0; i < equipos.size(); i++) {
            Equipo equipo = equipos.get(i);
            if (equipo == null) continue;
            
            if (i < 4) {
                grupoA.add(equipo);
                System.out.println("Grupo A: " + equipo.getNombre());
            } else if (i < 8) {
                grupoB.add(equipo);
                System.out.println("Grupo B: " + equipo.getNombre());
            } else if (i < 12) {
                grupoC.add(equipo);
                System.out.println("Grupo C: " + equipo.getNombre());
            } else if (i < 16) {
                grupoD.add(equipo);
                System.out.println("Grupo D: " + equipo.getNombre());
            } else if (i < 20) {
                grupoE.add(equipo);
                System.out.println("Grupo E: " + equipo.getNombre());
            } else if (i < 24) {
                grupoF.add(equipo);
                System.out.println("Grupo F: " + equipo.getNombre());
            } else if (i < 28) {
                grupoG.add(equipo);
                System.out.println("Grupo G: " + equipo.getNombre());
            } else if (i < 32) {
                grupoH.add(equipo);
                System.out.println("Grupo H: " + equipo.getNombre());
            }
        }
        System.out.println("\n=== FIN DEL SORTEO ===\n");
    }

    private void simularPartidosGrupo(List<Equipo> grupo) {
        if (grupo.size() < 4) return;
        
        Scanner scanner = new Scanner(System.in);
        boolean verClasificacion = false;
        
        // Cada equipo juega contra todos los demás dos veces (ida y vuelta)
        for (int i = 0; i < grupo.size(); i++) {
            for (int j = 0; j < grupo.size(); j++) {
                if (i != j) {
                    verClasificacion = false;
                    Equipo local = grupo.get(i);
                    Equipo visitante = grupo.get(j);
                
                    Partido partido = new Partido(local, visitante, new int[]{0, 0}, 0);
                    partido.simularPartido(local, visitante);
                    partidos.add(partido);

                    if (!verClasificacion) {
                        System.out.println("\n¿Quieres ver la clasificación? (s/n)");
                        String respuesta = scanner.nextLine();
                        verClasificacion = respuesta.equalsIgnoreCase("s");
                    }
                    
                    if (verClasificacion) {
                        System.out.println("\nClasificación actualizada después del partido " + 
                                         local.getNombre() + " vs " + visitante.getNombre() + ":");
                        VerClasificacion();
                        System.out.println("\nPresiona Enter para continuar...");
                        scanner.nextLine();

                    }
                }
            }
        }
    }

    public void JugarGrupos() {
        System.out.println("\n=== INICIO DE LA FASE DE GRUPOS ===\n");
        
        simularPartidosGrupo(grupoA);
        simularPartidosGrupo(grupoB);
        simularPartidosGrupo(grupoC);
        simularPartidosGrupo(grupoD);
        simularPartidosGrupo(grupoE);
        simularPartidosGrupo(grupoF);
        simularPartidosGrupo(grupoG);
        simularPartidosGrupo(grupoH);
        
        System.out.println("\n=== FIN DE LA FASE DE GRUPOS ===\n");
    }

    private void mostrarClasificacionGrupo(String nombreGrupo, List<Equipo> grupo) {
        if (grupo.isEmpty()) return;
        
        System.out.println("\n=== GRUPO " + nombreGrupo + " ===");
        System.out.println("Pos  Equipo                  PJ   PG   PE   PP   GF   GC   DG   Pts");
        System.out.println("----------------------------------------------------------------");

        // Ordenar equipos por puntos y diferencia de goles
        Collections.sort(grupo, (e1, e2) -> {
            if (e2.getPuntos() != e1.getPuntos()) {
                return e2.getPuntos() - e1.getPuntos();
            }
            return e2.getDiferenciaGoles() - e1.getDiferenciaGoles();
        });

        for (int i = 0; i < grupo.size(); i++) {
            Equipo e = grupo.get(i);
            int partidosJugados = e.getPartidosJugados();
            int partidosGanados = e.getPuntos() / 3;
            int partidosEmpatados = e.getPuntos() % 3;
            int partidosPerdidos = partidosJugados - partidosGanados - partidosEmpatados;

            System.out.printf("%-4d %-20s %4d %4d %4d %4d %4d %4d %4d %4d%n",
                i + 1,
                e.getNombre(),
                partidosJugados,
                partidosGanados,
                partidosEmpatados,
                partidosPerdidos,
                e.getGolesAfavor(),
                e.getGolesEnContra(),
                e.getDiferenciaGoles(),
                e.getPuntos()
            );
        }
        System.out.println("----------------------------------------------------------------");
    }

    public void VerClasificacion() {
        System.out.println("\n====== CLASIFICACIÓN DE LA CHAMPIONS LEAGUE ======\n");
        
        mostrarClasificacionGrupo("A", grupoA);
        mostrarClasificacionGrupo("B", grupoB);
        mostrarClasificacionGrupo("C", grupoC);
        mostrarClasificacionGrupo("D", grupoD);
        mostrarClasificacionGrupo("E", grupoE);
        mostrarClasificacionGrupo("F", grupoF);
        mostrarClasificacionGrupo("G", grupoG);
        mostrarClasificacionGrupo("H", grupoH);
    }

    public List<Equipo> getClasificadosOctavos() {
        List<Equipo> clasificados = new ArrayList<>();
        
        for (List<Equipo> grupo : Arrays.asList(grupoA, grupoB, grupoC, grupoD, grupoE, grupoF, grupoG, grupoH)) {
            if (grupo.size() >= 2) {
                Collections.sort(grupo, (e1, e2) -> {
                    if (e2.getPuntos() != e1.getPuntos()) {
                        return e2.getPuntos() - e1.getPuntos();
                    }
                    return e2.getDiferenciaGoles() - e1.getDiferenciaGoles();
                });
                clasificados.add(grupo.get(0));
                clasificados.add(grupo.get(1));
            }
        }
        
        return clasificados;
    }
}