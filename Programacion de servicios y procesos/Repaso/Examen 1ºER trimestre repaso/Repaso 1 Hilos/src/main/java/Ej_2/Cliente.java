package Ej_2;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    private static final String HOST = "localhost";
    private static final int PUERTO = 5000;

    public static void main(String[] args) {
        try (Socket socket = new Socket(HOST, PUERTO);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
             Scanner sc = new Scanner(System.in)) {

            String opcion;
            do {
                System.out.println("1. Listar contactos");
                System.out.println("2. Buscar contacto");
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
                        System.out.print("Nombre del contacto: ");
                        String nombre = sc.nextLine().toLowerCase();
                        salida.println("BUSCAR " + nombre);
                        System.out.println(entrada.readLine());
                        break;
                    case "3":
                        salida.println("SALIR");
                        System.out.println(entrada.readLine());
                        break;
                    default:
                        System.out.println("Opcion invalida");
                }
            } while (!opcion.equals("3"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

