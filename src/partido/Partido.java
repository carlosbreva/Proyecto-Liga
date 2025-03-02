package partido;

import personal.Equipo;
import personal.Jugador;
import personal.Posicion;
import personal.Portero;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import liga.Liga;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Scanner;

public class Partido {
    
    /* Variables */
    private Equipo equipoLocal;
    private Equipo equipoVisitante;
    private int[] resultado;
    private int numeroDeGoles;
    private List<Jugador> tarjetasAmarillasPartido;
    private List<Jugador> tarjetasRojasPartido;

    /* Constructor */
    public Partido(Equipo equipoLocal, Equipo equipoVisitante, int[] resultado, int numeroDeGoles) {
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.resultado = resultado;
        this.numeroDeGoles = numeroDeGoles;
        this.tarjetasAmarillasPartido = new ArrayList<>();
        this.tarjetasRojasPartido = new ArrayList<>();
    }

    /* Getters y setters */
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


    public List<Jugador> getTarjetasAmarillasPartido() {
        return tarjetasAmarillasPartido;
    }

    public void setTarjetasAmarillasPartido(List<Jugador> tarjetasAmarillasPartido) {
        this.tarjetasAmarillasPartido = tarjetasAmarillasPartido;
    }

    public List<Jugador> getTarjetasRojasPartido() {
        return tarjetasRojasPartido;
    }

    public void setTarjetasRojasPartido(List<Jugador> tarjetasRojasPartido) {
        this.tarjetasRojasPartido = tarjetasRojasPartido;
    }

    /* Metodos*/

