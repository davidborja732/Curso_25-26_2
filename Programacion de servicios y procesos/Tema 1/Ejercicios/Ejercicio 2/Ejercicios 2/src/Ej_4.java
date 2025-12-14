import java.util.List;
/*
Crea una aplicación que conste de 2 hilos; el primero el hilo principal de la
aplicación Java. El hilo principal deberá lanzar un nuevo hilo encargado de
imprimir por consola los siguientes mensajes con un intervalo de 4 segundos
entre cada uno de ellos (Mensajes: “Programas”, “Procesos”, “Servicios”,
“Hilos”). El hilo principal debe quedar a la espera hasta que termine,
mostrando cada segundo que está esperando por la finalización del hilo hijo.
La ejecución del programa debe durar 16s ya que son 4 mensajes y 4s de
espera por cada uno.
Para poder reducir la duración de la ejecución el programa debe aceptar por
parámetro (método main) el tiempo de espera máximo que el hilo principal
esperará a la ejecución del hilo secundario, una vez superado ese tiempo el
hilo principal debe interrumpir la ejecución del hilo secundario y a partir de
ese momento el hilo secundario mostrará los mensajes restantes sin esperas
entre la impresión de los mensajes para finalizar la ejecución del hilo cuanto
antes. El hilo principal debe mostrar por consola el mensaje de finalización de
la ejecución del programa. Puedes imprimir los mensajes que consideres
oportunos para verificar la correcta ejecución del programa. Calcula el tiempo
de ejecución del hilo principal y muéstralo por consola. Incluye el nombre del
hilo que imprime por consola cada vez que muestres un mensaje de salida.
* */
public class Ej_4 {
    public static void main(String[] args) {
        //int tiempoMax = Integer.parseInt(args[0]);
        int tiempoMax =10;
        String[] mensajes = {"Programas", "Procesos", "Servicios", "Hilos"};

        Thread hilo = new Thread(() -> {
            for (int i = 0; i < mensajes.length; i++) {
                System.out.println("Hilo 2 " + mensajes[i]);
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    for (int j = i + 1; j < mensajes.length; j++)
                        System.out.println("Hilo 2 "+mensajes[j]);
                    break;
                }
            }
        });

        long inicio = System.currentTimeMillis();
        hilo.start();

        for (int i = 1; i < tiempoMax+1 && hilo.isAlive(); i++) {
            System.out.println("Hilo 1 esperando "+i+" s");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {}
        }

        if (hilo.isAlive()) {
            System.out.println("Hilo 1 tiempo agotado");
            hilo.interrupt();
        }

        try {
            hilo.join();
        } catch (InterruptedException ignored) {}

        long duracion = (System.currentTimeMillis() - inicio) / 1000;
        System.out.println("Hilo 1 finalizado ha durado: " + duracion);
    }
}


