package Ej_3;

public class Ej_3_con_Interrupcion {
    public static void main(String[] args) {
        // Hilo que imprime "Hola"
        Thread hiloHola = new Thread(() -> {
            try {
                for (int i = 1; i <= 15; i++) {
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("Hilo 'Hola' Finalizado");
                        break;
                    }
                    System.out.print("Hola ");
                    Thread.sleep(2000);
                }
            } catch (InterruptedException e) {
                System.out.println("Hilo 'Hola' Finalizado");
            }
        });

        // Inicio hilo que imprime mundo!
        Thread hiloMundo = new Thread(() -> {
            try {
                for (int i = 1; i <= 15; i++) {
                    System.out.println("mundo!");
                    Thread.sleep(2000);
                }
            } catch (InterruptedException e) {
                System.out.println("Hilo 'Mundo' interrumpido.");
            }
        });

        // Inicio el hilo hola
        hiloHola.start();

        // Retraso para sincronizar salida
        try {
            Thread.sleep(20); // 20 milisegundos
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Inicio el hilo que imprime mundo!
        hiloMundo.start();

        // Espero 5 segundos antes de interrumpir el hilo 'Hola'
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        // Interrumpo el hilo hola
        hiloHola.interrupt();
    }
}
