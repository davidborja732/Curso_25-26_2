package Ej_5;

import java.util.Random;

public class Saldo {
    private int saldo;

    public Saldo() {
    }

    public Saldo(int inicial) {
        saldo = inicial;
    }

    public int getSaldo() {
        sleepAleatorio();
        return saldo;
    }

    private void setSaldo(int nuevo) {
        sleepAleatorio();
        saldo = nuevo;
    }

    public synchronized void anadir(int cantidad, String hilo) {
        int antes = getSaldo();
        setSaldo(antes + cantidad);
        System.out.println(hilo+" mete "+cantidad+" tenias "+antes+" tienes "+getSaldo());
    }

    private void sleepAleatorio() {
        Random random=new Random();
        try {
            Thread.sleep(random.nextInt(1500));
        } catch (InterruptedException ignored) {}
    }
}

