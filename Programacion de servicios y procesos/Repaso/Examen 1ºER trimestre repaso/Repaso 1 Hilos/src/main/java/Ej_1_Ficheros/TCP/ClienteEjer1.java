package Ej_1_Ficheros.TCP;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClienteEjer1 {
    private static final String HOST = "localhost";
    private static final int PUERTO = 5000;
    private static final String DIRECTORIO_IMAGENES = "imagenesusuario";

    public static void main(String[] args) {
        // Crear carpeta de imágenes del cliente si no existe
        File dirImg = new File(DIRECTORIO_IMAGENES);
        if (!dirImg.exists()) dirImg.mkdir();

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
                System.out.println("4. Listar imágenes");
                System.out.println("5. Descargar imagen");
                System.out.print("Elige opción: ");
                opcion = sc.nextLine();
                String linea;

                switch (opcion) {
                    case "1":
                        salida.println("LISTAR");
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

                    case "4":
                        salida.println("LISTARIMG");
                        while (!(linea = entrada.readLine()).equals("FIN")) {
                            System.out.println(linea);
                        }
                        break;

                    case "5":
                        System.out.print("Nombre de la imagen: ");
                        String nombreImg = sc.nextLine();
                        salida.println("DESCARGAR " + nombreImg);

                        // Leer cabecera de inicio de descarga
                        linea = entrada.readLine();
                        if (linea != null && linea.startsWith("INICIO_DESCARGA")) {
                            String[] partes = linea.split(" ");
                            String nombreArchivo = partes[1];
                            long tamaño = Long.parseLong(partes[2]);

                            File destino = new File(DIRECTORIO_IMAGENES, nombreArchivo);
                            try (FileOutputStream fos = new FileOutputStream(destino);
                                 InputStream is = socket.getInputStream()) {

                                byte[] buffer = new byte[4096];
                                long recibidos = 0;
                                while (recibidos < tamaño) {
                                    int leidos = is.read(buffer);
                                    if (leidos == -1) break;
                                    fos.write(buffer, 0, leidos);
                                    recibidos += leidos;
                                }
                                fos.flush();
                                System.out.println("Imagen descargada en: " + destino.getAbsolutePath());
                            }

                            // Leer fin de descarga
                            linea = entrada.readLine();
                            if (linea != null && linea.equals("FIN_DESCARGA")) {
                                System.out.println("Descarga completada.");
                            }
                        } else {
                            System.out.println(linea); // Mensaje de error del servidor
                        }
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
