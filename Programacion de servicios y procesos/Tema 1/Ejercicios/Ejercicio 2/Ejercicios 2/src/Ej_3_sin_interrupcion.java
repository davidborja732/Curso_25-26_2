public class Ej_3_sin_interrupcion {
    public static void main(String[] args) {
        // Creo hilo que dice hola
        Thread hiloHola = new Thread(() -> {
            try {
                // Bucle for que hace que se ejecute 15 veces
                for (int i = 1; i <= 15; i++) {
                    // Compruebo si el hilo se ha interrumpido si es el
                    // caso imprimo un mensaje y salgo del for
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("\nHilo 'Hola' interrumpido. Finalizando...");
                        break;
                    }
                    // Si no es el caso imprimo hola
                    System.out.print("Hola ");
                    // Hago un apausa en el hilo de 2 segundos
                    Thread.sleep(2000);
                }
            } catch (InterruptedException e) {
                System.out.println("Hilo 'Hola' Finalizado");
            }
        });

        // Creo el hilo hola
        Thread hiloMundo = new Thread(() -> {
            try {
                // Bucle for que hace que se ejecute 15 veces el hilo e imprima mundo!
                // ademas hago una pausa en el hilo de 2 segundos
                for (int i = 1; i <= 15; i++) {
                    System.out.println("mundo!");
                    Thread.sleep(2000); // pausa de 2 segundos
                }
            } catch (InterruptedException e) {
                System.out.println("Hilo 'Mundo' interrumpido.");
            }
        });

        // Inicio el hilo que imprime hola
        hiloHola.start();

        // Retraso para que el hilo que dice hola tenga ventaja visual sobre el hiloMundo
        try {
            Thread.sleep(20); // 20 milisegundos
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Lanzamos el hilo 'Mundo'
        hiloMundo.start();
    }
}

