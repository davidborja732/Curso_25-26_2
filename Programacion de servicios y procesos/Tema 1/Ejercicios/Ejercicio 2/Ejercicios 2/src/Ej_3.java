public class Ej_3 {
    public static void main(String[] args) {
        // Hilo que imprime "Hola "
        Thread hiloHola = new Thread(() -> {
            try {
                for (int i = 1; i <= 15; i++) {
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("\nHilo 'Hola' interrumpido. Finalizando...");
                        break;
                    }
                    System.out.print("Hola ");
                    Thread.sleep(2000); // pausa de 2 segundos
                }
            } catch (InterruptedException e) {
                System.out.println("\nHilo 'Hola' interrumpido durante el sueño. Finalizando...");
                // Se puede hacer limpieza aquí si fuera necesario
            }
        });

        // Hilo que imprime " mundo!" + salto de línea
        Thread hiloMundo = new Thread(() -> {
            try {
                for (int i = 1; i <= 15; i++) {
                    System.out.println("mundo!");
                    Thread.sleep(2000); // pausa de 2 segundos
                }
            } catch (InterruptedException e) {
                System.out.println("Hilo 'Mundo' interrumpido.");
            }
        });

        // Lanzamos el hilo 'Hola'
        hiloHola.start();

        // Pequeño retraso para sincronizar visualmente la salida
        try {
            Thread.sleep(20); // 20 milisegundos
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Lanzamos el hilo 'Mundo'
        hiloMundo.start();

        // Esperamos 5 segundos antes de interrumpir el hilo 'Hola'
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Interrumpimos el hilo 'Hola'
        hiloHola.interrupt();
    }
}
