package Ej_3;

import java.util.ArrayList;
import java.util.List;

public class Ej_4 {
    static void main() {
        List<String> lista=List.of( "Programas”, “Procesos”, “Servicios", "Hilos");
        Thread hiloMain=new Thread(() -> {
            System.out.println("Tiempo de espera 3s\n"+" Esperando a que el hilo hilo0 termine\n"+" Todavia esperando");
            Thread hilo0=new Thread(() -> {
                for (String palabra:lista){
                    System.out.println(palabra);
                }
                try {
                    Thread.sleep(3);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            hilo0.start();
        });
        hiloMain.start();
    }
}
