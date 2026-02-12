package org.example.MenuThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MenuServer {

    public static void main(String[] args) {

        try (ServerSocket server = new ServerSocket(5000)) {

            System.out.println("Servidor de menú iniciado en puerto 5000...");

            while (true) {
                // Espera a que un cliente se conecte
                Socket client = server.accept();
                System.out.println("Cliente conectado: " + client.getInetAddress());

                // Crea un hilo nuevo para ese cliente
                Thread t = new Thread(new MenuClientHandler(client));
                t.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
