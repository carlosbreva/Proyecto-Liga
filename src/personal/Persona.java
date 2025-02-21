package personal;


public class Persona {
    /* Variables*/
    private int edad;
    private String nombre;




    /* Constructor */
    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public Persona(String nombre) {
        this.nombre = nombre;
    }

    /* Getters y Setters */
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





    /* Equals y ToString */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Persona jugador = (Persona) o;
        return edad == jugador.edad && nombre == jugador.nombre;
    }

    @Override
    public String toString() {
        return "nombre=" + nombre +
                ", edad=" + edad;
    }
}
