package Ej1;

import java.time.LocalDateTime;

public class SesionCliente {
    String sesionid;
    LocalDateTime diahorapeticion=LocalDateTime.now();
    int numero=(int) (Math.random()*51);

    public String getSesionid() {
        return sesionid;
    }

    public void setSesionid(String sesionid) {
        this.sesionid = sesionid;
    }

    public LocalDateTime getDiahorapeticion() {
        return diahorapeticion;
    }

    public int getNumero() {
        return numero;
    }

    public SesionCliente() {
    }

    public SesionCliente(String sesionid) {
        this.sesionid = sesionid;
    }

    public SesionCliente(String sesionid, LocalDateTime diahorapeticion, int numero) {
        this.sesionid = sesionid;
        this.diahorapeticion = diahorapeticion;
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "SesionCliente{" +
                "sesionid='" + sesionid + '\'' +
                ", diahorapeticion=" + diahorapeticion +
                ", numero=" + numero +
                '}';
    }
}
