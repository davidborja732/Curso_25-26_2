package Ej_1;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClientNames {
    private static final String SERVER = "127.0.0.1";
    private static final int PORT = 5000;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER, PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.println("\n--- Cliente de Imágenes (solo nombres) ---");
                System.out.println("1. Listar imágenes");
                System.out.println("2. Subir imagen (nombre)");
                System.out.println("3. Descargar imagen (simulada)");
                System.out.println("4. Salir");
                System.out.print("Elige una opción: ");
                String opcion = sc.nextLine();

                if (opcion.equals("1")) {
                    out.println("LISTAR");
                    String response = in.readLine();
                    System.out.println("Imágenes disponibles: " + response);

                } else if (opcion.equals("2")) {
                    System.out.print("Introduce el nombre de la nueva imagen: ");
                    String nombre = sc.nextLine();
                    out.println("SUBIR " + nombre);
                    String response = in.readLine();
                    System.out.println(response);

                } else if (opcion.equals("3")) {
                    System.out.print("Introduce el nombre de la imagen a descargar: ");
                    String nombre = sc.nextLine();
                    out.println("DESCARGAR " + nombre);
                    String response = in.readLine();
                    System.out.println(response);

                } else if (opcion.equals("4")) {
                    out.println("SALIR");
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
