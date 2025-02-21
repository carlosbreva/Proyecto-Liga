package personal;

import java.util.Objects;

public class Portero extends Persona {
    private int numeroDeParadas;
    private int estirada;
    private int agarre;
    private int saqueLargo;
    private int reflejos;
    private int posicionamiento;

    public Portero(String nombre, int edad, int numeroDeParadas, int estirada, int agarre, int saqueLargo, int reflejos, int posicionamiento) {
        super(nombre, edad);
        this.numeroDeParadas = numeroDeParadas;
        this.estirada = estirada;
        this.agarre = agarre;
        this.saqueLargo = saqueLargo;
        this.reflejos = reflejos;
        this.posicionamiento = posicionamiento;
    }

    public int getNumeroDeParadas() {
        return numeroDeParadas;
    }

    public void setNumeroDeParadas(int numeroDeParadas) {
        this.numeroDeParadas = numeroDeParadas;
    }

    public int getEstirada() {
        return estirada;
    }

    public void setEstirada(int estirada) {
        this.estirada = estirada;
    }

    public int getAgarre() {
        return agarre;
    }

    public void setAgarre(int agarre) {
        this.agarre = agarre;
    }

    public int getSaqueLargo() {
        return saqueLargo;
    }

    public void setSaqueLargo(int saqueLargo) {
        this.saqueLargo = saqueLargo;
    }

    public int getReflejos() {
        return reflejos;
    }

    public void setReflejos(int reflejos) {
        this.reflejos = reflejos;
    }

    public int getPosicionamiento() {
        return posicionamiento;
    }

    public void setPosicionamiento(int posicionamiento) {
        this.posicionamiento = posicionamiento;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Portero portero = (Portero) o;
        return numeroDeParadas == portero.numeroDeParadas && estirada == portero.estirada && agarre == portero.agarre && saqueLargo == portero.saqueLargo && reflejos == portero.reflejos && posicionamiento == portero.posicionamiento;
    }

    @Override
    public String toString() {
        return "Portero{" +
                "numeroDeParadas=" + numeroDeParadas +
                ", estirada=" + estirada +
                ", agarre=" + agarre +
                ", saqueLargo=" + saqueLargo +
                ", reflejos=" + reflejos +
                ", posicionamiento=" + posicionamiento +
                '}';
    }
}

