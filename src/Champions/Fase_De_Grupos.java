package Champions;
import java.util.List;
import partido.Partido;
import personal.Equipo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Arrays;
public class Fase_De_Grupos {

    /* Variables */
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

    /* Constructor */
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
 
    public List<Equipo> getGrupoA() {
        return grupoA;
    }

    public void setGrupoA(List<Equipo> grupoA) {
        this.grupoA = grupoA;
    }

    public List<Equipo> getGrupoB() {
        return grupoB;
    }

    public void setGrupoB(List<Equipo> grupoB) {
        this.grupoB = grupoB;
    }

    public List<Equipo> getGrupoC() {
        return grupoC;
    }

    public void setGrupoC(List<Equipo> grupoC) {
        this.grupoC = grupoC;
    }

    public List<Equipo> getGrupoD() {
        return grupoD;
    }

    public void setGrupoD(List<Equipo> grupoD) {
        this.grupoD = grupoD;
    }

    public List<Equipo> getGrupoE() {
        return grupoE;
    }

    public void setGrupoE(List<Equipo> grupoE) {
        this.grupoE = grupoE;
    }

    public List<Equipo> getGrupoF() {
        return grupoF;
    }

    public void setGrupoF(List<Equipo> grupoF) {
        this.grupoF = grupoF;
    }

    public List<Equipo> getGrupoG() {
        return grupoG;
    }

    public void setGrupoG(List<Equipo> grupoG) {
        this.grupoG = grupoG;
    }

    public List<Equipo> getGrupoH() {
        return grupoH;
    }

    public void setGrupoH(List<Equipo> grupoH) {
        this.grupoH = grupoH;
    }

