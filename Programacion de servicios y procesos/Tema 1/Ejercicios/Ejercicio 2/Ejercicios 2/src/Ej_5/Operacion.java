package Ej_5;

public class Operacion implements Runnable {
    Saldo saldo=new Saldo();
    private final int cantidad;
    private final String nombre;

    public Operacion(Saldo saldo, int cantidad, String nombre) {
        this.saldo = saldo;
        this.cantidad = cantidad;
        this.nombre = nombre;
    }

    public void run() {
        saldo.anadir(cantidad, nombre);
    }
}
