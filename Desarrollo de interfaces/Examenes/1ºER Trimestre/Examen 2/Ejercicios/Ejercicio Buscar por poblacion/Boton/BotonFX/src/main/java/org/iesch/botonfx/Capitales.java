package org.iesch.botonfx;

public class Capitales {
    String provincia;
    String autonomia;
    int poblacion;

    public Capitales() {
    }

    public Capitales(String provincia, String autonomia, int poblacion) {
        this.provincia = provincia;
        this.autonomia = autonomia;
        this.poblacion = poblacion;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getAutonomia() {
        return autonomia;
    }

    public void setAutonomia(String autonomia) {
        this.autonomia = autonomia;
    }

    public int getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(int poblacion) {
        this.poblacion = poblacion;
    }

    @Override
    public String toString() {
        return "Capitales{" +
                "provinvia='" + provincia + '\'' +
                ", autonomia='" + autonomia + '\'' +
                ", poblacion='" + poblacion + '\'' +
                '}';
    }
}
