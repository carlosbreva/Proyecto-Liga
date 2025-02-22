package liga;

import personal.Equipo;
import personal.Jugador;
import personal.Posicion;
import java.util.Arrays;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class Partido {
    // Variables declaradas
    private Equipo equipoLocal;
    private Equipo equipoVisitante;
    private int[] resultado;
    private int numeroDeGoles;

    // Constructor
    public Partido(Equipo equipoLocal, Equipo equipoVisitante, int[] resultado, int numeroDeGoles) {
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.resultado = resultado;
        this.numeroDeGoles = numeroDeGoles;
    }
    public Partido(){

    }

    // Getters y setters
    public Equipo getEquipoLocal() {
        return equipoLocal;
    }

    public void setEquipoLocal(Equipo equipoLocal) {
        this.equipoLocal = equipoLocal;
    }

    public Equipo getEquipoVisitante() {
        return equipoVisitante;
    }

    public void setEquipoVisitante(Equipo equipoVisitante) {
        this.equipoVisitante = equipoVisitante;
    }

    public int[] getResultado() {
        return resultado;
    }

    public void setResultado(int[] resultado) {
        this.resultado = resultado;
    }

    public int getNumeroDeGoles() {
        return numeroDeGoles;
    }

    public void setNumeroDeGoles(int numeroDeGoles) {
        this.numeroDeGoles = numeroDeGoles;
    }


    /* Metodos y funciones */
    public void simularPartido(Equipo equipoLocal, Equipo equipoVisitante, List<Jugador> jugadoresEquipo) { 
        System.out.println("\n=== INICIO DEL PARTIDO ===");
        System.out.println(equipoLocal.getNombre() + " vs " + equipoVisitante.getNombre());
        final int[] golesLocal = {0};
        final int[] golesVisitante = {0};
        double probabilidadLocal = calcularProbabilidadLocal(equipoLocal);
        double probabilidadVisitante = calcularProbabilidadVisitante(equipoVisitante);
        Random random = new Random();
        Timer timer = new Timer();
        
        TimerTask task = new TimerTask() {
            private int loQueLleva = 0;
            final int tiempoMax = (int) (3/0.35);
            
            @Override
            public void run() {
                if (loQueLleva < tiempoMax) {
                    if (probabilidadLocal > random.nextDouble() * 1.0) {
                        // Seleccionar un delantero o mediocentro aleatorio del equipo local
                        List<Jugador> posiblesGoleadores = new ArrayList<>();
                        for (Jugador j : equipoLocal.getJugadores()) {
                            if (j.getPosicion() == Posicion.DELANTERO || j.getPosicion() == Posicion.MEDIOCENTRO) {
                                posiblesGoleadores.add(j);
                            }
                        }
                        if (!posiblesGoleadores.isEmpty()) {
                            Jugador goleador = posiblesGoleadores.get(random.nextInt(posiblesGoleadores.size()));
                            golesLocal[0]++;
                            System.out.println("¡GOL! del " + equipoLocal.getNombre() + " marcado por " + goleador.getNombre());
                        }
                    }
                    if (probabilidadVisitante > random.nextDouble() * 1.0) {
                        // Seleccionar un delantero o mediocentro aleatorio del equipo visitante
                        List<Jugador> posiblesGoleadores = new ArrayList<>();
                        for (Jugador j : equipoVisitante.getJugadores()) {
                            if (j.getPosicion() == Posicion.DELANTERO || j.getPosicion() == Posicion.MEDIOCENTRO) {
                                posiblesGoleadores.add(j);
                            }
                        }
                        if (!posiblesGoleadores.isEmpty()) {
                            Jugador goleador = posiblesGoleadores.get(random.nextInt(posiblesGoleadores.size()));
                            golesVisitante[0]++;
                            System.out.println("¡GOL! del " + equipoVisitante.getNombre() + " marcado por " + goleador.getNombre());
                        }
                    }
                    loQueLleva++;
                } else {
                    timer.cancel();
                    // Actualizar resultado del partido
                    resultado = new int[]{golesLocal[0], golesVisitante[0]};
                    numeroDeGoles = golesLocal[0] + golesVisitante[0];
                    
                    // Actualizar estadísticas de los equipos
                    equipoLocal.setGolesAfavor(equipoLocal.getGolesAfavor() + golesLocal[0]);
                    equipoLocal.setGolesEnContra(equipoLocal.getGolesEnContra() + golesVisitante[0]);
                    equipoLocal.setDiferenciaGoles(equipoLocal.getGolesAfavor() - equipoLocal.getGolesEnContra());
                    
                    equipoVisitante.setGolesAfavor(equipoVisitante.getGolesAfavor() + golesVisitante[0]);
                    equipoVisitante.setGolesEnContra(equipoVisitante.getGolesEnContra() + golesLocal[0]);
                    equipoVisitante.setDiferenciaGoles(equipoVisitante.getGolesAfavor() - equipoVisitante.getGolesEnContra());
                    
                    // Actualizar puntos
                    if (golesLocal[0] > golesVisitante[0]) {
                        equipoLocal.setPuntos(equipoLocal.getPuntos() + 3);
                    } else if (golesVisitante[0] > golesLocal[0]) {
                        equipoVisitante.setPuntos(equipoVisitante.getPuntos() + 3);
                    } else {
                        equipoLocal.setPuntos(equipoLocal.getPuntos() + 1);
                        equipoVisitante.setPuntos(equipoVisitante.getPuntos() + 1);
                    }
                    
                    System.out.println("\n=== FINAL DEL PARTIDO ===");
                    System.out.println(equipoLocal.getNombre() + " " + golesLocal[0] + " - " + golesVisitante[0] + " " + equipoVisitante.getNombre());
                    System.out.println("==========================================\n");
                }
            }
        };

        timer.scheduleAtFixedRate(task, 0, 350);

        try {
            // Esperar a que termine el partido (aproximadamente 3 segundos)
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private double calcularProbabilidadLocal(Equipo equipo) {
        switch (equipo.getMediaStatsEquipo()) {
            case 50: return 0.5;
            case 60: return 0.6;
            case 70: return 0.7;
            case 80: return 0.8;
            case 90: return 0.9;
            default: return 0.5;
        }
    }

    private double calcularProbabilidadVisitante(Equipo equipo) {
        switch (equipo.getMediaStatsEquipo()) {
            case 50: return 0.4;
            case 60: return 0.5;
            case 70: return 0.6;
            case 80: return 0.7;
            case 90: return 0.8;
            default: return 0.4;
        }
    }

    // Método equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Partido partido = (Partido) o;
        return numeroDeGoles == partido.numeroDeGoles &&
                Objects.equals(equipoLocal, partido.equipoLocal) &&
                Objects.equals(equipoVisitante, partido.equipoVisitante) &&
                Arrays.equals(resultado, partido.resultado);
    }

    // Método toString
    @Override
    public String toString() {
        return "Partido{" +
                "equipoLocal=" + equipoLocal +
                ", equipoVisitante=" + equipoVisitante +
                ", resultado=" + Arrays.toString(resultado) +
                ", numeroDeGoles=" + numeroDeGoles +
                '}';
    }
}

