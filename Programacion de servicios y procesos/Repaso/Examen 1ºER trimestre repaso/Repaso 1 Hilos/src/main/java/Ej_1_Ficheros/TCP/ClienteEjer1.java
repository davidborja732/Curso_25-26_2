package Ej_1_Ficheros.TCP;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClienteEjer1 {
    private static final String HOST = "localhost";
    private static final int PUERTO = 5000;

    public static void main(String[] args) {
        try (Socket socket = new Socket(HOST, PUERTO);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
             Scanner sc = new Scanner(System.in)) {

            String opcion;
            do {
                System.out.println("\n--- MENÚ ---");
                System.out.println("1. Listar ficheros");
                System.out.println("2. Mostrar fichero");
                System.out.println("3. Salir");
                System.out.print("Elige opción: ");
                opcion = sc.nextLine();

                switch (opcion) {
                    case "1":
                        salida.println("LISTAR");
                        String linea;
                        while (!(linea = entrada.readLine()).equals("FIN")) {
                            System.out.println(linea);
                        }
                        break;
                    case "2":
                        System.out.print("Nombre del fichero: ");
                        String nombre = sc.nextLine();
                        salida.println("MOSTRAR " + nombre);
                        while (!(linea = entrada.readLine()).equals("FIN")) {
                            System.out.println(linea);
                        }
                        break;
                    case "3":
                        salida.println("SALIR");
                        System.out.println(entrada.readLine());
                        break;
                    default:
                        System.out.println("Opción inválida");
                }
            } while (!opcion.equals("3"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
