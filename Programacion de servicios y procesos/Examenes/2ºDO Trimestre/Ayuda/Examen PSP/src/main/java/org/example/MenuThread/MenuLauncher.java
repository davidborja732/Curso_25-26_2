package org.example.MenuThread;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class MenuLauncher {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("¿Cuántas conexiones quieres lanzar? ");
        int conexiones = scanner.nextInt();
        scanner.nextLine(); // limpiar salto de línea

        System.out.println("\nIniciando " + conexiones + " conexiones al MenuServer...");

        for (int i = 0; i < conexiones; i++) {

            int id = i + 1;

            Thread t = new Thread(() -> {
                try {
                    Socket socket = new Socket("localhost", 5000);
                    System.out.println("Cliente " + id + " conectado al servidor.");

                    // Cada cliente ejecuta su propio menú
                    new MenuClientHandler(socket).run();

                } catch (IOException e) {
                    System.out.println("Error conectando cliente " + id + ": " + e.getMessage());
                }
            });

            t.start();
        }
    }
}

