package personal;

import java.util.Objects;

public class Persona {
    private int edad;
    private String nombre;

    public Persona(int edad, String nombre) {
        this.edad = edad;
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return edad == persona.edad && Objects.equals(nombre, persona.nombre);
    }

    @Override
    public String toString() {
        return "Persona{" +
                "edad=" + edad +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
    
