package Ej_5;

import java.util.Random;
/*
Crea una clase llamada Saldo, con un atributo que indique el saldo disponible.
El constructor dará valor inicial al saldo. Crea los métodos get (público) y set
(privado) para el saldo incluyendo un sleep() aleatorio. Crea otro método que
reciba una cantidad y se la añada al saldo, este método debe informar de
quién realiza la operación, la cantidad, el saldo inicial y el final. Este método
debe ser definido como synchronized. Crea una clase que implemente
Runnable, desde el método run hemos de usar el método que añade la
cantidad al saldo. Crea el método main un objeto saldo con un valor inicial.
Visualiza el valor inicial. Crea varios hilos que compartan el objeto Saldo, a
cada hilo le asignamos un nombre y una cantidad. Lanzamos los hilos y
esperamos que finalicen para visualizar el saldo final. Comprueba el
funcionamiento de la aplicación si quitando synchronized de la declaración del
método.
 */
public class Ej_5_Main {

    public static void main(String[] args) {
        Saldo saldo = new Saldo(100);
        System.out.println("Saldo inicial: " + saldo.getSaldo());
        Random random=new Random();

        Thread t1 = new Thread(new Operacion(saldo, random.nextInt(1,230), "Hilo1"));
        Thread t2 = new Thread(new Operacion(saldo, random.nextInt(4,220), "Hilo2"));
        Thread t3 = new Thread(new Operacion(saldo, random.nextInt(9,90), "Hilo3"));

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException ignored) {
            System.out.println("Fallo");
        }

        System.out.println("Saldo final: " + saldo.getSaldo());
    }
}