    /* Seleccionar 11 titulares */
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
        } else {
            System.out.println("No hay porteros en el equipo " + equipo.getNombre());
        }


        // Seleccionar 4 defensas al azar
        List<Jugador> defensas = new ArrayList<>();
        for (Jugador j : equipo.getJugadores()) {
            if (j.getPosicion() == Posicion.DEFENSA) {
                defensas.add(j);
            }
        }
        if (defensas.isEmpty()) {
            System.out.println("No hay defensas en el equipo " + equipo.getNombre());
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
        if (mediocentros.isEmpty()) {
            System.out.println("No hay mediocentros en el equipo " + equipo.getNombre());
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
        if (delanteros.isEmpty()) {
            System.out.println("No hay delanteros en el equipo " + equipo.getNombre());
        }
        for (int i = 0; i < 3 && !delanteros.isEmpty(); i++) {
            int index = random.nextInt(delanteros.size());
            titular.add(delanteros.get(index));
            delanteros.remove(index); // Evitar seleccionar el mismo jugador dos veces
        }

        return titular;
    }

    /* Procesar equipo local */
    private void procesarEquipoLocal(List<Jugador> titularesLocal, List<Jugador> jugadoresExpulsados, 
                                   double[] probabilidadLocal, int[] golesLocal, int[] paradasVisitante, 
                                   Portero porteroVisitanteFinal, Random random) {
        // Comprobar tarjetas
        for (Jugador jugador : titularesLocal) {
            if (!jugadoresExpulsados.contains(jugador)) {
                if (random.nextDouble() < 0.005) { // 0.5% de probabilidad de tarjeta amarilla
                    jugador.setTarjetasAmarillas(jugador.getTarjetasAmarillas() + 1);
                    tarjetasAmarillasPartido.add(jugador);
                    System.out.println("¡TARJETA AMARILLA! para " + jugador.getNombre() + " del " + equipoLocal.getNombre());
                    if (jugador.getTarjetasAmarillas() == 2) {
                        jugador.setTarjetasRojas(jugador.getTarjetasRojas() + 1);
                        tarjetasRojasPartido.add(jugador);
                        jugadoresExpulsados.add(jugador);
                        System.out.println("¡EXPULSIÓN! " + jugador.getNombre() + " del " + equipoLocal.getNombre() + " por doble amarilla");
                    }
                } else if (random.nextDouble() < 0.001) { // 0.1% de probabilidad de roja directa
                    jugador.setTarjetasRojas(jugador.getTarjetasRojas() + 1);
                    tarjetasRojasPartido.add(jugador);
                    jugadoresExpulsados.add(jugador);
                    System.out.println("¡TARJETA ROJA DIRECTA! para " + jugador.getNombre() + " del " + equipoLocal.getNombre());
                }
            }
        }

        // Intento de gol del equipo local
        if (probabilidadLocal[0] > random.nextDouble() * 1.0) {
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
                int jugadoresLocalesExpulsados = 0;
                for (Jugador j : titularesLocal) {
                    if (jugadoresExpulsados.contains(j)) {
                        jugadoresLocalesExpulsados++;
                        continue;
                    }
                    if (j.getPosicion() == Posicion.DELANTERO || j.getPosicion() == Posicion.MEDIOCENTRO) {
                        posiblesGoleadores.add(j);
                    }
                }

                // Reducir probabilidad de gol según jugadores expulsados
                if (jugadoresLocalesExpulsados > 0) {
                    probabilidadLocal[0] *= (11.0 - jugadoresLocalesExpulsados) / 11.0;
                }

                if (!posiblesGoleadores.isEmpty()) {
                    Jugador goleador = posiblesGoleadores.get(random.nextInt(posiblesGoleadores.size()));
                    golesLocal[0]++;
                    goleador.setGolesAnotados(goleador.getGolesAnotados() + 1);
                    System.out.println("¡GOL! del " + equipoLocal.getNombre() + " marcado por " + goleador.getNombre());
                }
            }
        }
    }

    /* Procesar equipo visitante */
    private void procesarEquipoVisitante(List<Jugador> titularesVisitante, List<Jugador> jugadoresExpulsados, 
                                       double[] probabilidadVisitante, int[] golesVisitante, int[] paradasLocal, 
                                       Portero porteroLocalFinal, Random random) {
        // Comprobar tarjetas
        for (Jugador jugador : titularesVisitante) {
            if (!jugadoresExpulsados.contains(jugador)) {
                if (random.nextDouble() < 0.005) { // 0.5% de probabilidad de tarjeta amarilla
                    jugador.setTarjetasAmarillas(jugador.getTarjetasAmarillas() + 1);
                    tarjetasAmarillasPartido.add(jugador);
                    System.out.println("¡TARJETA AMARILLA! para " + jugador.getNombre() + " del " + equipoVisitante.getNombre());
                    if (jugador.getTarjetasAmarillas() == 2) {
                        jugador.setTarjetasRojas(jugador.getTarjetasRojas() + 1);
                        tarjetasRojasPartido.add(jugador);
                        jugadoresExpulsados.add(jugador);
                        System.out.println("¡EXPULSIÓN! " + jugador.getNombre() + " del " + equipoVisitante.getNombre() + " por doble amarilla");
                    }
                } else if (random.nextDouble() < 0.001) { // 0.1% de probabilidad de roja directa
                    jugador.setTarjetasRojas(jugador.getTarjetasRojas() + 1);
                    tarjetasRojasPartido.add(jugador);
                    jugadoresExpulsados.add(jugador);
                    System.out.println("¡TARJETA ROJA DIRECTA! para " + jugador.getNombre() + " del " + equipoVisitante.getNombre());
                }
            }
        }

        // Intento de gol del equipo visitante
        if (probabilidadVisitante[0] > random.nextDouble() * 1.0) {
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
                int jugadoresVisitantesExpulsados = 0;
                for (Jugador j : titularesVisitante) {
                    if (jugadoresExpulsados.contains(j)) {
                        jugadoresVisitantesExpulsados++;
                        continue;
                    }
                    if (j.getPosicion() == Posicion.DELANTERO || j.getPosicion() == Posicion.MEDIOCENTRO) {
                        posiblesGoleadores.add(j);
                    }
                }

                // Reducir probabilidad de gol según jugadores expulsados
                if (jugadoresVisitantesExpulsados > 0) {
                    probabilidadVisitante[0] *= (11.0 - jugadoresVisitantesExpulsados) / 11.0;
                }

                if (!posiblesGoleadores.isEmpty()) {
                    Jugador goleador = posiblesGoleadores.get(random.nextInt(posiblesGoleadores.size()));
                    golesVisitante[0]++;
                    goleador.setGolesAnotados(goleador.getGolesAnotados() + 1);
                    System.out.println("¡GOL! del " + equipoVisitante.getNombre() + " marcado por " + goleador.getNombre());
                }
            }
        }
    }

    /* Calcular probabilidad de gol del equipo local */
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

    /* Calcular probabilidad de gol del equipo visitante */
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

    /* Método para validar que un equipo tenga suficientes jugadores */
    private boolean validarEquipo(Equipo equipo) {
        int porteros = 0;
        int defensas = 0;
        int mediocentros = 0;
        int delanteros = 0;

        for (Jugador j : equipo.getJugadores()) {
            switch (j.getPosicion()) {
                case PORTERO: porteros++; break;
                case DEFENSA: defensas++; break;
                case MEDIOCENTRO: mediocentros++; break;
                case DELANTERO: delanteros++; break;
            }
        }

        return porteros >= 1 && defensas >= 4 && mediocentros >= 3 && delanteros >= 3;
    }


    /* Simular partido */
    public void simularPartido(Equipo equipoLocal, Equipo equipoVisitante, Object competicion) { 
        // Validar que los equipos tengan suficientes jugadores
        if (!validarEquipo(equipoLocal) || !validarEquipo(equipoVisitante)) {
            System.out.println("No se puede jugar el partido: uno o ambos equipos no tienen suficientes jugadores");
            return;
        }

        System.out.println("\n=== INICIO DEL PARTIDO ===");
        System.out.println(equipoLocal.getNombre() + " vs " + equipoVisitante.getNombre());

        // Seleccionar 11 titulares para cada equipo
        List<Jugador> titularesLocal = seleccionar11Titular(equipoLocal);
        List<Jugador> titularesVisitante = seleccionar11Titular(equipoVisitante);

        if (titularesLocal.size() < 11 || titularesVisitante.size() < 11) {
            System.out.println("No se puede jugar el partido: uno o ambos equipos no pueden formar un 11 inicial completo");
            return;
        }

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

        /* Variables del partido */
        final int[] golesLocal = {0};
        final int[] golesVisitante = {0};
        final int[] paradasLocal = {0};
        final int[] paradasVisitante = {0};
        final List<Jugador> jugadoresExpulsados = new ArrayList<>();
        final double[] probabilidadLocal = {calcularProbabilidadLocal(equipoLocal)};
        final double[] probabilidadVisitante = {calcularProbabilidadVisitante(equipoVisitante)};
        Random random = new Random();
        Timer timer = new Timer();
        
        /* Obtener los porteros titulares */
        Portero porteroLocal = (Portero)titularesLocal.get(0);
        Portero porteroVisitante = (Portero)titularesVisitante.get(0);
        
        final Portero porteroLocalFinal = porteroLocal;
        final Portero porteroVisitanteFinal = porteroVisitante;
        
        /* Tarea del partido */
        TimerTask task = new TimerTask() {
            private int loQueLleva = 0;
            final int tiempoMax = 30; // Aumentado a 30 ciclos (3 segundos)
            final int intervaloEventos = 3; // Eventos cada 0.3 segundos

            @Override
            public void run() {
                if (loQueLleva < tiempoMax) {
                    if (loQueLleva % intervaloEventos == 0) {
                        procesarEquipoLocal(titularesLocal, jugadoresExpulsados, probabilidadLocal, 
                                          golesLocal, paradasVisitante, porteroVisitanteFinal, random);
                        
                        procesarEquipoVisitante(titularesVisitante, jugadoresExpulsados, probabilidadVisitante, 
                                              golesVisitante, paradasLocal, porteroLocalFinal, random);
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
                    if (!tarjetasAmarillasPartido.isEmpty() || !tarjetasRojasPartido.isEmpty()) {
                        System.out.println(equipoLocal.getNombre() + ":");
                        for (Jugador j : titularesLocal) {
                            if (j.getTarjetasAmarillas() > 0 || j.getTarjetasRojas() > 0) {
                                System.out.println("- " + j.getNombre() + ": " + 
                                    (j.getTarjetasAmarillas() > 0 ? j.getTarjetasAmarillas() + " amarilla(s) " : "") +
                                    (j.getTarjetasRojas() > 0 ? j.getTarjetasRojas() + " roja(s)" : ""));
                            }
                        }
                        System.out.println(equipoVisitante.getNombre() + ":");
                        for (Jugador j : titularesVisitante) {
                            if (j.getTarjetasAmarillas() > 0 || j.getTarjetasRojas() > 0) {
                                System.out.println("- " + j.getNombre() + ": " + 
                                    (j.getTarjetasAmarillas() > 0 ? j.getTarjetasAmarillas() + " amarilla(s) " : "") +
                                    (j.getTarjetasRojas() > 0 ? j.getTarjetasRojas() + " roja(s)" : ""));
                            }
                        }
                    } else {
                        System.out.println("No hubo tarjetas en este partido");
                    }
                    
                    System.out.println("==========================================\n");
                }
            }
        };

        timer.scheduleAtFixedRate(task, 0, 100); // 100ms entre eventos

        try {
            // Esperar a que termine el partido (3 segundos + margen)
            Thread.sleep(3500);
            
            Scanner scanner = new Scanner(System.in);
            
            if (competicion instanceof Liga) {
                mostrarClasificacionLiga((Liga) competicion, scanner);
            } else if (competicion instanceof Champions.Fase_De_Grupos) {
                mostrarClasificacionChampions((Champions.Fase_De_Grupos) competicion, scanner);
            }
            
            System.out.println("\nPresiona ENTER para continuar...");
            scanner.nextLine();
            
        } catch (InterruptedException e) {
            System.err.println("Error: El partido fue interrumpido - " + e.getMessage());
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            System.err.println("Error inesperado durante el partido: " + e.getMessage());
            e.printStackTrace();
        } finally {
            timer.cancel();  // Aseguramos que el timer se cancele incluso si hay una excepción
        }
        
    }

    /* Método para mostrar clasificación de Liga */
    private void mostrarClasificacionLiga(Liga liga, Scanner scanner) {
        System.out.println("\n¿Quieres ver la clasificación de la liga? (s/n)");
        String respuesta = scanner.nextLine().toLowerCase().trim();
        if (respuesta.equals("s")) {
            liga.VerClasificacion();
        } else if (!respuesta.equals("n")) {
            System.out.println("Entrada no válida. Se esperaba 's' para sí o 'n' para no.");
        }
    }

    /* Método para mostrar clasificación de Champions */
    private void mostrarClasificacionChampions(Champions.Fase_De_Grupos faseGrupos, Scanner scanner) {
        System.out.println("\n¿Quieres ver la clasificación de todos los grupos? (s/n)");
        String respuesta = scanner.nextLine().toLowerCase().trim();
        if (respuesta.equals("s")) {
            faseGrupos.VerClasificacion();
        } else if (!respuesta.equals("n")) {
            System.out.println("Entrada no válida. Se esperaba 's' para sí o 'n' para no.");
        }
    }

    /* Equals y toString */

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Partido other = (Partido) obj;
        if (equipoLocal == null) {
            if (other.equipoLocal != null)
                return false;
        } else if (!equipoLocal.equals(other.equipoLocal))
            return false;
        if (equipoVisitante == null) {
            if (other.equipoVisitante != null)
                return false;
        } else if (!equipoVisitante.equals(other.equipoVisitante))
            return false;
        if (!Arrays.equals(resultado, other.resultado))
            return false;
        if (numeroDeGoles != other.numeroDeGoles)
            return false;
        if (tarjetasAmarillasPartido == null) {
            if (other.tarjetasAmarillasPartido != null)
                return false;
        } else if (!tarjetasAmarillasPartido.equals(other.tarjetasAmarillasPartido))
            return false;
        if (tarjetasRojasPartido == null) {
            if (other.tarjetasRojasPartido != null)
                return false;
        } else if (!tarjetasRojasPartido.equals(other.tarjetasRojasPartido))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Partido [equipoLocal=" + equipoLocal + ", equipoVisitante=" + equipoVisitante + ", resultado="
                + Arrays.toString(resultado) + ", numeroDeGoles=" + numeroDeGoles + ", tarjetasAmarillasPartido="
                + tarjetasAmarillasPartido + ", tarjetasRojasPartido=" + tarjetasRojasPartido + "]";
    }


}

