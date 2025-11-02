package Ej_5;

public class Operacion implements Runnable {
    Saldo saldo=new Saldo();
    private int cantidad;
    private String nombre;

    public Operacion(Saldo saldo, int cantidad, String nombre) {
        this.saldo = saldo;
        this.cantidad = cantidad;
        this.nombre = nombre;
    }

    public void run() {
        saldo.añadir(cantidad, nombre);
    }
}
