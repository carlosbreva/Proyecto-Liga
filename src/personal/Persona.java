package personal;


public class Persona {
    /* Variables*/
    private int edad;
    private String nombre;
    private Paises pais;



    /* Constructor */
    public Persona(String nombre, int edad, Paises pais) {
        this.nombre = nombre;
        this.edad = edad;
        this.pais = pais;
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

    public Paises getPais() {
        return pais;
    }

    public void setPais(Paises pais) {
        this.pais = pais;
    }


    /* Equals y ToString */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Persona jugador = (Persona) o;
        return edad == jugador.edad && nombre == jugador.nombre && pais == jugador.pais;
    }

    @Override
    public String toString() {
        return "nombre=" + nombre +
                ", edad=" + edad +
                ", pais=" + pais;
    }
}
