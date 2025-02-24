package liga;

import personal.Equipo;
import personal.Jugador;
import personal.Posicion;
import personal.Portero;
import java.util.Arrays;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import liga.Liga;
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
    private List<Jugador> seleccionar11Titular(Equipo equipo) {
        List<Jugador> titular = new ArrayList<>();
        Random random = new Random();
        
        // Seleccionar 1 portero al azar
        List<Jugador> porteros = new ArrayList<>();
        for (Jugador j : equipo.getJugadores()) {
            if (j.getPosicion() == Posicion.PORTERO) {
                porteros.add(j);
            }
        }
        if (!porteros.isEmpty()) {
            titular.add(porteros.get(random.nextInt(porteros.size())));
        }

        // Seleccionar 4 defensas al azar
        List<Jugador> defensas = new ArrayList<>();
        for (Jugador j : equipo.getJugadores()) {
            if (j.getPosicion() == Posicion.DEFENSA) {
                defensas.add(j);
            }
        }
        for (int i = 0; i < 4 && !defensas.isEmpty(); i++) {
            int index = random.nextInt(defensas.size());
            titular.add(defensas.get(index));
            defensas.remove(index); // Evitar seleccionar el mismo jugador dos veces
        }

        // Seleccionar 3 mediocentros al azar
        List<Jugador> mediocentros = new ArrayList<>();
        for (Jugador j : equipo.getJugadores()) {
            if (j.getPosicion() == Posicion.MEDIOCENTRO) {
                mediocentros.add(j);
            }
        }
        for (int i = 0; i < 3 && !mediocentros.isEmpty(); i++) {
            int index = random.nextInt(mediocentros.size());
            titular.add(mediocentros.get(index));
            mediocentros.remove(index); // Evitar seleccionar el mismo jugador dos veces
        }

        // Seleccionar 3 delanteros al azar
        List<Jugador> delanteros = new ArrayList<>();
        for (Jugador j : equipo.getJugadores()) {
            if (j.getPosicion() == Posicion.DELANTERO) {
                delanteros.add(j);
            }
        }
        for (int i = 0; i < 3 && !delanteros.isEmpty(); i++) {
            int index = random.nextInt(delanteros.size());
            titular.add(delanteros.get(index));
            delanteros.remove(index); // Evitar seleccionar el mismo jugador dos veces
        }

        return titular;
    }

    public void simularPartido(Equipo equipoLocal, Equipo equipoVisitante) { 
        System.out.println("\n=== INICIO DEL PARTIDO ===");
        System.out.println(equipoLocal.getNombre() + " vs " + equipoVisitante.getNombre());

        // Seleccionar 11 titulares para cada equipo
        List<Jugador> titularesLocal = seleccionar11Titular(equipoLocal);
        List<Jugador> titularesVisitante = seleccionar11Titular(equipoVisitante);

        // Mostrar alineaciones
        System.out.println("\nAlineación " + equipoLocal.getNombre() + ":");
        for (Jugador j : titularesLocal) {
            System.out.println("- " + j.getNombre() + " (" + j.getPosicion() + ")");
        }

        System.out.println("\nAlineación " + equipoVisitante.getNombre() + ":");
        for (Jugador j : titularesVisitante) {
            System.out.println("- " + j.getNombre() + " (" + j.getPosicion() + ")");
        }
        System.out.println();

        final int[] golesLocal = {0};
        final int[] golesVisitante = {0};
        final int[] paradasLocal = {0};
        final int[] paradasVisitante = {0};
        final List<Jugador> jugadoresExpulsados = new ArrayList<>();
        double probabilidadLocal = calcularProbabilidadLocal(equipoLocal);
        double probabilidadVisitante = calcularProbabilidadVisitante(equipoVisitante);
        Random random = new Random();
        Timer timer = new Timer();
        
        // Obtener los porteros titulares
        Portero porteroLocal = (Portero)titularesLocal.get(0);
        Portero porteroVisitante = (Portero)titularesVisitante.get(0);
        
        final Portero porteroLocalFinal = porteroLocal;
        final Portero porteroVisitanteFinal = porteroVisitante;
        
        TimerTask task = new TimerTask() {
            private int loQueLleva = 0;
            final int tiempoMax = (int) (0.3/0.05); // Reducido a 0.3 segundos total

            @Override
            public void run() {
                if (loQueLleva < tiempoMax) {
                    // Comprobar tarjetas para equipo local
                    for (Jugador j : titularesLocal) {
                        if (!jugadoresExpulsados.contains(j)) {
                            // 5% de probabilidad de tarjeta amarilla
                            if (random.nextDouble() < 0.05) {
                                j.setTarjetasAmarillas(j.getTarjetasAmarillas() + 1);
                                System.out.println("¡TARJETA AMARILLA! para " + j.getNombre() + " del " + equipoLocal.getNombre());
                                
                                // Si tiene 2 amarillas, expulsión
                                if (j.getTarjetasAmarillas() == 2) {
                                    System.out.println("¡EXPULSIÓN! " + j.getNombre() + " del " + equipoLocal.getNombre() + " por doble amarilla");
                                    jugadoresExpulsados.add(j);
                                }
                            }
                            // 1% de probabilidad de roja directa
                            else if (random.nextDouble() < 0.01) {
                                j.setTarjetasRojas(j.getTarjetasRojas() + 1);
                                System.out.println("¡TARJETA ROJA DIRECTA! para " + j.getNombre() + " del " + equipoLocal.getNombre());
                                jugadoresExpulsados.add(j);
                            }
                        }
                    }
                    
                    // Comprobar tarjetas para equipo visitante
                    for (Jugador j : titularesVisitante) {
                        if (!jugadoresExpulsados.contains(j)) {
                            // 5% de probabilidad de tarjeta amarilla
                            if (random.nextDouble() < 0.05) {
                                j.setTarjetasAmarillas(j.getTarjetasAmarillas() + 1);
                                System.out.println("¡TARJETA AMARILLA! para " + j.getNombre() + " del " + equipoVisitante.getNombre());
                                
                                // Si tiene 2 amarillas, expulsión
                                if (j.getTarjetasAmarillas() == 2) {
                                    System.out.println("¡EXPULSIÓN! " + j.getNombre() + " del " + equipoVisitante.getNombre() + " por doble amarilla");
                                    jugadoresExpulsados.add(j);
                                }
                            }
                            // 1% de probabilidad de roja directa
                            else if (random.nextDouble() < 0.01) {
                                j.setTarjetasRojas(j.getTarjetasRojas() + 1);
                                System.out.println("¡TARJETA ROJA DIRECTA! para " + j.getNombre() + " del " + equipoVisitante.getNombre());
                                jugadoresExpulsados.add(j);
                            }
                        }
                    }

                    // Intento de gol del equipo local
                    if (probabilidadLocal > random.nextDouble() * 1.0) {
                        // Comprobar si el portero visitante realiza una parada
                        boolean parada = false;
                        if (porteroVisitanteFinal != null) {
                            double probabilidadParada = (porteroVisitanteFinal.getReflejos() + 
                                                       porteroVisitanteFinal.getEstirada() + 
                                                       porteroVisitanteFinal.getPosicionamiento()) / 300.0;
                            if (probabilidadParada > random.nextDouble()) {
                                paradasVisitante[0]++;
                                porteroVisitanteFinal.setNumeroDeParadas(porteroVisitanteFinal.getNumeroDeParadas() + 1);
                                System.out.println("¡PARADA ESPECTACULAR! de " + porteroVisitanteFinal.getNombre() + " del " + equipoVisitante.getNombre());
                                parada = true;
                            }
                        }
                        
                        if (!parada) {
                            List<Jugador> posiblesGoleadores = new ArrayList<>();
                            for (Jugador j : titularesLocal) {
                                if (j.getPosicion() == Posicion.DELANTERO || j.getPosicion() == Posicion.MEDIOCENTRO) {
                                    if (!jugadoresExpulsados.contains(j)) {
                                        posiblesGoleadores.add(j);
                                    }
                                }
                            }
                            if (!posiblesGoleadores.isEmpty()) {
                                Jugador goleador = posiblesGoleadores.get(random.nextInt(posiblesGoleadores.size()));
                                golesLocal[0]++;
                                System.out.println("¡GOL! del " + equipoLocal.getNombre() + " marcado por " + goleador.getNombre());
                            }
                        }
                    }

                    // Intento de gol del equipo visitante
                    if (probabilidadVisitante > random.nextDouble() * 1.0) {
                        // Comprobar si el portero local realiza una parada
                        boolean parada = false;
                        if (porteroLocalFinal != null) {
                            double probabilidadParada = (porteroLocalFinal.getReflejos() + 
                                                       porteroLocalFinal.getEstirada() + 
                                                       porteroLocalFinal.getPosicionamiento()) / 300.0;
                            if (probabilidadParada > random.nextDouble()) {
                                paradasLocal[0]++;
                                porteroLocalFinal.setNumeroDeParadas(porteroLocalFinal.getNumeroDeParadas() + 1);
                                System.out.println("¡PARADA ESPECTACULAR! de " + porteroLocalFinal.getNombre() + " del " + equipoLocal.getNombre());
                                parada = true;
                            }
                        }
                        
                        if (!parada) {
                            List<Jugador> posiblesGoleadores = new ArrayList<>();
                            for (Jugador j : titularesVisitante) {
                                if (j.getPosicion() == Posicion.DELANTERO || j.getPosicion() == Posicion.MEDIOCENTRO) {
                                    if (!jugadoresExpulsados.contains(j)) {
                                        posiblesGoleadores.add(j);
                                    }
                                }
                            }
                            if (!posiblesGoleadores.isEmpty()) {
                                Jugador goleador = posiblesGoleadores.get(random.nextInt(posiblesGoleadores.size()));
                                golesVisitante[0]++;
                                System.out.println("¡GOL! del " + equipoVisitante.getNombre() + " marcado por " + goleador.getNombre());
                            }
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
                    } else if (golesLocal[0] == golesVisitante[0]) {
                        equipoLocal.setPuntos(equipoLocal.getPuntos() + 1);
                        equipoVisitante.setPuntos(equipoVisitante.getPuntos() + 1);
                    }
                    
                    System.out.println("\n=== FINAL DEL PARTIDO ===");
                    System.out.println(equipoLocal.getNombre() + " " + golesLocal[0] + " - " + golesVisitante[0] + " " + equipoVisitante.getNombre());
                    equipoLocal.setPartidosJugados(equipoLocal.getPartidosJugados() + 1);
                    equipoVisitante.setPartidosJugados(equipoVisitante.getPartidosJugados() + 1);

                    
                    
                    if (porteroLocalFinal != null) {
                        System.out.println("Paradas de " + porteroLocalFinal.getNombre() + ": " + paradasLocal[0]);
                    }
                    if (porteroVisitanteFinal != null) {
                        System.out.println("Paradas de " + porteroVisitanteFinal.getNombre() + ": " + paradasVisitante[0]);
                    }
                    
                    // Mostrar resumen de tarjetas
                    System.out.println("\nRESUMEN DE TARJETAS:");
                    System.out.println(equipoLocal.getNombre() + ":");
                    for (Jugador j : titularesLocal) {
                        if (j.getTarjetasAmarillas() > 0 || j.getTarjetasRojas() > 0) {
                            System.out.println("- " + j.getNombre() + ": " + 
                                             (j.getTarjetasAmarillas() > 0 ? j.getTarjetasAmarillas() + " amarillas" : "") +
                                             (j.getTarjetasRojas() > 0 ? " " + j.getTarjetasRojas() + " rojas" : ""));
                        }
                    }
                    System.out.println(equipoVisitante.getNombre() + ":");
                    for (Jugador j : titularesVisitante) {
                        if (j.getTarjetasAmarillas() > 0 || j.getTarjetasRojas() > 0) {
                            System.out.println("- " + j.getNombre() + ": " + 
                                             (j.getTarjetasAmarillas() > 0 ? j.getTarjetasAmarillas() + " amarillas" : "") +
                                             (j.getTarjetasRojas() > 0 ? " " + j.getTarjetasRojas() + " rojas" : ""));
                        }
                    }
                    
                    System.out.println("==========================================\n");
                }
            }
        };

        timer.scheduleAtFixedRate(task, 0, 50); // 50ms entre eventos

        try {
            // Esperar a que termine el partido (0.4 segundos)
            Thread.sleep(400);
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