    /* Asignar grupos */
    public void AsignarGrupos() {
        // Limpiar grupos anteriores
        grupoA.clear(); grupoB.clear(); grupoC.clear(); grupoD.clear();
        grupoE.clear(); grupoF.clear(); grupoG.clear(); grupoH.clear();

        /* Resetear estadisticas de los equipos */
        for (Equipo equipo : equipos){
            equipo.setPartidosJugados(0);
            equipo.setPuntos(0);
            equipo.setGolesAfavor(0);
            equipo.setGolesEnContra(0);
            equipo.setDiferenciaGoles(0);
        }

        /* Sortero de grupos */
        System.out.println("\n=== SORTEO DE GRUPOS DE LA CHAMPIONS LEAGUE ===\n");
        /* Mezclar equipos */
        Collections.shuffle(equipos);
        /* Asignar equipos a grupos */
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
            } else {
                System.out.println("Error: Equipo no asignado a ningun grupo");
            }
        }
        System.out.println("\n=== FIN DEL SORTEO ===\n");
    }

    /* Simular partidos de un grupo */
    private void simularPartidosGrupo(List<Equipo> grupo, String nombreGrupo) {
        if (grupo.size() < 4) return;
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=== PARTIDOS DEL GRUPO " + nombreGrupo + " ===");
        
        // Primera vuelta (partidos de ida)
        for (int i = 0; i < grupo.size(); i++) {
            for (int j = i + 1; j < grupo.size(); j++) {
                Equipo local = grupo.get(i);
                Equipo visitante = grupo.get(j);
                
                System.out.println("\n--- Partido de IDA ---");
                Partido partidoIda = new Partido(local, visitante, new int[]{0, 0}, 0);
                partidoIda.simularPartido(local, visitante, this);
                partidos.add(partidoIda);
                
                System.out.println("\n¿Quieres ver la clasificación del Grupo " + nombreGrupo + "? (s/n)");
                String respuesta = scanner.nextLine().toLowerCase().trim();
                if (respuesta.equals("s")) {
                    mostrarClasificacionGrupo(nombreGrupo, grupo);
                    System.out.println("\nPresiona ENTER para continuar con el siguiente partido...");
                    scanner.nextLine();
                }
            }
        }
        
        // Segunda vuelta (partidos de vuelta)
        for (int i = 0; i < grupo.size(); i++) {
            for (int j = i + 1; j < grupo.size(); j++) {
                Equipo local = grupo.get(j);  // Invertimos local y visitante
                Equipo visitante = grupo.get(i);
                
                System.out.println("\n--- Partido de VUELTA ---");
                Partido partidoVuelta = new Partido(local, visitante, new int[]{0, 0}, 0);
                partidoVuelta.simularPartido(local, visitante, this);
                partidos.add(partidoVuelta);
                
                System.out.println("\n¿Quieres ver la clasificación del Grupo " + nombreGrupo + "? (s/n)");
                String respuesta = scanner.nextLine().toLowerCase().trim();
                if (respuesta.equals("s")) {
                    mostrarClasificacionGrupo(nombreGrupo, grupo);
                    System.out.println("\nPresiona ENTER para continuar con el siguiente partido...");
                    scanner.nextLine();
                }
            }
        }
    }

    /* Jugar grupos */
    public void JugarGrupos() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=== INICIO DE LA FASE DE GRUPOS ===\n");
        
        System.out.println("\n=== GRUPO A ===");
        simularPartidosGrupo(grupoA, "A");
        mostrarClasificacionGrupo("A", grupoA);
        System.out.println("\nPresiona ENTER para continuar con el siguiente grupo...");
        scanner.nextLine();
        
        System.out.println("\n=== GRUPO B ===");
        simularPartidosGrupo(grupoB, "B");
        mostrarClasificacionGrupo("B", grupoB);
        System.out.println("\nPresiona ENTER para continuar con el siguiente grupo...");
        scanner.nextLine();
        
        System.out.println("\n=== GRUPO C ===");
        simularPartidosGrupo(grupoC, "C");
        mostrarClasificacionGrupo("C", grupoC);
        System.out.println("\nPresiona ENTER para continuar con el siguiente grupo...");
        scanner.nextLine();
        
        System.out.println("\n=== GRUPO D ===");
        simularPartidosGrupo(grupoD, "D");
        mostrarClasificacionGrupo("D", grupoD);
        System.out.println("\nPresiona ENTER para continuar con el siguiente grupo...");
        scanner.nextLine();
        
        System.out.println("\n=== GRUPO E ===");
        simularPartidosGrupo(grupoE, "E");
        mostrarClasificacionGrupo("E", grupoE);
        System.out.println("\nPresiona ENTER para continuar con el siguiente grupo...");
        scanner.nextLine();
        
        System.out.println("\n=== GRUPO F ===");
        simularPartidosGrupo(grupoF, "F");
        mostrarClasificacionGrupo("F", grupoF);
        System.out.println("\nPresiona ENTER para continuar con el siguiente grupo...");
        scanner.nextLine();
        
        System.out.println("\n=== GRUPO G ===");
        simularPartidosGrupo(grupoG, "G");
        mostrarClasificacionGrupo("G", grupoG);
        System.out.println("\nPresiona ENTER para continuar con el siguiente grupo...");
        scanner.nextLine();
        
        System.out.println("\n=== GRUPO H ===");
        simularPartidosGrupo(grupoH, "H");
        mostrarClasificacionGrupo("H", grupoH);
        System.out.println("\nPresiona ENTER para ver el resumen final de la fase de grupos...");
        scanner.nextLine();
        
        System.out.println("\n=== RESUMEN FINAL DE LA FASE DE GRUPOS ===");
        VerClasificacion();
        
        System.out.println("\n=== FIN DE LA FASE DE GRUPOS ===\n");
    }

    /* Mostrar clasificacion de un grupo */
    private void mostrarClasificacionGrupo(String nombreGrupo, List<Equipo> grupo) {
        if (grupo.isEmpty()) return;
        
        System.out.println("\n=== GRUPO " + nombreGrupo + " ===");
        System.out.println("Pos  Equipo                  PJ   PG   PE   PP   GF   GC   DG   Pts");
        System.out.println("----------------------------------------------------------------");

        // Ordenar equipos por puntos y diferencia de goles
        Collections.sort(grupo, (e1, e2) -> {
            if (e2.getPuntos() != e1.getPuntos()) {
                return e2.getPuntos() - e1.getPuntos();  // Ordena por puntos de mayor a menor
            }
            return e2.getDiferenciaGoles() - e1.getDiferenciaGoles();  // Si tienen mismos puntos, ordena por diferencia de goles
        });

        /* Mostrar clasificacion */
        for (int i = 0; i < grupo.size(); i++) {
            Equipo e = grupo.get(i);
            int partidosJugados = e.getPartidosJugados();
            int partidosGanados = e.getPuntos() / 3;
            int partidosEmpatados = e.getPuntos() % 3;
            int partidosPerdidos = partidosJugados - partidosGanados - partidosEmpatados;

            /* Formato de la clasificacion */
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

    /* Ver clasificacion */
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

    /* Obtener clasificados de octavos */
    public List<Equipo> getClasificadosOctavos() {
        List<Equipo> clasificados = new ArrayList<>();
        List<Equipo> primeros = new ArrayList<>();
        List<Equipo> segundos = new ArrayList<>();
        
        // Primero separamos los primeros y segundos de cada grupo
        for (List<Equipo> grupo : Arrays.asList(grupoA, grupoB, grupoC, grupoD, grupoE, grupoF, grupoG, grupoH)) {
            if (grupo.size() >= 2) {
                Collections.sort(grupo, (e1, e2) -> {
                    if (e2.getPuntos() != e1.getPuntos()) {
                        return e2.getPuntos() - e1.getPuntos();
                    }
                    return e2.getDiferenciaGoles() - e1.getDiferenciaGoles();
                });
                primeros.add(grupo.get(0));
                segundos.add(grupo.get(1));
            }
        }

        // Reordenamos los segundos para evitar equipos del mismo país
        for (Equipo primero : primeros) {
            clasificados.add(primero);
            // Buscamos un segundo de diferente país
            for (Equipo segundo : segundos) {
                if (!segundo.getPais().equals(primero.getPais())) {
                    clasificados.add(segundo);
                    segundos.remove(segundo);
                    break;
                }
            }
        }
        
        return clasificados;
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
        Fase_De_Grupos other = (Fase_De_Grupos) obj;
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
        if (grupoA == null) {
            if (other.grupoA != null)
                return false;
        } else if (!grupoA.equals(other.grupoA))
            return false;
        if (grupoB == null) {
            if (other.grupoB != null)
                return false;
        } else if (!grupoB.equals(other.grupoB))
            return false;
        if (grupoC == null) {
            if (other.grupoC != null)
                return false;
        } else if (!grupoC.equals(other.grupoC))
            return false;
        if (grupoD == null) {
            if (other.grupoD != null)
                return false;
        } else if (!grupoD.equals(other.grupoD))
            return false;
        if (grupoE == null) {
            if (other.grupoE != null)
                return false;
        } else if (!grupoE.equals(other.grupoE))
            return false;
        if (grupoF == null) {
            if (other.grupoF != null)
                return false;
        } else if (!grupoF.equals(other.grupoF))
            return false;
        if (grupoG == null) {
            if (other.grupoG != null)
                return false;
        } else if (!grupoG.equals(other.grupoG))
            return false;
        if (grupoH == null) {
            if (other.grupoH != null)
                return false;
        } else if (!grupoH.equals(other.grupoH))
            return false;
        return true;
    }

    /* To string */
    @Override
    public String toString() {
        return "Fase_De_Grupos [equipos=" + equipos + ", partidos=" + partidos + ", grupoA=" + grupoA + ", grupoB="
                + grupoB + ", grupoC=" + grupoC + ", grupoD=" + grupoD + ", grupoE=" + grupoE + ", grupoF=" + grupoF
                + ", grupoG=" + grupoG + ", grupoH=" + grupoH + "]";
    }


}