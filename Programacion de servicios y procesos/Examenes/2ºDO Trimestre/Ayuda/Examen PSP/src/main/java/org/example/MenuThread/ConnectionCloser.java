package org.example.MenuThread;


import java.io.IOException;
import java.net.Socket;

public class ConnectionCloser {

    public static void close(Socket socket) {
        if (socket != null && !socket.isClosed()) {
            try {
                System.out.println("Connection closed LLamado");
                socket.close();
                System.out.println("Conexión cerrada con el cliente.");
            } catch (IOException e) {
                System.out.println("Error cerrando la conexión: " + e.getMessage());
            }
        }
    }
}

