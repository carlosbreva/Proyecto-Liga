package personal;

import java.util.Objects;

public class Entrenador extends Persona {
    private int añosDeExperiencia;
    private Equipo equipo;

    public Entrenador(String nombre, int edad, int añosDeExperiencia, Equipo equipo) {
        super(edad);
        this.añosDeExperiencia = añosDeExperiencia;
        this.equipo = equipo;
    }

    public int getAñosDeExperiencia() {
        return añosDeExperiencia;
    }
    public void setAñosDeExperiencia(int añosDeExperiencia) {
        this.añosDeExperiencia = añosDeExperiencia;
    }
    public Equipo getEquipo() {
        return equipo;
    }
    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Entrenador that = (Entrenador) o;
        return añosDeExperiencia == that.añosDeExperiencia && Objects.equals(equipo, that.equipo);
    }


}

