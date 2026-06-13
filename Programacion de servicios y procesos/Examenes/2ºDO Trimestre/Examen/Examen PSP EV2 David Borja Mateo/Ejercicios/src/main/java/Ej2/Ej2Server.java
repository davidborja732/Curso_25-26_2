package Ej2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;

public class Ej2Server {

    private static final int PORT = 7001;
    public static void main(String[] args) {
        // Arrancar el servidor
        try (ServerSocket svr = new ServerSocket(PORT);) {
            System.out.println("Puerto: " + svr.getLocalPort());

            // Aceptar peticiones en bucle
            while (true) {
                // Crear nuevo thread para cada cliente
                Socket clientSocket = svr.accept();
                new Thread(new Ej2Thread(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Server stopped");
    }
}

// Hilo que atiende a cada cliente
class Ej2Thread implements Runnable {

    private Socket socket;

    public Ej2Thread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream())
                );
        ) {
            System.out.println("Cliente conectado " + socket.getInetAddress()
                    + ":" + socket.getPort());

            String inputLine;
            int messageCount = 0;
            int multi=1;
            // Leer mensajes del cliente y hacer echo
            while ((inputLine = in.readLine()) != null) {
                int numero=Integer.parseInt(inputLine);

                do {
                    multi*=numero;
                    numero--;
                }while (numero!=0);

                // Enviar echo al cliente
                out.println("Puerto "+socket.getPort()+" Numero Recibido "+inputLine+" Factorial "+multi);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Cerrar el socket
        try {
            socket.close();
            System.out.println("Conexion con el cliente: "
                    + socket.getPort());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}