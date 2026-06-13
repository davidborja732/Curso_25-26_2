package Ej_1_Ficheros.TCP.UDP;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClienteEjer1UDP {
    private static final String HOST = "localhost";
    private static final int PUERTO = 5000;

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket();
             Scanner sc = new Scanner(System.in)) {

            String opcion;
            do {
                System.out.println("\n--- MENÚ ---");
                System.out.println("1. Listar ficheros");
                System.out.println("2. Mostrar fichero");
                System.out.println("3. Salir");
                System.out.print("Elige opción: ");
                opcion = sc.nextLine();

                String mensaje = "";
                switch (opcion) {
                    case "1":
                        mensaje = "LISTAR";
                        break;
                    case "2":
                        System.out.print("Nombre del fichero: ");
                        String nombre = sc.nextLine();
                        mensaje = "MOSTRAR " + nombre;
                        break;
                    case "3":
                        mensaje = "SALIR";
                        break;
                    default:
                        System.out.println("Opción inválida");
                        continue;
                }

                // Enviar petición
                byte[] datosEnvio = mensaje.getBytes();
                DatagramPacket paqueteEnvio = new DatagramPacket(
                        datosEnvio,
                        datosEnvio.length,
                        InetAddress.getByName(HOST),
                        PUERTO
                );
                socket.send(paqueteEnvio);

                // Recibir respuesta
                byte[] buffer = new byte[4096];
                DatagramPacket paqueteRespuesta = new DatagramPacket(buffer, buffer.length);
                socket.receive(paqueteRespuesta);

                String respuesta = new String(paqueteRespuesta.getData(), 0, paqueteRespuesta.getLength());
                System.out.println("\nRespuesta del servidor:\n" + respuesta);

            } while (!opcion.equals("3"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

