package Ej1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Ej1Client {

    private static final int MAX_MESSAGES = 10;
    private static final String HOST = "localhost";
    private static final int PORT = 7000;

    public static void main(String[] args) {


        // Conectar al servidor
        try (
                Socket socket = new Socket(HOST, PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream())
                );
        ) {

                String message = "Dime la hora";
                SesionCliente sesionCliente=new SesionCliente();
                // Enviar mensaje al servidor
                System.out.println("Envio " + message);
                out.println(message);

                // Recibir el echo del servidor
                String response = in.readLine();
                System.out.println(response);
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                String[] caracteristicasCliente=inputLine.split(" ");

                sesionCliente=new SesionCliente(caracteristicasCliente[0], LocalDateTime.parse(caracteristicasCliente[1]),Integer.parseInt(caracteristicasCliente[2]));
                System.out.println("La informacion del cliente es=");
                System.out.println(sesionCliente.getSesionid()+" "+sesionCliente.getDiahorapeticion()+" "+sesionCliente.getNumero());
            }
        } catch (IOException e) {
            System.err.println("Error al enviar o recibir mensaje");
        }
    }
}