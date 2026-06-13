package Ej1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;

public class Ej1Server {

    private static final int PORT = 7000;


    public static void main(String[] args) {
        // Arrancar el servidor
        try (ServerSocket svr = new ServerSocket(PORT);) {
            int segundos=10;
            System.out.println("Servidor en puerto: " + svr.getLocalPort());
            // Aceptar peticiones en bucle
            while (true) {
                // Crear nuevo thread para cada cliente
                Socket clientSocket = svr.accept();
                new Thread(new Ej1Thread(clientSocket)).start();



            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Servidor finalizado");
    }
}

// Hilo que atiende a cada cliente
class Ej1Thread implements Runnable {

    private Socket socket;

    public Ej1Thread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        SesionCliente sesionCliente=new SesionCliente();
        try (
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream())
                );
        ) {
            System.out.println("Client connected: " + socket.getInetAddress()
                    + ":" + socket.getPort());

            String inputLine;
            int messageCount = 0;

            // Leer mensajes del cliente y hacer echo
            while ((inputLine = in.readLine()) != null) {
                messageCount++;
                System.out.println("Message " + messageCount + " from "
                        + socket.getPort() + ": " + inputLine);

                // Enviar echo al cliente
                sesionCliente=new SesionCliente("Sesion"+socket.getPort());

                out.println("La hora actual es "+LocalTime.now()+" tu puerto es "+socket.getPort());
                out.println(sesionCliente.getSesionid()+" "+sesionCliente.getDiahorapeticion()+" "+sesionCliente.getNumero());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        // Cerrar el socket
        try {
            socket.close();
            System.out.println("Conexion cerrada con el cliente: "
                    + socket.getPort());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}