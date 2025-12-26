package THreadpool;

import java.util.concurrent.*;
import java.util.Random;

public class EjemploThreadPool {

    public static void main(String[] args) {
        // Crear un pool de 4 hilos fijos
        //ExecutorService pool = Executors.newFixedThreadPool(4);//COnfiguro los hilos necesarios
        ExecutorService pool = Executors.newFixedThreadPool(10);
        Operaciones operaciones = new Operaciones();
        Random rnd = new Random();

        // Enviar 10 tareas al pool
        for (int i = 1; i <= 10; i++) {
            pool.submit(() -> {
                int a = rnd.nextInt(100) + 1;
                int b = rnd.nextInt(100) + 1;
                String hilo = Thread.currentThread().getName();

                // Construir toda la salida en una sola cadena
                String resultado =
                        "N1=" + a + " N2=" + b + " | " +"\n"+
                                hilo + " suma=" + operaciones.sumar(a, b) + " | " +"\n"+
                                hilo + " resta=" + operaciones.restar(a, b) + " | " +"\n"+
                                hilo + " multiplicacion=" + operaciones.multiplicar(a, b) + " | " +"\n"+
                                hilo + " division=" + operaciones.dividir(a, b);

                // Bloque sincronizado: solo un hilo imprime a la vez
                synchronized (System.out) {
                    System.out.println(resultado);
                }
            });
        }

        // Indicar que no se aceptarán más tareas
        pool.shutdown();

        try {
            // Esperar hasta 1 minuto a que terminen todas las tareas
            if (!pool.awaitTermination(1, TimeUnit.MINUTES)) {
                pool.shutdownNow(); // Forzar cierre si no terminan
            }
        } catch (InterruptedException e) {
            pool.shutdownNow(); // Manejar interrupción forzando cierre
        }

        // Mensaje final
        System.out.println("Todas las operaciones finalizadas.");
    }
}
